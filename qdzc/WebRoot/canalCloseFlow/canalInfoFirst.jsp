<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>渠道关闭信息首列表</title>
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
	        	<pageADMOperAuth.tld:pageADMOperAuth menuId="'070101','070103'"></pageADMOperAuth.tld:pageADMOperAuth>       	
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
	            $("#nextStepVal").ligerComboBox({url :'nextStepValQueryCloseAction', isMultiSelect: false});	
	          	
	            f_initGrid();         
	          
	            //ligerForm需在ligerComboBox之后
	            $("form").ligerForm();
	        	//get_curDay();
	           // get_lDay();
	            
	        }); 
	      /*   function get_curDay() {
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
		                      	{ display: '工单号', name: 'closeId', width: 100, minWidth: 50 },
		                      	{ display: '操作人员', name: 'userName', width: 60, minWidth: 50 },
		                    	{ display: '审核部门', name: 'deptName', width: 150, minWidth: 60 },
		                    	{ display: '部门编码', name: 'deptId', width: 70, minWidth: 50 },	                   	
		                    	{ display: '当前步骤', name: 'currentStepVal', width: 70, minWidth: 60 },
		                    	{ display: '当前状态', name: 'auditState', width: 70, minWidth: 60 ,render: function (row){
	        				        if (row.auditState == '驳回')
	    				                return  '<font color="#ff0000">'+row.auditState+'</font>'; 
	    				        else return row.auditState; 
	    				    }},
		                    	{ display: '下一步骤', name: 'nextStepVal', width: 70, minWidth: 60 },	    					
		                    	{ display: '渠道编码', name: 'canalId', width: 80, minWidth: 60 },
		    					{ display: '渠道名称', name: 'canalName', width: 80, minWidth: 60 },
		    					{ display: '步骤编号', name: 'stepNo', width: 60, minWidth: 60,hide:1 },
		    				    { display: '操作时间', name: 'operTime', width: 120, minWidth: 100 },
		    				    { display: '渠道申请时工单号', name: 'inId', width: 120, minWidth: 100,hide:1  },	    				    
		    				    { display: '申请原因', name: 'refuseReason',width: 120, minWidth: 100 }
		    					], 
		    						dataAction: 'server',
		    					pageSize:20,
		    					where : f_getWhere(),
		    					rownumbers:false, 
		    					checkbox:false,
		    		            url: 'canalCloseQueryAction',
		    		            allowAdjustColWidth:true,
		    		            width: '100%', 
		    		            height: '100%',
		    		            toolbar:toolBar                               
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
	        	var closeId = encodeURI($("#closeId").val());
	        	var userName = encodeURI($("#userName").val());
	        	var canalName = encodeURI($("#canalName").val());
	        	var canalId= encodeURI($("#canalId").val());
	        	var nextStepVal = encodeURI($("#nextStepVal").val());
	        	
	            manager.setOptions(
	                { parms: [{name:'txtBeginDate',value:txtBeginDate},
							  {name:'txtEndDate',value:txtEndDate},
							  {name:'closeId',value:closeId},
							  {name:'canalName',value:canalName},
							  {name:'canalId',value:canalId},
							  {name:'userName',value:userName},
							  {name:'nextStepVal',value:nextStepVal}
							
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
	                 { parms: [{name:'txtBeginDate',value:txtBeginDate},{name:'txtEndDate',value:txtEndDate},{name:'nextStepVal',value:nextStepVal}] }
	            );
	            manager.loadData(true);	             
	         }

	           function f_excelOut() {
	           	//查询条件
	           	  if (!manager) return;	

	        	var txtBeginDate = encodeURI($("#txtBeginDate").val());
	        	var txtEndDate = encodeURI($("#txtEndDate").val());
	        	var closeId = encodeURI(encodeURI($("#closeId").val()));
	        	var userName =encodeURI(encodeURI($("#userName").val()));
	        	var canalName = encodeURI(encodeURI($("#canalName").val()));
	        	var canalId= encodeURI(encodeURI($("#canalId").val()));
            	var nextStepVal = encodeURI(encodeURI($("#nextStepVal").val()));	       	
	          	$.ligerDialog.confirm('确认要导出渠道信息吗？', function (yes){
	    	            
	    	               if(yes)
	    	            	   window.open('canalCloseInfoDownExcel?txtBeginDate='+txtBeginDate+'&txtEndDate='+txtEndDate+'&closeId='+closeId+'&userName='+userName+'&canalName='+canalName+'&canalId='+canalId+'&nextStepVal='+nextStepVal);
	           	   			window.close();
	    	            });
	           
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
					<td><input	name="closeId"  id="closeId"  type="text"/></td>
					<td align="right" class="l-table-edit-td">渠道名称:</td>
					<td><input	name="canalName"    id="canalName"  type="text"/></td>		
					<td align="right" class="l-table-edit-td">渠道编码:</td>
					<td><input	name="canalId"    id="canalId"  type="text"/></td>
					<td align="right" class="l-table-edit-td">操作人:</td>
					<td><input	name="userName"    id="userName"  type="text"/></td>
				    <td align="right" class="l-table-edit-td">下一状态:</td>
					<td><input	name="nextStepVal"    id="nextStepVal"  type="text" value="分公司审核"/></td>
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
			<td class="blackbold_b"><img src="images/util/arrow6.gif" hspace="5">关闭工单列表</td>
		</tr>
	</table>
<div id="maingrid4" style="margin:0; padding:0"></div>
</body>

<script type="text/javascript">
function f_close(){
		var row = manager.getSelectedRow();
   		
		var m = $.ligerDialog.open({ 
    	  title: '渠道关闭',
    	  height: 400, 
    	  width: 700, 
    	  url: 'canalCloseFlow/canalInfo1.jsp', 
    	  showMax: true, 
    	  isResize: true,      	 
    	  buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
      }); 
      m.max();
      
}

function f_delete(){
		var row = manager.getSelectedRow();		
   		if (!row) { 
   			$.ligerDialog.error('请选择需要操作的行！');
   			return ; 
   		}
   		
   		var ints=row.inId;
		var actionURL = "chaXunCurrentStepCloseAction?inId="+ints;
		var ints2;
		$.ajax({
				type:"POST",
				url:actionURL,
				dataType:"json",
				async:false,
				success:function(data){
					//console.log(data);
					//构建监控指标数组seriesArr
					$.each(data, function(key,val){
						ints2 = val.currentStep;
					});
				}
			});
	
	
		var action3="chaXunCurrentStateCloseAction?currentStep="+ints2+'&inId='+ints;
		var ganga;
		var papa;
		$.ajax({
			type:"POST",
			url:action3,
			dataType:"json",
			async:false,
			success:function(data){
				//console.log(data);
				//构建监控指标数组seriesArr
				$.each(data, function(key,val){
					ganga = val.auditState;
					papa=ganga.replace(/  /g,  "");
				});
			}
		});
   if(papa!='驳回'){
		alert("当前渠道正在审核，不能取消！");
			return;
		}
   $.ligerDialog.confirm('确认要取消申请关闭选中的记录吗？请谨慎操作！', function (yes) {              
	                     if(yes)
	                     {
	                    	var txtID = "";
					    	  for(var i=0;i<row.length-1;i++){   
					    		  txtID += row[i].selectId+";";		
					       	  }
					    	  if(i == row.length-1){//最后一个不加分号	    		  
					       		  txtID += row[i].selectId;
					    	  }
	                    	  $.ajax({   
					              url :'canalQuxiaoCloseDetailAction?closeId='+row.closeId+'&inId='+row.inId,//后台处理程序   
					              type:'post',    //数据发送方式   
					              dataType:'text',   //接受数据格式   
					              data:null,   //要传递的数据；就是上面序列化的值   
					              success:function(result){
	                    		  	if(result.substring(0,7)=="success"){    		  	      
	                    		  		$.ligerDialog.success('取消关闭渠道成功！');
	                    		  		manager.loadData(true);
	                    		  	}else if(result=='error'){
	                    		  		$.ligerDialog.error('系统异常,关闭失败,请重试或联系系统管理员！');
	                    		  	}
					              } //回传函数(这里是函数名)    
						     });   
	                     //根据主键用ajax访问action删除数据；删除后给出提示框，成功或失败
	       				}   
	  				 });
	

  
}

</script>
</html>