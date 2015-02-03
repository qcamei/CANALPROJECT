<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="com_css/css/my.css" />
<link href="mainFrame/js/themes/default/easyui.css" rel="stylesheet" type="text/css"	/>
<link href="mainFrame/js/themes/icon.css"  rel="stylesheet" type="text/css"	/>

      <link href="./com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
   
    <link href="./com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
    <script src="./com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>  
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>         
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>  
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>  
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
      
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="./com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="./com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="./com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
 <script type="text/javascript">
    var grid = null;   
    var manager=null;  
        $(function() {
             f_initGrid();    	
             f_initGrid2();    
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
        
         function f_initGrid(){
      		grid = manager = $("#maingrid4").ligerGrid({
                columns: [
                { display: '渠道编码', name: 'canalId',width: 80, minWidth: 60 },
                { display: '渠道名称', name: 'canalName',width: 100, minWidth: 60 },
                { display: '当前状态', name: 'curStep',width: 110, minWidth: 60 },
                { display: '下一状态', name: 'nextStep',width: 110, minWidth: 60 }
                ], 
                dataAction: 'server',
                pageSize:30,
                where : f_getWhere(),
                rownumbers:true, 
                checkbox:false,
	        	url: 'shouyeListAction',
	        	allowAdjustColWidth:true,
	        	width: '440px', 
	        	height: '200px'
            }); 
        }
        function f_initGrid2(){

      		grid = manager = $("#maingrid5").ligerGrid({
                columns: [
                { display: '渠道编码', name: 'canalId',width: 80, minWidth: 60 },
                { display: '渠道名称', name: 'canalName',width: 100, minWidth: 60 },
                { display: '当前状态', name: 'curStep',width: 110, minWidth: 60 },
                { display: '下一状态', name: 'nextStep',width: 110, minWidth: 60 }
                ], 
                dataAction: 'server',
                pageSize:30,
                where : f_getWhere(),
                rownumbers:true, 
                checkbox:false,
	        	url: 'shouyeListModifyAction',
	        	allowAdjustColWidth:true,
	        	width: '440px', 
	        	height: '200px'
            }); 
        }
        function f_getWhere(){
            if (!grid) return null;
            var clause = function (rowdata, rowindex){
                var key = $("#txtKey").val();
                return rowdata.orderId.indexOf(key) > -1;
            };
            return clause; 
        }
        
   </script>

<style type="text/css">
.small_contailer_box1{	
	height: 280px;  	
}
.small_contailer_box2{
	background:#FFFFFC;
	width:80%;
	height: 170px;  
	border: #BBD2E9 0px solid;
	margin: 1px 0px 3px 3px;
	float: left;
	overflow-y:scorll;	
}
.small_contailer_box1 .hr-content-cell{
	padding: 5px 0 0 10px;
	line-height: 20px;	
}
.hr_title{
	margin-bottom:5px;
}
.btn{
	margin-left:300px;
	margin-top:10px;
}
 body{ font-size:12px;font-family:"微软雅黑";} 
</style>
</head>
<body>
<div class="small_contailer_box2" >
      <table>
        <tr>
            <td width="150px"></td>
        	<td width="200px"><a href="#" onclick="$(window.parent.window).get(0).addTab('渠道申请','canalInfoAction','canal')" ><span class="STYLE3"><img src="images/apply.png"/></span></a></td>
        	<td width="200px"><a href="#" onclick="$(window.parent.window).get(0).addTab('渠道申请（变更）','canalApplyModify','sup')" ><span class="STYLE3"><img src="images/change.png"/></span></a></td>
        	<td width="200px"><a href="#" onclick="$(window.parent.window).get(0).addTab('渠道申请（关闭）','canalApplyClose','orderApprove')" ><span class="STYLE3"><img src="images/close.png"/></span></a></td>
        </tr>
         <tr>
            <td width="180px"></td>
        	<td width="100px" style="padding-left:10px;padding-top:14px;font-size:18px;font-weight:bold;">渠道申请</td>
        	<td width="100px" style="padding-left:10px;padding-top:14px;font-size:18px;font-weight:bold;">渠道变更</td>
        	<td width="100px" style="padding-left:10px;padding-top:14px;font-size:18px;font-weight:bold;">渠道关闭</td>
        </tr>
      </table>	

</div>

<div class="small_contailer_box1">
	<div class="hr_title" style="background-color: #E0ECFF"><img src="images/util/arrow6.gif" hspace="5" />新增流程待处理工单
	<!-- <img alt="刷新" style="margin-left:260px" src="images/refresh.jpg"></img>
		<a href="#"  style="text-decoration: none; color:#556B2F;font-size: 12px" onclick="javascript:window.location.href='sendMailAction'">刷新</a> --></div>
    <div id="maingrid4" style="margin:3px; padding:0"></div>
    <!-- <div class="btn"><input type="image" src="images/btn.gif"  onclick="$(window.parent.window).get(0).addTab('渠道申请','canalInfoAction','canal')"/> </div> -->
</div>

<div class="small_contailer_box1">
	<div class="hr_title" style="background-color: #E0ECFF"><img src="images/util/arrow6.gif" hspace="5" />变更流程待修改工单</div>
 	<div id="maingrid5" style="margin:3px; padding:0"></div>
 	<!-- <div class="btn"><input type="image" src="images/btn.gif"  onclick="$(window.parent.window).get(0).addTab('渠道申请','canalApplyModify','canal')"/> </div> -->
</div>

</body>
</html>
