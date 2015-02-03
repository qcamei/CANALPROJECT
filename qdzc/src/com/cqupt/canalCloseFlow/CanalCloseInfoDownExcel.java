package com.cqupt.canalCloseFlow;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.cqupt.pub.util.ExcelServiceImpl;
import com.cqupt.pub.util.IExcelService;
import com.opensymphony.xwork2.ActionSupport;

public class CanalCloseInfoDownExcel extends ActionSupport {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());
	InputStream excelStream;
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String execute() {
		String resultStr = "";
		String sql = "";
		DataStormSession session = null;
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		// txtBeginDate,txtEndDate,closeId,userName,
		// canalName,canalId,nextStepVal
		try {
			String closeId = DecodeUtils.decodeRequestString(request
					.getParameter("closeId"));
			String canalName = DecodeUtils.decodeRequestString(request
					.getParameter("canalName"));
			String canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			String userName = DecodeUtils.decodeRequestString(request
					.getParameter("userName"));
			String nextStepVal = DecodeUtils.decodeRequestString(request
					.getParameter("nextStepVal"));
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();
			String txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			String txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");

			session = DataStormSession.getInstance();
			String fileName = "渠道导出列表.xls";
			try {
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + fileName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			IExcelService es = new ExcelServiceImpl();

			if (nextStepVal.equals("")) {
				nextStepVal = "分公司审核";
			} else if (nextStepVal.equals("全部")) {
				nextStepVal = "";
			}

			String[] title = { "序号", "工单号", "操作人员", "审核部门", "部门编码", "当前步骤",
					"当前状态", "下一步骤", "渠道编码", "渠道名称", "操作时间", "申请原因" };// excel工作表的标题
			String[] keys = { "rownum", "closeId", "userName", "deptName",
					"deptId", "currentStepVal", "auditState", "nextStepVal",
					"canalId", "canalName", "operTime", "refuseReason" };
			sql = "SELECT\n" + "	@rownum :=@rownum + 1 AS rownum,\n" + "	t.*\n"
					+ "FROM\n" + "	(SELECT @rownum := 0) r,\n" + "	(\n"
					+ "		SELECT\n" + "			a.close_id,\n" + "			a.user_name,\n"
					+ "			a.dept_name,\n" + "			a.dept_id,\n"
					+ "			a.current_step_val,\n" + "			a.audit_state,\n"
					+ "			a.next_step_val,\n" + "			a.canal_id,\n"
					+ "			a.canal_name,\n" + "			date_format(\n"
					+ "				a.oper_time,\n" + "				'%Y-%m-%d'\n"
					+ "			) oper_time,\n" + "			a.refuse_reason\n" + "		FROM\n"
					+ "			qdzc.canal_infomation_close a\n"
					+ " where a.dept_id in (" + dataAuthId + ") ";
			if (!txtBeginDate.equals("")) {
				sql += " and c.oper_time>'" + txtBeginDate + " 00:00:00' ";
			}
			if (!txtEndDate.equals("")) {
				sql += "  and c.oper_time<'" + txtEndDate + " 23:59:59' ";
			}
			if (userName != null && !userName.equals("")) {
				sql += " and  a.user_name like '%" + userName + "%'";
			}
			if (closeId != null && !closeId.equals("")) {
				sql += " and a.close_id like '%" + closeId + "%'";
			}
			if (canalName != null && !canalName.equals("")) {
				sql += " and  a.canal_name like '%" + canalName + "%'";
			}
			if (canalId != null && !canalId.equals("")) {
				sql += " and a.canal_id like '%" + canalId + "%'";
			}
			if (nextStepVal != null && !nextStepVal.equals("")) {
				sql += " and  a.next_step_val like '%" + nextStepVal + "%'";
			}
			sql += " order by a.close_id desc) t";
			logger.info("渠道列表导出excel：" + sql);

			excelStream = es.getExcelInputStream(title, keys, sql);
			resultStr = "excel";
		} catch (CquptException ce) {
			ce.printStackTrace();
		} finally {
			if (session != null) {
				try {
					session.closeSession();
				} catch (CquptException e) {
					e.printStackTrace();
				}
			}
		}
		return resultStr;
	}
}
