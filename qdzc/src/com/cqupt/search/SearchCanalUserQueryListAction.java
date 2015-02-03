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

public class SearchCanalUserQueryListAction extends ActionSupport {
	//
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("ServiceQueryAction:)");
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

		String canalId = "";
		String canalName = "";
		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();
			String operUser = request.getSession().getAttribute("userName")
					.toString();
			String individualAuth = request.getSession()
					.getAttribute("individualAuth").toString();
			canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			canalName = DecodeUtils.decodeRequestString(request
					.getParameter("canalName"));
			session = DataStormSession.getInstance();
			sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(SELECT a.user_kind,a.user_name,"
					+ "a.user_state,a.user_agent,a.user_dept,a.user_number,a.user_sex,a.user_phone,date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time"
					+ " FROM qdzc.canal_user a"
					+ " where a.dept_id in ("
					+ dataAuthId
					+ ") and a.canal_id='"
					+ canalId
					+ "' and a.canal_name='" + canalName + "'";
			if (individualAuth.equals("selfOperator")) {
				// 渠道客户经理 只能看自己录入 的数据
				sql += " and a.canal_user_name = '" + operUser + "'";
			}
			sql += " ) t";
			logger.info("查询工单信息：" + sql);
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
