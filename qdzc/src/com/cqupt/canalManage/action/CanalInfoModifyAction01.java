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

public class CanalInfoModifyAction01 extends ActionSupport {
	private static final long serialVersionUID = -3503583143062479748L;
	Logger logger = Logger.getLogger(this.getClass());
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	String standbyPhoneId = "null";

	@Override
	// 必填字段管理员修改处理
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
		// 渠道表信息
		String canalForm = DecodeUtils.decodeRequestString(request
				.getParameter("canalForm"));// 渠道形态
		String canalProperty = DecodeUtils.decodeRequestString(request
				.getParameter("canalProperty"));// 渠道性质
		String canalType = DecodeUtils.decodeRequestString(request
				.getParameter("canalType"));// 渠道类型
		// String canalXingzhi = DecodeUtils.decodeRequestString(request
		// .getParameter("canalXingzhi"));// 渠道性质
		String nativeType = DecodeUtils.decodeRequestString(request
				.getParameter("nativeType"));// 本地类型

		// 代理商表信息
		String branchCompany = DecodeUtils.decodeRequestString(request
				.getParameter("branchCompany"));// 分公司
		String agentName = DecodeUtils.decodeRequestString(request
				.getParameter("agentName"));// 代理商名称
		String agentShortName = DecodeUtils.decodeRequestString(request
				.getParameter("agentShortName"));// 代理商简称
		String agentDept = DecodeUtils.decodeRequestString(request
				.getParameter("agentDept"));// 管理部门
		String agentLevel = DecodeUtils.decodeRequestString(request
				.getParameter("agentLevel"));// 代理商级别
		String companyType = DecodeUtils.decodeRequestString(request
				.getParameter("companyType"));// 公司类型
		String cooperationType = DecodeUtils.decodeRequestString(request
				.getParameter("cooperationType"));// 合作伙伴类型
		String legalPerson = DecodeUtils.decodeRequestString(request
				.getParameter("legalPerson"));// 法人代表
		String legalPhone = DecodeUtils.decodeRequestString(request
				.getParameter("legalPhone"));// 法人代表联系电话
		String legalCardType = DecodeUtils.decodeRequestString(request
				.getParameter("legalCardType"));// 法人证件类型
		String legalIdCard = DecodeUtils.decodeRequestString(request
				.getParameter("legalIdCard"));// 法人证件号码
		String ondutyPerson = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPerson"));// 负责人姓名
		String ondutyPersonPhone = DecodeUtils.decodeRequestString(request
				.getParameter("ondutyPersonPhone"));// 负责人联系电话

		String startBank = DecodeUtils.decodeRequestString(request
				.getParameter("startBank"));// 开户银行
		String bankAccountId = DecodeUtils.decodeRequestString(request
				.getParameter("bankAccountId"));// 开户银行账号

		// 渠道表信息

		String isCrm = "接入";// CRM是否接入
		String inCollectNumber = DecodeUtils.decodeRequestString(request
				.getParameter("inCollectNumber"));// 翼支付账号
		String isMoney = DecodeUtils.decodeRequestString(request
				.getParameter("isMoney"));// 翼支付账号
		String taskType = DecodeUtils.decodeRequestString(request
				.getParameter("taskType"));// 业务类型
		String privilege = DecodeUtils.decodeRequestString(request
				.getParameter("privilege"));// 工号权限
		String guaranteeAmount = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeAmount"));// 保证金金额
		String guaranteeUser = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeUser"));// 办理人
		String guaranteeTime = DecodeUtils.decodeRequestString(request
				.getParameter("guaranteeTime"));// 办理时间
		String guaranteeReceiptScanUrl = DecodeUtils
				.decodeRequestString(request
						.getParameter("guaranteeReceiptScanUrl"));// 上传快照路径
		String canalName = DecodeUtils.decodeRequestString(request
				.getParameter("canalName"));// 渠道名称
		String areaName = DecodeUtils.decodeRequestString(request
				.getParameter("areaName"));// 区域名称
		String canalAddress = DecodeUtils.decodeRequestString(request
				.getParameter("canalAddress"));// 渠道地址
		String regionCharacter = DecodeUtils.decodeRequestString(request
				.getParameter("regionCharacter"));// 区域特征
		String canalDept = DecodeUtils.decodeRequestString(request
				.getParameter("canalDept"));// 渠道归属部门
		String canalUserName = DecodeUtils.decodeRequestString(request
				.getParameter("canalUserName"));// 负责人（渠道客户经理）
		String canalUserPhone = DecodeUtils.decodeRequestString(request
				.getParameter("canalUserPhone"));// 联系电话
		String urbanIdetity = DecodeUtils.decodeRequestString(request
				.getParameter("urbanIdetity"));// 城乡标识
		String expireType = DecodeUtils.decodeRequestString(request
				.getParameter("expireType"));// 产权归属
		String businessArea = DecodeUtils.decodeRequestString(request
				.getParameter("businessArea"));// 营业面积
		String rentStartDate = DecodeUtils.decodeRequestString(request
				.getParameter("rentStartDate"));// 房租开始时间
		String rentEndDate = DecodeUtils.decodeRequestString(request
				.getParameter("rentEndDate"));// 房租结束时间
		String firstRentAllowance = DecodeUtils.decodeRequestString(request
				.getParameter("firstRentAllowance"));// 首期房租补贴(元/月)
		String firstDecorationAllowance = DecodeUtils
				.decodeRequestString(request
						.getParameter("firstDecorationAllowance"));// 首期装修补贴金额(元/月)
		String contractScanUrl = "";

