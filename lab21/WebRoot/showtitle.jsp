<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查找结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    

  </head>
<body>
<body bgcolor="#FFE4E1">
<br/><br/><br/><br/><br/><br/><br/><br/>
	<h1 align="center">作者作品</h1>
	<table width="100%" border="1" align="center">
		<tr>
			<th align="center">ISBN</th>
			<th align="center">书名</th>
			<th align="center">删除</th>

		</tr>
		 <s:iterator value="titles">
         <tr>
           <td>
              <s:property value="isbn" />
           </td>
           <td>
               <a href="showdetail.action?bookname=<s:property value="title"/>"><s:property value="title"/></a>
           </td>
           <td>
              <a href="delete.action?isbn=<s:property value="isbn"/>">delete</a>
           </td>
         </tr>
         </s:iterator>
	</table>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<a href="index.jsp">返回主页</a>
</body>
</html>
