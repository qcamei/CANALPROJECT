package com.cqupt.sysManage.popodom;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAddAction extends ActionSupport {

	public String execute() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String roal = java.net.URLDecoder.decode(
				request.getParameter("roleName"), "utf-8");
		String roalDesc = java.net.URLDecoder.decode(
				request.getParameter("roleDesc"), "utf-8");

		// ����xml
		try {
			Reader reader = request.getReader();
			SAXReader saxReader = new SAXReader();
			// ����ĵ���Ӧʵ��
			Document document = saxReader.read(reader, "utf-8");
			// ���rootԪ��
			Element root = document.getRootElement();
			List elementList = root.elements("nodeId");
			List nodeIdList = new ArrayList();
			for (int i = 0; i < elementList.size(); i++) {
				Element element = (Element) elementList.get(i);
				nodeIdList.add(element.getTextTrim());
			}
			addPermission(nodeIdList, roal, roalDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private void addPermission(List nodeIdList, String roal, String roalDesc)
			throws CquptException {
		DataStormSession session = null;

		session = DataStormSession.getInstance();
		String sql = null;
		sql = "select count(*)+1 sum from roal";

		List list = null;

		try {
			list = session.findSql(sql);

			int roalId = ((BigDecimal) list.get(0)).intValue();

			// �����ɫ
			sql = "insert into roal values('" + roalId + "','" + roal + "','"
					+ roalDesc + "')";
			// System.out.println(sql);
			session.add(sql);

			// ����˵�Ȩ��
			for (int i = 0; i < nodeIdList.size(); i++) {
				sql = "insert into roal_permission values('" + roalId + "','"
						+ nodeIdList.get(i) + "')";
				session.add(sql);

			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
