package com.cqupt.sysManage.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.util.ExcelIn;
import com.cqupt.pub.util.Md;

import common.Logger;

public class OrganizationAddExcelAction {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());

		HttpServletRequest request = null;
		private static final int BUFFER_SIZE = 20 * 1024; // 20K

		private File myFile; // 与页面 <input type="file"> 控件的 name 保持一致
		private String inId; 
		private String myFileFileName; //
		private String contentType;
		private Connection conn = null;
		Statement stmt = null;
		public File getMyFile() {
			return myFile;
		}

		public void setMyFile(File myFile) {
			this.myFile = myFile;
		}

		// 是setMyFileFileName而不是setFileName 。 struts2的规则。MyFile 是上面private File
		// myFile

		public String getContentType() {
			return contentType;
		}

		public String getMyFileFileName() {
			return myFileFileName;
		}

		public void setMyFileFileName(String myFileFileName) {
			this.myFileFileName = myFileFileName;
		}

		// 同上
		public void setMyFileContentType(String contentType) {
			this.contentType = contentType;
		}

		HttpSession session = null;
		
		public String execute() {

			String extName = ""; // 保存文件拓展名
			String newFileName = ""; // 保存新的文件名
			String nowTimeStr = ""; // 保存当前时间
			SimpleDateFormat sDateFormat;
			Random r = new Random(); // 一个随机数对象

			String savePath = ServletActionContext.getServletContext().getRealPath(
					""); // 获取项目根路径
			savePath = savePath + "/upload/"; // 拼串组成要上传保存文件的路径，即：D:\Program
			// Files\apache-tomcat-6.0.20\webapps\(项目名)\pic\secondhand
			// 这样的路径
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码

			// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
			int rannum = (int) (r.nextDouble() * (99 - 10 + 1)) + 10; // 获取随机数
			sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
			nowTimeStr = sDateFormat.format(new Date()); // 当前时间
			
			newFileName = nowTimeStr + rannum + "部门信息.xls"; // 文件重命名后的名字
			logger.info("newFileName: "+newFileName);
			inId = nowTimeStr + rannum;//inId跟保存的文件名一样
			myFile.renameTo(new File(savePath + newFileName)); // 保存文件
			String result = null;
			PrintWriter out;
			try {
				out = response.getWriter();
				String storageInList = ExcelIn.getListByJxl(savePath + newFileName,
						1, 0, 9);// 从第一行，第0列开始解析		
				logger.info("#################"+storageInList+"333333333");
				if (storageInList.equals(" ")){				
					result = "Excel格式不对，请参照模板@@";
				}else{
					result = insertStorageInList(storageInList);
					logger.info("!!!!!!!!!");
				}
				
				logger.info("result: "+result);
				out.print(result);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null; // 这里不需要页面转向，所以返回空就可以了
		}
		
		private Md md5fun = new Md();
		private String insertStorageInList( String storageInList) {
			
			request = ServletActionContext.getRequest();
			String operUserName = "",cityId="";
			
			
			DataStormSession session = null;		
					
			String resultStr = "";
			String sql= "";
			String[] resultArray = null;
			String[] cellArray = null;
			int inCount = 0;//插入的行数
			List resultList;
			Map resultMap;
			try{
				
				operUserName = request.getSession().getAttribute("userName").toString();	
				cityId = request.getSession().getAttribute("cityId").toString();
				
				resultArray = storageInList.split(";");
				inCount = resultArray.length;//插入的行数
				
				session = DataStormSession.getInstance();	
				
				List resultListCode = null;
				Map resultMapCode = null;
				List emptyTest = null;
				Map emptyTestCode = null;
				
				
				String hidParentDeptId = "";
				String deptName = "";
				String area="";
				String address="";
				String contactNumber = "";
				String isUseable = "可用";
				String deptLevel = "";
				String postNum = "";
				
				String remark = "";
				String phoneNum = "";
				
				for (int i = 0; i < resultArray.length; i++) {
					 System.out.println(resultArray[i]);//一行的所有数据
					cellArray = resultArray[i].split("@");
					deptName = cellArray[0];
					deptLevel = cellArray[1];
					area = cellArray[2];
					phoneNum = cellArray[3];
					contactNumber = cellArray[4];
					hidParentDeptId = cellArray[5];
					address = cellArray[6];
					postNum = cellArray[7];
					remark = cellArray[8];
					
					emptyTest = session.findSql("select dept_id from sys_dept where dept_name='"+hidParentDeptId+"'");
					logger.info("emptyTest: "+emptyTest);
					emptyTestCode = (Map)emptyTest.get(0);
					hidParentDeptId = emptyTestCode.get("deptId").toString();
						
					//判断部门名称是否存在
					sql = "select * from qdzc.sys_dept  where dept_name='"+deptName+"'";
					logger.info("判断部门是否存在:"+sql);
					List resultListCode3 = session.findSql(sql);
					
					if(resultListCode3.size()== 0){
						//部门号自增，自动生成
						sql = "INSERT INTO qdzc.sys_dept (dept_id,dept_name,parent_dept_id,area,dept_address,phone_num," +
								"email,dept_state,company_name,contact_num,post_num,in_date,oper_user_name,remark,dept_level,city_id)" +
								"VALUES ('','"+deptName+"','"+hidParentDeptId+"','"+area+"','"+address+"','"+phoneNum+"','','"+isUseable+"','','"+contactNumber+"','"+postNum+"'," +
										"sysdate(),'"+operUserName+"','"+remark+"','"+deptLevel+"','"+cityId+"')";
						logger.info("Excel导入增加部门到sys_dept表:"+sql);	
						session.add(sql);
						
						
						resultStr = "success@@";
					}else{
						resultStr = "deptExist@"+deptName+"@";
						break;//遇到部门存在，就跳出循环
					}
				}//for循环结束			
				String[] result = resultStr.split("@");
				if(result[0].equals("success") ) {
					resultStr = "success@"+inId+"@"+inCount;
					session.closeSession();
				}else if(result[0].equals("deptExist") ) {
					resultStr = "deptExist@"+result[1]+"@";	
					session.exceptionCloseSession();//有某个部门存在，表格中所有记录都不插入
				}
				
			
			
			}catch (Exception e) {
				resultStr = "系统异常,请重试或者联系系统管理员@@";
				try {
					session.exceptionCloseSession();			
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				
			}
		System.out.println("+++++++"+resultStr);
			return resultStr;
	
		}


	}
