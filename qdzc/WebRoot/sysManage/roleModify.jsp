<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../checkLogin.jsp" %>
<%
	String groupId = request.getParameter("groupId");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>修改角色</title>
<link type="text/css" rel="stylesheet" href="../sysManage/css/storm.css">
<link type="text/css" rel="stylesheet" href="../sysManage/css/xtree.css">
<link type="text/css" rel="stylesheet" href="../sysManage/css/mytab.css">

<script src="../sysManage/popdom/xmlRPC.js" type="text/javascript"></script>
<script src="../sysManage/popdom/tigra_tables.js" type="text/javascript"></script>
<script src="../sysManage/popdom/util.js" type="text/javascript"></script>
<script src="../sysManage/popdom/load_container.js" type="text/javascript"></script>
<script src="../sysManage/popdom/dataTable.js" type="text/javascript"></script>
<script src="../sysManage/popdom/xtree.js" type="text/javascript"></script>
<script src="../sysManage/popdom/rightMenu.js" type="text/javascript"></script>
<script src="../sysManage/popdom/permissionModify.js" type="text/javascript"></script>
<script type="text/javascript">
	var groupId = '<%=groupId%>';
	alert("roleModify："+groupId);
	function f_close(){
		top.f_closeTab("roleModify");   		
	}	
</script>
<style type="text/css">
    .l-button-submit,.l-button{width:80px;  }
     .blackbold { /* 标题样式 */
	line-height: 22px;
	width: 100px;
	height: 22px;
	font-size: 12px;
	font-weight: bold;
	color: #FF5317
    </style>
</head>

<body onload="createTree();initTable();" style="padding:6px; background-color: #F7F8F9;">

	<table width="100%" border="0">
		<tr><td colspan="2" height="5"></td></tr>
		<tr><td class="blackbold_b" ><img src="../images/util/arrow6.gif" hspace="5">角色基本信息</td><td align="right">&nbsp;</td></tr>
	</table>

<table width="100%" cellspacing="3">
	<tr><td colspan="2" align="center">
			<table width="100%" cellspacing="3" class="border_all_light">
				<tr>
					<td class="text_title" width="90">&nbsp;&nbsp;<font color="#ff0000">*</font>角色名称：</td>
					<td ><input id="groupId" name="groupId" value="${requestScope.groupId}" style="display: none"/><input class="input_t"  id="groupName" border="1" type="text" name="groupName" style="width:50%" value="${requestScope.groupName}"></td>
				</tr>		
				<tr>
					<td class="text_title" width="90">&nbsp;&nbsp;&nbsp;角色描述：</td>
					<td><textarea  class="input_t"  rows="3" id="remark" name="remark"  style="width:50%;background-color: #FFFFFF">${requestScope.remark}</textarea></td>
				</tr>
			</table>
		</td></tr>
</table>



<div id="operAuthorityDiv" style="height:325;width:100%;overflow:auto">
<table width="100%">
	<tr><td colspan="2" height="5"></td></tr>
	<tr><td class="blackbold"><img src="../images/util/arrow6.gif" hspace="5">增加权限</td><td></td></tr>
	<tr ><td colspan="2" height="1" bgcolor="#BBD2E9" ></td></tr>
</table>

<table bordercolor="red" width="100%" border="0" cellspacing="1" cellpadding="1">
	<tr>
		<td><div align="center"><strong>已有权限</strong></div></td>
		<td></td>
		<td><div align="center"><strong>所有权限</strong></div></td>
	</tr>
	<tr valign="top" >
		<td width="57%" style="border: 1px">
		   <fieldset style="top:inherit;width:98%;height:275;">
		    <div align="left" style="width:100%;height:260;overflow:auto;">
			<table id="popedomTable" width="100%" border="0" cellspacing="1" cellpadding="1" class="tableSty2">
				<tr align="center">
					<th width="30">序号</th><th>权限名称</th><th width="80">菜单级别</th><th width="30">选择</th>
				</tr>
			</table>
		     </div>
		   </fieldset>
		</td>
		<td width="6%">
          <div align="center" style="width:100%;position:absolute;top:150px">
            <p><img src="../images/tree/frame_gz_ods_toLeft.gif" width="20" height="20" onClick="addPopedom()" style="cursor:hand" alt=""></p>
                <br>
            <p><img src="../images/tree/frame_gz_ods_toRight.gif" width="20" height="20" onClick="delPopedom()"style="cursor:hand"  alt=""></p>
          </div>
        </td>
		<td align="center" width="37%">
			<fieldset style="top:inherit;width:98%;height:275;">
			    <div align="left" style="width:100%;height:260;overflow:auto;" id="treeDiv"></div>
			</fieldset>
		</td>
	</tr>
</table>
</div>

<table  width="100%" cellpadding="0" cellspacing="0" class="l-table-edit" align="center">
  <tr ><td colspan="2" height="2" bgcolor="#BBD2E9" ></td></tr>
  <tr  height="25" valign="bottom">
	    <td width="48%" align="right" class="l-table-edit-td">
		     <input type="submit" class="l-button" id="Button1"  value="确定" onclick="confirmModify()" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td align="left" class="l-table-edit-td">
		     <input name="button" type="button" class="l-button" value="取消"   onclick="f_close()"/></td>
    </tr>
</table>


</body>
</html>
