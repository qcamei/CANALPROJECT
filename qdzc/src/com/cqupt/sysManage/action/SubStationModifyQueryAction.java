package com.cqupt.sysManage.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class SubStationModifyQueryAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=null;
	
	public String execute()
	{
		logger.info("AreaModifyQueryAction:)");
		request = ServletActionContext.getRequest();
		String areaId = request.getParameter("areaId");
		
		DataStormSession session = null;
		try 
		{
			session = DataStormSession.getInstance();
			String sql = "select concat(area_id,' ') as area_id, area_name from sys_area where area_id='"+areaId+"'";
			logger.info("查询要修改区域的详细信息" + sql);
			List<Map<String, Object>> resultList = session.findSql(sql);
			Map<String, Object> resultMap = (Map<String, Object>)resultList.get(0);			
			
			request.setAttribute("areaId", resultMap.get("areaId"));
			request.setAttribute("areaName", resultMap.get("areaName"));
			
			
			
			session.closeSession();
		}		
		catch (Exception e) 
		{
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		            
       return "success";
       }
}
