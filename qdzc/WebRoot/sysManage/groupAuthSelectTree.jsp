<%@ page contentType="text/html; charset=gb2312" %>
 <html xmlns="http://www.w3.org/1999/xhtml">
 <%@ taglib uri="/WEB-INF/tld/groupAuthTree.tld" prefix="groupAuthTree" %>

    <head>

        <title></title>


        <script type="text/javascript" src="../com_css/js/xtree.js"></script>
<script type="text/javascript" src="../com_css/js/xmlextras.js"></script>
<script type="text/javascript" src="../com_css/js/xloadtree.js"></script>
<link type="text/css" rel="stylesheet" href="../com_css/css/xtree.css" /> 
 
 <script type="text/javascript">


	function changeList(obj)
    {
         window.returnValue = obj;
	window.close();
                	
     }
                
                </script> 
   
    </head>

    <body>  

 

          
    <groupAuthTree:groupAuthTree></groupAuthTree:groupAuthTree>

    </body>

    </html>

