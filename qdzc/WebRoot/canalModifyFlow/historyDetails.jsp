<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>渠道信息修改历史列表</title>
    <link href="../com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    
    <link href="../com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
    <script src="../com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>  
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>         
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>  
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>  
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../com_css/js/jquery.blockUI.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="../com_css/js/scanSerialNumber.js"></script>
	<script>
	 var grid = null;   
	 var manager=null;     
	        $(function() {        
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
	       
	            f_initGrid();         
	          
	            //ligerForm需在ligerComboBox之后
	            $("form").ligerForm();
	        	get_curDay();
	            get_lDay();
	            
	        }); 
	         function get_curDay() {
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
	        }
	      
	         function f_initGrid()
	        {
	        grid = manager = $("#maingrid4").ligerGrid({
	                columns: [
	                      	{ display: '编号', name: 'id', width: 50, minWidth: 50 },
	                      	{ display: '代理商编码', name: 'agentId', width: 80, minWidth: 50 },
	                    	{ display: '渠道编码', name: 'canalId', width: 80, minWidth: 50 },
	                    //	{ display: 'CRM工号', name: 'userNumber', width: 80, minWidth: 50 },
	                    	
	                    	{ display: '操作人', name: 'operUser', width: 70, minWidth: 60 },
	    					{ display: '操作部门', name: 'operDept', width: 100, minWidth: 60 },
	    					{ display: '操作时间', name: 'operTime', width: 100, minWidth: 60 },
	    					{ display: '操作字段', name: 'operColumn', width: 60, minWidth: 60 },
	    					{ display: '原始值', name: 'oldValue', width: 100, minWidth: 60 },
	    					{ display: '修改值', name: 'newValue', width: 100, minWidth: 60 }
	    					], 
	    					dataAction: 'server',
	    					pageSize:20,
	    					where : f_getWhere(),
	    					rownumbers:false, 
	    					checkbox:false,
	    		            url: 'historyQueryAction?canalId=${param.canalId}',
	    		            allowAdjustColWidth:true,
	    		            width: '100%', 
	    		            height: '100%'                              
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
    
	       function f_closeAddWindow(item, dialog)
	        {
	           $.ligerDialog.confirm('确认要关闭窗口吗', function (yes)
	            {
	               if(yes)
	                   dialog.close();
	            });
	            manager.loadData(true);
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
	</table>

<div id="maingrid4" style="margin:0; padding:0"></div>
</body>

<script type="text/javascript">
function f_modify(){
		var row = manager.getSelectedRow();
   		if (!row) { 
   			$.ligerDialog.error('请选择需要操作的行！');
   			return ; 
   		}
		var m = $.ligerDialog.open({ 
    	  title: '渠道变更',
    	  height: 400, 
    	  width: 700, 
    	//  url: 'canalInfoAdd1.jsp', 
    	  url: 'canalModifyDetailAction?inId='+row.inId,
    	  showMax: true, 
    	  isResize: true,      	 
    	  buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
      }); 
      m.max();
}

function f_detail(){
	var row = manager.getSelectedRow();
   	if (!row) { 
   		$.ligerDialog.error('请选择需要操作的行！');
   		return ; 
   	}
   	var inId = row.inId;
	var m = $.ligerDialog.open({ 
  	  	title: '工单详情',
  	  	height: 400, 
  	  	width: 700, 
  		//url: 'canalInfoDetailAction?inId='+inId+'&type=detail', 
  		url:'canalModifyFlow/detailQuery.jsp?inId='+row.inId,
  	   	showMax: true, 
  	   	isResize: true,      	 
  	   	buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
    }); 
    m.max();
}
</script>
</html>