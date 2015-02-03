package com.cqupt.sysManage.tld;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.Component;

import com.cqupt.pub.dao.DataStormSession;
import com.cqupt.pub.exception.CquptException;
import com.cqupt.pub.util.JsonUtil;
import com.opensymphony.xwork2.util.ValueStack;

public class GroupAuthTree  extends Component{
	
	public GroupAuthTree(ValueStack stack) {
		super(stack);
		// TODO Auto-generated constructor stub
	}
	
	public boolean start(Writer writer){
		boolean result = super.start(writer);
		try{
			String str = getPermission();
			writer.write(str);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	private String getPermission(){
		StringBuilder resultStr = new StringBuilder();
		resultStr.append("<script type=\"text/javascript\">");
		
		resultStr.append("webFXTreeConfig.rootIcon		= \"../images/sysManage/folder.png\";");
		resultStr.append("webFXTreeConfig.openRootIcon = \"../images/sysManage/folder.png\";");
		resultStr.append("webFXTreeConfig.folderIcon = \"../images/sysManage/deptfolder.png\";");
		resultStr.append("webFXTreeConfig.openFolderIcon = \"../images/sysManage/deptfolder-open.png\";");
		resultStr.append("webFXTreeConfig.fileIcon	= \"../images/sysManage/dept.png\";");
		resultStr.append("webFXTreeConfig.lMinusIcon = \"../images/sysManage/Lminus.png\";");
		resultStr.append("webFXTreeConfig.lPlusIcon	= \"../images/sysManage/Lplus.png\";");
		resultStr.append("webFXTreeConfig.tMinusIcon = \"../images/sysManage/Tminus.png\";");
		resultStr.append("webFXTreeConfig.tPlusIcon	= \"../images/sysManage/Tplus.png\";");
		resultStr.append("webFXTreeConfig.iIcon	= \"../images/sysManage/I.png\";");
		resultStr.append("webFXTreeConfig.lIcon	= \"../images/sysManage/L.png\";");
		resultStr.append("webFXTreeConfig.tIcon	= \"../images/sysManage/T.png\";");
		resultStr.append("webFXTreeConfig.blankIcon	= \"../images/sysManage/blank.png\";");  
		resultStr.append("var tree = new WebFXTree(\"权限管理\");");
		resultStr.append("tree.add(new WebFXLoadTreeItem(\"所有权限组\", \"groupAuthTreeQueryAction?userGroupId=init\",action=\"javascript:changeList('0:所有权限组')\"));");
		resultStr.append("document.write(tree)");
		resultStr.append("</script>");
		
		
		return resultStr.toString();
	}

}
