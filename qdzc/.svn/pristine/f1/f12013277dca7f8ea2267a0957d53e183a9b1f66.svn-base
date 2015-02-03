package com.cqupt.canalAuditFlow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class LineConfig12DetailAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		String inId = request.getParameter("inId");

		String resultStr = "";
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			String sql = "select * from qdzc.process12_config_line where in_id='"
					+ inId + "'";
			logger.info("查询信息" + sql);

			List<Map<String, Object>> resultList = session.findSql(sql);
			Map<String, Object> resultMap = (Map<String, Object>) resultList
					.get(0);
			// 命名自动更换模式
			request.setAttribute("inId", resultMap.get("inId"));
			request.setAttribute("canalId", resultMap.get("canalId"));
			request.setAttribute("canalName", resultMap.get("canalName"));

			request.setAttribute("broadbandAccount",
					resultMap.get("broadbandAccount"));
			request.setAttribute("broadbandPassword",
					resultMap.get("broadbandPassword"));
			// request.setAttribute("privilegeInfo",
			// resultMap.get("privilegeInfo"));
			request.setAttribute("wwwAccount", resultMap.get("wwwAccount"));
			request.setAttribute("wwwPrivilegeInfo",
					resultMap.get("wwwPrivilegeInfo"));
			// request.setAttribute("noPaperAccount",
			// resultMap.get("noPaperAccount"));
			// request.setAttribute("noPaperPrivilegeInfo",
			// resultMap.get("noPaperPrivilegeInfo"));

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
		resultStr = "modify";

		return resultStr;

	}
}
