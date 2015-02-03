<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../checkLogin.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>增加角色</title>
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
<script src="../sysManage/popdom/permissionAdd.js" type="text/javascript"></script>
	
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

<script type="text/javascript">
	function f_close(){
		top.f_closeTab("roalAdd");   		
	}	
</script>
</head>

<body onload="createTree();setOnReset();" style="padding:6px; background-color: #F7F8F9;">
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
		     <input name="submit" type="submit" class="l-button" id="Button1"  value="确定" onclick="confirmAdd()" name="addButton"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	    <td align="left" class="l-table-edit-td">
		     <input name="button" type="reset" class="l-button" value="取消"   onclick="f_close()"/></td>
    </tr>
</table>

</body>
</html>
