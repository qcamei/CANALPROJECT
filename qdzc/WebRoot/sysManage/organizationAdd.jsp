<%@ page contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>增加部门</title>
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
          	$("#parentDept").hide();
          	$("#area").ligerComboBox({url :'areaQueryAction', isMultiSelect: false,valueField: 'areaId'});	
          	$("#deptLevel").ligerComboBox({url :'deptLevelQueryAction', isMultiSelect: false});	
          	$("#parentDeptName").ligerComboBox({url :'parentDeptNameQueryAction', isMultiSelect: false,valueField: 'hidParentDeptId'});	
        
            $("form").ligerForm();
            
            $(".l-button-test").click(function() {
                alert(v.element($("#txtName")));
            }); 
        });  
        
          function checklegal(){    //验证账号、密码是否合法
      		var cd=/^[A-Za-z0-9]\w{5,19}$/;
      		var us=/^[A-Za-z0-9]\w{2,19}$/;
      		var pn=/^[1-9]\d{5}(?!\d)$/;
      		var em= /^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/;
      		
      		var postNum = document.getElementById("postNum").value;
      		var email = document.getElementById("email").value;
      		if(postNum != ""){
    	  		  if(pn.test(postNum) == false)
    	  		  {
    	  		    alert("请正确填写邮政编码，邮政编码为6位数字组成。");
    	  		    $("#postNum").val("");
    	  		    return false;
    	  		  }
      		  }
      		if(email != ""){
    	  		  if(em.test(email) == false)
    	  		  {
    	  		    alert("请正确填写邮箱地址。");
    	  		    $("#email").val("");
    	  		    return false;
    	  		  }
      		  }
      		  //固定电话
      		var ab2 = /(\d{3}-\d{8})|(\d{4}-\d{7})/;
  		   var phoneNum = document.getElementById("phoneNum").value;
  		  if(phoneNum != ""){
	  		  if(ab2.test(phoneNum) == false)
	  		  {
	  		    alert("请正确填写固定电话号码,例如 0511-4405222 或 021-87888822");
	  		    return false;
	  		  }
  		  }
  		  	//验证电话号码手机号码，包含至今所有号段 
  		  var ab=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
  		  var contactNumber = document.getElementById("contactNumber").value;
  		  if(contactNumber != ""){
	  		  if(ab.test(contactNumber) == false)
	  		  {
	  		    alert("请正确填写移动电话号码,例如 13812345678");
	  		    return false;
	  		  }
  		  }
  		  
      		/*
      		var id = document.getElementById("userId").value;
      		var psw = document.getElementById("userPsw").value;
      		if(id != ""){
  	  		  if(us.test(id) == false)
  	  		  {
  	  		    alert("请正确填写用户登录名,登录名长度3~20位，且只能以字母或数字开头，账号中只能包含字母、数字、下划线。");
  	  		    $("#userId").val("");
  	  		    return false;
  	  		  }
    		  }
      		
      		if(psw != ""){
    	  		  if(cd.test(psw) == false)
    	  		  {
    	  		    alert("请正确填写用户密码,密码长度6~20位，且只能以字母或数字开头，密码中只能包含字母、数字、下划线。");
    	  		    $("#userPsw").val("");
    	  		    return false;
    	  		  }
      		  }*/
      		
          }
          
		function checkDeptLevel(){
			var deptLevel = $("#deptLevel").val();
			if(deptLevel == '支/分局'){
				$("#parentDept").show();
			}else{
				$("#parentDept").hide();
			}
		
		}
		
        
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

        <table cellpadding="0" cellspacing="0" class="l-table-edit" width="100%">
        	<tr>
                <td align="right" class="l-table-edit-td"><font color="#ff0000">*</font>部门类型:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="deptLevel" type="text" id="deptLevel" ltype="text" validate="{required:true}" onchange="checkDeptLevel()" />
                </td>
                
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td"><font color="#ff0000">*</font>部门名称:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="deptName" type="text" id="deptName" ltype="text" validate="{required:true}" />
                	
                </td>
                
            </tr>
            <!-- 如果是支分局，需要选择父部门，表示属于哪个分公司 -->
          	<tr id = "parentDept">
                <td align="right" class="l-table-edit-td"><font color="#ff0000">*</font>父部门名称:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="hidParentDeptId" type="hidden" id="hidParentDeptId" ltype="text"  />
               	 	<input name="parentDeptName" type="text" id="parentDeptName" ltype="text"  />
                </td>
                
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td"><font color="#ff0000">*</font>区域:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="area" type="text" id="area" ltype="text" validate="{required:true}" />
                </td>                
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">部门固定电话:</td>
                <td align="left" class="l-table-edit-td"><input name="phoneNum" type="text" id="phoneNum" ltype="text" />格式如:0830-4405222</td>
                
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td"><font color="#ff0000">*</font>部门移动电话:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="contactNumber" type="text" id="contactNumber" ltype="text" validate="{required:true}" />
                </td>
                
            </tr>
            
            

            <tr>
                <td align="right" class="l-table-edit-td" valign="top"><font color="#ff0000">*</font>是否可用:</td>
                <td align="left" class="l-table-edit-td">
                    <input id="isUseable" type="radio" name="isUseable" value="可用" checked="checked" />可用 
                    <input id="isUseable" type="radio" name="isUseable" value="不可用" />不可用
                </td>
            </tr> 
            <tr>
                <td align="right" class="l-table-edit-td">地址:</td>
                <td align="left" class="l-table-edit-td"> 
                <textarea cols="100" rows="4" class="l-textarea" name="address" id="address" style="width:300px" ></textarea>
               </td>
            </tr>
           
             <tr>
                <td align="right" class="l-table-edit-td">邮编:</td>
               
               <td align="left" class="l-table-edit-td">
               		<input name="postNum"  type="text" id="postNum" ltype="text" onblur="checklegal()"/>
               </td>
                
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">Emai:</td>
               
               <td align="left" class="l-table-edit-td">
               		<input name="email"  type="text" id="email" ltype="text" onblur="checklegal()"/>
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
      		 
      		 var deptLevel = $("#deptLevel").val();
			if(deptLevel == '支/分局'){
				var parentDeptName = document.getElementById("parentDeptName").value;
				if(parentDeptName == "" || parentDeptName==null){
					alert("请选择支分局的父部门");
					return;
				}
			}
        	  $.blockUI({message:'<h4><img src="../images/loading.gif" /> 系统正在提交中……</h4>' }); 
	         var params=$('#form1').serialize().replace(/\+/g, ' '); //这里直接就序列化了表单里面的值；很方便  
			 params = decodeURIComponent(params,true);	      
			 params = encodeURIComponent(encodeURIComponent(params)).replace(/%253D/g,'=').replace(/%2526/g,'&');
		     
			
		      $.ajax({   //把表单内容插入数据库
		              url :'organizationDeptAddAction',  //后台处理程序   
		              type:'post',    //数据发送方式   
		              dataType:'text',   //接受数据格式   
		              data:params,   //要传递的数据；就是上面序列化的值   
		              success:submit_Result //回传函数(这里是函数名)    
		       });  
	             
 }   
        
   function submit_Result(result){ //回传函数实体，参数为XMLhttpRequest.responseText   
  		$.unblockUI();
  		
  		if(result=='success') {

       		 window.parent.$("#maingrid4").ligerGrid().loadData(true);		
       		  	$.ligerDialog.success('提交成功，请关闭对话框！','提示',function(){		  		
	   			 var parentWindow_body =  $(window.parent.document).find("body");if(! parentWindow_body.find(".dealWithMask").length){parentWindow_body.append("<input type=text class=dealWithMask style=height:0;border:none/>");}parentWindow_body.find(".dealWithMask").focus(); window.parent.$.ligerDialog.close();
  		      });	

       		
  		} else if(result=='error'){
  			$.ligerDialog.error('系统异常，联系管理员！');
 		}
 }
 
</script>
</html>
