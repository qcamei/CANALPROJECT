<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

 <html xmlns="http://www.w3.org/1999/xhtml">
 <%@ taglib uri="/WEB-INF/tld/groupAuthTree.tld" prefix="groupAuthTree" %>

    <head>

        <title></title>


<script type="text/javascript" src="../com_css/js/xtree.js"></script>
<script type="text/javascript" src="../com_css/js/xmlextras.js"></script>
<script type="text/javascript" src="../com_css/js/xloadtree.js"></script>
<link type="text/css" rel="stylesheet" href="../com_css/css/xtree.css" /> 
 
 <script type="text/javascript">


	function changeList(group)
    {
        if(group)
        {
			var groupArr = group.split(":");
			var groupId = groupArr[0];
			var groupName = groupArr[1];
			if(groupId.indexOf(";;") != -1){
				groupId = groupId.substring(0,groupId.indexOf(";;"));
			}
			
			var listUrl = 'sysManage/groupAuthList.jsp?group='+groupId;
            window.parent.document.getElementById('content_frame').src = listUrl;
   		}
                	
     }
                
</script> 
   
    </head>

    <body>  

 
 
          
    <groupAuthTree:groupAuthTree></groupAuthTree:groupAuthTree>

    </body>

    </html>

