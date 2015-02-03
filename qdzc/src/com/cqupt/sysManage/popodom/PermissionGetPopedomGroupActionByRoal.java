package com.cqupt.sysManage.popodom;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.util.XmlCreater1;
import com.opensymphony.xwork2.ActionSupport;

public class PermissionGetPopedomGroupActionByRoal extends ActionSupport {
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private XmlCreater1 xmlCreater = XmlCreater1.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	public String execute() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		PrintWriter out = null;

		String groupId = request.getParameter("groupId");
		System.out.println("--------" + groupId);

		DataStormSession session = null;

		String sql = "select menuid from qdzc.sys_menu where menuid in (select menuid from qdzc.sys_user_group_oper_auth where group_id='"
				+ groupId + "')";
		Map dataMap = null;
		String elementId = "";

		try {
			session = DataStormSession.getInstance();

			response.setContentType("text/xml; charset=UTF-8");
			out = response.getWriter();
			List resultList = session.findSql(sql);
			logger.info("resultList为:" + resultList);
			// [{menuid=05}, {menuid=0506}, {menuid=050601}, {menuid=050602},
			// {menuid=050603}, {menuid=050604}, {menuid=0515}, {menuid=051501},
			// {menuid=051502},
			// {menuid=051503}, {menuid=051504}, {menuid=07}, {menuid=0707},
			// {menuid=070701}]
			List popedomList = null;
			String xml = "";

			PermissiondomDao dao = new PermissiondomDao();
			popedomList = dao.getPopedomGroup(resultList);
			List tmpList = new ArrayList();

			for (int i = 0; i < popedomList.size(); i++) {
				Popedom p1 = (Popedom) popedomList.get(i);
				for (int j = popedomList.size() - 1; j > i; j--) {
					Popedom p2 = (Popedom) popedomList.get(j);
					if (p1 == p2) {
						popedomList.remove(j);
					}
				}
			}

			popedomList = dao.getPopemdomList(popedomList);
			out.print(this.xmlCreater.createListXml(popedomList));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
