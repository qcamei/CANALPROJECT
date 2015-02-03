<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<style>
html,body{
     margin:0px;
     width:100%;
      height:100%;
}
.liger-tab,.l-tab-content{
height:100%;
}
.l-tab-content-item{
height:100%;
overflow:visible;
}
.l-tab-content-item iframe{
height:100% !important;
}

</style>
<link href="../com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
<script src="../com_css/js/jquery-1.6.2.min.js" type="text/javascript"></script>
 <!-- <script src="../com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script> -->
    
<script src="../com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>

<script type="text/javascript">
var srcProcess = "";
	$(function() {
		
		$("#navtab1").ligerTab();
		var processName = "${param.process}";
		 if(processName == "filialeAudit"){
			
			var inId = "${param.inId}";
			var canalName = decodeURI("${param.canalName}");
			var canalId = "${param.canalId}";
			canalName = encodeURIComponent(encodeURIComponent(canalName));
			srcProcess = '../canalModifyFlow/filialeAuditAdd.jsp?inId='+inId+'&canalName='+canalName+'&canalId='+canalId;
			
			
		}else if(processName == "subDeptAudit"){
			
			var inId = "${param.inId}";
			var canalName = decodeURI("${param.canalName}");
			canalName = encodeURIComponent(encodeURIComponent(canalName));
		
			var canalId = "${param.canalId}";
	
			srcProcess = '../canalModifyFlow/subDeptAdd.jsp?inId='+inId+'&canalName='+canalName+'&canalId='+canalId;
			
			
		}else if(processName == "finance"){
			
			var inId = "${param.inId}";
			var canalName = decodeURI("${param.canalName}");
			var canalId = "${param.canalId}";
			canalName = encodeURIComponent(encodeURIComponent(canalName));
			srcProcess = '../canalModifyFlow/financeAdd.jsp?inId='+inId+'&canalName='+canalName+'&canalId='+canalId;
			
			
		}else if(processName == "agentCanal"){
			
			var inId = "${param.inId}";
			var canalName = decodeURI("${param.canalName}");
			var canalId = "${param.canalId}";
			var agentId = "${param.agentId}";
			var agentName = decodeURI("${param.agentName}");
			agentName = encodeURIComponent(encodeURIComponent(agentName));
			canalName = encodeURIComponent(encodeURIComponent(canalName));
			srcProcess = '../canalModifyFlow/agentCanalAdd.jsp?inId='+inId+'&canalName='+canalName+'&canalId='+canalId+'&agentId='+agentId+'&agentName='+agentName;
			
			
		}else if(processName == "openCRM"){
			
			var inId = "${param.inId}";
			var canalName = decodeURI("${param.canalName}");
			var canalId = "${param.canalId}";
			canalName = encodeURIComponent(encodeURIComponent(canalName));
			srcProcess = '../canalModifyFlow/openCRMAdd1.jsp?inId='+inId+'&canalName='+canalName+'&canalId='+canalId;
			
			
		}else{
			$("#audit").css("display","none");
			//$("#caseDetail").attr("lselected",true); 
		}
	
		$("#process").attr("src",srcProcess); 
	});
</script>

</head>
<body>

	<div id="navtab1" style="overflow: hidden;border: 1px solid #D3D3d3;" class="liger-tab">
		<div tabid="home" title="审核模块" lselected="true" id="audit" >	
			<iframe id="process" src=""></iframe>
		</div>
		<div title="工单流程" id="caseDetail">
				<iframe style="height: auto;"src="canalProcessDetail.jsp?canalId=${param.canalId}"  ></iframe>
		</div>
		<div title="必填信息" >
			<iframe src="../detailAction?canalId=${param.canalId}&type=detail " width="100%" height="100%" ></iframe>
		</div>
		<div title="选填信息" >
			 <iframe src="../detailAction_0?canalId=${param.canalId}&type=detail" width="100%" height="100%" ></iframe> 
		</div>
	</div>
	
</body>
<script>
</script>
</html>
