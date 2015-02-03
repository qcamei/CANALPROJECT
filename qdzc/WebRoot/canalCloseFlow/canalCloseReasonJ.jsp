<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/operatorUser.tld" prefix="operatorUser" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%	
String canalName=request.getParameter("canalName");
canalName = new String(canalName.getBytes("iso8859_1"),"utf-8");
%>
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
 
	 $(function() {
  	   $.metadata.setType("attr", "validate");
 			var v = $("form").validate({
           debug: true,
           errorPlacement: function (lable, element)
           {
               if (element.hasClass("l-textarea"))
               {
                   element.ligerTip({ content: lable.html(), target: element[0] }); 
               }
               else if (element.hasClass("l-text-field"))
               {
                   element.parent().ligerTip({ content: lable.html(), target: element[0] });
               }
               else
               {
                   lable.appendTo(element.parents("td:first").next("td"));
               }
           },
           success: function (lable)
           {
               lable.ligerHideTip();
               lable.remove();
           },
           submitHandler: function ()
           {
               $("form .l-text,.l-textarea").ligerHideTip();
               
           }

  });

 $("form").ligerForm();

 
  });  

		 function ajaxUpdate(){//提交表单内容
                 $.blockUI({message:'<h4><img src="../images/loading.gif" /> 系统正在提交中……</h4>' }); 
		         var params=$('#form1').serialize().replace(/\+/g, ' '); //这里直接就序列化了表单里面的值；所有name的值都显示在里面，很方便  
				 params = decodeURIComponent(params,true);	      
				 params = encodeURIComponent(encodeURIComponent(params)).replace(/%253D/g,'=').replace(/%2526/g,'&');
				 var ind='<%= request.getParameter("inId")%>';
		         var close='<%= request.getParameter("closeId")%>';  
			      $.ajax({   //把表单内容插入数据库
			              url :'writeCanalCloseContentJHXTAction?inId='+ind+'&closeId='+close,  //后台处理程序   
			              type:'post',    //数据发送方式   
			              dataType:'text',   //接受数据格式   
			              data:params,   //要传递的数据；就是上面序列化的值   
			              success: submit_Result 
			       });  
		             
	 } 
		 function submit_Result(result){ //回传函数实体，参数为XMLhttpRequest.responseText   
		 		$.unblockUI();		 		
		 		if(result=='success') {
                      window.parent.parent.$("#maingrid4").ligerGrid().loadData(true);		
		      		  	$.ligerDialog.success('提交成功，请关闭对话框！','提示',function(){		  		
			   			 var parentWindow_body =  $(window.parent.parent.document).find("body");
			   			 if(! parentWindow_body.find(".dealWithMask").length){
			   				 parentWindow_body.append("<input type=text class=dealWithMask style=height:0;border:none/>");
			   				 }parentWindow_body.find(".dealWithMask").focus(); 
			   			 window.parent.parent.$.ligerDialog.close();
		 		      });	

		      		
		 		}else if(result=='infoLoss'){
		 			$.ligerDialog.error('信息填写不完整，请重试！');
				} else if(result=='error'){
		 			$.ligerDialog.error('系统异常，联系管理员！');
				}else{
					$.ligerDialog.error(result);
				}
		}
		 
		 
		 
		 function getState(){
			 var money = $("#money").val();
			 if(money != 0){
				 $("#auditState").val("驳回");
			 }else{
				 $("#auditState").val("通过");
			 }
		 }

		
</script>	
	
<style type="text/css">
body{ font-size:12px;background:#ffffff}
.blackbold_b { /* 标题样式 */
	line-height: 22px;
	width: 150px;
	height: 22px;
	font-size: 12px;
	font-weight: bold;
	color: #FF5317
}
.l-table-edit-td{ padding:4px;font-size:12px;}
.l-button-submit,.l-button-test{width:80px; float:left; margin-left:9px; padding-bottom:2px;}
</style>
  </head>
<body>
<form action="" method="post" name = "form1" id = "form1"   > 
<table>



 <tr> <td align="right" class="l-table-edit-td"></td></tr>

<tr> 
<td  align="right" class="l-table-edit-td">渠道名称：</td>
<td class="l-table-edit-td"  width="120px" >
<input name="canalName" type="text" id="canalName" value="<%=canalName %>" ltype="text" readonly="readonly"/></td>
</tr>

<tr> 
<td  align="right" class="l-table-edit-td">欠款金额：</td>
<td class="l-table-edit-td"  width="120px" >
<input type="text" id="money" onchange="getState()" name="money"   /></td>
</tr>
 
 <tr>
<td  align="right" class="l-table-edit-td">审核状态：</td>
<td class="l-table-edit-td"  width="120px" >
<input type="text" id="auditState" name="auditState"  readonly="readonly" /></td>
 </tr>
 
 
 <tr> <td align="right" class="l-table-edit-td"></td></tr>

 <tr>
 <td  align="right" class="l-table-edit-td">备注内容：</td>
 <td align="left" class="l-table-edit-td"> 
<textarea name="refuseReason"  cols="100" rows="8" class="l-table-edit-td"  ></textarea></td>
</tr>
 <tr>
                <td align="right" class="l-table-edit-td"></td>
                <td align="left" class="l-table-edit-td"> 
                  <input type="submit" value="提交" onclick="ajaxUpdate()" class="l-button l-button-submit" /> 
                </td>
            </tr>

</table>
 </form> 
  </body>
  </html>