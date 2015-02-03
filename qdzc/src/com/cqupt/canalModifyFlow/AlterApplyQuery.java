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

public class AlterApplyQuery extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("CanalInfoQueryAction1:)");
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
		String canalUserPhone = "";
		String txtBeginDate = "";
		String txtEndDate = "";
		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			String deptId = request.getSession().getAttribute("deptId")
					.toString();
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();
			String individualAuth = request.getSession()
					.getAttribute("individualAuth").toString();
			String userName = request.getSession().getAttribute("userName")
					.toString();

			inId = DecodeUtils
					.decodeRequestString(request.getParameter("inId"));
			canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			canalName = DecodeUtils.decodeRequestString(request
					.getParameter("canalName"));
			areaName = DecodeUtils.decodeRequestString(request
					.getParameter("areaName"));
			canalUserPhone = DecodeUtils.decodeRequestString(request
					.getParameter("canalUserPhone"));
			txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");

			session = DataStormSession.getInstance();
			sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(select t.*,a.step_val next_step_val,a.pre_step_val current_step_val from"
					+ " ( SELECT a.in_id,a.canal_id,a.agent_id,a.canal_name,a.area_name,a.canal_state,a.canal_form,"
					+ "a.canal_property,a.canal_type,a.canal_parent_name,a.agent_name,a.canal_user_name,"
					+ "a.canal_user_phone,a.broadband_account,a.urban_idetity,a.oper_user,a.dept_id,a.dept_name,"
					+ "date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,b.current_step,b.is_back"
					+ " FROM qdzc.canal_infomation a left join qdzc.canal_step_state b on a.in_id=b.in_id "
					+ " where a.dept_id in ("
					+ dataAuthId
					+ ") AND a.canal_state='正常' and b.current_step='11' ";// 不能显示非正常状态下的工单，只能显示新增流程为完成的工单。不能显示变更流程正在走的流程

			if (individualAuth.equals("selfOperator")) {
				// 渠道客户经理 只能看自己录入 的数据
				sql += " and a.canal_user_name = '" + userName + "'";

			}
			// 以下为查询筛选条件
			if (!canalId.equals("")) {
				sql += " and a.canal_id like '%" + canalId + "%' ";
			}
			if (!txtBeginDate.equals("")) {
				sql += " and a.oper_time>'" + txtBeginDate + " 00:00:00' ";
			}
			if (!txtEndDate.equals("")) {
				sql += "  and a.oper_time<'" + txtEndDate + " 23:59:59' ";
			}
			if (!canalUserPhone.equals("")) {
				sql += " and a.canal_user_phone like '%" + canalUserPhone
						+ "%' ";
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
					+ " on t.current_step = a.pre_step_no) t WHERE t.canal_id not IN(SELECT canal_id FROM canal_step_state_his WHERE current_step<>'8'  )";

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
		// System.out.println("resultStr:" + resultStr);
		return resultStr;
	}
}
