<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<html>
<head>
<title>工单信息列表页面</title>
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
        
        var inId = "${param.inId}";
         function f_initGrid(){
      		grid = manager = $("#maingrid4").ligerGrid({
                columns: [
                { display: '工单号', name: 'inId',width: 150, minWidth: 60 },
                { display: '渠道编码', name: 'canalId',width: 80, minWidth: 60 },
                { display: '渠道名称', name: 'canalName',width: 100, minWidth: 60 },
                { display: '流程名称', name: 'stepVal',width: 100, minWidth: 60 ,hide:1},
                { display: '操作人', name: 'operUser', width: 80, minWidth: 60 },
                { display: '部门名称', name: 'deptName', width: 80, minWidth: 60 },
				{ display: '操作时间', name: 'operTime', width:100, minWidth: 60 },
				{ display: '审批结果', name: 'processState', width: 80, minWidth: 60 },
				{ display: '审批意见', name: 'remark', width: 150, minWidth: 60 }
                ], 
                dataAction: 'server',
                pageSize:30,
                where : f_getWhere(),
                rownumbers:true, 
                checkbox:false,
	        	url: 'canalProcessDetailAction?inId='+inId,
	        	allowAdjustColWidth:true,
	        	width: '100%', 
	        	height: '98%'
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
    </style>

</head>

<body style="padding:6px; overflow:hidden;">
<div id="maingrid4" style="margin:0; padding:0"></div>
<div style="display: none;"> </div>

</body>
</html>
