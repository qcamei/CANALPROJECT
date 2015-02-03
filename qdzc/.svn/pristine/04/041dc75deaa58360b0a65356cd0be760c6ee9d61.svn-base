package com.cqupt.canalModifyFlow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 历史记录 列表页面
 * 
 * @author Yang
 * @version 1.0 2014-11-3 上午8:54:40
 */
public class HistoryDetailQueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("HistoryDetailQueryAction:)");
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getDate());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String getDate() {
		String resultStr = "";
		String sql = "";
		DataStormSession session = null;

		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			String deptId = request.getSession().getAttribute("deptId")
					.toString();
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();

			String canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));

			session = DataStormSession.getInstance();
			sql = "SELECT @rownum :=@rownum + 1 AS rownum,t.* FROM (SELECT @rownum := 0) r, ( SELECT a.id, a.canal_id, a.agent_id, a.user_number, a.oper_user, a.oper_dept, date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time, a.oper_column, a.old_value, a.new_value FROM qdzc.modify_history a where 1=1 ";

			if (!canalId.equals("")) {
				sql += " and a.canal_id='" + canalId + "'";
			}

			sql += ")t";

			logger.info("查询工单信息：" + sql);
			resultStr = JsonUtil.map2json(session.findSql(sql,
					Integer.parseInt(page), Integer.parseInt(pageSize)));
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
