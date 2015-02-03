package com.cqupt.canalManage.action;

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

public class AgentLevelQueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(this.getClass());

	public String execute() {
		request = ServletActionContext.getRequest();
		String dept = null;
		try {
			dept = java.net.URLDecoder.decode(request.getParameter("dept"),
					"UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		logger.info("所选部门为：" + dept);
		response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getDate(dept));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	public String getDate(String dept) {
		String resultStr = "";
		String sql = "";

		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();

			if (dept.equals("客服部")) {
				sql = "select select_id as id,select_item as text from select_item where select_name='agent_level_zq' ";
			} else if (dept.equals("政企部")) {
				sql = "select select_id as id,select_item as text from select_item where select_name='agent_level_zq' ";
			} else if (dept.equals("新业务推广中心")) {
				sql = "select select_id as id,select_item as text from select_item where select_name='agent_level_xs' ";
			} else if (dept.equals("区县分公司")) {
				sql = "select select_id as id,select_item as text from select_item where select_name='agent_level_qx' ";
			}

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
