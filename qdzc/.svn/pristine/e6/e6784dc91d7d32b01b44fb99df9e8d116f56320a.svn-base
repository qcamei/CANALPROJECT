package com.cqupt.canalAuditFlow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class DetailQueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	HttpServletRequest request = null;

	String type = "";
	String canalId = "";
	String resultStr = "";

	public String execute() {
		request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		type = request.getParameter("type");

		canalId = request.getParameter("canalId");

		if (type.equals("canal")) {
			queryCanalInfo();
			queryCannalStepInfo();
			resultStr = "canal";
		}

		if (type.equals("agent")) {
			queryAgentInfo();
			resultStr = "agent";
		}

		return resultStr;
	}

	private void queryCanalInfo() {
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			String sql = "select * from qdzc.canal_infomation where canal_id ="
					+ canalId;
			logger.info("查询渠道信息的详细信息" + sql);
			logger.info("canalId_QueryAction: " + canalId);
			List<Map<String, Object>> resultList = session.findSql(sql);
			Map<String, Object> resultMap = (Map<String, Object>) resultList
					.get(0);
			// 命名自动更换模式
			request.setAttribute("inId", resultMap.get("inId"));
			request.setAttribute("canalId", resultMap.get("canalId"));
			request.setAttribute("canalName", resultMap.get("canalName"));
			request.setAttribute("areaName", resultMap.get("areaName"));
			request.setAttribute("canalAddress", resultMap.get("canalAddress"));
			request.setAttribute("regionCharacter",
					resultMap.get("regionCharacter"));
			request.setAttribute("canalState", resultMap.get("canalState"));

			request.setAttribute("canalForm", resultMap.get("canalForm"));
			request.setAttribute("canalProperty",
					resultMap.get("canalProperty"));
			request.setAttribute("canalType", resultMap.get("canalType"));
			request.setAttribute("canalParentId",
					resultMap.get("canalParentId"));
			request.setAttribute("canalParentName",
					resultMap.get("canalParentName"));
			request.setAttribute("agentName", resultMap.get("agentName"));
			request.setAttribute("agentId", resultMap.get("agentId"));

			request.setAttribute("canalUserName",
					resultMap.get("canalUserName"));
			request.setAttribute("canalUserPhone",
					resultMap.get("canalUserPhone"));
			request.setAttribute("canalIdCard", resultMap.get("canalIdCard"));
			request.setAttribute("canalManager", resultMap.get("canalManager"));
			request.setAttribute("canalManagerId",
					resultMap.get("canalManagerId"));
			request.setAttribute("urbanIdetity", resultMap.get("urbanIdetity"));
			request.setAttribute("expireType", resultMap.get("expireType"));

			request.setAttribute("businessArea", resultMap.get("businessArea"));
			request.setAttribute("rentMoney", resultMap.get("rentMoney"));
			request.setAttribute("rentStartDate",
					resultMap.get("rentStartDate1"));
			request.setAttribute("rentEndDate", resultMap.get("rentEndDate1"));
			request.setAttribute("allowancePolicy",
					resultMap.get("allowancePolicy"));
			request.setAttribute("firstRentAllowance",
					resultMap.get("firstRentAllowance"));
			request.setAttribute("firstDecorationAllowance",
					resultMap.get("firstDecorationAllowance"));

			request.setAttribute("canalDept", resultMap.get("canalDept"));
			request.setAttribute("houseOwnerName",
					resultMap.get("houseOwnerName"));
			request.setAttribute("houseOwnerPhone",
					resultMap.get("houseOwnerPhone"));
			request.setAttribute("distributeCard",
					resultMap.get("distributeCard"));
			request.setAttribute("inBankAccount",
					resultMap.get("inBankAccount"));
			request.setAttribute("inBankAccountNumber",
					resultMap.get("inBankAccountNumber"));
			request.setAttribute("inBankName", resultMap.get("inBankName"));

			request.setAttribute("inCollectNumber",
					resultMap.get("inCollectNumber"));
			request.setAttribute("inPersonIdCard",
					resultMap.get("inPersonIdCard"));
			request.setAttribute("contractScanUrl",
					resultMap.get("contractScanUrl"));
			request.setAttribute("guaranteeAmount",
					resultMap.get("guaranteeAmount"));
			request.setAttribute("guaranteeUser",
					resultMap.get("guaranteeUser"));
			request.setAttribute("guaranteeTime",
					resultMap.get("guaranteeTime1"));
			request.setAttribute("guaranteeReceiptScanUrl",
					resultMap.get("guaranteeReceiptScanUrl"));

			request.setAttribute("operUserNum", resultMap.get("operUserNum"));
			request.setAttribute("operUser", resultMap.get("operUser"));
			request.setAttribute("deptId", resultMap.get("deptId"));
			request.setAttribute("deptName", resultMap.get("deptName"));
			request.setAttribute("operTime", resultMap.get("time"));
			request.setAttribute("remark", resultMap.get("remark"));

			session.closeSession();
		} catch (Exception e) {
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void queryAgentInfo() {
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();

			String sql1 = "SELECT * from qdzc.canal_infomation WHERE canal_id ='"
					+ canalId + "'";
			Map resultMap1 = session.findSql(sql1).get(0);
			// 获得当前代理商的工单号
			String agentId = (String) resultMap1.get("agentId");

			String sql = "select * from qdzc.agent_information where agent_id='"
					+ agentId + "'";
			logger.info("查询代理商详细信息的详细信息" + sql);
			logger.info("agent_in_id_QueryAction: " + agentId);
			List<Map<String, Object>> resultList = session.findSql(sql);

			if (resultList.size() > 0) {
				Map<String, Object> resultMap = (Map<String, Object>) resultList
						.get(0);
				// 命名自动更换模式
				request.setAttribute("inId", resultMap.get("inId"));
				request.setAttribute("branchCompany",
						resultMap.get("branchCompany"));
				request.setAttribute("areaName", resultMap.get("areaName"));
				request.setAttribute("agentId", resultMap.get("agentId"));
				request.setAttribute("agentName", resultMap.get("agentName"));
				request.setAttribute("agentShortName",
						resultMap.get("agentShortName"));
				request.setAttribute("agentState", resultMap.get("agentState"));
				request.setAttribute("agentDept", resultMap.get("agentDept"));

				request.setAttribute("agentLevel", resultMap.get("agentLevel"));
				request.setAttribute("companyType",
						resultMap.get("companyType"));
				request.setAttribute("cooperationType",
						resultMap.get("cooperationType"));
				request.setAttribute("legalPerson",
						resultMap.get("legalPerson"));
				request.setAttribute("legalPhone", resultMap.get("legalPhone"));
				request.setAttribute("legalCardType",
						resultMap.get("legalCardType"));
				request.setAttribute("legalIdCard",
						resultMap.get("legalIdCard"));

				request.setAttribute("manageModel",
						resultMap.get("manageModel"));
				request.setAttribute("is11888Card",
						resultMap.get("is11888Card"));
				request.setAttribute("ondutyPerson",
						resultMap.get("ondutyPerson"));
				request.setAttribute("ondutyPersonPhone",
						resultMap.get("ondutyPersonPhone"));
				request.setAttribute("startBank", resultMap.get("startBank"));
				request.setAttribute("bankAccountid",
						resultMap.get("bankAccountid"));
				request.setAttribute("parentId", resultMap.get("parentId"));

				request.setAttribute("parentName", resultMap.get("parentName"));
				request.setAttribute("companyAddress",
						resultMap.get("companyAddress"));
				request.setAttribute("ondutyPersonAddress",
						resultMap.get("ondutyPersonAddress"));
				request.setAttribute("ondutyPersonQqnum",
						resultMap.get("ondutyPersonQqnum"));
				request.setAttribute("postCode", resultMap.get("postCode"));
				request.setAttribute("agentFax", resultMap.get("agentFax"));
				request.setAttribute("agentEmail", resultMap.get("agentEmail"));

				request.setAttribute("payWay", resultMap.get("payWay"));
				request.setAttribute("bankAccountName",
						resultMap.get("bankAccountName"));
				request.setAttribute("weituoStartBank",
						resultMap.get("weituoStartBank"));
				request.setAttribute("weituoBankAccount",
						resultMap.get("weituoBankAccount"));
				request.setAttribute("weituoBankName",
						resultMap.get("weituoBankName"));
				request.setAttribute("payObject", resultMap.get("payObject"));
				request.setAttribute("registerMoney",
						resultMap.get("registerMoney"));
				request.setAttribute("registerDate",
						resultMap.get("registerDate"));
				request.setAttribute("businessLicenseId",
						resultMap.get("businessLicenseId"));

				request.setAttribute("taxRegisteId",
						resultMap.get("taxRegisteId"));
				request.setAttribute("bankPermisionId",
						resultMap.get("bankPermisionId"));
				request.setAttribute("organizationCodeId",
						resultMap.get("organizationCodeId"));
				request.setAttribute("operUser", resultMap.get("operUser"));
				request.setAttribute("deptName", resultMap.get("deptName"));
				request.setAttribute("remark", resultMap.get("remark"));
			}
			session.closeSession();
		} catch (Exception e) {
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	// 查询各环节信息
	private void queryCannalStepInfo() {
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			// 环节12 专线受理

			String sql = "select * from qdzc.process12_config_line where canal_id='"
					+ canalId + "'";
			logger.info("专线受理：" + sql);

			List resultList = session.findSql(sql);
			Map resultMap = null;
			if (resultList.size() > 0) {

				resultMap = (Map) resultList.get(0);
				request.setAttribute("broadbandAccount",
						resultMap.get("broadbandAccount"));
				request.setAttribute("broadbandPassword",
						resultMap.get("broadbandPassword"));
				request.setAttribute("privilegeInfo",
						resultMap.get("privilegeInfo"));
				request.setAttribute("wwwAccount", resultMap.get("wwwAccount"));
				request.setAttribute("wwwPrivilegeInfo",
						resultMap.get("wwwPrivilegeInfo"));

			}

			// 环节9 专线受理确认
			sql = "select *,date_format(open_time,'%Y-%m-%d %H:%i:%s') open_time1,date_format(oper_time,'%Y-%m-%d %H:%i:%s') line_time9 from qdzc.process9_config_line where canal_id='"
					+ canalId + "'";
			logger.info("查询专线受理确认：" + sql);

			resultList = session.findSql(sql);
			if (resultList.size() > 0) {
				resultMap = (Map) resultList.get(0);
				request.setAttribute("broadbandAccount",
						resultMap.get("broadbandAccount"));
				request.setAttribute("broadbandPassword",
						resultMap.get("broadbandPassword"));
				request.setAttribute("openTime", resultMap.get("openTime1"));

			}

			// 代理商系统渠道信息确认
			String agentId = "";
			sql = "select * from qdzc.process4_agent_canal where canal_id='"
					+ canalId + "'";
			logger.info("查询信息" + sql);

			resultList = session.findSql(sql);
			if (resultList.size() > 0) {
				resultMap = (Map) resultList.get(0);
				request.setAttribute("canalName", resultMap.get("canalName"));// 厅店类型
				request.setAttribute("canalId", resultMap.get("canalId"));// 渠道类别(内渠、外渠)
				request.setAttribute("agentName", resultMap.get("agentName"));// 归属渠道客户经理
				request.setAttribute("agentId", resultMap.get("agentId"));// 归属机构
				agentId = resultMap.get("agentId").toString();

			}
			// 开CRM工号
			sql = "select * from qdzc.process5_open_CRM where canal_id='"
					+ canalId + "'";
			logger.info("查询信息" + sql);

			resultList = session.findSql(sql);
			if (resultList.size() > 0) {
				resultMap = (Map) resultList.get(0);
				request.setAttribute("crmNumber", resultMap.get("crmNumber"));
				request.setAttribute("employeeName",
						resultMap.get("employeeName"));
				request.setAttribute("telephone", resultMap.get("telephone"));
				request.setAttribute("privilege", resultMap.get("privilege"));// 号工所属权限

			}

			// 开代理商门户网站工号
			sql = "select * from qdzc.process6_open_agentNumber where canal_id='"
					+ canalId + "'";
			logger.info("查询信息" + sql);
			resultList = session.findSql(sql);
			if (resultList.size() > 0) {
				resultMap = (Map) resultList.get(0);
				request.setAttribute("agentJobNumber",
						resultMap.get("agentJobNumber"));// 代理商系统门户网站工号

			}
			// 稽核编码
			sql = "select * from qdzc.process7_check_code where agent_id='"
					+ agentId + "'";
			logger.info("查询信息" + sql);

			resultList = session.findSql(sql);
			if (resultList.size() > 0) {
				resultMap = (Map) resultList.get(0);
				request.setAttribute("checkName", resultMap.get("checkName"));// 稽核厅名称
				request.setAttribute("checkNumber",
						resultMap.get("checkNumber"));// 稽核编码
			}
			// 配置稽核人员
			sql = "select * from qdzc.process8_check_configuration where canal_id='"
					+ canalId + "'";
			logger.info("查询信息" + sql);
			resultList = session.findSql(sql);
			if (resultList.size() > 0) {
				resultMap = (Map) resultList.get(0);
				request.setAttribute("checkUser", resultMap.get("checkUser"));// 稽核人员
			}
			// 设备基础配置
			sql = "select * from qdzc.process10_device_configuration where canal_id='"
					+ canalId + "'";
			logger.info("查询信息" + sql);

			resultList = session.findSql(sql);
			if (resultList.size() > 0) {
				resultMap = (Map) resultList.get(0);
				request.setAttribute("dx360Number",
						resultMap.get("dx360Number"));
				request.setAttribute("terminalType",
						resultMap.get("terminalType"));
				request.setAttribute("terminalNumber",
						resultMap.get("terminalNumber"));
				request.setAttribute("cpu", resultMap.get("cpu"));
				request.setAttribute("kernal", resultMap.get("kernal"));
				request.setAttribute("frequency", resultMap.get("frequency"));
				request.setAttribute("memory", resultMap.get("memory"));
				request.setAttribute("disk", resultMap.get("disk"));

			}
			session.closeSession();
		} catch (Exception e) {
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
