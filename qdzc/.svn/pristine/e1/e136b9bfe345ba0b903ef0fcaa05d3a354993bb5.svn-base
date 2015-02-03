package com.cqupt.sysManage.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class OrganizationListQueryAction extends ActionSupport{
		Logger logger = Logger.getLogger(getClass());
		private static final long serialVersionUID = 1794195924380050122L;
		HttpServletRequest request=null;

		
		
		private InputStream inputStream;
		public InputStream getInputStream() {
			return inputStream;
		}

		
		public String execute()
		{
			logger.info("OrganizationListQueryAction:");
			this.request = ServletActionContext.getRequest();
			String sessionDeptId = request.getSession().getAttribute("deptId").toString();
			String cityId = request.getSession().getAttribute("cityId").toString();
			String deptId = request.getParameter("deptId");
			if(deptId.equals("undefined")) {
				//若为初始化，则根据权限判断deptId
				
				deptId = "";

			} 
			

            String pageSize = request.getParameter("pagesize");
            String page = request.getParameter("page");
			HttpServletResponse response=ServletActionContext.getResponse();
	           //设置字符集    
	           response.setCharacterEncoding("UTF-8");    
	           PrintWriter out;
			try {
				out = response.getWriter();
				out.println(getDeptList(deptId,sessionDeptId,cityId,pageSize,page));    
		        out.flush();    
		        out.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    

	           //直接输入响应的内容    
	            

	        
	       return null;//不需要跳转某个视图 因为上面已经有了直接输出的响应结果    


		}
		
		
		private String getDeptList(String deptId,String sessionDeptId,String cityId,String pageSize,String page) {
			DataStormSession session = null;
			String resultStr = "";
			
			try 
			{   
				session = DataStormSession.getInstance();
				String sql="";
				
				
				
				sql = "SELECT @rownum:=@rownum+1 as rownum,a.* from (select t.dept_id,t.dept_name,t.parent_dept_id,t.company_name,t.area,t.dept_address," +
						"t.post_num,t.phone_num,t.email,t.contact_num,date_format(t.in_date, '%Y-%c-%d %H:%i:%s') in_date,t.oper_user_name,ifnull(t.remark,'') remark," +
						"t.dept_state,t.dept_level,s.dept_name parent_dept_name " +
						"FROM (select @rownum:=0) r,qdzc.sys_dept t,qdzc.sys_dept s where t.parent_dept_id=s.dept_id and t.dept_state = '可用' and t.city_id = '"+cityId+"'";
				if(!deptId.equals("")){
					sql += " and t.parent_dept_id='"+deptId+"'";
				}
					sql +=	" ) a where a.remark != '结点'";
				logger.info("部门列表："+sql);
				Map resultMap = session.findSql(sql, Integer.parseInt(page), Integer.parseInt(pageSize));

				resultStr = new JsonUtil().map2json(resultMap);	
				session.closeSession();
			    
			}catch (Exception e) 
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
