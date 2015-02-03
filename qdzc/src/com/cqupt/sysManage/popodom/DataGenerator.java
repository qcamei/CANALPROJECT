package com.cqupt.sysManage.popodom;

import java.util.List;
import java.util.Map;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;

public class DataGenerator {

	public static List getVirtualResult() {
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
		} catch (CquptException e1) {
			e1.printStackTrace();
		}
		String sql = "select menuid,menuname,p_menuid from qdzc.sys_menu";
		List list = null;
		try {
			Map map = session.executeSQL(sql);
			if (map != null) {
				list = (List) map.get("resultList");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
