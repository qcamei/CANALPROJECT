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
 * 渠道变更流程中的分公司审核列表页面
 * 
 * @author Yang
 * @version 1.0 2014-10-26 上午9:45:40
 */
public class FilialeAuditQueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("FilialeAuditQueryAction1:)");
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getList());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String getList() {
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
				nextStepVal = "分公司审核";
			} else if (nextStepVal.equals("全部")) {
				nextStepVal = "";
			}
			txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");

			session = DataStormSession.getInstance();
			sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(select t.*,a.audit_state,a.oper_user,a.dept_name,a.audit_remark,date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time from "
					+ " (select t.*,a.step_val next_step_val,a.pre_step_val current_step_val from"
					+ " ( SELECT b.in_id,a.canal_id,a.canal_name,a.area_name,a.canal_state,a.canal_form,"
					+ "a.canal_property,a.canal_type,a.canal_parent_name,a.agent_id, a.agent_name,a.canal_user_name,"
					+ "a.canal_user_phone,a.broadband_account,a.urban_idetity,"
					+ "b.current_step,b.is_back,b.reason"
					+ " FROM qdzc.canal_infomation a right join qdzc.canal_step_state_his b on a.canal_id=b.canal_id "
					+ " where a.dept_id in ("
					+ dataAuthId
					+ ") AND a.canal_id <>'' ";

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
					+ " (select b.*,a.step_key pre_step_key,a.step_val pre_step_val from qdzc.step_info_modify a left join qdzc.step_info_modify b on a.step_no = b.pre_step_no) a"
					+ " on t.current_step = a.pre_step_no) t "
					+ " left join qdzc.process2_branch_audit_his a on t.in_id=a.in_id where 1=1";
			if (!txtBeginDate.equals("")) {
				sql += " and a.oper_time>'" + txtBeginDate + " 00:00:00' ";
			}
			if (!txtEndDate.equals("")) {
				sql += "  and a.oper_time<'" + txtEndDate + " 23:59:59' ";
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