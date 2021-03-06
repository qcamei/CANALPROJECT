var paramDom = new ActiveXObject("Msxml2.DOMDocument.3.0");
paramDom.setProperty("SelectionLanguage", "XPath");
var paramRoot = paramDom.createElement("root");
paramDom.appendChild(paramRoot);

var tree;

function createTree() {
	tree = new WebFXTree('菜单权限', '', '', '');
    tree.setBehavior('classic');
    url = "#";
	actionurl = "permissionCreateTreeAction";
	doc = sendXml(actionurl);
	root = doc.selectSingleNode("root");
	if(root == null) {
		document.all("treeDiv").innerHTML = tree;
    	tree.expandAll();
		return;
	}
	var exMsg = root.selectSingleNode("exMsg");
  	if(exMsg != null) {
    	alert(exMsg.text);
    	return;
  	}
	
	firsts = root.selectNodes("first");
	//取得一级节点
	for(i=0; i<firsts.length; i++) {
		name = firsts[i].selectSingleNode("name").text;
		id = firsts[i].selectSingleNode("id").text;
		title = firsts[i].selectSingleNode("name").text;

		firstNode = new WebFXTreeItem(title, url, id, '1', '', '');
		tree.add(firstNode);
		
		seconds = firsts[i].selectNodes("second");
		for(ii=0; ii<seconds.length; ii++) {
			name = seconds[ii].selectSingleNode("name").text;
			id = seconds[ii].selectSingleNode("id").text;
			title = seconds[ii].selectSingleNode("name").text;
			secondNode = new WebFXTreeItem(title, url, id, '2', '', '');
			firstNode.add(secondNode);
			
			thirds = seconds[ii].selectNodes("third");
			for(iii=0; iii<thirds.length; iii++) {
				name = thirds[iii].selectSingleNode("name").text;
				id = thirds[iii].selectSingleNode("id").text;
				title = thirds[iii].selectSingleNode("name").text;
				thirdNode = new WebFXTreeItem(title, url, id, '3', '', '');
				secondNode.add(thirdNode);
			}
		}
	}
	
	document.all("treeDiv").innerHTML = tree;
    tree.expandAll();
}

function addPopedom() {
	if(tree.getSelected()) {
		node = tree.getSelected();
		if(node.nodeId == undefined) {
			node.nodeId = "";
		}
		actionurl = "permissionGetPopedomGroupAction?elementId=" + node.nodeId;
		dom = sendXml(actionurl);
		root = dom.selectSingleNode("root");
		var exMsg = root.selectSingleNode("exMsg");
		if(exMsg != null) {
	    	alert(exMsg.text);
	    	return;
	  	}
		result = root.selectNodes("result");
		addTable(result);
	}
}

function addTable(result) {
	tableObj = document.all("popedomTable");
	for(i=0; i<result.length; i++) {
		if(isNodeExist(result[i].selectSingleNode("elementId").text)) {
			continue;
		}
		nodeIdTmpDoc = paramDom.createElement("nodeId");
		nodeIdTmpDoc.text = result[i].selectSingleNode("elementId").text;
		paramRoot.appendChild(nodeIdTmpDoc);
		
		var pRows = tableObj.rows;
  		var vRCount= pRows.length;
  		tableObj.insertRow(vRCount);
 		var row=pRows[vRCount];
  	
  		var col = row.insertCell();
  		col.align = "center";
  		col.innerHTML = vRCount;
  		
  		elementNameValue = "";
		if(result[i].selectSingleNode("elementGrade").text == "1") {
			elementGradeValue = "一级菜单";
			elementNameValue = "<font color='red'>" + result[i].selectSingleNode("elementName").text + "</font>";
		} if(result[i].selectSingleNode("elementGrade").text == "2") {
			elementGradeValue = "二级菜单";
			elementNameValue = "<font color='green'>&nbsp;&nbsp;&nbsp;" + result[i].selectSingleNode("elementName").text + "</font>";
		} if(result[i].selectSingleNode("elementGrade").text == "3") {
			elementGradeValue = "三级菜单";
			elementNameValue = "<font color='blue'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + result[i].selectSingleNode("elementName").text + "</font>";
		} 
		
		var col = row.insertCell();
  		col.align = "left";
  		col.innerHTML = elementNameValue;
  		
		var col = row.insertCell();
  		col.align = "left";
  		col.innerHTML = elementGradeValue;
  	
  		var col = row.insertCell();
  		col.align = "center";
  		col.innerHTML = "<input type='checkbox' name='nodeId' value='" + 
  			result[i].selectSingleNode("elementId").text + "' onclick='setChecked(this, \"" +
  			result[i].selectSingleNode("elementId").text + "\")'>";
  	}
}

