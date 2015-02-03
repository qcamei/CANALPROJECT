<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/operatorUser.tld" prefix="operatorUser" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String inId = request.getParameter("inId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>渠道信息增加页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="./com_css/css/common.css" rel="stylesheet" type="text/css"/>
<link href="./canalManage/canalInfoAdd.css" rel="stylesheet" type="text/css"/>
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
	<script src="./canalManage/canalInfoAdd.js"></script>
<script type="text/javascript">
$(function(){
	$.metadata.setType("attr", "validate");
	var v = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element){
            if (element.hasClass("l-textarea")){
                element.ligerTip({ content: lable.html(), target: element[0] }); 
            }
            else if (element.hasClass("l-text-field")){
                element.parent().ligerTip({ content: lable.html(), target: element[0] });
            }
            else{
                lable.appendTo(element.parents("td:first").next("td"));
            }
        },
        success: function (lable){
            lable.ligerHideTip();
            lable.remove();
        },
        submitHandler: function (){
            $("form .l-text,.l-textarea").ligerHideTip();
            ajaxUpdate();
        }
	});
	$("#form1").ligerForm();
	
	var registerDate="${request.registerDate}";
	$("#registerDate").val(registerDate.substring(0,10));
	var userBirthday="${request.userBirthday}";
	$("#userBirthday").val(userBirthday.substring(0,10));
	var userInDate="${request.userInDate}";
	$("#userInDate").val(userInDate.substring(0,10));
	var userOutDate="${request.userOutDate}";
	$("#userOutDate").val(userOutDate.substring(0,10));
});
function ajaxUpdate() {
	$.blockUI({
		message : '<h5><img src="./images/loading.gif" /> 系统正在提交中……</h5>'
	});
	var params = $('#form1').serialize().replace(/\+/g, ' '); //这里直接就序列化了表单里面的值；很方便  
	params = decodeURIComponent(params, true);
	params = encodeURIComponent(encodeURIComponent(params)).replace(/%253D/g, '=').replace(/%2526/g, '&');
	$.ajax({
		url : 'canalModifyAction_0', //后台处理程序   
		type : 'post', //数据发送方式   
		dataType : 'text', //接受数据格式   
		data : params, //要传递的数据；就是上面序列化的值   
		success : submit_Result  //回传函数(这里是函数名)    
	});
}

function submit_Result(result) { //回传函数实体，参数为XMLhttpRequest.responseText   
	$.unblockUI();			
	if (result == 'success') {	
	  	$.ligerDialog.success('提交成功，请关闭对话框！','提示',function(){		  		
	  		var parentWindow_body =  $(window.parent.parent.document).find("body");if(! parentWindow_body.find(".dealWithMask").length){parentWindow_body.append("<input type=text class=dealWithMask style=height:0;border:none/>");}parentWindow_body.find(".dealWithMask").focus(); window.parent.parent.$.ligerDialog.close();     	});	
	} else if (result == 'error') {
		alert('系统错误，请联系管理员！');
	} 
}
</script>

