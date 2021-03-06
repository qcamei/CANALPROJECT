<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<html>
<head>
	 <link href="../com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
   
    <link href="../com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
    <script src="../com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>  
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>  
     <script type="text/javascript">
     var grid = null;      
      $(function ()
        {
         	<pageADMOperAuth.tld:pageADMOperAuth menuId="'040401','040402'"></pageADMOperAuth.tld:pageADMOperAuth>            
            f_initGrid();
        });
       //'040401',
      
        function f_add(){    
        	var m =$.ligerDialog.open({ 
	        	  title: '新增角色',
	        	  height: 400, 
	        	  width: 700, 
	        	  url: 'roleAdd.jsp',	        	
	        	   showMax: true,	        	   
	        	  isResize: true,   
	        	  slide: false,  	        
	        	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
	          });
        	m.max();
        }
         function f_modify(){
         	var  manager = $("#maingrid4").ligerGetGridManager();
            if(!manager) {
            		$.ligerDialog.error('请查询部门清单后,选中需要修改的条目,再修改！')
            		return;
            } else {
            	var row = manager.getSelectedRow();
            	if (!row) { 
            		$.ligerDialog.error('请选择需要修改的行！'); 
            		return;
            	}
            	 var m = $.ligerDialog.open({ 
   	        	  title: '修改角色信息',
   	        	  height: 400, 
   	        	  width: 700, 
   	        	  url: 'roleModify?groupId='+row.groupId,	        	
   	        	   showMax: true,	        	   
   	        	  isResize: true,   
   	        	  slide: false,  	        
   	        	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
   	          });
            	 m.max();
            }
        }
             
        function f_closeAddWindow(item, dialog)
        {
            
           $.ligerDialog.confirm('确认要关闭窗口吗', function (yes)
                     {
                     if(yes)
                         dialog.close();
                     })
            
        }
        
        
        
        
        function getArgs(strParame) {
         var args = new Object();
         var query = location.search.substring(1); // Get query string
         var pairs = query.split("&"); // Break at ampersand
         for (var i = 0; i < pairs.length; i++) {
             var pos = pairs[i].indexOf('='); // Look for "name=value"
             if (pos == -1) continue; // If not found, skip
             var argname = pairs[i].substring(0, pos); // Extract the name
             var value = pairs[i].substring(pos + 1); // Extract the value
             value = decodeURIComponent(value); // Decode it, if needed
             args[argname] = value; // Store as a property
         }
         return args[strParame]; // Return the object
     }

        
           
         function f_initGrid()
        {
        var actionAdr = encodeURI(encodeURI("groupAuthListQueryAction?group="+getArgs('group')));
        grid = $("#maingrid4").ligerGrid({
                columns: [
                { display: '序号', name: 'rownum',width: 40, minWidth: 30 },
                
                { display: '角色组ID', name: 'groupId',  width: 150, minWidth: 60 },
                { display: '角色组名', name: 'groupName', width: 250, minWidth: 60},
                { display: '上级角色组', name: 'parentGroupName', width: 250, minWidth: 60}
                ], dataAction: 'server',pageSize:30,where : f_getWhere(),
                url: actionAdr, sortName: 'orderId',
                width: '100%', height: '100%',
                toolbar: toolBar
            });
        
        }
        
           function f_search()
        { 
            grid.loadData(f_getWhere());
        }
        function f_getWhere()
        {
            if (!grid) return null;
            var clause = function (rowdata, rowindex)
            {
                var key = $("#txtKey").val();
                return rowdata.orderId.indexOf(key) > -1;
            };
            return clause; 
        }
        
 
    </script>
     <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        .blackbold { /* 标题样式 */
	line-height: 22px;
	width: 100px;
	height: 22px;
	font-size: 12px;
	font-weight: bold;
	color: #FF5317
}

.blackbold_b { /* 标题样式 */
	line-height: 22px;
	width: 150px;
	height: 22px;
	font-size: 12px;
	font-weight: bold;
	color: #FF5317
}
    </style>
    
<title>权限管理</title>
</head>
<body style="padding:6px; overflow:hidden;">
<div id="toptoolbar"></div> 
<table width="100%" id="result">
	<tr>
		<td colspan="2" height="5"></td>
	</tr>
	<tr>
		<td class="blackbold_b"><img src="../images/util/arrow6.gif" hspace="5"/>权限列表</td>
	</tr>
	    
</table>


<div id="maingrid4" style="margin:0; padding:0"></div>




 
</body>
</html>
