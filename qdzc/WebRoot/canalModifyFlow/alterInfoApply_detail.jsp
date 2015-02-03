<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/operatorUser.tld" prefix="operatorUser" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改渠道信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="./com_css/css/common.css" rel="stylesheet" type="text/css"/>
<link href="./canalModifyFlow/canalInfoAdd.css" rel="stylesheet" type="text/css"/>
  <link href="./com_css/css/serviceAccept.css" rel="stylesheet" type="text/css"/>
 <link href="./com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="./com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
        <link href="./com_css/LigerUILib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="./com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>  
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>  
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>      
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>    
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
  <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="./com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="./com_css/js/jquery.blockUI.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>     
 	<script src="./com_css/uploadify/swfobject.js" type="text/javascript" ></script>
	<script src="./com_css/uploadify/jquery.uploadify.v2.1.4.min.js" type="text/javascript"></script>
   <link href="./com_css/jqueryUI/themes/base/jquery.ui.all.css" rel="stylesheet">
	<script src="./com_css/jqueryUI/ui/jquery.ui.core.js"></script>
	<script src="./com_css/jqueryUI/ui/jquery.ui.widget.js"></script>
	<script src="./com_css/jqueryUI/ui/jquery.ui.position.js"></script>
	<script src="./com_css/jqueryUI/ui/jquery.ui.autocomplete.js"></script>
	<script src="./com_css/js/PinYin.js"></script>
<script type="text/javascript">
$(function(){
	$.metadata.setType("attr", "validate");
	$("input").attr("readonly","readonly");
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
	 
	$("#form1").ligerForm(); 
	
	var date1="${request.rentStartDate }";
	var date2="${request.rentEndDate }";
	$("#rentStartDate").val(date1.substring(0,10));
	$("#rentEndDate").val(date2.substring(0,10));
	$("#rentStartDateOld").val(date1.substring(0,10));
	$("#rentEndDateOld").val(date2.substring(0,10));
	 
});
function downAffix(){
		  $.ligerDialog.open({ 
    	  title: '附件查看',
    	  height: 400, 
    	  width: 500, 
    	  url: 'canalModifyFlow/showAffix.jsp?id=${request.guaranteeReceiptScanUrl}',
    	   showMax: true, 
    	   isResize: true,      	 
    	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
      }); 
}
function f_closeAddWindow(item, dialog){
   $.ligerDialog.confirm('确认要关闭窗口吗', function (yes){
             if(yes)
                 dialog.close();
     });
}
</script>
</head>

