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

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.Md;
import com.opensymphony.xwork2.ActionSupport;

public class GroupAuthAddAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6941574699319426537L;

	/**
		 * 
		 */
		HttpServletRequest request=null;
		private Md md5fun = new Md();

		public String execute()
		{
			
			

			this.request = ServletActionContext.getRequest();
			
			try {
				String roleName = java.net.URLDecoder.decode(request.getParameter("roleName"), "UTF-8");
				String roleDesc = java.net.URLDecoder.decode(request.getParameter("roleDesc"), "UTF-8");
				String hidGroupId = java.net.URLDecoder.decode(request.getParameter("hidGroupId"), "UTF-8");
				String txtDataAuthIDList = java.net.URLDecoder.decode(request.getParameter("txtDataAuthIDList"), "UTF-8");
				String txtProdAuthIDList = java.net.URLDecoder.decode(request.getParameter("txtProdAuthIDList"), "UTF-8");
System.out.println("roleName"+roleName);
System.out.println("roleDesc"+roleDesc);
System.out.println("hidGroupId"+hidGroupId);
System.out.println("txtDataAuthIDList"+txtDataAuthIDList);
System.out.println("txtProdAuthIDList"+txtProdAuthIDList);
				HttpServletResponse response=ServletActionContext.getResponse();
		           //�����ַ�    
		        response.setCharacterEncoding("UTF-8");    
		        PrintWriter out;
		        out = response.getWriter();
				//out.print(insertPermissioninfo(roleName, roleDesc, hidGroupId));   
				
				Reader reader = request.getReader();
		        try {
		        	SAXReader saxReader = new SAXReader();
		            Document document = saxReader.read(reader, "UTF-8");
		            Element root = document.getRootElement();
		            List elementList = root.elements("nodeId");
		            List nodeIdList = new ArrayList();
		            for(int i=0; i<elementList.size(); i++) {
		            	Element element = (Element) elementList.get(i);
		 System.out.println("element.getTextTrim()+"+element.getTextTrim());
		            	nodeIdList.add(element.getTextTrim());
		            }
		            insertPermissioninfo(nodeIdList, roleName, roleDesc, hidGroupId, txtDataAuthIDList, txtProdAuthIDList);
		           /* Role role = new Role();
		            role.setRoleName(roleName);
		            role.setRoleDesc(roleDesc);
		            
		            RoleDao dao = new RoleDao();
		            dao.saveRole(role, nodeIdList,dataAuthIdList);
		        	wr.close();*/
				} catch (Exception ex) {
					ex.printStackTrace();
		            /*wr.write(this.xmlCreater.createExXml(ex));
		            wr.close();*/
		        }
				
				
				
				
		        out.flush();    
		        out.close();  
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	           //ֱ��������Ӧ������    	        
	       return null;//����Ҫ��תĳ����ͼ ��Ϊ�����Ѿ�����ֱ���������Ӧ���    


		}
		
		
		private String insertPermissioninfo(List nodeIdList, String roleName, String roleDesc, String hidGroupId, String txtDataAuthIDList, String txtProdAuthIDList) {
			DataStormSession session = null;
			String resultStr = "";
			String sql= "";
			
			try {
				session = DataStormSession.getInstance();
				//�����ɫ��
				if(hidGroupId.equals("1")) {
					sql = "select nvl(MAX(t.group_id)+1,0) group_id from sys_user_group t WHERE t.group_id LIKE '"+hidGroupId+"%' AND length(t.group_id) = 3";
				} else {
					sql = "select nvl(MAX(t.group_id)+1,0) group_id from sys_user_group t WHERE t.group_id LIKE '"+hidGroupId+"%' AND length(t.group_id) = length('"+hidGroupId+"')+ 3";
				} 
				
				List resultList = session.findSql(sql);
				Map resultMap = (Map)resultList.get(0);
				String groupId = resultMap.get("groupId").toString();
				if(groupId.equals("0"))
					groupId = hidGroupId+"001";
System.out.println("groupId"+groupId);					
			    //�����ɫ��
				sql = "INSERT INTO sys_user_group VALUES('"+roleName+"','"+groupId+"','"+hidGroupId+"')";
				session.add(sql);	
				//�������Ȩ��
				String[] txtDataAuthIDListArr = txtDataAuthIDList.split(";");
				for(int i = 0; i < txtDataAuthIDListArr.length; i++) {
					sql = "INSERT INTO sys_user_group_data_auth VALUES('"+txtDataAuthIDListArr[i]+"','"+groupId+"')";
					session.add(sql);
				}	
				//���빦��Ȩ��
				for(int i = 0; i < nodeIdList.size(); i++) {
					sql = "INSERT INTO sys_user_group_oper_auth VALUES('"+groupId+"','"+nodeIdList.get(i)+"')";
					session.add(sql);
				}
				//�����ƷȨ��
				String[] txtProdAuthIDListArr = txtProdAuthIDList.split(";");
				for(int i = 0; i < txtProdAuthIDListArr.length; i++) {
					sql = "INSERT INTO sys_user_group_prod_auth VALUES('"+groupId+"','"+txtProdAuthIDListArr[i]+"')";
					session.add(sql);
				}
			
					
				
				session.closeSession();
				resultStr = "success";
			
				
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
