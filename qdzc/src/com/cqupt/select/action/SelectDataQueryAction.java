package com.cqupt.select.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SelectDataQueryAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = -2465087541585226388L;
	/**
	 * 查询本代理商信息
	 */
	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String pageSize = request.getParameter("pagesize");
		String page = request.getParameter("page");

		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getSelectList(pageSize, page));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String getSelectList(String pageSize, String page) {
		String sql = "";
		String resultStr = "";

		String remark = "";
		String selectItem = "";
		String selectId = "";
		String selectName = "";

		try {
			remark = java.net.URLDecoder.decode(request.getParameter("remark"),
					"UTF-8");
			selectItem = java.net.URLDecoder.decode(
					request.getParameter("selectItem"), "UTF-8");
			// selectId = java.net.URLDecoder.decode(
			// request.getParameter("selectId"), "UTF-8");
			// selectName = java.net.URLDecoder.decode(
			// request.getParameter("selectName"), "UTF-8");

		} catch (Exception e1) {

		}

		sql = "select b.* , ROWNUM order_id FROM (select @rownum:=@rownum+1 as rownum, t.select_id , t.select_name , t.select_item , t.remark"
				+ " from (select @rownum:=0) r, qdzc.select_item t WHERE 1=1";

		// if (selectId != null && !selectId.equals("")) {
		// sql += " and  t.select_id like '%" + selectId + "%'";
		// }
		if (selectItem != null && !selectItem.equals("")) {
			sql += " and t.select_item like '%" + selectItem + "%'";
		}
		// if (selectName != null && !selectName.equals("")) {
		// sql += " and t.select_name like '%" + selectName + "%'";
		// }
		if (remark != null && !remark.equals("")) {
			sql += " and  t.remark like '%" + remark + "%'";
		}
		sql += ")b";

		logger.info("资产List：" + sql);

		try {
			DataStormSession session = DataStormSession.getInstance();
			Map resultMap = session.findSql(sql, Integer.parseInt(page),
					Integer.parseInt(pageSize));
			resultStr = JsonUtil.map2json(resultMap);
			session.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultStr;
	}
}
