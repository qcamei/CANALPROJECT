package com.cqupt.search;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAgentQueryListAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("AgentCanalQueryAction:)");
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

		String txtBeginDate = "";
		String txtEndDate = "";
		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			String operUser = request.getSession().getAttribute("userName")
					.toString();
			String individualAuth = request.getSession()
					.getAttribute("individualAuth").toString();
			String branchCompany = DecodeUtils.decodeRequestString(request
					.getParameter("branchCompany"));
			String agentName = DecodeUtils.decodeRequestString(request
					.getParameter("agentName"));
			String agentId = DecodeUtils.decodeRequestString(request
					.getParameter("agentId"));
			String legalPerson = DecodeUtils.decodeRequestString(request
					.getParameter("legalPerson"));
			txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");

			session = DataStormSession.getInstance();
			sql = "select b.* , ROWNUM order_id FROM (select @rownum:=@rownum+1 as rownum,t.area_name,t.oper_time,t.branch_company,t.agent_name,date_format(t.register_date,'%Y-%m-%d %H:%i:%s') register_date,t.agent_id,t.legal_person,t.agent_state,t.legal_phone,t.agent_level,t.agent_dept,t.company_type from (select @rownum:=0) r,qdzc.agent_information t where 1=1 ";

			if (individualAuth.equals("selfOperator")) {
				// 渠道客户经理 只能看自己录入 的数据
				sql += " and t.canal_user_name = '" + operUser + "'";
			}
			if (!txtBeginDate.equals("")) {
				sql += " and t.oper_time>'" + txtBeginDate + " 00:00:00' ";
			}
			if (!txtEndDate.equals("")) {
				sql += "  and t.oper_time<'" + txtEndDate + " 23:59:59' ";
			}
			if (branchCompany != null && !branchCompany.equals("")) {
				sql += " and t.branch_company like '%" + branchCompany + "%'";
			}

			if (agentName != null && !agentName.equals("")) {
				sql += " and  t.agent_name like '%" + agentName + "%'";
			}
			if (agentId != null && !agentId.equals("")) {
				sql += " and  t.agent_id like '%" + agentId + "%'";
			}
			if (legalPerson != null && !legalPerson.equals("")) {
				sql += " and  t.legal_person like '%" + legalPerson + "%'";
			}

			sql += ")b";

			logger.info("资产List：" + sql);
			resultStr = JsonUtil.map2json(session.findSql(sql,
					Integer.parseInt(page), Integer.parseInt(pageSize)));
			session.closeSession();
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
