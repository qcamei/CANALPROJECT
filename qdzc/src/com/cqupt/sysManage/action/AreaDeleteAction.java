package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;

public class AreaDeleteAction {
	HttpServletRequest request=null;
	Logger logger = Logger.getLogger(this.getClass());
	
	public String execute()
	{
		logger.info("CellphoneFailureDeleteAction:)");
		this.request = ServletActionContext.getRequest();
		request = ServletActionContext.getRequest();
		try {
			String txtID = DecodeUtils.decodeRequestString(request.getParameter("txtID"));//型号id
			HttpServletResponse response=ServletActionContext.getResponse();
	        //设置字符集    
	        response.setCharacterEncoding("UTF-8");    
	        PrintWriter out;
	        out = response.getWriter();
			out.print(delteBrand(txtID));    
	        out.flush();    
	        out.close();  
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		} 
       return null;//不需要跳转某个视图 因为上面已经有了直接输出的响应结果    


	}
	
	
	private String delteBrand(String txtID) {
		DataStormSession session = null;
		String resultStr = "";
		String sql= "";
		try {
			session = DataStormSession.getInstance();
			String[] saleIdArray = txtID.split(";");
			for(int i=0;i<saleIdArray.length;i++){
					sql = "delete from qdzc.sys_area where area_id='"+saleIdArray[i]+"'";
					logger.info("故障删除sql: "+sql);
					session.delete(sql);
					resultStr = "success";			
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
		return resultStr;

	}
}