<body>
<div id="wrapper">
<form action="" method="post" name = "form" id = "form1" >
<ul>
<h2>渠道信息</h2>
	<div class="dash"></div>
	<li>
		<font>*</font><span>城乡：</span>
			<input type="hidden" id="inId" name="inId" value="${request.inId }"/>
			<input type="hidden" id="canalId" name="canalId" value="${request.canalId }"/>
			<input name="canalForm" type="text" id="canalForm" validate="{required:true}" onchange="getCanalType()"  value="${request.canalForm}" />
			<input name="canalFormOld" type="hidden"   value="${request.canalForm}" />
		</li>
		<li>
			<font>*</font><span>管理属性：</span>
			<input name="canalType"  type="text" id="canalType" value="${request.canalType}" validate="{required:true}" onchange="getCanalProperty()"  onfocus="alamTxt('canalType','请先选择渠道形态！')" onblur="removeAlamTxt('canalType')" />
			<input name="canalTypeOld"  type="hidden"  value="${request.canalType}" />
		</li>
		<li>
			<font>*</font><span>分类名称：</span>
			<input name="canalProperty" type="text" id="canalProperty" value="${request.canalProperty}" onfocus="alamTxt('canalProperty','请先选择渠道类型！')" onblur="removeAlamTxt('canalProperty')" />
			<input name="canalPropertyOld"  type="hidden"  value="${request.canalProperty}" />
		</li>
		<h2>代理商信息</h2>
	<div class="dash"></div>
		<li class="add">
			<font>*</font><span>分公司：</span>
			 <input type="text" ltype="text" id="branchCompany" name="branchCompany"  value="${request.branchCompany }" onfocus="alamTxt('branchCompany','厅店所属分公司')" onblur="removeAlamTxt('branchCompany')" />
			<input name="branchCompanyOld"  type="hidden"  value="${request.branchCompany}" />
		</li>
		<li class="add">
			<font>*</font><span>代理商名称：</span>
			<input type="text"   ltype="text"  id="agentName" name="agentName"   value="${request.agentName }" onfocus="alamTxt('agentName','厅店名称')" onblur="removeAlamTxt('agentName')"/>
			<input name="agentNameOld"  type="hidden"  value="${request.agentName}" />
		</li>
		<li class="add">
			<font>*</font><span>代理商简称</span>
			<input type="text"   ltype="text" id="agentShortName" name="agentShortName"   value="${request.agentShortName }" validate="{required:true}" onfocus="alamTxt('agentShortName','厅店名称简称')" onblur="removeAlamTxt('agentShortName')" />
			<input name="agentShortNameOld"  type="hidden"  value="${request.agentShortName}" />
		</li>
		<li class="add">
			<font>*</font><span>管理部门：</span>
			<input type="text"  ltype="text"  id="agentDept" name="agentDept"  value="${request.agentDept }" onchange="getAgentLevel()"  />
			<input name="agentDeptOld"  type="hidden"  value="${request.agentDept}" />
		</li>
		<li class="add">
			<font class="agentLevel">*</font><span>代理商级别：</span>
			<input type="text"  ltype="text"  id="agentLevel" name="agentLevel"  value="${request.agentLevel }" onfocus="alamTxt('agentLevel','请先选择管理部门！')" onblur="removeAlamTxt('agentLevel')" />
			<input name="agentLevelOld"  type="hidden"  value="${request.agentLevel}" />
		</li>
		<li class="add">
			<font>*</font><span>公司类型：</span>
			<input type="text"  ltype="text"  id="companyType" name="companyType" value="${request.companyType }"  />
			<input name="companyTypeOld"  type="hidden"  value="${request.companyType}" />
		</li>
		<li class="add">
			<font>*</font><span>合作伙伴类型：</span>
			<input type="text"  ltype="text"  id="cooperationType" name="cooperationType" value="${request.cooperationType }"  />
			<input name="cooperationTypeOld"  type="hidden"  value="${request.cooperationType}" />
		</li>
		<li class="add">
			<font>*</font><span>法人代表：</span>
			<input type="text"  ltype="text"  id="legalPerson" name="legalPerson"  value="${request.legalPerson }" onfocus="alamTxt('legalPerson','法人代表名字')" onblur="removeAlamTxt('legalPerson')"  />
			<input name="legalPersonOld"  type="hidden"  value="${request.legalPerson}" />
		</li>
		<li class="add">
			<font>*</font><span>法人代表联系电话：</span>
			<input type="text"  ltype="text"  id="legalPhone" name="legalPhone" value="${request.legalPhone }" onblur="checklegal('legalPhone')"  onfocus="alamTxt('legalPhone','厅店法人代表联系电话')"  />
			<input name="legalPhoneOld"  type="hidden"  value="${request.legalPhone}" />
		</li>
		<li class="add">
			<font>*</font><span>法人证件类型：</span>
			<input type="text"  ltype="text"  id="legalCardType" name="legalCardType" value="${request.legalCardType }" onfocus="alamTxt('legalCardType','身份证')" onblur="removeAlamTxt('legalCardType')" />
			<input name="legalCardTypeOld"  type="hidden"  value="${request.legalCardType}" />
		</li >
		<li class="add">
			<font>*</font><span>法人证件号码：</span>
			<input type="text"   ltype="text" id="legalIdCard" name="legalIdCard"  value="${request.legalIdCard }"  onfocus="alamTxt('legalIdCard','法人代表身份证号码')" onblur="removeAlamTxt('legalIdCard')" />
			<input name="legalIdCardOld"  type="hidden"  value="${request.legalIdCard}" />
		</li>
		
		<li class="add">
			<font>*</font><span>负责人姓名：</span>
			<input type="text"  ltype="text"  id="ondutyPerson" name="ondutyPerson"  value="${request.ondutyPerson }" onfocus="alamTxt('ondutyPerson','代理商名字')" onblur="removeAlamTxt('ondutyPerson')" />
			<input name="ondutyPersonOld"  type="hidden"  value="${request.ondutyPerson}" />
		</li>
		<li class="add">
			<font>*</font><span>负责人联系电话：</span>
			<input type="text"  ltype="text"  id="ondutyPersonPhone" name="ondutyPersonPhone"  value="${request.ondutyPersonPhone }"  onfocus="alamTxt('ondutyPersonPhone','代理商电话')" onblur="checklegal('ondutyPersonPhone')"  />
			<input name="ondutyPersonPhoneOld"  type="hidden"  value="${request.ondutyPersonPhone}" />
		</li>		
		
		  <h2>渠道信息</h2> 
		  <div class="dash"></div>
		<li>
			<font>*</font><span>渠道申请名称：</span>
			<input name="canalName" type="text" id="canalName" ltype="text"  value="${request.canalName }" validate="{required:true}" onfocus="alamTxt('canalName','渠道名称')" onblur="removeAlamTxt('canalName')" />
			<input name="canalNameOld"  type="hidden"  value="${request.canalName}" />
		</li>
		<li>
			<font>*</font><span>区域名称：</span>
			<input name="areaName" type="text" id="areaName"  validate="{required:true}" value="${request.areaName }" />
			<input name="areaNameOld"  type="hidden"  value="${request.areaName}" />
		</li>
		
		<li>
			<font>*</font><span>区域特征：</span>
			<input name="regionCharacter" type="text" id="regionCharacter" validate="{required:true}" value="${request.regionCharacter }"/>
			<input name="regionCharacterOld"  type="hidden"  value="${request.regionCharacter}" />
		</li>
		<li>
			<font>*</font><span>渠道地址：</span>
			<input name="canalAddress" type="text" id="canalAddress"   value="${request.canalAddress }"  validate="{required:true}" onfocus="alamTxt('canalAddress','厅店详细地址')" onblur="removeAlamTxt('canalAddress')"  />
			<input name="canalAddressOld"  type="hidden"  value="${request.canalAddress}" />
		</li>
		<li>
			<font>*</font><span>渠道归属部门：</span>
			<input name="canalDept" type="text" id="canalDept"  value="${request.canalDept }" validate="{required:true}" onfocus="alamTxt('canalDept','渠道所属部门')" onblur="removeAlamTxt('canalDept')"/>
			<input name="canalDeptOld"  type="hidden"  value="${request.canalDept}" />
		</li>
		<li>
			<font>*</font><span>对口支撑人(客户经理)：</span>
			<input name="canalUserName"  type="text" id="canalUserName"  value="${request.canalUserName }" validate="{required:true}" />
			<input name="canalUserNameOld"  type="hidden"  value="${request.canalUserName}" />
		</li>
		<li>
			<font>*</font><span>对口支撑人电话：</span>
			<input name="canalUserPhone" type="text" id="canalUserPhone"   value="${request.canalUserPhone }" validate="{required:true}" onblur="checklegal('canalUserPhone')"/>
			<input name="canalUserPhoneOld"  type="hidden"  value="${request.canalUserPhone}" />
		</li>
	
		<li class="add">
			<font >*</font><span>开户银行：</span>
			<input type="text"   ltype="text" id="startBank" name="startBank"  value="${request.startBank }" onfocus="alamTxt('startBank','邮政银行')" onblur="removeAlamTxt('startBank')"  />
			<input name="startBankOld"  type="hidden"  value="${request.startBank}" />
		</li>
		<li class="add">
			<font >*</font><span>银行账号：</span>
			<input type="text"  ltype="text"  id="bankAccountId" name="bankAccountId"   value="${request.bankAccountId }" onfocus="alamTxt('bankAccountId','银行卡号')" onblur="removeAlamTxt('bankAccountId')"  />
			<input name="bankAccountIdOld"  type="hidden"  value="${request.bankAccountId}" />
		</li>
		<li>
			<font>&nbsp;</font><span>翼支付账号：</span>
			<input name="inCollectNumber" type="text" id="inCollectNumber" value="${request.inCollectNumber }"  />
			<input name="inCollectNumberOld"  type="hidden"  value="${request.inCollectNumber}" />
		</li>
	
		<li>
			<font>*</font><span>城乡标识：</span>
			<input name="urbanIdetity" type="text" id="urbanIdetity"  value="${request.urbanIdetity }" validate="{required:true}" />
			<input name="urbanIdetityOld"  type="hidden"  value="${request.urbanIdetity}" />
		</li>
		<li>
			<font class="kf">*</font><span>产权归属：</span>
			<input name="expireType" type="text" id="expireType" value="${request.expireType }" />
			<input name="expireTypeOld"  type="hidden"  value="${request.expireType}" />
		</li>
		<li>
			<font class="kf">*</font><span>营业面积：</span>
			<input name="businessArea" type="text" id="businessArea"   value="${request.businessArea }" />
			<input name="businessAreaOld"  type="hidden"  value="${request.businessArea}" />
		</li>
		
		<li>
			<font class="kf">*</font><span>房租合同开始时间：</span>
			<input name="rentStartDate" type="text" id="rentStartDate"   />
			<input name="rentStartDateOld"  type="hidden" id="rentStartDateOld" />
		</li>
		<li>
			<font class="kf">*</font><span>房租合同结束时间：</span>
			<input name="rentEndDate"   type="text" id="rentEndDate"  />
			<input name="rentEndDateOld"  type="hidden"  id="rentEndDateOld" />
		</li>
		<li>
			<font class="kf">*</font><span>首期房租补贴(元/月)：</span>
			<input name="firstRentAllowance" type="text" id="firstRentAllowance"   value="${request.firstRentAllowance }" />
			<input name="firstRentAllowanceOld"  type="hidden"  value="${request.firstRentAllowance}" />
		</li>
		<li>
			<font class="kf">*</font><span>首期装修补贴金额(元/月)：</span>
			<input name="firstDecorationAllowance" type="text" id="firstDecorationAllowance"  value="${request.firstDecorationAllowance }" />
			<input name="firstDecorationAllowanceOld"  type="hidden"  value="${request.firstDecorationAllowance}" />
		</li>
		<li>
			<font>*</font><span>是否交保证金：</span>
			<input name="isMoney" type="text" id="isMoney"  value="${request.isMoney }" />
			<input name="isMoneyOld"  type="hidden"  value="${request.isMoney}" />
		</li>
		<li>
			<font class="isMoney">&nbsp;</font><span>业务类型：</span>
			<input name="taskType" type="text" id="taskType"  value="${request.taskType }"   />
			<input name="taskTypeOld"  type="hidden"  value="${request.taskType}" />
		</li>
		<li>
			<font  class="isMoney">&nbsp;</font><span>保证金金额：</span>
			<input name="guaranteeAmount" type="text" id="guaranteeAmount" readonly="readonly"   value="${request.guaranteeAmount }"/>
			<input name="guaranteeAmountOld"  type="hidden"  value="${request.guaranteeAmount}" />
		</li>
		<li>
			<font >*</font><span>是否变更保证金：</span>
			<input name="isAlterMoney" type="text" id="isAlterMoney" value="是" validate="{required:true}"  />
		</li>
		<li>
			<font >*</font><span>变更金额：</span>
			<input name="moneyAmount" type="text" id="moneyAmount" validate="{required:true}"  />
		</li>
		<li>
			<font >*</font><span>变更备注：</span>
			<input name="moneyRemark" type="text" id="moneyRemark" validate="{required:true}"  />
		</li>
		<li>
			<font  class="isMoney">&nbsp;</font><span>缴款人：</span>
			<input name="guaranteeUser" type="text" id="guaranteeUser"  value="${request.guaranteeUser }" />
			<input name="guaranteeUserOld"  type="hidden"  value="${request.guaranteeUser}" />
		</li>
		<li>
			<font  class="isMoney">&nbsp;</font><span>缴款时间：</span>
			<input name="guaranteeTime" type="text" id="guaranteeTime"  value="${request.guaranteeTime }" />
			<input name="guaranteeTimeOld"  type="hidden"  value="${request.guaranteeTime}" />
		</li>
		
		<li>
			<font  class="isMoney">&nbsp;</font><span>发票扫描件/合同扫描件：</span>
			<%String guaranteeReceiptScanUrl =(String) request.getAttribute("guaranteeReceiptScanUrl");
					if(guaranteeReceiptScanUrl != null && guaranteeReceiptScanUrl.length() > 1 ){
			%>
			<input name="download" type="button"  id="download"  onclick = "downAffix()"  value = "查看图片"/>
 			<%} %>
