package com.cqupt.canalAuditFlow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.opensymphony.xwork2.ActionSupport;

public class GraphicQueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("GraphicQueryAction:)");
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getResult());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String getResult() {
		String resultStr = "";
		String sql = "";
		DataStormSession session = null;

		String inId = "";
		String canalId = "";
		try {
			inId = DecodeUtils
					.decodeRequestString(request.getParameter("inId"));
			canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			session = DataStormSession.getInstance();
			sql = "select * from canal_step_state where 1=1";
			if (inId != "" || !inId.equals("")) {
				sql += " and in_Id=" + inId;
			}
			if (canalId != "" || !canalId.equals("")) {
				sql += " and canal_id=" + canalId;
			}
			logger.info("查询工单信息：" + sql);
			int result = 0;
			List list = session.findSql(sql);
			if (list.size() > 0) {
				Map map = (Map) list.get(0);
				result = (Integer) map.get("currentStep");
				logger.info("工单当前所处的步骤：" + result);
				resultStr = result + "";
			} else {
				resultStr = result + "";
			}
			session.closeSession();
		} catch (Exception e) {
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return resultStr;
	}
}
