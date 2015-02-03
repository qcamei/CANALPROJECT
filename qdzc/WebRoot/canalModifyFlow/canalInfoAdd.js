$(function(){
	//显示日期
	$('#rentStartDate').ligerDateEditor();
	$('#rentStartDate').ligerDateEditor();
	$('#rentEndDate').ligerDateEditor();
	$('#guaranteeTime').ligerDateEditor();
	$("#registerDate").ligerDateEditor();
	
	$("#userOutDate").ligerDateEditor();
	$("#userInDate").ligerDateEditor();
	$("#userBirthday").ligerDateEditor();
	
	//默认显示或者隐藏的文本框
	$(".yiyou").hide();
	$(".isMoney").html("*");
	//渠道信息的下拉列表
	$("#branchCompany").ligerComboBox({url :'areaQueryAction', isMultiSelect: false});	
	$("#areaName").ligerComboBox({url :'areaQueryAction', isMultiSelect: false,valueField: 'areaId'});	
	$("#areaName1").ligerComboBox({url :'areaQueryAction', isMultiSelect: false,valueField: 'areaId'});	//代理商中的区县信息
	$("#regionCharacter").ligerComboBox({url :'selectItemQueryAction?type=canal_region_character', isMultiSelect: false});
	$("#canalState").ligerComboBox({url :'selectItemQueryAction?type=canal_state', isMultiSelect: false});
	$("#canalForm").ligerComboBox({
		url :'selectItemQueryAction?type=canal_form', 
		isMultiSelect: false/*,
		onSelected: function (result){	
			if(result == 60){//表示此时选中的是城市
				$(".kf").html("&nbsp;");
				$(".agentLevel").html("&nbsp;");//针对开放性渠道，【代理商级别】字段不是必选。
				//$("#add").css("display","block");
				$("#add").show();//针对开放性渠道。界面加上是否开通CRM字段，如果是其他的，默认为接入
			}else{
				$(".kf").html("*");
				$(".agentLevel").html("*");
				$("#isCrm").attr("disabled","true");
			}
       }*/
    });
	$("#isLine").ligerComboBox({   //是否开通专线
		url :'selectItemQueryAction?type=is_line', 
		isMultiSelect: false
    });
	$("#canalProperty").ligerComboBox();
	$("#canalType").ligerComboBox();
	$("#distributeCard").ligerComboBox({url :'selectItemQueryAction?type=is_true', isMultiSelect: false});
	$("#urbanIdetity").ligerComboBox({url :'selectItemQueryAction?type=canal_urban_idetity', isMultiSelect: false});
	$("#legalCardType").ligerComboBox({url :'selectItemQueryAction?type=legal_card_type', isMultiSelect: false});
	$("#agentPointType").ligerComboBox({url :'selectItemQueryAction?type=agent_point_type', isMultiSelect: false});
	$("#sellPointType").ligerComboBox({url :'selectItemQueryAction?type=sell_point_type', isMultiSelect: false});
	$("#manageWay").ligerComboBox({url :'selectItemQueryAction?type=manage_way', isMultiSelect: false});
	$("#openSellYetai").ligerComboBox({url :'selectItemQueryAction?type=open_sell_yetai', isMultiSelect: false});
	$("#liansuoCatorySign").ligerComboBox({url :'selectItemQueryAction?type=liansuo_catory_sign', isMultiSelect: false});
	$("#topZiyou").ligerComboBox({url :'selectItemQueryAction?type=top_ziyou', isMultiSelect: false});
	$("#majorMendianType").ligerComboBox({url :'selectItemQueryAction?type=major_mendian_type', isMultiSelect: false});
	$("#grantMendianClass").ligerComboBox({url :'selectItemQueryAction?type=grant_mendian_class', isMultiSelect: false});
	$("#bianlidianTask").ligerComboBox({url :'selectItemQueryAction?type=bianlidian_task', isMultiSelect: false});
	$("#sellPointTask").ligerComboBox({url :'selectItemQueryAction?type=sell_point_task', isMultiSelect: false});
	$("#sellPointSellType").ligerComboBox({url :'selectItemQueryAction?type=sell_point_sell_type', isMultiSelect: false});
	$("#kechangEntity").ligerComboBox({url :'selectItemQueryAction?type=kechang_entity', isMultiSelect: false});
	$("#factoryCanalSign").ligerComboBox({url :'selectItemQueryAction?type=factory_canal_sign', isMultiSelect: false});
	/*$("#isCrm").ligerComboBox({
		url :'selectItemQueryAction?type=is_crm', 
		isMultiSelect: false,
		onSelected:function(result){
			if(result == '184'){ //表示CRM帐号接入
				$(".bank").html("*");//开户银行和开户银行帐号属性前加*
				$("#startBank").attr("validate","required:true");//开户银行必填
				$("#bankAccountId").attr("validate","required:true");//开户银行账号必填
			}else if(result == '185'){//表示CRM帐号未接入
				$(".bank").html("&nbsp;"); //开户银行和开户银行帐号属性前加空格
				$("#startBank").removeAttr("validate");//开户银行不必填
				$("#bankAccountId").removeAttr("validate");//开户银行账号不必填
			}
		}
	});*/
	$("#isMoney").ligerComboBox({
		url :'selectItemQueryAction?type=is_true',
		isMultiSelect: false,
		onSelected:function(result){
			if(result == '68'){ //表示此时选中的是缴纳保证金
				$(".isMoney").html("*"); //与保证金相应的信息前面加*
				$("#taskType").attr("validate","required:true");//业务类型必填
				$("#guaranteeAmount").attr("validate","required:true");//保证金金额必填
				$("#guaranteeUser").attr("validate","required:true");//保证金办理人必填
				$("#guaranteeTime").attr("validate","required:true");//保证金时间
			}else if(result == '69'){
				$(".isMoney").html("&nbsp;");//与保证金相应的信息前面加空格
				$("#taskType").removeAttr("validate");//业务类型不必填
				$("#guaranteeAmount").removeAttr("validate");//保证金金额不必填
				$("#guaranteeUser").removeAttr("validate");//保证金办理人不必填
				$("#guaranteeTime").removeAttr("validate");//保证金时间不必填
			}
		}
	});
	$("#taskType").ligerComboBox({
		url :'guaranteeMoneyQueryAction', 
		isMultiSelect: false,
		onSelected:function(result){
			if(result == '1'){
				$("#guaranteeAmount").val("1000");
			}else if(result == '2'){
				$("#guaranteeAmount").val("10000");
			}else if(result == '3'){
				$("#guaranteeAmount").val("5000");
			}else if(result == '4'){
				$("#guaranteeAmount").val("0");
			}else if(result == '5'){
				$("#guaranteeAmount").val("2000");
			}else if(result == '6'){
				$("#guaranteeAmount").val("10000");
			}else if(result == '7'){
				$("#guaranteeAmount").val("5000");
			}
		}
	});
	$("#isDragonSystem").ligerComboBox({url :'selectItemQueryAction?type=is_dragon_system', isMultiSelect: false});
	
	//代理商信息的下拉列表
	 $("#agentDept").ligerComboBox({url :'selectItemQueryAction?type=agent_dept',isMultiSelect: false});
	 $("#canalDept").ligerComboBox({url :'selectItemQueryAction?type=canal_dept',isMultiSelect: false});
	 $("#agentLevel").ligerComboBox();
	 $("#companyType").ligerComboBox({url :'selectItemQueryAction?type=company_type',isMultiSelect: false});
	 $("#cooperationType").ligerComboBox({url :'selectItemQueryAction?type=cooperation_type',isMultiSelect: false});
	 $("#manageModel").ligerComboBox({url :'selectItemQueryAction?type=manage_model',isMultiSelect: false});
	 $("#payWay").ligerComboBox({url :'selectItemQueryAction?type=pay_way',isMultiSelect: false});
	 $("#areaName").ligerComboBox({url :'areaQueryAction', isMultiSelect: false});	
	 //代理商员工信息
	 $("#userKind").ligerComboBox({url :'selectItemQueryAction?type=user_kind',isMultiSelect: false});
	 $("#userState").ligerComboBox({url :'selectItemQueryAction?type=user_state',isMultiSelect: false}); 
	 $("#userAuthority").ligerComboBox({url :'selectItemQueryAction?type=user_authority',isMultiSelect: false}); 
	 $("#userWay").ligerComboBox({url :'selectItemQueryAction?type=user_way',isMultiSelect: false});
	 $("#userRegionName").ligerComboBox({url :'areaQueryAction', isMultiSelect: false});	
	 
	//归属部门模糊查询
	 $.ajax({
			      type : "post",
			      url : "deptQueryActionRough",
			      data:  null,
			      dataType: "json",
			      async : false,
			      success : function(data){	
			        names = data;
			      }
	    });
		$("#userDept").autocomplete({
			source: names,
		}); 
		
	//归属客户经理模糊查询
	 $.ajax({
			      type : "post",
			      url : "userQueryActionRough",
			      data:  null,
			      dataType: "json",
			      async : false,
			      success : function(data){	
			        names = data;
			      }
	    });
		$("#canalUserName").autocomplete({
			source: names
		});
		//归属代理商模糊查询
		 $.ajax({
				      type : "post",
				      url : "agentNameQueryActionRough",
				      data:  null,
				      dataType: "json",
				      async : false,
				      success : function(data){	
				        names = data;
				      }
		    });
			$("#agentName1").autocomplete({
				source: names,
				select : function(e, ui) {	
					$("#agentId1").val(ui.item.id);
				}
			});
		
	
});

