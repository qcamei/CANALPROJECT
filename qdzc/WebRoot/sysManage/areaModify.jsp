<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
    <link href="com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="com_css/LigerUILib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
    <script src="com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>  
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>  
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>      
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>    
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="com_css/js/jquery.blockUI.js" type="text/javascript"></script>
     <style type="text/css">
        body{ font-size:12px;}        
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}        
    </style>
<script type="text/javascript">
$(function(){		
		var v = $("form").validate({
	            debug: true,
	            errorPlacement: function(lable, element) {
	                if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
	                else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
	                else lable.appendTo(element.parents("td:first").next("td"));
	            },
	            success: function(lable) {
	                lable.ligerHideTip();
	            },
	            submitHandler: function() {
	                $("form .l-text,.l-textarea").ligerHideTip();
	                ajaxUpdate();
	            }
	        });
		
		$("form").ligerForm();
		
		$("#areaId").val("${request.areaId}");
		$("#areaName").val("${request.areaName}");
	
		

		
	});
	 
	function alamTxt(id,txtContext){
	  $("#"+id).ligerTip({ content: txtContext, width: 150 });
	}
	  
	function removeAlamTxt(id){
	    $("#"+id).ligerHideTip();
	}
	function ajaxUpdate(){ 

	    $.blockUI({message:'<h5><img src="images/loading.gif" /> 系统正在提交中……</h5>' }); 
	     var params=$('#form1').serialize().replace(/\+/g, ' '); //这里直接就序列化了表单里面的值；很方便  
			 params = decodeURIComponent(params,true);	      
			 params = encodeURIComponent(encodeURIComponent(params)).replace(/%253D/g,'=').replace(/%2526/g,'&');
		     	  
	      $.ajax({   
	              url :'areaUpdateAction',  //后台处理程序   
	              type:'post',    //数据发送方式   
	              dataType:'text',   //接受数据格式   
	              data:params,   //要传递的数据；就是上面序列化的值   
	              success:submit_Result //回传函数(这里是函数名)    
	       });         
	}   
	function submit_Result(result){ //回传函数实体，参数为XMLhttpRequest.responseText   
	  $.unblockUI();
	  if(result=='success'){ 
	      if(result=='success') 
	      {  
	     	  window.parent.$("#maingrid4").ligerGrid().loadData(true);			
	     	 $.ligerDialog.success('提交成功，请关闭对话框！','提示',function(){		  		
	   			 var parentWindow_body =  $(window.parent.document).find("body");if(! parentWindow_body.find(".dealWithMask").length){parentWindow_body.append("<input type=text class=dealWithMask style=height:0;border:none/>");}parentWindow_body.find(".dealWithMask").focus(); window.parent.$.ligerDialog.close();
  		      });
	
	      } 
	
	    }else if(result == 'error'){  
	        $.ligerDialog.error('系统异常,提交失败,请重试或联系系统管理员！');   
	   }else{
	   		$.ligerDialog.error(result);  
	   }
	}  

</script>
</head>
<body style="padding:10px">
	<form id="form1" name="form1" method="post" action="">
		

	<table width="100%" style="margin-top:10px;">  
			<tr >
                <td align="right" class="l-table-edit-td"><font color="#ff0000">*</font>区域名称:</td>
                <td align="left" class="l-table-edit-td">
                	<input type="text" id="areaName" name="areaName"  validate="{required:true}"  />
                	<input type="hidden" id="areaId" name="areaId" />
                </td>
           </tr>  
           
           
               
      
        <tr>
			<td height="5" colspan="2"></td>
		</tr>  
          <tr>
               <td align="right" class="l-table-edit-td"></td>
                <td align="left" class="l-table-edit-td" >
                	<input type="submit" value="提交" class="l-button l-button-submit" />
                </td> 
              
          </tr>
	</table>
	
	</form>

</body>
</html>