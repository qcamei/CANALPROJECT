package com.cqupt.sysManage.action;

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


public class GetsubStationQueryActionII {
	Logger logger = Logger.getLogger(this.getClass());
	HttpServletRequest request = null;
	

	public String execute() {
		request = ServletActionContext.getRequest();

		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			logger.info("GetsubStationQueryActionII: sssssssssssssssssssss");
			out = response.getWriter();
			// 直接输入响应的内容
			out.println(getData());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;// 不需要跳转某个视图 因为上面已经有了直接输出的响应结果

	}

	private String getData() {
		
		String resultStr = "";
		String sql = "";
		String depId = request.getSession().getAttribute("deptId").toString();
		
		String pageSize = request.getParameter("pagesize");
		String page = request.getParameter("page");
		
		
		DataStormSession session = null;
		
		try {
			
			
			session = DataStormSession.getInstance();
			
			sql = "SELECT @rownum :=@rownum + 1 AS rownum,concat(t1.area_id, ' ') AS area_id,t1.area_name,t2.area_name as area_namepr FROM(SELECT @rownum := 0) r,sys_area t1,sys_area t2 where 1=1";
			
			if(depId.length() != 1){
				sql += " and t2.area_rid like '"+depId.substring(0,3)+"___'and t1.area_rid like concat(t2.area_rid,'___')";
			}
			
					
			
			
			
			logger.info("得到支局信息: "+sql);
			
			resultStr = JsonUtil.map2json(session.findSql (sql, Integer
					.parseInt(page), Integer.parseInt(pageSize)));
			
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
		logger.info("resultStr:" + resultStr);
		return resultStr;
	}
}
