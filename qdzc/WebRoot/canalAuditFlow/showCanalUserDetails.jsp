<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String canalId = request.getParameter("canalId");
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
            })
         }
        
         function f_initGrid()
        {
        	 var  canalId = "<%=canalId%>";
      		grid = manager = $("#maingrid4").ligerGrid({
                columns: [
                { display: '工单号', name: 'inId',  width: 100, minWidth: 60 },
                { display: '渠道工单号', name: 'canalInId', width: 100, minWidth: 60},
                { display: '营业员姓名', name: 'userName', width: 100, minWidth: 60},
                { display: '营业员类别', name: 'userKind', width: 100, minWidth: 60 },                
                { display: '绑定手机号码', name: 'userCellphone', width: 100, minWidth: 60},
                { display: '工号状态', name: 'userState', width: 100, minWidth: 60 },
                
                { display: '归属渠道ID', name: 'canalId', width: 80, minWidth: 60 },
                { display: '归属渠道名称', name: 'canalName', width: 150, minWidth: 60 },
                { display: '归属代理商', name: 'userAgent',width: 100, minWidth: 60 },
                { display: '工号权限', name: 'userAuthority',  width: 80, minWidth: 60 },
                { display: '身份证号', name: 'userIdCard', width: 130, minWidth: 60},
                { display: '姓名拼音', name: 'userPinyin', width: 70, minWidth: 60 },                
                { display: '所在部门', name: 'userDept', width: 100, minWidth: 60},
                { display: '邮箱', name: 'userEmail', width: 100, minWidth: 60 },
                { display: '计费角色', name: 'userRole', width: 80, minWidth: 60 },
                
                { display: '计费工号', name: 'userCountId', width: 80, minWidth: 60 },
                { display: '性别', name: 'userSex',width: 50, minWidth: 60 },
                { display: '生日', name: 'userBirthday',  width: 150, minWidth: 60 },
                { display: '籍贯', name: 'userNativeplace', width: 120, minWidth: 60},
                
                { display: '联系电话', name: 'userPhone', width: 100, minWidth: 60 },                
                { display: '文化程度', name: 'userEducation', width: 100, minWidth: 60},
                { display: '技能证书', name: 'userCertification', width: 100, minWidth: 60 },
                { display: '入职时间', name: 'userInDate', width: 150, minWidth: 60 },
                { display: '离职时间', name: 'userOutDate', width: 150, minWidth: 60 },
                { display: '住址', name: 'userAddress',width: 150, minWidth: 60 },
                { display: '岗位', name: 'userPosition',  width: 100, minWidth: 60 },
                { display: '用工方式', name: 'userWay', width:80, minWidth: 80},
                { display: '地区名称', name: 'userRegionName', width: 150, minWidth: 60 },              
              
                { display: '操作人', name: 'operUser', width: 80, minWidth: 60 },
                //{ display: '操作部门ID', name: 'deptId', width: 80, minWidth: 60 },
                { display: '操作部门名称', name: 'deptName',width: 100, minWidth: 60 },
                { display: '操作时间', name: 'operTime',  width: 150, minWidth: 60 },
                { display: '备注', name: 'remark', width: 150, minWidth: 60}
                ], 
                dataAction: 'server',
                pageSize:30,
                where : f_getWhere(),
                rownumbers:false, 
                checkbox:false,
	            url: 'canalUserDetailAction?canalId='+canalId,
	            allowAdjustColWidth:true,
	            width: '100%', height: '98%'
	           
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
