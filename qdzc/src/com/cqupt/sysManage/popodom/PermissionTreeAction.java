package com.cqupt.sysManage.popodom;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.opensymphony.xwork2.ActionSupport;

public class PermissionTreeAction extends ActionSupport {
	HttpServletRequest request = null;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String execute() {
		request = ServletActionContext.getRequest();
		int permissionId = Integer.valueOf(request.getParameter("menuid"));

		inputStream = new StringBufferInputStream(
				"Hello World! This is a text string response from a Struts 2 Action.");
		String str = new String(getChildDept(permissionId));
		try {
			inputStream = new ByteArrayInputStream(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	private String getChildDept(int permissionId) {
		DataStormSession session = null;
		int childDept = 0;
		String sql = "";
		StringBuilder str = new StringBuilder();
		int length = (permissionId + "").length();

		try {
			session = DataStormSession.getInstance();

			if (permissionId == 0) {
				sql = "select a.*, ifnull(total,0) child_total from (select * from qdzc.sys_menu where length(menuid)=1) a ,(select p_menuid,count(*) total from qdzc.sys_menu where length(p_menuid)=1 and menuid != p_menuid group by p_menuid) b where a.menuid = b.p_menuid";
			} else {
				sql = "select a.*,ifnull(total,0) child_total from (select * from qdzc.sys_menu where menuid like '"
						+ permissionId
						+ "%' and length(menuid)="
						+ (length + 1)
						+ ") a,(select p_menuid ,count(*) total from qdzc.sys_menu where menuid like '"
						+ permissionId
						+ "%' and length(p_menuid)="
						+ (length + 1)
						+ " group by p_menuid) b where a.menuid=b.p_menuid(+)";
			}

			Map map = session.executeSQL(sql);
			List list = (List) map.get("resultList");

			for (int i = 0; i < list.size(); i++) {
				Map resultMap = (Map) list.get(i);
				if (0 == i) {
					str.append("<?xml version=\"1.0\"?> <tree>");
				}
				if (resultMap.get("childTotal").toString().equals("0")) {
					str.append("<tree text=\"" + resultMap.get("menuname")
							+ "\" action=\"javascript:changList('"
							+ resultMap.get("menuid") + ":"
							+ resultMap.get("menuname") + "')\"/>");
				} else {
					str.append("<tree text=\"" + resultMap.get("menuname")
							+ "\" action=\"javascript:changList('"
							+ resultMap.get("menuid") + ":"
							+ resultMap.get("menuname")
							+ "')\" src=\"permissionQueryAction?menuid="
							+ resultMap.get("menuid") + "\" />");
				}
				if (i == list.size() - 1) {
					str.append("</tree>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}
}
