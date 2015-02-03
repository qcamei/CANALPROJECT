package com.cqupt.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CanalCloseDealDetailQueryAAction extends ActionSupport {
	// 处理步骤显示函数；
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = -246508754158513869L;
	/**
	 * 查询本代理商信息
	 */
	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String pageSize = request.getParameter("pagesize");
		String page = request.getParameter("page");

		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getSelectList(pageSize, page));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String getSelectList(String pageSize, String page) {
		int currentStep = 0;
		String sql = "";
		String resultStr = "";
		String canalId = "";
		String stepVal = "";
		DataStormSession session = null;

		try {
			session = DataStormSession.getInstance();

			canalId = java.net.URLDecoder.decode(
					request.getParameter("canalId"), "UTF-8");

			sql = "select current_step from qdzc.canal_step_state_close where canal_id='"
					+ canalId + "' ";

			System.out.println(canalId + "hahhahahhahhah");
			logger.info(sql);
			List list1 = session.findSql(sql);
			if (list1.size() > 0) {
				Map map = (Map) list1.get(0);
				currentStep = (Integer) map.get("currentStep");
			}

			sql = "select step_val from qdzc.step_info_close where step_no='"
					+ currentStep + "' ";

			logger.info(sql);
			List list2 = session.findSql(sql);
			if (list1.size() > 0) {
				Map map = (Map) list2.get(0);
				stepVal = (String) map.get("stepVal");
			}

		} catch (Exception e1) {

		}
		System.out.println(stepVal + "99999999999");
		sql = "select b.* , ROWNUM order_id FROM (select @rownum:=@rownum+1 as rownum, t.close_id , t.user_name, t.dept_name , t.dept_id,t.audit_state,t.canal_id,t.canal_name,"
				+ "t.current_step_val,t.current_step,t.next_step_val,date_format(t.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,t.in_id,t.refuse_reason,t.money,t.bmoney"
				+ " from (select @rownum:=0) r, qdzc.canal_infomation_close t WHERE t.canal_id='"
				+ canalId + "'";
		if (stepVal.equals("分公司审核")) {
			sql += " and ( t.current_step_val='分公司审核'";
		}
		if (stepVal.equals("分管部门审核")) {
			sql += " and (t.current_step_val='分管部门审核' or t.current_step_val='分公司审核'";
		}
		if (stepVal.equals("关闭CRM工号")) {
			sql += " and (t.current_step_val='关闭CRM工号' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核'";
		}
		if (stepVal.equals("代理商管理系统")) {
			sql += " and (t.current_step_val='代理商管理系统' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核' or t.current_step_val='关闭CRM工号'";
		}
		if (stepVal.equals("渠道归属")) {
			sql += " and (t.current_step_val='渠道归属' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核' or t.current_step_val='关闭CRM工号' or t.current_step_val='代理商管理系统'";
		}
		if (stepVal.equals("删除代理商网站工号")) {
			sql += " and (t.current_step_val='删除代理商网站工号' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核' or t.current_step_val='关闭CRM工号' or t.current_step_val='代理商管理系统' or t.current_step_val='渠道归属'";
		}
		if (stepVal.equals("资金稽核编码")) {
			sql += " and (t.current_step_val='资金稽核编码' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核' or t.current_step_val='关闭CRM工号' or t.current_step_val='代理商管理系统' or t.current_step_val='渠道归属' or t.current_step_val='删除代理商网站工号'";
		}
		if (stepVal.equals("专线受理环节")) {
			sql += " and (t.current_step_val='专线受理环节' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核' or t.current_step_val='关闭CRM工号' or t.current_step_val='代理商管理系统' or t.current_step_val='渠道归属' or t.current_step_val='删除代理商网站工号' or t.current_step_val='资金稽核编码'";
		}
		if (stepVal.equals("渠道基础配置")) {
			sql += " and (t.current_step_val='渠道基础配置' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核' or t.current_step_val='关闭CRM工号' or t.current_step_val='代理商管理系统' or t.current_step_val='渠道归属' or t.current_step_val='删除代理商网站工号' or t.current_step_val='资金稽核编码'  or t.current_step_val='专线受理环节'";
		}
		if (stepVal.equals("稽核系统配置人员")) {
			sql += " and (t.current_step_val='稽核系统配置人员' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核' or t.current_step_val='关闭CRM工号' or t.current_step_val='代理商管理系统' or t.current_step_val='渠道归属' or t.current_step_val='删除代理商网站工号' or t.current_step_val='资金稽核编码'  or t.current_step_val='专线受理环节'  or t.current_step_val='渠道基础配置'";
		}
		if (stepVal.equals("财务审核")) {
			sql += " and (t.current_step_val='财务审核' or t.current_step_val='分公司审核' or t.current_step_val='分管部门审核' or t.current_step_val='关闭CRM工号' or t.current_step_val='代理商管理系统' or t.current_step_val='渠道归属' or t.current_step_val='删除代理商网站工号' or t.current_step_val='资金稽核编码'  or t.current_step_val='专线受理环节'  or t.current_step_val='渠道基础配置' or t.current_step_val='稽核系统配置人员'";
		}

		sql += "))b";

		logger.info("关闭处理记录详情List：" + sql);

		try {

			Map resultMap = session.findSql(sql, Integer.parseInt(page),
					Integer.parseInt(pageSize));
			resultStr = JsonUtil.map2json(resultMap);
			session.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultStr;
	}
}
