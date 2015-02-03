package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class OrganizationDeptUpdateAction extends ActionSupport{

   	Logger logger = Logger.getLogger(getClass());

		private static final long serialVersionUID = 1794195924380050122L;
		HttpServletRequest request=null;

		private InputStream inputStream;
		public InputStream getInputStream() {
			return inputStream;
		}
		
		public String execute()
		{
			logger.info("OrganizationDeptUpdateAction:");
			this.request = ServletActionContext.getRequest();

			String deptName,deptId;
			try {
				deptId = java.net.URLDecoder.decode(request.getParameter("deptId") , "UTF-8");
				deptName = java.net.URLDecoder.decode(request.getParameter("deptName") , "UTF-8");
				
				String hidParentDeptId = "";
				String isUseable = "";
				String contactNumber = "";
				String phoneNum = "";
				String address = "";
				String remark = "";
				String postNum = "";
				String email = "";
				String area = "";
				String deptLevel = "";
				
				String cityId = request.getSession().getAttribute("cityId").toString();
				String operUserName = request.getSession().getAttribute("userName").toString();
				try {
					if(request.getParameter("deptName")!=null){			
						deptLevel = DecodeUtils.decodeRequestString(request.getParameter("deptLevel"));
						
						deptName = DecodeUtils.decodeRequestString(request.getParameter("deptName"));
						
					
						if(hidParentDeptId != null && hidParentDeptId != "")
							hidParentDeptId = DecodeUtils.decodeRequestString(request.getParameter("hidParentDeptId"));
						else 
							hidParentDeptId = cityId;
						isUseable = DecodeUtils.decodeRequestString(request.getParameter("isUseable1"));
						contactNumber = DecodeUtils.decodeRequestString(request.getParameter("contactNumber"));
						phoneNum = DecodeUtils.decodeRequestString(request.getParameter("phoneNum"));
						address = DecodeUtils.decodeRequestString(request.getParameter("address"));
						remark = DecodeUtils.decodeRequestString(request.getParameter("remark"));
					
						postNum = DecodeUtils.decodeRequestString(request.getParameter("postNum"));
						email = DecodeUtils.decodeRequestString(request.getParameter("email"));
						area = DecodeUtils.decodeRequestString(request.getParameter("area"));	
							
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				HttpServletResponse response=ServletActionContext.getResponse();
		        response.setCharacterEncoding("utf-8");    
		        PrintWriter out;
		        out = response.getWriter();
		        out.print(updateDeptinfo(deptLevel,deptId,deptName , hidParentDeptId ,isUseable , contactNumber,phoneNum ,address ,
						remark , email,postNum,area,operUserName));    
			    out.flush();    
			    out.close();  
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	       return null;


		}
		
		private String updateDeptinfo(String deptLevel,String deptId,String deptName , String hidParentDeptId ,String isUseable ,
				String contactNumber,String phoneNum ,String address ,
				String  remark ,String email,String postNum,
				String area,String operUserName) {
			DataStormSession session = null;
			String resultStr = "";
			String sql = "";
			try 
			{
				session = DataStormSession.getInstance();
			//判断部门名称是否存在,存在则不能修改，不存在则可以修改.---不会每次都改部门名称，暂时不做重复检查
			/*	sql = "select *　from qdzc.sys_dept where dept_name='"+deptName+"'";
				List list = session.findSql(sql);
				if(list.size() != 0){
					resultStr = "deptNameError";
				}else{}*/
					sql = "update qdzc.sys_dept set dept_name='"+deptName+"',parent_dept_id='"+hidParentDeptId+"',dept_address='"+address+"',phone_num='"+phoneNum+"',post_num='"+postNum+"'," +
					"area='"+area+"',email='"+email+"',dept_state='"+isUseable+"',remark='"+remark+"',dept_level='"+deptLevel+"',contact_num='"+contactNumber+"'" +
							"  where dept_id = '"+deptId+"'";
					logger.info("更新部门表："+sql);
					session.update(sql);
				//sql = "update credit set dept_name='"+deptName+"',total_credit='"+totalCredit+"',available_credit='"+availableCredit+"' where dept_id = '"+deptId+"'";
				//session.update(sql);

					resultStr = "success";
				
			session.closeSession();
			
			}		
			catch (Exception e) 
			{
				resultStr = "error";
				try {
					session.exceptionCloseSession();
				} catch (CquptException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				
			}
			logger.info("resultStr:"+resultStr);	
			return resultStr;

		}
		
		
		
}
