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

public class DeviceConfigurationAddAction extends ActionSupport {
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

		String dx360Number = DecodeUtils.decodeRequestString(request
				.getParameter("dx360Number"));
		String terminalType = DecodeUtils.decodeRequestString(request
				.getParameter("terminalType"));
		String terminalNumber = "";
		String cpu = "";
		String kernal = "";
		String frequency = "";
		String memory = "";
		String disk = "";
		if (terminalType.equals("PC")) {
			terminalNumber = DecodeUtils.decodeRequestString(request
					.getParameter("terminalNumber"));
		} else {
			cpu = DecodeUtils.decodeRequestString(request.getParameter("cpu"));
			kernal = DecodeUtils.decodeRequestString(request
					.getParameter("kernal"));
			frequency = DecodeUtils.decodeRequestString(request
					.getParameter("frequency"));
			memory = DecodeUtils.decodeRequestString(request
					.getParameter("memory"));
			disk = DecodeUtils
					.decodeRequestString(request.getParameter("disk"));
		}

		String printNumber = DecodeUtils.decodeRequestString(request
				.getParameter("printNumber"));
		String isNoPaper = DecodeUtils.decodeRequestString(request
				.getParameter("isNoPaper"));

		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));

		// 必填项：缺少必要信息，返回
		if (inId.equals("") || canalName.equals("") || dx360Number.equals("")) {
			return "infoLoss";
		}

		try {
			session = DataStormSession.getInstance();
			int currentStep = 0, yzfParallelStep = 0;
			if (resultStr.equals("success")) {
				// 1.判断环节“设备基础配置”
				sql = "select current_step,yzf_parallel_step from qdzc.canal_step_state where  in_id = '"
						+ inId + "'";
				logger.info(sql);
				List list1 = session.findSql(sql);
				if (list1.size() > 0) {
					Map map = (Map) list1.get(0);
					currentStep = (Integer) map.get("currentStep");
					yzfParallelStep = (Integer) map.get("yzfParallelStep");
					if (currentStep != 9) {
						resultStr = "该状态下不能做此操作，请刷新后重试！";
					}
				} else {
					resultStr = "渠道不存在！";
				}
			}

			if (resultStr.equals("success")) {

				// 2. 获得环节10的名称
				sql = "SELECT step_val FROM step_info WHERE step_no=10";
				Map resultMap = session.findSql(sql).get(0);
				currentStep = 10;
				String currentStepVal = resultMap.get("stepVal").toString();

				if (yzfParallelStep == 14) {
					// 如果翼支付已提交，主流程自动归档
					currentStep = 11;
				}
				// 3.更新渠道状态表
				sql = "update qdzc.canal_step_state set current_step = "
						+ currentStep + " where in_id='" + inId + "'";
				/*
				 * ,device_time=sysdate()," + "device_name='" + currentStepVal +
				 * "',device_state='完成'
				 */
				logger.info("10.更新渠道状态表：" + sql);
				session.update(sql);
				// 4.操作环节“设备基础配置”
				sql = "insert into qdzc.process10_device_configuration (in_id,canal_id,canal_name,dx360_number,terminal_type,terminal_number,cpu,kernal,frequency,memory,disk,print_number,is_no_paper,oper_user,dept_id,dept_name,oper_time,remark) values ("
						+ "'"
						+ inId
						+ "','"
						+ canalId
						+ "','"
						+ canalName
						+ "','"
						+ dx360Number
						+ "', '"
						+ terminalType
						+ "','"
						+ terminalNumber
						+ "','"
						+ cpu
						+ "','"
						+ kernal
						+ "','"
						+ frequency
						+ "','"
						+ memory
						+ "','"
						+ disk
						+ "','"
						+ printNumber
						+ "','"
						+ isNoPaper
						+ "',"
						+ "'"
						+ userName
						+ "','"
						+ deptId
						+ "','"
						+ deptName
						+ "',sysdate(),'" + remark + "')";
				logger.info("操作环节“设备基础配置”：" + sql);
				session.add(sql);
				// 2015年1月26号
				sql = "update qdzc.canal_infomation set dx360_number='"
						+ dx360Number + "',terminal_type='" + terminalType
						+ "',terminal_number='" + terminalNumber + "',cpu='"
						+ cpu + "',kernal='" + terminalNumber + "',frequency='"
						+ frequency + "',memory='" + memory + "',disk='" + disk
						+ "',print_number='" + printNumber + "',is_no_paper='"
						+ isNoPaper + "' where in_id='" + inId + "'";
				session.update(sql);
				currentStep = 10;// 记录本环节的操作详情
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