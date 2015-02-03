package com.cqupt.canalModifyFlow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class AlterDetailAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		String inId = request.getParameter("inId");
		String type = request.getParameter("type");
		String resultStr = "";
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			String sql = "select * from qdzc.canal_infomation where in_id="
					+ inId;
			logger.info("查询详细信息" + sql);

			List<Map<String, Object>> resultList = session.findSql(sql);
			Map<String, Object> resultMap = (Map<String, Object>) resultList
					.get(0);
			// 命名自动更换模式
			request.setAttribute("inId", resultMap.get("inId"));
			request.setAttribute("canalId", resultMap.get("canalId"));
			request.setAttribute("canalForm", resultMap.get("canalForm"));
			request.setAttribute("canalProperty",
					resultMap.get("canalProperty"));
			request.setAttribute("canalType", resultMap.get("canalType"));
			request.setAttribute("nativeType", resultMap.get("nativeType"));

			request.setAttribute("agentId", resultMap.get("agentId"));
			request.setAttribute("agentName", resultMap.get("agentName"));
			request.setAttribute("isCrm", resultMap.get("isCrm"));
			request.setAttribute("inCollectNumber",
					resultMap.get("inCollectNumber"));
			request.setAttribute("isMoney", resultMap.get("isMoney"));
			request.setAttribute("taskType", resultMap.get("taskType"));
			request.setAttribute("guaranteeAmount",
					resultMap.get("guaranteeAmount"));
			request.setAttribute("guaranteeUser",
					resultMap.get("guaranteeUser"));
			request.setAttribute("guaranteeTime",
					resultMap.get("guaranteeTime1"));
			request.setAttribute("guaranteeReceiptScanUrl",
					resultMap.get("guaranteeReceiptScanUrl"));

			request.setAttribute("canalName", resultMap.get("canalName"));
			request.setAttribute("areaName", resultMap.get("areaName"));
			request.setAttribute("canalAddress", resultMap.get("canalAddress"));
			request.setAttribute("regionCharacter",
					resultMap.get("regionCharacter"));
			request.setAttribute("canalDept", resultMap.get("canalDept"));
			request.setAttribute("canalUserName",
					resultMap.get("canalUserName"));
			request.setAttribute("canalUserPhone",
					resultMap.get("canalUserPhone"));

			request.setAttribute("canalParentId",
					resultMap.get("canalParentId"));
			request.setAttribute("canalParentName",
					resultMap.get("canalParentName"));
			request.setAttribute("urbanIdetity", resultMap.get("urbanIdetity"));
			request.setAttribute("expireType", resultMap.get("expireType"));
			request.setAttribute("businessArea", resultMap.get("businessArea"));
			request.setAttribute("rentStartDate",
					resultMap.get("rentStartDate"));
			request.setAttribute("rentEndDate", resultMap.get("rentEndDate"));
			request.setAttribute("firstRentAllowance",
					resultMap.get("firstRentAllowance"));
			request.setAttribute("firstDecorationAllowance",
					resultMap.get("firstDecorationAllowance"));

			// request.setAttribute("kh", resultMap.get("kh"));
			// request.setAttribute("khyh", resultMap.get("khyh"));
			// request.setAttribute("hm", resultMap.get("hm"));
			// request.setAttribute("sfzh", resultMap.get("sfzh"));

			String agentInId = (String) resultMap.get("agentInId");
			String sql1 = "select * from qdzc.agent_information where in_id='"
					+ agentInId + "'";
			logger.info("查询详细信息" + sql1);
			List<Map<String, Object>> resultList1 = session.findSql(sql1);
			Map<String, Object> resultMap1 = (Map<String, Object>) resultList1
					.get(0);
			// 命名自动更换模式
			request.setAttribute("branchCompany",
					resultMap1.get("branchCompany"));
			request.setAttribute("agentName", resultMap1.get("agentName"));
			request.setAttribute("agentLevel", resultMap1.get("agentLevel"));
			request.setAttribute("agentShortName",
					resultMap1.get("agentShortName"));
			request.setAttribute("agentDept", resultMap1.get("agentDept"));
			request.setAttribute("companyType", resultMap1.get("companyType"));
			request.setAttribute("cooperationType",
					resultMap1.get("cooperationType"));
			request.setAttribute("legalPerson", resultMap1.get("legalPerson"));
			request.setAttribute("legalPhone", resultMap1.get("legalPhone"));
			request.setAttribute("legalCardType",
					resultMap1.get("legalCardType"));
			request.setAttribute("legalIdCard", resultMap1.get("legalIdCard"));
			request.setAttribute("ondutyPerson", resultMap1.get("ondutyPerson"));
			request.setAttribute("ondutyPersonPhone",
					resultMap1.get("ondutyPersonPhone"));
			request.setAttribute("startBank", resultMap1.get("startBank"));
			request.setAttribute("bankAccountId",
					resultMap1.get("bankAccountid"));

			String sql2 = "select * from qdzc.canal_user where canal_in_id="
					+ inId;
			logger.info("查询详细信息" + sql2);
			List<Map<String, Object>> resultList2 = session.findSql(sql2);
			Map<String, Object> resultMap2 = (Map<String, Object>) resultList2
					.get(0);
			request.setAttribute("userName", resultMap2.get("userName"));
			request.setAttribute("userPinyin", resultMap2.get("userPinyin"));
			request.setAttribute("userSex", resultMap2.get("userSex"));
			resultStr = "success";
			session.closeSession();
		} catch (Exception e) {
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		if (type.equals("modify")) {
			resultStr = "modify";
		} else if (type.equals("detail")) {
			resultStr = "detail";
		}
		return resultStr;
	}
}
