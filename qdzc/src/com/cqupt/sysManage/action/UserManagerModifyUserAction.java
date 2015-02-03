package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.Md;
import com.opensymphony.xwork2.ActionSupport;

public class UserManagerModifyUserAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = -6941574699319426537L;

	/**
		 * 
		 */
	HttpServletRequest request = null;
	private Md md5fun = new Md();

	public String execute() {

		this.request = ServletActionContext.getRequest();

		try {
			String oldName = java.net.URLDecoder.decode(
					request.getParameter("oldName"), "UTF-8");
			String txtUserId = java.net.URLDecoder.decode(
					request.getParameter("txtUserId"), "UTF-8");
			String txtName = java.net.URLDecoder.decode(
					request.getParameter("txtName"), "UTF-8");

			String deptId = java.net.URLDecoder.decode(
					request.getParameter("deptId"), "UTF-8");
			String deptName = java.net.URLDecoder.decode(
					request.getParameter("deptName"), "UTF-8");
			String groupId = java.net.URLDecoder.decode(
					request.getParameter("groupId"), "UTF-8");
			String txtUserGroup = java.net.URLDecoder.decode(
					request.getParameter("txtUserGroup"), "UTF-8");

			String txtPhone = java.net.URLDecoder.decode(
					request.getParameter("txtPhone"), "UTF-8");
			String txtEmail = java.net.URLDecoder.decode(
					request.getParameter("txtEmail"), "UTF-8");
			String isUseable = java.net.URLDecoder.decode(
					request.getParameter("isUseable"), "UTF-8");

			if (isUseable.equals("1")) {
				isUseable = "可用";
			} else {
				isUseable = "不可用";
			}
			logger.info(isUseable);
			HttpServletResponse response = ServletActionContext.getResponse();

			response.setCharacterEncoding("UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.print(insertUserinfo(txtUserId, txtName, deptId, deptName,
					groupId, txtUserGroup, txtPhone, txtEmail, isUseable,
					oldName));
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	private String insertUserinfo(String txtUserId, String txtName,
			String deptId, String deptName, String groupId,
			String txtUserGroup, String txtPhone, String txtEmail,
			String isUseable, String oldName) {
		DataStormSession session = null;
		String resultStr = "";
		String sql = "";

		try {
			session = DataStormSession.getInstance();

			/*
			 * sql = "delete from sys_user t WHERE t.user_id = '"+txtUserId+"'";
			 * session.delete(sql);
			 */
			if (deptId.length() == 3 || deptId.length() == 4) {
				resultStr = "deptError";
			} else {

				sql = "update qdzc.sys_user set user_name ='" + txtName
						+ "',group_id='" + groupId + "',group_name = '"
						+ txtUserGroup + "',dept_id='" + deptId
						+ "',dept_name='" + deptName + "'," + "user_state='"
						+ isUseable + "',user_email='" + txtEmail
						+ "',phone_num='" + txtPhone + "' where user_id='"
						+ txtUserId + "'";
				logger.info("更改用户信息：" + sql);
				session.update(sql);

				sql = "update qdzc.canal_infomation set canal_user_name='"
						+ txtName + "' where canal_user_name='" + oldName + "'";
				logger.info("更改对应渠道信息：" + sql);
				session.update(sql);
				resultStr = "success";
			}

			session.closeSession();

		} catch (Exception e) {
			resultStr = "error";
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
