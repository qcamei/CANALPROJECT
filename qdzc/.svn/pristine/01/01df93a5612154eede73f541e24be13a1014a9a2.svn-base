<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<style>
html,body{
     margin:0px;
     width:100%;
      height:1100px;
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
<script src="../com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$("#navtab1").ligerTab();
		//var inId = "${param.inId}"
	});
</script>

</head>
<body>

	<div id="navtab1" style="overflow: hidden;border: 1px solid #D3D3d3;" class="liger-tab">

		<div tabid="home" title="必填信息" lselected="true" >
			<!-- <iframe src="showCanalUserDetails.jsp?inId=${param.inId}"></iframe> -->
			<iframe src="../canalDetailAction?inId=${param.inId}&type=detail " width="100%" height="100%" ></iframe>
		</div>
		<div title="选填信息" >
			<!--<iframe src="showCanalUserDetails.jsp?inId=${param.inId}"></iframe>-->
			 <iframe src="../canalDetailAction_0?inId=${param.inId}&type=detail" width="100%" height="100%" ></iframe> 
		</div>
	</div>
	
</body>
</html>
