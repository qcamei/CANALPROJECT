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

public class MoneyCheckAddAction extends ActionSupport {
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
		String sql = "", sql1 = "", sql2 = "";
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
		String checkName = DecodeUtils.decodeRequestString(request
				.getParameter("checkName"));
		String checkNumber = DecodeUtils.decodeRequestString(request
				.getParameter("checkNumber"));
		String moneyLogin = DecodeUtils.decodeRequestString(request
				.getParameter("moneyLogin"));

		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));

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
					if (currentStep != 6) {
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

				sql1 = "SELECT * FROM canal_infomation WHERE in_id='" + inId
						+ "'";
				Map resultMap1 = session.findSql(sql1).get(0);
				String agentInId = (String) resultMap1.get("agentInId");

				sql2 = "SELECT * FROM agent_information WHERE in_id='"
						+ agentInId + "'";
				Map resultMap2 = session.findSql(sql2).get(0);
				String agentId = (String) resultMap2.get("agentId");
				// 更新代理商信息中的代理商名称
				// sql = "update qdzc.agent_information set agent_name = '"
				// + agentName + "' where in_id='" + agentInId + "'";
				// logger.info("更新渠道状态表：" + sql);
				// session.update(sql);
				// // 更新代理商信息中的代理商名称
				// sql = "update qdzc.canal_infomation set agent_name = '"
				// + agentName + "' where in_id='" + inId + "'";
				// logger.info("更新渠道状态表：" + sql);
				// session.update(sql);
				//
				// // 更新渠道用户表中的代理商名称
				// sql = "update qdzc.canal_user set user_agent = '" + agentName
				// + "' where canal_in_id='" + inId + "'";
				// logger.info("更新渠道状态表：" + sql);
				// session.update(sql);
				//
				// // 更新代理商管理系统中的代理商名称
				// sql = "update qdzc.process4_agent_canal set agent_name='"
				// + agentName + "' where in_id='" + inId + "'";
				// logger.info("更新渠道状态表：" + sql);
				// session.update(sql);

				// 3.更新渠道状态表
				sql = "update qdzc.canal_step_state set current_step = "
						+ currentStep + ",check_code_time=sysdate(),"
						+ "check_code_name='" + currentStepVal
						+ "',check_code_state='通过' where in_id='" + inId + "'";
				logger.info("更新渠道状态表：" + sql);
				session.update(sql);

				// 2015年1月26号
				sql = "update qdzc.canal_infomation set check_name='"
						+ checkName + "'," + "check_number='" + checkNumber
						+ "',money_login='" + moneyLogin + "'"
						+ " where in_id='" + inId + "'";
				session.update(sql);

				// 4.操作入环节“配置稽核人员”
				sql = "insert into qdzc.process7_check_code (in_id,agent_id,check_name,check_number,money_login,oper_user,dept_id,dept_name,oper_time,remark) values ("
						+ "'"
						+ inId
						+ "','"
						+ agentId
						+ "','"
						+ checkName
						+ "','"
						+ checkNumber
						+ "','"
						+ moneyLogin
						+ "','"
						+ userName
						+ "','"
						+ deptId
						+ "','"
						+ deptName
						+ "',sysdate(),'" + remark + "')";
				logger.info("操作入环节“资金稽核厅编码”：" + sql);
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

				sql = "select canal_user_name from qdzc.canal_infomation where in_id = '"
						+ inId + "'";
				List list2 = session.findSql(sql);
				Map map = (Map) list2.get(0);
				String user = map.get("canalUserName").toString();
				// 资金稽核编码-->稽核操作
				MailRemindUtil.mailJHCZ(user, canalName);

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