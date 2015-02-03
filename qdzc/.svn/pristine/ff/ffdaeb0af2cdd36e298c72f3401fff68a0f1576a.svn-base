package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.util.XmlCreater1;
import com.cqupt.sysManage.dao.PermissiondomDao;

import com.opensymphony.xwork2.ActionSupport;

public class GroupAuthModifyInitData extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2465087541585226388L;
	/**
	 *   查询菜单组
	 */
	HttpServletRequest request = null;
	private XmlCreater1 xmlCreater = XmlCreater1.getInstance();

	public String execute() {
		request = ServletActionContext.getRequest();
		
		//String txtUserId = request.getParameter("txtUserId");
		String elementId = request.getParameter("elementId");

	
	
System.out.println("elementId：" + elementId);
	

		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			PermissiondomDao dao = new PermissiondomDao();
			List popedomList = dao.initPopedomGroup(elementId);
//System.out.println(this.xmlCreater.createListXml(popedomList));
			out.print(this.xmlCreater.createListXml(popedomList));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 直接输入响应的内容
		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果
	}

	
}
