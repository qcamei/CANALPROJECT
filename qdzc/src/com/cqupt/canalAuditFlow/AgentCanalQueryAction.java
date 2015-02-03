package com.cqupt.canalAuditFlow;

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

public class AgentCanalQueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("AgentCanalQueryAction:)");
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

		String inId = "";
		String canalId = "";
		String canalName = "";
		String areaName = "";
		String nextStepVal = "";
		String txtBeginDate = "";
		String txtEndDate = "";
		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			String deptId = request.getSession().getAttribute("deptId")
					.toString();
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();

			inId = DecodeUtils
					.decodeRequestString(request.getParameter("inId"));
			canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			canalName = DecodeUtils.decodeRequestString(request
					.getParameter("canalName"));
			areaName = DecodeUtils.decodeRequestString(request
					.getParameter("areaName"));
			nextStepVal = DecodeUtils.decodeRequestString(request
					.getParameter("nextStepVal"));
			if (nextStepVal.equals("")) {
				nextStepVal = "代理商管理系统";
			} else if (nextStepVal.equals("全部")) {
				nextStepVal = "";
			}
			txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");

			session = DataStormSession.getInstance();
			sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(select t.*,a.canal_genre,a.canal_classes,a.belong_manager,a.belong_institution_id,a.belong_branch,a.dept_name,a.oper_user,a.remark,date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') check_time from "
					+ " (select t.*,a.step_val next_step_val,a.pre_step_val current_step_val from"
					+ " ( SELECT a.in_id,a.canal_id,a.crm_number,a.canal_name,a.area_name,a.canal_state,a.canal_form,"
					+ "a.canal_property,a.canal_type,a.canal_parent_name,a.agent_id,a.agent_name,a.canal_user_name,"
					+ "a.canal_user_phone,a.broadband_account,a.in_collect_number,a.urban_idetity,"
					+ "date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,b.current_step,b.is_back,b.reason"
					+ " FROM (SELECT m.*, n.crm_number crmn FROM qdzc.canal_infomation m LEFT JOIN qdzc.process5_open_CRM n ON m.in_id = n.in_id)a left join qdzc.canal_step_state b on a.in_id=b.in_id "
					+ " where a.dept_id in (" + dataAuthId + ")";

			if (!canalId.equals("")) {
				sql += " and a.canal_id like '%" + canalId + "%' ";
			}
			if (!canalName.equals("")) {
				sql += " and a.canal_name like '%" + canalName + "%' ";
			}
			if (!areaName.equals("")) {
				sql += " and a.area_name like '%" + areaName + "%' ";
			}
			if (!inId.equals("")) {
				sql += " and a.in_id like '%" + inId + "%' ";
			}

			sql += " ORDER BY a.in_id DESC) as t  left join"
					+ " (select b.*,a.step_key pre_step_key,a.step_val pre_step_val from qdzc.step_info a left join qdzc.step_info b on a.step_no = b.pre_step_no) a"
					+ " on t.current_step = a.pre_step_no) t "
					+ " left join qdzc.process4_agent_canal a on t.in_id=a.in_id where 1=1";
			if (!txtBeginDate.equals("")) {
				sql += " and t.oper_time>'" + txtBeginDate + " 00:00:00' ";
			}
			if (!txtEndDate.equals("")) {
				sql += "  and t.oper_time<'" + txtEndDate + " 23:59:59' ";
			}

			if (!nextStepVal.equals("")) {
				sql += " and t.next_step_val='" + nextStepVal + "' ";
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
