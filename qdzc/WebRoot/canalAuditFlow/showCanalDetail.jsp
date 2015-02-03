<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../com_css/css/common.css" rel="stylesheet" type="text/css"/>
  <link href="../com_css/css/serviceAccept.css" rel="stylesheet" type="text/css"/>
 <link href="../com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="../com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
        <link href="../com_css/LigerUILib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="../com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>  
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>  
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>      
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>    
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
  <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../com_css/js/jquery.blockUI.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>     
 	<script src="../com_css/uploadify/swfobject.js" type="text/javascript" ></script>
	<script src="../com_css/uploadify/jquery.uploadify.v2.1.4.min.js" type="text/javascript"></script>
   <link href="../com_css/jqueryUI/themes/base/jquery.ui.all.css" rel="stylesheet">
	<script src="../com_css/jqueryUI/ui/jquery.ui.core.js"></script>
	<script src="../com_css/jqueryUI/ui/jquery.ui.widget.js"></script>
	<script src="../com_css/jqueryUI/ui/jquery.ui.position.js"></script>
	<script src="../com_css/jqueryUI/ui/jquery.ui.autocomplete.js"></script>
<script type="text/javascript">
$(function(){
$("input").attr("readonly","readonly");
	
	$("#form1").ligerForm();
});


 function downAffix(){
	 		var m = $.ligerDialog.open({ 
	        	  title: '附件查看',
	        	  height: 500, 
	        	  width: 700, 
	        	url: '../canalManage/showAffix.jsp?id=${request.guaranteeReceiptScanUrl}',
	        	   showMax: true, 
	        	   isResize: true,      	 
	        	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
	          }); 
	 }
	 
	 function f_closeAddWindow(item, dialog)
        {
           $.ligerDialog.confirm('确认要关闭窗口吗', function (yes)
            {
               if(yes)
                   dialog.close();
            })
          //  manager.loadData(true);
        }
