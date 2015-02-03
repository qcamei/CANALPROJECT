<%@ page contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>第四五步 财务审核（变更）</title>
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
                     ajaxUpdate();
                 }

        });
       			
       		var inId = "${param.inId}";
       		var canalId = "${param.canalId}";
       		var canalName = decodeURI("${param.canalName}");
       		var agentId = decodeURI("${param.agentId}");
       		var agentName = decodeURI("${param.agentName}");
   
       	 $("#isPay").ligerComboBox({url :'selectItemQueryAction?type=is_true', isMultiSelect: false});	
       	$("#auditState").ligerComboBox({url :'selectItemQueryAction?type=audit_state', isMultiSelect: false});	
       	 
       		$("#inId").val(inId);
       		$("#canalId").val(canalId);
       		$("#canalName").val(canalName);
       		$("#agentId").val(agentId);
       		$("#agentName").val(agentName);
 
            $("form").ligerForm();
            
            $(".l-button-test").click(function() {
                alert(v.element($("#txtName")));
            }); 
            
            
        });  
        
        
		
        
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>

</head>

<body style="padding:10px">

    <form name="form1" method="post" action="" id="form1">

        <table cellpadding="0" cellspacing="0" class="l-table-edit" width="500px">
        	<tr>
                <td align="right" class="l-table-edit-td" width="200px"><font color="red">*</font>渠道编码:</td>
                <td align="left" class="l-table-edit-td" width="200px">
                <input name="inId" type="hidden" id="inId" />
                	<input name="canalId" type="text" id="canalId"   ltype="text" readonly="readonly" validate="{required:true}" />
                </td>
            </tr>
        	<tr>
                <td align="right" class="l-table-edit-td"><font color="red">*</font>渠道名称:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="canalName" type="text" id="canalName" ltype="text"  validate="{required:true}"/>
                </td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td"><font color="red">*</font>是否欠款:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="isPay" type="text" id="isPay" ltype="text"  validate="{required:true}"/>
                </td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td"><font color="red">*</font>审核状态:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="auditState" type="text" id="auditState" ltype="text"  value="通过" validate="{required:true}"/>
                </td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">备注:</td>
                <td align="left" class="l-table-edit-td"> 
                <textarea cols="100" rows="4" class="l-textarea" name="remark" id="remark" style="width:300px" ></textarea>
                </td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td"></td>
                <td align="left" class="l-table-edit-td"> 
                 <input type="submit" value="提交" class="l-button l-button-submit" /> 
                </td>
            </tr>
          
        </table>


    </form>
    <div style="display:none">
    <!--  数据统计代码 --></div>

    
</body>

<script type="text/javascript">
  function ajaxUpdate(){//提交表单内容
      		 
      		
        	  $.blockUI({message:'<h4><img src="../images/loading.gif" /> 系统正在提交中……</h4>' }); 
	         var params=$('#form1').serialize().replace(/\+/g, ' '); //这里直接就序列化了表单里面的值；很方便  
			 params = decodeURIComponent(params,true);	      
			 params = encodeURIComponent(encodeURIComponent(params)).replace(/%253D/g,'=').replace(/%2526/g,'&');
		     
			
		      $.ajax({   //把表单内容插入数据库
		              url :'financeAdd',  //后台处理程序   
		              type:'post',    //数据发送方式   
		              dataType:'text',   //接受数据格式   
		              data:params,   //要传递的数据；就是上面序列化的值   
		              success:submit_Result //回传函数(这里是函数名)    
		       });  
	             
 }   
        
   function submit_Result(result){ //回传函数实体，参数为XMLhttpRequest.responseText   
  		$.unblockUI();
  		
  		if(result=='success') {
       		 window.parent.parent.$("#maingrid4").ligerGrid().loadData(true);		
       		  	$.ligerDialog.success('提交成功，请关闭对话框！','提示',function(){		  		
	   			 var parentWindow_body =  $(window.parent.parent.document).find("body");if(! parentWindow_body.find(".dealWithMask").length){parentWindow_body.append("<input type=text class=dealWithMask style=height:0;border:none/>");}parentWindow_body.find(".dealWithMask").focus(); window.parent.parent.$.ligerDialog.close();
  		      });	

       		
  		}else if(result=='infoLoss'){
  			$.ligerDialog.error('信息填写不完整，请重试！');
 		} else if(result=='error'){
  			$.ligerDialog.error('系统异常，联系管理员！');
 		}else{
 			$.ligerDialog.error(result);
 		}
 }
 
</script>
</html>
