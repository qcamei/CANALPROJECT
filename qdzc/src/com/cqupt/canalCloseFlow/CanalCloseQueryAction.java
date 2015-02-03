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

public class CanalCloseQueryAction extends ActionSupport {
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
		String individualAuth = "";
		String txtEndDate = "";
		try {
			String pageSize = request.getParameter("pagesize");
			String page = request.getParameter("page");
			String operUser = request.getSession().getAttribute("userName")
					.toString();
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
			individualAuth = request.getSession()
					.getAttribute("individualAuth").toString();
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();
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

			if (nextStepVal.equals("分公司审核")) {
				sql = "select b.* , ROWNUM order_id FROM (select @rownum:=@rownum+1 as rownum, t.close_id , t.user_name , t.dept_name , t.dept_id,t.audit_state,t.canal_id,t.canal_name,"
						+ "t.current_step_val,t.current_step,t.next_step_val,date_format(t.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,t.in_id,t.refuse_reason,t.money,t.bmoney"
						+ " from (select @rownum:=0) r, qdzc.canal_infomation_close t WHERE t.audit_state!='取消关闭'  ";

				if (!txtBeginDate.equals("")) {
					sql += " and t.oper_time>'" + txtBeginDate + " 00:00:00' ";
				}
				if (!txtEndDate.equals("")) {
					sql += "  and t.oper_time<'" + txtEndDate + " 23:59:59' ";
				}

				if (closeId != null && !closeId.equals("")) {
					sql += " and t.close_id like '%" + closeId + "%'";
				}

				if (canalName != null && !canalName.equals("")) {
					sql += " and  t.canal_name like '%" + canalName + "%'";
				}
				if (canalId != null && !canalId.equals("")) {
					sql += " and  t.canal_id like '%" + canalId + "%'";
				}
				if (userName != null && !userName.equals("")) {
					sql += " and  t.user_name like '%" + userName + "%'";
				}
				if (nextStepVal != null && !nextStepVal.equals("")) {
					sql += " and  t.next_step_val like '%" + nextStepVal + "%'";
				}

				sql += ")b";
				logger.info("资产List：" + sql);

			} else {

				sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(select t.*,c.close_id ,c.user_name , c.dept_name ,c.dept_id,c.audit_state,"
						+ "date_format(c.oper_time,'%Y-%m-%d %H:%i:%s') check_time,c.refuse_reason,c.money,c.bmoney from "
						+ " (select t.*,a.step_val next_step_val,a.pre_step_val current_step_val from"
						+ " ( SELECT a.in_id,a.canal_id,a.canal_name,a.area_name,a.canal_state,a.canal_form,"
						+ "a.canal_property,a.canal_type,a.canal_parent_name,a.agent_name,a.canal_user_name,"
						+ "a.canal_user_phone,a.broadband_account,a.canal_manager,a.urban_idetity,"
						+ "date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,b.current_step,b.is_back"
						+ " FROM qdzc.canal_infomation a RIGHT join qdzc.canal_step_state_close b on a.in_id=b.in_id "
						+ " where a.dept_id in (" + dataAuthId + ")";

				if (individualAuth.equals("selfOperator")) {
					// 渠道客户经理 只能看自己录入 的数据
					sql += " and a.canal_user_name = '" + operUser + "'";

				}
				// 数据权限控制
				// if (individualAuth.equals("")) {
				//
				// } else if (individualAuth.equals("OpenCanal")) {
				// // 渠道客户经理 只能看自己录入 的数据
				// sql += " and a.canal_type like '%开放渠道%'";
				//
				// } else {
				// sql += " and a.canal_type not like '%开放渠道%'";
				// }

				if (!canalName.equals("")) {
					sql += " and a.canal_name like '%" + canalName + "%' ";
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
			}

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
