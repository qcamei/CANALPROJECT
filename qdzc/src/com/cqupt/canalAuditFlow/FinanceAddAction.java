package com.cqupt.canalAuditFlow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.cqupt.service.MailRemindUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FinanceAddAction extends ActionSupport {
	private static final long serialVersionUID = -3503583143062479748L;
	Logger logger = Logger.getLogger(this.getClass());
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	String standbyPhoneId = "null";

	@Override
	public String execute() {

		try {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(insertIntoDatabase());
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private String insertIntoDatabase() {
		String resultStr = "success";
		String sql = "";
		request = ServletActionContext.getRequest();
		DataStormSession session = null;

		String deptId = request.getSession().getAttribute("deptId").toString();
		String deptName = request.getSession().getAttribute("deptName")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();

		String inId = DecodeUtils.decodeRequestString(request
				.getParameter("inId"));
		String canalId = DecodeUtils.decodeRequestString(request
				.getParameter("canalId"));
		String canalName = DecodeUtils.decodeRequestString(request
				.getParameter("canalName"));

		String auditState = DecodeUtils.decodeRequestString(request
				.getParameter("auditState"));

		String isPay = DecodeUtils.decodeRequestString(request
				.getParameter("isPay"));

		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));

		// 必填项：缺少必要信息，返回
		if (inId.equals("") || canalName.equals("") || auditState.equals("")) {
			return "infoLoss";
		}
		String isBack = "无";
		if (!auditState.equals("通过")) {
			isBack = "驳回";
		}
		try {
			session = DataStormSession.getInstance();
			int currentStep = 0;
			if (resultStr.equals("success")) {
				// 1.判断环节“财务审核”
				sql = "select current_step from qdzc.canal_step_state where  in_id = '"
						+ inId + "'";
				logger.info(sql);
				List list1 = session.findSql(sql);
				if (list1.size() > 0) {
					Map map = (Map) list1.get(0);
					currentStep = (Integer) map.get("currentStep");
					if (currentStep != 3) {
						resultStr = "该状态下不能做此操作，请刷新后重试！";
					}
				} else {
					resultStr = "渠道不存在！";
				}
			}

			if (resultStr.equals("success")) {

				// 2. 获得当前状态的下一状态
				sql = "SELECT * FROM step_info WHERE pre_step_no="
						+ currentStep;
				Map resultMap = session.findSql(sql).get(0);
				currentStep = (Integer) resultMap.get("stepNo");
				String currentStepVal = resultMap.get("stepVal").toString();
				// 3.更新渠道状态表

				// 当auditState是驳回时，只更新流程表，不把数据更新到到canal_step_state中
				if (auditState.equals("通过")) {
					sql = "update qdzc.canal_step_state set current_step = "
							+ currentStep + ",is_back='" + isBack
							+ "',reason='' where in_id='" + inId + "'";
					logger.info("更新渠道状态表：" + sql);
					session.update(sql);
					// 4.操作入环节“财务审核”
					sql = "insert into qdzc.process13_finance (in_id,canal_id,canal_name,oper_user,dept_id,dept_name,oper_time,remark,is_pay,audit_state) values ("
							+ "'"
							+ inId
							+ "','"
							+ canalId
							+ "','"
							+ canalName
							+ "','"
							+ userName
							+ "','"
							+ deptId
							+ "','"
							+ deptName
							+ "',sysdate(),'"
							+ remark
							+ "','"
							+ isPay + "','" + auditState + "')";
					logger.info("操作入环节“账务审核”：" + sql);
					session.add(sql);

					// 5.插入环节详情
					sql = "insert into qdzc.process_detail_add (in_id,canal_id,canal_name,step_no,step_val,oper_user,dept_id,dept_name,oper_time,process_state,remark) values ("
							+ "'"
							+ inId
							+ "','"
							+ canalId
							+ "','"
							+ canalName
							+ "','"

							+ currentStep
							+ "','"
							+ currentStepVal
							+ "','"
							+ userName
							+ "','"
							+ deptId
							+ "','"
							+ deptName
							+ "',sysdate(),'"
							+ auditState
							+ "','"
							+ remark
							+ "')";
					logger.info("操作入环节详情：" + sql);
					session.add(sql);
				} else {
					sql = "update qdzc.canal_step_state set branch_audit_time=sysdate(),branch_audit_state='"
							+ auditState
							+ "',is_back='"
							+ isBack
							+ "',reason='"
							+ remark
							+ "' where in_id='"
							+ inId
							+ "'";
					logger.info("更新渠道状态表：" + sql);
					session.update(sql);
				}
				if (!auditState.equals("驳回")) {
					sql = "select canal_user_name from qdzc.canal_infomation where  in_id = '"
							+ inId + "'";
					List list2 = session.findSql(sql);
					Map map = (Map) list2.get(0);
					String user = map.get("canalUserName").toString();
					// 财务审核————>开代理商管理系统
					MailRemindUtil.mailDLSGL(user, canalName);
				}

				session.closeSession();
			}
		} catch (CquptException e) {
			resultStr = "error";
			e.printStackTrace();
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
		}

		logger.info(resultStr);
		return resultStr;
	}
}