		// 资金归结（缴营业款）
		String hm = DecodeUtils.decodeRequestString(request.getParameter("hm"));// 户名
		String khyh = DecodeUtils.decodeRequestString(request
				.getParameter("khyh"));// 开户银行
		String kh = DecodeUtils.decodeRequestString(request.getParameter("kh"));// 卡号
		String sfzh = DecodeUtils.decodeRequestString(request
				.getParameter("sfzh"));// 身份证号

		// 营业员信息表

		String userName = DecodeUtils.decodeRequestString(request
				.getParameter("userName"));// 营业员姓名
		String userPinyin = DecodeUtils.decodeRequestString(request
				.getParameter("userPinyin"));// 姓名拼音
		String userSex = DecodeUtils.decodeRequestString(request
				.getParameter("userSex"));// 性别
		// 2015年1月27号资金稽核编码信息
		String checkNumber = DecodeUtils.decodeRequestString(request
				.getParameter("checkNumber"));
		String checkName = DecodeUtils.decodeRequestString(request
				.getParameter("checkName"));
		String moneyLogin = DecodeUtils.decodeRequestString(request
				.getParameter("moneyLogin"));
		// 稽核人员配置信息
		String checkUser = DecodeUtils.decodeRequestString(request
				.getParameter("checkUser"));
		// 专线受理
		String broadbandAccount = DecodeUtils.decodeRequestString(request
				.getParameter("broadbandAccount"));
		String broadbandPassword = DecodeUtils.decodeRequestString(request
				.getParameter("broadbandPassword"));
		String wwwAccount = DecodeUtils.decodeRequestString(request
				.getParameter("wwwAccount"));
		String wwwPrivilegeInfo = DecodeUtils.decodeRequestString(request
				.getParameter("wwwPrivilegeInfo"));
		// crm工号和代理商工号2015年1月27号
		String crmNumber = DecodeUtils.decodeRequestString(request
				.getParameter("crmNumber"));// 专营门店类型
		String agentJobNumber = DecodeUtils.decodeRequestString(request
				.getParameter("agentJobNumber"));// 授权门店级别
		// 设备基础配置
		String terminalType = DecodeUtils.decodeRequestString(request
				.getParameter("terminalType"));
		String dx360Number = DecodeUtils.decodeRequestString(request
				.getParameter("dx360Number"));
		String terminalNumber = DecodeUtils.decodeRequestString(request
				.getParameter("terminalNumber"));
		String cpu = DecodeUtils.decodeRequestString(request
				.getParameter("cpu"));
		String kernal = DecodeUtils.decodeRequestString(request
				.getParameter("kernal"));
		String frequency = DecodeUtils.decodeRequestString(request
				.getParameter("frequency"));
		String memory = DecodeUtils.decodeRequestString(request
				.getParameter("memory"));
		String printNumber = DecodeUtils.decodeRequestString(request
				.getParameter("printNumber"));
		String disk = DecodeUtils.decodeRequestString(request
				.getParameter("disk"));
		String isNoPaper = DecodeUtils.decodeRequestString(request
				.getParameter("isNoPaper"));