</script>
</head>
<style type="text/css">
	 body{ font-size:13px;background:#F0F0F0}
	 .hehe{text-align:right;}
     .l-table-edit-td{ padding:2px;}
     .l-button-submit{width:80px; float:left; margin-left:50px; padding-bottom:2px;} 
  </style>
<body style="padding:15px;">
<form action="" method="post" name = "form1" id = "form1" >
		<table border="0" cellspacing="20" class="l-table-edit">
		 <tr>
			<td colspan="6" align="center" ><h3 class="meTitle" >渠道信息</h3></td>
		  </tr>
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>渠道名称：</td>
				<td ><input name="canalName" type="text" id="canalName"  value="${request.canalName}"/>
					<input name="canalNameOld" type="hidden" id="canalNameOld"  value="${request.canalName}"/>
					<input name="inId" type="hidden" id="inId"  value="${request.inId}"/>
				</td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>区域名称：</td>
				<td class="l-table-edit-td"><input name="areaName" type="text"
					id="areaName"  value="${request.areaName}"/></td>
				<td width="130px"></td>
			</tr>
			
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>渠道状态：</td>
				<td class="l-table-edit-td"><input name="canalState"
					type="text" id="canalState"   value="${request.canalState}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>城乡：</td>
				<td class="l-table-edit-td"><input name="canalForm"
					type="text" id="canalForm"   value="${request.canalForm}"/></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>管理属性：</td>
				<td class="l-table-edit-td"><input name="canalProperty"
					type="text" id="canalProperty"   value="${request.canalProperty}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>分类名称：</td>
				<td class="l-table-edit-td"><input name="canalType"
					type="text" id="canalType"   value="${request.canalType}"/></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>区域特征：</td>
				<td class="l-table-edit-td"><input name="regionCharacter"
					type="text" id="regionCharacter"   value="${request.regionCharacter}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>渠道地址：</td>
				<td class="l-table-edit-td"><input name="canalAddress"
					type="text" id="canalAddress"   value="${request.canalAddress}"/></td>
				<td width="130px"></td>
			</tr>
			
			<tr>

				<td class="hehe"><font color="#ff0000">* </font>归属上级渠道名称：</td>
				<td class="l-table-edit-td"><input name="canalParentName"
					type="text" id="canalParentName"   value="${request.canalParentName}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>归属上级渠道ID：</td>
				<td class="l-table-edit-td"><input name="canalParentId"
					type="text" id="canalParentId"  value="${request.canalParentId}"/></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>归属代理商ID：</td>
				<td class="l-table-edit-td"><input name="agentId"
					type="text" id="agentId"  value="${request.agentId}" /></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>归属代理商名称：</td>
				<td class="l-table-edit-td"><input name="agentName"
					type="text" id="agentName"  value="${request.agentName}"/></td>
				<td width="130px"></td>
				
			</tr>
			
			<tr>
				<td class="hehe">所属渠道客户经理：</td>
				<td class="l-table-edit-td"><input name="canalManager"
					type="text" id="canalManager" value="${request.canalManager}"/>
					<input name="canalManagerId"
					type="hidden" id="canalManagerId" value="${request.canalManagerId}"/>
					</td>
				<td width="130px"></td>
				
				<td class="hehe"><font color="#ff0000">* </font>渠道归属部门：</td>
				<td class="l-table-edit-td"><input name="canalDept"
					type="text" id="canalDept"  value="${request.canalDept}"/></td>
				<td width="130px"></td>
				
			</tr>
			
			
			
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>负责人：</td>
				<td class="l-table-edit-td"><input name="canalUserName"
					type="text" id="canalUserName"   value="${request.canalUserName}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>联系电话：</td>
				<td class="l-table-edit-td"><input name="canalUserPhone"
					type="text" id="canalUserPhone"   value="${request.canalUserPhone}"/></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">身份证号码：</td>
				<td class="l-table-edit-td"><input name="canalIdCard"
					type="text" id="canalIdCard"   value="${request.canalIdCard}"/></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			<tr>

				<td class="hehe"><font color="#ff0000">* </font>城乡标识：</td>
				<td class="l-table-edit-td"><input name="urbanIdetity"
					type="text" id="urbanIdetity"   value="${request.urbanIdetity}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>产权归属：</td>
				<td class="l-table-edit-td"><input name="expireType"
					type="text" id="expireType"  value="${request.expireType}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>营业面积：</td>
				<td class="l-table-edit-td"><input name="businessArea"
					 id="businessArea"    value="0.00" value="${request.businessArea}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>月租金额：</td>
				<td class="l-table-edit-td"><input name="rentMoney"
					id="rentMoney"    value="0.00" value="${request.rentMoney}"/></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>房租合同开始时间：
				</td>
				<td class="l-table-edit-td"><input name="rentStartDate"
					type="text" id="rentStartDate"   value="${request.rentStartDate}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>房租合同结束时间：
				</td>
				<td class="l-table-edit-td"><input name="rentEndDate"
					type="text" id="rentEndDate"   value="${request.rentEndDate}"/></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">合同期补贴政策：</td>
				<td class="l-table-edit-td"><input name="allowancePolicy"
					type="text" id="allowancePolicy"  value="${request.allowancePolicy}"/></td>
				<td width="130px"></td>
				<td class="hehe"><font color="#ff0000">* </font>首期房租补贴(元/月)：
				</td>
				<td class="l-table-edit-td"><input name="firstRentAllowance"
					type="text" id="firstRentAllowance"    value="0.00" value="${request.firstRentAllowance}"/>


				</td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe"><font color="#ff0000">* </font>首期装修补贴金额(元/月)：
				</td>
				<td class="l-table-edit-td"><input
					name="firstDecorationAllowance" type="text"
					id="firstDecorationAllowance"    value="0.00" value="${request.firstDecorationAllowance}"/></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">房产权所有人姓名：</td>
				<td class="l-table-edit-td"><input name="houseOwnerName"
					type="text" id="houseOwnerName"  value="${request.houseOwnerName}"/></td>
				<td width="130px"></td>
				<td class="hehe">房产权所有人电话：</td>
				<td class="l-table-edit-td"><input name="houseOwnerPhone"
					type="text" id="houseOwnerPhone"   value="${request.houseOwnerPhone}"/></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">是否发卡网点： </td>
				<td class="l-table-edit-td"><input name="distributeCard"
					type="text" id="distributeCard"   value="${request.distributeCard}"/>
				</td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">营收资金银行账号：</td>
				<td class="l-table-edit-td"><input name="inBankAccount"
					type="text" id="inBankAccount" value="${request.inBankAccount}" /></td>
				<td width="130px"></td>
				<td class="hehe">开户号：</td>
				<td class="l-table-edit-td"><input name="inBankAccountNumber"
					type="text" id="inBankAccountNumber"  value="${request.inBankAccountNumber}"/></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">开户银行名：</td>
				<td class="l-table-edit-td"><input name="inBankName"
					type="text" id="inBankName" value="${request.inBankName}" /></td>
				<td width="130px"></td>
				<td class="hehe">归集账号：</td>
				<td class="l-table-edit-td"><input name="inCollectNumber"
					type="text" id="inCollectNumber" value="${request.inCollectNumber}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">开户人身份证号：</td>
				<td class="l-table-edit-td"><input name="inPersonIdCard"
					type="text" id="inPersonIdCard"  value="${request.inPersonIdCard}" /></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">保证金金额：</td>
				<td class="l-table-edit-td"><input name="guaranteeAmount"
					type="text" id="guaranteeAmount"   value="0.00" value="${request.guaranteeAmount}" /></td>
				<td width="130px"></td>
				<td class="hehe">保证金办理人：</td>
				<td class="l-table-edit-td"><input name="guaranteeUser"
					type="text" id="guaranteeUser"  value="${request.guaranteeUser}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">办理时间：</td>
				<td class="l-table-edit-td"><input name="guaranteeTime"
					type="text" id="guaranteeTime" value="${request.guaranteeTime}"  /></td>
				<td width="130px"></td>
				<td class="hehe">收据扫描件/合同扫描件：</td>
				<td class="l-table-edit-td">
					<%String guaranteeReceiptScanUrl =(String) request.getAttribute("guaranteeReceiptScanUrl");
					if(guaranteeReceiptScanUrl != null && guaranteeReceiptScanUrl.length() > 1 ){
					 %><input name="download" type="button"  id="download"  onclick = "downAffix()"  value = "查看图片"/>
					  <%} %>
					  
				</td>
				<td width="130px">
					
				</td>
			</tr>
			<tr>
				<td class="hehe">备注：</td>
				<td class="l-table-edit-td"><textarea name="remark"
						id="remark" style="width:200px; height:50px;"> ${request.remark} </textarea></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			
			
			<tr>
				<td colspan="6" align="center" ><h3 class="meTitle" >专线受理信息</h3></td>
			</tr>
			<tr>
				<td class="hehe">内网账号:</td>
				<td class="l-table-edit-td"><input name="broadbandAccount"
					type="text" id="broadbandAccount"  value="${request.broadbandAccount}" /></td>
				<td width="130px"></td>
				<td class="hehe">内网密码:</td>
				<td class="l-table-edit-td"><input name="broadbandPassword"
					type="text" id="broadbandPassword" value="${request.broadbandPassword}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">优惠信息:</td>
				<td class="l-table-edit-td"><input name="privilegeInfo"
					type="text" id="privilegeInfo"   value="${request.privilegeInfo}" /></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			
			<tr>
				<td class="hehe">外网账号:</td>
				<td class="l-table-edit-td"><input name="wwwAccount"
					type="text" id="wwwAccount"  value="${request.wwwAccount}" /></td>
				<td width="130px"></td>
				<td class="hehe">外网优惠:</td>
				<td class="l-table-edit-td"><input name="wwwPrivilegeInfo"
					type="text" id="wwwPrivilegeInfo" value="${request.wwwPrivilegeInfo}" /></td>
				<td width="130px"></td>
			</tr>
		
			<tr>
				<td colspan="6" align="center" ><h3 class="meTitle" >专线受理确认信息</h3></td>
			</tr>
				<tr>
				<td class="hehe">外网账号：</td>
				<td class="l-table-edit-td"><input name="q"
					type="text" id="q"  value="${request.wwwAccount}" /></td>
				<td width="130px"></td>
				<td class="hehe">外网优惠：</td>
				<td class="l-table-edit-td"><input name="w"
					type="text" id="w" value="${request.wwwPrivilegeInfo}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">开通专线时间：</td>
				<td class="l-table-edit-td"><input name="openTime"
					type="text" id="openTime"  value="${request.openTime}" /></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td colspan="6" align="center" ><h3 class="meTitle" >代理商系统渠道信息确认</h3></td>
			</tr>
			<tr>
				<td class="hehe">渠道编码：</td>
				<td class="l-table-edit-td"><input name="a"
					type="text" id="a"  value="${request.canalId}" /></td>
				<td width="130px"></td>
				<td class="hehe">渠道名称：</td>
				<td class="l-table-edit-td"><input name="b"
					type="text" id="b" value="${request.canalId}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">代理商名称：</td>
				<td class="l-table-edit-td"><input name="belongManager"
					type="text" id="belongManager"  value="${request.agentName}" /></td>
				<td width="130px"></td>
				<td class="hehe">代理商编码：</td>
				<td class="l-table-edit-td"><input name="c"
					type="text" id="c" value="${request.agentId}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td colspan="6" align="center" ><h3 class="meTitle" >开CRM工号</h3></td>
			</tr>
			<tr>
				<td class="hehe">员工姓名：</td>
				<td class="l-table-edit-td"><input name="employeeName"
					type="text" id="employeeName" value="${request.employeeName}" /></td>
				<td width="130px"></td>
				<td class="hehe">CRM工号：</td>
				<td class="l-table-edit-td"><input name="crmNumber"
					type="text" id="crmNumber"  value="${request.crmNumber}" /></td>
				<td width="130px"></td>
				
			</tr>
			<tr>
				<td class="hehe">工号权限：</td>
				<td class="l-table-edit-td"><input name="privilege"
					type="text" id="privilege" value="${request.privilege}" /></td>
				<td width="130px"></td>
				<td class="hehe">绑定手机号：</td>
				<td class="l-table-edit-td"><input name="telephone"
					type="text" id="telephone" value="${request.telephone}" /></td>
				<td width="130px"></td>
			</tr>
			
			
			<tr>
				<td colspan="6" align="center" ><h3 class="meTitle" >开代理商门户网站工号</h3></td>
			</tr>
			<tr>
				<td class="hehe">代理商门户网站工号：</td>
				<td class="l-table-edit-td"><input name="agentJobNumber" type="text" id="agentJobNumber" value="${request.agentJobNumber}" /></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			
			
			
			
			
			
			
			<tr>
				<td colspan="6" align="center" ><h3 class="meTitle" >稽核厅编码</h3></td>
			</tr>
			<tr>
				<td class="hehe">稽核厅名称：</td>
				<td class="l-table-edit-td"><input name="checkName" type="text" id="checkName"  value="${request.checkName}" /></td>
				<td width="130px"></td>
				<td class="hehe">稽核厅编码：</td>
				<td class="l-table-edit-td"><input name="guaranteeUser" type="text" id="checkNumber" value="${request.checkNumber}" /></td>
				<td width="130px"></td>
			</tr>
			
			
			<tr>
				<td colspan="6" align="center" ><h3 class="meTitle" >配置稽核人员</h3></td>
			</tr>
			<tr>
				<td class="hehe">稽核人员：</td>
				<td class="l-table-edit-td"><input name="checkUser" type="text" id="checkUser" value="${request.checkUser}" /></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
			
			
			<tr>
				<td colspan="6" align="center" ><h3 class="meTitle" >设备基础配置</h3></td>
			</tr>
			<tr>
				<td class="hehe">终端一键式平台账号：</td>
				<td class="l-table-edit-td"><input name="dx360Number" type="text" id="dx360Number" value="${request.dx360Number}" /></td>
				<td width="130px"></td>
				<td class="hehe">受理设备类型：</td>
				<td class="l-table-edit-td"><input name="terminalType"	type="text" id="terminalType" value="${request.terminalType}" /></td>
				<td width="130px"></td>
			</tr>
			
			
			<tr>
				<td class="hehe">CPU：</td>
				<td class="l-table-edit-td"><input name="cpu" type="text" id="cpu" value="${request.cpu}" /></td>
				<td width="130px"></td>
				<td class="hehe">核数：</td>
				<td class="l-table-edit-td"><input name="kernal" type="text" id="kernal" value="${request.kernal}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">主频：</td>
				<td class="l-table-edit-td"><input name="frequency" type="text" id="frequency" value="${request.frequency}" /></td>
				<td width="130px"></td>
				<td class="hehe">内存：</td>
				<td class="l-table-edit-td"><input name="memory" type="text" id="memory" value="${request.memory}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe">硬盘：</td>
				<td class="l-table-edit-td"><input name="disk" type="text" id="disk" value="${request.disk}" /></td>
				<td width="130px"></td>
				<td class="hehe">终端机型号：</td>
				<td class="l-table-edit-td"><input name="terminalNumber"	type="text" id="terminalNumber" value="${request.terminalNumber}" /></td>
				<td width="130px"></td>
			</tr>
			<tr>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
				<td class="hehe"></td>
				<td class="l-table-edit-td"></td>
				<td width="130px"></td>
			</tr>
		</table>
		

	</form>
</body>

</html>