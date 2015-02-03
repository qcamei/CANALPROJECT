package com.cqupt.canalModifyFlow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class DetailAction_0 extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		String canalId = request.getParameter("canalId");
		String type = request.getParameter("type");
		String resultStr = "";
		String sql = "";
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();

			// sql = "select canal_id from qdzc.canal_infomation where in_id="
			// + inId;
			// Map resultMap11 = session.findSql(sql).get(0);
			// String canalId = (String) resultMap11.get("canalId");

			sql = "select * from qdzc.canal_infomation where canal_id='"
					+ canalId + "' ";
			logger.info("查询详细信息" + sql);

			List<Map<String, Object>> resultList = session.findSql(sql);
			Map<String, Object> resultMap = (Map<String, Object>) resultList
					.get(0);
			// 命名自动更换模式
			request.setAttribute("inId", resultMap.get("inId"));

			request.setAttribute("rentMoney", resultMap.get("rentMoney"));
			// request.setAttribute("canalManager",
			// resultMap.get("canalManager"));
			// request.setAttribute("canalManagerId",
			// resultMap.get("canalManagerId"));
			request.setAttribute("canalParentId",
					resultMap.get("canalParentId"));
			request.setAttribute("canalParentName",
					resultMap.get("canalParentName"));
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
			request.setAttribute("inPersonIdCard",
					resultMap.get("inPersonIdCard"));

			request.setAttribute("agentPointType",
					resultMap.get("agentPointType"));
			request.setAttribute("sellPointType",
					resultMap.get("sellPointType"));
			request.setAttribute("manageWay", resultMap.get("manageWay"));
			request.setAttribute("openSellYetai",
					resultMap.get("openSellYetai"));
			request.setAttribute("liansuoCatorySign",
					resultMap.get("liansuoCatorySign"));
			request.setAttribute("topZiyou", resultMap.get("topZiyou"));
			request.setAttribute("majorMendianType",
					resultMap.get("majorMendianType"));

			request.setAttribute("grantMendianClass",
					resultMap.get("grantMendianClass"));
			request.setAttribute("bianlidianTask",
					resultMap.get("bianlidianTask"));
			request.setAttribute("sellPointTask",
					resultMap.get("sellPointTask"));
			request.setAttribute("sellPointSellType",
					resultMap.get("sellPointSellType"));
			request.setAttribute("kechangEntity",
					resultMap.get("kechangEntity"));
			request.setAttribute("factoryCanalSign",
					resultMap.get("factoryCanalSign"));
			request.setAttribute("isDragonSystem",
					resultMap.get("isDragonSystem"));

			request.setAttribute("canalIdCard", resultMap.get("canalIdCard"));
			request.setAttribute("allowancePolicy",
					resultMap.get("allowancePolicy"));
			request.setAttribute("contractScanUrl",
					resultMap.get("contractScanUrl"));

			// request.setAttribute("operUserNum",
			// resultMap.get("operUserNum"));
			// request.setAttribute("operUser", resultMap.get("operUser"));
			// request.setAttribute("deptId", resultMap.get("deptId"));
			// request.setAttribute("deptName", resultMap.get("deptName"));
			// request.setAttribute("operTime", resultMap.get("time"));
			// request.setAttribute("remark", resultMap.get("remark"));

			String agentId = (String) resultMap.get("agentId");
			String sql1 = "select * from qdzc.agent_information where agent_id='"
					+ agentId + "' ";
			logger.info("查询详细信息" + sql);
			List<Map<String, Object>> resultList1 = session.findSql(sql1);
			Map<String, Object> resultMap1 = (Map<String, Object>) resultList1
					.get(0);
			// 命名自动更换模式
			request.setAttribute("manageModel", resultMap1.get("manageModel"));
			request.setAttribute("is11888Card", resultMap1.get("is11888Card"));

			request.setAttribute("parentId", resultMap1.get("parentId"));
			request.setAttribute("parentName", resultMap1.get("parentName"));
			request.setAttribute("companyAddress",
					resultMap1.get("companyAddress"));
			request.setAttribute("ondutyPersonAddress",
					resultMap1.get("ondutyPersonAddress"));
			request.setAttribute("ondutyPersonQqnum",
					resultMap1.get("ondutyPersonQqnum"));
			request.setAttribute("postCode", resultMap1.get("postCode"));
			request.setAttribute("agentFax", resultMap1.get("agentFax"));
			request.setAttribute("agentEmail", resultMap1.get("agentEmail"));
			request.setAttribute("payWay", resultMap1.get("payWay"));
			request.setAttribute("bankAccountName",
					resultMap1.get("bankAccountName"));
			request.setAttribute("weituoBankAccount",
					resultMap1.get("weituoBankAccount"));
			request.setAttribute("weituoBankName",
					resultMap1.get("weituoBankName"));

			request.setAttribute("payObject", resultMap1.get("payObject"));
			request.setAttribute("registerMoney",
					resultMap1.get("registerMoney"));
			request.setAttribute("registerDate", resultMap1.get("registerDate"));
			request.setAttribute("businessLicenseId",
					resultMap1.get("businessLicenseId"));
			request.setAttribute("taxRegisteId", resultMap1.get("taxRegisteId"));
			request.setAttribute("bankPermisionId",
					resultMap1.get("bankPermisionId"));
			request.setAttribute("organizationCodeId",
					resultMap1.get("organizationCodeId"));

			String sql2 = "select * from qdzc.canal_user where canal_id='"
					+ canalId + "' ";
			logger.info("查询详细信息" + sql);
			List<Map<String, Object>> resultList2 = session.findSql(sql2);
			Map<String, Object> resultMap2 = (Map<String, Object>) resultList2
					.get(0);

			request.setAttribute("userKind", resultMap2.get("userKind"));
			request.setAttribute("userState", resultMap2.get("userState"));
			request.setAttribute("userIdCard", resultMap2.get("userIdCard"));
			request.setAttribute("userAuthority",
					resultMap2.get("userAuthority"));
			request.setAttribute("userEmail", resultMap2.get("userEmail"));
			request.setAttribute("userRole", resultMap2.get("userRole"));
			request.setAttribute("userCountId", resultMap2.get("userCountId"));
			request.setAttribute("userBirthday", resultMap2.get("userBirthday"));
			request.setAttribute("userNativeplace",
					resultMap2.get("userNativeplace"));
			request.setAttribute("userPhone", resultMap2.get("userPhone"));
			request.setAttribute("userEducation",
					resultMap2.get("userEducation"));
			request.setAttribute("userCertification",
					resultMap2.get("userCertification"));
			request.setAttribute("userInDate", resultMap2.get("userInDate"));
			request.setAttribute("userOutDate", resultMap2.get("userOutDate"));
			request.setAttribute("userAddress", resultMap2.get("userAddress"));
			request.setAttribute("userPosition", resultMap2.get("userPosition"));
			request.setAttribute("userWay", resultMap2.get("userWay"));
			request.setAttribute("userRegionName",
					resultMap2.get("userRegionName"));
			request.setAttribute("reamark", resultMap2.get("remark"));

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
