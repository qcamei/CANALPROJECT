package com.cqupt.canalManage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.opensymphony.xwork2.ActionSupport;

public class CanalInfoAddAction_0 extends ActionSupport {
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

		String inId = request.getParameter("inId");
		// 代理商信息
		String manageModel = DecodeUtils.decodeRequestString(request
				.getParameter("manageModel"));// 管理模式
		String is11888Card = DecodeUtils.decodeRequestString(request
				.getParameter("is11888Card"));// 是否发11888卡
		String parentId = DecodeUtils.decodeRequestString(request
				.getParameter("parentId"));// 上级合作伙伴编码
		String parentName = DecodeUtils.decodeRequestString(request
				.getParameter("parentName"));// 上级合作伙伴名称
		String companyAddress = DecodeUtils.decodeRequestString(request
				.getParameter("companyAddress"));// 公司地址
		String ondutyPersonAddress = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonAddress"));// 负责人联系地址
		String ondutyPersonQqnum = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonQqnum"));// 负责人QQ号
		String postCode = DecodeUtils.decodeRequestString(request
				.getParameter("postCode"));// 邮政编码
		String agentFax = DecodeUtils.decodeRequestString(request
				.getParameter("agentFax"));// 传真
		String agentEmail = DecodeUtils.decodeRequestString(request
				.getParameter("agentEmail"));// 邮箱
		String payWay = DecodeUtils.decodeRequestString(request
				.getParameter("payWay"));// 付款方式
		String bankAccountName = DecodeUtils.decodeRequestString(request
				.getParameter("bankAccountName"));// 银行账户名称
		String weituoStartBank = DecodeUtils.decodeRequestString(request
				.getParameter("weituoStartBank"));// 委托代理商开户银行
		String weituoBankAccount = DecodeUtils.decodeRequestString(request
				.getParameter("weituoBankAccount"));// 委托代理商开户银行帐号
		String weituoBankName = DecodeUtils.decodeRequestString(request
				.getParameter("weituoBankName"));// 委托代理商开户银行名称
		String payObject = DecodeUtils.decodeRequestString(request
				.getParameter("payObject"));// 支付对象
		String registerMoney = DecodeUtils.decodeRequestString(request
				.getParameter("registerMoney"));// 注册资本
		String registerDate = DecodeUtils.decodeRequestString(request
				.getParameter("registerDate"));// 工商登记时间
		String businessLicenseId = DecodeUtils.decodeRequestString(request
				.getParameter("businessLicenseId"));// 法人营业执照编号
		String taxRegisteId = DecodeUtils.decodeRequestString(request
				.getParameter("taxRegisteId"));// 税务登记证编号
		String bankPermisionId = DecodeUtils.decodeRequestString(request
				.getParameter("bankPermisionId"));// 银行开户许可证编号
		String organizationCodeId = DecodeUtils.decodeRequestString(request
				.getParameter("organizationCodeId"));// 组织机构代码证编号