</head>
<body>
<div id="wrapper">
<form action="" method="post" name = "form" id = "form1" >
	<ul>
		<h2>代理商信息：</h2>
		<div class="dash"></div>
		<li>
			<span>管理模式：</span>
			<input type="hidden" id="inId" name="inId" value="<%=inId%>"/>
			<input type="text"  ltype="text"  id="manageModel" name="manageModel" value="${request.manageModel}"  />
		</li>
		<li>
			<span>是否发11888卡：</span>
			 <input type="radio"  name="is11888Card"  checked="checked"  value="是"  
			 <c:if test="${is11888Card=='是' }">checked="checked"</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="is11888Card" value="否" 
            <c:if test="${is11888Card=='否' }">checked="checked"</c:if>  />否

		</li>
		
		<li>
			<span>上级合作伙伴编码：</span>
			<input type="text"  ltype="text"  id="parentId" name="parentId"  value="${request.parentId}" onfocus="alamTxt('parentId','有上级合作伙伴的填写')" onblur="removeAlamTxt('parentId')" />
		</li>
		<li>
			<span>上级合作伙伴名称：</span>
			<input type="text"  ltype="text"  id="parentName" name="parentName" value="${request.parentName}" onfocus="alamTxt('parentName','有上级合作伙伴的填写')" onblur="removeAlamTxt('parentName')"  />
		</li>
		<li>
			<span>公司办公地址：</span>
			<input type="text"   ltype="text" id="companyAddress" name="companyAddress" value="${request.companyAddress}" onfocus="alamTxt('companyAddress','厅店地址')" onblur="removeAlamTxt('companyAddress')" />
		</li>
		<li>
			<span>负责人联系地址：</span>
			<input type="text"  ltype="text"  id="ondutyPersonAddress" name="ondutyPersonAddress"   value="${request.ondutyPersonAddress}" onfocus="alamTxt('ondutyPersonAddress','代理商详细地址')" onblur="removeAlamTxt('ondutyPersonAddress')"  />
		</li>
		<li>
			<span>负责人QQ：</span>
			<input type="text"  ltype="text"  id="ondutyPersonQqnum" name="ondutyPersonQqnum" value="${request.ondutyPersonQqnum}" onfocus="alamTxt('ondutyPersonQqnum','代理商QQ号')" onblur="checklegal('ondutyPersonQqnum')"   />
		</li>
		<li>
			<span>邮政编码：</span>
			<input type="text"   ltype="text" id="postCode" name="postCode" value="${request.postCode}" onfocus="alamTxt('postCode','厅店地区邮政编码')" onblur="checklegal('postCode')" />
		</li>
		<li>
			<span>传真：</span>
			<input type="text"  ltype="text"  id="agentFax" name="agentFax" value="${request.agentFax}" onfocus="alamTxt('agentFax','传真号')"  onblur="checklegal('agentFax')" />
		</li>
		<li>
			<span>电子邮箱：</span>
			<input type="text"   ltype="text" id="agentEmail" name="agentEmail" value="${request.agentEmail}" onfocus="alamTxt('agentEmail','代理商电子邮箱')"  onblur="checklegal('agentEmail')" />
		</li>
		<li>
			<span>付款方式：</span>
			<input type="text"  ltype="text"  id="payWay" value="银行卡" name="payWay"  value="${request.payWay}" />
		</li>
		<li>
			<span>银行账户名称：</span>
			<input type="text"   ltype="text" id="bankAccountName" name="bankAccountName"  value="${request.bankAccountName}" onfocus="alamTxt('bankAccountName','代理商银行账户名称')" onblur="removeAlamTxt('bankAccountName')"  />
		</li>
		<li>
			<span>委托收款方开户银行：</span>
			<input type="text"  ltype="text"  id="weituoStartBank" name="weituoStartBank"  value="${request.weituoStartBank}" onfocus="alamTxt('weituoStartBank','邮政银行')" onblur="removeAlamTxt('weituoStartBank')" />
		</li>
		<li>
			<span>委托收款方帐号：</span>
			<input type="text"  ltype="text"  id="weituoBankAccount" name="weituoBankAccount"  value="${request.weituoBankAccount}" onfocus="alamTxt('weituoBankAccount','5115602001205166')" onblur="removeAlamTxt('weituoBankAccount')" />
		</li>
		<li>
			<span>委托收款方名称：</span>
			<input type="text"   ltype="text" id="weituoBankName" name="weituoBankName"  value="${request.weituoBankName}" onfocus="alamTxt('weituoBankName','中国电信股份有限公司泸州分公司')" onblur="removeAlamTxt('weituoBankName')"  />
		</li>
		<li>
			<span>支付对象：</span>
			<input type="text"  ltype="text"  id="payObject" name="payObject"  value="${request.payObject}" onfocus="alamTxt('payObject','中国电信股份有限公司泸州分公司代结算户')" onblur="removeAlamTxt('payObject')" />
		</li>
		<li>
			<span>注册资本：</span>
			<input type="text"   ltype="text" id="registerMoney" name="registerMoney"  value="${request.registerMoney}" onfocus="alamTxt('registerMoney','公司填写项')" onblur="checklegal('registerMoney')" />
		</li>
		<li>
			<span>工商登记时间：</span>
			<input type="text"  ltype="text"  id="registerDate" name="registerDate"  />
		</li>
		<li>
			<span>法人营业执照编号：</span>
			<input type="text"  ltype="text"  id="businessLicenseId" name="businessLicenseId" value="${request.businessLicenseId}" onfocus="alamTxt('businessLicenseId','营业执照编号')" onblur="removeAlamTxt('businessLicenseId')" />
		</li>
		<li>
			<span>税务登记证编号：</span>
			<input type="text"  ltype="text"  id="taxRegisteId" name="taxRegisteId" value="${request.taxRegisteId}" onfocus="alamTxt('taxRegisteId','公司填写项')" onblur="removeAlamTxt('taxRegisteId')" />
		</li>
		<li>
			<span>银行开户许可证编号：</span>
			<input type="text"  ltype="text"  id="bankPermisionId" name="bankPermisionId" value="${request.bankPermisionId}" onfocus="alamTxt('bankPermisionId','开户许可证编号')" onblur="removeAlamTxt('bankPermisionId')" />
		</li>
		<li>
			<span>组织机构代码证编号：</span>
			<input type="text"   ltype="text" id="organizationCodeId" name="organizationCodeId" value="${request.organizationCodeId}" onfocus="alamTxt('organizationCodeId','公司填写项')" onblur="removeAlamTxt('organizationCodeId')"  />
		</li>
		<h2>渠道信息</h2> 
		<div class="dash"></div>
		<li>
			<span>月租金额：</span>
			<input name="rentMoney" type="text" id="rentMoney"  ltype='spinner' ligerui="{type:'float'}" onblur="check()" value="${request.rentMoney}" />
		</li>
		<li>
			<span>合同期补贴政策：</span>
			<input name="allowancePolicy" type="text" id="allowancePolicy"  value="${request.allowancePolicy}"  ltype='text' />
		</li><!--
		<li>
			<span>所属渠道客户经理：</span>
			<input name="canalManager"type="text" id="canalManager"  value="${request.canalManager}" onfocus="alamTxt('canalManager','客户经理名字')" onblur="removeAlamTxt('canalManager')" />
            <input name="canalManagerId" type="hidden" id="canalManagerId" value="${request.canalManagerId}" />
		</li>  -->
		<li>
			<span>归属上级渠道名称：</span>
			<input name="canalParentName" type="text" id="canalParentName" value="${request.canalParentName}" onfocus="alamTxt('canalParentName','上级渠道名称')" onblur="removeAlamTxt('canalParentName')" />
		</li>
		<li>
			<span>归属上级渠道编码：</span>
			<input name="canalParentId" type="text" id="canalParentId" value="${request.canalParentId}" onfocus="alamTxt('canalParentId','上级渠道ID ')" onblur="removeAlamTxt('canalParentId')"  />
		</li>
		<li>
			<span>房产权所有人姓名：</span>
			<input name="houseOwnerName" type="text" id="houseOwnerName" value="${request.houseOwnerName}" />
		</li>
		<li>
			<span>房产权所有人电话：</span>
			<input name="houseOwnerPhone" type="text" id="houseOwnerPhone"  value="${request.houseOwnerPhone}" onblur="check()"/>
		</li>
		<li>
			<span>是否发卡网点：</span>
			<input name="distributeCard" type="text" id="distributeCard"   value="${request.distributeCard}" onblur="check()" />
		</li>
		<li>
			<span>开户人身份证号：</span>
			<input name="inPersonIdCard" type="text" id="inPersonIdCard" value="${request.inPersonIdCard}" onblur="check()" />
		</li>
		<li>
			<span>营收资金银行账号：</span>
			<input name="inBankAccount" type="text" id="inBankAccount" value="${request.inBankAccount}" />
		</li>
		<li>
			<span>开户号：</span>
			<input name="inBankAccountNumber" type="text" id="inBankAccountNumber" value="${request.inBankAccountNumber}" />
		</li>
		<li>
			<span>开户银行名：</span>
			<input name="inBankName" type="text" id="inBankName" value="${request.inBankName}" />
		</li>
		<li>
			<span>代理点类型：</span>
			<input type="text"  ltype="text"  id="agentPointType" name="agentPointType"  value="${request.agentPointType}" />
		</li>
		<li>
			<span>销售点类型：</span>
			<input type="text"   ltype="text" id="sellPointType" name="sellPointType" value="${request.sellPointType}" />
		</li>
		<li>
			<span>自营厅经营方式：</span>
			<input type="text"  ltype="text"  id="manageWay" name="manageWay" value="${request.manageWay}" />
		</li>
		<li>
			<span>开放销售点业态：</span>
			<input type="text"  ltype="text"  id="openSellYetai" name="openSellYetai" value="${request.openSellYetai}" />
		</li>
		<li>
			<span>连锁分类标识：</span>
			<input type="text"  ltype="text"  id="liansuoCatorySign" name="liansuoCatorySign" value="${request.liansuoCatorySign}" />
		</li>
		<li>
			<span>TOP自有厅标识：</span>
			<input type="text"   ltype="text" id="topZiyou" name="topZiyou" value="${request.topZiyou}" />
		</li>
		<li>
			<span>专营门店类别：</span>
			<input type="text"  ltype="text"  id="majorMendianType" name="majorMendianType" value="${request.majorMendianType}" />
		</li>
		<li>
			<span>授权门店级别：</span>
			<input type="text"  ltype="text"  id="grantMendianClass" name="grantMendianClass" value="${request.grantMendianClass}" />
		</li>
		<li>
			<span>便利店业态：</span>
			<input type="text"  ltype="text"  id="bianlidianTask" name="bianlidianTask" value="${request.bianlidianTask}" />
		</li>
		<li>
			<span>销售点业务范围：</span>
			<input type="text"   ltype="text" id="sellPointTask" name="sellPointTask" value="${request.sellPointTask}" />
		</li>
		<li>
			<span>销售点卖场类型：</span>
			<input type="text"  ltype="text"  id="sellPointSellType" name="sellPointSellType" value="${request.sellPointSellType}"  />
		</li>
		<li>
			<span>商客实体店细分：</span>
			<input type="text"  ltype="text"  id="kechangEntity" name="kechangEntity" value="${request.kechangEntity}" />
		</li>
		<li>
			<span>厂商渠道标识：</span>
			<input type="text"   ltype="text" id="factoryCanalSign" name="factoryCanalSign" value="${request.factoryCanalSign}"  />
		</li>
		
		<li>
			<span>龙系统是否接入：</span>
			<input type="text"   ltype="text" id="isDragonSystem" name="isDragonSystem" value="${request.isDragonSystem}"  />
		</li>
		 <h2>渠道营业员信息</h2>
		 <div class="dash"></div>
		<li>
			<span>营业员类别：</span>
			<input type="text"   ltype="text" id="userKind" name="userKind"  value="${request.userKind}"  />
		</li>
		<li>
			<span>工号状态：</span>
			<input type="text"   ltype="text" id="userState" name="userState"  value="${request.userState}"  />
		</li>
		<li>
			<span>身份证号：</span>
			<input type="text"  ltype="text"  id="userIdCard" name="userIdCard" value="${request.userIdCard}" onblur="checklegal('userIdCard')"  />
		</li>
		<li>
			<span>工号权限：</span>
			<input type="text"  ltype="text"  id="userAuthority" name="userAuthority"  value="${request.userAuthority}" />
		</li>
		<li>
			<span>邮箱：</span>
			<input type="text"  ltype="text"  id="userEmail" name="userEmail"  value="${request.userEmail}" onblur="checklegal('userEmail')" />
		</li>
		<li>
			<span>计费角色：</span>
			<input type="text"   ltype="text" id="userRole" name="userRole"  value="${request.userRole}" />
		</li>
		<li>
			<span>计费工号：</span>
			<input type="text"  ltype="text"  id="userCountId" name="userCountId" value="${request.userCountId}" />
		</li>
		<li>
			<span>生日：</span>
			<input type="text"  ltype="text"  id="userBirthday" name="userBirthday"  />
		</li>
		<li>
			<span>籍贯：</span>
			<input type="text"  ltype="text"  id="userNativeplace" name="userNativeplace" value="${request.userNativeplace}"  />
		</li>
		<li>
			<span>联系电话：</span>
			<input type="text"   ltype="text" id="userPhone" name="userPhone" value="${request.userPhone}" onblur="checklegal('userPhone')"  />
		</li>
		<li>
			<span>文化程度：</span>
			<input type="text"  ltype="text"  id="userEducation" name="userEducation" value="${request.userEducation}"  />
		</li>
		<li>
			<span>技能证书：</span>
			<input type="text"  ltype="text"  id="userCertification" name="userCertification"  value="${request.userCertification}" />
		</li>
		<li>
			<span>入职时间：</span>
			<input type="text"  ltype="text"  id="userInDate" name="userInDate"   />
		</li>
		<li>
			<span>离职时间：</span>
			<input type="text"   ltype="text" id="userOutDate" name="userOutDate"  />
		</li>
		<li>
			<span>住址：</span>
			<input type="text"  ltype="text"  id="userAddress" name="userAddress" value="${request.userAddress}"  />
		</li>
		<li>
			<span>岗位：</span>
			<input type="text"  ltype="text"  id="userPosition" name="userPosition" value="${request.userPosition}" />
		</li>
		<li>
			<span>用工方式：</span>
			<input type="text"   ltype="text" id="userWay" name="userWay"  value="${request.userWay}"  />
		</li>
		<li>
			<span>地区名称：</span>
			<input type="text"  ltype="text"  id="userRegionName" name="userRegionName"  value="${request.userRegionName}" />
		</li>

		
		<li>
			<span>备注：</span>
			<textarea class="l-textarea" name="remark" id="remark" style="width:200px; height:50px;" >${request.remark}</textarea>
		</li>
	</ul>
	<center>
		<button type="submit" id="btn"  name="btn">提交</button>
	</center>