		try {
			session = DataStormSession.getInstance();
			// 判断客户经理是否存在
			if (resultStr.equals("success")) {
				sql = "select user_id from qdzc.sys_user where user_name = '"
						+ canalUserName + "' and group_id=7 ";
				logger.info(sql);
				List list = session.findSql(sql);
				if (list.size() != 1) {
					resultStr = "对应客户经理不存在！";
				}
			}
			if (resultStr.equals("success")) {
				// 根据渠道的inId查出代理商的agent_in_id
				sql = "select agent_in_id,oper_user from qdzc.canal_infomation where in_id="
						+ inId;
				logger.info("查出agentInId" + sql);
				Map resultMap = session.findSql(sql).get(0);
				String agentInId = (String) resultMap.get("agentInId");
				String user = resultMap.get("operUser").toString();
				// 更新渠道基础信息
				sql = "update qdzc.canal_infomation set canal_name='"
						+ canalName + "',area_name='" + areaName
						+ "',canal_address='" + canalAddress
						+ "',region_character='" + regionCharacter
						+ "',canal_form='" + canalForm + "',canal_property='"
						+ canalProperty + "',canal_type='" + canalType
						+ "',agent_name='" + agentName + "',canal_user_name='"
						+ canalUserName + "',canal_user_phone='"
						+ canalUserPhone + "'," + "urban_idetity='"
						+ urbanIdetity + "',expire_type='" + expireType
						+ "',business_area='" + businessArea
						+ "',rent_start_date='" + rentStartDate
						+ "',rent_end_date='" + rentEndDate
						+ "',first_rent_allowance='" + firstRentAllowance
						+ "',first_decoration_allowance='"
						+ firstDecorationAllowance + "'," + "canal_dept='"
						+ canalDept + "',in_collect_number='" + inCollectNumber
						+ "',contract_scan_url='" + contractScanUrl
						+ "',guarantee_amount='" + guaranteeAmount
						+ "',guarantee_user='" + guaranteeUser
						+ "',guarantee_time='" + guaranteeTime
						+ "',guarantee_receipt_scan_url='"
						+ guaranteeReceiptScanUrl + "',hm='" + hm + "',khyh='"
						+ khyh + "',kh='" + kh + "',sfzh='" + sfzh
						+ "',is_crm='" + isCrm + "',is_money='" + isMoney
						+ "',task_type='" + taskType + "',privilege='"
						+ privilege + "',native_type='" + nativeType
						+ "' ,check_number='" + checkNumber + "',check_name='"
						+ checkName + "',money_login='" + moneyLogin
						+ "',check_user='" + checkUser + "',agent_job_number='"
						+ agentJobNumber + "',crm_number='" + crmNumber
						+ "',terminal_type='" + terminalType
						+ "',terminal_number='" + terminalNumber
						+ "',dx360_number='" + dx360Number + "',is_no_paper='"
						+ isNoPaper + "',print_number='" + printNumber
						+ "',disk='" + disk + "',broadband_account='"
						+ broadbandAccount + "',broadband_password='"
						+ broadbandPassword + "',www_account='" + wwwAccount
						+ "',www_privilege_info='" + wwwPrivilegeInfo
						+ "',memory='" + memory + "',frequency='" + frequency
						+ "',kernal='" + kernal + "',cpu='" + cpu
						+ "'where in_id='" + inId + "' ";
				logger.info("新增渠道信息：" + sql);
				session.update(sql);
				// 插入渠道状态表

				sql = "update qdzc.canal_step_state set canal_name='"
						+ canalName + "' where in_id=" + inId;
				logger.info("更新渠道状态表：" + sql);
				session.update(sql);

				sql = "update qdzc.agent_information set branch_company='"
						+ branchCompany + "', area_name='" + areaName
						+ "', agent_name='" + agentName
						+ "', agent_short_name='" + agentShortName
						+ "', agent_dept='" + agentDept + "', agent_level='"
						+ agentLevel + "', company_type='" + companyType
						+ "', cooperation_type='" + cooperationType
						+ "', legal_person='" + legalPerson
						+ "', legal_phone='" + legalPhone
						+ "', legal_card_type='" + legalCardType
						+ "', legal_id_card='" + legalIdCard
						+ "', onduty_person='" + ondutyPerson
						+ "', onduty_person_phone='" + ondutyPersonPhone
						+ "', start_bank='" + startBank + "', bank_account_id='"
						+ bankAccountId + "' where in_id=" + agentInId;
				logger.info("修改代理商信息：" + sql);
				session.update(sql);
				sql = "update qdzc.canal_user set user_name='" + userName
						+ "',user_pinyin='" + userPinyin + "',user_sex='"
						+ userSex + "' where canal_in_id=" + inId;
				logger.info("添加用户：" + sql);
				session.add(sql);
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