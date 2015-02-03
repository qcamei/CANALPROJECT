<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page import="com.cqupt.pub.dao.DataStormSession,java.util.*" %>
<html>
<head>

<title>修改角色权限</title>
<link type="text/css" rel="stylesheet" href="css/storm.css"/>
<link type="text/css" rel="stylesheet" href="css/xtree.css"/>
<link type="text/css" rel="stylesheet" href="css/mytab.css"/>

<script src="js/xmlRPC.js" type="text/javascript"></script>
<script src="js/tigra_tables.js" type="text/javascript"></script>
<script src="js/util.js" type="text/javascript"></script>
<script src="js/load_container.js" type="text/javascript"></script>
<script src="js/dataTable.js" type="text/javascript"></script>
<script src="js/xtree.js" type="text/javascript"></script>
<script src="js/rightMenu.js" type="text/javascript"></script>
<script src="js/permissionAdd.js" type="text/javascript"></script>


<script type="text/javascript">

function lastPage(){
   window.history.go(-1);
}

    function selectParentRole(obj) {
			var time=new Date();
			var url="groupAuthSelectTree.jsp";
			var orgIdAndName = window.showModalDialog(url+"?time="+time , window,
	    		'dialogHeight:400px;dialogWidth:300px;center:yes;status:no;scroll:yes;help:no;resizable:yes;edge:Raised');
   			if(orgIdAndName){
				var org = orgIdAndName.split(":");
				var orgId = org[0];
				var orgName = org[1];
				if(orgId.indexOf(";;") != -1){
				orgId = orgId.substring(0,orgId.indexOf(";;"));
			}
		document.getElementById("hidGroupId").value = orgId;
		document.getElementById("groupName").value = orgName;
   		}
}

 
</script>
</head>

<body onload="createTree();setOnReset();">

