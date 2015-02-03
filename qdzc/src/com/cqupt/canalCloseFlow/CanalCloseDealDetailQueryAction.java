package com.cqupt.canalCloseFlow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CanalCloseDealDetailQueryAction extends ActionSupport {
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
		String sql = "";
		String resultStr = "";
		String inId = "";
		String nextStepVal = "";

		try {
			inId = java.net.URLDecoder.decode(request.getParameter("inId"),
					"UTF-8");
			nextStepVal = java.net.URLDecoder.decode(
					request.getParameter("nextStepVal"), "UTF-8");

		} catch (Exception e1) {

		}

		sql = "select b.* , ROWNUM order_id FROM (select @rownum:=@rownum+1 as rownum, t.close_id , t.user_name, t.dept_name , t.dept_id,t.audit_state,t.canal_id,t.canal_name,"
				+ "t.current_step_val,t.current_step,t.next_step_val,date_format(t.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,t.in_id,t.refuse_reason,t.money,t.bmoney"
				+ " from (select @rownum:=0) r, qdzc.canal_infomation_close t WHERE in_id='"
				+ inId + "'";
		if (nextStepVal.equals("分公司审核")) {
			sql += " and t.next_step_val='分公司审核'";
		}
		if (nextStepVal.equals("分管部门审核")) {
			sql += " and t.next_step_val='分管部门审核' or t.next_step_val='分公司审核'";
		}
		if (nextStepVal.equals("关闭CRM工号")) {
			sql += " and t.next_step_val='关闭CRM工号' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核'";
		}
		if (nextStepVal.equals("代理商管理系统")) {
			sql += " and t.next_step_val='代理商管理系统' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核' or t.next_step_val='关闭CRM工号'";
		}
		if (nextStepVal.equals("渠道归属")) {
			sql += " and t.next_step_val='渠道归属' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核' or t.next_step_val='关闭CRM工号' or t.next_step_val='代理商管理系统'";
		}
		if (nextStepVal.equals("删除代理商网站工号")) {
			sql += " and t.next_step_val='删除代理商网站工号' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核' or t.next_step_val='关闭CRM工号' or t.next_step_val='代理商管理系统' or t.next_step_val='渠道归属'";
		}
		if (nextStepVal.equals("资金稽核系统配置")) {
			sql += " and t.next_step_val='资金稽核系统配置' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核' or t.next_step_val='关闭CRM工号' or t.next_step_val='代理商管理系统' or t.next_step_val='渠道归属' or t.next_step_val='删除代理商网站工号'";
		}
		if (nextStepVal.equals("专线受理环节")) {
			sql += " and t.next_step_val='专线受理环节' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核' or t.next_step_val='关闭CRM工号' or t.next_step_val='代理商管理系统' or t.next_step_val='渠道归属' or t.next_step_val='删除代理商网站工号' or t.next_step_val='资金稽核系统配置'";
		}
		if (nextStepVal.equals("渠道基础配置")) {
			sql += " and t.next_step_val='渠道基础配置' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核' or t.next_step_val='关闭CRM工号' or t.next_step_val='代理商管理系统' or t.next_step_val='渠道归属' or t.next_step_val='删除代理商网站工号' or t.next_step_val='资金稽核系统配置'  or t.next_step_val='专线受理环节'";
		}
		if (nextStepVal.equals("业务稽核系统配置")) {
			sql += " and t.next_step_val='业务稽核系统配置' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核' or t.next_step_val='关闭CRM工号' or t.next_step_val='代理商管理系统' or t.next_step_val='渠道归属' or t.next_step_val='删除代理商网站工号' or t.next_step_val='资金稽核系统配置'  or t.next_step_val='专线受理环节'  or t.next_step_val='渠道基础配置'";
		}
		if (nextStepVal.equals("财务审核")) {
			sql += " and t.next_step_val='财务审核' or t.next_step_val='分公司审核' or t.next_step_val='分管部门审核' or t.next_step_val='关闭CRM工号' or t.next_step_val='代理商管理系统' or t.next_step_val='渠道归属' or t.next_step_val='删除代理商网站工号' or t.next_step_val='资金稽核系统配置'  or t.next_step_val='专线受理环节'  or t.next_step_val='渠道基础配置' or t.next_step_val='业务稽核系统配置'";
		}
		sql += ")b";

		logger.info("关闭处理记录详情List：" + sql);

		try {
			DataStormSession session = DataStormSession.getInstance();
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
