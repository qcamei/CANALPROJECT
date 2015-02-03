
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/pageADMOperAuth.tld" prefix="pageADMOperAuth.tld"%>
<html>
<head>
<title>渠道信息列表页面</title>
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
	var zddaynum = null;
	var countClass = "管理属性";
	var manager = null;
	$(function() {

		var v = $("form").validate({
			//调试状态，不会提交数据的
			debug : true,
			errorLabelContainer : "#errorLabelContainer",
			wrapper : "li",
			invalidHandler : function(form, validator) {
				$("#errorLabelContainer").show();
				setTimeout(function() {
					$.ligerDialog.tip({
						content : $("#errorLabelContainer").html()
					});
				}, 10);
			},
			submitHandler : function() {
				$("#errorLabelContainer").hide();
				alert("Submitted!");
			}
		});
		$(".l-button-test").click(function() {
			alert(v.element($("#txtName")));
		});
		f_initGrid();

		$("form").ligerForm();
		//get_curDay();
		//get_lDay();

	});

	/*function get_curDay() {
		var today = new Date();
		var day = today.getDate();
		var month = today.getMonth() + 1;
		var year = today.getFullYear();
		if (month * 1 < 10) {
			month = '0' + month;
		}
		if (day * 1 < 10) {
			day = '0' + day;
		}
		var date = year + "-" + month + "-" + day;
		document.getElementById('txtEndDate').value = date;
	}
	function get_lDay() { //获取本月1号
		var today = new Date();
		var month = today.getMonth() + 1;
		var year = today.getFullYear();
		if (month * 1 < 10) {
			month = '0' + month;
		}
		var date = year + "-" + month + "-" + "01";
		document.getElementById('txtBeginDate').value = date;
	}*/

	function f_initGrid() {

		grid = manager = $("#maingrid4").ligerGrid({

			columns : [ {
				display : "" + countClass,
				name : 'name',
				width : 200,
				minWidth : 100
			}, {
				display : '数量',
				name : 'sum',
				width : 200,
				minWidth : 100
			}

			],
			where : f_getWhere(),
			url : 'searchCanal?countClass=' + encodeURI(encodeURI(countClass)),
			dataAction : 'server',
			pageSize : 28,
			rownumbers : false,
			//checkbox:true,
			allowAdjustColWidth : true,
			columnWidth : 110,
			width : '100%',
			height : '98%',
			frozen : false,
			detail : {
				onShowDetail : f_showOrder,
				height : 280
			},
			allowUnSelectRow : true

		});

	}

	function f_showOrder(row, detailPanel, callback) {
		var actionUrl = "searchCanalUser?value=" + row.name + "&countClass="
				+ countClass + "&canalName=" + $("#canalName").val()
				+ "&guaranteeAmount=" + $("#guaranteeAmount").val()
				+ "&canalManager=" + $("#canalManager").val() + "&canalId="
				+ $("#canalId").val();

		actionUrl = encodeURI(encodeURI(actionUrl));
		$(".l-selected").removeClass("l-selected");
		var gridDetail = document.createElement('div');
		$(detailPanel).append(gridDetail);
		manager2 = $(gridDetail).css('margin', 10).ligerGrid({
			columns : [

			{
				display : '工单号',
				name : 'inId',
				width : 150,
				minWidth : 60
			}, {
				display : '渠道ID',
				name : 'canalId',
				width : 70,
				minWidth : 60,
				render:function (row){
					var html ='<a href="#" onclick="getDetail(\'' + row.canalId + '\')" style="text-decoration : none "> ' + row.canalId + '</a>  ';	
					return html;
				}
			}, {
				display : '渠道名称',
				name : 'canalName',
				width : 80,
				minWidth : 60
			}, {
				display : '区域',
				name : 'areaName',
				width : 80,
				minWidth : 60
			}, {
				display : '状态',
				name : 'canalState',
				width : 60,
				minWidth : 60
			}, {
				display : '形态',
				name : 'canalForm',
				width : 60,
				minWidth : 60
			}, {
				display : '性质',
				name : 'canalProperty',
				width : 60,
				minWidth : 60
			}, {
				display : '类型',
				name : 'canalType',
				width : 60,
				minWidth : 60
			}, {
				display : '归属代理商',
				name : 'agentName',
				width : 80,
				minWidth : 60
			}, {
				display : '城乡标识',
				name : 'urbanIdetity',
				width : 60,
				minWidth : 60
			}, {
				display : '负责人',
				name : 'canalUserName',
				width : 60,
				minWidth : 60
			}, {
				display : '联系电话',
				name : 'canalUserPhone',
				width : 60,
				minWidth : 60
			}, {
				display : '客户经理',
				name : 'canalManager',
				width : 60,
				minWidth : 60
			}, {
				display : 'VPDN',
				name : 'broadbandAccount',
				width : 80,
				minWidth : 60
			}, {
				display : '申请操作人',
				name : 'operUser',
				width : 60,
				minWidth : 60
			}, {
				display : '申请时间',
				name : 'operTime',
				width : 80,
				minWidth : 60
			}, {
				display : '申请人部门',
				name : 'deptName',
				width : 60,
				minWidth : 60
			} ],
			where : f_getWhere(),
			pageSize : 26,
			isScroll : false,
			showToggleColBtn : false,
			showTitle : false,
			url : actionUrl,
			onAfterShowData : callback,
			frozen : false,
			detail : {
				onShowDetail : f_showOrder1,
				height : 280
			},
			allowUnSelectRow : true
		});
		$(detailPanel).show();
	}

	function getDetail(canalId){
		var m = $.ligerDialog.open({ 
    	  	title: '工单详情',
    	 	height: 500, 
    	  	width: 800, 
    	  	url:'search/lifeStyle.jsp?canalId='+canalId,
    	  	showMax: true,
			isResize: true,
			slide: false
      }); 
    m.max();
	}
	function f_showOrder1(row, detailPanel, callback) {
		var actionUrl = "searchCanalUserList?canalId=" + row.canalId
				+ "&canalName=" + row.canalName;
		actionUrl = encodeURI(encodeURI(actionUrl));
		$(".l-selected").removeClass("l-selected");
		var gridDetail = document.createElement('div');
		$(detailPanel).append(gridDetail);
		manager2 = $(gridDetail).css('margin', 10).ligerGrid({
			columns : [
			//user_id,user_number,user_number,user_name,user_kind
			//,user_cellphone,user_state,user_agent,
			//user_phone,user_dept,user_sex

			{
				display : '营业员姓名',
				name : 'userName',
				width : 100,
				minWidth : 80
			}, {
				display : '性别',
				name : 'userSex',
				width : 100,
				minWidth : 60
			}, {
				display : '营业员编号',
				name : 'userNumber',
				width : 100,
				minWidth : 60
			}, {
				display : '营业员类型',
				name : 'userKind',
				width : 100,
				minWidth : 60
			}, {
				display : '联系电话',
				name : 'userPhone',
				width : 100,
				minWidth : 60
			}, {
				display : '所属部门',
				name : 'userDept',
				width : 100,
				minWidth : 60
			}, {
				display : '当前用工状态',
				name : 'userState',
				width : 100,
				minWidth : 60
			}, {
				display : '归属代理商',
				name : 'userAgent',
				width : 100,
				minWidth : 60
			} ],
			where : f_getWhere(),
			pageSize : 24,
			isScroll : false,
			showToggleColBtn : false,
			showTitle : false,
			url : actionUrl,
			onAfterShowData : callback,
			frozen : false,
			allowUnSelectRow : true
		});
		$(detailPanel).show();
	}

	function f_query() {
		if (!manager)
			return;
		var countClass = encodeURIComponent($("#countClass").val());
		var txtBeginDate = encodeURIComponent($("#txtBeginDate").val());
		var txtEndDate = encodeURIComponent($("#txtEndDate").val());
		var canalManager = encodeURIComponent($("#canalManager").val());
		var canalId = encodeURIComponent($("#canalId").val());
		var canalName = encodeURIComponent($("#canalName").val());
		var guaranteeAmount = encodeURIComponent($("#guaranteeAmount").val());
		manager.setOptions({
			parms : [ {
				name : 'txtBeginDate',
				value : txtBeginDate
			}, {
				name : 'txtEndDate',
				value : txtEndDate
			}, {
				name : 'canalManager',
				value : canalManager
			}, {
				name : 'canalId',
				value : canalId
			}, {
				name : 'canalName',
				value : canalName
			}, {
				name : 'countClass',
				value : countClass
			}, {
				name : 'guaranteeAmount',
				value : guaranteeAmount
			} ]
		});

		manager.loadData(true);
	}
	function f_getWhere() {
		if (!grid)
			return null;
		var clause = function(rowdata, rowindex) {
			var key = $("#txtKey").val();
			return rowdata.orderId.indexOf(key) > -1;
		};
		return clause;
	}
	function f_closeAddWindow(item, dialog) {
		$.ligerDialog.confirm('确认要关闭窗口吗', function(yes) {
			if (yes)
				dialog.close();
		});
	}

	function changCountClass() {
		countClass = $("#countClass").val();
		f_initGrid();
	}
