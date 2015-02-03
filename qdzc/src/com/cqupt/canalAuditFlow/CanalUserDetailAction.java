package com.cqupt.canalAuditFlow;

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

public class CanalUserDetailAction extends ActionSupport {
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
		String canalId = request.getParameter("canalId");

		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(getAgentList(canalId, pageSize, page));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private char[] getAgentList(String canalId, String pageSize, String page) {
		String sql = "";
		String resultStr = "";
		Map result = null;

		sql = "select b.* , ROWNUM order_id FROM (select @rownum:=@rownum+1 as rownum, t.in_id, t.canal_in_id, t.user_number , t.user_name , t.user_kind , t.user_cellphone, t.user_state, t.canal_id, t.canal_name, t.user_agent, t.user_authority, t.user_id_card, t.user_pinyin, t.user_dept, t.user_email, t.user_role, t.user_count_id, t.user_sex, date_format(t.user_birthday, '%Y-%c-%d %H:%i:%s') user_birthday, t.user_nativeplace, t.user_phone, t.user_education, t.user_certification, date_format(t.user_in_date, '%Y-%c-%d %H:%i:%s') user_in_date, date_format(t.user_out_date, '%Y-%c-%d %H:%i:%s') user_out_date, t.user_address, t.user_position, t.user_way, t.user_region_name, t.oper_user, t.dept_id, t.dept_name, date_format(t.oper_time, '%Y-%c-%d %H:%i:%s') oper_time, t.remark "
				+ " from (select @rownum:=0) r, qdzc.canal_user t WHERE canal_id='"
				+ canalId + "'";
		sql += ")b";

		logger.info("资产List：" + sql);

		try {
			DataStormSession session = DataStormSession.getInstance();
			result = session.findSql(sql, Integer.parseInt(page),
					Integer.parseInt(pageSize));
			resultStr = JsonUtil.map2json(result);
			logger.info("Json:" + resultStr);
			session.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultStr.toCharArray();
	}
}
