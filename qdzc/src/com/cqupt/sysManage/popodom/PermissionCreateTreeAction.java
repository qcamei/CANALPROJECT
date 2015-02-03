package com.cqupt.sysManage.popodom;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class PermissionCreateTreeAction extends ActionSupport {

	private static final long serialVersionUID = -2465087541585226388L;

	HttpServletRequest request = null;

	public String execute() {

		HttpServletResponse response = ServletActionContext.getResponse();
		// �����ַ�
		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			PermissiondomDao dao = new PermissiondomDao();
			String xml = dao.createPopedomTree();
			out.print(xml);
			System.out.println(xml);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ֱ��������Ӧ������
		return null;// ����Ҫ��תĳ����ͼ ��Ϊ�����Ѿ�����ֱ���������Ӧ���
	}
}
