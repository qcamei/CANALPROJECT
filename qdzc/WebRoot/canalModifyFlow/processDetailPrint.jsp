<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.cqupt.pub.dao.DataStormSession,java.util.*,java.util.Map,com.cqupt.pub.util.JsonUtil,sun.jdbc.rowset.CachedRowSet" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<title>��ӡҳ��</title>

 <script type="text/javascript">  
function doPrint() {      
bdhtml=window.document.body.innerHTML;      
sprnstr="<!--startprint-->";      
eprnstr="<!--endprint-->";      
prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr));      
prnhtmlprnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));      
window.document.body.innerHTML=prnhtmlprnhtml;   

window.print();
}    

</script>  
<STYLE type=text/css>
 td{ font-size:10pt;}
.listDetail td{border:solid 1px}
.listDetail{border-collapse:collapse}
</STYLE>
</head>
<% 
String canalId = "";
if(request.getParameter("canalId")!= null) {
	canalId = request.getParameter("canalId");	
	System.out.println("Ҫ��ѯ�Ĺ����ţ�"+canalId);
}

DataStormSession session1 = null;
session1= DataStormSession.getInstance();
String sql1= "select a.canal_name,a.agent_name,a.canal_manager,a.canal_dept from qdzc.canal_infomation  a where  a.canal_id = '"+canalId+"'";
System.out.println("��ѯ�����Ĵ�������:"+sql1);
String sql2= "select a.in_id,a.canal_id,a.canal_name,a.step_val,a.oper_user,a.dept_name,a.oper_time,a.process_state,a.remark from qdzc.process_detail_modify a where  a.canal_id = '"+canalId+"' ";
System.out.println("��ѯ�����Ĵ�������:"+sql2);
List resultList = session1.findSql(sql1);
List resultList2 = session1.findSql(sql2);//���Ҫ�ر� 
if(resultList.size() != 0){
Map resultMap = (Map)resultList.get(0);
Map resultMap1 = (Map)resultList2.get(0);
	
%>
<body>


<!--startprint-->
<table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
 	<tr>
	  <td vAlign=center align=center>
	  	<FONT size="4pt" face=����_GB2312>  <strong>������������</strong></FONT>
	  </td>
 	</tr>
  	
  <tr>
    <td>
     
      <table  cellSpacing=0 cellPadding=0 width="100%"  >
      <tr height=40>          
            <td width="50%" style = "font-size:10pt" align=center>�����ţ�<%=resultMap1.get("inId").toString() %></td>           
            <td width="50%" style = "font-size:10pt" align=center>�������ţ�<%=resultMap.get("canalDept").toString() %></td>
          </tr>
        	<tr height=40>          
            <td width="50%" style = "font-size:10pt" align=center>����ʱ�䣺<%=resultMap1.get("operTime").toString() %></td> 
            <td width="50%" style = "font-size:10pt" align=center>�����ˣ�<%=resultMap1.get("operUser").toString() %></td>  
          </tr>
      </table>

<h4>�����б�</h4>	
<table cellspacing="0" cellpadding="0" width="100%" class="listDetail">
          <tr>            
            <td width="15%"><div align="center">������</div></td>
            <td width="6%"><div align="center">��������</div></td>
            <td width="8%"><div align="center">����������</div></td>
 		   <td width="6%"><div align="center">��������</div></td>
            <td width="10%"><div align="center">��������</div></td>
             <td width="10%"><div align="center">������</div></td>
             <td width="10%"><div align="center">����ʱ��</div></td>
              <td width="10%"><div align="center">��������</div></td>
            <td ><div align="center">��˽��</div></td>
             <td width="10%"><div align="center">�������</div></td>   
        </tr>
         <%for(int i=0;i<resultList2.size();i++)
            {
            	Map resultMap0 = (Map)resultList2.get(i);
        %>     
          <tr>       
          	<td width="15%"><div align="center"><%=resultMap0.get("inId").toString() %></div></td>
            <td width="6%"><div align="center"><%=resultMap0.get("canalName").toString() %></div></td>
              <td width="8%"><div align="center"><%=resultMap.get("agentName").toString() %></div></td> 
     	<td width="6%"><div align="center"><%=resultMap.get("canalName").toString() %></div></td> 
            <td width="10%"><div align="center"><%=resultMap0.get("stepVal").toString() %></div></td>
            <td width="10%"><div align="center"><%=resultMap0.get("operUser").toString() %></div></td>
            <td width="10%"><div align="center"><%=resultMap0.get("operTime").toString() %></div></td>
            <td width="10%"><div align="center"><%=resultMap0.get("deptName").toString() %></div></td>            
            <td ><div align="center"><%=resultMap0.get("processState").toString() %></div></td>
		    <td width="10%"><div align="center"><%=resultMap0.get("remark").toString() %></div></td>    
          </tr>
          <%} %>
      </table>
    </td>
  </tr> 
        <!--endprint-->  
         
   <tr height=10><td><br></td></tr>
   <tr>
   	<td align="center">
   	<input type="button" name="print" value="��ӡ" onclick="doPrint()">
   	</td>
   </tr>
</table>
<%}session1.closeSession(); %>

</body>
</html>