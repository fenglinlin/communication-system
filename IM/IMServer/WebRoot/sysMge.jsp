<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script  charset="UTF-8"  type="text/javascript" src="js/sysMgement.js"></script>
  </head>
  
  <body>
      <div align="center">
      <form name="serverForm" action="serverAction!startServer"> 
         <input type="hidden" value="<%=new Date().toString() %>">
            <input type="button" name="start" onclick="getStartInfo()" value="开启服务"> 
            <input type="button" name="close" onclick="closeServer()" value="关闭服务"> 
      </form>
      <span id="loginInfo"></span>
      </div>
  </body>
</html>
