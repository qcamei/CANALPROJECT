<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>工单视图页面</title>
    <link href="com_css/LigerUILib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
 
    <link href="com_css/LigerUILib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />    
    <script src="com_css/LigerUILib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/core/base.js" type="text/javascript"></script>    
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>       
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>          
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>        
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="com_css/LigerUILib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="com_css/LigerUILib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>  
     <link href="com_css/jqueryUI/themes/base/jquery.ui.all.css" rel="stylesheet">
	<script src="com_css/jqueryUI/ui/jquery.ui.core.js"></script>
	<script src="com_css/jqueryUI/ui/jquery.ui.widget.js"></script>
	<script src="com_css/jqueryUI/ui/jquery.ui.position.js"></script>
	<script src="com_css/jqueryUI/ui/jquery.ui.autocomplete.js"></script>
    <script type="text/javascript">
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
       
    $("form").ligerForm();
    
});

      
        function f_query()
        {      	        
	       	var inId = encodeURI($("#inId").val());
	       	var canalId = encodeURI($("#canalId").val());
	       	var aDiv=$("#process div");
	       	var len=aDiv.length;
	        for(var i=0;i<len;i++){
	        	$("#div"+i).attr("src", "images/process/"+i+".jpg");
	        }
	        $("#div12").attr("src", "images/process/12.jpg");
	    	if (inId=="" && canalId=="") { 
	    		$.ligerDialog.error('请输入工单号或者渠道编码！');
	    		return ; 
	    	}
	        $.ajax({   
			       url :'graphicQueryAction?inId='+inId+'&canalId='+canalId,  //后台处理程序   
			       type:'post',    //数据发送方式   
			       dataType:'text',   //接受数据格式   
			       data:null,   //要传递的数据；就是上面序列化的值   
			       success:submit_Result //回传函数(这里是函数名)    
			});    
        }
        function submit_Result(result){ //回传函数实体，参数为XMLhttpRequest.responseText   
 
        	if(result != 0){   
        		for(var i=1;i<result;i++){
        			$("#div"+i).attr("src", "images/process/"+i+i+".jpg");
        		 }
        		if(result>3){
        			$("#div12").attr("src", "images/process/1212.jpg");
        		}
             }else{ 
            	  $.ligerDialog.error('您所查询的单号不存在，请核实后再查!'); 
             }   
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

#process{background: url(./images/process/process.png) no-repeat;width:1259px;height: 304px;position: relative;font-size:10px;font-family: "宋体"}
#process div{position: absolute;width:88px;height: 46px;text-align: center;padding-top:0px;}
#process .active{background:#2C868E;color:#FFF;}
#div11{top:11px;left:11px;}
#div22{left:11px;top:110px;}
#div33{left:121px;top:110px;}

#div122{left:490px;top:164px;}

#div44{left:261px;top:49px;}
#div55{left:381px;top:49px;}
#div66{left:503px;top:48px;}
#div77{left:626px;top:48px;}

#div88{left:746px;top:48px;}
#div99{right:294px;top:105px;}
#div100{right:174px;top:103px;}
#div111{right:174px;top:20px;}

 </style>
</head>
<body style="padding:6px; overflow:hidden;background:#f9fbfc">
	<fieldset style="top:inherit;width:100%; margin:10px;">
		<form name="form1" method="post" action="" id="form1">
			<table cellpadding="0" cellspacing="0" class="l-table-edit">			
				<tr>
					<td align="right" class="l-table-edit-td">工单号:</td>
					<td align="left" class="l-table-edit-td">
						<input name="inId" type="text" id="inId"  ltype="text"/>
					</td>
					<td align="right" class="l-table-edit-td">渠道编码:</td>
					<td align="left" class="l-table-edit-td">
						<input name="canalId" type="text" id="canalId"  ltype="text"/>
					</td>
					<td align="right" class="l-table-edit-td">
						<input type="button" value="查询" id="Button1" class="l-button" style="width:80px" onclick="f_query()"/>
					</td>
						<td align="left" class="l-table-edit-td"></td>					
				</tr>
			</table>
		</form>
	</fieldset>	
	<hr color="#2C868E">
	<div id="process">
	<div id="div11"><input type="image" src="images/process/1.jpg" id="div1" /></div>
	<div id="div22"><input type="image" src="images/process/2.jpg" id="div2" /></div>
	<div id="div33"><input type="image" src="images/process/3.jpg" id="div3" /></div>

	<div id="div44"><input type="image" src="images/process/4.jpg" id="div4" /></div>
	<div id="div55"><input type="image" src="images/process/5.jpg" id="div5" /></div>
	<div id="div66"><input type="image" src="images/process/6.jpg" id="div6" /></div>
	<div id="div77"><input type="image" src="images/process/7.jpg" id="div7" /></div>

	<div id="div88"><input type="image" src="images/process/8.jpg" id="div8" /></div>
	<div id="div99"><input type="image" src="images/process/9.jpg" id="div9" /></div>
	<div id="div100"><input type="image" src="images/process/10.jpg" id="div10" /></div>
	<div id="div111"><input type="image" src="images/process/111.jpg" id="div11" /></div>
	
	<div id="div122"><input type="image" src="images/process/12.jpg" id="div12" /></div>
</div>
</body>
</html>
