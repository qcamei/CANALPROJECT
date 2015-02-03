<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/operatorUser.tld" prefix="operatorUser" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>渠道员工信息修改页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="com_css/css/common.css" rel="stylesheet" type="text/css"/>
  <link href="com_css/css/serviceAccept.css" rel="stylesheet" type="text/css"/>
 <link href="com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
        <link href="com_css/LigerUILib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
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
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>     
 	<script src="com_css/uploadify/swfobject.js" type="text/javascript" ></script>
	<script src="com_css/uploadify/jquery.uploadify.v2.1.4.min.js" type="text/javascript"></script>
	
   <link href="com_css/jqueryUI/themes/base/jquery.ui.all.css" rel="stylesheet">
	<script src="com_css/jqueryUI/ui/jquery.ui.core.js"></script>
	<script src="com_css/jqueryUI/ui/jquery.ui.widget.js"></script>
	<script src="com_css/jqueryUI/ui/jquery.ui.position.js"></script>
	<script src="com_css/jqueryUI/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript">
$(function(){
	
	$("#userInDate").ligerDateEditor({showTime: true});
	
	$("#userOutDate").ligerDateEditor({showTime: true});
	
	 $("#userBirthday").ligerDateEditor({showTime: true});

	 $("#userKind").ligerComboBox({url :'selectItemQueryAction?type=user_kind',isMultiSelect: false});
		
	 $("#userState").ligerComboBox({url :'selectItemQueryAction?type=user_state',isMultiSelect: false});
	 
	 $("#userAuthority").ligerComboBox({url :'selectItemQueryAction?type=user_authority',isMultiSelect: false});
	 
	 $("#userWay").ligerComboBox({url :'selectItemQueryAction?type=user_way',isMultiSelect: false});
	 
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
                    f_add();
                 }
        });
    
	$("#form1").ligerForm();
});
function f_close(){
	top.f_closeTab('canalUserModifyAction');
}
</script>
<style type="text/css">
	 body{ font-size:13px;background:#F0F0F0}
	 .hehe{text-align:right;}
     .l-table-edit-td{ padding:2px;}
     .l-button-submit{width:80px; float:left; margin-left:50px; padding-bottom:2px;} 
  </style>
</head>
<body>
<form action="" method="post" name = "form1" id = "form1" >
	<table border="0" cellspacing="0"  class="l-table-edit">
   <tr>
	<td colspan="6" align="center" ><h3 class="meTitle" >重要信息</h3></td>
  </tr>
  <tr>
  <td class="hehe"  width="150"><font color="red" size="4">*</font>员工姓名：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text"  id="userName" name="userName" value="${request.userName}"    readonly="readonly"  validate="{required:true}" /></td>
	<td width="200px"></td>
	<td class="hehe" width="150">姓名拼音：</td>
	<td  class="l-table-edit-td"><input type="text"  ltype="text"  id="userPinyin" name="userPinyin"    readonly="readonly"  value="${request.userPinyin }" /></td>
	<td width="200px" ></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>员工类别：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text"  id="userKind" name="userKind" value="${request.userKind }"  readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
	<td class="hehe">工号状态：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="userState" name="userState" value="${request.userState }"   readonly="readonly"  /></td>
	<td></td>
  </tr>
  <tr>
  <td class="hehe"><font color="red" size="4">*</font>归属渠道名称：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="canalName" name="canalName"  value="${request.canalName }"  readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
	<td class="hehe">归属代理商：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userAgent" name="userAgent" value="${request.userAgent }"/></td>
	<td></td>
  </tr>
  <tr>
	<td colspan="6" align="center"><h3 class="meTitle" >员工基本信息</h3></td>
  </tr>
  <tr>	
	<td class="hehe">身份证号：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userIdCard" name="userIdCard"   readonly="readonly"  value="${request.userIdCard }" /></td>
	<td></td>
	<td class="hehe">工号权限：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userAuthority" name="userAuthority" value="${request.userAuthority }"  /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>所在部门：</td>
	<td  class="l-table-edit-td"><input type="text"  ltype="text"  id="userDept" name="userDept" value="${request.userDept }"  readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
	<td class="hehe">邮箱：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userEmail" name="userEmail"  value="${request.userEmail }"   readonly="readonly"  /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe">计费角色：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="userRole" name="userRole" value="${request.userRole }"  readonly="readonly"   /></td>
	<td></td>
	<td class="hehe">计费工号：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userCountId" name="userCountId" value="${request.userCountId }"  readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>性别：</td>
	<td class="l-table-edit-td">
		<input type="radio" name="userSex" value="男"   readonly="readonly"  validate="{required:true}" 
		     <c:if test="${userSex=='男' }">checked="checked"</c:if> />男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" name="userSex" value="女"  readonly="readonly"   validate="{required:true}" 
		    <c:if test="${userSex=='女' }">checked="checked"</c:if> />女</td>
	<td></td>
	<td class="hehe">生日：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userBirthday" name="userBirthday" value="${request.userBirthday }"  readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe">籍贯：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userNativeplace" name="userNativeplace" value="${request.userNativeplace }"   readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">联系电话：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="userPhone" name="userPhone" value="${request.userPhone }"   readonly="readonly"  /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe">文化程度：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userEducation" name="userEducation" value="${request.userEducation }"   readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">技能证书：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userCertification" name="userCertification"  value="${request.userCertification }"  readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe">入职时间：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userInDate" name="userInDate" value="${request.userInDate }"  readonly="readonly"   /></td>
	<td></td>
	<td class="hehe">离职时间：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="userOutDate" name="userOutDate"  value="${request.userOutDate }"  readonly="readonly"  /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe">住址：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userAddress" name="userAddress" value="${request.userAddress }"   readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">岗位：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userPosition" name="userPosition" value="${request.userPosition }"  readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
	<td colspan="6" align="center"><h3 class="meTitle" >其他信息</h3></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>用工方式：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="userWay" name="userWay" value="${request.userWay }"   readonly="readonly"  validate="{required:true}" /></td>
	<td></td>
	<td class="hehe">地区名称：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="userRegionName" name="userRegionName" value="${request.userRegionName }"  readonly="readonly"   /></td>
	<td></td>
  </tr>
  <!-- <tr>
	<td class="hehe"><font color="red" size="4">*</font>操作人：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="operUser" name="operUser" value="${request.operUser }"  readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>操作部门ID：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="deptId" name="deptId" value="${request.deptId }"   readonly="readonly"  validate="{required:true}" /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>操作部门名称：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="deptName" name="deptName" value="${request.deptName }"   readonly="readonly"  validate="{required:true}" /></td>
	<td></td>
	<td class="hehe">备注：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="remark" name="remark"  value="${request.remark }"   readonly="readonly"  /></td>
	<td></td>
  </tr> -->
  <tr>
			<td class="hehe">备注：</td>
				<td class="l-table-edit-td"><textarea name="remark"
						id="remark" style="width:200px; height:50px;"> ${request.remark} </textarea></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
  </table>
</form>

</body>
</html>
