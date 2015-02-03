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

public class CanalAlterFilialeAuditInfoDownExcel extends ActionSupport {
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
			String nextStepVal = DecodeUtils.decodeRequestString(request
					.getParameter("nextStepVal"));
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
			String[] title = { "序号", "工单号", "当前状态", "下一状态", "是否驳回", "原因",
					"渠道编码", "渠道名称", "区域", "状态", "城乡", "管理属性", "分类名称", "上级渠道",
					"VPDN", "代理商编码", "归属代理商", "客户经理", "审核部门", "审核操作人", "审核时间",
					"审核状态", "审核备注" };// excel工作表的标题
			String[] keys = { "rownum", "inId", "currentStepVal",
					"nextStepVal", "isBack", "reason", "canalId", "canalName",
					"areaName", "canalState", "canalForm", "canalProperty",
					"canalType", "canalParentName", "broadbandAccount",
					"agentId", "agentName", "canalUserName", "deptName",
					"operUser", "operTime", "auditState", "auditRemark" };

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