</script>






<style type="text/css">
body {
	font-size: 12px;
}

.blackbold_b { /* 标题样式 */
	line-height: 22px;
	width: 150px;
	height: 22px;
	font-size: 12px;
	font-weight: bold;
	color: #FF5317
}

.l-table-edit-td {
	padding: 4px;
	font-size: 12px;
}
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

	<fieldset style="top:inherit;width:100%">
		<form name="form1" method="post" action="" id="form1">
			<table cellpadding="0" cellspacing="0" class="l-table-edit">


				<tr height="45px">
					<td align=right class=l-table-edit-td>统计维度:</td>

					<td align=left class=l-table-edit-td><select name="countClass" id="countClass" onchange="changCountClass()">
							<option value="管理属性" selected="selected">管理属性</option>
							<option value="所属地区">所属地区</option>
					</select>
					</td>

					<td align=right class=l-table-edit-td>渠道编码:</td>
					<td align=left class=l-table-edit-td><input name="canalId" type=text id="canalId" />
					</td>
					<td align=right class=l-table-edit-td>渠道名称:</td>
					<td align=left class=l-table-edit-td><input name="canalName" type=text id="canalName" />
					</td>
					<td align="right" class="l-table-edit-td">客户经理:</td>
					<td align=left class=l-table-edit-td><input name="canalManager" type=text id="canalManager" />
					</td>

				</tr>
				<tr>
					<td align=right class=l-table-edit-td>保证金:</td>
					<td align=left class=l-table-edit-td><input name="guaranteeAmount" type=text id="guaranteeAmount" />
					</td>

					<td align="right" class="l-table-edit-td">开始时间:</td>
					<td align="left" class="l-table-edit-td"><input name="txtBeginDate" type="text" id="txtBeginDate" ltype="date" /></td>
					<td align="right" class="l-table-edit-td">结束时间:</td>
					<td align="left" class="l-table-edit-td"><input name="txtEndDate" type="text" id="txtEndDate" ltype="date" /></td>
					<td align="right" class="l-table-edit-td"><input type="button" value="查询" id="Button1" class="l-button" style="width:80px" onclick="f_query()" /></td>


				</tr>
			</table>

		</form>
	</fieldset>


	<table width="100%" id="result">
		<tr>
			<td height="1" bgcolor="#BBD2E9"></td>
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