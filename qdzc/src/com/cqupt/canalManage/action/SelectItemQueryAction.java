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

public class SelectItemQueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(this.getClass());

	public String execute() {

		response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getDate());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	public String getDate() {
		String resultStr = "";
		String sql = "";

		request = ServletActionContext.getRequest();
		String type = request.getParameter("type");

		String cityId = request.getSession().getAttribute("cityId").toString();
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();

			if (type.equals("belong_branch")) {

				sql = "select t.dept_name text,t.dept_id id from qdzc.sys_dept t where city_id='"
						+ cityId + "' and dept_level='支/分局'";

			} else if (type.equals("canal_dept")) {

				sql = "select t.dept_name text,t.dept_id id from qdzc.sys_dept t where city_id='"
						+ cityId + "' and dept_level='办公部门'";
			} else if (type.equals("branch_company")) {

				sql = "select t.dept_name text,t.dept_id id from qdzc.sys_dept t where city_id='"
						+ cityId + "' and dept_level='分公司'";
			} else if (type.equals("all_dept")) {

				sql = "select t.dept_name text,t.dept_id id from qdzc.sys_dept t where city_id='"
						+ cityId + "'";
			} else {

				sql = "select select_id as id,select_item as text from select_item where select_name='"
						+ type + "'";
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
