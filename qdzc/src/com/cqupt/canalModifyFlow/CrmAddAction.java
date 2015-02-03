package com.cqupt.canalModifyFlow;

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

public class CrmAddAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.print(getDate());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String getDate() {
		String resultStr = "";
		String sql = "";
		DataStormSession session = null;
		int currentStep = 0;
		String inId = "";
		String canalId = "";

		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			String deptId = request.getSession().getAttribute("deptId")
					.toString();
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();
			String userName = request.getSession().getAttribute("userName")
					.toString();
			String deptName = request.getSession().getAttribute("deptName")
					.toString();
			inId = DecodeUtils
					.decodeRequestString(request.getParameter("inId"));
			canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			String phone = java.net.URLDecoder.decode(
					request.getParameter("phone"), "UTF-8");

			String crmNumber = java.net.URLDecoder.decode(
					request.getParameter("crmNumber"), "UTF-8");
			session = DataStormSession.getInstance();

			sql = "select current_step from qdzc.canal_step_state_his where  in_id = '"
					+ inId + "'";
			logger.info(sql);
			List list1 = session.findSql(sql);
			Map map = (Map) list1.get(0);
			currentStep = (Integer) map.get("currentStep");

			sql = "select * from qdzc.canal_infomation where canal_id='"
					+ canalId + "'";
			logger.info("更新渠道信息表：" + sql);
			Map resultMap4 = session.findSql(sql).get(0);
			String canalName = resultMap4.get("canalName").toString();
			String agentId = resultMap4.get("agentId").toString();
			int number = (Integer) resultMap4.get("number");
			System.out.println("打印吃的number为u" + number);
			if (number > 0) {
				number = number - 1;
				sql = "update qdzc.canal_infomation set number='" + number
						+ "' where canal_id='" + canalId + "' ";
				session.update(sql);
			}
			if (currentStep != 8) {
				sql = "SELECT * FROM step_info_modify WHERE pre_step_no="
						+ currentStep;
				Map resultMap = session.findSql(sql).get(0);
				currentStep = (Integer) resultMap.get("stepNo");
				String currentStepVal = resultMap.get("stepVal").toString();
				sql = "SELECT * FROM step_info_modify WHERE pre_step_no='"
						+ currentStep + "'";
				Map resultMap3 = session.findSql(sql).get(0);
				currentStep = (Integer) resultMap3.get("stepNo");// 8
				currentStepVal = resultMap3.get("stepVal").toString();// 工单归档

				sql = "update qdzc.canal_step_state_his set current_step = "
						+ currentStep
						+ ",crm_time=sysdate(),"
						+ "crm_name='"
						+ currentStepVal
						+ "',crm_state='完成',is_back='无',reason='无' where in_id='"
						+ inId + "'";
				logger.info("更新渠道状态表：" + sql);
				session.update(sql);
				// 5.插入环节详情
				sql = "insert into qdzc.process_detail_modify (in_id,canal_id,canal_name,step_no,step_val,oper_user,dept_id,dept_name,oper_time,process_state,remark) values ("
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
						+ "',sysdate(),'完成','无')";
				logger.info("操作入环节详情：" + sql);
				session.add(sql);
			}

			// 4.操作入环节“CRM”
			sql = "insert into qdzc.process5_open_CRM_his (in_id,canal_id,canal_name,crm_number,telephone,oper_user,dept_id,dept_name,oper_time,remark) values ("
					+ "'"
					+ inId
					+ "','"
					+ canalId
					+ "','"
					+ canalName
					+ "','"
					+ crmNumber
					+ "','"
					+ phone
					+ "','"
					+ userName
					+ "','"
					+ deptId + "','" + deptName + "',sysdate(),'无')";
			logger.info("操作入环节“分管部门审核”：" + sql);
			session.add(sql);

