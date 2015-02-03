package com.cqupt.sysManage.popodom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.opensymphony.xwork2.ActionSupport;

public class RoleDeleteAction extends ActionSupport {
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		PrintWriter out = null;
		String roalId = request.getParameter("roalId");
		// String[] roalIds = roalId.split(",");

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String msg = deleteRoal(roalId);
		out.print(msg);
		out.flush();
		out.close();

		return null;
	}

	private String deleteRoal(String roalIds) {
		String msg = "success";
		DataStormSession session = null;
		roalIds = roalIds.substring(0, roalIds.length() - 1);
		try {
			session = DataStormSession.getInstance();
			String sql = "delete roal_permission where role_id in (" + roalIds
					+ ")";
			session.delete(sql);

			/*
			 * sql =
			 * "update emp set roal_id=null where roal_id in("+roalIds+")";
			 * session.update(sql);
			 */

			sql = "delete roal where roal_id in(" + roalIds + ")";
			session.delete(sql);
		} catch (Exception e) {
			msg = "failure";
			e.printStackTrace();

		}

		return msg;
	}
}
