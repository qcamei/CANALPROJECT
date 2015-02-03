package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.Md;
import com.opensymphony.xwork2.ActionSupport;

public class GroupAuthModifyAction extends ActionSupport{
	
	private static final long serialVersionUID = -6941574699319426537L;

		HttpServletRequest request=null;
		private Md md5fun = new Md();
		Logger logger = Logger.getLogger(this.getClass());

		public String execute()
		{
			
System.out.println("permissionModifyAction");	

			this.request = ServletActionContext.getRequest();
			String resultStr = "";
			
			try {								
				String groupId = java.net.URLDecoder.decode(request.getParameter("groupId"), "UTF-8");
				
System.out.println("groupId"+groupId);

				HttpServletResponse response=ServletActionContext.getResponse();
		           //�����ַ�    
		        response.setCharacterEncoding("UTF-8");    
		        PrintWriter out;
		        out = response.getWriter();
				
				Reader reader = request.getReader();
				DataStormSession session = null;
				
				String sql;
				
				
				
		        try {
		        	session = DataStormSession.getInstance();
		        	
		        	sql = "select * from qdzc.sys_user_group_oper_auth  where group_id='"+groupId+"'";
		        	logger.info(sql+"+++++++++");
					List resultListCode3 = session.findSql(sql);
		        	if(resultListCode3.size()!=0){
		        	sql = "DELETE FROM  qdzc.sys_user_group_oper_auth   WHERE group_id = '"+groupId+"'";
System.out.println("sql="+sql);
					session.delete(sql);}
					
					
		        	SAXReader saxReader = new SAXReader();
		            Document document = saxReader.read(reader, "UTF-8");
		            Element root = document.getRootElement();
		            List elementList = root.elements("nodeId");
		          
		            String menuId = "";
		            for(int i=0; i<elementList.size(); i++) {
		            	Element element = (Element) elementList.get(i);
		            	menuId = element.getTextTrim();
System.out.println("menuId="+menuId);
                        sql = "INSERT INTO qdzc.sys_user_group_oper_auth(group_id,menuid) VALUES('"+groupId+"','"+menuId+"')";
System.out.println("sql+++++++: "+sql);
                        session.add(sql); 
		              }
		         		            
		            session.closeSession();
		            resultStr = "success";
				} catch (Exception ex) {
					resultStr = "error";
					try {
						session.exceptionCloseSession();
					}catch (CquptException e1) {
						e1.printStackTrace();
					}
					ex.printStackTrace();
												           
		        }
				
		        out.flush();    
		        out.close();         
			} 			
			catch (UnsupportedEncodingException e1) {				
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	           //ֱ��������Ӧ������    	        
	       return null;//����Ҫ��תĳ����ͼ ��Ϊ�����Ѿ�����ֱ���������Ӧ���    
		}					
		
}
