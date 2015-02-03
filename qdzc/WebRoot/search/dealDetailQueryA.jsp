<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String inId = request.getParameter("inId");
%>
<html>
<head>
<title>渠道用户管理</title>
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
      
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="../com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="../com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="../com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript">
    var grid = null;   
    var manager=null;  
        $(function() {

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
            $("form").ligerForm();
       
            manager= $("#maingrid4").ligerGetGridManager();
             
        }); 
        
   

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

        function f_closeAddWindow(item, dialog)
        {
           	$.ligerDialog.confirm('确认要关闭窗口吗', function (yes){
                if(yes)
                         dialog.close();
            });
         }
        
        function f_initGrid()
        {
        	var canalId='<%=request.getParameter("canalId")%>';
        	
        grid = manager = $("#maingrid4").ligerGrid({
                columns: [
                      	{ display: '工单号', name: 'closeId', width: 150, minWidth: 130,hide:1 },
                      	{ display: '审核人', name: 'userName', width: 150, minWidth: 120 },
                      	{ display: '审核步骤', name: 'currentStepVal', width: 150, minWidth: 130 },
                    	{ display: '审核意见', name: 'auditState', width: 150, minWidth: 100 },  
                    	{ display: '审核时间', name: 'operTime', width: 150, minWidth: 100 },   				    
    				    { display: '处理意见', name: 'refuseReason',width: 300, minWidth: 160 }
   				    
    					], 
    					dataAction: 'server',
    					pageSize:20,
    					where : f_getWhere(),
    					rownumbers:true, 
    					checkbox:false,
    		            url: 'canalCloseDealDetailQueryAAction?canalId='+canalId,
    		            allowAdjustColWidth:true,
    		            width: '100%', 
    		            height: '100%'                            
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

<div id="maingrid4" style="margin:0; padding:0"></div>
<div style="display: none;"> </div>

</body>
</html>
