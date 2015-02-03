package com.cqupt.canalAuditFlow;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.DecodeUtils;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigLineDetailAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=null;
	
	public String execute()
	{
		request = ServletActionContext.getRequest();
		String inId = request.getParameter("inId");
		String type = request.getParameter("type");
		String canalName = DecodeUtils.decodeRequestString(request.getParameter("canalName"));
		String canalId = DecodeUtils.decodeRequestString(request.getParameter("canalId"));
		
		String resultStr = "";
		DataStormSession session = null;
		try 
		{
			session = DataStormSession.getInstance();
				
			String sql = "select *,date_format(oper_time,'%Y-%m-%d %H:%i:%s') line_time12 from qdzc.process12_config_line where in_id='"+inId+"'";
			logger.info("查询专线受理：" + sql);
			List list = session.findSql(sql);
			if(list.size()>0){
				Map map = (Map)list.get(0);
				
				request.setAttribute("broadbandAccount", map.get("broadbandAccount"));
				request.setAttribute("broadbandPassword", map.get("broadbandPassword"));
				
				
			}
			
			sql = "select *,date_format(open_time,'%Y-%m-%d %H:%i:%s') open_time1,date_format(oper_time,'%Y-%m-%d %H:%i:%s') line_time9 from qdzc.process9_config_line where in_id='"+inId+"'";
			logger.info("查询专线受理确认：" + sql);
			
			List resultList = session.findSql(sql);
			if(resultList.size()>0){
				Map resultMap = (Map)resultList.get(0);			
				request.setAttribute("remark", resultMap.get("remark"));
				request.setAttribute("openTime", resultMap.get("openTime1"));
				// request.setAttribute("serverIp", resultMap.get("serverIp"));
				
			}
			
			
			request.setAttribute("inId",inId);

			request.setAttribute("canalName",canalName);
			request.setAttribute("canalId",canalId);
			session.closeSession();
		}		
		catch (Exception e) 
		{
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		if(type.equals("modify")){
			resultStr = "modify";
		}else if(type.equals("add")){
			resultStr = "add";
		}
		return resultStr;         
       
       }
}
