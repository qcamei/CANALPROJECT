<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
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
	<script>
	 var grid = null;   
	 var manager=null;     
	        $(function() {               	
	        	<pageADMOperAuth.tld:pageADMOperAuth menuId="'040501','040502','040503','040504'"></pageADMOperAuth.tld:pageADMOperAuth>
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
	            $("#mainFailure").ligerComboBox({url :'mainFailureQueryAction', isMultiSelect: false});	
	            $("#subFailure").ligerComboBox({url :'subFailureQueryAction', isMultiSelect: false, valueFieldID: 'failureId'});		 
	            //ligerForm需在ligerComboBox之后 
	            $("form").ligerForm();
	        });        
	        
	         function f_initGrid()
	        {
	        
	        grid = manager = $("#maingrid4").ligerGrid({
	                columns: [	                    
	                { display: '区域id', name: 'areaId', width: 100, minWidth: 60 ,hide:1},      
	                { display: '区域名称', name: 'areaName', width: 100, minWidth: 60 }
	                ],
	                dataAction: 'server',
	                pageSize:30,
	                where : f_getWhere(),
	                rownumbers:true, 
	                checkbox:true,
	                url: 'getAreaQueryActionII',
	                allowAdjustColWidth:true,
	                width: '100%', height: '98%', 
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
	         function f_query()
	         {
	        	if (!manager) return;	        	
	        	var mainFailure = encodeURIComponent($("#mainFailure").val());
	        	var failureId = encodeURIComponent($("#failureId").val());
	            manager.setOptions(
	                { parms: [{name:'mainFailure',value:mainFailure},{name:'failureId',value:failureId}] }
	            );
	            
	            manager.loadData(true);
	         }
	         function f_query_all()
	         {
	        	 if (!manager) return;	        	
	        	 	$("#mainFailure").val("全部");
	        	 	$("#subFailure").val("全部");
	        	 	$("#failureId").val("");
		            manager.setOptions(
		                { parms: [] }
		            );
		            
		            manager.loadData(true);
	         }
	       
	       
	       function f_closeAddWindow(item, dialog)
	        {
	            
	           $.ligerDialog.confirm('确认要关闭窗口吗', function (yes)
	            {
	               if(yes)
	                   dialog.close();
	            })
	            manager.loadData(true);
	        }
	       
	   		function f_add()
	        {
	   			
	   			$.ligerDialog.open({
		        	   title: '区域添加',
		        	   height: 400, 
		        	   width: 700, 
		        	   url: 'sysManage/areaAdd.jsp', 
		        	   showMax: true, 
		        	   isResize: true, 
		        	   slide: false,
		        	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
	   			}); 
	        }
	   		function f_add_excel(){
	 			$.ligerDialog.open({ 
	 				title: '添加配件',
	 				height: 300, 
	 				width: 600, 
	 				url: 'baseInfo/brandAddByExcel.jsp', 
	 				showMax: true,
	 				isResize: true,
	 				slide: false,
	        	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]
	        	}); 
	 		}
	 		
	       function f_modify()
	        {	
	            var row = manager.getCheckedRows();
	           
	           	if (row.length == 0) { 
	           		$.ligerDialog.error('请选择需要修改的行！');
	           		return ; 
	           	}else if(row.length > 1){           	
		       		$.ligerDialog.error('修改时只能选择一行！'); 
		       		return ;  
	       		}else{      
	       			var actionURL = 'areaModifyQueryAction?areaId='+row[0].areaId;
	       			
	       			$.ligerDialog.open({ title: '修改品牌信息',height: 400, width: 700, url: actionURL, showMax: true,  isResize: true, slide : false,
	 	        	   buttons: [{ text: '关闭窗口', onclick: f_closeAddWindow }]}); 
	           	 
	          	}      
	        }

	        function f_delete()
	        {
	         
	            if(!manager) {
	            		$.ligerDialog.error('请查询后,选中需要删除的条目,再删除！')
	            		return;
	            } else {
	            	var row = manager.getCheckedRows();
	            	if (row.length == 0) { $.ligerDialog.error('请选择需要删除的行！'); return; }
	            	$.ligerDialog.confirm('确认要删除选中的记录吗？删除后不能恢复，请谨慎操作！', function (yes) {              
	                     if(yes)
	                     {
	                    	  var txtID = "";
					    	  for(var i=0;i<row.length-1;i++){   
					    		  txtID += row[i].areaId+";";		
					       	  }
					    	  if(i == row.length-1){//最后一个不加分号	    		  
					       		  txtID += row[i].areaId;
					    	  }
	                    	  $.ajax({   
					              url :'areaDeleteAction?txtID='+txtID,  //后台处理程序   
					              type:'post',    //数据发送方式   
					              dataType:'text',   //接受数据格式   
					              data:null,   //要传递的数据；就是上面序列化的值   
					              success:function(result){
					            	 
	                    		  	if(result=='success'){
	                    		  		manager.deleteSelectedRow();
	                    		  		$.ligerDialog.success('删除成功！');
	                    		  	}else if(result=='changed'){
	                    		  		$.ligerDialog.error('该入库单数据被操作过，不能删除！');
	                    		  	}else{
	                    		  		$.ligerDialog.error('系统异常,删除失败,请重试或联系系统管理员！');
	                    		  	}
					              } //回传函数(这里是函数名)    
						     });   
	                     //根据主键用ajax访问action删除数据；删除后给出提示框，成功或失败
	       				}   
	  				 });
	            	
	            }   
	          
	    	}
	        
        function updateSubFailure(){
        	$("#failureId").val("");
        	$("#subFailure").val("");
        	
        	    var actionURL = "subFailureQueryAction?mainFailure="+encodeURIComponent(encodeURIComponent($("#mainFailure").val()));
		 		  //var actionURL = "subFailureQueryAction?mainFailure=1";
		 		   //alert(encodeURIComponent(encodeURIComponent($("#mainFailure").val())));
		 		   $.ajax({
		 				type:"POST",
		 				url:actionURL,
		 				dataType:"json",
		 				success:function(data){
		 					$("#subFailure").ligerGetComboBoxManager().setData(data);
		 					
		 				}
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
<body style="padding:6px; overflow:hidden;">
	<table width="100%">
		<tr>
			<td height="2"></td>
		</tr>
		
		<tr>
			<td height="1" bgcolor="#F3A148"></td>
		</tr>

	</table>
	
	<fieldset style="top:inherit;width:100%">
		<form name="form1" method="post" action="" id="form1">

			<table cellpadding="0" cellspacing="0" class="l-table-edit">			
			

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
			<td class="blackbold_b"><img src="images/util/arrow6.gif"
				hspace="5">区域列表</td>
		</tr>

	</table>
<div id="maingrid4" style="margin:0; padding:0"></div>
	

</body>
</html>