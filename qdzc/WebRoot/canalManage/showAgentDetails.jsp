<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/operatorUser.tld" prefix="operatorUser" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改代理商信息</title>
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
	$('input').attr("readonly","readonly");

	$("#form1").ligerForm();
});
function f_close(){
	top.f_closeTab('agentModifyAction');
}
</script>
<script type="text/javascript">

 
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
	<td colspan="6" align="center" ><h3 class="meTitle" >代理商信息</h3></td>
  </tr>
  <tr>
	<td class="hehe" width="180px"><font color="red" size="4">*</font>分公司：</td>
	<td class="l-table-edit-td" ><input type="text" ltype="text" id="branchCompany" name="branchCompany"  value="${request.branchCompany }"  validate="{required:true}" /></td>
	<td width="200px" ></td>
	<td class="hehe" width="180px">代理商编码：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text"  id="agentId" name="agentId"  value="${request.agentId }"     readonly="readonly"  /></td>
	<td width="200px"></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>代理商名称：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text"  id="agentName" name="agentName"  value="${request.agentName }"     readonly="readonly"  validate="{required:true}" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>代理商简称：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="agentShortName" name="agentShortName"   value="${request.agentShortName }"    readonly="readonly"  validate="{required:true}" /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>管理部门：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="agentDept" name="agentDept"  value="${request.agentDept }"   readonly="readonly"   validate="{required:true}" /></td></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>代理商级别：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="agentLevel" name="agentLevel"   value="${request.agentLevel }"   readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>公司类型：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="companyType" name="companyType"   value="${request.companyType }"     readonly="readonly" validate="{required:true}" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>合作伙伴类型：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="cooperationType" name="cooperationType"  value="${request.cooperationType }"    readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
  </tr>
  <tr>
	<td colspan="6" align="center"><h3 class="meTitle" >法人信息</h3></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>法人代表：</td>
	<td  class="l-table-edit-td"><input type="text"  ltype="text"  id="legalPerson" name="legalPerson"   value="${request.legalPerson }"     readonly="readonly" validate="{required:true}" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>法人代表联系电话：</td>
	<td  class="l-table-edit-td"><input type="text"  ltype="text"  id="legalPhone" name="legalPhone"   value="${request.legalPhone }"     readonly="readonly" validate="{required:true}" /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>法人证件类型：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="legalCardType" name="legalCardType"   value="${request.legalCardType }"   readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>法人证件号码：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="legalIdCard" name="legalIdCard"   value="${request.legalIdCard }"   readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>管理模式：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="manageModel" name="manageModel"  value="${request.manageModel }"   readonly="readonly"    validate="{required:true}" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>是否发11888卡：</td>
	<td class="l-table-edit-td">
		<input type="radio" name="is11888Card" value="是"    readonly="readonly"    validate="{required:true}"
		<c:if test="${is11888Card=='是' }">checked="checked"</c:if>  />是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" name="is11888Card" value="否"    readonly="readonly"   validate="{required:true}" 
		<c:if test="${is11888Card=='否' }">checked="checked"</c:if> />否</td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>负责人姓名：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="ondutyPerson" name="ondutyPerson"   value="${request.ondutyPerson }"   readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>负责人联系电话：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="ondutyPersonPhone" name="ondutyPersonPhone"   value="${request.ondutyPersonPhone }"     readonly="readonly" validate="{required:true}" /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe"><font color="red" size="4">*</font>开户银行：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="startBank" name="startBank"  value="${request.startBank }"    readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>银行账号：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="bankAccountId" name="bankAccountId"   value="${request.bankAccountid }"   readonly="readonly"   validate="{required:true}" /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe">上级合作伙伴编码：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="parentId" name="parentId"   value="${request.parentId }"    readonly="readonly"   /></td>
	<td></td>
	<td class="hehe">上级合作伙伴名称：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="parentName" name="parentName"    value="${request.parentName }"    readonly="readonly"  /></td>
	<td></td>
  </tr>
  <tr>
	<td class="hehe">公司办公地址：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="companyAddress" name="companyAddress"    value="${request.companyAddress }"    readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">负责人联系地址：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="ondutyPersonAddress" name="ondutyPersonAddress"   value="${request.ondutyPersonAddress }"     readonly="readonly"  /></td>
	<td></td>
  </tr>
  <tr>
  	<td class="hehe">负责人QQ：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="ondutyPersonQqnum" name="ondutyPersonQqnum"   value="${request.ondutyPersonQqnum }"    readonly="readonly"   /></td>
	<td></td>
	<td class="hehe">邮政编码：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="postCode" name="postCode"   value="${request.postCode }"   readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
  	<td class="hehe">传真：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="agentFax" name="agentFax"   value="${request.agentFax }"    readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">电子邮箱：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="agentEmail" name="agentEmail"  value="${request.agentEmail }"     readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
	<td colspan="6" align="center"><h3 class="meTitle" >银行信息</h3></td>
  </tr>
  <tr>
  	<td class="hehe">付款方式：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="payWay" name="payWay"    value="${request.payWay }"    readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">银行账户名称：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="bankAccountName" name="bankAccountName"   value="${request.bankAccountName }"    readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
  	<td class="hehe">委托收款方开户银行：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="weituoStartBank" name="weituoStartBank"    value="${request.weituoStartBank }"    readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">委托收款方帐号：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="weituoBankAccount" name="weituoBankAccount"   value="${request.weituoBankAccount }"     readonly="readonly"  /></td>
	<td></td>
  </tr>
  <tr>
  	<td class="hehe">委托收款方名称：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="weituoBankName" name="weituoBankName"   value="${request.weituoBankName }"    readonly="readonly"   /></td>
	<td></td>
	<td class="hehe">支付对象：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="payObject" name="payObject"   value="${request.payObject }"    readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
  	<td class="hehe">注册资本：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="registerMoney" name="registerMoney"   value="${request.registerMoney }"     readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">工商登记时间：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="registerDate" name="registerDate"   value="${request.registerDate }"    readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
  	<td class="hehe">法人营业执照编号：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="businessLicenseId" name="businessLicenseId"    value="${request.businessLicenseId }"    readonly="readonly"  /></td>
	<td></td>
	<td class="hehe">税务登记证编号：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="taxRegisteId" name="taxRegisteId"   value="${request.taxRegisteId }"    readonly="readonly"   /></td>
	<td></td>
  </tr>
  <tr>
  	<td class="hehe">银行开户许可证编号：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="bankPermisionId" name="bankPermisionId"  value="${request.bankPermisionId }"    readonly="readonly"    /></td>
	<td></td>
	<td class="hehe">组织机构代码证编号：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="organizationCodeId" name="organizationCodeId"   value="${request.organizationCodeId }"    readonly="readonly"   /></td>
	<td></td>
  </tr>
  <!-- <tr>
  	<td class="hehe"><font color="red" size="4">*</font>操作人：</td>
	<td class="l-table-edit-td"><input type="text"   ltype="text" id="operUser" name="operUser"  value="${request.operUser }"   validate="{required:true}"    readonly="readonly" /></td>
	<td></td>
	<td class="hehe"><font color="red" size="4">*</font>操作部门：</td>
	<td class="l-table-edit-td"><input type="text"  ltype="text"  id="deptName" name="deptName"   value="${request.deptName }"  validate="{required:true}"    readonly="readonly" /></td>
	<td></td>
  </tr> -->
  <tr>
		<td class="hehe">备注：</td>
	    <td class="l-table-edit-td"><textarea name="remark" id="remark" style="width:200px; height:50px;"> ${request.remark} </textarea></td>
		<td width="130px"></td>
		<td class="hehe">工单号：</td>
		<td class="l-table-edit-td"><input type="text"   ltype="text" id="inId" name="inId"   value="${request.inId }"     readonly="readonly" /></td>
		<td></td>
  </tr>
  </table>
</form>

</body>
</html>