</form>
</div>
</body>
<script type="text/javascript">
function checklegal(id){    //验证账号、密码是否合
	var pn=/^[1-9]\d{5}(?!\d)$/;
	var postCode = document.getElementById("postCode").value;
	if(postCode != ""){
		  	if(pn.test(postCode) == false){
		    	$.ligerDialog.error("请正确填写邮政编码，邮政编码为6位数字组成!");
		    	$("#"+id).ligerHideTip();
		    	return false;
		  	}
	}
	//验证身份证号
	var userIdCard= document.getElementById("userIdCard").value;
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	if(userIdCard != ""){
		if(reg.test(userIdCard) === false)  {  
		       	$.ligerDialog.error("身份证输入不合法!");
		       	$("#"+id).ligerHideTip();
		      	return  false;  
		 }
	}
	//验证邮箱
	var em= /^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/;
	var agentEmail= document.getElementById("agentEmail").value;
	var userEmail= document.getElementById("userEmail").value;
	if(agentEmail != ""){
		  if(em.test(agentEmail) == false){
		    	$.ligerDialog.error("请正确填写邮箱地址！!");
		    	$("#"+id).ligerHideTip();
		    	return false;
		  }
	  }
	if(userEmail != ""){
		  if(em.test(userEmail) == false){
			  $.ligerDialog.error("请正确填写邮箱地址！!");
			  $("#"+id).ligerHideTip();
		    	return false;
		  }
	  }
	//验证QQ
	  var ab=/^[1-9][0-9]{4,11}$/;
	  var ondutyPersonQqnum = document.getElementById("ondutyPersonQqnum").value;
	  if(ondutyPersonQqnum != ""){
		  if(ab.test(ondutyPersonQqnum) == false){
		    	$.ligerDialog.error("请正确填写QQ号码,格式为5-12位数字，首位不能是0！!");
		    	$("#"+id).ligerHideTip();
		    	return false;
		  }
	  }
  	//验证电话号码手机号码，包含至今所有号段 
  var ab=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
  var userPhone = document.getElementById("userPhone").value;
  if(userPhone != null && userPhone !=""){
		if (ab.test(userPhone) == false) {
			$.ligerDialog.error("请正确填写手机号码,例如 13812345678，或者固定电话02312345678!");
			$("#"+id).ligerHideTip();
			return false;
		}
  }
//数字是否合法
	var money = /^[0-9]+[0-9]*(\.\d+)?$/;
	var registerMoney = $('#registerMoney').val();
	if(registerMoney != null && registerMoney!=""){
		if (money.test(registerMoney) == false) {
			$.ligerDialog.error("请输入正确的数字");
			$("#"+id).ligerHideTip();
			return false;
		}
	}
	var fax =/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	var agentFax = $('#agentFax').val();
	if(agentFax != null && agentFax!=""){
		if (fax.test(agentFax) == false) {
			$.ligerDialog.error("请输入正确的传真，：+123 -999 999 ； +123 -999 999 ；123 999 999 ；+123 999999!");
			$("#"+id).ligerHideTip();
			return false;
		}
	}
}

function alamTxt(id,txtContext){
	$("#"+id).ligerTip({ content: txtContext, width: 150 });
}

function removeAlamTxt(id){
	$("#"+id).ligerHideTip();
}
</script>
</html>