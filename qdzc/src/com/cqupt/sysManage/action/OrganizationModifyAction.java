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

public class OrganizationModifyAction extends ActionSupport{

		private static final long serialVersionUID = 1794195924380050122L;
		HttpServletRequest request=null;

	
		public String execute()
		{
			this.request = ServletActionContext.getRequest();
			String deptId = request.getParameter("deptId");
			String cityId = request.getSession().getAttribute("cityId").toString();

			
			DataStormSession session = null;
			try 
			{
				session = DataStormSession.getInstance();
				String sql = "";
				sql = "SELECT @rownum:=@rownum+1 as rownum,a.* from (select t.dept_id,t.dept_name,t.parent_dept_id,t.company_name,t.area,t.dept_address," +
						"t.post_num,t.phone_num,t.email,t.contact_num,date_format(t.in_date, '%Y-%c-%d %H:%i:%s') in_date,t.oper_user_name,ifnull(t.remark,'') remark," +
						"t.dept_state,t.dept_level,s.dept_name parent_dept_name " +
						"FROM (select @rownum:=0) r,qdzc.sys_dept t,qdzc.sys_dept s where t.parent_dept_id=s.dept_id and t.dept_state = '可用' and t.city_id = '"+cityId+"'"+
						" and t.dept_id='"+deptId+"' ) a where a.remark != '结点'";
				
System.out.println("修改部门时的查询："+sql);
				List resultList = session.findSql(sql);
				Map resultMap = (Map)resultList.get(0);
				//dept_id,dept_name,parent_dept_id,area,dept_address,phone_num,
				//email,dept_state,company_name,post_num,remark
				request.setAttribute("deptName", resultMap.get("deptName"));
				request.setAttribute("deptId", resultMap.get("deptId"));
				request.setAttribute("parentDeptId", resultMap.get("parentDeptId"));
				request.setAttribute("parentDeptName", resultMap.get("parentDeptName"));
				request.setAttribute("area", resultMap.get("area"));
				request.setAttribute("deptAddress", resultMap.get("deptAddress"));
				request.setAttribute("phoneNum", resultMap.get("phoneNum"));
				request.setAttribute("contactNum", resultMap.get("contactNum"));
				request.setAttribute("email", resultMap.get("email"));
				request.setAttribute("deptState", resultMap.get("deptState"));
				request.setAttribute("deptLevel", resultMap.get("deptLevel"));
				
				request.setAttribute("postNum", resultMap.get("postNum"));
				request.setAttribute("remark", resultMap.get("remark"));
				
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
			            
       
	        
	       return "modify";


		}
		
		
}
