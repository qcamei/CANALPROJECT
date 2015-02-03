package com.cqupt.canalModifyFlow;

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

/**
 * 渠道变更流程中查找条件文本框的下拉列表中的流程信息
 * 
 * @author Yang
 * @version 1.0 2014-10-26 上午9:53:25
 */
public class NextStepValQueryAction1 extends ActionSupport {
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
		
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			
			
			
			sql = "select step_no as id,step_val as text from step_info_modify ";
			
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
