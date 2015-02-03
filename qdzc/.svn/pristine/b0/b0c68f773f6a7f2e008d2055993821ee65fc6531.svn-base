package com.cqupt.select.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.opensymphony.xwork2.ActionSupport;

public class SelectDataUpdateAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	public String execute() {
		logger.info("FailureTypeUpdateAction：");
		request = ServletActionContext.getRequest();

		try {
			String selectId = DecodeUtils.decodeRequestString(request
					.getParameter("selectId"));// 下拉列表名称
			// String remark = DecodeUtils.decodeRequestString(request
			// .getParameter("remark"));// 下拉列表名称
			// String selectName = DecodeUtils.decodeRequestString(request
			// .getParameter("selectName"));// 属性内容
			String selectItem = DecodeUtils.decodeRequestString(request
					.getParameter("selectItem"));// 下拉列表内容
			response = ServletActionContext.getResponse();

			// 设置字符集
			response.setCharacterEncoding("UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.print(saveToDatabase(selectId, selectItem));
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 直接输入响应的内容
		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String saveToDatabase(String selectId, String selectItem) {
		DataStormSession session = null;
		String resultStr = "";
		String sql = "";

		try {
			session = DataStormSession.getInstance();

			sql = "update qdzc.select_item set  select_item='" + selectItem
					+ "' where select_id=" + selectId;
			logger.info("修改区域信息：" + sql);
			session.add(sql);
			resultStr = "success";
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
		logger.info(resultStr);
		return resultStr;

	}
}
