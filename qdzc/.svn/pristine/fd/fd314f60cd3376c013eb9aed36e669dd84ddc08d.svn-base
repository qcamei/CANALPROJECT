<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="com_css/css/my.css" />
<link href="mainFrame/js/themes/default/easyui.css" rel="stylesheet" type="text/css"	/>
<link href="mainFrame/js/themes/icon.css"  rel="stylesheet" type="text/css"	/>

 <link href="com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
  <link href="com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
    
<script src="com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script> 
<script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
<script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>        

<style type="text/css">

.small_contailer_box1{	
	height: 280px;  	
}
.small_contailer_box2{
	background:#FFFFFC;
	width:80%;
	height: 170px;  
	border: #BBD2E9 0px solid;
	margin: 1px 0px 3px 3px;
	float: left;
	overflow-y:scorll;	
}
.small_contailer_box1 .hr-content-cell{
	padding: 5px 0 0 10px;
	line-height: 20px;	
}

 body{ font-size:14px;font-family:"微软雅黑";} 
</style>
</head>
<body>
<div class="small_contailer_box2" >
      <table>
        <tr>
            <td width="150px"></td>
        	<td width="200px"><a href="#" onclick="$(window.parent.window).get(0).addTab('渠道申请','canalInfoAction','canal')" ><span class="STYLE3"><img src="images/apply.png"/></span></a></td>
        	<td width="200px"><a href="#" onclick="$(window.parent.window).get(0).addTab('渠道申请（变更）','canalApplyModify','sup')" ><span class="STYLE3"><img src="images/change.png"/></span></a></td>
        	<td width="200px"><a href="#" onclick="$(window.parent.window).get(0).addTab('渠道申请（关闭）','canalApplyClose','orderApprove')" ><span class="STYLE3"><img src="images/close.png"/></span></a></td>
        </tr>
         <tr>
            <td width="180px"></td>
        	<td width="100px" style="padding-left:10px;padding-top:14px;font-size:18px;font-weight:bold;">渠道申请</td>
        	<td width="100px" style="padding-left:10px;padding-top:14px;font-size:18px;font-weight:bold;">渠道变更</td>
        	<td width="100px" style="padding-left:10px;padding-top:14px;font-size:18px;font-weight:bold;">渠道关闭</td>
        </tr>
      </table>	

</div>

<div class="small_contailer_box1">
	<div class="hr_title" style="background-color: #E0ECFF"><img src="images/util/arrow6.gif" hspace="5" />待处理工单<img alt="刷新" style="margin-left:260px" src="images/refresh.jpg"></img>
	<a href="#"  style="text-decoration: none; color:#556B2F;font-size: 12px" onclick="javascript:window.location.href='undefined'">刷新</a></div>
 	<div class="hr_content" >		
    	<table border="0" cellspacing="5" cellpadding="0" >
     <tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('分公司审核','filialeAuditAction','instore')" ><span class="STYLE3" >分公司流程有  <font color="#ff0000">${request.waitToCheck2}</font> 条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('分管部门审核','subDeptAuditAction','instore')" ><span class="STYLE3" >分管部门流程有  <font color="#ff0000">${request.waitToCheck3}</font>  条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('代理商系统渠道信息','agentCanalAuditAction','instore')" ><span class="STYLE3" >代理商系统渠道信息有  <font color="#ff0000">${request.waitToDo4}</font> 条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('开CRM工号','openCRMAuditAction','instore')" ><span class="STYLE3" >开CRM工号流程有  <font color="#ff0000">${request.waitToDo5}</font>  条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     	<tr><td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('开代理商门户网站工号','openAgentWebAction','instore')" ><span class="STYLE3" >开代理商门户网站工号流程有  <font color="#ff0000">${request.waitToDo6}</font> 条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('资金稽核编码','moneyCheckAction','instore')" ><span class="STYLE3" >资金稽核编码流程有  <font color="#ff0000">${request.waitToDo7}</font>  条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     	
     	<tr><td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('配置稽核人员','checkConfigurationAction','brand')" ><span class="STYLE3" >稽核系统配置人员流程有  <font color="#ff0000">${request.waitToDo8}</font> 条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('渠道专线受理','lineConfig12Action','instore')" ><span class="STYLE3" >渠道专线受理环节有  <font color="#ff0000">${request.waitToDo9}</font>  条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     	<tr><td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('专线受理确认','configLineAction','input')" ><span class="STYLE3" >专线受理确认环节有  <font color="#ff0000">${request.waitToDo12}</font> 条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('设备基础配置','deviceConfigurationAction','instore')" ><span class="STYLE3" >设备基础配置流程有  <font color="#ff0000">${request.waitToDo10}</font>  条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     <tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('财务审核','financeAction','caseWait')" ><span class="STYLE3" >财务审核流程有  <font color="#ff0000">${request.waitToCheck11}</font>  条工单  待处理&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     </table> 
 </div>
</div>

<div class="small_contailer_box1">
	<div class="hr_title" style="background-color: #E0ECFF"><img src="images/util/arrow6.gif" hspace="5" />待修改工单</div>
 <div class="hr_content">		
      <table border="0" cellspacing="5" cellpadding="0" >
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('申请环节','filialeAuditAction','instore')" ><span class="STYLE3" >申请环节有  <font color="#ff0000">${request.waitToModify1}</font> 条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('代理商系统渠道信息','agentCanalAuditAction','instore')" ><span class="STYLE3" >代理商系统渠道信息有  <font color="#ff0000">${request.waitToModify4}</font> 条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('开CRM工号','openCRMAuditAction','instore')" ><span class="STYLE3" >开CRM工号流程有  <font color="#ff0000">${request.waitToModify5}</font>  条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     	<tr><td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('开代理商门户网站工号','openAgentWebAction','instore')" ><span class="STYLE3" >开代理商门户网站工号流程有  <font color="#ff0000">${request.waitToModify6}</font> 条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('资金稽核编码','moneyCheckAction','instore')" ><span class="STYLE3" >资金稽核编码流程有  <font color="#ff0000">${request.waitToModify7}</font>  条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     	
     	<tr><td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('配置稽核人员','checkConfigurationAction','brand')" ><span class="STYLE3" >稽核系统配置人员流程有  <font color="#ff0000">${request.waitToModify8}</font> 条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('渠道专线受理','lineConfig12Action','instore')" ><span class="STYLE3" >渠道专线受理环节有  <font color="#ff0000">${request.waitToModify9}</font>  条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     	<tr><td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('专线受理确认','configLineAction','input')" ><span class="STYLE3" >专线受理确认环节有  <font color="#ff0000">${request.waitToModify12}</font> 条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a><br /></td></tr> 
     	<tr> <td colspan="2"> <a href="#" onclick="$(window.parent.window).get(0).addTab('设备基础配置','deviceConfigurationAction','instore')" ><span class="STYLE3" >设备基础配置流程有  <font color="#ff0000">${request.waitToModify10}</font>  条工单  待修改&nbsp;&nbsp;<span class="STYLE4">     </span></span>&nbsp;</a></td></tr>
     </table> 
 </div>
</div>

</body>
</html>
