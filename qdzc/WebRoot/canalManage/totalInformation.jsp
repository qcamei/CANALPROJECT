<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//String agentId = request.getParameter("agentId");
	String inId = request.getParameter("inId");
	//String canalUserName = request.getParameter("canalUserName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link href="./com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
<script src="./com_css/js/jquery-1.6.2.min.js" type="text/javascript"></script>
<script src="./com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#navtab1").ligerTab();
	});
</script>
</head>
<body>
	<div id="navtab1" style="width:1155px; overflow: hidden; border: 1px solid #D3D3d3;" class="liger-tab">
		<div tabid="home" title="代理商信息>>>>" lselected="true" >
			<!-- <iframe src="canalManage/showAgentDetails.jsp"></iframe> -->
			<iframe src="detailQueryAction?inId='<%=inId%>'&type=agent "></iframe>
		</div>
		<div title="渠道信息>>>>" showclose="true">
			  <!--<iframe src="canalManage/canalInfodetail.jsp"></iframe>-->
				<iframe src="detailQueryAction?inId='<%=inId%>'&type=canal "></iframe>
		</div>
		<div title="渠道员工信息>>>>" showclose="true">
			<!-- <iframe src="canalManage/showCanalUserDetails.jsp"></iframe> -->
			<iframe src="detailQueryAction?inId='<%=inId%>'&type=user "></iframe>
		</div>
		<div title="第二步审核信息" showclose="true">
			  <iframe src="canalAuditFlow/audit2.jsp"></iframe>
		</div>
		<div title="第三步审核信息" showclose="true">
			  <iframe src="canalAuditFlow/audit2.jsp"></iframe>
		</div>
		<div title="第四步审核信息" showclose="true">
			  <iframe src="canalAuditFlow/audit2.jsp"></iframe>
		</div>
		<div title="第六步审核信息" showclose="true">
			  <iframe src="canalAuditFlow/audit2.jsp"></iframe>
		</div>
		<div title="第七步审核信息" showclose="true">
			  <iframe src="canalAuditFlow/audit2.jsp"></iframe>
		</div>
	</div>
	
</body>
</html>