			// 更新完成后把所有信息更新到渠道信息表、渠道营业员表、归属代理商表
			sql = "select * from qdzc.canal_infomation_his where canal_id='"
					+ canalId + "'";
			Map resultMap5 = session.findSql(sql).get(0);
			if (((String) resultMap5.get("canalName")) != null) {
				sql = "update qdzc.canal_infomation set canal_name='"
						+ (String) resultMap5.get("canalName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			String areaName = (String) resultMap5.get("areaName");
			if (areaName != null) {
				sql = "update qdzc.canal_infomation set area_name='"
						+ (String) resultMap5.get("areaName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if ((String) resultMap5.get("canalAddress") != null) {
				sql = "update qdzc.canal_infomation set canal_address='"
						+ (String) resultMap5.get("canalAddress")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("regionCharacter")) != null) {
				sql = "update qdzc.canal_infomation set region_character='"
						+ (String) resultMap5.get("regionCharacter")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalState")) != null) {
				sql = "update qdzc.canal_infomation set canal_state='"
						+ (String) resultMap5.get("canalState")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalForm")) != null) {
				sql = "update qdzc.canal_infomation set canal_form='"
						+ (String) resultMap5.get("canalForm")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("nativeType")) != null) {
				sql = "update qdzc.canal_infomation set native_type='"
						+ (String) resultMap5.get("nativeType")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalProperty")) != null) {
				sql = "update qdzc.canal_infomation set canal_property='"
						+ (String) resultMap5.get("canalProperty")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalType")) != null) {
				sql = "update qdzc.canal_infomation set canal_type='"
						+ (String) resultMap5.get("canalType")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalParentId")) != null) {
				sql = "update qdzc.canal_infomation set canal_parent_id='"
						+ (String) resultMap5.get("canalParentId")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalParentName")) != null) {
				sql = "update qdzc.canal_infomation set canal_parent_name='"
						+ (String) resultMap5.get("canalParentName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("agentName")) != null) {
				sql = "update qdzc.canal_infomation set agent_name='"
						+ (String) resultMap5.get("agentName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalNameStart")) != null) {
				sql = "update qdzc.canal_infomation set canal_name_start='"
						+ (String) resultMap5.get("canalNameStart")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalUserName")) != null) {
				sql = "update qdzc.canal_infomation set canal_user_name='"
						+ (String) resultMap5.get("canalUserName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalUserPhone")) != null) {
				sql = "update qdzc.canal_infomation set canal_user_phone='"
						+ (String) resultMap5.get("canalUserPhone")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalIdCard")) != null) {
				sql = "update qdzc.canal_infomation set canal_id_card='"
						+ (String) resultMap5.get("canalIdCard")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalManager")) != null) {
				sql = "update qdzc.canal_infomation set canal_manager='"
						+ (String) resultMap5.get("canalManager")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalManagerId")) != null) {
				sql = "update qdzc.canal_infomation set canal_manager_id='"
						+ (String) resultMap5.get("canalManagerId")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("urbanIdetity")) != null) {
				sql = "update qdzc.canal_infomation set urban_idetity='"
						+ (String) resultMap5.get("urbanIdetity")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("expireType")) != null) {
				sql = "update qdzc.canal_infomation set expire_type='"
						+ (String) resultMap5.get("expireType")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("businessArea")) != null) {
				sql = "update qdzc.canal_infomation set business_area='"
						+ (String) resultMap5.get("businessArea")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("rentMoney")) != null) {
				sql = "update qdzc.canal_infomation set rent_money='"
						+ (String) resultMap5.get("rentMoney")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap5.get("allowancePolicy")) != null) {
				sql = "update qdzc.canal_infomation set allowance_policy='"
						+ (String) resultMap5.get("allowancePolicy")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("firstRentAllowance")) != null) {
				sql = "update qdzc.canal_infomation set first_rent_allowance='"
						+ (String) resultMap5.get("firstRentAllowance")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("firstDecorationAllowance")) != null) {
				sql = "update qdzc.canal_infomation set first_decoration_allowance='"
						+ (String) resultMap5.get("firstDecorationAllowance")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("canalDept")) != null) {
				sql = "update qdzc.canal_infomation set canal_dept='"
						+ (String) resultMap5.get("canalDept")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("houseOwnerName")) != null) {
				sql = "update qdzc.canal_infomation set house_owner_name='"
						+ (String) resultMap5.get("houseOwnerName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("houseOwnerPhone")) != null) {
				sql = "update qdzc.canal_infomation set house_owner_phone='"
						+ (String) resultMap5.get("houseOwnerPhone")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("distributeCard")) != null) {
				sql = "update qdzc.canal_infomation set distribute_card='"
						+ (String) resultMap5.get("distributeCard")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("inBankAccount")) != null) {
				sql = "update qdzc.canal_infomation set in_bank_account='"
						+ (String) resultMap5.get("inBankAccount")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("inBankAccountNumber")) != null) {
				sql = "update qdzc.canal_infomation set in_bank_account_number='"
						+ (String) resultMap5.get("inBankAccountNumber")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("inBankName")) != null) {
				sql = "update qdzc.canal_infomation set in_bank_name='"
						+ (String) resultMap5.get("inBankName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("inCollectNumber")) != null) {
				sql = "update qdzc.canal_infomation set in_collect_number='"
						+ (String) resultMap5.get("inCollectNumber")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("inPersonIdCard")) != null) {
				sql = "update qdzc.canal_infomation set in_person_id_card='"
						+ (String) resultMap5.get("inPersonIdCard")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("contractScanUrl")) != null) {
				sql = "update qdzc.canal_infomation set contract_scan_url='"
						+ (String) resultMap5.get("contractScanUrl")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("isMoney")) != null) {
				sql = "update qdzc.canal_infomation set is_money='"
						+ (String) resultMap5.get("isMoney")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("taskType")) != null) {
				sql = "update qdzc.canal_infomation set task_type='"
						+ (String) resultMap5.get("taskType")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("guaranteeAmount")) != null) {
				sql = "update qdzc.canal_infomation set guarantee_amount='"
						+ (String) resultMap5.get("guaranteeAmount")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("guaranteeUser")) != null) {
				sql = "update qdzc.canal_infomation set guarantee_user='"
						+ (String) resultMap5.get("guaranteeUser")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("guaranteeTime")) != null) {
				sql = "update qdzc.canal_infomation set guarantee_time='"
						+ (String) resultMap5.get("guaranteeTime")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("guaranteeReceiptScanUrl")) != null) {
				sql = "update qdzc.canal_infomation set guarantee_receipt_scan_url='"
						+ (String) resultMap5.get("guaranteeReceiptScanUrl")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("operUserNum")) != null) {
				sql = "update qdzc.canal_infomation set oper_user_num='"
						+ (String) resultMap5.get("operUserNum")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("agentPointType")) != null) {
				sql = "update qdzc.canal_infomation set agent_point_type='"
						+ (String) resultMap5.get("agentPointType")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("sellPointType")) != null) {
				sql = "update qdzc.canal_infomation set sell_point_type='"
						+ (String) resultMap5.get("sellPointType")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("manageWay")) != null) {
				sql = "update qdzc.canal_infomation set manage_way='"
						+ (String) resultMap5.get("manageWay")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("openSellYetai")) != null) {
				sql = "update qdzc.canal_infomation set open_sell_yetai='"
						+ (String) resultMap5.get("openSellYetai")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("liansuoCatorySign")) != null) {
				sql = "update qdzc.canal_infomation set liansuo_catory_sign='"
						+ (String) resultMap5.get("liansuoCatorySign")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("topZiyou")) != null) {
				sql = "update qdzc.canal_infomation set top_ziyou='"
						+ (String) resultMap5.get("topZiyou")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("majorMendianType")) != null) {
				sql = "update qdzc.canal_infomation set major_mendian_type='"
						+ (String) resultMap5.get("majorMendianType")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("grantMendianClass")) != null) {
				sql = "update qdzc.canal_infomation set grant_mendian_class='"
						+ (String) resultMap5.get("grantMendianClass")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("bianlidianTask")) != null) {
				sql = "update qdzc.canal_infomation set bianlidian_task='"
						+ (String) resultMap5.get("bianlidianTask")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("sellPointTask")) != null) {
				sql = "update qdzc.canal_infomation set sell_point_task='"
						+ (String) resultMap5.get("sellPointTask")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("sellPointSellType")) != null) {
				sql = "update qdzc.canal_infomation set sell_point_sell_type='"
						+ (String) resultMap5.get("sellPointSellType")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("kechangEntity")) != null) {
				sql = "update qdzc.canal_infomation set kechang_entity='"
						+ (String) resultMap5.get("kechangEntity")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("factoryCanalSign")) != null) {
				sql = "update qdzc.canal_infomation set factory_canal_sign='"
						+ (String) resultMap5.get("factoryCanalSign")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("isCrm")) != null) {
				sql = "update qdzc.canal_infomation set is_crm='"
						+ (String) resultMap5.get("isCrm")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap5.get("isDragonSystem")) != null) {
				sql = "update qdzc.canal_infomation set is_dragon_system='"
						+ (String) resultMap5.get("isDragonSystem")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			sql = "select * from qdzc.agent_information_his where agent_id='"
					+ agentId + "' ";
			Map resultMap6 = session.findSql(sql).get(0);
			if (((String) resultMap6.get("branchCompany")) != null) {
				sql = "update qdzc.agent_information set branch_company='"
						+ (String) resultMap6.get("branchCompany")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("areaName")) != null) {
				sql = "update qdzc.agent_information set area_name='"
						+ (String) resultMap6.get("areaName")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("agentName")) != null) {
				sql = "update qdzc.agent_information set agent_name='"
						+ (String) resultMap6.get("agentName")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("agentShortName")) != null) {
				sql = "update qdzc.agent_information set agent_short_name='"
						+ (String) resultMap6.get("agentShortName")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("agentDept")) != null) {
				sql = "update qdzc.agent_information set agent_dept='"
						+ (String) resultMap6.get("agentDept")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("agentLevel")) != null) {
				sql = "update qdzc.agent_information set agent_level='"
						+ (String) resultMap6.get("agentLevel")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("companyType")) != null) {
				sql = "update qdzc.agent_information set company_type='"
						+ (String) resultMap6.get("companyType")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("cooperationType")) != null) {
				sql = "update qdzc.agent_information set cooperation_type='"
						+ (String) resultMap6.get("cooperationType")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("legalPerson")) != null) {
				sql = "update qdzc.agent_information set legal_person='"
						+ (String) resultMap6.get("legalPerson")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("legalPhone")) != null) {
				sql = "update qdzc.agent_information set legal_phone='"
						+ (String) resultMap6.get("legalPhone")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("legalCardType")) != null) {
				sql = "update qdzc.agent_information set legal_card_type='"
						+ (String) resultMap6.get("legalCardType")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("legalIdCard")) != null) {
				sql = "update qdzc.agent_information set legal_id_card='"
						+ (String) resultMap6.get("legalIdCard")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("manageModel")) != null) {
				sql = "update qdzc.agent_information set manage_model='"
						+ (String) resultMap6.get("manageModel")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("is11888Card")) != null) {
				sql = "update qdzc.agent_information set is_11888_card='"
						+ (String) resultMap6.get("is_11888Card")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("ondutyPerson")) != null) {
				sql = "update qdzc.agent_information set onduty_person='"
						+ (String) resultMap6.get("ondutyPerson")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("ondutyPersonPhone")) != null) {
				sql = "update qdzc.agent_information set onduty_person_phone='"
						+ (String) resultMap6.get("ondutyPersonPhone")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("startBank")) != null) {
				sql = "update qdzc.agent_information set start_bank='"
						+ (String) resultMap6.get("startBank")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("bankAccountid")) != null) {
				sql = "update qdzc.agent_information set bank_accountId='"
						+ (String) resultMap6.get("bankAccountid")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("parentId")) != null) {
				sql = "update qdzc.agent_information set parent_id='"
						+ (String) resultMap6.get("parentId")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("parentName")) != null) {
				sql = "update qdzc.agent_information set parent_name='"
						+ (String) resultMap6.get("parentName")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("companyAddress")) != null) {
				sql = "update qdzc.agent_information set company_address='"
						+ (String) resultMap6.get("companyAddress")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("ondutyPersonAddress")) != null) {
				sql = "update qdzc.agent_information set onduty_person_address='"
						+ (String) resultMap6.get("ondutyPersonAddress")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}

			if (((String) resultMap6.get("ondutyPersonQqnum")) != null) {
				sql = "update qdzc.agent_information set onduty_person_qqnum='"
						+ (String) resultMap6.get("ondutyPersonQqnum")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("postCode")) != null) {
				sql = "update qdzc.agent_information set post_code='"
						+ (String) resultMap6.get("postCode")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("agentFax")) != null) {
				sql = "update qdzc.agent_information set agent_fax='"
						+ (String) resultMap6.get("agentFax")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("agentEmail")) != null) {
				sql = "update qdzc.agent_information set agent_email='"
						+ (String) resultMap6.get("agentEmail")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("payWay")) != null) {
				sql = "update qdzc.agent_information set pay_way='"
						+ (String) resultMap6.get("payWay")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("bankAccountName")) != null) {
				sql = "update qdzc.agent_information set bank_account_name='"
						+ (String) resultMap6.get("bankAccountName")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}

			if (((String) resultMap6.get("weituoStartBank")) != null) {
				sql = "update qdzc.agent_information set weituo_start_bank='"
						+ (String) resultMap6.get("weituoStartBank")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("weituoBankAccount")) != null) {
				sql = "update qdzc.agent_information set weituo_bank_account='"
						+ (String) resultMap6.get("weituoBankAccount")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("weituoBankName")) != null) {
				sql = "update qdzc.agent_information set weituo_bank_name='"
						+ (String) resultMap6.get("weituoBankName")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("payObject")) != null) {
				sql = "update qdzc.agent_information set pay_object='"
						+ (String) resultMap6.get("payObject")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("registerMoney")) != null) {
				sql = "update qdzc.agent_information set register_money='"
						+ (String) resultMap6.get("registerMoney")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("registerDate")) != null) {
				sql = "update qdzc.agent_information set register_date='"
						+ (String) resultMap6.get("registerDate")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}

			if (((String) resultMap6.get("businessLicenseId")) != null) {
				sql = "update qdzc.agent_information set business_license_id='"
						+ (String) resultMap6.get("businessLicenseId")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("taxRegisteId")) != null) {
				sql = "update qdzc.agent_information set tax_registe_id='"
						+ (String) resultMap6.get("taxRegisteId")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("bankPermisionId")) != null) {
				sql = "update qdzc.agent_information set bank_permision_id='"
						+ (String) resultMap6.get("bankPermisionId")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("organizationCodeId")) != null) {
				sql = "update qdzc.agent_information set organization_code_id='"
						+ (String) resultMap6.get("organizationCodeId")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("registerMoney")) != null) {
				sql = "update qdzc.agent_information set register_money='"
						+ (String) resultMap6.get("registerMoney")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			if (((String) resultMap6.get("registerDate")) != null) {
				sql = "update qdzc.agent_information set register_date='"
						+ (String) resultMap6.get("registerDate")
						+ "' where agent_id='" + agentId + "'";
				session.update(sql);
			}
			sql = "select * from qdzc.canal_user_his where canal_id='"
					+ canalId + "' ";
			Map resultMap7 = session.findSql(sql).get(0);
			if (((String) resultMap7.get("userName")) != null) {
				sql = "update qdzc.canal_user set user_name='"
						+ (String) resultMap7.get("userName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userKind")) != null) {
				sql = "update qdzc.canal_user set user_kind='"
						+ (String) resultMap7.get("userKind")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userCellphone")) != null) {
				sql = "update qdzc.canal_user set user_cellphone='"
						+ (String) resultMap7.get("userCellphone")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("canal_name")) != null) {
				sql = "update qdzc.canal_user set user_name='"
						+ (String) resultMap7.get("userName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userName")) != null) {
				sql = "update qdzc.canal_user set user_name='"
						+ (String) resultMap7.get("userName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userName")) != null) {
				sql = "update qdzc.canal_user set user_name='"
						+ (String) resultMap7.get("userName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("canalName")) != null) {
				sql = "update qdzc.canal_user set canal_name='"
						+ (String) resultMap7.get("canalName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userAgent")) != null) {
				sql = "update qdzc.canal_user set user_agent='"
						+ (String) resultMap7.get("userAgent")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userAuthority")) != null) {
				sql = "update qdzc.canal_user set user_authority='"
						+ (String) resultMap7.get("userAuthority")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userIdCard")) != null) {
				sql = "update qdzc.canal_user set user_id_card='"
						+ (String) resultMap7.get("userIdCard")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userPinyin")) != null) {
				sql = "update qdzc.canal_user set user_pinyin='"
						+ (String) resultMap7.get("userPinyin")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userDept")) != null) {
				sql = "update qdzc.canal_user set user_dept='"
						+ (String) resultMap7.get("userDept")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userEmail")) != null) {
				sql = "update qdzc.canal_user set user_email='"
						+ (String) resultMap7.get("userEmail")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userRole")) != null) {
				sql = "update qdzc.canal_user set user_role='"
						+ (String) resultMap7.get("userRole")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userCountId")) != null) {
				sql = "update qdzc.canal_user set user_count_id='"
						+ (String) resultMap7.get("userCountId")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userSex")) != null) {
				sql = "update qdzc.canal_user set user_sex='"
						+ (String) resultMap7.get("userSex")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userBirthday")) != null) {
				sql = "update qdzc.canal_user set user_birthday='"
						+ (String) resultMap7.get("userBirthday")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userNativeplace")) != null) {
				sql = "update qdzc.canal_user set user_nativeplace='"
						+ (String) resultMap7.get("userNativeplace")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userPhone")) != null) {
				sql = "update qdzc.canal_user set user_phone='"
						+ (String) resultMap7.get("userPhone")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userEducation")) != null) {
				sql = "update qdzc.canal_user set user_education='"
						+ (String) resultMap7.get("userEducation")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userCertification")) != null) {
				sql = "update qdzc.canal_user set user_certification='"
						+ (String) resultMap7.get("userCertification")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userInDate")) != null) {
				sql = "update qdzc.canal_user set user_in_date='"
						+ (String) resultMap7.get("userInDate")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userOutDate")) != null) {
				sql = "update qdzc.canal_user set user_out_date='"
						+ (String) resultMap7.get("userOutDate")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userAddress")) != null) {
				sql = "update qdzc.canal_user set user_address='"
						+ (String) resultMap7.get("userAddress")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}

			if (((String) resultMap7.get("userPosition")) != null) {
				sql = "update qdzc.canal_user set user_position='"
						+ (String) resultMap7.get("userPosition")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userWay")) != null) {
				sql = "update qdzc.canal_user set user_way='"
						+ (String) resultMap7.get("userWay")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			if (((String) resultMap7.get("userRegionName")) != null) {
				sql = "update qdzc.canal_user set user_region_name='"
						+ (String) resultMap7.get("userRegionName")
						+ "' where canal_id='" + canalId + "'";
				session.update(sql);
			}
			// 更新渠道表的crm工号
			sql = "update qdzc.canal_infomation set crm_number='" + crmNumber
					+ "' where canal_id='" + canalId + "'";
			session.update(sql);
			session.closeSession();
			resultStr = "success";
		} catch (Exception e) {
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
