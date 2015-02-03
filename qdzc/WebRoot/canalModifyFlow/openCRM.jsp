<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<html>
<head>
<title>工单归档 （变更流程）</title>
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
    	<pageADMOperAuth.tld:pageADMOperAuth menuId="'060501','060502','060503','060504','060505'"></pageADMOperAuth.tld:pageADMOperAuth>       	
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
        $("#nextStepVal").ligerComboBox({url :'nextStepValQueryAction1', isMultiSelect: false});	
      	
        f_initGrid();         
      
        //ligerForm需在ligerComboBox之后
        $("form").ligerForm();
    //get_curDay();
        //get_lDay();
        
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
   
         function f_initGrid() {
      
        grid = manager = $("#maingrid4").ligerGrid({
        	
                columns: [
                { display: '工单号', name: 'inId',width: 90, minWidth: 60 },
                { display: '当前状态', name: 'currentStepVal',width: 60, minWidth: 60 },
                { display: '下一状态', name: 'nextStepVal',width: 60, minWidth: 60 },
                { display: '下一状态ID', name: 'currentStep',width: 10, minWidth: 60 ,hide:1},
                
               { display: '是否驳回', name: 'isBack',width: 50, minWidth: 60,
                	render: function (row){
				        if (row.isBack == '驳回')
				                return  '<font color="#ff0000">'+row.isBack+'</font>'; 
				        else return row.isBack; 
				    }	
                },
                { display: '操作个数', name: 'number', width: 70, minWidth: 60},
                { display: '渠道编码', name: 'canalId', width: 70, minWidth: 60 },
				{ display: '渠道名称', name: 'canalName', width: 80, minWidth: 60 },
				{ display: '区域', name: 'areaName', width: 80, minWidth: 60 },
				{ display: '状态', name: 'canalState', width: 50, minWidth: 60 },
				{ display: '城乡', name: 'canalForm', width: 50, minWidth: 60 },
				{ display: '管理属性', name: 'canalProperty', width: 50, minWidth: 60 },
				{ display: '分类名称', name: 'canalType', width: 50, minWidth: 60 },
				{ display: '客户经理', name: 'canalUserName', width: 80, minWidth: 60 },
				{ display: 'VPDN', name: 'broadbandAccount', width: 80, minWidth: 60 },
				{ display: '上级渠道', name: 'canalParentName', width: 80, minWidth: 60 },
				{ display: '归属代理商', name: 'agentName', width: 80, minWidth: 60 },
              
				{ display: 'CRM工号', name: 'crmNumber', width: 60, minWidth: 60},
				{ display: '绑定手机号码', name: 'telephone', width: 100, minWidth: 60},
				
				{ display: '操作部门', name: 'deptName',  width: 60, minWidth: 60 },
                { display: '操作人', name: 'operUser', width: 60, minWidth: 60},
                { display: '操作时间', name: 'checkTime', width: 60, minWidth: 60 },                
                { display: '备注', name: 'remark', width: 50, minWidth: 60 }
                ], 
                url: 'crmQuery',
                dataAction: 'server',
                pageSize:30,
                where : f_getWhere(),
                rownumbers:false, 
                checkbox:false,
               	allowAdjustColWidth:true,
               width: '100%', 
               height: '98%', 
               toolbar: toolBar
            });
                   
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
        
        function f_query(){        
       	if (!manager) return;	        	
       	var txtBeginDate = encodeURI($("#txtBeginDate").val());
       	var txtEndDate = encodeURI($("#txtEndDate").val());
       	var inId = encodeURI($("#inId").val());
       	var canalId = encodeURI($("#canalId").val());
       	var canalName = encodeURI($("#canalName").val());
       	var areaName = encodeURI($("#areaName").val());
       	var nextStepVal = encodeURI($("#nextStepVal").val());
           manager.setOptions(
               { parms: [{name:'txtBeginDate',value:txtBeginDate},
						  {name:'txtEndDate',value:txtEndDate},
						  {name:'inId',value:inId},
						  {name:'canalId',value:canalId},
						  {name:'nextStepVal',value:nextStepVal},
						  {name:'canalName',value:canalName},
						  {name:'areaName',value:areaName}
						  ]}
           );
           
           manager.loadData(true);
        }
        
          function f_query_all()
        {        
       	if (!manager) return;
           document.getElementById('txtBeginDate').value= '全部';
           document.getElementById('txtEndDate').value= '全部';
           $("#nextStepVal").val("全部");
       	var txtBeginDate = encodeURI($("#txtBeginDate").val());
       	var txtEndDate =encodeURI($("#txtEndDate").val());
       	var nextStepVal = encodeURI($("#nextStepVal").val());
           manager.setOptions(
                { parms: [{name:'nextStepVal',value:nextStepVal},{name:'txtBeginDate',value:txtBeginDate},{name:'txtEndDate',value:txtEndDate}] }
           );
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
	            var nextStepVal = encodeURI(encodeURI($("#nextStepVal").val()));	                	
	           	$.ligerDialog.confirm('确认要导出渠道信息吗？', function (yes){	    	            
	           	  if(yes)
	           		  window.open('"canalAlterOpenCRMInfoDownExcel"?txtBeginDate='+txtBeginDate+'&txtEndDate='+txtEndDate+'&inId='+inId+'&areaName='+areaName+'&canalName='+canalName+'&canalId='+canalId+'&nextStepVal='+nextStepVal);
	           	   			window.close();
	    	            });
	           
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

<body style="padding:6px;background:#f9fbfc; overflow:hidden;">
<div id="toptoolbar"></div> 


    
    <div style="display:none">
    <!--  数据统计代码 --></div>

   
 


 <table width="100%">
		<tr>
			<td height="2"></td>
		</tr>
		<tr>
			<td class="blackbold_b"><img src="images/util/arrow6.gif"
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
					<td align="right" class="l-table-edit-td">工单号:</td>
					<td><input	name="inId"  id="inId"  type="text"/></td>
					<td align="right" class="l-table-edit-td">渠道编码:</td>
					<td><input	name="canalId"  id="canalId"  type="text"/></td>
						<td align="right" class="l-table-edit-td">区域:</td>
					<td><input	name="areaName"  id="areaName"  type="text"/></td>
					<td align="right" class="l-table-edit-td">渠道名称:</td>
					<td><input	name="canalName"    id="canalName"  type="text"/></td>		
					<td align="right" class="l-table-edit-td">下一状态:</td>
					<td><input	name="nextStepVal"    id="nextStepVal"  type="text" value="开CRM工号"/></td>
				 	
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
						<input type="button"	value="查询" id="Button1" class="l-button" style="width:80px"
							onclick="f_query()" /></td>
						<td><input type="button"	value="查询全部" id="Button2" class="l-button" style="width:80px" onclick="f_query_all()" /></td>
						<td align="left" class="l-table-edit-td"></td>
							<td><input type="button"	value="导出" id="Button2" class="l-button" style="width:80px" onclick="f_excelOut()" /></td>
						<td align="left" class="l-table-edit-td"></td>						
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
		<td class="blackbold_b"><img src="images/util/arrow6.gif" hspace="5">变更工单列表</td>
	</tr>
	    
</table>


<div id="maingrid4" style="margin:0; padding:0"></div>
<div style="display: none;"> </div>

</body>

<script type="text/javascript">
function f_modify(){
	var row = manager.getSelectedRow();
	if (!row) { 
		$.ligerDialog.error('请选择需要操作的行！');
		return ; 
	}
	var inId = row.inId;
	//var number = row.number;
	var currentStep = row.currentStep;
	//if(number <=0 ){
	//	alert("您已不能做此操作！");
	//	return;
	//}
	
	var canalName = row.canalName;
	var canalId = row.canalId;
	var canalName = decodeURI(canalName);
	canalName = encodeURIComponent(encodeURIComponent(canalName));
	var m = $.ligerDialog.open({ 
		  title: '审核操作',
		  height: 400, 
		  width: 700, 
		   url: 'canalModifyFlow/detailModify.jsp?inId='+inId+'&canalName='+canalName+'&canalId='+canalId+'&process=openCRM', 
		   showMax: true, 
		   isResize: true
	  }); 
  m.max();
}

function getDetails(){
	var row = manager.getSelectedRow();
	if (!row) { 
		$.ligerDialog.error('请选择需要操作的行！');
		return ; 
	}
     var m = $.ligerDialog.open({ 
    	  	title: '显示详细',
    	 	height: 500, 
    	  	width: 800, 
    	    url:'canalModifyFlow/detail.jsp?canalId='+row.canalId,
    	  	showMax: true,
			isResize: true,
			slide: false
      }); 
    m.max();
}
function f_detail(){
	var row = manager.getSelectedRow();
	 var m = $.ligerDialog.open({ 
	 	  	title: '修改详情',
	 	 	height: 500, 
	 	  	width: 800, 
	 	  	url:'canalModifyFlow/historyDetails.jsp?canalId='+row.canalId,
	 	  	showMax: true,
			isResize: true,
			slide: false
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
    window.open('canalModifyFlow/processDetailSave.jsp?canalId='+row.canalId);
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
      	 url:'canalModifyFlow/processDetailPrint.jsp?canalId='+row.canalId,  	
      	 showMax: true,	        	   
      	 isResize: true,   
      	 slide: false
    }); 
   m.max();
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

</script>
</html>
