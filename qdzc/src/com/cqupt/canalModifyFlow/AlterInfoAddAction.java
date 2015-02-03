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
import com.cqupt.pub.util.Tools;
import com.cqupt.service.MailRemindUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AlterInfoAddAction extends ActionSupport {
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
		String canalId = DecodeUtils.decodeRequestString(request
				.getParameter("canalId"));// 渠道编码
		String agentId = DecodeUtils.decodeRequestString(request
				.getParameter("agentId"));// 代理商编码
		// 渠道表信息
		String canalForm = DecodeUtils.decodeRequestString(request
				.getParameter("canalForm"));// 渠道形态
		String canalProperty = DecodeUtils.decodeRequestString(request
				.getParameter("canalProperty"));// 渠道性质
		String canalType = DecodeUtils.decodeRequestString(request
				.getParameter("canalType"));// 渠道类型

		String canalFormOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalFormOld"));// 渠道形态
		String canalPropertyOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalPropertyOld"));// 渠道性质
		String canalTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalTypeOld"));// 渠道类型

		// 代理商表信息
		String branchCompany = DecodeUtils.decodeRequestString(request
				.getParameter("branchCompany"));// 分公司
		String agentName = DecodeUtils.decodeRequestString(request
				.getParameter("agentName"));// 代理商名称
		String agentShortName = DecodeUtils.decodeRequestString(request
				.getParameter("agentShortName"));// 代理商简称
		String branchCompanyOld = DecodeUtils.decodeRequestString(request
				.getParameter("branchCompanyOld"));// 分公司
		String agentNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("agentNameOld"));// 代理商名称
		String agentShortNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("agentShortNameOld"));// 代理商简称
		String agentDept = DecodeUtils.decodeRequestString(request
				.getParameter("agentDept"));// 管理部门
		String agentLevel = DecodeUtils.decodeRequestString(request
				.getParameter("agentLevel"));// 代理商级别
		String companyType = DecodeUtils.decodeRequestString(request
				.getParameter("companyType"));// 公司类型
		String agentDeptOld = DecodeUtils.decodeRequestString(request
				.getParameter("agentDeptOld"));// 管理部门
		String agentLevelOld = DecodeUtils.decodeRequestString(request
				.getParameter("agentLevelOld"));// 代理商级别
		String companyTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("companyTypeOld"));// 公司类型
		String cooperationType = DecodeUtils.decodeRequestString(request
				.getParameter("cooperationType"));// 合作伙伴类型
		String legalPerson = DecodeUtils.decodeRequestString(request
				.getParameter("legalPerson"));// 法人代表
		String legalPhone = DecodeUtils.decodeRequestString(request
				.getParameter("legalPhone"));// 法人代表联系电话
		String cooperationTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("cooperationTypeOld"));// 合作伙伴类型
		String legalPersonOld = DecodeUtils.decodeRequestString(request
				.getParameter("legalPersonOld"));// 法人代表
		String legalPhoneOld = DecodeUtils.decodeRequestString(request
				.getParameter("legalPhoneOld"));// 法人代表联系电话
		String legalCardType = DecodeUtils.decodeRequestString(request
				.getParameter("legalCardType"));// 法人证件类型
		String legalIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("legalIdCard"));// 法人证件号码
		String ondutyPerson = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPerson"));// 负责人姓名
		String ondutyPersonPhone = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonPhone"));// 负责人联系电话

		String legalCardTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("legalCardTypeOld"));// 法人证件类型
		String legalIdCardOld = DecodeUtils.decodeRequestString(request
				.getParameter("legalIdCardOld"));// 法人证件号码
		String ondutyPersonOld = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonOld"));// 负责人姓名
		String ondutyPersonPhoneOld = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonPhoneOld"));// 负责人联系电话

		String startBank = DecodeUtils.decodeRequestString(request
				.getParameter("startBank"));// 开户银行
		String bankAccountId = DecodeUtils.decodeRequestString(request
				.getParameter("bankAccountId"));// 开户银行账号
		String startBankOld = DecodeUtils.decodeRequestString(request
				.getParameter("startBankOld"));// 开户银行
		String bankAccountIdOld = DecodeUtils.decodeRequestString(request
				.getParameter("bankAccountIdOld"));// 开户银行账号

		// 渠道表信息

		String isCrm = "接入";// CRM是否接入
		String inCollectNumber = DecodeUtils.decodeRequestString(request
				.getParameter("inCollectNumber"));// 翼支付账号
		String isMoney = DecodeUtils.decodeRequestString(request
				.getParameter("isMoney"));// 是否交保证金
		String isAlterMoney = DecodeUtils.decodeRequestString(request
				.getParameter("isAlterMoney"));// 变更保证金金额
		String moneyAccount = DecodeUtils.decodeRequestString(request
				.getParameter("moneyAccount"));// 保证金数量
		String moneyRemark = DecodeUtils.decodeRequestString(request
				.getParameter("moneyRemark"));// 保证金备注
		String isOpenCrm = DecodeUtils.decodeRequestString(request
				.getParameter("isOpenCrm"));// 是否开CRM工号
		String isCrmOld = "接入";// CRM是否接入
		String inCollectNumberOld = DecodeUtils.decodeRequestString(request
				.getParameter("inCollectNumberOld"));// 翼支付账号
		String isMoneyOld = DecodeUtils.decodeRequestString(request
				.getParameter("isMoneyOld"));// 是否交保证金
		String taskType = DecodeUtils.decodeRequestString(request
				.getParameter("taskType"));// 业务类型
		String guaranteeAmount = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeAmount"));// 保证金金额
		String guaranteeUser = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeUser"));// 办理人
		String taskTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("taskTypeOld"));// 业务类型
		String guaranteeAmountOld = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeAmountOld"));// 保证金金额
		String guaranteeUserOld = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeUserOld"));// 办理人
		String guaranteeTime = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeTime"));// 办理时间
		String guaranteeReceiptScanUrl = DecodeUtils
				.decodeRequestString(request
						.getParameter("guaranteeReceiptScanUrl"));// 上传快照路径
		String guaranteeTimeOld = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeTimeOld"));// 办理时间
		String guaranteeReceiptScanUrlOld = DecodeUtils
				.decodeRequestString(request
						.getParameter("guaranteeReceiptScanUrlOld"));// 上传快照路径
		String canalName = DecodeUtils.decodeRequestString(request
				.getParameter("canalName"));// 渠道名称
		String areaName = DecodeUtils.decodeRequestString(request
				.getParameter("areaName"));// 区域名称
		String canalAddress = DecodeUtils.decodeRequestString(request
				.getParameter("canalAddress"));// 渠道地址
		String canalNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalNameOld"));// 渠道名称
		String areaNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("areaNameOld"));// 区域名称
		String canalAddressOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalAddressOld"));// 渠道地址
		String regionCharacter = DecodeUtils.decodeRequestString(request
				.getParameter("regionCharacter"));// 区域特征
		String canalDept = DecodeUtils.decodeRequestString(request
				.getParameter("canalDept"));// 渠道归属部门
		String canalUserName = DecodeUtils.decodeRequestString(request
				.getParameter("canalUserName"));// 负责人
		String regionCharacterOld = DecodeUtils.decodeRequestString(request
				.getParameter("regionCharacterOld"));// 区域特征
		String canalDeptOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalDeptOld"));// 渠道归属部门
		String canalUserNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalUserNameOld"));// 负责人
		String canalUserPhone = DecodeUtils.decodeRequestString(request
				.getParameter("canalUserPhone"));// 联系电话
		String urbanIdetity = DecodeUtils.decodeRequestString(request
				.getParameter("urbanIdetity"));// 城乡标识
		String expireType = DecodeUtils.decodeRequestString(request
				.getParameter("expireType"));// 产权归属
		String canalUserPhoneOld = DecodeUtils.decodeRequestString(request
				.getParameter("canalUserPhoneOld"));// 联系电话
		String urbanIdetityOld = DecodeUtils.decodeRequestString(request
				.getParameter("urbanIdetityOld"));// 城乡标识
		String expireTypeOld = DecodeUtils.decodeRequestString(request
				.getParameter("expireTypeOld"));// 产权归属
		String businessArea = DecodeUtils.decodeRequestString(request
				.getParameter("businessArea"));// 营业面积
		String rentStartDate = DecodeUtils.decodeRequestString(request
				.getParameter("rentStartDate"));// 房租开始时间
		String rentEndDate = DecodeUtils.decodeRequestString(request
				.getParameter("rentEndDate"));// 房租结束时间
		String businessAreaOld = DecodeUtils.decodeRequestString(request
				.getParameter("businessAreaOld"));// 营业面积
		String rentStartDateOld = DecodeUtils.decodeRequestString(request
				.getParameter("rentStartDateOld"));// 房租开始时间
		String rentEndDateOld = DecodeUtils.decodeRequestString(request
				.getParameter("rentEndDateOld"));// 房租结束时间
		String firstRentAllowance = DecodeUtils.decodeRequestString(request
				.getParameter("firstRentAllowance"));// 首期房租补贴(元/月)
		String firstDecorationAllowance = DecodeUtils
				.decodeRequestString(request
						.getParameter("firstDecorationAllowance"));// 首期装修补贴金额(元/月)
		String firstRentAllowanceOld = DecodeUtils.decodeRequestString(request
				.getParameter("firstRentAllowanceOld"));// 首期房租补贴(元/月)
		String firstDecorationAllowanceOld = DecodeUtils
				.decodeRequestString(request
						.getParameter("firstDecorationAllowanceOld"));// 首期装修补贴金额(元/月)
		String contractScanUrl = "";
		// 营业员信息表

		String userName = DecodeUtils.decodeRequestString(request
				.getParameter("userName"));// 营业员姓名
		String userPinyin = DecodeUtils.decodeRequestString(request
				.getParameter("userPinyin"));// 姓名拼音
		String userSex = DecodeUtils.decodeRequestString(request
				.getParameter("userSex"));// 性别
		String userNameOld = DecodeUtils.decodeRequestString(request
				.getParameter("userNameOld"));// 营业员姓名
		String userPinyinOld = DecodeUtils.decodeRequestString(request
				.getParameter("userPinyinOld"));// 姓名拼音
		String userSexOld = DecodeUtils.decodeRequestString(request
				.getParameter("userSexOld"));// 性别
		try {
			session = DataStormSession.getInstance();

			if (resultStr.equals("success")) {
				sql = "select user_id from qdzc.sys_user where user_name = '"
						+ canalUserName + "' and group_id=7 ";
				logger.info(sql);
				List list = session.findSql(sql);
				if (list.size() != 1) {
					resultStr = "对应客户经理不存在！";
				}
			}

			String inIdAgent = Tools.getOrderID();
			String inIdCanal = Tools.getOrderID();
			String inIdUser = Tools.getOrderID();

			if (resultStr.equals("success")) {

				// 根据工单号查询渠道编码
				sql = "select * from qdzc.canal_infomation WHERE in_id='"
						+ inId + "'";
				Map resultMap1 = session.findSql(sql).get(0);
				String user = resultMap1.get("operUser").toString();
				canalId = resultMap1.get("canalId").toString();
				String userNumber = "";

				sql = "insert into qdzc.canal_infomation_his(in_id,canal_id,agent_id,is_open_crm,is_alter_money,money_account,money_remark,oper_user,dept_id,dept_name,oper_time,agent_in_id) values('"
						+ inIdCanal
						+ "','"
						+ canalId
						+ "','"
						+ agentId
						+ "','"
						+ isOpenCrm
						+ "','"
						+ isAlterMoney
						+ "','"
						+ moneyAccount
						+ "','"
						+ moneyRemark
						+ "','"
						+ operUser
						+ "','"
						+ deptId
						+ "','"
						+ deptName
						+ "',sysdate(),'"
						+ inIdAgent + "') ";
				logger.info("新增渠道信息：" + sql);
				session.add(sql);
				// 插入渠道状态表

				sql = "insert into qdzc.canal_step_state_his (in_id,canal_id,canal_name,oper_user,dept_id,dept_name,oper_time,current_step) values ("
						+ "'"
						+ inIdCanal
						+ "','"
						+ canalId
						+ "','"
						+ canalName
						+ "', '"
						+ operUser
						+ "','"
						+ deptId
						+ "','"
						+ deptName
						+ "',sysdate(),'1')";
				logger.info("插入渠道状态表：" + sql);
				session.add(sql);

				sql = "insert into qdzc.agent_information_his(in_id,agent_id,oper_user, dept_id, dept_name, oper_time)values('"
						+ inIdAgent
						+ "','"
						+ agentId
						+ "','"
						+ operUser
						+ "','" + deptId + "','" + deptName + "',sysdate())";
				logger.info("添加代理商信息：" + sql);
				session.add(sql);

				sql = "insert into qdzc.canal_user_his(in_id,canal_id,canal_in_id,oper_user, dept_name, dept_id, oper_time)values('"
						+ inIdUser
						+ "','"
						+ canalId
						+ "','"
						+ inIdCanal
						+ "','"
						+ operUser
						+ "','"
						+ deptName
						+ "','"
						+ deptId
						+ "',sysdate())";
				logger.info("添加用户：" + sql);
				session.add(sql);

				if (!canalName.equals(canalNameOld)) {
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
							+ "',sysdate(),'渠道申请名称','"
							+ canalNameOld
							+ "','"
							+ canalName + "')";
					logger.info("修改渠道申请名称：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set canal_name='"
							+ canalName + "' where in_id='" + inIdCanal + "' ";
					session.update(sql);
				}
				if (!areaName.equals(areaNameOld)) {
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
							+ "',sysdate(),'区域名称','"
							+ areaNameOld
							+ "','"
							+ areaName + "')";
					logger.info("修改区域名称：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set area_name='"
							+ areaName + "' where in_id='" + inIdCanal + "' ";
					session.update(sql);
				}
				if (!canalForm.equals(canalFormOld)) {
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
							+ "',sysdate(),'城乡','"
							+ canalFormOld
							+ "','"
							+ canalForm + "')";
					logger.info("修改城乡：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set canal_form='"
							+ canalForm + "' where in_id='" + inIdCanal + "' ";
					session.update(sql);
				}
				if (!canalProperty.equals(canalPropertyOld)) {
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
							+ "',sysdate(),'管理属性','"
							+ canalPropertyOld
							+ "','"
							+ canalProperty + "')";
					logger.info("修改管理属性：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set canal_property='"
							+ canalProperty
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!canalType.equals(canalTypeOld)) {
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
							+ "',sysdate(),'分类名称','"
							+ canalTypeOld
							+ "','"
							+ canalType + "')";
					logger.info("修改分类名称：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set canal_type='"
							+ canalType + "' where in_id='" + inIdCanal + "' ";
					session.update(sql);
				}
				if (!regionCharacter.equals(regionCharacterOld)) {
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
							+ "',sysdate(),'区域特征','"
							+ regionCharacterOld
							+ "','" + regionCharacter + "')";
					logger.info("修改区域特征：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set region_character='"
							+ regionCharacter
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!canalAddress.equals(canalAddressOld)) {
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
							+ "',sysdate(),'渠道地址','"
							+ canalAddressOld
							+ "','"
							+ canalAddress + "')";
					logger.info("修改渠道地址：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set canal_address='"
							+ canalAddress
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!agentName.equals(agentNameOld)) {
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
							+ "',sysdate(),'渠道名称','"
							+ agentNameOld
							+ "','"
							+ agentName + "')";
					logger.info("修改渠道名称：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set agent_name='"
							+ agentName + "' where in_id='" + inIdAgent + "' ";
					session.update(sql);
				}
				if (!canalDept.equals(canalDeptOld)) {
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
							+ "',sysdate(),'渠道归属部门','"
							+ canalDeptOld
							+ "','"
							+ canalDept + "')";
					logger.info("修改渠道归属部门：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set canal_dept='"
							+ canalDept + "' where in_id='" + inIdCanal + "' ";
					session.update(sql);
				}
				if (!canalUserName.equals(canalUserNameOld)) {
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
							+ "',sysdate(),'负责人','"
							+ canalUserNameOld
							+ "','"
							+ canalUserName + "')";
					logger.info("修改渠道负责人：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set canal_user_name='"
							+ canalUserName
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!canalUserPhone.equals(canalUserPhoneOld)) {
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
							+ canalUserPhoneOld
							+ "','" + canalUserPhone + "')";
					logger.info("修改联系电话：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set canal_user_phone='"
							+ canalUserPhone
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}

				if (!urbanIdetity.equals(urbanIdetityOld)) {
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
							+ "',sysdate(),'城乡标识','"
							+ urbanIdetityOld
							+ "','"
							+ urbanIdetity + "')";
					logger.info("修改城乡标识：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set urban_idetity='"
							+ urbanIdetity
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!expireType.equals(expireTypeOld)) {
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
							+ "',sysdate(),'产权归属','"
							+ expireTypeOld
							+ "','"
							+ expireType + "')";
					logger.info("修改产权归属：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set expire_type='"
							+ expireType + "' where in_id='" + inIdCanal + "' ";
					session.update(sql);
				}
				if (!businessArea.equals(businessAreaOld)) {
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
							+ "',sysdate(),'营业面积','"
							+ businessAreaOld
							+ "','"
							+ businessArea + "')";
					logger.info("修改营业面积：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set business_area='"
							+ businessArea
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}

				if (!rentStartDate.equals(rentStartDateOld)) {
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
							+ "',sysdate(),'房租合同开始时间','"
							+ rentStartDateOld
							+ "','" + rentStartDate + "')";
					logger.info("修改房租合同开始时间：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set rent_start_date='"
							+ rentStartDate
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!rentEndDate.equals(rentEndDateOld)) {
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
							+ "',sysdate(),'房租合同结束时间','"
							+ rentEndDateOld
							+ "','" + rentEndDate + "')";
					logger.info("修改房租合同结束时间：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set rent_end_date='"
							+ rentEndDate
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}

				if (!firstRentAllowance.equals(firstRentAllowanceOld)) {
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
							+ "',sysdate(),'首期房租补贴(元/月)','"
							+ firstRentAllowanceOld
							+ "','"
							+ firstRentAllowance + "')";
					logger.info("修改首期房租补贴(元/月)：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set first_rent_allowance='"
							+ firstRentAllowance
							+ "' where in_id='"
							+ inIdCanal + "' ";
					session.update(sql);
				}
				if (!firstDecorationAllowance
						.equals(firstDecorationAllowanceOld)) {
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
							+ "',sysdate(),'首期装修补贴金额(元/月)','"
							+ firstDecorationAllowanceOld
							+ "','"
							+ firstDecorationAllowance + "')";
					logger.info("修改首期装修补贴金额(元/月)：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set first_decoration_allowance='"
							+ firstDecorationAllowance
							+ "' where in_id='"
							+ inIdCanal + "' ";
					session.update(sql);
				}

				if (!inCollectNumber.equals(inCollectNumberOld)) {
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
							+ "',sysdate(),'归集账号','"
							+ inCollectNumberOld
							+ "','" + inCollectNumber + "')";
					logger.info("修改归集账号：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set in_collect_number='"
							+ inCollectNumber
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!guaranteeAmount.equals(guaranteeAmountOld)) {
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
							+ "',sysdate(),'保证金金额','"
							+ guaranteeAmountOld
							+ "','" + guaranteeAmount + "')";
					logger.info("修改保证金金额：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set guarantee_amount='"
							+ guaranteeAmount
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!guaranteeUser.equals(guaranteeUserOld)) {
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
							+ "',sysdate(),'保证金办理人','"
							+ guaranteeUserOld
							+ "','" + guaranteeUser + "')";
					logger.info("修改保证金办理人：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set guarantee_user='"
							+ guaranteeUser
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!guaranteeTime.equals(guaranteeTimeOld)) {
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
							+ "',sysdate(),'办理时间','"
							+ guaranteeTimeOld
							+ "','"
							+ guaranteeTime + "')";
					logger.info("修改办理时间：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set guarantee_time='"
							+ guaranteeTime
							+ "' where in_id='"
							+ inIdCanal
							+ "' ";
					session.update(sql);
				}
				if (!branchCompany.equals(branchCompanyOld)) {
					sql = "insert into qdzc.modify_history (agent_id,canal_id,user_number,oper_user,oper_dept,oper_time,oper_column,old_value,new_value) values('"
							+ agentId
							+ "','"
							+ canalId
							+ "','"
							+ userNumber
							+ "','"
							+ operUser
							+ "','"
							+ deptName
							+ "',sysdate(),'分公司','"
							+ branchCompanyOld
							+ "','"
							+ branchCompany + "')";
					logger.info("修改分公司：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set branch_company='"
							+ branchCompany
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}

				if (!agentShortName.equals(agentShortNameOld)) {
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
							+ "',sysdate(),'代理商简称','"
							+ agentShortNameOld
							+ "','" + agentShortName + "')";
					logger.info("修改代理商简称：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set agent_short_name='"
							+ agentShortName
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}
				if (!agentDept.equals(agentDeptOld)) {
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
							+ "',sysdate(),'管理部门','"
							+ agentDeptOld
							+ "','"
							+ agentDept + "')";
					logger.info("修改管理部门：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set agent_dept='"
							+ agentDept + "' where in_id='" + inIdAgent + "' ";
					session.update(sql);
				}
				if (!agentLevel.equals(agentLevelOld)) {
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
							+ "',sysdate(),'代理商级别','"
							+ agentLevelOld
							+ "','"
							+ agentLevel + "')";
					logger.info("修改代理商级别：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set agent_level='"
							+ agentLevel + "' where in_id='" + inIdAgent + "' ";
					session.update(sql);
				}
				if (!companyType.equals(companyTypeOld)) {
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
							+ "',sysdate(),'公司类型','"
							+ companyTypeOld
							+ "','"
							+ companyType + "')";
					logger.info("修改公司类型：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set company_type='"
							+ companyType
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}
				if (!cooperationType.equals(cooperationTypeOld)) {
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
							+ "',sysdate(),'合作伙伴类型','"
							+ cooperationTypeOld
							+ "','" + cooperationType + "')";
					logger.info("修改合作伙伴类型：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set cooperation_type='"
							+ cooperationType
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}
				if (!legalPerson.equals(legalPersonOld)) {
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
							+ "',sysdate(),'法人代表','"
							+ legalPersonOld
							+ "','"
							+ legalPerson + "')";
					logger.info("修改法人代表：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set legal_person='"
							+ legalPerson
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}
				if (!legalPhone.equals(legalPhoneOld)) {
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
							+ "',sysdate(),'法人代表联系电话','"
							+ legalPhoneOld
							+ "','" + legalPhone + "')";
					logger.info("修改法人代表联系电话：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set legal_phone='"
							+ legalPhone + "' where in_id='" + inIdAgent + "' ";
					session.update(sql);
				}
				if (!legalCardType.equals(legalCardTypeOld)) {
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
							+ "',sysdate(),'法人证件类型','"
							+ legalCardTypeOld
							+ "','" + legalCardType + "')";
					logger.info("修改法人证件类型：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set legal_card_type='"
							+ legalCardType
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}
				if (!legalIdCard.equals(legalIdCardOld)) {
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
							+ "',sysdate(),'法人证件号码','"
							+ legalIdCardOld
							+ "','"
							+ legalIdCard + "')";
					logger.info("修改法人证件号码：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set legal_id_card='"
							+ legalIdCard
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}

				if (!ondutyPerson.equals(ondutyPersonOld)) {
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
							+ "',sysdate(),'负责人姓名','"
							+ ondutyPersonOld
							+ "','"
							+ ondutyPerson + "')";
					logger.info("修改负责人姓名：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set onduty_person='"
							+ ondutyPerson
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}
				if (!ondutyPersonPhone.equals(ondutyPersonPhoneOld)) {
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
							+ "',sysdate(),'负责人联系电话','"
							+ ondutyPersonPhoneOld
							+ "','" + ondutyPersonPhone + "')";
					logger.info("修改负责人联系电话：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set onduty_person_phone='"
							+ ondutyPersonPhone
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}
				if (!startBank.equals(startBankOld)) {
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
							+ "',sysdate(),'开户银行','"
							+ startBankOld
							+ "','"
							+ startBank + "')";
					logger.info("修改开户银行：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set start_bank='"
							+ startBank + "' where in_id='" + inIdAgent + "' ";
					session.update(sql);
				}
				if (!bankAccountId.equals(bankAccountIdOld)) {
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
							+ "',sysdate(),'银行帐号','"
							+ bankAccountIdOld
							+ "','"
							+ bankAccountId + "')";
					logger.info("修改银行帐号：" + sql);
					session.add(sql);
					sql = "update qdzc.agent_information_his set bank_account_id='"
							+ bankAccountId
							+ "' where in_id='"
							+ inIdAgent
							+ "' ";
					session.update(sql);
				}
				if (!userName.equals(userNameOld)) {
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
							+ "',sysdate(),'员工姓名','"
							+ userNameOld
							+ "','"
							+ userName + "')";
					logger.info("修改员工姓名：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_user_his set user_name='"
							+ userName + "' where in_id='" + inIdUser + "' ";
					session.update(sql);
				}
				if (!userPinyin.equals(userPinyinOld)) {
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
							+ "',sysdate(),'姓名拼音','"
							+ userPinyinOld
							+ "','"
							+ userPinyin + "')";
					logger.info("修改姓名拼音：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_user_his set user_pinyin='"
							+ userPinyin + "' where in_id='" + inIdUser + "' ";
					session.update(sql);
				}
				if (!userSex.equals(userSexOld)) {
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
							+ "',sysdate(),'性别','"
							+ userSexOld
							+ "','"
							+ userSex + "')";
					logger.info("修改性别：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_user_his set user_sex='" + userSex
							+ "' where in_id='" + inIdUser + "' ";
					session.update(sql);
				}
				if (!isMoney.equals(isMoneyOld)) {
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
							+ "',sysdate(),'是否交保证金','"
							+ isMoneyOld
							+ "','"
							+ isMoney + "')";
					logger.info("修改是否交保证金：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set is_money='"
							+ isMoney + "' where in_id='" + inIdCanal + "' ";
					session.update(sql);
				}
				if (!taskType.equals(taskTypeOld)) {
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
							+ "',sysdate(),'业务类型','"
							+ taskTypeOld
							+ "','"
							+ taskType + "')";
					logger.info("修改业务类型：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set task_type='"
							+ taskType + "' where in_id='" + inIdCanal + "' ";
					session.update(sql);
				}
				if (!guaranteeReceiptScanUrl.equals(guaranteeReceiptScanUrlOld)) {
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
							+ "',sysdate(),'扫描件','"
							+ guaranteeReceiptScanUrlOld
							+ "','"
							+ guaranteeReceiptScanUrl + "')";
					logger.info("修改扫描件：" + sql);
					session.add(sql);
					sql = "update qdzc.canal_infomation_his set guarantee_receipt_scan_url='"
							+ guaranteeReceiptScanUrl
							+ "' where in_id='"
							+ inIdCanal + "' ";
					session.update(sql);
				}

				sql = "select is_back from qdzc.canal_step_state_his where canal_id='"
						+ canalId + "' ";
				logger.info("查出驳回的字段" + sql);
				Map resultMap11 = session.findSql(sql).get(0);
				String isBack = (String) resultMap11.get("isBack");
				if (isBack.equals("驳回")) {
					sql = "update qdzc.canal_step_state_his set is_back='无' where canal_id='"
							+ canalId + "' ";
					logger.info("修改信息后把驳回改为无" + sql);
					session.update(sql);
				}
				// 申请环节
				MailRemindUtil.mailFGS(user, canalName);
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