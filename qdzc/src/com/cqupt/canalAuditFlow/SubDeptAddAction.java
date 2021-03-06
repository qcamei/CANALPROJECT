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

public class SubDeptAddAction extends ActionSupport {
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
		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));
		String auditState = DecodeUtils.decodeRequestString(request
				.getParameter("auditState"));

		// 当前时间
		// Date now = new Date();
		// logger.info(now);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// // HH表示24小时制
		// // 如果换成hh表示12小时制
		// String checkTime = sdf.format(now);
		// logger.info(checkTime);

		// 必填项：缺少必要信息，返回
		if (inId.equals("") || canalName.equals("")) {
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
				// 1.判断环节“配置稽核人员”
				sql = "select current_step from qdzc.canal_step_state where  in_id = '"
						+ inId + "'";
				logger.info(sql);
				List list1 = session.findSql(sql);
				if (list1.size() > 0) {
					Map map = (Map) list1.get(0);
					currentStep = (Integer) map.get("currentStep");
					if (currentStep != 2) {
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

				// 根据CRM是否接入判断流程是到分管部门审核结束
				sql = "select is_crm from qdzc.canal_infomation where in_id='"
						+ inId + "'";
				Map resultMap2 = session.findSql(sql).get(0);
				String isCrm = (String) resultMap2.get("isCrm");
				if (!isCrm.equals("接入")) {
					sql = "SELECT * FROM step_info WHERE step_no='11'";
					Map resultMap3 = session.findSql(sql).get(0);
					currentStep = (Integer) resultMap3.get("stepNo");
					currentStepVal = resultMap.get("stepVal").toString();
				}

				// 3.更新渠道状态表

				// 当audit_state是驳回时，只更新流程表，不把数据更新到到canal_step_state中
				if (auditState.equals("通过")) {
					sql = "update qdzc.canal_step_state set current_step = "
							+ currentStep + ",department_audit_time=sysdate(),"
							+ "department_audit_name='" + currentStepVal
							+ "',department_audit_state='" + auditState
							+ "',is_back='" + isBack
							+ "',reason='' where in_id='" + inId + "'";
					logger.info("更新渠道状态表：" + sql);
					session.update(sql);

					// 4.操作入环节“分管部门审核”
					sql = "insert into qdzc.process3_department_audit (in_id,canal_id,canal_name,oper_user,dept_id,dept_name,oper_time,audit_state,remark) values ("
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
							+ auditState
							+ "','"
							+ remark + "')";
					logger.info("操作入环节“分管部门审核”：" + sql);
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
							+ remark + "')";
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
					sql = "select canal_user_name from qdzc.canal_infomation where in_id = '"
							+ inId + "'";
					List list2 = session.findSql(sql);
					Map map = (Map) list2.get(0);
					String user = map.get("canalUserName").toString();
					// 分管部门审核-->财务审核
					MailRemindUtil.mailCWSH(user, canalName);
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