function alamTxt(id,txtContext){
		$("#"+id).ligerTip({ content: txtContext, width: 150 });
}

function removeAlamTxt(id){
		$("#"+id).ligerHideTip();
}



function ajaxUpdate() {
	var isMoney = $("#isMoney").val();
	if(isMoney == "是"){
		var image = $("#guaranteeReceiptScanUrl").val();
		if(image==""){
			$.ligerDialog.error('请上传扫描件！');
			return;
		}
	}
	
		$.blockUI({
			message : '<h5><img src="images/loading.gif" /> 系统正在提交中……</h5>'
		});
		var params = $('#form1').serialize().replace(/\+/g, ' ').replace(/\%/g, '%25'); //这里直接就序列化了表单里面的值；很方便  
		params = decodeURIComponent(params, true);
		params = encodeURIComponent(encodeURIComponent(params)).replace(/%253D/g, '=').replace(/%2526/g, '&');
		$.ajax({
			url : 'canalInfoAddAction', //后台处理程序   
			type : 'post', //数据发送方式   
			dataType : 'text', //接受数据格式   
			data : params, //要传递的数据；就是上面序列化的值   
			success : submit_Result  //回传函数(这里是函数名)    
		});
}
function submit_Result(result) { //回传函数实体，参数为XMLhttpRequest.responseText   
	$.unblockUI();			
	var temp = result.split(";");
	if (temp[0] == 'success') {
		 window.parent.$("#maingrid4").ligerGrid().loadData(true);		
		  	$.ligerDialog.success('提交成功，请关闭对话框！','提示',function(){		  		
   			 var parentWindow_body =  $(window.parent.document).find("body");if(! parentWindow_body.find(".dealWithMask").length){parentWindow_body.append("<input type=text class=dealWithMask style=height:0;border:none/>");}parentWindow_body.find(".dealWithMask").focus(); window.parent.$.ligerDialog.close();
	      });	
	} else if (temp[0] == 'infoLoss') {
		alert('信息填写不完整！');
	} else if (temp[0] == 'error') {
		alert('系统错误，请联系管理员！');
	} else if (temp[0] == 'duplicate') {
		alert('重复提交');
	} else {
		alert(temp[0]);
	}
}

	//代理商信息的确认
	function checklegal(id){    //验证账号、密码是否合
	  	//验证电话号码手机号码，包含至今所有号段 
	  var ab=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
	  var ondutyPersonPhone = $("#ondutyPersonPhone").val();
	  var legalPhone = $("#legalPhone").val();
	  var canalUserPhone = $("#canalUserPhone").val();
	  if(ondutyPersonPhone != null && ondutyPersonPhone!=""){
		  if(ab.test(ondutyPersonPhone) == false){
		    	$.ligerDialog.error("请正确填写手机号码,例如 13812345678，或者固定电话02312345678!");
		    	$("#ondutyPersonPhone").val("");
		    	return false;
		  }
	  }
	  if(legalPhone != null && legalPhone!=""){
			if (ab.test(legalPhone) == false) {
				$.ligerDialog.error("请正确填写手机号码,例如 13812345678，或者固定电话02312345678!");
				$("#legalPhone").val("");
				return false;
			}
	  }
	  if(canalUserPhone != null && canalUserPhone !=""){
			if (ab.test(canalUserPhone) == false) {
				$.ligerDialog.error("请正确填写手机号码,例如 13812345678，或者固定电话02312345678!");
				$("#canalUserPhone").val("");
				return false;
			}
	  }
	  $("#"+id).ligerHideTip();
  }

	//根据选中的状态判断是否显示代理商信息
	function setEnable(){
		if (document.getElementById("state1").checked){
				$(".add").hide();
				$(".yiyou").show();
				$("#agentName1").attr("validate","required:true");
				$("#branchCompany").removeAttr("validate");
				$("#agentName").removeAttr("validate");
				$("#agentName").removeAttr("validate");
				$("#agentShortName").removeAttr("validate");
				$("#agentDept").removeAttr("validate");
				$("#companyType").removeAttr("validate");
				$("#cooperationType").removeAttr("validate");
				$("#legalPerson").removeAttr("validate");
				$("#legalPhone").removeAttr("validate");
				$("#legalCardType").removeAttr("validate");
				$("#legalIdCard").removeAttr("validate");
				$("#ondutyPerson").removeAttr("validate");
				$("#ondutyPersonPhone").removeAttr("validate");
		}else if(document.getElementById("state2").checked){
			$(".add").show();
			$(".yiyou").hide();
			$("#agentName1").removeAttr("validate");
			$("#branchCompany").attr("validate", "{required:true}");
			$("#agentName").attr("validate", "{required:true}");
			$("#agentName").attr("validate", "{required:true}");
			$("#agentShortName").attr("validate", "{required:true}");
			$("#agentDept").attr("validate", "{required:true}");
			$("#companyType").attr("validate", "{required:true}");
			$("#cooperationType").attr("validate", "{required:true}");
			$("#legalPerson").attr("validate", "{required:true}");
			$("#legalPhone").attr("validate", "{required:true}");
			$("#legalCardType").attr("validate", "{required:true}");
			$("#legalIdCard").attr("validate", "{required:true}");
			$("#ondutyPerson").attr("validate", "{required:true}");
			$("#ondutyPersonPhone").attr("validate", "{required:true}");
		}
	}
	/***Ajax引擎******/
	function createXmlHttpRequest() {
		var xmlHttp;
		try { //Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try { //Internet Explorer
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		return xmlHttp;
	}
	//验证代理商编码是否重复
	function checkAgentCode(object) {
		var agentCode = object.value;
		var xmlHttp = createXmlHttpRequest();
		xmlHttp.onreadystatechange = function() {
			document.all.unc.innerHTML="";
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var data = xmlHttp.responseText;
					if (data == 1) {
						$.ligerDialog.warn("当前代理商编码【" + agentCode + "】已经存在！");
						object.value = "";
					}
				}
			}
		}
		//第三步：打开连接
		xmlHttp.open("POST", "testAgentIdOnlyOneAction?timeStamp="+new Date().getTime(), true);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//第四步：发送数据
		xmlHttp.send("agentCode=" +agentCode);
	}

	function getPinyin(){
		var userName = $('#userName').val();
		var pinyin = copy2Pinyin(userName);
		$("#userPinyin").val(pinyin);
	}
	//根据管理部门得代理商级别
	function getAgentLevel(){
		  var actionURL = encodeURI(encodeURI( "agentLevelQueryAction?dept="+$('#agentDept').val()));
		   $.ajax({
				type:"post",
				url:actionURL,
				dataType:"json",
				success:function(data){
					$("#agentLevel").ligerGetComboBoxManager().setData(data);
				}
			});
	}
	//根据区域特征得管理属性
	function getCanalType(){
		  var type=$("#canalForm").val();
		  var actionURL = encodeURI(encodeURI( "canalTypeQueryAction?type="+type));
		   $.ajax({
				type:"post",
				url:actionURL,
				dataType:"json",
				success:function(data){
					$("#canalType").ligerGetComboBoxManager().setData(data);	
				}
			});
	}
	//根据渠管理属性得分类名称
	function getCanalProperty(){
		  var type=$("#canalType").val();
		  if(type == "农村开放渠道" || type == "开放渠道"){//表示此时选中的是开放渠道
				$(".kf").html("&nbsp;");
				$(".agentLevel").html("&nbsp;");//针对开放性渠道，【代理商级别】字段不是必选。
				$("#add").show();//针对开放性渠道。界面加上是否开通CRM字段，如果是其他的，默认为接入
				$(".nima").html("&nbsp;");//针对开放性渠道，资金归结所有字段非必选。
				$("#hm").removeAttr("validate");
				$("#khyh").removeAttr("validate");
				$("#kh").removeAttr("validate");
				$("#sfzh").removeAttr("validate");
			}else{
				$(".kf").html("*");
				$(".agentLevel").html("*");
				$(".nima").html("*");
				$("#hm").attr("validate", "{required:true}");
				$("#khyh").attr("validate", "{required:true}");
				$("#kh").attr("validate", "{required:true}");
				$("#sfzh").attr("validate", "{required:true}");
			}
		  var actionURL = encodeURI(encodeURI( "canalPropertyQueryAction?type="+type));
		   $.ajax({
				type:"post",
				url:actionURL,
				dataType:"json",
				success:function(data){
					$("#canalProperty").ligerGetComboBoxManager().setData(data);
				}
			});
	}
	function f_closeAddWindow(item, dialog) {
		$.ligerDialog.confirm('确认要关闭窗口吗', function(yes) {
			if (yes)
				dialog.close();
		})
		manager.loadData(true);
	}