function isNodeExist(nodeId) {
	nodeIdDoc = paramRoot.selectNodes("nodeId");
	if(nodeIdDoc.length == 0) {
		return false;
	}
	for(x=0; x<nodeIdDoc.length; x++) {
		if(nodeId == nodeIdDoc[x].text) {
			return true;
		}
	}
}

function setChecked(checkObj, nodeId) {
	oneNodes = tree.childNodes;
	checkNodes = [];
	index = 0;
	for(var i=0; i<oneNodes.length; i++) {
		//点击的节点为一级节点
		if(oneNodes[i].nodeId == nodeId) {
			checkNodes[index++] = oneNodes[i].nodeId;
			//取得所有二级子节点
			twoNodes = oneNodes[i].childNodes;
			for(var ii=0; ii<twoNodes.length; ii++) {
				checkNodes[index++] = twoNodes[ii].nodeId;
				threeNodes = twoNodes[ii].childNodes;
				for(var iii=0; iii<threeNodes.length; iii++) {
					checkNodes[index++] = threeNodes[iii].nodeId;
				}
			}
			
			break;
		}
	}
	if(checkNodes.length == 0) {
		//点击的节点可能是二级节点
		for(var i=0; i<oneNodes.length; i++) {
			twoNodes = oneNodes[i].childNodes;
			for(var ii=0; ii<twoNodes.length; ii++) {
				if(twoNodes[ii].nodeId == nodeId) {
					checkNodes[index++] = twoNodes[ii].nodeId;
					threeNodes = twoNodes[ii].childNodes;
					for(var iii=0; iii<threeNodes.length; iii++) {
						checkNodes[index++] = threeNodes[iii].nodeId;
					}
				}
			}
		}
	}
	
	checkObjs = document.all("nodeId");
	if(checkObjs.length > 0) {
		if(checkObj.checked) {
			for(var i=0; i<checkNodes.length; i++) {
				for(var ii=0; ii<checkObjs.length; ii++) {
					if(checkNodes[i] == checkObjs[ii].value) {
						checkObjs[ii].checked = true;
					}
				}
			}
		} else {
			for(var i=0; i<checkNodes.length; i++) {
				for(var ii=0; ii<checkObjs.length; ii++) {
					if(checkNodes[i] == checkObjs[ii].value) {
						checkObjs[ii].checked = false;
					}
				}
			}
		}
	}
	
}

/**
 * 删除权限
 */
function delPopedom() {
	nodeIdCheckObj = document.all("nodeId");
	if(nodeIdCheckObj == null) {
		return;
	}
	tableObj = document.all("popedomTable");
	if(nodeIdCheckObj.length > 0) {
		for(i=nodeIdCheckObj.length-1; i>=0; i--) {
			if(nodeIdCheckObj[i].checked) {
				for(ii=0; ii<paramRoot.selectNodes("nodeId").length; ii++) {
					if(paramRoot.selectNodes("nodeId")[ii].text == nodeIdCheckObj[i].value) {
						paramRoot.removeChild(paramRoot.selectNodes("nodeId")[ii]);
						break;
					}
				}
				tableObj.deleteRow(i+1);
			}
		}
	} else {
		if(nodeIdCheckObj.checked) {
			tableObj = document.all("popedomTable");
			tableObj.deleteRow(1);
			paramRoot.removeChild(paramRoot.selectSingleNode("nodeId"));
		}
	}
}


function initTable(){
		actionurl = "permissionGetPopedomGroupActionByRoal?groupId="+groupId ;
		alert("initTable:"+groupId);
		dom = sendXml(actionurl);
		var root = dom.selectSingleNode("root");
		var exMsg = root.selectSingleNode("exMsg");
		if(exMsg != null) {
	    	alert(exMsg.text);
	    	return;
	  	}
		result = root.selectNodes("result");
		addTable(result);
}

function confirmModify(){
	var groupId = document.getElementById("groupId").value;	
	var groupName = document.getElementById("groupName").value;
	
	if(groupName.trim() == "") {
		alert("请输入角色名称！");
		return;
	}
	var remark = document.getElementById("remark").value;
	alert("groupId=" +groupId+ "&groupName=" + groupName + "&remark=" + remark);
	url = "roleModifyAction?groupId=" +groupId+ "&groupName=" + groupName + "&remark=" + remark ;
	url = encodeURI(encodeURI(url));
	if(!confirm("是否确定修改角色？")) {
		return;
	}
	doc = sendXml(url, paramDom);
	var root = doc.selectSingleNode("root");
	if(root != null) {
		var exMsg = root.selectSingleNode("exMsg");
	  	if(exMsg != null) {
	    	alert(exMsg.text);
	    	return;
	  	}
	}
	alert("修改成功");
	top.f_closeAndReload("roleModify","authorityManager");
}

//---------------------------数据权限---------------------------------
