package com.cqupt.search;

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

public class SearchCanalTypeCount extends ActionSupport {
	//
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	Logger logger = Logger.getLogger(getClass());

	public String execute() {
		logger.info("ServiceQueryAction:)");
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			// 直接输入响应的内容
			out.print(getDate());
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
			String operUser = request.getSession().getAttribute("userName")
					.toString();

			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();
			String individualAuth = request.getSession()
					.getAttribute("individualAuth").toString();
			String txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			String txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");

			String countClass = DecodeUtils.decodeRequestString(request
					.getParameter("countClass"));
			String canalManager = DecodeUtils.decodeRequestString(request
					.getParameter("canalManager"));
			String canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			String canalName = DecodeUtils.decodeRequestString(request
					.getParameter("canalName"));

			String guaranteeAmount = DecodeUtils.decodeRequestString(request
					.getParameter("guaranteeAmount"));
			session = DataStormSession.getInstance();
			if (countClass.equals("所属地区")) {
				sql = "SELECT@rownum :=@rownum + 1 AS rownum,t.* FROM (SELECT @rownum := 0) r,(SELECT DISTINCT a.area_name name,COUNT(area_name) sum FROM qdzc.canal_infomation a where a.dept_id in ("
						+ dataAuthId + ") ";

				if (individualAuth.equals("selfOperator")) {
					// 渠道客户经理 只能看自己录入 的数据
					sql += " and a.canal_user_name = '" + operUser + "'";
				} else if (individualAuth.equals("OpenCanal")) {
					sql += " and a.canal_type like '%开放渠道%'";
				} else if (individualAuth.equals("Canal")) {
					sql += " and a.canal_type not like '%开放渠道%'";
				} else {// 管理员和分公司经理登录

				}

				if (!txtBeginDate.equals("")) {
					sql += " and a.oper_time>'" + txtBeginDate + " 00:00:00' ";
				}
				if (!txtEndDate.equals("")) {
					sql += "  and a.oper_time<'" + txtEndDate + " 23:59:59' ";
				}
				if (canalManager != null && !canalManager.equals("")) {
					sql += " and a.canal_manager like '%" + canalManager + "%'";
				}

				if (canalName != null && !canalName.equals("")) {
					sql += " and  a.canal_name like '%" + canalName + "%'";
				}
				if (canalId != null && !canalId.equals("")) {
					sql += " and  a.canal_id like '%" + canalId + "%'";
				}
				if (guaranteeAmount != null && !guaranteeAmount.equals("")) {
					sql += " and  a.guarantee_amount ='" + guaranteeAmount
							+ "'";
				}

				sql += " GROUP BY a.area_name) t";

			} else if (countClass.equals("管理属性")) {
				sql = "SELECT@rownum :=@rownum + 1 AS rownum,t.* FROM (SELECT @rownum := 0) r,(SELECT DISTINCT a.canal_type name,a.oper_time,a.canal_manager,a.canal_name,a.canal_id,a.guarantee_amount,COUNT(canal_type) sum FROM qdzc.canal_infomation a where a.dept_id in ("
						+ dataAuthId + ") ";

				if (individualAuth.equals("selfOperator")) {
					// 渠道客户经理 只能看自己录入 的数据
					sql += " and a.oper_user = '" + operUser + "'";
				} else if (individualAuth.equals("OpenCanal")) {
					sql += " and a.canal_type like '%开放渠道%'";
				} else if (individualAuth.equals("Canal")) {
					sql += " and a.canal_type not like '%开放渠道%'";
				} else {// 管理员和分公司经理登录

				}

				if (!txtBeginDate.equals("")) {
					sql += " and a.oper_time>'" + txtBeginDate + " 00:00:00' ";
				}
				if (!txtEndDate.equals("")) {
					sql += "  and a.oper_time<'" + txtEndDate + " 23:59:59' ";
				}
				if (canalManager != null && !canalManager.equals("")) {
					sql += " and a.canal_manager like '%" + canalManager + "%'";
				}

				if (canalName != null && !canalName.equals("")) {
					sql += " and  a.canal_name like '%" + canalName + "%'";
				}
				if (canalId != null && !canalId.equals("")) {
					sql += " and  a.canal_id like '%" + canalId + "%'";
				}
				if (guaranteeAmount != null && !guaranteeAmount.equals("")) {
					sql += " and  a.guarantee_amount ='" + guaranteeAmount
							+ "'";
				}

				sql += " GROUP BY a.canal_type) t";

			}

			logger.info("查询工单信息：" + sql);

			resultStr = JsonUtil.map2json(session.findSql(sql,
					Integer.parseInt(page), Integer.parseInt(pageSize)));
			session.closeSession();
			// }
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
