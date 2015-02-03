package com.cqupt.canalManage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import sun.jdbc.rowset.CachedRowSet;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;

public class AgentNameQueryActionRough extends ActionSupport{

	private static final long serialVersionUID = 1L;
	HttpServletRequest request=null;	
	
	public String execute(){
			request=ServletActionContext.getRequest();
			String cityId = request.getSession().getAttribute("cityId").toString();
			
			HttpServletResponse response=ServletActionContext.getResponse();
	           //设置字符集    
           response.setCharacterEncoding("UTF-8");    
           PrintWriter out;
			try {
				out = response.getWriter();
				   //直接输入响应的内容    
		        out.println(getList(cityId));    
		        out.flush();    
		        out.close();    
			} catch (IOException e) {
				e.printStackTrace();
			}    

	       return null;//不需要跳转某个视图 因为上面已经有了直接输出的响应结果    

	}
	
	public String getList(String cityId){
		String resultStr = "";
		String sql = "";
		DataStormSession session = null;
		CachedRowSet rowset = null;
		StringBuffer sb =new StringBuffer();
		try{
			session = DataStormSession.getInstance();
			
				sql = "select t.agent_name label,t.agent_name value,t.agent_id id from qdzc.agent_information t ";			
	
			JSONArray jsonObject = JSONArray.fromObject(session.findSql(sql));
			resultStr = jsonObject.toString();
		
		}catch (CquptException ce) {
			ce.printStackTrace();
		}finally {
			if (session != null) {
				try {
					session.closeSession();
				} catch (CquptException e) {
					e.printStackTrace();
				}
			}
		}
System.out.println("resultStr:"+resultStr);
		return resultStr;

	}

}

