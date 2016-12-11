<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script  charset="UTF-8"  type="text/javascript" src="js/validateInput.js"></script>
  </head>
  
  <body>
 
     <s:form action="userAction!saveOrUpdate" method="post" theme="simple" name="inputForm">
      <h1>您的注册信息为：请注意保存</h1>
        <input type="hidden" name="register" value="${register}"/>
        <input type="hidden" name="user.account" value="<s:property value="user.account"/>"/>
				<table width="70%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="skin/images/tbg.gif">&nbsp;文档列表&nbsp;</td>
</tr>
					<tr bgcolor="#FAFAF1">
				    	<td >用户账号:</td>
						<td>
							<s:property value="user.account"/>
						</td>
						<td >
							<span id="accountSpan">****</span>
						</td>
					</tr> 
					<tr bgcolor="#FAFAF1">
				    	<td>用户姓名:</td>
						<td >
							<s:property value="user.name"/>
						</td>
						<td>
							<span id="nameSpan">****</span>
						</td>
					</tr>
					<tr bgcolor="#FAFAF1">
				    	<td>个性签名:</td>
						<td >
								<s:property value="user.signature"/>
						</td>
						<td>
							<span id="signatureSpan"></span>
						<br></td>
					</tr>
					<tr bgcolor="#FAFAF1">
				    	<td>年龄:</td>
						<td >
							<s:property value="user.age"/>
						</td>
						<td>
							<span id="ageSpan"></span>
						<br></td>
					</tr>
					<tr bgcolor="#FAFAF1">
					<td>密码:</td>
						<td >
								<s:property value="user.password"/>
						</td>
						<td>
							<span id="passwordSpan">****</span>
						</td>
					</tr>
					
					<tr bgcolor="#FAFAF1">
						<td>
							性别&nbsp;&nbsp;
						</td>
						<td>
							<s:property value="user.sex"/>
						</td>
						<td>
							<span>****</span>
						</td>
					</tr>
					
					
					
				</table>

			</s:form>
  </body>
</html>