</li>
		<h2>渠道营业员信息：</h2>
		<div class="dash"></div>
		
		<li>
			<font>*</font><span>营业员姓名：</span>
			<input type="text"   ltype="text"  id="userName" name="userName" value="${request.userName }" validate="{required:true}" onblur="getPinyin()"    />
			<input name="userNameOld"  type="hidden"  value="${request.userName}" />
		</li>
		<li>
			<span>姓名拼音：</span>
			<input type="text"  ltype="text"  id="userPinyin" readonly="readonly"  value="${request.userPinyin }" name="userPinyin"  />
			<input name="userPinyinOld"  type="hidden"  value="${request.userPinyin}" />
		</li>
		<li>
			<font>*</font><span>性别：</span>
			<input type="radio" name="userSex"  value="男"  checked="checked" 
			<c:if test="${userSex=='男' }">checked="checked"</c:if>  />男
            <input type="radio" name="userSex" value="女"  
            <c:if test="${userSex=='女' }">checked="checked"</c:if>  />女
            <input name="userSexOld"  type="hidden"  value="${request.userSex}" />
		</li><%--
				<h2>资金归结</h2>
		<div class="dash"></div>
		<li>
			<font class="nima">*</font><span>户名：</span>
			<input type="text"  ltype="text"  id="hm"  name="hm" validate="{required:true}" value="${request.hm }" />
		</li>
		<li>
			<font class="nima">*</font><span>开户银行：</span>
			<input type="text"  ltype="text"  id="khyh"  name="khyh"  validate="{required:true}" value="${request.khyh }"  />
		</li>
		<li>
			<font class="nima">*</font><span>卡号：</span>
			<input type="text"  ltype="text"  id="kh"  name="kh" validate="{required:true}" value="${request.kh }"  />
		</li>
		<li>
			<font class="nima">*</font><span>身份证号：</span>
			<input type="text"  ltype="text"  id="sfzh"  name="sfzh"  validate="{required:true}" value="${request.sfzh }"  />
		</li>
	--%></ul>
</form>
</div>
</body>
</html>