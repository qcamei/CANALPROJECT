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

public class AreaAddAction extends ActionSupport {
	
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=null;
	HttpServletResponse response = null;


	public String execute()
	{
		logger.info("AreaAddAction:");
		request = ServletActionContext.getRequest();

		
		try {
			String areaName = DecodeUtils.decodeRequestString(request.getParameter("areaName"));//终端类型ID
			String depId = request.getSession().getAttribute("deptId").toString().substring(0,3);
			
			
			response=ServletActionContext.getResponse();
			
			//设置字符集    
	        response.setCharacterEncoding("UTF-8");    
	        PrintWriter out;
	        out = response.getWriter();		 
	        out.print(saveToDatabase(areaName, depId));    
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
	
	private String saveToDatabase(String areaName, String depId) {
		
		DataStormSession session = null;
		String resultStr = "";
		String sql= "";
		List emptyTest = null;
		Map emptyTestCode = null;
		String areaRid = "";

		try{
			session = DataStormSession.getInstance();
			
			sql = "select * from sys_area where area_name = '" + areaName + "'";
			List<Map<String,Object>> resultList = session.findSql(sql);
			logger.info("ssize: "+resultList.size());
			if(resultList.size()==0){
				
				emptyTest = session.findSql("select max(area_rid) as area_rid from sys_area where area_rid like '"+depId+"___'");
				emptyTestCode = (Map)emptyTest.get(0);
				logger.info("emptyTestCode: "+emptyTestCode);
				areaRid = emptyTestCode.get("areaRid").toString();
				logger.info("areaRid0: "+areaRid);
				areaRid = areaRid.substring(3, 6);
				logger.info("areaRid1: "+areaRid);
				int rid = Integer.parseInt(areaRid)+1;
				logger.info("areaRid2: "+areaRid);
				if (rid < 1000) {
					if (rid < 10)
						areaRid =  "00" + rid;
					else if(rid < 100)
						areaRid =  "0" + rid;
					else 
						areaRid =  ""+rid;
				}
				logger.info("areaRid3: "+areaRid);
				areaRid = depId+areaRid;
				sql = "insert into sys_area(area_id,area_name,area_rid)" + 
						"  values(null,'"+areaName+"','" + areaRid + "')";
				logger.info("添加区域信息sql：" + sql);
				session.add(sql);						
				resultStr = "success";
			}else{
				resultStr = "区域: "+areaName+"已存在，不能重复插入";
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
		logger.info("AreaAddAction_resultStr: "+resultStr);
		return resultStr;

	}
}
