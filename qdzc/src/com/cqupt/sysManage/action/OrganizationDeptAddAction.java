package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.cqupt.pub.util.DecodeUtils;
import com.cqupt.pub.util.Md;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class OrganizationDeptAddAction extends ActionSupport{

	/**
	 * 
	 */
	//private static final long serialVersionUID = -6941574699319426537L;
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());

	private Md md5fun = new Md();
		HttpServletRequest request=null;
		private InputStream inputStream;
		public InputStream getInputStream() {
			return inputStream;
		}
		public String execute()
		{
			String deptName=null;
			String hidParentDeptId=null;
			String isUseable=null;
			String contactNumber=null;
			String phoneNum = null;
			String address=null;
			String postNum=null;
			String email=null;
			String area=null;
			String remark=null;	
			
			String deptLevel=null;
			String cityId=null;
			
			
			String operUserName=null;
			
			this.request = ServletActionContext.getRequest();
			
			
			operUserName = request.getSession().getAttribute("userName").toString();
			cityId = request.getSession().getAttribute("cityId").toString();
			
			System.out.println("hidParentDeptId:"+hidParentDeptId);
			
			try {
				if(request.getParameter("deptName")!=null){		
						deptLevel = DecodeUtils.decodeRequestString(request.getParameter("deptLevel"));
					
						deptName = DecodeUtils.decodeRequestString(request.getParameter("deptName"));
						
					if(hidParentDeptId != null && hidParentDeptId != "")
						hidParentDeptId = DecodeUtils.decodeRequestString(request.getParameter("hidParentDeptId"));
					else 
						hidParentDeptId = cityId;
						isUseable = DecodeUtils.decodeRequestString(request.getParameter("isUseable"));
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
	
	           response.setCharacterEncoding("UTF-8");    
	           PrintWriter out;
			try {
				out = response.getWriter();
				out.print(inserDeptinfo(deptLevel,deptName , hidParentDeptId ,isUseable , contactNumber,phoneNum ,address ,
						remark , email,postNum,area,operUserName,cityId));    
		        out.flush();    
		        out.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	       return null;
		}
		
		private String inserDeptinfo(String deptLevel,String deptName , String hidParentDeptId ,String isUseable , String contactNumber,String phoneNum ,String address ,
				String  remark ,String email,String postNum,
				String area,String operUserName,String cityId) {
			DataStormSession session = null;
			String resultStr = "";
			
			String sql = "";
			String resultId="",resultIdNew="",deptNameNew=deptName;
			List resultListCode = null;
			Map resultMapCode = null;
			int bid = 0;
			DecimalFormat df = null;
			try 
			{
				
				
				
				//部门号自增，自动生成
				sql = "INSERT INTO qdzc.sys_dept (dept_id,dept_name,parent_dept_id,area,dept_address,phone_num," +
						"email,dept_state,company_name,contact_num,post_num,in_date,oper_user_name,remark,dept_level,city_id)" +
						"VALUES ('','"+deptName+"','"+hidParentDeptId+"','"+area+"','"+address+"','"+phoneNum+"','"+email+"','"+isUseable+"','','"+contactNumber+"','"+postNum+"'," +
								"sysdate(),'"+operUserName+"','"+remark+"','"+deptLevel+"','"+cityId+"')";
				logger.info("增加部门到sys_dept表:"+sql);	
				session = DataStormSession.getInstance();
				session.add(sql);
				
				resultStr = "success";
						
				session.closeSession();
				
				
			}catch (Exception e) {
			
				try {
					resultStr = "error";
					session.exceptionCloseSession();
				} catch (CquptException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
		
			
			}
			logger.info("resultStr ="+resultStr);			
			return resultStr;

		}
		
}
