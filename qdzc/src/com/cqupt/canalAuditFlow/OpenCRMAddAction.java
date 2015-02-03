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

public class OpenCRMAddAction extends ActionSupport {
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
		String crmNumber = DecodeUtils.decodeRequestString(request
				.getParameter("crmNumber"));
		String telephone = DecodeUtils.decodeRequestString(request
				.getParameter("telephone"));
		String privilege = DecodeUtils.decodeRequestString(request
				.getParameter("privilege"));
		String employeeName = DecodeUtils.decodeRequestString(request
				.getParameter("employeeName"));
		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));

		request.getSession().setAttribute("QQ", "LZ" + crmNumber);

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
					if (currentStep != 4) {
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

				sql = "update qdzc.canal_step_state set current_step = "
						+ currentStep + ",crm_time=sysdate()," + "crm_name='"
						+ currentStepVal + "',crm_state='通过' where in_id='"
						+ inId + "'";
				logger.info("更新渠道状态表：" + sql);
				session.update(sql);
				// 4.操作入环节“配置稽核人员”
				sql = "insert into qdzc.process5_open_CRM (in_id,canal_id,canal_name,employee_name,crm_number,telephone,privilege,oper_user,dept_id,dept_name,oper_time,remark) values ("
						+ "'"
						+ inId
						+ "','"
						+ canalId
						+ "','"
						+ canalName
						+ "','"
						+ employeeName
						+ "','"
						+ crmNumber
						+ "','"
						+ telephone
						+ "','"
						+ privilege
						+ "','"
						+ userName
						+ "','"
						+ deptId
						+ "','" + deptName + "',sysdate(),'" + remark + "')";
				logger.info("操作入环节“配置稽核人员”：" + sql);
				session.add(sql);
				// 2015年1月26号
				sql = "update qdzc.canal_infomation set crm_number='"
						+ crmNumber + "'where in_id='" + inId + "'";
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
				// 开CRM工号-->开代理商门户网站工号
				MailRemindUtil.mailKDLSMHWZ(user, canalName);
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