<%
	String groupId = request.getParameter("groupId");
	if (!groupId.equals("null")) {

		System.out.println("角色ID:" + groupId);
		DataStormSession dataSession = null;
		dataSession = DataStormSession.getInstance();
		String sql = "select group_name,group_parent_id from lztass.sys_user_group   where group_id='"+ groupId + "'";
		System.out.println("查询角色名:" + sql);
		List resultList = dataSession.findSql(sql);
		Map resultMap = (Map)resultList.get(0); 
%>


<table width="100%" border="0">
	<tr>
		<td colspan="2" height="5"></td>
	</tr>
	<tr>
		<td class="blackbold_b" ><img src="../images/util/arrow6.gif" hspace="5">角色基本信息</td>
		<td align="right">&nbsp;</td>
	</tr>
	<tr >
		<td colspan="2" height="2" bgcolor="#BBD2E9" ></td>
	</tr>
</table>

	<%
				String groupName = resultMap.get("groupName").toString();
				String groupParentId = resultMap.get("groupParentId").toString();
					
					sql = "select group_name from lztass.sys_user_group   where group_id='"+ groupParentId + "'";
					System.out.println("查询父角色:" + sql);
					resultList = dataSession.findSql(sql);
					resultMap = (Map)resultList.get(0);
					String groupParentName = resultMap.get("groupName").toString();
System.out.println("一 " +"groupId:" + groupId +"，二 " +"groupName:"+groupName+"，三 "+ "groupParentId:"+groupParentId+"，四   "+"groupParentName:"+groupParentName);
						
						
	
				%> 
<table width="100%" cellspacing="3">
	<tr>
		<td colspan="2" align="center">
			<table width="100%" cellspacing="3" class="border_all_light">
				<tr>
	                <td class="text_title" width="90"><font color="#ff0000"></font>角色ID：</td>
					<td><input class="input_t" border="1" type="text" name="groupId" id="groupId" style="width:200px" value="<%=groupId%>" readonly="readonly"/></td>
						
				
					<td class="text_title" width="90"><font color="#ff0000"></font>角色名称：</td>
					<td ><input class="input_t" border="1" type="text" name="roleName" id="roleName" style="width:300px"  value="<%=groupName%>" readonly="readonly"/></td>
			
					<td class="text_title" width="90"><font color="#ff0000"></font>父角色名称：</td>
					<td ><input class="input_t" border="1" type="text" name="groupParentName"  style="width:200px" value="<%=groupParentName%>" readonly="readonly"/></td>
				</tr>
			
			</table>
		</td>
	</tr>
</table>


<div id="tabsJ" style="width:100%;overflow:auto;">
<ul>
	<li id='tab' lang="tab1">
		<a href="javascript:setTab('tab1', 'oper')"><span >菜单权限</span></a>
	</li>

</ul>
</div>

<div id="operAuthorityDiv" style="height:480;width:100%;overflow:auto">
<table width="100%">
	<tr>
		<td colspan="2" height="5"></td>
	</tr>
	<tr>
		<td class="blackbold"><img src="../images/util/arrow6.gif" hspace="5"/>增加权限</td>
		<td></td>
	</tr>
	<tr >
		<td colspan="2" height="2" bgcolor="#BBD2E9" ></td>
	</tr>
</table>


<table bordercolor="red" width="100%" border="0" cellspacing="1" cellpadding="1">
	<tr>
		<td><div align="center"><strong>已有权限</strong></div></td>
		<td></td>
		<td><div align="center"><strong>所有权限</strong></div></td>
	</tr>
	<tr valign="top" >
		<td width="37%" style="border: 1px" align="left">
			<fieldset style="top: inherit; width: 98%; height: 400;">
				<div align="left"
					style="width: 100%; height: 380; overflow: auto;">
					<table id="popedomTable" width="100%" border="0" cellspacing="1"
						cellpadding="1" class="tableSty2">
						<tr align="center">
							<th width="30">
								序号
							</th>
							<th>
								权限名称
							</th>
							<th width="80">
								菜单级别
							</th>
							<th width="30">
								选择
							</th>
						</tr>
					</table>
				</div>
			</fieldset>
		</td>
		<td width="6%" align="left">
          <div align="center" style="width:100%;top:250px">
            <p>
              <img src="../images/tree/frame_gz_ods_toLeft.gif" width="20" height="20" onClick="addPopedom()" style="cursor:hand" alt=""/>
            </p>
            <br/>
            <p>
              <img src="../images/tree/frame_gz_ods_toRight.gif" width="20" height="20" onClick="delPopedom()"style="cursor:hand"  alt=""/>
            </p>
          </div>
        </td>
		<td align="right" width="37%">
		<fieldset style="top:inherit;width:98%;height:400;">
			<div align="left" style="width:100%;height:380;overflow:auto;" id="treeDiv"></div>
		</fieldset>
		</td>
	</tr>
</table>
</div>
<div id="menuArray">

    <%
    sql = "select menuid from lztass.sys_user_group_oper_auth t WHERE t.group_id ='"+ groupId + "' order by menuid ";
System.out.println("查询菜单:" + sql);
	resultList = dataSession.findSql(sql);
	String menuId ="" ;      //菜单Id
	//String formMenuId;       // 前一个菜单Id
	int size = 0;
	size = resultList.size();
	//List menuIdArray = new ArrayList();
	String menuIdArray = "[";
	
	for(int i=0;i<resultList.size();i++) {
	//formMenuId=menuId;
	resultMap = (Map)resultList.get(i);
	menuId = resultMap.get("menuid").toString();
	if(i == 0)
	menuIdArray += "\""+menuId+"\"";
	menuIdArray +=",\""+menuId+"\"";
	
	
	System.out.println("menuId:"+menuId);}
	menuIdArray += "]";	
	%>	   	   
			  <script type="text/javascript">
			  var menuId= "<%= menuId%>";
			 
			  //if(menuId)
			  //addPopedomInit(menuId);
			  
			  var menuIdArray = <%= menuIdArray%>;
			 // alert("menuIdArray: "+menuIdArray);
			  //alert(menuIdArray instanceof Array);
			  
			  addPopedomInit2(menuIdArray);
			//  alert("end");
			  </script>	 	   
<%
     
      
      // 185 for end;
	  
    dataSession.closeSession();
	}  // 52 if end    %> 
</div> 	


<table width="100%">
	<tr height="25" valign="bottom">
		<td colspan="3" align="center">
			<input class="button_2z" type="button" value="确定" onclick="confirmModify()" name="addButton">&nbsp;&nbsp;
			<input class="button_2z" type="button" value="返回"  onclick="lastPage()">
		</td>
	</tr>
</table>
<script type="text/javascript">
	setTab('tab1', 'oper', true);
</script>


</body>
</html>
