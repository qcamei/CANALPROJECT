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
import com.cqupt.pub.util.Tools;
import com.cqupt.service.MailRemindUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CanalInfoAddAction extends ActionSupport {
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

		String state = DecodeUtils.decodeRequestString(request
				.getParameter("state"));// yes表示已有代理商

		// 渠道表信息
		String canalForm = DecodeUtils.decodeRequestString(request
				.getParameter("canalForm"));// 渠道形态
		String canalProperty = DecodeUtils.decodeRequestString(request
				.getParameter("canalProperty"));// 管理属性
		String canalType = DecodeUtils.decodeRequestString(request
				.getParameter("canalType"));// 渠道类型
//		String canalXingzhi = DecodeUtils.decodeRequestString(request
//				.getParameter("canalXingzhi"));// 渠道性质

		String inIdAgent = "";
		String agentId = "";
		String branchCompany = "";// 分公司
		String agentName = "";// 代理商名称
		String agentShortName = "";// 代理商简称
		String agentDept = "";// 管理部门
		String agentLevel = "";// 代理商级别
		String companyType = "";// 公司类型
		String cooperationType = "";// 合作伙伴类型
		String legalPerson = "";// 法人代表
		String legalPhone = "";// 法人代表联系电话
		String legalCardType = "";// 法人证件类型
		String legalIdCard = "";// 法人证件号码
		String ondutyPerson = "";// 负责人姓名
		String ondutyPersonPhone = "";// 负责人联系电话

		String startBank = "";// 开户银行
		String bankAccountId = "";// 开户银行账号
		// 代理商表信息
		if (state.equals("no")) {// 表示新增代理商
			inIdAgent = Tools.getOrderID();
			branchCompany = DecodeUtils.decodeRequestString(request
					.getParameter("branchCompany"));// 分公司
			agentName = DecodeUtils.decodeRequestString(request
					.getParameter("agentName"));// 代理商名称
			agentShortName = DecodeUtils.decodeRequestString(request
					.getParameter("agentShortName"));// 代理商简称
			agentDept = DecodeUtils.decodeRequestString(request
					.getParameter("agentDept"));// 管理部门
			agentLevel = DecodeUtils.decodeRequestString(request
					.getParameter("agentLevel"));// 代理商级别
			companyType = DecodeUtils.decodeRequestString(request
					.getParameter("companyType"));// 公司类型
			cooperationType = DecodeUtils.decodeRequestString(request
					.getParameter("cooperationType"));// 合作伙伴类型
			legalPerson = DecodeUtils.decodeRequestString(request
					.getParameter("legalPerson"));// 法人代表
			legalPhone = DecodeUtils.decodeRequestString(request
					.getParameter("legalPhone"));// 法人代表联系电话
			legalCardType = DecodeUtils.decodeRequestString(request
					.getParameter("legalCardType"));// 法人证件类型
			legalIdCard = DecodeUtils.decodeRequestString(request
					.getParameter("legalIdCard"));// 法人证件号码
			ondutyPerson = DecodeUtils.decodeRequestString(request
					.getParameter("ondutyPerson"));// 负责人姓名
			ondutyPersonPhone = DecodeUtils.decodeRequestString(request
					.getParameter("ondutyPersonPhone"));// 负责人联系电话

			startBank = DecodeUtils.decodeRequestString(request
					.getParameter("startBank"));// 开户银行
			bankAccountId = DecodeUtils.decodeRequestString(request
					.getParameter("bankAccountId"));// 开户银行账号
		} else {
			// 选择已有代理商时显示这两个字段
			agentName = DecodeUtils.decodeRequestString(request
					.getParameter("agentName1"));// 归属代理商名称
			agentId = DecodeUtils.decodeRequestString(request
					.getParameter("agentId1"));// 归属代理商编码
		}

		// 渠道表信息
		String inIdCanal = Tools.getOrderID();

		String isCrm = "接入";
		// 这里主要是因为前台当选择【开放渠道】时，把isCrm的下拉框禁用了，所以使用后台验证

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
		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));
		String isLine = DecodeUtils.decodeRequestString(request
				.getParameter("isLine"));
		// 营业员信息表
		String inIdUser = Tools.getOrderID();
		String userName = DecodeUtils.decodeRequestString(request
				.getParameter("userName"));// 营业员姓名
		String userPinyin = DecodeUtils.decodeRequestString(request
				.getParameter("userPinyin"));// 姓名拼音
		String userSex = DecodeUtils.decodeRequestString(request
				.getParameter("userSex"));// 性别
		// 资金归结（缴营业款）
		String hm = DecodeUtils.decodeRequestString(request.getParameter("hm"));// 户名
		String khyh = DecodeUtils.decodeRequestString(request
				.getParameter("khyh"));// 开户银行
		String kh = DecodeUtils.decodeRequestString(request.getParameter("kh"));// 卡号
		String sfzh = DecodeUtils.decodeRequestString(request
				.getParameter("sfzh"));// 身份证号

		try {
			session = DataStormSession.getInstance();
			if (resultStr.equals("success")) {
				sql = "select in_id from qdzc.canal_infomation where canal_name = '"
						+ canalName + "'";
				logger.info(sql);
				List list = session.findSql(sql);
				if (list.size() > 0) {
					resultStr = "渠道名称已存在！";
				}
			}

			if (resultStr.equals("success")) {
				sql = "select user_id from qdzc.sys_user where user_name = '"
						+ canalUserName + "' and group_id=7 ";
				logger.info(sql);
				List list = session.findSql(sql);
				if (list.size() != 1) {
					resultStr = "对应客户经理不存在！";
				}
			}

			if (state.equals("yes")) {// 选择已有代理商时，可以从代理商信息表中查出代理商的详细信息
				sql = "select * from qdzc.agent_information where agent_name = '"
						+ agentName + "' and agent_id='" + agentId + "' ";
				logger.info(sql);
				List list = session.findSql(sql);
				if (list.size() == 1) {
					Map map = (Map) list.get(0);
					inIdAgent = (String) map.get("inId");
					branchCompany = (String) map.get("branchCompany");// 分公司
					agentName = (String) map.get("agentName");// 代理商名称
					agentShortName = (String) map.get("agentShortName");// 代理商简称
					agentDept = (String) map.get("agentDept");// 管理部门
					agentLevel = (String) map.get("agentLevel");// 代理商级别
					companyType = (String) map.get("companyType");// 公司类型
					cooperationType = (String) map.get("cooperationType");// 合作伙伴类型
					legalPerson = (String) map.get("legalPerson");// 法人代表
					legalPhone = (String) map.get("legalPhone");// 法人代表联系电话
					legalCardType = (String) map.get("legalCardType");// 法人证件类型
					legalIdCard = (String) map.get("legalIdCard");// 法人证件号码
					ondutyPerson = (String) map.get("ondutyPerson");// 负责人姓名
					ondutyPersonPhone = (String) map.get("ondutyPersonPhone");// 负责人联系电话

					startBank = (String) map.get("startBank");// 开户银行
					bankAccountId = (String) map.get("bankAccountId");// 开户银行账号
				}
			}

			if (resultStr.equals("success")) {
				// 插入渠道基础信息
				sql = "insert into qdzc.canal_infomation (in_id,is_crm,is_money,task_type,privilege,canal_name,area_name,canal_address,region_character,"
						+ "canal_state,canal_form,canal_property,canal_type,agent_name,canal_user_name,canal_user_phone,"
						+ "urban_idetity,expire_type,business_area,rent_start_date,rent_end_date,first_rent_allowance,first_decoration_allowance,"
						+ "canal_dept,in_collect_number,contract_scan_url,"
						+ "guarantee_amount,guarantee_user,guarantee_time,guarantee_receipt_scan_url,hm,khyh,kh,sfzh,oper_user,dept_id,dept_name,oper_time,agent_in_id,remark,is_line)"
						+ " values ('"
						+ inIdCanal
						+ "','"
						+ isCrm
						+ "','"
						+ isMoney
						+ "','"
						+ taskType
						+ "','"
						+ privilege
						+ "','"
						+ canalName
						+ "','"
						+ areaName
						+ "','"
						+ canalAddress
						+ "','"
						+ regionCharacter
						+ "','正常', '"
						+ canalForm
						+ "', '"
						+ canalProperty
						+ "', '"
						+ canalType
						+ "','"
						+ agentName
						+ "', '"
						+ canalUserName
						+ "', '"
						+ canalUserPhone
						+ "','"
						+ urbanIdetity
						+ "', '"
						+ expireType
						+ "', '"
						+ businessArea
						+ "', '"
						+ rentStartDate
						+ "', '"
						+ rentEndDate
						+ "','"
						+ firstRentAllowance
						+ "','"
						+ firstDecorationAllowance
						+ "', "
						+ "'"
						+ canalDept
						+ "', '"
						+ inCollectNumber
						+ "', '"
						+ contractScanUrl
						+ "', "
						+ "'"
						+ guaranteeAmount
						+ "','"
						+ guaranteeUser
						+ "','"
						+ guaranteeTime
						+ "','"
						+ guaranteeReceiptScanUrl
						+ "','"
						+ hm
						+ "','"
						+ khyh
						+ "','"
						+ kh
						+ "','"
						+ sfzh
						+ "','"
						+ operUser
						+ "','"
						+ deptId
						+ "','"
						+ deptName
						+ "',sysdate(),'"
						+ inIdAgent + "','" + remark + "','" + isLine + "')";
				logger.info("新增渠道信息：" + sql);
				session.add(sql);
				// 插入渠道状态表

				sql = "insert into qdzc.canal_step_state (in_id,canal_id,canal_name,oper_user,dept_id,dept_name,oper_time,current_step) values ("
						+ "'"
						+ inIdCanal
						+ "','','"
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

				if (state.equals("no")) {
					sql = "insert into agent_information (in_id, branch_company, area_name, agent_id, agent_name, agent_short_name, agent_state, agent_dept, agent_level, company_type, cooperation_type, legal_person, legal_phone, legal_card_type, legal_id_card, onduty_person, onduty_person_phone, start_bank, bank_account_id, oper_user, dept_id, dept_name, oper_time)values('"
							+ inIdAgent
							+ "','"
							+ branchCompany
							+ "','"
							+ areaName
							+ "','"
							+ agentId
							+ "','"
							+ agentName
							+ "','"
							+ agentShortName
							+ "','正常','"
							+ agentDept
							+ "','"
							+ agentLevel
							+ "','"
							+ companyType
							+ "','"
							+ cooperationType
							+ "','"
							+ legalPerson
							+ "','"
							+ legalPhone
							+ "','"
							+ legalCardType
							+ "','"
							+ legalIdCard
							+ "','"
							+ ondutyPerson
							+ "','"
							+ ondutyPersonPhone
							+ "','"
							+ startBank
							+ "','"
							+ bankAccountId
							+ "','"
							+ operUser
							+ "','"
							+ deptId
							+ "','" + deptName + "',sysdate())";
					logger.info("添加用户：" + sql);
					session.add(sql);
				} else { // 把归属代理商和归属代理商编码插入到渠道信息表
					sql = "update qdzc.canal_infomation set agent_id='"
							+ agentId + "',agent_name='" + agentName
							+ "' where in_id='" + inIdCanal + "' ";
					logger.info("添加归属代理商信息：" + sql);
					session.update(sql);
				}

				sql = "insert into canal_user (in_id, canal_in_id,user_name,canal_name,user_agent,user_pinyin,user_sex, oper_user, dept_name, dept_id, oper_time) values('"
						+ inIdUser
						+ "','"
						+ inIdCanal
						+ "','"
						+ userName
						+ "','"
						+ canalName
						+ "','"
						+ agentName
						+ "','"
						+ userPinyin
						+ "','"
						+ userSex
						+ "','"
						+ operUser
						+ "','" + deptName + "','" + deptId + "',sysdate())";
				logger.info("添加用户：" + sql);
				session.add(sql);
				// 申请成功发邮件通知
				MailRemindUtil.mailFGS(canalUserName, canalName);
				session.closeSession();
				resultStr += ";" + inIdCanal;
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