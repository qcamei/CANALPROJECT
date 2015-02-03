package com.cqupt.sysManage.popodom;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cqupt.pub.dao.DataStormSession;
import com.opensymphony.xwork2.ActionSupport;

public class RoleModifyAction extends ActionSupport {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	DataStormSession session = null;

	public String execute() {
		try {
			String groupId = java.net.URLDecoder.decode(
					request.getParameter("groupId"), "utf-8");
			String groupName = java.net.URLDecoder.decode(
					request.getParameter("groupName"), "utf-8");
			String remark = java.net.URLDecoder.decode(
					request.getParameter("remark"), "utf-8");
			// ����xml
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
			boolean isSuccess = ModifyPermission(nodeIdList, groupId,
					groupName, remark);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean ModifyPermission(List nodeIdList, String groupId,
			String groupName, String remark) {
		boolean isSuccess = true;
		String sql = "update qdzc.sys_user_group set group_name='" + groupName
				+ "',remark='" + remark + "' where group_id="
				+ Integer.valueOf(groupId);

		try {
			session = DataStormSession.getInstance();
			session.update(sql);
			sql = "delete from qdzc.sys_user_group_oper_auth where group_id="
					+ Integer.valueOf(groupId);
			session.delete(sql);
			for (int i = 0; i < nodeIdList.size(); i++) {
				sql = "insert into qdzc.sys_user_group_oper_auth(group_id,menuid) values( "
						+ Integer.valueOf(groupId)
						+ ","
						+ nodeIdList.get(i)
						+ ")";
				session.add(sql);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;
	}
}
