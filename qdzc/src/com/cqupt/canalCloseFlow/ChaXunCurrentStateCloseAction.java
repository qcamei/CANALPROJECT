package com.cqupt.canalCloseFlow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class ChaXunCurrentStateCloseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(this.getClass());

	public String execute() {
		request = ServletActionContext.getRequest();

		response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			String inId = request.getParameter("inId");
			String currentStep = request.getParameter("currentStep");
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getDate(inId, currentStep));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	public String getDate(String inId, String currentStep) {
		String resultStr = "";
		String sql = "";

		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();

			sql = "select audit_state  from canal_infomation_close where in_id='"
					+ inId + "' and current_step='" + currentStep + "'";

			logger.info(sql);
			JSONArray jsonObject = JSONArray.fromObject(session.findSql(sql));
			resultStr = jsonObject.toString();
			session.closeSession();
		} catch (Exception e) {
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		logger.info(resultStr);
		return resultStr;
	}
}
