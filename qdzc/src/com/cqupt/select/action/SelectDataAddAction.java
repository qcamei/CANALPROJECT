package com.cqupt.select.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.opensymphony.xwork2.ActionSupport;

public class SelectDataAddAction extends ActionSupport {
	private static final long serialVersionUID = -3503583143062479748L;
	Logger logger = Logger.getLogger(this.getClass());
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	String standbyPhoneId = "null";

	@Override
	public String execute() {
		System.out.println("SelectDataAddAction");
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

		String selectItem = DecodeUtils.decodeRequestString(request
				.getParameter("selectItem"));
		String remark = DecodeUtils.decodeRequestString(request
				.getParameter("remark"));
		String select = DecodeUtils.decodeRequestString(request
				.getParameter("select"));
		System.out.println(selectItem + "000" + remark + "9999" + select);

		try {
			session = DataStormSession.getInstance();
			sql = "insert   into qdzc.select_item (select_name,select_item,remark)values('"
					+ select + "','" + selectItem + "','" + remark + "')";
			logger.info(sql);
			session.add(sql);
			session.closeSession();

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