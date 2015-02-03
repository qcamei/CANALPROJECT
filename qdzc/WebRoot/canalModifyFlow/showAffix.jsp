<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%
    String productIcon = request.getParameter("id");
    if(productIcon == null ){
    	productIcon = "";
    }
    %>
    <title>My JSP 'showAffix.jsp' starting page</title>
  </head>
  <body>
    <div align = center>
    <%
    String images[] = productIcon.split(";");
    for(String s:images) {
    	if( s.length() > 1){
    %>
   		 <img alt="需要下载，请右键点击图片，另存到电脑"  height="400" width="500"  src='<%="upload/certificate/"+s %>'>
	 <%
    	}
    } %>
    </div>
  </body>
</html>
