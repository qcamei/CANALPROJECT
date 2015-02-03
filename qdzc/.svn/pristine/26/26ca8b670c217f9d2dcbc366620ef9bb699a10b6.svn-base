package com.cqupt.sysManage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.Md;
import com.opensymphony.xwork2.ActionSupport;

public class UserManagerAddUserAction  extends ActionSupport{


	private static final long serialVersionUID = -6941574699319426537L;
		Logger logger = Logger.getLogger(getClass());
		HttpServletRequest request=null;
		private Md md5fun = new Md();

		public String execute()
		{
			
			

			this.request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			
			try {
				String txtUserId = java.net.URLDecoder.decode(request.getParameter("txtUserId"), "UTF-8");
				
				String deptId = request.getParameter("deptId");
				String deptName = java.net.URLDecoder.decode(request.getParameter("deptName"), "UTF-8");
				String txtName = java.net.URLDecoder.decode(request.getParameter("txtName"), "UTF-8");
				String txtPsw = java.net.URLDecoder.decode(request.getParameter("txtPsw"), "UTF-8");
				String groupId = request.getParameter("groupId");
				String txtUserGroup = java.net.URLDecoder.decode(request.getParameter("txtUserGroup"), "UTF-8");			
				String txtPhone = java.net.URLDecoder.decode(request.getParameter("txtPhone"), "UTF-8");
				String txtEmail = java.net.URLDecoder.decode(request.getParameter("txtEmail"), "UTF-8");
				String isUseable = java.net.URLDecoder.decode(request.getParameter("isUseable"), "UTF-8");
				
				
				
				String operUserName = (String)session.getAttribute("userName");
				if(isUseable.equals("1")){
					 isUseable = "可用";
				}else{
					isUseable = "不可用";
				}
				
				System.out.println(isUseable);
				HttpServletResponse response=ServletActionContext.getResponse();
		          
		        response.setCharacterEncoding("UTF-8");    
		        PrintWriter out;
		        out = response.getWriter();
				out.print(insertUserinfo( deptId,deptName, txtUserId, txtName,  txtPsw, groupId,txtUserGroup, txtPhone, txtEmail, isUseable,operUserName));    
		        out.flush();    
		        out.close();  
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}    
	               
	       return null;


		}
		
		
		private String insertUserinfo(String deptId,String deptName,String txtUserId, String txtName,  String txtPsw,
				String groupId,String txtUserGroup, String txtPhone, String txtEmail, String isUseable,String operUserName) {
			DataStormSession session = null;
			String resultStr = "";
			String sql= "";
			
			try {
				session = DataStormSession.getInstance();
				if(deptId.length() == 3 || deptId.length() == 4){
					resultStr = "deptError";
				}else{
					sql = "select COUNT(*) COUNT from sys_user t WHERE t.user_id = '"+txtUserId+"'";
					List resultList = session.findSql(sql);
					Map resultMap = (Map)resultList.get(0);
					if(!resultMap.get("count").toString().equals("0")){
						
						resultStr = "allExist";
					} else {
			
				
						sql = "INSERT INTO sys_user (user_id,user_name,user_pwd,dept_id,dept_name,group_id,group_name,user_state," +
								"	user_email,phone_num,remark,in_date,oper_user_name)" +
								" VALUES('"+txtUserId+"','"+txtName+"','"+md5fun.getMD5ofStr(txtPsw)+"','"+deptId+"','"+deptName+"','"+groupId+"','"+txtUserGroup+"','"+isUseable+"','"+
								txtEmail+"','"+txtPhone+"','',sysdate(),'"+operUserName+"')";
						logger.info("添加用户："+sql);
						session.add(sql);
						
						
						resultStr = "success";
					}
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
