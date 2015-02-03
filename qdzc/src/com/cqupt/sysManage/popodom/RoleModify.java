package com.cqupt.sysManage.popodom;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.opensymphony.xwork2.ActionSupport;

public class RoleModify extends ActionSupport {
	Logger logger = Logger.getLogger(this.getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	DataStormSession session = null;

	public String execute() {
		String groupName = null;
		String remark = null;
		int groupId = Integer.valueOf(request.getParameter("groupId"));
		logger.info("groupId:" + groupId);
		String sql = "select group_name,remark from qdzc.sys_user_group where group_id="
				+ groupId;
		try {
			session = DataStormSession.getInstance();
			Map map = session.executeSQL(sql);
			if (map != null) {
				List list = (List) map.get("resultList");
				Map resultMap = (Map) list.get(0);
				groupName = (String) resultMap.get("groupName");
				remark = (String) resultMap.get("remark");
				logger.info(groupId + ",,," + groupName + ",,,," + remark);
			}
			request.setAttribute("groupId", groupId);
			request.setAttribute("groupName", groupName);
			request.setAttribute("remark", remark);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
