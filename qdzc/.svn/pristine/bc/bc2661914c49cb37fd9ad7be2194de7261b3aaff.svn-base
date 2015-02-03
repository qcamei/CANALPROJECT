package com.cqupt.sysManage.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AreaUpdateAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=null;
	HttpServletResponse response = null;


	public String execute()
	{
		logger.info("FailureTypeUpdateAction：");
		request = ServletActionContext.getRequest();
	
		try {
			String areaName = DecodeUtils.decodeRequestString(request.getParameter("areaName"));//区域名称
			String areaId = DecodeUtils.decodeRequestString(request.getParameter("areaId"));//区域id
		
			
			response=ServletActionContext.getResponse();
			
			//设置字符集    
	        response.setCharacterEncoding("UTF-8");    
	        PrintWriter out;
	        out = response.getWriter();		 
	        out.print(saveToDatabase(areaId, areaName));    
	        out.flush();    
	        out.close();  
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		} 
           //直接输入响应的内容    	        
       return null;//不需要跳转某个视图 因为上面已经有了直接输出的响应结果    


	}
	
	private String saveToDatabase(String areaId, String areaName) {
		DataStormSession session = null;
		String resultStr = "";
		String sql= "";
		
		try{
			session = DataStormSession.getInstance();
			sql = "select * from sys_area where area_name = '"+areaName+"'";
			List<Map<String,Object>> resultList = session.findSql(sql);
			if(resultList.size()==0){
				sql = "update qdzc.sys_area set area_name='" + areaName +"'where area_id=" + areaId;
				logger.info("修改区域信息：" + sql);
				session.add(sql);						
				resultStr = "success";
			}else{
				resultStr = "此区域已存在，不能重复";
			}
			session.closeSession();
		}catch (Exception e) {
			resultStr = "error";
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		logger.info(resultStr);
		return resultStr;

	}
}
