package com.cqupt.canalManage.action;

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

public class CanalInfoUpdateAction extends ActionSupport {
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
		String operUser = request.getSession().getAttribute("userName")
				.toString();

		String inId = request.getParameter("inId");
		// 渠道信息
		String canalId = DecodeUtils.decodeRequestString(request
				.getParameter("canalId"));// 判断是否存在
		String canalName = DecodeUtils.decodeRequestString(request
				.getParameter("canalName"));// 判断是否存在
		String areaName = DecodeUtils.decodeRequestString(request
				.getParameter("areaName"));

		String canalForm = DecodeUtils.decodeRequestString(request
				.getParameter("canalForm"));
		String canalProperty = DecodeUtils.decodeRequestString(request
				.getParameter("canalProperty"));
		String canalType = DecodeUtils.decodeRequestString(request
				.getParameter("canalType"));

		String canalAddress = DecodeUtils.decodeRequestString(request
				.getParameter("canalAddress"));
		String regionCharacter = DecodeUtils.decodeRequestString(request
				.getParameter("regionCharacter"));

		// String canalManager = DecodeUtils.decodeRequestString(request
		// .getParameter("canalManager"));
		// String canalManagerId = "";// 归属渠道客户经理ＩＤ,需要查询

		String canalParentId = DecodeUtils.decodeRequestString(request
				.getParameter("canalParentId"));
		String canalParentName = DecodeUtils.decodeRequestString(request
				.getParameter("canalParentName"));

