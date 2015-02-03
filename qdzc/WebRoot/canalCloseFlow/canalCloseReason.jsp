<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@
 
 taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<%@ page import="com.cqupt.pub.dao.DataStormSession,java.util.*,java.util.Map" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
  <title>渠道关闭审核</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="../com_css/LigerUILib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="../com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
    <script src="../com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
     
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>  
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>    
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../com_css/js/jquery.blockUI.js" type="text/javascript"></script>
       <link href="../com_css/jqueryUI/themes/base/jquery.ui.all.css" rel="stylesheet">
	<script src="../com_css/jqueryUI/ui/jquery.ui.core.js"></script>
	<script src="../com_css/jqueryUI/ui/jquery.ui.widget.js"></script>
	<script src="../com_css/jqueryUI/ui/jquery.ui.position.js"></script>
	<script src="../com_css/jqueryUI/ui/jquery.ui.autocomplete.js"></script>
	<script type="text/javascript">
$(function(){
	 var ind='<%= request.getParameter("inId")%>'; 
     $("#submitReason").click(function(){
	 $.ajax( {
      url :'writeCanalCloseContentAction?inId='+ind+"&refuseReason="+encodeURI(encodeURI($("#refuseReason").val())), 
			type : 'post', //数据发送方式   
			dataType : 'text', //接受数据格式   
			success : function(result){
				if(result.substring(0,7)=="success"){
					 window.parent.parent.$("#maingrid4").ligerGrid().loadData(true);		
		      		  	$.ligerDialog.success('提交成功，请关闭对话框！','提示',function(){		  		
			   			 var parentWindow_body =  $(window.parent.parent.document).find("body");
			   			 if(! parentWindow_body.find(".dealWithMask").length){
			   				 parentWindow_body.append("<input type=text class=dealWithMask style=height:0;border:none/>");
			   				 }parentWindow_body.find(".dealWithMask").focus(); 
			   			 window.parent.parent.$.ligerDialog.close();
		 		      });	 		
				}else{				
					alert(result);
				}
			}
		//回传函数(这里是函数名)    
			});
  });
  });
  
  
  </script>
  </head>  
  <table>
  <tr>
  <td style="font-size:15px;">备注内容：
  </td>
  </tr>
  <tr>
  <td>
  <textarea id="refuseReason" rows="8" cols="40"></textarea> 
  </td>
  </tr>
  <tr>
 <td align="left" class="l-table-edit-td">
  <input type="button" value="提交" id="submitReason" style="width:80px;margin-top:20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
 </td>
 </tr>
  </table>
  <body>
  </body>
</html>
