package com.cqupt.sysManage.popodom;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class PermissionTree extends ActionSupport {
	HttpServletResponse response = null;

	public String execute() {
		// json ��ʽ[{text:'�ڵ�һ',children:[{text:'�ڵ�1.1'}]},{text:'�ڵ��'}]
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;

		List dataList = null;
		try {
			dataList = DataGenerator.getVirtualResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap nodeList = new HashMap();
		// һ���ڵ�
		List<Node> root = new ArrayList<Node>();

		for (Iterator it = dataList.iterator(); it.hasNext();) {
			Map dataRecord = (Map) it.next();
			Node node = new Node();
			node.id = ((BigDecimal) dataRecord.get("menuid")).intValue();
			node.text = (String) dataRecord.get("menuname");
			if (dataRecord.get("pMenuid") != null) {
				node.parentId = ((BigDecimal) dataRecord.get("pMenuid"))
						.intValue();
			} else {
				node.parentId = 0;
			}
			nodeList.put(node.id, node);
		}
		Set entrySet = nodeList.entrySet();
		for (Iterator it = entrySet.iterator(); it.hasNext();) {
			Node node = (Node) ((Map.Entry) it.next()).getValue();
			if (node.parentId == node.id) {
				root.add(node);
			} else {
				((Node) nodeList.get(node.parentId)).addChild(node);
			}
		}
		Iterator it = root.iterator();
		String json = "";
		while (it.hasNext()) {
			json += it.next().toString() + ",";
		}
		json = "[" + json.substring(0, json.length() - 1) + "]";

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.flush();
		out.close();
		return null;
	}
}
