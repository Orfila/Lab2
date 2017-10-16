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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书详情</title>
	</head>
<body>
<body bgcolor="#FFE4E1">
     <br/><br/><br/><br/><br/>
	<h1 align="center">作者信息</h1>
	<table width="100%" border="1" align="center">
		<tr>
			<th>AuthorID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Country</th>
		</tr>
	    <s:iterator value="info">
         <tr>
           <td>
              <s:property value="authorid" />
           </td>
           <td>
              <s:property value="name" />
           </td>
           <td>
              <s:property value="age" />
           </td>
           <td>
              <s:property value="country" />
           </td>
         </tr>
         </s:iterator>
	</table>
	<br/><br/><br/><br/><br/>
	<h1 align="center">作品详细信息</h1>
	<table width="100%" border="1" align="center">
		<tr>
			<th>ISBN</th>
			<th>Title</th>
			<th>AuthorID</th>
			<th>Publisher</th>
			<th>PublishDate</th>
			<th>Price</th>
		</tr>
		<s:iterator value="info">
         <tr>
           <td>
              <s:property value="isbn" />
           </td>
           <td>
              <s:property value="title" />
           </td>
           <td>
              <s:property value="authorid" />
           </td>
           <td>
              <s:property value="publisher" />
           </td>
           <td>
              <s:property value="publishdate" />
           </td>
           <td>
              <s:property value="price" />
           </td>
         </tr>
         </s:iterator>
	</table>
	<br><br/><br/><br/><br/>
	    
		<a href="index.jsp">返回主页</a>
</body>
</html>