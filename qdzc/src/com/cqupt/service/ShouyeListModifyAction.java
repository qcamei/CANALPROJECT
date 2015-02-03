package com.cqupt.service;

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

public class ShouyeListModifyAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("ShouyeListAction:)");
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

		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			String deptId = request.getSession().getAttribute("deptId")
					.toString();
			String operUser = request.getSession().getAttribute("userName")
					.toString();
			String groupId = request.getSession().getAttribute("groupId")
					.toString();
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();

			String individualAuth = request.getSession()
					.getAttribute("individualAuth").toString();

			inId = DecodeUtils
					.decodeRequestString(request.getParameter("inId"));

			session = DataStormSession.getInstance();
			// 1.根据Session中的userName通过sys_user得到对应的group_id
			// sql = "select group_id from qdzc.sys_user where user_name='"
			// + operUser + "'";
			// Map resultMap = session.findSql(sql).get(0);
			// String groupId = (String) resultMap.get("groupId");
			// // 2.根据group_id通过data_auth得到对应的step_no
			// System.out.println("查出的group_Id为：" + groupId);
			// 3.通过step_no在step_info中得到对应流程的名称step_val以便列表中可以显示名称

			// 4.通过step_no在canal_step_state找到current_step=step_no的记录

			sql = "SELECT @rownum :=@rownum + 1 AS rownum,t.* FROM(SELECT @rownum := 0) r,(SELECT a.* FROM(SELECT p.*, q.current_step,q.in_id,q.canal_id,q.canal_name FROM(SELECT c.group_id,d.* FROM data_auth c,( SELECT a.step_no,a.step_val cur_step, b.step_val next_step FROM qdzc.step_info_modify a LEFT JOIN qdzc.step_info_modify b ON a.step_no = b.pre_step_no WHERE b.step_val IS NOT NULL ) d WHERE c.step_no = d.step_no AND c.group_id = '"
					+ groupId
					+ "' ) p LEFT JOIN canal_step_state_his q ON p.step_no = q.current_step where q.current_step<>'11' and q.dept_id in ("
					+ dataAuthId + ") ";

			if (individualAuth.equals("selfOperator")) {
				// 渠道客户经理 只能看自己录入 的数据
				sql += " and q.oper_user = '" + operUser + "'";
			}
			sql += ") a LEFT JOIN qdzc.canal_infomation b ON a.in_id = b.in_id ";
			if (individualAuth.equals("OpenCanal")) {
				// 渠道客户经理 只能看自己录入 的数据 canal_information
				sql += " and b.canal_type like '%开放渠道%'";

			} else if (individualAuth.equals("Canal")) {
				sql += " and b.canal_type not like '%开放渠道%'";
			}
			sql += " ) t";
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
