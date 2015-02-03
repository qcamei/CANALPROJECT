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
import com.opensymphony.xwork2.ActionSupport;

public class BestoayAddAction extends ActionSupport {
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

		String inCollectNumber = DecodeUtils.decodeRequestString(request
				.getParameter("inCollectNumber"));

		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));

		// 必填项：缺少必要信息，返回

		if (inId.equals("") || canalName.equals("")
				|| inCollectNumber.equals("")) {
			return "infoLoss";
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
					if (currentStep <= 4 || currentStep == 11) {
						resultStr = "该状态下不能做此操作，请刷新后重试！";
					}
				} else {
					resultStr = "渠道不存在！";
				}
			}

			if (resultStr.equals("success")) {

				if (currentStep == 10) {
					// 如果主流程是最后一个环节了，提交翼支付时自动归档
					// 3.更新渠道状态表

					sql = "update qdzc.canal_step_state set current_step='11', yzf_parallel_step='14' where in_id='"
							+ inId + "'";
				} else {

					// 3.更新渠道状态表

					sql = "update qdzc.canal_step_state set yzf_parallel_step='14' where in_id='"
							+ inId + "'";
				}
				logger.info("１4.更新渠道状态表：" + sql);
				session.update(sql);

				// 4.操作环节“翼支付”
				sql = "insert into qdzc.process14_bestoay (in_id,canal_id,canal_name,"
						+ "oper_user,dept_id,dept_name,oper_time,remark,in_collect_number) values ("
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
						+ inCollectNumber
						+ "')";
				logger.info("操作环节“翼支付”：" + sql);
				session.add(sql);
				// 2. 获得环节14的名称
				sql = "SELECT step_val FROM step_info WHERE step_no=14";
				Map resultMap = session.findSql(sql).get(0);
				currentStep = 14;
				String currentStepVal = resultMap.get("stepVal").toString();

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