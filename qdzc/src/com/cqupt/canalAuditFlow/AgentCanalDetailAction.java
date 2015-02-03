package com.cqupt.canalAuditFlow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class AgentCanalDetailAction extends ActionSupport {
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
			String sql = "select * from qdzc.process4_agent_canal where in_id='"
					+ inId + "'";
			logger.info("查询信息" + sql);

			List<Map<String, Object>> resultList = session.findSql(sql);
			Map<String, Object> resultMap = (Map<String, Object>) resultList
					.get(0);
			// 命名自动更换模式
			request.setAttribute("inId", resultMap.get("inId"));
			request.setAttribute("canalId", resultMap.get("canalId"));
			request.setAttribute("canalName", resultMap.get("canalName"));
			// request.setAttribute("canalGenre", resultMap.get("canalGenre"));
			// request.setAttribute("canalClasses",
			// resultMap.get("canalClasses"));
			// request.setAttribute("belongManager",
			// resultMap.get("belongManager"));
			// request.setAttribute("belongInstitutionId",
			// resultMap.get("belongInstitutionId"));
			// request.setAttribute("belongBranch",
			// resultMap.get("belongBranch"));

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
