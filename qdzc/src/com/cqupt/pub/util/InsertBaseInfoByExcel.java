package com.cqupt.pub.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;

public class InsertBaseInfoByExcel {

	public static void main(String[] args) throws BiffException, IOException, CquptException {
		DataStormSession session = DataStormSession.getInstance();
		String sql = "";
		Workbook wb = Workbook.getWorkbook(new File("C:\\Users\\Administrator\\Desktop\\移动终端-手机品牌和机型.xls"));
		Sheet sheet = wb.getSheet(0);
		Set<String> brands = new TreeSet<String>();
		Cell cell = null;
		System.out.println(sheet.getRows()); 
		//获取所有手机品牌
		for(int i = 2; i < sheet.getRows(); i ++) {
			cell = sheet.getCell(0, i);
			System.out.println(cell.getContents());
			brands.add(cell.getContents());
		}
		Iterator<String> iter = brands.iterator();
		while(iter.hasNext()) {
			String brandName = iter.next();
			sql = "insert into terminal_brand values(null, '" + brandName + "', 1, '移动终端', '可用', 'lzadmin', sysdate(),'')";
			session.add(sql);
		}
		
		
		List<Map<String, Object>> list = null;
		//获取所有手机品牌以及型号
		for(int i = 2; i < sheet.getRows(); i ++) {
			String brandName = sheet.getCell(0, i).getContents();
			String versionName = sheet.getCell(1, i).getContents();
			sql = "select concat(brand_id,'') brand_id, brand_name from qdzc.terminal_brand where terminal_id=1 and brand_name='" + brandName + "'";
			list = session.findSql(sql);
			sql = "insert into terminal_version value(null, '" + versionName + "',  " + list.get(0).get("brandId").toString() + ", '" + brandName + "', '可用', 'lzadmin', sysdate(),'')";
			System.out.println("sql:" + sql);
			session.add(sql);
		}
		wb.close();
		session.closeSession();
	}
	
}
