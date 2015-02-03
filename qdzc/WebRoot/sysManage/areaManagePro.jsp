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
	       var grid = null;   
	 var manager=null;     
	        $(function() {               	
	        	<pageADMOperAuth.tld:pageADMOperAuth menuId="''"></pageADMOperAuth.tld:pageADMOperAuth>
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
	            $("#cityName").ligerComboBox({url :'areaProQueryAction', isMultiSelect: false,valueFieldID:'cityId'});	
	            
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
	        	var cityName = encodeURIComponent($("#cityName").val());
	        	var cityId = encodeURIComponent($("#cityId").val());
	            manager.setOptions(
	                { parms: [{name:'cityId',value:cityId}] }
	            );
	            
	            manager.loadData(true);
	         }
	         function f_query_all()
	         {
	        	 if (!manager) return;	        	
	        	 	$("#cityName").val("全部");
	        	 	$("#cityId").val(null);
	        	 	
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
			<td class="blackbold"><img src="images/util/arrow6.gif"
				hspace="5">筛选条件</td>

		</tr>
		<tr>
			<td height="1" bgcolor="#F3A148"></td>
		</tr>

	</table>
	
	<fieldset style="top:inherit;width:100%">
		<form name="form1" method="post" action="" id="form1">

			<table cellpadding="0" cellspacing="0" class="l-table-edit">			
				<tr>
					<td align="right" class="l-table-edit-td">选择市:</td>
					<td align="left" class="l-table-edit-td"><input
						name="cityName" type="text" id="cityName"   />
					</td>	
					
								
					<td align="right" class="l-table-edit-td"><input type="button"
						value="查询" id="Button1" class="l-button" style="width:80px"
						onclick="f_query()" /></td>
					<td align="right" class="l-table-edit-td"><input type="button"
						value="查询所有" id="Button1" class="l-button" style="width:80px"
						onclick="f_query_all()" /></td>
				</tr>

			</table>

		</form>
	</fieldset>

	<table width="100%" id="result">
		<tr>
			<td  height="1" bgcolor="#F3A148"></td>
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