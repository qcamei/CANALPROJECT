<%@ page language="java" import="java.util.*,com.cqupt.pub.util.*" pageEncoding="UTF-8"%>
<%
String email= request.getParameter("email");
if(email != null && !email.equals("") ){
	email = DeEncode.decodeString(email);
	System.out.println("通过邮箱传过来的email为："+email);
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>四川电信渠道支撑系统</title>
	<link href="com_css/css/login.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="com_css/formValidator/style/validator.css" type="text/css"></link>
<script type="text/javascript" src="com_css/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="com_css/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="com_css/formValidator/formValidator-4.0.1.min.js"></script>
	<script type="text/javascript" src="com_css/formValidator/formValidatorRegex.js"></script>
	
<script type="text/javascript">
var userEmail = "<%=email%>";
if( userEmail != "null"){
	$(function() {  
		$.blockUI({        message: "正在登录，请稍候……"});
		$.ajax({
		    type:"POST",
		    url:"checkLoginAction",
		    data:"userEmail="+userEmail,
		    success:function(data){
		    	var dataArray=new  Array(); 
				dataArray = data.split("@");
				if(dataArray[0] == "success"){
					if(dataArray[1] == "isExist"){
						$.unblockUI();
						alert("不能同时登录多个用户，请正常退出后重新登录！");
						window.location.reload(true);
					}else{
						window.location.href="loginAction";
					}
				}
			}
		});
	});
}

function key_down(){
	if (event.keyCode == 13) {
		checkLogin();
	}
}
</script>	

</head>

<body>
<div id="container">
	<div id="header">
		<img alt="" src="mainFrame/images/zgdx.png" >
	</div>
	<div id="content">
		<div class="left_main">
			<ul class="news" style="color:#FFFFFF;"><br /><br />
				<li><font style="font-size:20px;" size="5">&nbsp;&nbsp;&nbsp;&nbsp;四川电信渠道支撑系统  </font></li>
			</ul>
		</div>
		<form id="form1" method="post" action = "">
			<fieldset class="right_main">
				<dl class="setting">
					<dt>
						<label>登录名</label>
					</dt>
					<dd>
						<span class="text_input">
							<input type="text" name="userId" id="loginAction_userId"/>
						</span>
						<div id="usernameTip" style="width:200px;"></div>
					</dd>
					<dt>
						<label>密　码</label>
					</dt>
					<dd>
						<span class="text_input">
							<input type="password" name="password" id="loginAction_password" />
						</span>
						<div id="userpassTip" style="width:200px;"></div>
					</dd>
					<dt>
						<label>验证码</label>
					</dt>
					<dd>
						<span class="short_input">
							<input id="loginAction_usercaptcha" type="text" style="text-transform: uppercase;" name="usercaptcha" onkeydown="key_down()"/>
						</span>
						<span class="yzm">
							<img src="identify_Code.jsp" id="identifycode"/>
							<a href="javascript:changeCode()">换一张</a>
						</span>
						<div id="vdcodeTip" style="width:280px;"></div>
					</dd>
					<dd>
						<input type="button" id="loginBtn" value="登录" name="sm1"  class="login_btn" onClick="checkLogin()" />
						<div id="loginbottom" class="login">
							<font color="#ff0000"><div id="errorInfo"><div class="warn" id="resultpsw"></div></div></font>
						</div>
					</dd>
				</dl>
				
			</fieldset>
		</form>
	</div>	
	<div id="footer">Copyright &copy; 2014  中国电信. All rights reserved.</div>
</div>	
</body>
	<script type="text/javascript" src="com_css/js/loginjs.js"></script>
</html>