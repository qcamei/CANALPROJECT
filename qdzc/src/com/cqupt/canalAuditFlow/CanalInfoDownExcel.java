package com.cqupt.canalAuditFlow;

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

public class CanalInfoDownExcel extends ActionSupport {
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
			String inId = DecodeUtils.decodeRequestString(request
					.getParameter("inId"));
			String canalName = DecodeUtils.decodeRequestString(request
					.getParameter("canalName"));
			String canalId = DecodeUtils.decodeRequestString(request
					.getParameter("canalId"));
			String areaName = DecodeUtils.decodeRequestString(request
					.getParameter("areaName"));
			String canalUserPhone = DecodeUtils.decodeRequestString(request
					.getParameter("canalUserPhone"));
			String dataAuthId = request.getSession().getAttribute("dataAuthId")
					.toString();
			String individualAuth = request.getSession()
					.getAttribute("individualAuth").toString();
			String txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			String txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");
			String userName = request.getSession().getAttribute("userName")
					.toString();
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
			txtBeginDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtBeginDate"), "全部", "");
			txtEndDate = DecodeUtils.decodeRequestString(
					request.getParameter("txtEndDate"), "全部", "");
			String[] title = { "序号", "工单号", "当前状态", "下一状态", "是否驳回", "驳回原因",
					"渠道编码", "工号", "翼支付账号", "渠道名称", "区域", "状态", "形态", "性质",
					"类型", "上级渠道", "归属代理商", "VPDN", "城乡标识", "客户经理", "联系电话",
					"申请操作人", "申请时间", "申请人部门" };// excel工作表的标题

			String[] keys = { "rownum", "inId", "currentStepVal",
					"nextStepVal", "isBack", "reason", "canalId", "crmNumber",
					"inCollectNumber", "canalName", "areaName", "canalState",
					"canalForm", "canalProperty", "canalType",
					"canalParentName", "agentName", "broadbandAccount",
					"urbanIdetity", "canalUserName", "canalUserPhone",
					"operUser", "operTime", "deptName" };

			sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(select t.*,a.step_val next_step_val,a.pre_step_val current_step_val from"
					+ " ( SELECT a.in_id,a.canal_id,a.crm_number,a.in_collect_number,a.canal_name,a.area_name,a.canal_state,a.canal_form,"
					+ "a.canal_property,a.canal_type,a.canal_parent_name,a.agent_name,a.canal_user_name,"
					+ "a.canal_user_phone,a.urban_idetity,a.broadband_account,a.oper_user,a.dept_id,a.dept_name,"
					+ "date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,b.current_step,b.is_back,b.reason"
					+ " FROM (SELECT m.*,n.crm_number FROM qdzc.canal_infomation m LEFT JOIN qdzc.process5_open_CRM n ON m.in_id = n.in_id) a left join qdzc.canal_step_state b on a.in_id=b.in_id "
					+ " where a.dept_id in (" + dataAuthId + ")";
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
					+ " on t.current_step = a.pre_step_no) t";

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