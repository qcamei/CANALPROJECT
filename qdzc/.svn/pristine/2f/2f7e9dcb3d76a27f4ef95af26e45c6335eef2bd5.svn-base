package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.JsonUtil;
import com.cqupt.sysManage.dao.PermissiondomDao;
import com.opensymphony.xwork2.ActionSupport;

public class GroupAuthCreateTreeAction  extends ActionSupport{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2465087541585226388L;
	/**
	 *   查询用户信息
	 */

	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		
		//String txtUserId = request.getParameter("txtUserId");
		String txtUserType = request.getParameter("txtUserType");
		String txtUserGroup = request.getParameter("txtUserGroup");
		String parentDeptName = request.getParameter("parentDeptName");
		String hidParentDeptId = request.getParameter("hidParentDeptId");
	/*
		try {
		
			if(txtUserId != null)
				txtUserId = java.net.URLDecoder.decode(txtUserId, "utf-8");
			else 
				txtUserId = "";
			
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		*/
	
	
		//System.out.println("用户工号：" + txtUserId);
	

		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			PermissiondomDao dao = new PermissiondomDao();
        	String xml = dao.createPopedomTree();
			out.print(xml);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 直接输入响应的内容
		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果
	}

	
}
