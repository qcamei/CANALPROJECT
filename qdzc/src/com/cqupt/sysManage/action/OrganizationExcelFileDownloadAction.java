package com.cqupt.sysManage.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class OrganizationExcelFileDownloadAction extends ActionSupport{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5489994619108462686L;
	/**
	 *   品牌信息录入文件模板
	 */

	HttpServletRequest request = null;

	public String execute() {
		request = ServletActionContext.getRequest();     
        String path = request.getRealPath("/")+"download/organizationExcel.xls";
System.out.println(path);
  
   	    request.setAttribute("path", path);
   	    request.setAttribute("userFileName", "organizationExcel.xls");
		return "success";
	
		
		
	}
}