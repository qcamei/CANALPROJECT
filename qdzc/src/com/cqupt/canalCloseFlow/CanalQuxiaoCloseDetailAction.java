package com.cqupt.canalCloseFlow;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class CanalQuxiaoCloseDetailAction extends ActionSupport {
	// 渠道经理取消关闭渠道的申请处理函数
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = ServletActionContext.getResponse();

	public String execute() {
		this.request = ServletActionContext.getRequest();
		String closeId = request.getParameter("closeId").toString();
		String inId = request.getParameter("inId").toString();

		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.print(getResult(closeId, inId));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getResult(String closeId, String inId) {
		request = ServletActionContext.getRequest();

		String resultStr = "";
		DataStormSession session = null;
		try {

			session = DataStormSession.getInstance();
			String sql = "";
			sql = "update   qdzc.canal_infomation_close set audit_state='取消关闭' where close_id='"
					+ closeId + "' ";
			logger.info("申请关闭信息" + sql);
			session.update(sql);

			sql = "update qdzc.canal_infomation set canal_state ='正常' where in_id='"
					+ inId + "'";

			logger.info("修改渠道状态表：" + sql);
			session.update(sql);

			sql = "update  qdzc.canal_step_state_close  set in_id=0+'" + inId
					+ "'  where in_id='" + inId + "'";
			logger.info("修改步骤信息" + sql);
			session.update(sql);

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
