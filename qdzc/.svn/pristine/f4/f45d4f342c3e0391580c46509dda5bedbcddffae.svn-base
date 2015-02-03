<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<style>
html,body{
     margin:0px;
     width:100%;
      height:1000px;
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
	});
</script>

</head>
<body>

	<div id="navtab1" style="overflow: hidden;border: 1px solid #D3D3d3;" class="liger-tab">
		<div tabid="home" title="代理商信息" lselected="true" >
			<iframe src="detailQueryAction?canalId=${param.canalId}&type=agent" ></iframe>
		</div>
		<div title="渠道信息" >
				<iframe style="height: auto;"src="detailQueryAction?canalId=${param.canalId}&type=canal"  ></iframe>
		</div>
		<div title="渠道营业员信息">
			<iframe src="../canalAuditFlow/showCanalUserDetails.jsp?canalId=${param.canalId}"></iframe>
		</div>
		<div title="新增流程审核记录">
				<iframe style="height: auto;"src="../canalModifyFlow/canalProcessDetail.jsp?canalId=${param.canalId}"  ></iframe>
		</div>
		<div title="变更流程修改信息">
				<iframe style="height: auto;"src="../canalModifyFlow/historyDetails.jsp?canalId=${param.canalId}"  ></iframe>
		</div>
		<div title="变更流程审核信息">
				<iframe style="height: auto;"src="../search/canalProcessDetail.jsp?canalId=${param.canalId}"  ></iframe>
		</div>
		<div title="关闭流程审核信息">
			<iframe src="dealDetailQueryA.jsp?canalId=${param.canalId}" ></iframe>
		</div>
	</div>
	
</body>
</html>
