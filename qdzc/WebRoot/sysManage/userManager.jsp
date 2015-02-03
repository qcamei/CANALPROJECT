<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<html>
<head>
<title>用户管理</title>
     <link href="com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />   
    <link href="com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
    <script src="com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>  
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>         
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>  
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>  
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
      
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript">
    var grid = null;   
    var manager=null;  
        $(function() {
            
            <pageADMOperAuth.tld:pageADMOperAuth menuId="'040301','040302','040303','040304'"></pageADMOperAuth.tld:pageADMOperAuth>
             f_initGrid();
            var v = $("form").validate({
	                //调试状态，不会提交数据的
	                debug: true,
	                errorLabelContainer: "#errorLabelContainer", wrapper: "li",
	                invalidHandler: function (form, validator) {
	                    $("#errorLabelContainer").show();
	                    setTimeout(function () {
	                        $.ligerDialog.tip({ content: $("#errorLabelContainer").html() });
	                    }, 10);
	                },
	                submitHandler: function () {
	                    $("#errorLabelContainer").hide();
	                    alert("Submitted!");
	                }
	            });            
	            $(".l-button-test").click(function () {
	                alert(v.element($("#txtName")));
	            });
	            
	            
	            $("#queryDept").ligerComboBox({
	          		url :'selectItemQueryAction?type=all_dept',
	          		isMultiSelect : false,
	          		isShowCheckBox : false,
	          		valueFieldID: 'queryDeptId'
	          	});   
            $("form").ligerForm();
          
            manager= $("#maingrid4").ligerGetGridManager();
           // $("#txtUserGroup").ligerComboBox({ data: null, isMultiSelect: true, isShowCheckBox: true });
             
        }); 
        
        
        function f_add()
        {
            /*$.ligerDialog.open({ title: '新增用户信息', width: 650, height: 300,modal:false,url: 'sysManger/userManagerAdd.jsp', buttons: [
                { text: '关闭窗口', onclick: f_closeAddWindow }
            ]
            });*/
            $.ligerDialog.open({ 
	        	  title: '新增用户信息',
	        	  height: 400, 
	        	  width: 700, 
	        	  url:'sysManage/userManagerAdd.jsp',	        	
	        	  showMax: true,
	 				isResize: true,
	 				slide: false, 
	        
	        	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
	          }); 
        }
        
        
       
         
      
       
        function getXMLHttpRequest() {
   			var xhr;   
    		try {   
       			xhr = new XMLHttpRequest();  
   			} catch (err1) {  
       			try {   	   
            		xhr = new ActiveXObject("Microsoft.XMLHTTP");   
            		alert(xhr);
       	 		} catch (err2) {   
            		alert("您的浏览器版本不支持Ajax....");   
        		}   
 
    		}   
  
   		return xhr; 
		}
   

 function f_modify()
        {
            
            if(!manager) {
            		$.ligerDialog.error('请查询用户清单后,选中需要修改的条目,再修改！')
            		return;
            } else {
            	var row = manager.getSelectedRow();
            	if (!row) { $.ligerDialog.error('请选择需要修改的行！'); return; }
            	$.ligerDialog.open({ 
  	        	  title: '修改用户信息',
  	        	  height: 400, 
  	        	  width: 700, 
  	        	  url: 'userManagerModifyAction?userId='+row.userId,	        	
  	        	   showMax: true,	        	   
  	        	  isResize: true,   
  	        	  slide: false,  	        
  	        	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
  	          }); 
            }
        }
        
         function f_delete()
        {
           
            var manager = $("#maingrid4").ligerGetGridManager();
            if(!manager) {
            		$.ligerDialog.error('请查询用户清单后,选中需要删除的条目,再删除！')
            		return;
            } else {
            	var row = manager.getSelectedRow();
            	if (!row) { $.ligerDialog.error('请选择需要删除的行！'); return; }
            	$.ligerDialog.confirm('确认要删除该用户信息吗?若删除,用户信息将丢失', function (yes)
                     {
              
                     if(yes)
                     {
                     	var xhr = getXMLHttpRequest();
    					xhr.open("POST", "userManagerDeleteUserAction?userId="+row.userId, true);   
    					xhr.send(); 
    					xhr.onreadystatechange = function() { 
       						if (xhr.readyState == 4 && xhr.status == 200) {  
           					var responseContext = xhr.responseText;
                            if(responseContext=='success') 
                            {
                            	manager.deleteSelectedRow();
                            	$.ligerDialog.success('删除成功！');
                            }else
                            {
                            	$.ligerDialog.error('系统异常,请联系系统管理员！');
                            }
        
       	   
       }   
  
    }   
                     
                     }
                      
                     }) 
            	//根据主键用ajax访问action删除数据；删除后给出提示框，成功或失败
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
        
        function f_initGrid()
        {
        	grid = manager = $("#maingrid4").ligerGrid({
            columns: [
                 { display: '序号', name: 'orderId',width: 50, minWidth: 60 },
                 { display: '登录名', name: 'userId',  width: 150, minWidth: 60 },
                 { display: '用户姓名', name: 'userName', width: 150, minWidth: 60},
                 { display: '用户部门', name: 'deptName', width: 150, minWidth: 60 },                
                 { display: '用户所属组', name: 'groupName', width: 150, minWidth: 60},
                 { display: '状态', name: 'userState', width: 50, minWidth: 60 },
                 { display: '用户联系方式', name: 'userMobPhone', width: 150, minWidth: 60 },
                 { display: '邮箱', name: 'userEmail', width: 200, minWidth: 60 }
                 
                 ], 
                 		where : f_getWhere(),
                  		url: 'userManagerQueryAction',
                     	dataAction: 'server',
                        pageSize:25,
                        rownumbers:false,
                        //checkbox:true,
                       allowAdjustColWidth:true,
                       columnWidth:110,
                       width: '100%', 
                       height: '98%',
                       frozen:false,
                       detail: { 
                    	onShowDetail: f_showOrder ,
                    	height:320
                     },
                    allowUnSelectRow:true,
                    toolbar: toolBar
                  
            });
 
        }
          
        function f_showOrder(row, detailPanel,callback)
        {
       	 var actionUrl = "searchUserManageCanal?userName="+row.userName;
            actionUrl = encodeURI(encodeURI(actionUrl));
		     $(".l-selected").removeClass("l-selected");
           var gridDetail = document.createElement('div'); 
           $(detailPanel).append(gridDetail);
           manager2 = $(gridDetail).css('margin',10).ligerGrid({
                columns:
                       [ 
                        { display: '渠道名称', name: 'canalName',width: 100, minWidth: 60 },
                        { display: '渠道编码', name: 'canalId',width: 80, minWidth: 60 },
                        { display: '客户经理', name: 'canalUserName', width: 80, minWidth: 60 },
                        { display: '区域', name: 'areaName', width: 80, minWidth: 60 },
                        { display: '状态', name: 'canalState', width: 50, minWidth: 60 },
                        { display: '形态', name: 'canalForm', width: 50, minWidth: 60 },
                        { display: '性质', name: 'canalProperty', width: 50, minWidth: 60 },
                        { display: '类型', name: 'canalType', width: 50, minWidth: 60 },
                        {display : 'VPDN',name : 'broadbandAccount',width : 80,minWidth : 60},
                        { display: '联系电话', name: 'canalUserPhone', width: 50, minWidth: 60 },
                        { display: '上级渠道', name: 'canalParentName', width: 80, minWidth: 60 }
                         ], 
                         where : f_getWhere(),
                         pageSize:15,
                         isScroll: false, 
                         showToggleColBtn: false,
                                             showTitle: false,
                                             url:actionUrl,
                                             onAfterShowData: callback,
                                             frozen:false,
                                             detail: {
                                             onShowDetail: f_showOrder2,
                                             height:370 
                                             },
                                             allowUnSelectRow:true
                                    });  
                                    $(detailPanel).show();   
                                }  
      function f_showOrder2(row, detailPanel,callback)
        {
   	    var actionUrl = "searchUserManageCanalUser?canalId="+row.canalId+"&canalName="+row.canalName;
   	    actionUrl = encodeURI(encodeURI(actionUrl));
   	    var gridDetail = document.createElement('div'); 
           $(detailPanel).append(gridDetail);
           
           $(gridDetail).css('margin',10).ligerGrid({
           columns:
               [  
           {display:'员工编号',name:'userId'},
           {display:'员工姓名',name:'userName'},
           {display:'员工性别',name:'userSex'},
           {display:'员工联系电话',name:'userPhone'}
                        ], 
                        pageSize:8,
                        where : f_getWhere(),
                        isScroll: false, 
                        showToggleColBtn: false,
                        showTitle: false,  
                        width: '100%',
                        url:actionUrl,
                        columnWidth : 115,
                        width : 1050,
                        onAfterShowData: callback,
                        frozen:false,
                        allowUnSelectRow:true,
         });  
         $(detailPanel).show();    
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
        
  			function f_query()
	         {
	        	if (!manager) return;	        	
	        	var queryDeptId = encodeURIComponent($("#queryDeptId").val());
	        	var username = encodeURIComponent($("#username").val());
	        	var telephone = encodeURIComponent($("#telephone").val());
	            manager.setOptions(
	                { parms: [{name:'queryDeptId',value:queryDeptId},
	                				{name:'username',value:username},
	                				{name:'telephone',value:telephone}]
	                }
	            );
	            
	            manager.loadData(true);
	         }
	         function f_query_all()
	         {
	        	 if (!manager) return;	        	
	        	var queryDeptId = "";
	            manager.setOptions(
	                { parms: [{name:'queryDeptId',value:queryDeptId}] }
	            );
		            
		            manager.loadData(true);
	         }
	       
function selectParentRole(obj) {
			var time=new Date();
			var url = "sysManage/organizationAddDeptTree.jsp";
			var orgIdAndName = window.showModalDialog(url+"?time="+time , window,
	    		'dialogHeight:400px;dialogWidth:300px;center:yes;status:no;help:no;resizable:yes;edge:Raised');
   			if(orgIdAndName){
				var org = orgIdAndName.split(":");
				var orgId = org[0];
				var orgName = org[1];
				if(orgId.indexOf(";;") != -1){
				orgId = orgId.substring(0,orgId.indexOf(";;"));
			}
		document.getElementById("queryDeptId").value = orgId;
		document.getElementById("queryDept").value = orgName;
   		}
}


	    /*    
	         function f_down(){
    	 		 $("#downloadbtn").click();
	        }
		
		          function f_action(){
    	  		 $("#formdownload").submit();
      		 }
      		 
      		*/ 
      		function f_down(){	    	
        		var queryDeptId = encodeURIComponent($("#queryDeptId").val());
        		var actionUrl = "UserDown?queryDeptId="+queryDeptId; 
	       		window.open(actionUrl);
	    		window.close();
	     	}
      		 
		
    </script>
    <style type="text/css">
body{ font-size:12px;}
.blackbold_b { /* 标题样式 */
	line-height: 22px;
	width: 150px;
	height: 22px;
	font-size: 12px;
	font-weight: bold;
	color: #FF5317
}
.l-table-edit-td{ padding:4px;font-size:12px;}


    </style>

</head>

<body style="padding:6px; overflow:hidden;">
<div id="toptoolbar"></div> 


    
    <div style="display:none">
    <!--  数据统计代码 --></div>

   
 


 <table width="100%">
		<tr>
			<td height="2"></td>
		</tr>
		<tr>
			<td class="blackbold"><img src="images/util/arrow6.gif"
				hspace="5">筛选条件</td>

		</tr>
		<tr>
			<td height="1" bgcolor="#BBD2E9"></td>
		</tr>

	</table>
	
	<fieldset style="top:inherit;width:100%">
		<form name="form1" method="post" action="" id="form1">

			<table cellpadding="0" cellspacing="0" class="l-table-edit">
	<tr>
		<td align="right" class="l-table-edit-td">网点:</td>
		<td align="left" class="l-table-edit-td">
		 	<!-- <input  type="hidden" name="queryDeptId" id="queryDeptId" />
	     	<input name="queryDept" type="text" id="queryDept" ltype="text" value="点击选择部门" readonly="readonly" onClick="selectParentRole(this)" />
	     -->
	     <input name="queryDept" type="text" id="queryDept" ltype="text" value="全部" />
	    </td>
	    <td align="right" class="l-table-edit-td">姓名:</td>
		<td align="left" class="l-table-edit-td"> <input name="username" type="text" id="username" ltype="text"  /></td>
	   <td align="right" class="l-table-edit-td">联系电话:</td>
		<td align="left" class="l-table-edit-td"> <input name="telephone" type="text" id="telephone" ltype="text"  /></td>
	    <td align="right" class="l-table-edit-td">
			<input type="button" value="查询" id="Button1" class="l-button" style="width:60px" onClick="f_query()" />
		</td>
		<td align="left" class="l-table-edit-td">
			<input type="button" value="查询所有" id="Button2" class="l-button" style="width:80px"	onclick="f_query_all()" />
		</td>
	</tr>
	</table>

		</form>
	</fieldset>
	
	

    
<table width="100%">
 	<tr >
		<td colspan="2" height="1" bgcolor="#BBD2E9" ></td>
	</tr>
	<tr>
		<td colspan="2" height="5"></td>
	</tr>
	<tr>
		<td class="blackbold_b"><img src="images/util/arrow6.gif" hspace="5">用户信息</td>
	</tr>
	    
</table>


<div id="maingrid4" style="margin:0; padding:0"></div>
<div style="display: none;"> </div>

</body>
</html>
