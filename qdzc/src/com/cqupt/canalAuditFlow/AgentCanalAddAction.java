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

public class AgentCanalAddAction extends ActionSupport {
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
		String operUser = request.getSession().getAttribute("userName")
				.toString();

		String inId = DecodeUtils.decodeRequestString(request
				.getParameter("inId"));
		String canalId = DecodeUtils.decodeRequestString(request
				.getParameter("canalId"));
		String canalName = DecodeUtils.decodeRequestString(request
				.getParameter("canalName"));

		String agentId = DecodeUtils.decodeRequestString(request
				.getParameter("agentId"));
		String agentName = DecodeUtils.decodeRequestString(request
				.getParameter("agentName"));
		String auditState = DecodeUtils.decodeRequestString(request
				.getParameter("auditState"));
		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));

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
				// 1.判断环节“代理商系统渠道信息确认”
				sql = "select current_step from qdzc.canal_step_state where  in_id = '"
						+ inId + "'";
				logger.info(sql);
				List list1 = session.findSql(sql);
				if (list1.size() > 0) {
					Map map = (Map) list1.get(0);
					currentStep = (Integer) map.get("currentStep");
					if (currentStep != 13) {
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
				if (auditState.equals("通过")) {
					sql = "update qdzc.canal_step_state set canal_id='"
							+ canalId + "',canal_name='" + canalName
							+ "',current_step = " + currentStep
							+ ",agent_canal_time=sysdate(),"
							+ "agent_canal_name='" + currentStepVal
							+ "',agent_canal_state='通过' where in_id='" + inId
							+ "'";
					logger.info("更新渠道状态表：" + sql);
					session.update(sql);
					// 4.在第四步的表中插入数据
					sql = "insert into qdzc.process4_agent_canal (in_id,canal_id,canal_name,agent_id,agent_name,oper_user,dept_id,dept_name,oper_time,remark) values ("
							+ "'"
							+ inId
							+ "','"
							+ canalId
							+ "','"
							+ canalName
							+ "','"
							+ agentId
							+ "','"
							+ agentName
							+ "','"
							+ operUser
							+ "','"
							+ deptId
							+ "','"
							+ deptName
							+ "',sysdate(),'" + remark + "')";
					logger.info("操作四环节“代理商系统渠道信息确认”：" + sql);
					session.add(sql);
					// 5 更新渠道信息和代理商信息
					sql = "update qdzc.canal_infomation set canal_id = '"
							+ canalId + "',canal_name='" + canalName + "',"
							+ "agent_id='" + agentId + "',agent_name='"
							+ agentName + "' where in_id='" + inId + "'";
					logger.info("更新渠信息表：" + sql);
					session.update(sql);

					// 6.根据渠道信息表的in_id得到与之对应的agent_in_id
					sql = "SELECT agent_in_id,canal_user_name FROM qdzc.canal_infomation WHERE in_id='"
							+ inId + "'";
					Map resultMap2 = session.findSql(sql).get(0);
					String agentInId = resultMap2.get("agentInId").toString();
					String user = resultMap2.get("canalUserName").toString();

					// 7 更新代理商信息表
					sql = "update qdzc.agent_information set  agent_id='"
							+ agentId + "',agent_name='" + agentName
							+ "' where in_id='" + agentInId + "'";
					logger.info("更新代理商信息表：" + sql);
					session.update(sql);

					// 7 更新流程二 分公司审核
					sql = "update qdzc.process2_branch_audit set  canal_id='"
							+ canalId + "',canal_name='" + canalName
							+ "' where in_id='" + inId + "'";
					logger.info("更新 分公司审核：" + sql);
					session.update(sql);

					// 8 更新流程三 分管部门审核
					sql = "update qdzc.process3_department_audit set canal_id='"
							+ canalId
							+ "',canal_name='"
							+ canalName
							+ "' where in_id='" + inId + "'";
					logger.info("更新 分管部门审核：" + sql);
					session.update(sql);

					// 9 更新渠道员工表
					sql = "update qdzc.canal_user set canal_id='" + canalId
							+ "',canal_name='" + canalName + "',user_agent='"
							+ agentName + "' where canal_in_id='" + inId + "'";
					logger.info("更新 分管部门审核：" + sql);
					session.update(sql);

					// 更新环节详情

					sql = "update qdzc.process_detail_add set  canal_id='"
							+ canalId + "',canal_name='" + canalName
							+ "' where in_id='" + inId + "'";
					logger.info("更新环节详情：" + sql);
					session.update(sql);

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
							+ operUser
							+ "','"
							+ deptId
							+ "','"
							+ deptName + "',sysdate(),'通过"

							+ "','" + remark + "')";
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
				// 代理商管理系统完成后，通知开CRM工号的处理
				if (!auditState.equals("驳回")) {

					sql = "select canal_user_name from qdzc.canal_infomation where  in_id = '"
							+ inId + "'";
					List list2 = session.findSql(sql);
					Map map = (Map) list2.get(0);
					String user1 = map.get("canalUserName").toString();
					// 分公司审核-->分管部门审核
					MailRemindUtil.mailFGBM(user1, canalName);
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