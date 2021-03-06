<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>渠道信息列表</title>
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
    <script src="com_css/js/jquery.blockUI.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="com_css/js/scanSerialNumber.js"></script>
	<script>
	 var grid = null;   
	 var manager=null;     
	        $(function() {        
	        	<pageADMOperAuth.tld:pageADMOperAuth menuId="'051301','051302','051303','051304','051305','051306'"></pageADMOperAuth.tld:pageADMOperAuth>       	
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
	            $("#areaName").ligerComboBox({url :'areaQueryAction', isMultiSelect: false});	
	          	
	            f_initGrid();         
	          
	            //ligerForm需在ligerComboBox之后
	            $("form").ligerForm();
	        	//get_curDay();
	           // get_lDay();
	            
	        }); 
	        /* function get_curDay() {
	        	var today = new Date();   
	    		var day = today.getDate();   
	    		var month = today.getMonth() + 1;   
	    		var year = today.getFullYear(); 
	    		if(month*1 < 10){
	    			month = '0'+month;
	    		}
	    		if(day*1 < 10){
	    			day = '0'+day;
	    		}
	    		var date = year + "-" + month + "-" + day;  
	    		document.getElementById('txtEndDate').value= date;
	    		
	        }
	        
	         function get_lDay() {   //获取本月1号
	        	var today = new Date();
	    		var month = today.getMonth() + 1;   
	    		var year = today.getFullYear(); 
	    		if(month*1 < 10){
	    			month = '0'+month;
	    		}
	    		var date = year + "-" + month + "-" + "01";  
	    		document.getElementById('txtBeginDate').value= date;
	        }*/
	      
	         function f_initGrid()
	        {
	        grid = manager = $("#maingrid4").ligerGrid({
	                columns: [
	                      	{ display: '工单号', name: 'inId', width: 150, minWidth: 60 },
	                      	{ display: '当前状态', name: 'currentStepVal', width: 50, minWidth: 50 },
	                    	{ display: '下一状态', name: 'nextStepVal', width: 50, minWidth: 50 },
	                    	{ display: '是否驳回', name: 'isBack', width: 60, minWidth: 50,
	                    		render: function (row){
	        				        if (row.isBack == '驳回')
	        				                return  '<font color="#ff0000">'+row.isBack+'</font>'; 
	        				        else return row.isBack; 
	        				    }
	                    	},
	                    	{ display: '驳回原因', name: 'reason', width: 70, minWidth: 60 },
	                    	{ display: '渠道编码', name: 'canalId', width: 70, minWidth: 60 },
	                    	{ display: '工号', name: 'crmNumber', width: 80, minWidth: 60 },
	    					{ display: '翼支付账号', name: 'inCollectNumber', width: 80, minWidth: 60 },
	    					{ display: '渠道名称', name: 'canalName', width: 80, minWidth: 60 },
	    					{ display: '区域', name: 'areaName', width: 80, minWidth: 60 },
	    					{ display: '状态', name: 'canalState', width: 60, minWidth: 60 },
	    					{ display: '城乡', name: 'canalForm', width: 60, minWidth: 60 },
	    					{ display: '管理属性', name: 'canalProperty', width: 60, minWidth: 60 },
	    					{ display: '分类名称', name: 'canalType', width: 60, minWidth: 60 },
	    					//{ display: '渠道性质', name: 'canalXingzhi', width: 80, minWidth: 60 },
	    					{ display: '上级渠道', name: 'canalParentName', width: 80, minWidth: 60 },
	    					{ display: '归属代理商', name: 'agentName', width: 80, minWidth: 60 },
	    					{ display: 'VPDN', name: 'broadbandAccount', width: 80, minWidth: 60 },
	    					{ display: '城乡标识', name: 'urbanIdetity', width: 60, minWidth: 60 },
	    					{ display: '客户经理', name: 'canalUserName', width: 60, minWidth: 60 },
	    					{ display: '联系电话', name: 'canalUserPhone', width: 60, minWidth: 60 },
	    					{ display: '申请操作人', name: 'operUser', width: 60, minWidth: 60 },
	    					{ display: '申请时间', name: 'operTime', width: 80, minWidth: 60 },
	    					{ display: '申请人部门', name: 'deptName', width: 60, minWidth: 60 }
	    					], dataAction: 'server',pageSize:20,where : f_getWhere(),rownumbers:true, checkbox:false,
	    		                url: 'canalInfoQueryAction',allowAdjustColWidth:true,
	    		                width: '100%', height: '100%',toolbar:toolBar                               
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
	        
	         function f_query()
	         {        
	        	if (!manager) return;	        	
	        	//还需要传areaName,deptId、deptName
	        	var txtBeginDate = encodeURI($("#txtBeginDate").val());
	        	var txtEndDate = encodeURI($("#txtEndDate").val());
	        	var inId = encodeURI($("#inId").val());
	        	var canalId = encodeURI($("#canalId").val());
	        	var canalName = encodeURI($("#canalName").val());
	        	var areaName = encodeURI($("#areaName").val());
	        	var canalUserPhone = encodeURI($("#canalUserPhone").val());
	            manager.setOptions(
	                { parms: [{name:'txtBeginDate',value:txtBeginDate},
							  {name:'txtEndDate',value:txtEndDate},
							  {name:'inId',value:inId},
							  {name:'canalId',value:canalId},
							  {name:'canalName',value:canalName},
							  {name:'areaName',value:areaName},
							  {name:'canalUserPhone',value:canalUserPhone}
							  ]}
	            );
	            
	            manager.loadData(true);
	         }
	         
	           function f_query_all()
	         {        
	        	if (!manager) return;
	            document.getElementById('txtBeginDate').value= '全部';
	            document.getElementById('txtEndDate').value= '全部';
	        	var txtBeginDate = encodeURI($("#txtBeginDate").val());
	        	var txtEndDate =encodeURI($("#txtEndDate").val());
	            manager.setOptions(
	                 { parms: [{name:'txtBeginDate',value:txtBeginDate},{name:'txtEndDate',value:txtEndDate}] }
	            );
	            manager.loadData(true);	             
	         }
	       
	       function f_closeAddWindow(item, dialog)
	        {
	           $.ligerDialog.confirm('确认要关闭窗口吗', function (yes)
	            {
	               if(yes)
	                   dialog.close();
	            });
	            manager.loadData(true);
	        }
	   	
	       function f_excelOut() {
	           	//查询条件
	            if (!manager) return;		        
	        	var txtBeginDate = encodeURI($("#txtBeginDate").val());
	        	var txtEndDate = encodeURI($("#txtEndDate").val());
	        	var inId = encodeURI(encodeURI($("#inId").val()));
	        	var areaName =encodeURI(encodeURI($("#areaName").val()));
	        	var canalName = encodeURI(encodeURI($("#canalName").val()));
	        	var canalId= encodeURI(encodeURI($("#canalId").val()));
	        	var canalUserPhone = encodeURI($("#canalUserPhone").val());
	          	$.ligerDialog.confirm('确认要导出渠道信息吗？',
	          			function (yes){	  
	          	  if(yes)
	    	            	   window.open('canalInfoDownExcel?txtBeginDate='+txtBeginDate+'&txtEndDate='+txtEndDate+'&inId='+inId+'&areaName='+areaName+'&canalName='+canalName+'&canalId='+canalId+'&canalUserPhone='+canalUserPhone);
	           	   			window.close();
	    	            });           
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
</head>
<body style="padding:6px;background:#f9fbfc">
	<table width="100%">
		<tr>
			<td height="2"></td>
		</tr>
		<tr>
			<td class="blackbold"><img src="images/util/arrow6.gif"
				hspace="5">条件筛选</td>
		</tr>
		<tr>
			<td height="1" bgcolor="#BBD2E9"></td>
		</tr>
	</table>
	<fieldset style="top:inherit;width:100%">
		<form name="form1" method="post" action="" id="form1">
			<table cellpadding="0" cellspacing="0" class="l-table-edit">			
				<tr>
					<td align="right" class="l-table-edit-td">工单号:</td>
					<td><input	name="inId"  id="inId"  type="text"/></td>
					<td align="right" class="l-table-edit-td">渠道编码:</td>
					<td><input	name="canalId"  id="canalId"  type="text"/></td>
						<td align="right" class="l-table-edit-td">区域:</td>
					<td><input	name="areaName"  id="areaName"  type="text"/></td>
					<td align="right" class="l-table-edit-td">渠道名称:</td>
					<td><input	name="canalName"    id="canalName"  type="text"/></td>		
						<td align="right" class="l-table-edit-td">负责人电话:</td>
					<td><input	name="canalUserPhone"    id="canalUserPhone"  type="text"/></td>
				</tr>
				<tr>
						<td align="right" class="l-table-edit-td">开始时间:</td>
						<td align="left" class="l-table-edit-td"><input
							name="txtBeginDate" type="text" id="txtBeginDate"  ltype="date"/>
						</td>
						<td align="right" class="l-table-edit-td">结束时间:</td>
						<td align="left" class="l-table-edit-td">
						<input	name="txtEndDate" type="text" id="txtEndDate"  ltype="date"/></td>	
						<td align="right" class="l-table-edit-td">
						<input type="button"	value="查询" id="Button1" class="l-button" style="width:80px"onclick="f_query()" /></td>
						<td><input type="button"	value="查询全部" id="Button2" class="l-button" style="width:80px" onclick="f_query_all()" /></td>
						<td align="left" class="l-table-edit-td"></td>
					     <td><input type="button"	value="导出" id="Button3" class="l-button" style="width:80px" onclick="f_excelOut()" /></td>
						<td align="left" class="l-table-edit-td"></td>							
				</tr>
			</table>

		</form>
	</fieldset>

	<table width="100%" id="result">
		<tr>
			<td  height="1" bgcolor="#BBD2E9"></td>
		</tr>
		<tr>
			<td height="5"></td>
		</tr>
		<tr>
			<td class="blackbold_b"><img src="images/util/arrow6.gif" hspace="5">变更工单列表</td>
		</tr>
	</table>
<div id="maingrid4" style="margin:0; padding:0"></div>
</body>

<script type="text/javascript">
function f_add(){
		var m = $.ligerDialog.open({ 
    	  title: '新增渠道',
    	  height: 400, 
    	  width: 700, 
    	  url: 'canalManage/canalInfoAdd.jsp', 
    	   showMax: true, 
    	   isResize: true
      }); 
      m.max();
	}
function f_modifyCanal(){
	var row = manager.getSelectedRow();
   	if (!row) { 
   		$.ligerDialog.error('请选择需要操作的行！');
   		return ; 
   	}
   var inId = row.inId;
   var isBack = row.isBack;
   if(isBack != "驳回"){
	   $.ligerDialog.error('驳回状态才能进行修改操作！');
  		return ; 
   }
	var m = $.ligerDialog.open({ 
	  title: '渠道修改',
	  height: 400, 
	  width: 700, 
	  url: 'canalManage/canalInfoXiugai.jsp?inId='+row.inId, 
	   showMax: true, 
	   isResize: true
  }); 
  m.max();
}


function f_bulu(){
	var row = manager.getSelectedRow();
   	if (!row) { 
   		$.ligerDialog.error('请选择需要操作的行！');
   		return ; 
   	}
    var inId = row.inId;
	var m = $.ligerDialog.open({ 
	  title: '补录字段',
	  height: 400, 
	  width: 700, 
	  url: 'canalManage/canalInfoAdd_0.jsp?inId='+inId, 
	  showMax: true, 
	  isResize: true
  }); 
  m.max();
}

function f_save(){
	var row = manager.getSelectedRow();
	if (!row) { 
   		$.ligerDialog.error('请选择需要操作的行！');
   		return ; 
   	}
    if(row.currentStepVal == "受理"){
    	$.ligerDialog.error("该工单还未开始审核，请等待...");
		return;
    }
    window.open('canalManage/processDetailSave.jsp?inId='+row.inId);
	window.close();
}

function f_print(){
	var row = manager.getSelectedRow();
	if (!row) { 
   		$.ligerDialog.error('请选择需要操作的行！');
   		return ; 
   	} 
    if(row.currentStepVal == "受理"){
    	$.ligerDialog.error("该工单还未开始审核，请等待...");
		return;
    }
    var m = $.ligerDialog.open({ 
      	 title: '打印工单处理信息',
      	 height: 500, 
      	 width: 1000, 
      	 url:'canalManage/processDetailPrint.jsp?inId='+row.inId,  	
      	 showMax: true,	        	   
      	 isResize: true,   
      	 slide: false
    }); 
   m.max();
}

function showDetail(){
	var row = manager.getSelectedRow();
   	if (!row) { 
   		$.ligerDialog.error('请选择需要操作的行！');
   		return ; 
   	}
   	var inId = row.inId;
	var m = $.ligerDialog.open({ 
  	  title: '渠道明细',
  	  height: 400, 
  	  width: 700, 
  	  url:'canalManage/detail.jsp?inId='+row.inId,
  	  showMax: true, 
  	  isResize: true
   }); 
    m.max();
}
</script>
</html>