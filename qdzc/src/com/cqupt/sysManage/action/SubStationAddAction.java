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

public class SubStationAddAction extends ActionSupport {
	
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=null;
	HttpServletResponse response = null;

	public String execute()
	{
		logger.info("VersionAddAction:");
		request = ServletActionContext.getRequest();
	
		
		try {
			String areaId = DecodeUtils.decodeRequestString(request.getParameter("areaId"));//所属区县ID
			String subStationName = DecodeUtils.decodeRequestString(request.getParameter("subStationName"));//支/分局名称
			
			logger.info("##@!#@!#!#!@: "+areaId);
			response=ServletActionContext.getResponse();
			String userId = request.getSession().getAttribute("userId").toString();//操作员
			//设置字符集    
	        response.setCharacterEncoding("UTF-8");    
	        PrintWriter out;
	        out = response.getWriter();		 
	        out.print(saveToDatabase(areaId, subStationName));    
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
	
	private String saveToDatabase(String areaId, String subStationName) {
		DataStormSession session = null;
		String resultStr = "";
		String sql= "";
		List emptyTest = null;
		Map emptyTestCode = null;
		String tempId = "";
		String tempId2 = "";
		int numId ;
		try{
			session = DataStormSession.getInstance();
			
			sql = "select * from qdzc.sys_area where area_name='"+subStationName+"'";
			List<Map<String,Object>> resultList = session.findSql(sql);
			logger.info("-----");
			if(resultList.size()==0){
				logger.info("-----++++++++++");
				sql = "select max(area_rid) as area_rid from sys_area where area_rid like concat((select area_rid  from sys_area where area_id = '"+areaId+"'),'___')";
				logger.info("sql:   "+sql);
				emptyTest = session.findSql(sql);
				logger.info("emptyTest.size(): "+emptyTest.size());
				if(emptyTest.size()!=0)
				{
					emptyTestCode = (Map)emptyTest.get(0);
					logger.info("emptyTestCode: "+emptyTestCode);
					if(emptyTestCode.get("areaRid")!=null){
						tempId = emptyTestCode.get("areaRid").toString();
					}else{
						sql = "select area_rid  from sys_area where area_id = '"+areaId+"'";
						emptyTest = session.findSql(sql);
						emptyTestCode = (Map)emptyTest.get(0);
						tempId2 = emptyTestCode.get("areaRid").toString();
					}
					logger.info("tempId: "+tempId);
				}
				if(!tempId.equals("")){
					numId = Integer.parseInt(tempId)+1;
					sql = "insert into qdzc.sys_area(area_id,area_name,area_rid)" + 
							"  values(null,'"+subStationName + "','"+numId+"')";
					
					logger.info("添加支/分局信息：" + sql);
					session.add(sql);						
					resultStr = "success";
				}else{
					
					logger.info("tempIddsadsadsa: "+tempId);
					tempId2 = tempId2 + "001";
					sql = "insert into qdzc.sys_area(area_id,area_name,area_rid)" + 
							"  values(null,'"+subStationName + "','"+tempId2+"')";
					
					logger.info("添加支/分局信息：" + sql);
					session.add(sql);						
					resultStr = "success";
				}
				
				/*
				sql = "insert into qdzc.sys_area(area_id,area_name,area_rid)" + 
						"  values(null,'"+subStationName + "','"+tempId+"')";
				
				logger.info("添加支/分局信息：" + sql);
				session.add(sql);						
				resultStr = "success";
				*/
			}else{
				resultStr = "支局: "+subStationName+"已存在，不能重复插入";
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
		logger.info("VersionAddAction_resultStr: "+resultStr);
		return resultStr;

	}
}
