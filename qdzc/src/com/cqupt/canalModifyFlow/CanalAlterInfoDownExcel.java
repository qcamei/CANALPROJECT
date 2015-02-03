package com.cqupt.canalModifyFlow;

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

public class CanalAlterInfoDownExcel extends ActionSupport {
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
		String dataAuthId = request.getSession().getAttribute("dataAuthId")
				.toString();
		String individualAuth = request.getSession()
				.getAttribute("individualAuth").toString();
		String operUser = request.getSession().getAttribute("userName")
				.toString();
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
					"渠道编码", "渠道名称", "区域", "状态", "形态", "性质", "类型", "上级渠道",
					"归属代理商", "VPDN", "城乡标识", "客户经理", "联系电话", "申请操作人", "申请时间",
					"申请人部门" };// excel工作表的标题

			String[] keys = { "rownum", "inId", "currentStepVal",
					"nextStepVal", "isBack", "reason", "canalId", "canalName",
					"areaName", "canalState", "canalForm", "canalProperty",
					"canalType", "canalParentName", "agentName",
					"broadbandAccount", "urbanIdetity", "canalUserName",
					"canalUserPhone", "operUser", "operTime", "deptName" };

			sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(select t.*,a.step_val next_step_val,a.pre_step_val current_step_val from"
					+ " ( SELECT b.in_id,a.canal_id,a.canal_name,a.area_name,a.canal_state,a.canal_form,"
					+ "a.canal_property,a.canal_type,a.canal_parent_name,a.agent_name,a.canal_user_name,"
					+ "a.canal_user_phone,a.canal_manager,a.broadband_account,a.urban_idetity,a.oper_user,a.dept_id,a.dept_name,"
					+ "date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,b.current_step,b.is_back,b.reason"
					+ " FROM qdzc.canal_infomation a right join qdzc.canal_step_state_his b on a.canal_id=b.canal_id "
					+ " where a.dept_id in ("
					+ dataAuthId
					+ ") and a.canal_state='正常' AND a.canal_id <>'' ";

			if (individualAuth.equals("selfOperator")) {
				// 渠道客户经理 只能看自己录入 的数据
				sql += " and a.canal_user_name = '" + operUser + "'";

			}

			// 以下为查询筛选条件
			if (!txtBeginDate.equals("")) {
				sql += " and t.oper_time>'" + txtBeginDate + " 00:00:00' ";
			}
			if (!txtEndDate.equals("")) {
				sql += "  and t.oper_time<'" + txtEndDate + " 23:59:59' ";
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
				sql += " and b.in_id like '%" + inId + "%' ";
			}
			if (!canalId.equals("")) {
				sql += " and a.canal_id like '%" + canalId + "%' ";
			}

			sql += " ORDER BY a.in_id DESC) as t  left join"
					+ " (select b.*,a.step_key pre_step_key,a.step_val pre_step_val from qdzc.step_info_modify a left join qdzc.step_info_modify b on a.step_no = b.pre_step_no) a"
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
