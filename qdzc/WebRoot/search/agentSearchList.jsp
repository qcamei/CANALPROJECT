
 <%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld" %>
<html>
<head>
<title>代理商信息列表页面</title>
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
	            $("form").ligerForm();
	        	//get_curDay();
	            //get_lDay();
	            
	        }); 
        

        
         function f_initGrid()
         {
        	    grid = manager = $("#maingrid4").ligerGrid({
             columns: [ 
                     { display: '代理商名称', name: 'agentName',width: 120, minWidth: 100 },
                     { display: '代理商编码', name: 'agentId',width: 120, minWidth: 100 },
                     { display: '代理商负责人', name: 'legalPerson',width: 120, minWidth: 100 },
                     { display: '代理商状态', name: 'agentState',width: 120, minWidth: 100}, 
                     { display: '所属辖区', name: 'areaName',width: 120, minWidth: 100},
                     { display: '所属分公司', name: 'branchCompany',width: 120, minWidth: 100}, 
                     { display: '联系电话', name: 'legalPhone',width: 120, minWidth: 100},
                     { display: '所属部门', name: 'agentDept',width: 120, minWidth: 100}, 
                     { display: '等级', name: 'agentLevel',width: 120, minWidth: 100,hide:1},
                     { display: '公司类型', name: 'companyType',width: 120, minWidth: 100},
                     { display: '注册时间', name: 'registerDate',width: 120, minWidth: 100,hide:1}
                      ],
                   		where : f_getWhere(),
                   		url: 'searchAgent',
                      	dataAction: 'server',
                         pageSize:25,
                         rownumbers:false,
                         //checkbox:true,
                        allowAdjustColWidth:true,
                        columnWidth:110,
                        width: '100%', 
                        height: '98%',
                        frozen:false,
                        detail: { 
                     	onShowDetail: f_showOrder ,
                     	height:320
                      },
                     allowUnSelectRow:true
                   
             });
  
         }
           
         function f_showOrder(row, detailPanel,callback)
         {
    	  
    	   
        	 var actionUrl = "searchAgentCanal?agentId="+row.agentId;
             actionUrl = encodeURI(encodeURI(actionUrl));
		     $(".l-selected").removeClass("l-selected");
            var gridDetail = document.createElement('div'); 
            $(detailPanel).append(gridDetail);
            manager2 = $(gridDetail).css('margin',10).ligerGrid({
                 columns:
                        [ 
                         { display: '渠道名称', name: 'canalName',width: 100, minWidth: 60 },
                         { display: '渠道编码', name: 'canalId',width: 80, minWidth: 60 },
                         { display: '区域', name: 'areaName', width: 80, minWidth: 60 },
                         { display: '状态', name: 'canalState', width: 50, minWidth: 60 },
                         { display: '形态', name: 'canalForm', width: 50, minWidth: 60 },
                         { display: '性质', name: 'canalProperty', width: 50, minWidth: 60 },
                         { display: '类型', name: 'canalType', width: 50, minWidth: 60 },
                         {display : 'VPDN',name : 'broadbandAccount',width : 80,minWidth : 60},
                         { display: '联系电话', name: 'canalUserPhone', width: 50, minWidth: 60 },
                         { display: '上级渠道', name: 'canalParentName', width: 80, minWidth: 60 }
                          ], 
                          where : f_getWhere(),
                          pageSize:15,
                          isScroll: false, 
                          showToggleColBtn: false,
                                              showTitle: false,
                                              url:actionUrl,
                                              onAfterShowData: callback,
                                              frozen:false,
                                              detail: {
                                              onShowDetail: f_showOrder2,
                                              height:370 
                                              },
                                              allowUnSelectRow:true
                                     });  
                                     $(detailPanel).show();   
                                 }  
       function f_showOrder2(row, detailPanel,callback)
         {
    	    var actionUrl = "searchAgentCanalUser?canalId="+row.canalId+"&canalName="+row.canalName;
    	    actionUrl = encodeURI(encodeURI(actionUrl));
    	    var gridDetail = document.createElement('div'); 
            $(detailPanel).append(gridDetail);
            
            $(gridDetail).css('margin',10).ligerGrid({
            columns:
                [  
            {display:'员工编号',name:'userId'},
            {display:'员工姓名',name:'userName'},
            {display:'员工性别',name:'userSex'},
            {display:'员工联系电话',name:'userPhone'}
                         ], 
                         pageSize:8,
                         where : f_getWhere(),
                         isScroll: false, 
                         showToggleColBtn: false,
                         showTitle: false,  
                         width: '100%',
                         url:actionUrl,
                         columnWidth : 115,
                         width : 1050,
                         onAfterShowData: callback,
                         frozen:false,
                         allowUnSelectRow:true,
          });  
          $(detailPanel).show();    
        } 
        function f_getWhere(){
            if (!grid) return null;
            var clause = function (rowdata, rowindex){
                var key = $("#txtKey").val();
                return rowdata.orderId.indexOf(key) > -1;
            };
            return clause; 
        }
        
  			function f_query(){
	        	 if (!manager) return;	        	
	        	 var  txtBeginDate = encodeURIComponent($("#txtBeginDate").val());
	              var txtEndDate = encodeURIComponent($("#txtEndDate").val());
		          var agentName = encodeURIComponent($("#agentName").val());
		          var agentId = encodeURIComponent($("#agentId").val());
		          var legalPerson = encodeURIComponent($("#legalPerson").val());
		          var branchCompany = encodeURIComponent($("#branchCompany").val());
	             manager.setOptions(
	                   { parms: [{name:'txtBeginDate',value:txtBeginDate},
	               				{name:'txtEndDate',value:txtEndDate},
	               				{name:'branchCompany',value:branchCompany},
					 			{name:'agentName',value:agentName},
								{name:'agentId',value:agentId},
								{name:'legalPerson',value:legalPerson}
	                ]}
	            );
	            manager.loadData(true);
	         }
  			 
  	        function f_closeAddWindow(item, dialog){
  	           	$.ligerDialog.confirm('确认要关闭窗口吗', function (yes){
  	                if(yes)
  	                         dialog.close();
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

<body style="padding:6px;background:#f9fbfc">
	<table width="100%">
		<tr>
			<td height="2"></td>
		</tr>
		<tr>
      <td class="blackbold_b"><img src="images/util/arrow6.gif" hspace="5">筛选条件</td>
		</tr>
		<tr>
			<td height="1" bgcolor="#BBD2E9"></td>
		</tr>
	</table>
      
      <fieldset style=top:inheritwidth:100%>
      <form name=form1 method=post action= id=form1>
      
      <table cellpadding=0 cellspacing=0 class=l-table-edit>
      <tr height=45px>
       <td align=right class=l-table-edit-td>
      代理商名称:
      </td>
      <td align=left class=l-table-edit-td>
      <input name=agentName type=text id=agentName  />
      </td>
       <td align=right class=l-table-edit-td>
      代理商编码:
      </td>
      <td align=left class=l-table-edit-td>
      <input name=agentId type=text id=agentId  />
      </td>
         <td align=right class=l-table-edit-td>
          代理商负责人:
      </td>
      <td align=left class=l-table-edit-td>
      <input name=legalPerson type=text id=legalPerson  />
      </td>
      <td align=right class=l-table-edit-td>
      所属分公司:
      </td>
      <td align=left class=l-table-edit-td>
      <input name=branchCompany type=text id=branchCompany  />
      </td>
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
			<td class="blackbold_b"><img src="images/util/arrow6.gif" hspace="5">代理商列表</td>
		</tr>
	</table>
<div id="maingrid4" style="margin:0; padding:0"></div>
</body>
      </html>