package com.cqupt.canalCloseFlow;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.Tools;
import com.opensymphony.xwork2.ActionSupport;
import common.Logger;

public class WriteCanalCloseContentAction extends ActionSupport {
	// 渠道经理添加申请关闭备注与添加数据库数据
	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();
		try {
			String inId = request.getParameter("inId");

			String refuseReason = request.getParameter("refuseReason");

			String deptName = request.getSession().getAttribute("deptName")
					.toString();
			String deptId = request.getSession().getAttribute("deptId")
					.toString();

			String userName = request.getSession().getAttribute("userName")
					.toString();
			if (refuseReason == null) {
				refuseReason = "";
			} else {
				refuseReason = java.net.URLDecoder
						.decode(refuseReason, "UTF-8");
				System.out.println(refuseReason);
			}
			// String userId = request.getSession().getAttribute("userId")
			// .toString();
			HttpServletResponse response = ServletActionContext.getResponse();
			// 设置字符集
			response.setCharacterEncoding("UTF-8");
			PrintWriter out;

			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getList(inId, refuseReason, deptName, deptId, userName));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	public String getList(String inId, String refuseReason, String deptName,
			String deptId, String userName) {
		String resultStr = "";
		String sql = "";
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			sql = "select * from qdzc.canal_infomation where in_id='" + inId
					+ "'";
			logger.info("查询详细信息" + sql);
			Date now = new Date();
			logger.info(now);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// HH表示24小时制
			// 如果换成hh表示12小时制
			String operTime = sdf.format(now);
			logger.info(operTime);

			List<Map<String, Object>> resultList = session.findSql(sql);
			Map<String, Object> resultMap = (Map<String, Object>) resultList
					.get(0);
			// 命名自动更换模式
			String canalId = (String) resultMap.get("canalId");
			String canalName = (String) resultMap.get("canalName");
			String canalState = (String) resultMap.get("canalState");
			String closeId = Tools.getOrderID();

			// sql =
			// "insert into qdzc.canal_infomation_close(close_id,user_name,dept_name,dept_id,audit_state,canal_id,canal_name,current_step_val,current_step,next_step_val,oper_time,in_id,refuse_reason,money,bmoney)values('"
			// + closeId
			// + "','"
			// + userName
			// + "','"
			// + deptName
			// + "','"
			// + deptId
			// + "','通过','"
			// + canalId
			// + "','"
			// + canalName
			// + "','受理','1','分公司审核 ','"
			// + operTime
			// + "','"
			// + inId
			// + "','"
			// + refuseReason + "','','')";

			sql = "insert into qdzc.canal_infomation_close(close_id,user_name,dept_name,dept_id,audit_state,canal_id,canal_name,current_step_val,current_step,next_step_val,oper_time,in_id,refuse_reason)values('"
					+ closeId
					+ "','"
					+ userName
					+ "','"
					+ deptName
					+ "','"
					+ deptId
					+ "','通过','"
					+ canalId
					+ "','"
					+ canalName
					+ "','受理','1','分公司审核 ','"
					+ operTime
					+ "','"
					+ inId
					+ "','"
					+ refuseReason + "')";
			session.add(sql);
			// 添加流程状态表
			sql = "insert into qdzc.canal_step_state_close(in_id,canal_id,canal_name,oper_user,dept_id,dept_name,oper_time,current_step,parall_step,is_back,back_to_stepno)"
					+ "values('"
					+ inId
					+ "','"
					+ canalId
					+ "','"
					+ canalName
					+ "','"
					+ userName
					+ "','"
					+ deptId
					+ "',"
					+ "'"
					+ deptName
					+ "','" + operTime + "','1',' ',' ',' ')";
			logger.info("添加流程状态表：" + sql);
			session.add(sql);
			System.out.println(sql + "110");
			// 修改渠道状态表
			sql = "update qdzc.canal_infomation set canal_state ='暂停 ' where in_id='"
					+ inId + "'";

			logger.info("修改渠道状态表：" + sql);
			session.update(sql);

			sql = "select oper_user from qdzc.canal_infomation where  in_id = '"
					+ inId + "'";
			List list2 = session.findSql(sql);
			Map map = (Map) list2.get(0);
			String user = map.get("operUser").toString();
			// String canalName = map.get("canalName").toString();
			// 分公司审核-->分管部门审核
			// MailRemindUtil.mailFGS(user, canalName);

			session.closeSession();
			resultStr = "success";

		} catch (Exception e) {
			resultStr = "error";
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