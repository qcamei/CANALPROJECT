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

public class SelectDataDeleteAction {
	HttpServletRequest request = null;
	Logger logger = Logger.getLogger(this.getClass());

	public String execute() {
		logger.info("DataDeleteAction:)");
		this.request = ServletActionContext.getRequest();
		request = ServletActionContext.getRequest();
		try {
			String txtID = DecodeUtils.decodeRequestString(request
					.getParameter("txtID"));// 型号id
			HttpServletResponse response = ServletActionContext.getResponse();
			// 设置字符集
			response.setCharacterEncoding("UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.print(selectDataDeleteAction(txtID));
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String selectDataDeleteAction(String txtID) {
		DataStormSession session = null;
		String resultStr = "";
		String sql = "";
		try {
			session = DataStormSession.getInstance();
			String[] selectIdArray = txtID.split(";");
			for (int i = 0; i < selectIdArray.length; i++) {
				sql = "delete from qdzc.select_item where select_id='"
						+ selectIdArray[i] + "'";
				logger.info("故障删除sql: " + sql);
				session.delete(sql);
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
