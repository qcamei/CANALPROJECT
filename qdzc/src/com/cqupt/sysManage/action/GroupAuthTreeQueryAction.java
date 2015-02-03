package com.cqupt.sysManage.action;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;
public class GroupAuthTreeQueryAction  extends ActionSupport{

		private static final long serialVersionUID = 1794195924380050122L;
		HttpServletRequest request=null;

		private InputStream inputStream;
		public InputStream getInputStream() {
			return inputStream;
		}

		
		public String execute()
		{
			String userGroupId ="";
			
			this.request = ServletActionContext.getRequest();
			userGroupId = request.getParameter("userGroupId").toString();

			//inputStream = new StringBufferInputStream("Hello World! This is a text string response from a Struts 2 Action.");
		    String str = new String(getChildGroup(userGroupId)) ;
		    try {
				inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return SUCCESS;


		}
		
		
		private String getChildGroup(String userGroupId) {
			DataStormSession session = null;
			StringBuilder resultStr = new StringBuilder();
			if(userGroupId.equals("init")) {
				//若为初始化，则根据权限判断deptId
				userGroupId = "1";
			} 
			try 
			{
				session = DataStormSession.getInstance();
				int childDeptIdLength = 0;
				//if(userGroupId.length()==1)
				//	childDeptIdLength = 2;
				//else
					childDeptIdLength = userGroupId.length()+2;
				
				String sql = "SELECT a.*,ifnull(child_total,0) child_total FROM "+
						     "(select * from qdzc.sys_user_group t WHERE t.group_id LIKE '"+userGroupId+"%' AND length(t.group_id)="+childDeptIdLength+" ORDER BY t.group_id) a" +
						     		" left join" +
						     		" (SELECT t.group_parent_id,COUNT(*) child_total "+
						     "from qdzc.sys_user_group t WHERE t.group_parent_id LIKE '"+userGroupId+"%' GROUP BY group_parent_id ) b" +
						     		" on a.group_id = b.group_parent_id";
System.out.println("sql:查询孩子"+sql);			
				List resultList = session.findSql(sql);
				if((resultList.size())==0)
				{
					resultStr.append("");
				} else {
					for(int i=0;i<resultList.size();i++) {
						Map resultMap = (Map)resultList.get(i);
						if( 0==i ){
							resultStr.append("<?xml version=\"1.0\"?> <tree>");
						}
						if(resultMap.get("childTotal").toString().equals("0")) {
							resultStr.append("<tree text=\""+resultMap.get("groupName")+"\"  action=\"javascript:changeList('"+resultMap.get("groupId")+":"+resultMap.get("groupName")+"')\"/>");
						} else {
							resultStr.append("<tree text=\""+resultMap.get("groupName")+"\" action=\"javascript:changeList('"+resultMap.get("groupId")+":"+resultMap.get("groupName")+"')\" src=\"groupAuthTreeQueryAction?userGroupId="+resultMap.get("groupId")+"\"   />");
						}
						
						
						if (i== (resultList.size()-1)) {
							resultStr.append("</tree>");
						}
						
					}
				} 
				session.closeSession();
			}		
			catch (Exception e) 
			{
				try {
					session.exceptionCloseSession();
				} catch (CquptException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			return resultStr.toString();
		}
		
}
