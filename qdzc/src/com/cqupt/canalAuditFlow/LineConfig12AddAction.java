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

public class LineConfig12AddAction extends ActionSupport {
	private static final long serialVersionUID = -3503583143062479748L;
	Logger logger = Logger.getLogger(this.getClass());
	HttpServletRequest request = null;
	HttpServletResponse response = null;

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

		String broadbandAccount = DecodeUtils.decodeRequestString(request
				.getParameter("broadbandAccount"));
		String broadbandPassword = DecodeUtils.decodeRequestString(request
				.getParameter("broadbandPassword"));
		// String privilegeInfo =
		// DecodeUtils.decodeRequestString(request.getParameter("privilegeInfo"));
		String wwwAccount = DecodeUtils.decodeRequestString(request
				.getParameter("wwwAccount"));
		String wwwPrivilegeInfo = DecodeUtils.decodeRequestString(request
				.getParameter("wwwPrivilegeInfo"));
		// String noPaperAccount =
		// DecodeUtils.decodeRequestString(request.getParameter("noPaperAccount"));
		// String noPaperPrivilegeInfo =
		// DecodeUtils.decodeRequestString(request.getParameter("noPaperPrivilegeInfo"));

		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));

		// 必填项：缺少必要信息，返回
		if (inId.equals("") || canalName.equals("")
				|| broadbandAccount.equals("")) {
			return "infoLoss";
		}

		try {
			session = DataStormSession.getInstance();
			int currentStep = 0, parallelStep = 0;
			if (resultStr.equals("success")) {
				// 1.判断环节“配置稽核人员”
				sql = "select current_step,parallel_step from qdzc.canal_step_state where  in_id = '"
						+ inId + "'";
				logger.info(sql);
				List list1 = session.findSql(sql);
				if (list1.size() > 0) {
					Map map = (Map) list1.get(0);
					currentStep = (Integer) map.get("currentStep");
					parallelStep = (Integer) map.get("parallelStep");
					if ((currentStep <= 3 && currentStep >= 1)
							|| (currentStep <= 11 && currentStep >= 9)
							|| parallelStep == 12) {
						resultStr = "该状态下不能做此操作，请刷新后重试！";
					}
				} else {
					resultStr = "渠道不存在！";
				}
			}

			if (resultStr.equals("success")) {

				// 2. 获得环节１２的名称
				sql = "SELECT step_val FROM step_info WHERE step_no=12";
				Map resultMap = session.findSql(sql).get(0);
				currentStep = 12;
				String currentStepVal = resultMap.get("stepVal").toString();

				// 3.更新渠道状态表

				sql = "update qdzc.canal_step_state set parallel_step='12',config_line12_time=sysdate(),"
						+ "config_line12_name='"
						+ currentStepVal
						+ "',config_line12_state='通过' where in_id='"
						+ inId
						+ "'";
				logger.info("１２.更新渠道状态表：" + sql);
				session.update(sql);
				// 将专线信息写入canal_infomation表
				sql = "update qdzc.canal_infomation set broadband_account='"
						+ broadbandAccount + "',broadband_password='"
						+ broadbandPassword + "',www_account='" + wwwAccount
						+ "',www_privilege_info='" + wwwPrivilegeInfo
						+ "' where in_id='" + inId + "' ";
				logger.info("更新canal_iinfoamtion表：" + sql);
				session.update(sql);

				// 4.操作环节“专线受理”
				sql = "insert into qdzc.process12_config_line (in_id,canal_id,canal_name,"
						+ "broadband_account,broadband_password,privilege_info,www_account,www_privilege_info,"
						+ "oper_user,dept_id,dept_name,oper_time,remark) values ("
						+ "'"
						+ inId
						+ "','"
						+ canalId
						+ "','"
						+ canalName
						+ "',"
						+ "'"
						+ broadbandAccount
						+ "', '"
						+ broadbandPassword
						+ "','','"
						+ wwwAccount
						+ "','"
						+ wwwPrivilegeInfo
						+ "',"
						+ "'"
						+ userName
						+ "','"
						+ deptId
						+ "','"
						+ deptName + "',sysdate(),'" + remark + "')";
				logger.info("操作环节“专线受理”：" + sql);
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
						+ "',sysdate(),'通过"

						+ "','" + remark + "')";
				logger.info("操作入环节详情：" + sql);
				session.add(sql);
				sql = "select canal_user_name from qdzc.canal_infomation where  in_id = '"
						+ inId + "'";
				List list2 = session.findSql(sql);
				Map map = (Map) list2.get(0);
				String user = map.get("canalUserName").toString();
				// 专线受理确认-->基础设施配置
				MailRemindUtil.mailSBJCPZ(user, canalName);
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