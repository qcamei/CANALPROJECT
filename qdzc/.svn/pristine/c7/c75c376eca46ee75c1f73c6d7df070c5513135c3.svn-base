package com.cqupt.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.Md;
import com.cqupt.pub.util.ObjectChanger;
import com.opensymphony.xwork2.ActionSupport;

public class CheckLoginAction extends ActionSupport {
	Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	private ObjectChanger objChanger = ObjectChanger.getInstance();

	private String userId;
	private String password;
	private String usercaptcha;
	private String userName;
	private String userDept;
	private String userDeptId;
	private String userCityId;// 地州市
	private String groupId;
	private String dataAuthId;
	private String individualAuth = "";// 个人的权限，1.按渠道性质；2.按操作人

	public String getDataAuthId() {
		return dataAuthId;
	}

	public void setDataAuthId(String dataAuthId) {
		this.dataAuthId = dataAuthId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	private Md md5fun = new Md();

	public String getUsercaptcha() {
		return usercaptcha;
	}

	public void setUsercaptcha(String usercaptcha) {
		this.usercaptcha = usercaptcha;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserDeptId(String userDeptId) {
		this.userDeptId = userDeptId;
	}

	public String getUserDeptId() {
		return userDeptId;
	}

	public String execute() {
		logger.info("CheckLoginAction：");
		this.request = ServletActionContext.getRequest();
		ServletContext sc = ServletActionContext.getServletContext();
		HttpServletResponse response = ServletActionContext.getResponse();
		String result = "success";

		String userEmail = request.getParameter("userEmail");
		logger.info("userEmail:" + userEmail);
		System.out.println("userEmail == null:" + (userEmail == null));
		if (userEmail == null) {
			System.out.println("0000000000000000:");
			String userId = this.getUserId();
			String userPwd = this.getPassword();
			this.setUserId(request.getAttribute("userId").toString());
			this.setPassword(request.getAttribute("password").toString());
			this.setUsercaptcha(request.getAttribute("usercaptcha").toString());
			result = checkPwd(userId, userPwd); // 验证登录名
			if (result.equals("success")) // 登录名成功验证,验证验证码
				result = checkCaptcha();
			if (result.equals("success")) // 验证码成功验证是否登陆
				result = checkLogin(userId, request.getRemoteAddr(), sc);

		} else {
			checkEmail(userEmail);
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 直接输入响应的内容

		return null;
	}

	// 根据邮箱登录
	private void checkEmail(String userEmail) {
		DataStormSession session = null;
		List list = null;
		Map map = null;
		String userId = "", userPwd = "";
		ServletContext sc = ServletActionContext.getServletContext();
		try {
			session = DataStormSession.getInstance();
			if (userEmail != null || !userEmail.equals("")) {
				String sql = "select * from qdzc.sys_user where user_email='"
						+ userEmail + "' ";
				logger.info("sql语句：" + sql);
				list = session.findSql(sql);
				if (list.size() > 0) {
					map = (Map) list.get(0);
					userId = map.get("userId").toString();
					userPwd = map.get("userPwd").toString();
					checkPwd(userId, userPwd);
					checkLogin(userId, request.getRemoteAddr(), sc);
				}
			}
		} catch (CquptException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户密码验证
	 * 
	 * @param prop
	 * @return useIdError-登录名错误 pwdError-密码错误 success-验证成功 ---sysError-系统异常
	 * @throws
	 */
	String checkPwd(String userId, String userPwd) {
		String result;
		DataStormSession session = null;
		try {
			session = DataStormSession.getInstance();
			String sql = "select user_pwd,dept_name,t.user_name,dept_id,group_id from qdzc.sys_user t where t.user_id='"
					+ userId + "' and t.user_state = '可用' ";// 在用户表中根据userId来获取密码的MD5值
			logger.info("检测登录用户信息sql：" + sql);
			List resultList = session.findSql(sql);
			if ((resultList.size()) == 0) {
				result = "登录名" + userId + "不存在或已禁用";
			} else {
				Map resultMap = (Map) resultList.get(0);
				String passwd = resultMap.get("userPwd").toString();
				setUserName(resultMap.get("userName").toString());
				setUserDept(resultMap.get("deptName").toString());
				setUserDeptId(resultMap.get("deptId").toString());
				setGroupId(resultMap.get("groupId").toString());

				String deptId = resultMap.get("deptId").toString();
				String groupId = resultMap.get("groupId").toString();

				// 用户所属地州市
				sql = "select city_id from qdzc.sys_dept where dept_id = '"
						+ deptId + "'";
				resultList = session.findSql(sql);
				userCityId = ((Map) resultList.get(0)).get("cityId").toString();

				List groupAuthList = null;
				Map groupAuthMap = null;
				String dataType = "";
				// 根据角色组判断用户数据权限
				StringBuffer sb = new StringBuffer();
				sql = "select t.group_name,data_type from qdzc.sys_data_auth t where t.group_id='"
						+ groupId + "'";
				groupAuthList = session.findSql(sql);

				if (groupAuthList.size() != 0) {
					groupAuthMap = (Map) groupAuthList.get(0);
					dataType = groupAuthMap.get("dataType").toString();

					if (dataType.equals("allCity")) {

						// 超级管理员 allCity 查看所有地州市的数据
						sql = "select t.dept_id from qdzc.sys_dept t where t.dept_state = '可用'";
						individualAuth = "";
					} else if (dataType.equals("allDeptId")) {

						// allDeptId 市公司管理员,IT及业管人员,其他部门经理，财务审核人员 查看本地州市的数据
						sql = "select t.dept_id from qdzc.sys_dept t where t.dept_state = '可用' and city_id = '"
								+ userCityId + "'";
						individualAuth = "";
					} else if (dataType.equals("allDeptIdOpenManager")
							|| dataType.equals("allDeptIdOpen")) {

						// allDeptIdOpenManager，allDeptIdOpen 开放渠道部经理，开渠部主管
						// 查看渠道“管理属性”为开渠
						sql = "select t.dept_id from qdzc.sys_dept t where t.dept_state = '可用' and city_id = '"
								+ userCityId + "'";
						individualAuth = "OpenCanal";

					} else if (dataType.equals("allDeptIdCanalManager")
							|| dataType.equals("allDeptIdCanal")) {
						// allDeptIdCanalManager，allDeptIdCanal渠道拓展部经理，渠道拓展部主管
						// 查看渠道“管理属性”为非开渠
						sql = "select t.dept_id from qdzc.sys_dept t where t.dept_state = '可用' and city_id = '"
								+ userCityId + "'";
						individualAuth = "Canal";

					} else if (dataType.equals("branchDeptId")) {
						// branchDeptId 分公司总经理 查看所属分公司的数据
						sql = "select t.dept_id from qdzc.sys_dept t where t.dept_state = '可用' and city_id = '"
								+ userCityId
								+ "' and dept_id = '"
								+ deptId
								+ "'";
						individualAuth = "";

					} else if (dataType.equals("selfOperator")) {
						// selfOperator 渠道客户经理 查看自己录入的数据
						sql = "select t.dept_id from qdzc.sys_dept t where t.dept_state = '可用' and city_id = '"
								+ userCityId
								+ "' and dept_id = '"
								+ deptId
								+ "'";
						individualAuth = "selfOperator";

					}

					List dataAuthList = null;
					Map dataAuthMap = null;
					logger.info("读取权限部门ＩＤ号sql：" + sql);
					dataAuthList = session.findSql(sql);
					for (int i = 0; i < dataAuthList.size(); i++) {
						Map resultMap1 = (Map) dataAuthList.get(i);
						sb.append("'" + resultMap1.get("deptId") + "'");
						if (i != dataAuthList.size() - 1)
							sb.append(",");
					}
				}
				// 现业查询部门 ：4江阳区分公司、6龙马潭区分公司的数据
				if (deptId.equals("25")) {
					sb.append(",'4','6'");
				}

				logger.info("最后的数据权限dataAuthId:" + sb.toString());
				setDataAuthId(sb.toString());

				if (passwd.equals(md5fun.getMD5ofStr(userPwd))) {// md5加密
					result = "success";
				} else {
					result = "登录名" + userId + "密码输入错误";
				}
			}
			session.closeSession();
		} catch (Exception e) {
			result = "系统异常，请联系系统管理员";
			try {
				session.exceptionCloseSession();
			} catch (CquptException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return result;

	}

	/*
	 * 验证码是否正确
	 */
	String checkCaptcha() {
		HttpSession session = request.getSession();
		String result = "success";

		String captcha = session.getAttribute("randomStr").toString();
		if (!captcha.equals(this.getUsercaptcha())) {
			result = "验证码输入错误，请重新输入";
		}

		return result;
	}

	/*
	 * 验证账号是否已经登陆
	 */
	String checkLogin(String userId, String addr, ServletContext sc) {
		String result = "success";
		String loginTime = "";
		try {
			loginTime = objChanger.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		} catch (CquptException e) {
			e.printStackTrace();
			// 系统错误
			result = "系统异常，请联系系统管理员";
		}
		try {

			// 取消单个用户登录机制
			HttpSession session = request.getSession(); // 把用户放进session

			session.setAttribute("userId", userId);//
			session.setAttribute("userIp", addr);//
			session.setAttribute("cityId", userCityId);//
			session.setAttribute("loginTime", loginTime);//
			session.setAttribute("userName", getUserName());
			session.setAttribute("deptName", getUserDept());
			session.setAttribute("deptId", getUserDeptId());
			session.setAttribute("groupId", getGroupId());
			session.setAttribute("dataAuthId", getDataAuthId());
			session.setAttribute("individualAuth", individualAuth);

			OnlineUser.online.put(userId, session);
		} catch (Exception e) {
			e.printStackTrace();
			// 系统错误
			result = "系统异常，请联系系统管理员";
		}
		return result;
	}

}
