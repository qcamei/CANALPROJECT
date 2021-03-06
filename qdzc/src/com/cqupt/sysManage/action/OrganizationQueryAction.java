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

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
public class OrganizationQueryAction extends ActionSupport{
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1794195924380050122L;
	HttpServletRequest request=null;

	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}

	
	public String execute()
	{	
		String deptId="";

		this.request = ServletActionContext.getRequest(); 
		deptId = (String)request.getParameter("deptId");
		
		logger.info("getInitDeptId:"+deptId);
		
	    String str = new String(getChildDept(deptId)) ;
	    try {
			inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return SUCCESS;


	}
	
	
	private String getChildDept(String deptId) {
		DataStormSession session = null;
		StringBuilder resultStr = new StringBuilder();
		String sql="";
		
		try 
		{
			session = DataStormSession.getInstance();
			

			sql="SELECT a.*,ifnull(child_total,0) child_total FROM (select * from qdzc.sys_dept t WHERE t.parent_dept_id = '"+deptId+"'  ORDER BY t.dept_id) a left join"+         //选出一级菜单
						   "(SELECT parent_dept_id,COUNT(*) child_total from qdzc.sys_dept t WHERE dept_level='支/分局' GROUP BY parent_dept_id ) b "+				 //找出所有子节点对应的子节点的数量
						   "on a.dept_id = b.parent_dept_id";
		   
		    logger.info("部门树查询:"+sql);
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
						resultStr.append("<tree text=\""+resultMap.get("deptName")+"\"  action=\"javascript:changeList('"+resultMap.get("deptId")+":"+resultMap.get("deptName")+"')\"/>");
					} else {
						resultStr.append("<tree text=\""+resultMap.get("deptName")+"\" action=\"javascript:changeList('"+resultMap.get("deptId")+":"+resultMap.get("deptName")+"')\" src=\"organizationQueryAction?deptId="+resultMap.get("deptId")+"\"   />");
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
		logger.info(resultStr);
		return resultStr.toString();
	}
	
}