		String canalDept = DecodeUtils.decodeRequestString(request
				.getParameter("canalDept"));
		String canalUserName = DecodeUtils.decodeRequestString(request
				.getParameter("canalUserName"));
		String canalUserPhone = DecodeUtils.decodeRequestString(request
				.getParameter("canalUserPhone"));
		String canalIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("canalIdCard"));

		String urbanIdetity = DecodeUtils.decodeRequestString(request
				.getParameter("urbanIdetity"));
		String expireType = DecodeUtils.decodeRequestString(request
				.getParameter("expireType"));
		String businessArea = DecodeUtils.decodeRequestString(request
				.getParameter("businessArea"));
		String rentMoney = DecodeUtils.decodeRequestString(request
				.getParameter("rentMoney"));
		String rentStartDate = DecodeUtils.decodeRequestString(request
				.getParameter("rentStartDate"));
		String rentEndDate = DecodeUtils.decodeRequestString(request
				.getParameter("rentEndDate"));

		String allowancePolicy = DecodeUtils.decodeRequestString(request
				.getParameter("allowancePolicy"));
		String firstRentAllowance = DecodeUtils.decodeRequestString(request
				.getParameter("firstRentAllowance"));
		String firstDecorationAllowance = DecodeUtils
				.decodeRequestString(request
						.getParameter("firstDecorationAllowance"));
		String houseOwnerName = DecodeUtils.decodeRequestString(request
				.getParameter("houseOwnerName"));
		String houseOwnerPhone = DecodeUtils.decodeRequestString(request
				.getParameter("houseOwnerPhone"));
		String distributeCard = DecodeUtils.decodeRequestString(request
				.getParameter("distributeCard"));

		String inBankAccount = DecodeUtils.decodeRequestString(request
				.getParameter("inBankAccount"));
		String inBankAccountNumber = DecodeUtils.decodeRequestString(request
				.getParameter("inBankAccountNumber"));
		String inBankName = DecodeUtils.decodeRequestString(request
				.getParameter("inBankName"));
		String inCollectNumber = DecodeUtils.decodeRequestString(request
				.getParameter("inCollectNumber"));
		String inPersonIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("inPersonIdCard"));
		String contractScanUrl = "";
		// String contractScanUrl =
		// DecodeUtils.decodeRequestString(request.getParameter("contractScanUrl"));
		String guaranteeAmount = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeAmount"));
		String guaranteeUser = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeUser"));
		String guaranteeTime = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeTime"));
		String guaranteeReceiptScanUrl = DecodeUtils
				.decodeRequestString(request
						.getParameter("guaranteeReceiptScanUrl"));
		// 代理商信息
		String branchCompany = DecodeUtils.decodeRequestString(request
				.getParameter("branchCompany"));
		String agentId1 = DecodeUtils.decodeRequestString(request
				.getParameter("agentId1"));
		String agentName1 = DecodeUtils.decodeRequestString(request
				.getParameter("agentName1"));
		String agentShortName = DecodeUtils.decodeRequestString(request
				.getParameter("agentShortName"));
		String agentDept = DecodeUtils.decodeRequestString(request
				.getParameter("agentDept"));
		String agentLevel = DecodeUtils.decodeRequestString(request
				.getParameter("agentLevel"));
		String companyType = DecodeUtils.decodeRequestString(request
				.getParameter("companyType"));

		String cooperationType = DecodeUtils.decodeRequestString(request
				.getParameter("cooperationType"));
		String legalPerson = DecodeUtils.decodeRequestString(request
				.getParameter("legalPerson"));
		String legalPhone = DecodeUtils.decodeRequestString(request
				.getParameter("legalPhone"));
		String legalCardType = DecodeUtils.decodeRequestString(request
				.getParameter("legalCardType"));
		String legalIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("legalIdCard"));
		String manageModel = DecodeUtils.decodeRequestString(request
				.getParameter("manageModel"));
		String is11888Card = DecodeUtils.decodeRequestString(request
				.getParameter("is11888Card"));
		String ondutyPerson = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPerson"));
		String ondutyPersonPhone = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonPhone"));

		String startBank = DecodeUtils.decodeRequestString(request
				.getParameter("startBank"));
		String bankAccountId = DecodeUtils.decodeRequestString(request
				.getParameter("bankAccountId"));
		String parentId = DecodeUtils.decodeRequestString(request
				.getParameter("parentId"));
		String parentName = DecodeUtils.decodeRequestString(request
				.getParameter("parentName"));
		String companyAddress = DecodeUtils.decodeRequestString(request
				.getParameter("companyAddress"));
		String ondutyPersonAddress = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonAddress"));
		String ondutyPersonQqnum = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonQqnum"));
		String postCode = DecodeUtils.decodeRequestString(request
				.getParameter("postCode"));
		String agentFax = DecodeUtils.decodeRequestString(request
				.getParameter("agentFax"));

		String agentEmail = DecodeUtils.decodeRequestString(request
				.getParameter("agentEmail"));
		String payWay = DecodeUtils.decodeRequestString(request
				.getParameter("payWay"));
		String bankAccountName = DecodeUtils.decodeRequestString(request
				.getParameter("bankAccountName"));
		String weituoStartBank = DecodeUtils.decodeRequestString(request
				.getParameter("weituoStartBank"));
		String weituoBankAccount = DecodeUtils.decodeRequestString(request
				.getParameter("weituoBankAccount"));
		String weituoBankName = DecodeUtils.decodeRequestString(request
				.getParameter("weituoBankName"));
		String payObject = DecodeUtils.decodeRequestString(request
				.getParameter("payObject"));
		String registerMoney = DecodeUtils.decodeRequestString(request
				.getParameter("registerMoney"));
		String registerDate = DecodeUtils.decodeRequestString(request
				.getParameter("registerDate"));

		String businessLicenseId = DecodeUtils.decodeRequestString(request
				.getParameter("businessLicenseId"));
		String taxRegisteId = DecodeUtils.decodeRequestString(request
				.getParameter("taxRegisteId"));
		String bankPermisionId = DecodeUtils.decodeRequestString(request
				.getParameter("bankPermisionId"));
		String organizationCodeId = DecodeUtils.decodeRequestString(request
				.getParameter("organizationCodeId"));
		// 渠道员工信息
		String userName = DecodeUtils.decodeRequestString(request
				.getParameter("userName"));
		String userKind = DecodeUtils.decodeRequestString(request
				.getParameter("userKind"));

		String userState = DecodeUtils.decodeRequestString(request
				.getParameter("userState"));
		String userAuthority = DecodeUtils.decodeRequestString(request
				.getParameter("userAuthority"));
		String userIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("userIdCard"));
		String userNumber = DecodeUtils.decodeRequestString(request
				.getParameter("userNumber"));

		String userPinyin = DecodeUtils.decodeRequestString(request
				.getParameter("userPinyin"));

		String userEmail = DecodeUtils.decodeRequestString(request
				.getParameter("userEmail"));
		String userRole = DecodeUtils.decodeRequestString(request
				.getParameter("userRole"));
		String userCountId = DecodeUtils.decodeRequestString(request
				.getParameter("userCountId"));
		String userSex = DecodeUtils.decodeRequestString(request
				.getParameter("userSex"));
		String userBirthday = DecodeUtils.decodeRequestString(request
				.getParameter("userBirthday"));
		String userNativeplace = DecodeUtils.decodeRequestString(request
				.getParameter("userNativeplace"));
		String userPhone = DecodeUtils.decodeRequestString(request
				.getParameter("userPhone"));

		String userEducation = DecodeUtils.decodeRequestString(request
				.getParameter("userEducation"));
		String userCertification = DecodeUtils.decodeRequestString(request
				.getParameter("userCertification"));
		String userInDate = DecodeUtils.decodeRequestString(request
				.getParameter("userInDate"));
		String userOutDate = DecodeUtils.decodeRequestString(request
				.getParameter("userOutDate"));
		String userAddress = DecodeUtils.decodeRequestString(request
				.getParameter("userAddress"));
		String userPosition = DecodeUtils.decodeRequestString(request
				.getParameter("userPosition"));
		String userWay = DecodeUtils.decodeRequestString(request
				.getParameter("userWay"));
		String userRegionName = DecodeUtils.decodeRequestString(request
				.getParameter("userRegionName"));
		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));

		// 必填项：缺少必要信息，返回
		if (canalName.equals("") || areaName.equals("")
				|| regionCharacter.equals("") || canalAddress.equals("")
				|| canalForm.equals("") || canalProperty.equals("")
				|| canalType.equals("") || canalUserName.equals("")
				|| canalUserPhone.equals("") ||

				urbanIdetity.equals("") || expireType.equals("")
				|| businessArea.equals("") || rentMoney.equals("")) {
			return "infoLoss";
		}

		try {
			session = DataStormSession.getInstance();

			if (resultStr.equals("success")) {
				// 修改渠道基础信息

				sql = "update qdzc.canal_infomation set canal_name = '"
						+ canalName + "',area_name = '" + areaName
						+ "',canal_address = '" + canalAddress
						+ "',region_character = '" + regionCharacter + "',"
						+ "canal_state = '正常',canal_form = '" + canalForm
						+ "',canal_property = '" + canalProperty
						+ "',canal_type = '" + canalType
						+ "',canal_parent_id = '" + canalParentId
						+ "',canal_parent_name = '" + canalParentName + "',"
						+ "agent_name = '" + agentName1 + "',agent_id = '"
						+ agentId1 + "',canal_user_name = '" + canalUserName
						+ "',canal_user_phone = '" + canalUserPhone + "',"
						+ "canal_id_card = '" + canalIdCard
						+ "',canal_manager = '字段不用',urban_idetity = '"
						+ urbanIdetity + "',expire_type = '" + expireType
						+ "',business_area = '" + businessArea + "',"
						+ "rent_money = '" + rentMoney
						+ "',rent_start_date = '" + rentStartDate
						+ "',rent_end_date = '" + rentEndDate
						+ "',allowance_policy = '" + allowancePolicy
						+ "',first_rent_allowance = '" + firstRentAllowance
						+ "',first_decoration_allowance = '"
						+ firstDecorationAllowance + "'," + "canal_dept = '"
						+ canalDept + "',house_owner_name = '" + houseOwnerName
						+ "',house_owner_phone = '" + houseOwnerPhone
						+ "',distribute_card = '" + distributeCard
						+ "',in_bank_account = '" + inBankAccount
						+ "',in_bank_account_number = '" + inBankAccountNumber
						+ "'," + "in_bank_name = '" + inBankName
						+ "',in_collect_number = '" + inCollectNumber
						+ "',in_person_id_card = '" + inPersonIdCard
						+ "',contract_scan_url = '" + contractScanUrl + "',"
						+ "guarantee_amount = '" + guaranteeAmount
						+ "',guarantee_user = '" + guaranteeUser
						+ "',guarantee_time = '" + guaranteeTime
						+ "',guarantee_receipt_scan_url = '"
						+ guaranteeReceiptScanUrl + "',canal_name_start = '"
						+ canalName + "' where in_id='" + inId + "'";
				logger.info("修改渠道信息：" + sql);
				session.update(sql);
				// 在渠道信息表中查出agent_in_id
				sql = "select * from qdzc.canal_infomation where in_id='"
						+ inId + "'";
				List list = session.findSql(sql);
				Map map = (Map) list.get(0);
				String agentInId = map.get("agentInId").toString();
				// 修改代理商信息
				sql = "update agent_information set branch_company='"
						+ branchCompany + "',area_name='" + areaName
						+ "',agent_name='" + agentName1
						+ "',agent_short_name='" + agentShortName
						+ "',agent_id='" + agentId1 + "',agent_dept='"
						+ agentDept + "',agent_level='" + agentLevel
						+ "',agent_state='正常',agent_dept='" + agentDept
						+ "',agent_level='" + agentLevel + "',company_type='"
						+ companyType + "',cooperation_type='"
						+ cooperationType + "',legal_person='" + legalPerson
						+ "',legal_phone='" + legalPhone
						+ "',legal_card_type='" + legalCardType
						+ "',legal_id_card='" + legalIdCard
						+ "',manage_model='" + manageModel
						+ "',is_11888_card='" + is11888Card
						+ "',onduty_person='" + ondutyPerson
						+ "',onduty_person_phone='" + ondutyPersonPhone
						+ "',start_bank='" + startBank + "',bank_accountId='"
						+ bankAccountId + "',parent_id='" + parentId
						+ "',parent_name='" + parentName
						+ "',company_address='" + companyAddress
						+ "',onduty_person_address='" + ondutyPersonAddress
						+ "',onduty_person_qqnum='" + ondutyPersonQqnum
						+ "',post_code='" + postCode + "',agent_fax='"
						+ agentFax + "',agent_email='" + agentEmail
						+ "',pay_way='" + payWay + "',bank_account_name='"
						+ bankAccountName + "',weituo_start_bank='"
						+ weituoStartBank + "',weituo_bank_account='"
						+ weituoBankAccount + "',weituo_bank_name='"
						+ weituoBankName + "',pay_object='" + payObject
						+ "',register_money='" + registerMoney
						+ "',register_date='" + registerDate
						+ "',business_license_id='" + businessLicenseId
						+ "',tax_registe_id='" + taxRegisteId
						+ "',bank_permision_id='" + bankPermisionId
						+ "',organization_code_id='" + organizationCodeId
						+ "',oper_user='" + operUser + "',dept_name='"
						+ deptName + "',remark='" + remark + "' where in_id="
						+ agentInId;
				logger.info("修改代理商：" + sql);
				session.update(sql);
				// 修改用户信息
				sql = "update canal_user set user_name='" + userName
						+ "',user_kind='" + userKind + "',user_state='"
						+ userState + "',canal_name='" + canalName
						+ "',user_agent='" + agentName1 + "',user_authority='"
						+ userAuthority + "',user_id_card='" + userIdCard
						+ "',user_pinyin='" + userPinyin + "',user_dept='"
						+ canalDept + "',user_email='" + userEmail
						+ "',user_role='" + userRole + "',user_count_id='"
						+ userCountId + "',user_sex='" + userSex
						+ "',user_birthday='" + userBirthday
						+ "',user_nativeplace='" + userNativeplace
						+ "',user_phone='" + userPhone + "',user_education='"
						+ userEducation + "',user_certification='"
						+ userCertification + "',user_in_date='" + userInDate
						+ "',user_out_date='" + userOutDate
						+ "',user_address='" + userAddress
						+ "',user_position='" + userPosition + "',user_way='"
						+ userWay + "',user_region_name='" + userRegionName
						+ "',oper_user='" + operUser + "',dept_name='"
						+ deptName + "',oper_time=sysdate(),remark='" + remark
						+ "' where canal_in_id=" + inId;
				logger.info("修改用户：" + sql);
				session.update(sql);

				// 修改渠道状态表
				sql = "update qdzc.canal_step_state set canal_name = '"
						+ canalName + "' where in_id='" + inId + "'";

				logger.info("修改渠道状态表：" + sql);
				session.update(sql);

				// 把驳回原因或者通过备注更新到canal_step_state表中
				sql = "update qdzc.canal_step_state set reason='" + remark
						+ "' where in_id='" + inId + "'";
				logger.info("添加备注原因至canal_step_state中：" + sql);
				session.update(sql);

				session.closeSession();
				resultStr = "success";
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