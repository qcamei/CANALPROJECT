package com.cqupt.canalModifyFlow;

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

public class AlterInfoAddAction_0 extends ActionSupport {
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
		String manageModelOld = DecodeUtils.decodeRequestString(request
				.getParameter("manageModelOld"));// 管理模式
		String is11888CardOld = DecodeUtils.decodeRequestString(request
				.getParameter("is11888CardOld"));// 是否发11888卡
		String parentIdOld = DecodeUtils.decodeRequestString(request
				.getParameter("parentIdOld"));// 上级合作伙伴编码
		String parentNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("parentNameOld"));// 上级合作伙伴名称
		String companyAddress = DecodeUtils.decodeRequestString(request
				.getParameter("companyAddress"));// 公司地址
		String ondutyPersonAddress = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonAddress"));// 负责人联系地址
		String ondutyPersonQqnum = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonQqnum"));// 负责人QQ号
		String companyAddressOld = DecodeUtils.decodeRequestString(request
				.getParameter("companyAddressOld"));// 公司地址
		String ondutyPersonAddressOld = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonAddressOld"));// 负责人联系地址
		String ondutyPersonQqnumOld = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonQqnumOld"));// 负责人QQ号
		String postCode = DecodeUtils.decodeRequestString(request
				.getParameter("postCode"));// 邮政编码
		String agentFax = DecodeUtils.decodeRequestString(request
				.getParameter("agentFax"));// 传真
		String agentEmail = DecodeUtils.decodeRequestString(request
				.getParameter("agentEmail"));// 邮箱
		String postCodeOld = DecodeUtils.decodeRequestString(request
				.getParameter("postCodeOld"));// 邮政编码
		String agentFaxOld = DecodeUtils.decodeRequestString(request
				.getParameter("agentFaxOld"));// 传真
		String agentEmailOld = DecodeUtils.decodeRequestString(request
				.getParameter("agentEmailOld"));// 邮箱
		String payWay = DecodeUtils.decodeRequestString(request
				.getParameter("payWay"));// 付款方式
		String bankAccountName = DecodeUtils.decodeRequestString(request
				.getParameter("bankAccountName"));// 银行账户名称
		String weituoStartBank = DecodeUtils.decodeRequestString(request
				.getParameter("weituoStartBank"));// 委托代理商开户银行
		String payWayOld = DecodeUtils.decodeRequestString(request
				.getParameter("payWayOld"));// 付款方式
		String bankAccountNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("bankAccountNameOld"));// 银行账户名称
		String weituoStartBankOld = DecodeUtils.decodeRequestString(request
				.getParameter("weituoStartBankOld"));// 委托代理商开户银行
		String weituoBankAccount = DecodeUtils.decodeRequestString(request
				.getParameter("weituoBankAccount"));// 委托代理商开户银行帐号
		String weituoBankName = DecodeUtils.decodeRequestString(request
				.getParameter("weituoBankName"));// 委托代理商开户银行名称
		String payObject = DecodeUtils.decodeRequestString(request
				.getParameter("payObject"));// 支付对象
		String weituoBankAccountOld = DecodeUtils.decodeRequestString(request
				.getParameter("weituoBankAccountOld"));// 委托代理商开户银行帐号
		String weituoBankNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("weituoBankNameOld"));// 委托代理商开户银行名称
		String payObjectOld = DecodeUtils.decodeRequestString(request
				.getParameter("payObjectOld"));// 支付对象
		String registerMoney = DecodeUtils.decodeRequestString(request
				.getParameter("registerMoney"));// 注册资本
		String registerDate = DecodeUtils.decodeRequestString(request
				.getParameter("registerDate"));// 工商登记时间
		String businessLicenseId = DecodeUtils.decodeRequestString(request
				.getParameter("businessLicenseId"));// 法人营业执照编号
		String registerMoneyOld = DecodeUtils.decodeRequestString(request
				.getParameter("registerMoneyOld"));// 注册资本
		String registerDateOld = DecodeUtils.decodeRequestString(request
				.getParameter("registerDateOld"));// 工商登记时间
		String businessLicenseIdOld = DecodeUtils.decodeRequestString(request
				.getParameter("businessLicenseIdOld"));// 法人营业执照编号
		String taxRegisteId = DecodeUtils.decodeRequestString(request
				.getParameter("taxRegisteId"));// 税务登记证编号
		String bankPermisionId = DecodeUtils.decodeRequestString(request
				.getParameter("bankPermisionId"));// 银行开户许可证编号
		String organizationCodeId = DecodeUtils.decodeRequestString(request
				.getParameter("organizationCodeId"));// 组织机构代码证编号
		String taxRegisteIdOld = DecodeUtils.decodeRequestString(request
				.getParameter("taxRegisteIdOld"));// 税务登记证编号
		String bankPermisionIdOld = DecodeUtils.decodeRequestString(request
				.getParameter("bankPermisionIdOld"));// 银行开户许可证编号
		String organizationCodeIdOld = DecodeUtils.decodeRequestString(request
				.getParameter("organizationCodeIdOld"));// 组织机构代码证编号

		// 渠道信息
		String rentMoney = DecodeUtils.decodeRequestString(request
				.getParameter("rentMoney"));// 月租金额
		String allowancePolicy = DecodeUtils.decodeRequestString(request
				.getParameter("allowancePolicy"));// 合同期补贴政策
		String canalManager = DecodeUtils.decodeRequestString(request
				.getParameter("canalManager"));// 所属渠道客户经理
		String canalManagerId = "";// 归属渠道客户经理ＩＤ,需要查询
		String rentMoneyOld = DecodeUtils.decodeRequestString(request
				.getParameter("rentMoneyOld"));// 月租金额
		String allowancePolicyOld = DecodeUtils.decodeRequestString(request
				.getParameter("allowancePolicyOld"));// 合同期补贴政策
		// String canalManagerOld = DecodeUtils.decodeRequestString(request
		// .getParameter("canalManagerOld"));// 所属渠道客户经理
		// String canalManagerId =
		// DecodeUtils.decodeRequestString(request.getParameter("canalManagerId"));
		String canalParentId = DecodeUtils.decodeRequestString(request
				.getParameter("canalParentId"));// 归属上级渠道编码
		String canalParentName = DecodeUtils.decodeRequestString(request
				.getParameter("canalParentName"));// 归属上级渠道名称
		String houseOwnerName = DecodeUtils.decodeRequestString(request
				.getParameter("houseOwnerName"));// 房产权所有人姓名
		String canalParentIdOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalParentIdOld"));// 归属上级渠道编码
		String canalParentNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalParentNameOld"));// 归属上级渠道名称
		String houseOwnerNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("houseOwnerNameOld"));// 房产权所有人姓名
		String houseOwnerPhone = DecodeUtils.decodeRequestString(request
				.getParameter("houseOwnerPhone"));// 房产权所有人电话
		String distributeCard = DecodeUtils.decodeRequestString(request
				.getParameter("distributeCard"));// 是否发卡网点
		String inPersonIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("inPersonIdCard"));// 开户人身份证号
		String houseOwnerPhoneOld = DecodeUtils.decodeRequestString(request
				.getParameter("houseOwnerPhoneOld"));// 房产权所有人电话
		String distributeCardOld = DecodeUtils.decodeRequestString(request
				.getParameter("distributeCardOld"));// 是否发卡网点
		String inPersonIdCardOld = DecodeUtils.decodeRequestString(request
				.getParameter("inPersonIdCardOld"));// 开户人身份证号
		String inBankAccount = DecodeUtils.decodeRequestString(request
				.getParameter("inBankAccount"));// 营收资金银行账号
		String inBankAccountNumber = DecodeUtils.decodeRequestString(request
				.getParameter("inBankAccountNumber"));// 开户号
		String inBankName = DecodeUtils.decodeRequestString(request
				.getParameter("inBankName"));// 开户银行名
		String inBankAccountOld = DecodeUtils.decodeRequestString(request
				.getParameter("inBankAccountOld"));// 营收资金银行账号
		String inBankAccountNumberOld = DecodeUtils.decodeRequestString(request
				.getParameter("inBankAccountNumberOld"));// 开户号
		String inBankNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("inBankNameOld"));// 开户银行名

		// 营业员表
		String userKind = DecodeUtils.decodeRequestString(request
				.getParameter("userKind"));// 员工类别
		String userState = DecodeUtils.decodeRequestString(request
				.getParameter("userState"));// 员工状态
		String userIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("userIdCard"));// 身份证号
		String userKindOld = DecodeUtils.decodeRequestString(request
				.getParameter("userKindOld"));// 员工类别
		String userStateOld = DecodeUtils.decodeRequestString(request
				.getParameter("userStateOld"));// 员工状态
		String userIdCardOld = DecodeUtils.decodeRequestString(request
				.getParameter("userIdCardOld"));// 身份证号
		String userAuthority = DecodeUtils.decodeRequestString(request
				.getParameter("userAuthority"));// 工号权限
		String userEmail = DecodeUtils.decodeRequestString(request
				.getParameter("userEmail"));// 邮箱
		String userRole = DecodeUtils.decodeRequestString(request
				.getParameter("userRole"));// 计费角色
		String userAuthorityOld = DecodeUtils.decodeRequestString(request
				.getParameter("userAuthorityOld"));// 工号权限
		String userEmailOld = DecodeUtils.decodeRequestString(request
				.getParameter("userEmailOld"));// 邮箱
		String userRoleOld = DecodeUtils.decodeRequestString(request
				.getParameter("userRoleOld"));// 计费角色
		String userCountId = DecodeUtils.decodeRequestString(request
				.getParameter("userCountId"));// 计费工号
		String userBirthday = DecodeUtils.decodeRequestString(request
				.getParameter("userBirthday"));// 生日
		String userNativeplace = DecodeUtils.decodeRequestString(request
				.getParameter("userNativeplace"));// 籍贯
		String userPhone = DecodeUtils.decodeRequestString(request
				.getParameter("userPhone"));// 联系电话
		String userCountIdOld = DecodeUtils.decodeRequestString(request
				.getParameter("userCountIdOld"));// 计费工号
		String userBirthdayOld = DecodeUtils.decodeRequestString(request
				.getParameter("userBirthdayOld"));// 生日
		String userNativeplaceOld = DecodeUtils.decodeRequestString(request
				.getParameter("userNativeplaceOld"));// 籍贯
		String userPhoneOld = DecodeUtils.decodeRequestString(request
				.getParameter("userPhoneOld"));// 联系电话

		String userEducation = DecodeUtils.decodeRequestString(request
				.getParameter("userEducation"));// 文化程度
		String userCertification = DecodeUtils.decodeRequestString(request
				.getParameter("userCertification"));// 技能证书
		String userInDate = DecodeUtils.decodeRequestString(request
				.getParameter("userInDate"));// 入职时间
		String userEducationOld = DecodeUtils.decodeRequestString(request
				.getParameter("userEducationOld"));// 文化程度
		String userCertificationOld = DecodeUtils.decodeRequestString(request
				.getParameter("userCertificationOld"));// 技能证书
		String userInDateOld = DecodeUtils.decodeRequestString(request
				.getParameter("userInDateOld"));// 入职时间
		String userOutDate = DecodeUtils.decodeRequestString(request
				.getParameter("userOutDate"));// 离职时间
		String userAddress = DecodeUtils.decodeRequestString(request
				.getParameter("userAddress"));// 住址
		String userPosition = DecodeUtils.decodeRequestString(request
				.getParameter("userPosition"));// 岗位
		String userOutDateOld = DecodeUtils.decodeRequestString(request
				.getParameter("userOutDateOld"));// 离职时间
		String userAddressOld = DecodeUtils.decodeRequestString(request
				.getParameter("userAddressOld"));// 住址
		String userPositionOld = DecodeUtils.decodeRequestString(request
				.getParameter("userPositionOld"));// 岗位
		String userWay = DecodeUtils.decodeRequestString(request
				.getParameter("userWay"));// 用工方式
		String userRegionName = DecodeUtils.decodeRequestString(request
				.getParameter("userRegionName"));// 地区名称
		String userWayOld = DecodeUtils.decodeRequestString(request
				.getParameter("userWayOld"));// 用工方式
		String userRegionNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("userRegionNameOld"));// 地区名称

		String agentPointType = DecodeUtils.decodeRequestString(request
				.getParameter("agentPointType"));// 代理商类型
		String sellPointType = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointType"));// 销售点类型
		String manageWay = DecodeUtils.decodeRequestString(request
				.getParameter("manageWay"));// 自营厅经营方式
		String agentPointTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("agentPointTypeOld"));// 代理商类型
		String sellPointTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointTypeOld"));// 销售点类型
		String manageWayOld = DecodeUtils.decodeRequestString(request
				.getParameter("manageWayOld"));// 自营厅经营方式
		String openSellYetai = DecodeUtils.decodeRequestString(request
				.getParameter("openSellYetai"));// 开放销售点业态
		String liansuoCatorySign = DecodeUtils.decodeRequestString(request
				.getParameter("liansuoCatorySign"));// 连锁分类标识
		String openSellYetaiOld = DecodeUtils.decodeRequestString(request
				.getParameter("openSellYetaiOld"));// 开放销售点业态
		String liansuoCatorySignOld = DecodeUtils.decodeRequestString(request
				.getParameter("liansuoCatorySignOld"));// 连锁分类标识

		String topZiyou = DecodeUtils.decodeRequestString(request
				.getParameter("topZiyou"));// top自有厅标识
		String majorMendianType = DecodeUtils.decodeRequestString(request
				.getParameter("majorMendianType"));// 专营门店类型
		String grantMendianClass = DecodeUtils.decodeRequestString(request
				.getParameter("grantMendianClass"));// 授权门店级别
		String topZiyouOld = DecodeUtils.decodeRequestString(request
				.getParameter("topZiyouOld"));// top自有厅标识
		String majorMendianTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("majorMendianTypeOld"));// 专营门店类型
		String grantMendianClassOld = DecodeUtils.decodeRequestString(request
				.getParameter("grantMendianClassOld"));// 授权门店级别
		String bianlidianTask = DecodeUtils.decodeRequestString(request
				.getParameter("bianlidianTask"));// 便利店业务
		String sellPointTask = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointTask"));// 销售点业务范围
		String bianlidianTaskOld = DecodeUtils.decodeRequestString(request
				.getParameter("bianlidianTaskOld"));// 便利店业务
		String sellPointTaskOld = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointTaskOld"));// 销售点业务范围

		String sellPointSellType = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointSellType"));// 销售点卖场类型
		String kechangEntity = DecodeUtils.decodeRequestString(request
				.getParameter("kechangEntity"));// 商客实体店细分
		String factoryCanalSign = DecodeUtils.decodeRequestString(request
				.getParameter("factoryCanalSign"));// 厂商渠道标识
		String isDragonSystem = DecodeUtils.decodeRequestString(request
				.getParameter("isDragonSystem"));// 龙系统是否接入
		String sellPointSellTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("sellPointSellTypeOld"));// 销售点卖场类型
		String kechangEntityOld = DecodeUtils.decodeRequestString(request
				.getParameter("kechangEntityOld"));// 商客实体店细分
		String factoryCanalSignOld = DecodeUtils.decodeRequestString(request
				.getParameter("factoryCanalSignOld"));// 厂商渠道标识
		String isDragonSystemOld = DecodeUtils.decodeRequestString(request
				.getParameter("isDragonSystemOld"));// 龙系统是否接入

		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));// 备注19
		String remarkOld = DecodeUtils.decodeRequestString(request
				.getParameter("remarkOld"));// 备注19

		try {
			session = DataStormSession.getInstance();
			// 根据工单号查询渠道编码
			sql = "select * from qdzc.canal_infomation WHERE in_id='" + inId
					+ "'";
			Map resultMap1 = session.findSql(sql).get(0);
			String canalId = resultMap1.get("canalId").toString();
			String agentInId = resultMap1.get("agentInId").toString();
			sql = "select * from qdzc.agent_information where in_id="
					+ agentInId;
			Map resultMap2 = session.findSql(sql).get(0);
			String agentId = resultMap2.get("agentId").toString();
			sql = "select * from qdzc.canal_user where canal_in_id=" + inId;
			Map resultMap3 = session.findSql(sql).get(0);
			String userNumber = resultMap3.get("userNumber").toString();

			// 添加渠道信息
			sql = "update qdzc.canal_infomation_his set canal_parent_id='"
					+ canalParentId + "',canal_parent_name='" + canalParentName
					+ "',canal_manager='" + canalManager
					+ "',canal_manager_id='" + canalManagerId
					+ "',rent_money='" + rentMoney + "',allowance_policy='"
					+ allowancePolicy + "',house_owner_name='" + houseOwnerName
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
			sql = "update qdzc.agent_information_his set manage_model='"
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
			sql = "update qdzc.canal_user_his set user_kind='" + userKind
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

			// if (!canalManager.equals(canalManagerOld)) {
			// sql =
			// "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
			// + agentId
			// + "','"
			// + canalId
			// + "','"
			// + userNumber
			// + "','"
			// + operUser
			// + "','"
			// + deptName
			// + "',sysdate(),'所属渠道客户经理','"
			// + canalManagerOld
			// + "','"
			// + canalManager + "')";
			// logger.info("修改所属渠道客户经理：" + sql);
			// session.add(sql);
			// sql = "update qdzc.canal_infomation_his set canal_manager='"
			// + canalManager + "' where in_id='" + inId + "' ";
			// session.update(sql);
			// }
			if (!canalParentName.equals(canalParentNameOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'归属上级渠道名称','"
						+ canalParentNameOld
						+ "','" + canalParentName + "')";
				logger.info("修改归属上级渠道名称：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set canal_parent_name='"
						+ canalParentName + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!canalParentId.equals(canalParentIdOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'归属上级渠道编码','"
						+ canalParentIdOld
						+ "','"
						+ canalParentId + "')";
				logger.info("修改归属上级渠道编码：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set canal_parent_id='"
						+ canalParentId + "' where in_id='" + inId + "' ";
				session.update(sql);
			}

			if (!rentMoney.equals(rentMoneyOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'月租金额','"
						+ rentMoneyOld
						+ "','"
						+ rentMoney + "')";
				logger.info("修改月租金额：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set rent_money='"
						+ rentMoney + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!allowancePolicy.equals(allowancePolicyOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'合同期补贴政策','"
						+ allowancePolicyOld
						+ "','" + allowancePolicy + "')";
				logger.info("修改合同期补贴政策：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set allowance_policy='"
						+ allowancePolicy + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!houseOwnerName.equals(houseOwnerNameOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'房产权所有人姓名','"
						+ houseOwnerNameOld
						+ "','" + houseOwnerName + "')";
				logger.info("修改房产权所有人姓名：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set house_owner_name='"
						+ houseOwnerName + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!houseOwnerPhone.equals(houseOwnerPhoneOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'房产权所有人电话','"
						+ houseOwnerPhoneOld
						+ "','" + houseOwnerPhone + "')";
				logger.info("修改房产权所有人电话：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set house_owner_phone='"
						+ houseOwnerPhone + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!distributeCard.equals(distributeCardOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'是否发卡网点','"
						+ distributeCardOld
						+ "','"
						+ distributeCard + "')";
				logger.info("修改是否发卡网点：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set distribute_card='"
						+ distributeCard + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!inPersonIdCard.equals(inPersonIdCardOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'开户人身份证号','"
						+ inPersonIdCardOld
						+ "','"
						+ inPersonIdCard + "')";
				logger.info("修改开户人身份证号：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set in_person_id_card='"
						+ inPersonIdCard + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!inBankAccount.equals(inBankAccountOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'营收资金银行账号','"
						+ inBankAccountOld
						+ "','"
						+ inBankAccount + "')";
				logger.info("修改营收资金银行账号：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set in_bank_account='"
						+ inBankAccount + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!inBankAccountNumber.equals(inBankAccountNumberOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'开户号','"
						+ inBankAccountNumberOld
						+ "','" + inBankAccountNumber + "')";
				logger.info("修改开户号：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set in_bank_account_number='"
						+ inBankAccountNumber + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!inBankName.equals(inBankNameOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'开户银行名','"
						+ inBankNameOld
						+ "','"
						+ inBankName + "')";
				logger.info("修改开户银行名：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set in_bank_name='"
						+ inBankName + "' where in_id='" + inId + "' ";
				session.update(sql);
			}

			if (!manageModel.equals(manageModelOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'管理模式','"
						+ manageModelOld
						+ "','"
						+ manageModel + "')";
				logger.info("修改管理模式：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set manage_model='"
						+ manageModel + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!is11888Card.equals(is11888CardOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'是否发11888卡','"
						+ is11888CardOld
						+ "','"
						+ is11888Card + "')";
				logger.info("修改是否发11888卡：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set is_11888_card='"
						+ is11888Card + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}

			if (!parentId.equals(parentIdOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'上级合作伙伴编码','"
						+ parentIdOld
						+ "','"
						+ parentId + "')";
				logger.info("修改上级合作伙伴编码：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set parent_id='"
						+ parentId + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!parentName.equals(parentNameOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'上级合作伙伴名称','"
						+ parentNameOld
						+ "','"
						+ parentName + "')";
				logger.info("修改上级合作伙伴名称：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set parent_name='"
						+ parentName + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!companyAddress.equals(companyAddressOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'公司办公地址','"
						+ companyAddressOld
						+ "','"
						+ companyAddress + "')";
				logger.info("修改公司办公地址：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set company_address='"
						+ companyAddress + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!ondutyPersonAddress.equals(ondutyPersonAddressOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'负责人联系地址','"
						+ ondutyPersonAddressOld
						+ "','" + ondutyPersonAddress + "')";
				logger.info("修改负责人联系地址：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set onduty_person_address='"
						+ ondutyPersonAddress
						+ "' where in_id='"
						+ agentInId
						+ "' ";
				session.update(sql);
			}
			if (!ondutyPersonQqnum.equals(ondutyPersonQqnumOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'负责人QQ','"
						+ ondutyPersonQqnumOld
						+ "','" + ondutyPersonQqnum + "')";
				logger.info("修改负责人QQ：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set onduty_person_qqnum='"
						+ ondutyPersonQqnum
						+ "' where in_id='"
						+ agentInId
						+ "' ";
				session.update(sql);
			}
			if (!postCode.equals(postCodeOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'邮政编码','"
						+ postCodeOld
						+ "','"
						+ postCode + "')";
				logger.info("修改邮政编码：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set post_code='"
						+ postCode + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!agentFax.equals(agentFaxOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'传真','"
						+ agentFaxOld
						+ "','"
						+ agentFax
						+ "')";
				logger.info("修改传真：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set agent_fax='"
						+ agentFax + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!agentEmail.equals(agentEmailOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'电子邮箱','"
						+ agentEmailOld
						+ "','"
						+ agentEmail + "')";
				logger.info("修改电子邮箱：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set agent_email='"
						+ agentEmail + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!payWay.equals(payWayOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'付款方式','"
						+ payWayOld
						+ "','"
						+ payWay
						+ "')";
				logger.info("修改付款方式：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set pay_way='"
						+ payWay + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!bankAccountName.equals(bankAccountNameOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'银行账户名称','"
						+ bankAccountNameOld
						+ "','"
						+ bankAccountName + "')";
				logger.info("修改银行账户名称：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set bank_account_name='"
						+ bankAccountName
						+ "' where in_id='"
						+ agentInId
						+ "' ";
				session.update(sql);
			}
			if (!weituoStartBank.equals(weituoStartBankOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'委托收款方开户银行','"
						+ weituoStartBankOld
						+ "','" + weituoStartBank + "')";
				logger.info("修改委托收款方开户银行：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set weituo_start_bank='"
						+ weituoStartBank
						+ "' where in_id='"
						+ agentInId
						+ "' ";
				session.update(sql);
			}
			if (!weituoBankAccount.equals(weituoBankAccountOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'委托收款方帐号','"
						+ weituoBankAccountOld
						+ "','" + weituoBankAccount + "')";
				logger.info("修改委托收款方帐号：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set weituo_bank_account='"
						+ weituoBankAccount
						+ "' where in_id='"
						+ agentInId
						+ "' ";
				session.update(sql);
			}
			if (!weituoBankName.equals(weituoBankNameOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'委托收款方名称','"
						+ weituoBankNameOld
						+ "','"
						+ weituoBankName + "')";
				logger.info("修改委托收款方名称：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set weituo_bank_name='"
						+ weituoBankName + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!payObject.equals(payObjectOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'支付对象','"
						+ payObjectOld
						+ "','"
						+ payObject + "')";
				logger.info("修改支付对象：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set pay_object='"
						+ payObject + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!registerMoney.equals(registerMoneyOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'注册资本','"
						+ registerMoneyOld
						+ "','"
						+ registerMoney + "')";
				logger.info("修改注册资本：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set register_money='"
						+ registerMoney + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!registerDate.equals(registerDateOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'工商登记时间','"
						+ registerDateOld
						+ "','"
						+ registerDate + "')";
				logger.info("修改工商登记时间：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set register_date='"
						+ registerDate + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!businessLicenseId.equals(businessLicenseIdOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'法人营业执照编码','"
						+ businessLicenseIdOld
						+ "','" + businessLicenseId + "')";
				logger.info("修改法人营业执照编码：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set business_license_id='"
						+ businessLicenseId
						+ "' where in_id='"
						+ agentInId
						+ "' ";
				session.update(sql);
			}
			if (!taxRegisteId.equals(taxRegisteIdOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'税务登记证编号','"
						+ taxRegisteIdOld
						+ "','"
						+ taxRegisteId + "')";
				logger.info("修改税务登记证编号：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set tax_registe_id='"
						+ taxRegisteId + "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			if (!bankPermisionId.equals(bankPermisionIdOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'银行开户许可证编号','"
						+ bankPermisionIdOld
						+ "','" + bankPermisionId + "')";
				logger.info("修改银行开户许可证编号：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set bank_permision_id='"
						+ bankPermisionId
						+ "' where in_id='"
						+ agentInId
						+ "' ";
				session.update(sql);
			}
			if (!organizationCodeId.equals(organizationCodeIdOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'组织机构代码证编号','"
						+ organizationCodeIdOld
						+ "','" + organizationCodeId + "')";
				logger.info("修改组织机构代码证编号：" + sql);
				session.add(sql);
				sql = "update qdzc.agent_information_his set organization_code_id='"
						+ organizationCodeId
						+ "' where in_id='"
						+ agentInId
						+ "' ";
				session.update(sql);
			}
			if (!agentPointType.equals(agentPointTypeOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'代理点类型','"
						+ agentPointTypeOld
						+ "','"
						+ agentPointType + "')";
				logger.info("修改代理点类型：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set agent_point_type='"
						+ agentPointType + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!sellPointType.equals(sellPointTypeOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'销售点类型','"
						+ sellPointTypeOld
						+ "','"
						+ sellPointType + "')";
				logger.info("修改销售点类型：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set sell_point_type='"
						+ sellPointType + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!manageWay.equals(manageWayOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'自营厅经营方式','"
						+ manageWayOld
						+ "','"
						+ manageWay + "')";
				logger.info("修改自营厅经营方式：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set manage_way='"
						+ manageWay + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!openSellYetai.equals(openSellYetaiOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'开放销售点业态','"
						+ openSellYetaiOld
						+ "','"
						+ openSellYetai + "')";
				logger.info("修改开放销售点业态：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set open_sell_yetai='"
						+ openSellYetai + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!liansuoCatorySign.equals(liansuoCatorySignOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'连锁分类标识','"
						+ liansuoCatorySignOld
						+ "','" + liansuoCatorySign + "')";
				logger.info("修改连锁分类标识：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set liansuo_catory_sign='"
						+ liansuoCatorySign + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!topZiyou.equals(topZiyouOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'top自有厅标识','"
						+ topZiyouOld
						+ "','"
						+ topZiyou + "')";
				logger.info("修改top自有厅标识：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set top_ziyou='"
						+ topZiyou + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!majorMendianType.equals(majorMendianTypeOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'专营门店类型','"
						+ majorMendianTypeOld
						+ "','" + majorMendianType + "')";
				logger.info("修改专营门店类型：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set major_mendian_type='"
						+ majorMendianType + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!grantMendianClass.equals(grantMendianClassOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'授权门店级别','"
						+ grantMendianClassOld
						+ "','" + grantMendianClass + "')";
				logger.info("修改授权门店级别：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set grant_mendian_class='"
						+ grantMendianClass + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!bianlidianTask.equals(bianlidianTaskOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'便利店业务','"
						+ bianlidianTaskOld
						+ "','"
						+ bianlidianTask + "')";
				logger.info("修改便利店业务：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set bianlidian_task='"
						+ bianlidianTask + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!sellPointTask.equals(sellPointTaskOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'销售点业务范围','"
						+ sellPointTaskOld
						+ "','"
						+ sellPointTask + "')";
				logger.info("修改销售点业务范围：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set sell_point_task='"
						+ sellPointTask + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!sellPointSellType.equals(sellPointSellTypeOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'销售点卖场类型','"
						+ sellPointSellTypeOld
						+ "','" + sellPointSellType + "')";
				logger.info("修改销售点卖场类型：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set sell_point_sell_type='"
						+ sellPointSellType + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!kechangEntity.equals(kechangEntityOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'商客实体店细分','"
						+ kechangEntityOld
						+ "','"
						+ kechangEntity + "')";
				logger.info("修改商客实体店细分：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set kechang_entity='"
						+ kechangEntity + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!factoryCanalSign.equals(factoryCanalSignOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'厂商渠道标识','"
						+ factoryCanalSignOld
						+ "','" + factoryCanalSign + "')";
				logger.info("修改厂商渠道标识：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set factory_canal_sign='"
						+ factoryCanalSign + "' where in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!isDragonSystem.equals(isDragonSystemOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'是否接入龙系统','"
						+ isDragonSystemOld
						+ "','"
						+ isDragonSystem + "')";
				logger.info("修改代理点类型：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_infomation_his set is_dragon_system='"
						+ isDragonSystem + "' where in_id='" + inId + "' ";
				session.update(sql);
			}

			if (!userKind.equals(userKindOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'营业员类别','"
						+ userKindOld
						+ "','"
						+ userKind + "')";
				logger.info("修改营业员类别：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_kind='" + userKind
						+ "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userState.equals(userStateOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'用户状态','"
						+ userStateOld
						+ "','"
						+ userState + "')";
				logger.info("修改用户状态：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_state='" + userState
						+ "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userIdCard.equals(userIdCardOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'身份证号','"
						+ userIdCardOld
						+ "','"
						+ userIdCard + "')";
				logger.info("修改身份证号：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_id_card='"
						+ userIdCard + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}

			if (!userAuthority.equals(userAuthorityOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'用户标识','"
						+ userAuthorityOld
						+ "','"
						+ userAuthority + "')";
				logger.info("修改用户标识：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_authority='"
						+ userAuthority + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userEmail.equals(userEmailOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'邮箱','"
						+ userEmailOld
						+ "','"
						+ userEmail + "')";
				logger.info("修改邮箱：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_email='" + userEmail
						+ "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userRole.equals(userRoleOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'用户角色','"
						+ userRoleOld
						+ "','"
						+ userRole + "')";
				logger.info("修改用户角色：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_role='" + userRole
						+ "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userCountId.equals(userCountIdOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'计费工号','"
						+ userCountIdOld
						+ "','"
						+ userCountId + "')";
				logger.info("修改计费工号：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_count_id='"
						+ userCountId + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userPhone.equals(userPhoneOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'联系电话','"
						+ userPhoneOld
						+ "','"
						+ userPhone + "')";
				logger.info("修改联系电话：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_Phone='" + userPhone
						+ "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userBirthday.equals(userBirthdayOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'生日','"
						+ userBirthdayOld
						+ "','"
						+ userBirthday + "')";
				logger.info("修改生日：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_birthday='"
						+ userBirthday + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userNativeplace.equals(userNativeplaceOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'籍贯','"
						+ userNativeplaceOld
						+ "','"
						+ userNativeplace + "')";
				logger.info("修改籍贯：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_native_place='"
						+ userNativeplace + "' where canal_in_id='" + inId
						+ "' ";
				session.update(sql);
			}

			if (!userOutDate.equals(userOutDateOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'离职时间','"
						+ userOutDateOld
						+ "','"
						+ userOutDate + "')";
				logger.info("修改离职时间：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_out_date='"
						+ userOutDate + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userAddress.equals(userAddressOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'住址','"
						+ userAddressOld
						+ "','"
						+ userAddress + "')";
				logger.info("修改住址：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_address='"
						+ userAddress + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userPosition.equals(userPositionOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'岗位','"
						+ userPositionOld
						+ "','"
						+ userPosition + "')";
				logger.info("修改岗位：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_position='"
						+ userPosition + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}

			if (!userEducation.equals(userEducationOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'文化程度','"
						+ userEducationOld
						+ "','"
						+ userEducation + "')";
				logger.info("修改 文化程度：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_education='"
						+ userEducation + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userCertification.equals(userCertificationOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'技能证书','"
						+ userCertificationOld
						+ "','"
						+ userCertification + "')";
				logger.info("修改技能证书：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_certification='"
						+ userCertification + "' where canal_in_id='" + inId
						+ "' ";
				session.update(sql);
			}
			if (!userInDate.equals(userInDateOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'入职时间','"
						+ userInDateOld
						+ "','"
						+ userInDate + "')";
				logger.info("修改入职时间：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_in_date='"
						+ userInDate + "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}

			if (!userWay.equals(userWayOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'用工方式','"
						+ userWayOld
						+ "','"
						+ userWay
						+ "')";
				logger.info("修改用工方式：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_way='" + userWay
						+ "' where canal_in_id='" + inId + "' ";
				session.update(sql);
			}
			if (!userRegionName.equals(userRegionNameOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'地区名称','"
						+ userRegionNameOld
						+ "','"
						+ userRegionName + "')";
				logger.info("修改地区名称：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set user_region_name='"
						+ userRegionName + "' where canal_in_id='" + inId
						+ "' ";
				session.update(sql);
			}
			if (!remark.equals(remarkOld)) {
				sql = "insert into modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
						+ agentId
						+ "','"
						+ canalId
						+ "','"
						+ userNumber
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "',sysdate(),'备注','"
						+ remarkOld
						+ "','"
						+ remark
						+ "')";
				logger.info("修改备注：" + sql);
				session.add(sql);
				sql = "update qdzc.canal_user_his set remark='" + remark
						+ "' where canal_in_id='" + inId + "' ";
				session.update(sql);
				sql = "update qdzc.canal_infomation_his set remark='" + remark
						+ "' where in_id='" + inId + "' ";
				session.update(sql);
				sql = "update qdzc.agent_information_his set remark='" + remark
						+ "' where in_id='" + agentInId + "' ";
				session.update(sql);
			}
			// 修改驳回为无
			sql = "select is_back from qdzc.canal_step_state_his where in_id="
					+ inId;
			logger.info("查出驳回的字段" + sql);
			Map resultMap11 = session.findSql(sql).get(0);
			String isBack = (String) resultMap11.get("isBack");
			if (isBack.equals("驳回")) {
				sql = "update qdzc.canal_step_state_his set is_back='无' where in_id="
						+ inId;
				logger.info("修改信息后把驳回改为无" + sql);
				session.update(sql);
			}

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