		// 渠道信息
		String rentMoney = DecodeUtils.decodeRequestString(request
				.getParameter("rentMoney"));// 月租金额
		String allowancePolicy = DecodeUtils.decodeRequestString(request
				.getParameter("allowancePolicy"));// 合同期补贴政策
		// String canalManager = DecodeUtils.decodeRequestString(request
		// .getParameter("canalManager"));// 所属渠道客户经理
		// String canalManagerId = "";// 归属渠道客户经理ＩＤ,需要查询
		// String canalManagerId =
		// DecodeUtils.decodeRequestString(request.getParameter("canalManagerId"));
		String canalParentId = DecodeUtils.decodeRequestString(request
				.getParameter("canalParentId"));// 归属上级渠道编码
		String canalParentName = DecodeUtils.decodeRequestString(request
				.getParameter("canalParentName"));// 归属上级渠道名称
		String houseOwnerName = DecodeUtils.decodeRequestString(request
				.getParameter("houseOwnerName"));// 房产权所有人姓名
		String houseOwnerPhone = DecodeUtils.decodeRequestString(request
				.getParameter("houseOwnerPhone"));// 房产权所有人电话
		String distributeCard = DecodeUtils.decodeRequestString(request
				.getParameter("distributeCard"));// 是否发卡网点
		String inPersonIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("inPersonIdCard"));// 开户人身份证号
		String inBankAccount = DecodeUtils.decodeRequestString(request
				.getParameter("inBankAccount"));// 营收资金银行账号
		String inBankAccountNumber = DecodeUtils.decodeRequestString(request
				.getParameter("inBankAccountNumber"));// 开户号
		String inBankName = DecodeUtils.decodeRequestString(request
				.getParameter("inBankName"));// 开户银行名

		// 营业员表
		String userKind = DecodeUtils.decodeRequestString(request
				.getParameter("userKind"));// 员工类别
		String userState = DecodeUtils.decodeRequestString(request
				.getParameter("userState"));// 员工状态
		String userIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("userIdCard"));// 身份证号
		String userAuthority = DecodeUtils.decodeRequestString(request
				.getParameter("userAuthority"));// 工号权限
		String userEmail = DecodeUtils.decodeRequestString(request
				.getParameter("userEmail"));// 邮箱
		String userRole = DecodeUtils.decodeRequestString(request
				.getParameter("userRole"));// 计费角色
		String userCountId = DecodeUtils.decodeRequestString(request
				.getParameter("userCountId"));// 计费工号
		String userBirthday = DecodeUtils.decodeRequestString(request
				.getParameter("userBirthday"));// 生日
		String userNativeplace = DecodeUtils.decodeRequestString(request
				.getParameter("userNativeplace"));// 籍贯
		String userPhone = DecodeUtils.decodeRequestString(request
				.getParameter("userPhone"));// 联系电话

		String userEducation = DecodeUtils.decodeRequestString(request
				.getParameter("userEducation"));// 文化程度
		String userCertification = DecodeUtils.decodeRequestString(request
				.getParameter("userCertification"));// 技能证书
		String userInDate = DecodeUtils.decodeRequestString(request
				.getParameter("userInDate"));// 入职时间
		String userOutDate = DecodeUtils.decodeRequestString(request
				.getParameter("userOutDate"));// 离职时间
		String userAddress = DecodeUtils.decodeRequestString(request
				.getParameter("userAddress"));// 住址
		String userPosition = DecodeUtils.decodeRequestString(request
				.getParameter("userPosition"));// 岗位
		String userWay = DecodeUtils.decodeRequestString(request
				.getParameter("userWay"));// 用工方式
		String userRegionName = DecodeUtils.decodeRequestString(request
				.getParameter("userRegionName"));// 地区名称

		String agentPointType = DecodeUtils.decodeRequestString(request
				.getParameter("agentPointType"));// 代理商类型
		String sellPointType = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointType"));// 销售点类型
		String manageWay = DecodeUtils.decodeRequestString(request
				.getParameter("manageWay"));// 自营厅经营方式
		String openSellYetai = DecodeUtils.decodeRequestString(request
				.getParameter("openSellYetai"));// 开放销售点业态
		String liansuoCatorySign = DecodeUtils.decodeRequestString(request
				.getParameter("liansuoCatorySign"));// 连锁分类标识

		String topZiyou = DecodeUtils.decodeRequestString(request
				.getParameter("topZiyou"));// top自有厅标识
		String majorMendianType = DecodeUtils.decodeRequestString(request
				.getParameter("majorMendianType"));// 专营门店类型
		String grantMendianClass = DecodeUtils.decodeRequestString(request
				.getParameter("grantMendianClass"));// 授权门店级别
		String bianlidianTask = DecodeUtils.decodeRequestString(request
				.getParameter("bianlidianTask"));// 便利店业务
		String sellPointTask = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointTask"));// 销售点业务范围

		String sellPointSellType = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointSellType"));// 销售点卖场类型
		String kechangEntity = DecodeUtils.decodeRequestString(request
				.getParameter("kechangEntity"));// 商客实体店细分
		String factoryCanalSign = DecodeUtils.decodeRequestString(request
				.getParameter("factoryCanalSign"));// 厂商渠道标识
		String isDragonSystem = DecodeUtils.decodeRequestString(request
				.getParameter("isDragonSystem"));// 龙系统是否接入

		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));// 备注19

		try {
			session = DataStormSession.getInstance();
			// 根据渠道的inId查出代理商的agent_in_id
			sql = "select agent_in_id from qdzc.canal_infomation where in_id="
					+ inId;
			logger.info("查出agentInId" + sql);
			Map resultMap = session.findSql(sql).get(0);
			String agentInId = (String) resultMap.get("agentInId");

			// 添加渠道信息
			sql = "update qdzc.canal_infomation set canal_parent_id='"
					+ canalParentId + "',canal_parent_name='" + canalParentName
					+ "',canal_manager='字段不用',rent_money='" + rentMoney
					+ "',allowance_policy='" + allowancePolicy
					+ "',house_owner_name='" + houseOwnerName
					+ "',house_owner_phone='" + houseOwnerPhone
					+ "',distribute_card='" + distributeCard
					+ "',in_bank_account='" + inBankAccount
					+ "',in_bank_account_number='" + inBankAccountNumber
					+ "',in_bank_name='" + inBankName + "',in_person_id_card='"
					+ inPersonIdCard + "',agent_point_type='" + agentPointType
					+ "',sell_point_type='" + sellPointType + "',manage_way='"
					+ manageWay + "',open_sell_yetai='" + openSellYetai
					+ "',liansuo_catory_sign='" + liansuoCatorySign
					+ "',top_ziyou='" + topZiyou + "',major_mendian_type='"
					+ majorMendianType + "',grant_mendian_class='"
					+ grantMendianClass + "',bianlidian_task='"
					+ bianlidianTask + "',sell_point_task='" + sellPointTask
					+ "',sell_point_sell_type='" + sellPointSellType
					+ "',kechang_entity='" + kechangEntity
					+ "',factory_canal_sign='" + factoryCanalSign
					+ "',is_dragon_system='" + isDragonSystem + "',remark='"
					+ remark + "' where in_id='" + inId + "' ";
			logger.info("根据补录内容修改渠道信息：" + sql);
			session.update(sql);

			// 补录内容插入代理商表
			sql = "update qdzc.agent_information set manage_model='"
					+ manageModel + "', is_11888_card='" + is11888Card
					+ "', parent_id='" + parentId + "', parent_name='"
					+ parentName + "', company_address='" + companyAddress
					+ "', onduty_person_address='" + ondutyPersonAddress
					+ "', onduty_person_qqnum='" + ondutyPersonQqnum
					+ "', post_code='" + postCode + "', agent_fax='" + agentFax
					+ "', agent_email='" + agentEmail + "', pay_way='" + payWay
					+ "', bank_account_name='" + bankAccountName
					+ "', weituo_start_bank='" + weituoStartBank
					+ "', weituo_bank_account='" + weituoBankAccount
					+ "', weituo_bank_name='" + weituoBankName
					+ "', pay_object='" + payObject + "', register_money='"
					+ registerMoney + "', register_date='" + registerDate
					+ "', business_license_id='" + businessLicenseId
					+ "', tax_registe_id='" + taxRegisteId
					+ "', bank_permision_id='" + bankPermisionId
					+ "', organization_code_id='" + organizationCodeId
					+ "', remark='" + remark + "' where in_id='" + agentInId
					+ "' ";
			logger.info("修改代理商信息：" + sql);
			session.update(sql);
			// 根据补录内容修改员工信息
			sql = "update qdzc.canal_user set user_kind='" + userKind
					+ "', user_state='" + userState + "', user_id_card='"
					+ userIdCard + "', user_authority='" + userAuthority
					+ "',user_email='" + userEmail + "', user_role='"
					+ userRole + "', user_count_id='" + userCountId
					+ "', user_birthday='" + userBirthday
					+ "', user_nativeplace='" + userNativeplace
					+ "', user_phone='" + userPhone + "', user_education='"
					+ userEducation + "', user_certification='"
					+ userCertification + "', user_in_date='" + userInDate
					+ "', user_out_date='" + userOutDate + "', user_address='"
					+ userAddress + "', user_position='" + userPosition
					+ "', user_way='" + userWay + "', user_region_name='"
					+ userRegionName + "', remark='" + remark
					+ "' where canal_in_id='" + inId + "' ";
			logger.info("修改员工信息：" + sql);
			session.update(sql);

			sql = "update qdzc.canal_step_state set is_bulu='是' where in_id='"
					+ inId + "' ";
			logger.info("修改补录字段的sql" + sql);
			session.update(sql);

			session.closeSession();
			resultStr = "success";
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