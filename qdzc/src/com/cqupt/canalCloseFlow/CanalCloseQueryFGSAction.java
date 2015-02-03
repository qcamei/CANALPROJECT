package com.cqupt.canalCloseFlow;

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

public class CanalCloseQueryFGSAction extends ActionSupport {
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

		String closeId = "";
		String canalName = "";
		String canalId = "";
		String userName = "";
		String nextStepVal = "";
		String txtBeginDate = "";
		String txtEndDate = "";
		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			closeId = DecodeUtils.decodeRequestString(request
					.getParameter("closeId"));
			canalName = DecodeUtils.decodeRequestString(request
					.getParameter("canalName"));
			canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			userName = DecodeUtils.decodeRequestString(request
					.getParameter("userName"));
			nextStepVal = DecodeUtils.decodeRequestString(request
					.getParameter("nextStepVal"));
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();
			String type = request.getParameter("type");
			// 判断需要查询的步骤
			if (type.equals("fgs")) {
				type = "分公司审核";
			}
			if (type.equals("fgbm")) {
				type = "分管部门审核";
			}
			if (type.equals("crm")) {
				type = "关闭CRM工号";
			}
			if (type.equals("dlsgl")) {
				type = "代理商管理系统";
			}
			if (type.equals("qdgs")) {
				type = "渠道归属";
			}
			if (type.equals("scw")) {
				type = "删除代理商网站工号";
			}
			if (type.equals("zjjh")) {
				type = "资金稽核系统配置";
			}
			if (type.equals("zxsl")) {
				type = "专线受理环节";
			}
			if (type.equals("jcpz")) {
				type = "渠道基础配置";
			}
			if (type.equals("jhxt")) {
				type = "业务稽核系统配置";
			}
			if (type.equals("cwsh")) {
				type = "财务审核";
			}

			if (nextStepVal.equals("")) {
				nextStepVal = type;
			} else if (nextStepVal.equals("全部")) {
				nextStepVal = "";
			}
			txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");
			session = DataStormSession.getInstance();

			sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(select t.*,c.close_id ,c.user_name , c.dept_name ,c.dept_id,c.audit_state,"
					+ "date_format(c.oper_time,'%Y-%m-%d %H:%i:%s') check_time,c.refuse_reason,c.money,c.bmoney from "
					+ " (select t.*,a.step_val next_step_val,a.pre_step_val current_step_val from"
					+ " ( SELECT a.in_id,a.canal_id,a.canal_name,a.area_name,a.canal_state,a.canal_form,"
					+ "a.canal_property,a.canal_type,a.canal_parent_name,a.agent_name,a.canal_user_name,"
					+ "a.canal_user_phone,a.canal_manager,a.urban_idetity,"
					+ "date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,b.current_step,b.is_back"
					+ " FROM qdzc.canal_infomation a RIGHT join qdzc.canal_step_state_close b on a.in_id=b.in_id "
					+ " where a.dept_id in (" + dataAuthId + ")";

			if (!canalName.equals("")) {
				sql += " and a.canal_name like '%" + canalName + "%' ";
			}
			if (canalId != null && !canalId.equals("")) {
				sql += " and  a.canal_id like '%" + canalId + "%'";
			}
			sql += " ORDER BY a.in_id DESC) as t  left join"
					+ " (select b.*,a.step_key pre_step_key,a.step_val pre_step_val from qdzc.step_info_close a left join qdzc.step_info_close b on a.step_no = b.pre_step_no) a"
					+ " on t.current_step = a.pre_step_no) t "
					+ " left join qdzc.canal_infomation_close c on t.in_id=c.in_id ";
			if (nextStepVal.equals("")) {
				sql += " and t.next_step_val = c.next_step_val";
			}
			sql += " where  c.audit_state!='取消关闭' and c.audit_state!='驳回' ";
			if (!txtBeginDate.equals("")) {
				sql += " and c.oper_time>'" + txtBeginDate + " 00:00:00' ";
			}
			if (!txtEndDate.equals("")) {
				sql += "  and c.oper_time<'" + txtEndDate + " 23:59:59' ";
			}
			if (!nextStepVal.equals("")) {
				sql += " and t.next_step_val='" + nextStepVal
						+ "' and (c.next_step_val='" + nextStepVal
						+ "' or c.next_step_val is null)";
			}
			if (closeId != null && !closeId.equals("")) {
				sql += " and c.close_id like '%" + closeId + "%'";
			}
			if (userName != null && !userName.equals("")) {
				sql += " and  c.user_name like '%" + userName + "%'";
			}
			sql += " ) t";
			logger.info("工单查询：" + sql);
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
