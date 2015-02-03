package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserManagerQueryAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = -2465087541585226388L;
	/**
	 * 查询本部门用户信息
	 */
	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String queryDeptId = request.getParameter("queryDeptId");
		/*
		 * if(queryDeptId == null){ queryDeptId =
		 * (String)session.getAttribute("dataAuthId"); }
		 */
		String pageSize = request.getParameter("pagesize");
		String page = request.getParameter("page");
		String username = DecodeUtils.decodeRequestString(request
				.getParameter("username"));

		String telephone = DecodeUtils.decodeRequestString(request
				.getParameter("telephone"));
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getUserList(queryDeptId, pageSize, page, username,
					telephone));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String getUserList(String queryDeptId, String pageSize,
			String page, String username, String telephone) {
		String resultStr = "";
		String sql = "";
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			sql = "select b.* , ROWNUM order_id FROM (select @rownum:=@rownum+1 as rownum, t.user_id , t.user_name , t.dept_name , t.group_name , t.user_state, ifnull(t.user_email,' ') user_email, ifnull(t.phone_num,' ') user_mob_phone "
					+ " from (select @rownum:=0) r, qdzc.sys_user t WHERE 1=1";
			if (queryDeptId != null && queryDeptId != "") {
				sql += " and t.dept_id like '" + queryDeptId + "%'";
			}
			if (username != null && username != "") {
				sql += " and t.user_name like '" + username + "%'";
			}
			if (telephone != null && telephone != "") {
				sql += " and t.phone_num like '" + telephone + "%'";
			}
			sql += " )b  ";
			logger.info("查询用户:" + sql);
			Map resultMap = session.findSql(sql, Integer.parseInt(page),
					Integer.parseInt(pageSize));
			resultStr = JsonUtil.map2json(resultMap);

			session.closeSession();
		} catch (CquptException ce) {
			try {
				resultStr = "error";
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			ce.printStackTrace();
		}
		return resultStr;

	}

}
