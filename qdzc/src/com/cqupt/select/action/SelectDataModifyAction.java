package com.cqupt.select.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class SelectDataModifyAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;

	public String execute() {
		logger.info("SelectDataModifyAction:)");
		request = ServletActionContext.getRequest();
		String selectId = request.getParameter("selectId");

		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			String sql = "select * from select_item where select_id="
					+ selectId;
			logger.info("查询要修改区域的详细信息" + sql);
			List<Map<String, Object>> resultList = session.findSql(sql);
			Map<String, Object> resultMap = (Map<String, Object>) resultList
					.get(0);
			// 将获取信息传入request
			request.setAttribute("selectId", resultMap.get("selectId"));
			request.setAttribute("selectName", resultMap.get("selectName"));
			request.setAttribute("selectItem", resultMap.get("selectItem"));
			request.setAttribute("remark", resultMap.get("remark"));

			session.closeSession();
		} catch (Exception e) {
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return "success";
	}
}
