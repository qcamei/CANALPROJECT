package com.cqupt.canalCloseFlow;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.cqupt.service.MailRemindUtil;
import com.opensymphony.xwork2.ActionSupport;
import common.Logger;

public class WriteCanalCloseContentQDGSAction extends ActionSupport {
	// 分管部门审核函数
	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		try {
			String closeId = request.getParameter("closeId");
			String inId = request.getParameter("inId");
			String refuseReason = request.getParameter("refuseReason");

			String auditState = DecodeUtils.decodeRequestString(request
					.getParameter("auditState"));

			String deptName = request.getSession().getAttribute("deptName")
					.toString();
			String deptId = request.getSession().getAttribute("deptId")
					.toString();

			String userName = request.getSession().getAttribute("userName")
					.toString();
			if (refuseReason == null) {
				refuseReason = "";
			} else {
				refuseReason = java.net.URLDecoder
						.decode(refuseReason, "UTF-8");
				System.out.println(refuseReason);
			}
			// String userId = request.getSession().getAttribute("userId")
			// .toString();
			HttpServletResponse response = ServletActionContext.getResponse();
			// 设置字符集
			response.setCharacterEncoding("UTF-8");
			PrintWriter out;

			out = response.getWriter();
			// 直接输入响应的内容
			out.print(getList(inId, closeId, refuseReason, auditState,
					deptName, deptId, userName));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	public String getList(String inId, String closeId, String refuseReason,
			String auditState, String deptName, String deptId, String userName) {
		String resultStr = "success";
		String sql = "";
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			int currentStep = 0;
			if (resultStr.equals("success")) {
				// 1.查询当前步骤
				sql = "select current_step from qdzc.canal_step_state_close where  in_id = '"
						+ inId + "'";
				logger.info(sql);
				List list1 = session.findSql(sql);
				if (list1.size() > 0) {
					Map map = (Map) list1.get(0);
					currentStep = (Integer) map.get("currentStep");
					if (currentStep != 5) {
						resultStr = "该状态下不能做此操作，请刷新后重试！";
					}
				} else {
					resultStr = "渠道不存在！";
				}
			}
			if (resultStr.equals("success")) {

				// 2. 获得当前状态的下一状态，并取得相应的值
				sql = "SELECT * FROM step_info_close WHERE pre_step_no="
						+ currentStep;
				Map resultMap = session.findSql(sql).get(0);
				currentStep = (Integer) resultMap.get("stepNo");
				String currentStepVal = resultMap.get("stepVal").toString();
				sql = "SELECT * FROM step_info_close WHERE pre_step_no="
						+ currentStep;
				Map resultMap1 = session.findSql(sql).get(0);
				String nextStepVal = resultMap1.get("stepVal").toString();
				// 3.更新相关数据库

				sql = "select * from qdzc.canal_infomation where in_id='"
						+ inId + "'";
				logger.info("查询详细信息" + sql);
				// 生产当前时间
				Date now = new Date();
				logger.info(now);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String operTime = sdf.format(now);
				logger.info(operTime);
				List<Map<String, Object>> resultList = session.findSql(sql);
				Map<String, Object> resultMap2 = (Map<String, Object>) resultList
						.get(0);
				String canalId = (String) resultMap2.get("canalId");
				String canalName = (String) resultMap2.get("canalName");

				// 判断审核状态
				if ("通过".equals(auditState)) {
					sql = "insert into qdzc.canal_infomation_close(close_id,user_name,dept_name,dept_id,audit_state,canal_id,canal_name,current_step_val,current_step,next_step_val,oper_time,in_id,refuse_reason)values('"
							+ closeId
							+ "','"
							+ userName
							+ "','"
							+ deptName
							+ "','"
							+ deptId
							+ "','"
							+ auditState
							+ "','"
							+ canalId
							+ "','"
							+ canalName
							+ "','"
							+ currentStepVal
							+ "','"
							+ currentStep
							+ "','"
							+ nextStepVal
							+ "','"
							+ operTime
							+ "','"
							+ inId
							+ "','" + refuseReason + "')";
					session.add(sql);

					// 添加流程状态表
					sql = "update  qdzc.canal_step_state_close set oper_user5="
							+ "'" + userName + "',dept_id5='" + deptId
							+ "',dept_name5='" + deptName + "',"
							+ " agent_number_time='" + operTime + "', "
							+ "agent_number_name='" + currentStepVal
							+ "',agent_number_state='" + auditState
							+ "',current_step='" + currentStep
							+ "' where in_id='" + inId + "'";

					logger.info("添加流程状态表：" + sql);
					session.update(sql);
					sql = "select oper_user from qdzc.canal_infomation where  in_id = '"
							+ inId + "'";
					List list2 = session.findSql(sql);
					Map map = (Map) list2.get(0);
					String user = map.get("operUser").toString();
					MailRemindUtil.mailKDLSMHWZ(user, canalName);

				} else if ("驳回".equals(auditState)) {
					sql = "insert into qdzc.canal_infomation_close(close_id,user_name,dept_name,dept_id,audit_state,canal_id,canal_name,current_step_val,current_step,next_step_val,oper_time,in_id,refuse_reason)values('"
							+ closeId
							+ "','"
							+ userName
							+ "','"
							+ deptName
							+ "','"
							+ deptId
							+ "','"
							+ auditState
							+ "','"
							+ canalId
							+ "','"
							+ canalName
							+ "','"
							+ currentStepVal
							+ "','"
							+ currentStep
							+ "','"
							+ nextStepVal
							+ "','"
							+ operTime
							+ "','"
							+ inId
							+ "','" + refuseReason + "')";
					session.add(sql);

					sql = "update  qdzc.canal_step_state_close set oper_user5="
							+ "'" + userName + "',dept_id5='" + deptId
							+ "',dept_name5='" + deptName + "',"
							+ " agent_number_time='" + operTime + "', "
							+ "agent_number_name='" + currentStepVal
							+ "',agent_number_state='" + auditState
							+ "',current_step='" + currentStep
							+ "' where in_id='" + inId + "'";

					logger.info("添加流程状态表：" + sql);
					session.update(sql);

					sql = "update qdzc.canal_infomation_close set audit_state='驳回' where close_id='"
							+ closeId + "' and  current_step=1";

					logger.info("添加流程状态表：" + sql);
					session.update(sql);
					sql = "select oper_user from qdzc.canal_infomation where  in_id = '"
							+ inId + "'";
					List list2 = session.findSql(sql);
					Map map = (Map) list2.get(0);
					String user = map.get("operUser").toString();
					// MailRemindUtil.mailSQ(user, canalName);
				}

			}

			session.closeSession();

		} catch (Exception e) {
			resultStr = "error";
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return resultStr;

	}
}
