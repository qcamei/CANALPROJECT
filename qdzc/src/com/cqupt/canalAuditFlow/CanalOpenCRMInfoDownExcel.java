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

public class CanalOpenCRMInfoDownExcel extends ActionSupport {
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
				nextStepVal = "开CRM工号";
			} else if (nextStepVal.equals("全部")) {
				nextStepVal = "";
			}

			// { display: '工单号', name: 'inId',width: 150, minWidth: 60 },
			// { display: '当前状态', name: 'currentStepVal',width: 60, minWidth: 60
			// },
			// { display: '下一状态', name: 'nextStepVal',width: 60, minWidth: 60 },
			// { display: '下一状态ID', name: 'currentStep',width: 10, minWidth: 60
			// ,hide:1},
			// { display: '是否驳回', name: 'isBack', width: 70, minWidth: 60 ,
			// render: function (row){
			// if (row.isBack == '驳回')
			// return '<font color="#ff0000">'+row.isBack+'</font>';
			// else return row.isBack;
			// }},
			//
			// { display: '驳回原因', name: 'reason', width: 70, minWidth: 60 },
			// { display: '渠道编码', name: 'canalId', width: 70, minWidth: 60 },
			// { display: '翼支付帐号', name: 'inCollectNumber', width: 80, minWidth:
			// 60 },
			// { display: '渠道名称', name: 'canalName', width: 80, minWidth: 60 },
			// { display: '区域', name: 'areaName', width: 80, minWidth: 60 },
			// { display: '状态', name: 'canalState', width: 50, minWidth: 60 },
			// { display: '城乡', name: 'canalForm', width: 50, minWidth: 60 },
			// { display: '管理属性', name: 'canalProperty', width: 50, minWidth: 60
			// },
			// { display: '分类名称', name: 'canalType', width: 50, minWidth: 60 },
			// { display: '客户经理', name: 'canalUserName', width: 80, minWidth: 60
			// },
			// { display: 'VPDN', name: 'broadbandAccount', width: 80, minWidth:
			// 60 },
			// { display: '上级渠道', name: 'canalParentName', width: 80, minWidth:
			// 60 },
			// { display: '归属代理商', name: 'agentName', width: 80, minWidth: 60 },
			// { display: '工号权限', name: 'privilege', width: 80, minWidth: 60
			// ,hide:1},
			//
			// { display: 'CRM工号', name: 'crmNumber', width: 50, minWidth: 60 },
			// { display: '绑定手机', name: 'telephone', width: 80, minWidth: 60 },
			//
			// { display: '操作部门', name: 'deptName', width: 60, minWidth: 60 },
			// { display: '操作人', name: 'operUser', width: 60, minWidth: 60},
			// { display: '操作时间', name: 'operTime', width: 100, minWidth: 60 },
			// { display: '备注', name: 'remark', width: 50, minWidth: 60 }
			//

			String[] title = { "序号", "工单号", "当前状态", "下一状态", "是否驳回", "驳回原因",
					"渠道编码", "翼支付账号", "渠道名称", "区域", "状态", "城乡", "管理属性", "分类名称",
					"客户经理", "VPDN", "上级渠道", "归属代理商", "工号权限", "CRM工号", "绑定手机",
					"操作部门", "操作人", "操作时间", "备注" };// excel工作表的标题
			String[] keys = { "rownum", "inId", "currentStepVal",
					"nextStepVal", "isBack", "reason", "canalId",
					"inCollectNumber", "canalName", "areaName", "canalState",
					"canalForm", "canalProperty", "canalType", "canalUserName",
					"broadbandAccount", "canalParentName", "agentName",
					"privilege", "crmNumber", "telephone", "deptName",
					"operUser", "operTime", "remark" };
			sql = "SELECT 	@rownum :=@rownum + 1 AS rownum,t.* from (select @rownum:=0) r,(select t.*,a.crm_number,a.telephone,a.employee_name,a.oper_user,a.dept_name,a.remark,date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') check_time from "
					+ " (select t.*,a.step_val next_step_val,a.pre_step_val current_step_val from"
					+ " ( SELECT a.in_id,a.canal_id,a.canal_name,a.area_name,a.canal_state,a.canal_form,"
					+ "a.canal_property,a.canal_type,a.canal_parent_name,a.agent_name,a.canal_user_name,"
					+ "a.canal_user_phone,a.privilege,a.broadband_account,a.in_collect_number,a.urban_idetity,"
					+ "date_format(a.oper_time,'%Y-%m-%d %H:%i:%s') oper_time,b.current_step,b.is_back,b.reason"
					+ " FROM qdzc.canal_infomation a left join qdzc.canal_step_state b on a.in_id=b.in_id "
					+ " where a.dept_id in (" + dataAuthId + ")";

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
					+ " (select b.*,a.step_key pre_step_key,a.step_val pre_step_val from qdzc.step_info a left join qdzc.step_info b on a.step_no = b.pre_step_no) a"
					+ " on t.current_step = a.pre_step_no) t "
					+ " left join qdzc.process5_open_CRM a on t.in_id=a.in_id where 1=1";
			if (!txtBeginDate.equals("")) {
				sql += " and t.oper_time>'" + txtBeginDate + " 00:00:00' ";
			}
			if (!txtEndDate.equals("")) {
				sql += "  and t.oper_time<'" + txtEndDate + " 23:59:59' ";
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
