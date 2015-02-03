package com.cqupt.sysManage.action;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;
public class GroupAuthListQueryAction extends ActionSupport{


private static final long serialVersionUID = 5094474016721792339L;

	HttpServletRequest request=null;

	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}

	
	public String execute()
	{
		this.request = ServletActionContext.getRequest();
		String group = request.getParameter("group");
		DataStormSession session = null;

		
		if(group.equals("undefined")||group.equals("0")) {
			//若为初始化，则根据权限判断deptId
			group = "1";
		} 
System.out.println("group:"+group);
        String pageSize = request.getParameter("pagesize");
        String page = request.getParameter("page");
		HttpServletResponse response=ServletActionContext.getResponse();
           //设置字符集    
           response.setCharacterEncoding("UTF-8");    
           PrintWriter out;
		try {
			out = response.getWriter();
			out.println(getDeptList(group,pageSize,page));    
	        out.flush();    
	        out.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    

           //直接输入响应的内容    
            

        
       return null;//不需要跳转某个视图 因为上面已经有了直接输出的响应结果    


	}
	
	
	private String getDeptList(String group,String pageSize,String page) {
		DataStormSession session = null;
		String resultStr = "";
		try 
		{
			session = DataStormSession.getInstance();
			String sql="SELECT c.*,@rownum:=@rownum+1 as rownum FROM (select @rownum:=0) r," +
					"( select a.group_id,a.group_name,b.group_name parent_group_name  from " +
					"qdzc.sys_user_group a,qdzc.sys_user_group b WHERE a.group_parent_id = b.group_id" +
					" AND a.group_id LIKE '"+group+"%' and a.remark != '节点' ORDER BY a.group_id) c";	
System.out.println("查询角色："+sql);	
			Map resultMap = session.findSql(sql, Integer.parseInt(page), Integer.parseInt(pageSize));
			resultStr = new JsonUtil().map2json(resultMap);	
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
		return resultStr;

	}
	
}
