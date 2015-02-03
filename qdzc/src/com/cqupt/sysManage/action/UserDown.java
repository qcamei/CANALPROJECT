package com.cqupt.sysManage.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户管理模块的用户导出
 * 
 * @author Yang
 * @version 1.0 2014-11-1 上午8:52:08
 */

public class UserDown extends ActionSupport {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());
	InputStream excelStream;
	HttpServletRequest request = null;

	public InputStream getExcelFile() {
		DataStormSession session = null;
		request = ServletActionContext.getRequest();
		String queryDeptId = request.getParameter("queryDeptId");
		try {

			if (queryDeptId != null && queryDeptId != "") {
				queryDeptId = java.net.URLDecoder.decode(queryDeptId, "UTF-8");
			} else {
				queryDeptId = "SELECT DISTINCT m.dept_id from qdzc.sys_user m";
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");

		Workbook book = new HSSFWorkbook();
		ByteArrayOutputStream baos = null;
		Sheet sheet = book.createSheet("用户个人信息表");
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("登录名");
		row.createCell(1).setCellValue("用户姓名");
		row.createCell(2).setCellValue("用户部门");
		row.createCell(3).setCellValue("用户所属组");
		row.createCell(4).setCellValue("状态");
		row.createCell(5).setCellValue("用户联系方式");
		row.createCell(6).setCellValue("邮箱");
		CellStyle sty = book.createCellStyle();

		String sql = "select m.user_id , m.user_name , m.dept_name , m.group_name , m.user_state , m.phone_num , m.user_email "
				+ " from qdzc.sys_user m "
				+ "where  m.dept_id in ("
				+ queryDeptId + ")  ";

		logger.info("用户信息Excel:" + sql);
		try {
			session = DataStormSession.getInstance();
			Map map = session.executeSQL(sql);
			List list = (List) map.get("resultList");
			for (int i = 1; i < list.size(); i++) {
				Map resultMap = (Map) list.get(i);
				row = sheet.createRow(i);
				row.createCell(0)
						.setCellValue((String) resultMap.get("userId"));
				row.createCell(1).setCellValue(
						(String) resultMap.get("userName"));
				row.createCell(2).setCellValue(
						(String) resultMap.get("deptName"));
				row.createCell(3).setCellValue(
						(String) resultMap.get("groupName"));
				row.createCell(4).setCellValue(
						((String) resultMap.get("userState")));
				row.createCell(5).setCellValue(
						(String) resultMap.get("phoneNum"));
				row.createCell(6).setCellValue(
						(String) resultMap.get("userEmail"));
			}

			baos = new ByteArrayOutputStream();
			book.write(baos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] ba = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);
		return bais;
	}

	public String execute() throws Exception {
		return super.execute();
	}
}
