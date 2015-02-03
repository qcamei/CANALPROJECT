package com.cqupt.canalManage.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class TestAgentIdOnlyOneAction extends ActionSupport {

	private static final long serialVersionUID = -6941574699319426537L;
	Logger logger = Logger.getLogger(getClass());
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	String agentCode;

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String execute() {
		response = ServletActionContext.getResponse();

		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(check(agentCode));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String check(String agentCode) {
		DataStormSession session = null;
		String flag = "";
		String sql = "";
		List list = null;
		try {
			session = DataStormSession.getInstance();
			/**
			 * flag：作为判断当前登录名是否存在的标识 * flag=1：说明当前登录名已经存在 *
			 * flag=2：说明当前登录名不存在，用户可以继续操作
			 * */

			sql = "select * from agent_information where agent_id='"
					+ agentCode + "'";

			list = session.findSql(sql);
			if (list != null && list.size() > 0) {
				flag = "1";
			} else {
				flag = "2";
			}
			session.closeSession();
		} catch (Exception e) {
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return flag;

	}

	/*
	 * public String checkUser() { try {
	 * 
	 * String logonName = electUser.getLogonName(); String flag =
	 * electUserService.checkUser(logonName); // 将服务器的值响应给客户端 PrintWriter out =
	 * response.getWriter(); out.println(flag); } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 * 
	 * public String checkUser(String logonName) { String condition =
	 * " and o.logonName = ?"; Object[] params = { logonName }; List<ElectUser>
	 * list = electUserDao.findCollectionByConditionNoPage( condition, params,
	 * null); String flag = ""; // 说明当前用户名已经存在 if (list != null && list.size() >
	 * 0) { flag = "1"; } else { flag = "2"; } return flag; }
	 */
}
