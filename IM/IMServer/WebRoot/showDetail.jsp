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
        <input type="hidden"" name="register" value="${register}"/>
        <input type="hidden" name="user.account" value="<s:property value="user.account"/>"/>
				<table width="70%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="skin/images/tbg.gif">&nbsp;文档列表&nbsp;</td>
</tr>
				<!--	<tr bgcolor="#FAFAF1">
				    	<td >用户账号:</td>
						<td>
							<s:textfield required="true" name="user.account"
								value="%{user.account}" />
						</td>
						<td >
							<span id="accountSpan">****</span>
						</td>
					</tr>  -->
					<tr bgcolor="#FAFAF1">
				    	<td>用户姓名:</td>
						<td >
							<s:textfield required="true" name="user.name"
								value="%{user.name}" />
						</td>
						<td>
							<span id="nameSpan">****</span>
						</td>
					</tr>
					<tr bgcolor="#FAFAF1">
				    	<td>个性签名:</td>
						<td >
							<s:textfield required="true" name="user.signature"
								value="%{user.signature}" />
						</td>
						<td>
							<span id="signatureSpan"></span>
						<br></td>
					</tr>
					<tr bgcolor="#FAFAF1">
				    	<td>年龄:</td>
						<td >
							<s:textfield required="true" name="user.age"
								value="%{user.age}" />
						</td>
						<td>
							<span id="ageSpan"></span>
						<br></td>
					</tr>
					<tr bgcolor="#FAFAF1">
					<td>密码:</td>
						<td >
							<!-- <s:password equired="true"  name="eu.password"
								value="%{eu.password}"/> -->
						    <input type="password" name="user.password"  value="<s:property value="user.password"/>">
								
						</td>
						<td>
							<span id="passwordSpan">****</span>
						</td>
					</tr>
					<tr bgcolor="#FAFAF1">
					<td>密码确认:</td>
						<td >
							 <input type="password" name="password"  value="<s:property value="user.password"/>">
						</td>
						<td>
							<span id="passwordConSpan">****</span>
						</td>
					</tr>
					<tr bgcolor="#FAFAF1">
						<td>
							性别&nbsp;&nbsp;
						</td>
						<td>
							<select name="user.sex">
								<option value="男" selected="selected">
									男
								</option>
								<option value="女">
									女
								</option>
							</select>
						</td>
						<td>
							<span>****</span>
						</td>
					</tr>
					
					
					<tr bgcolor="#FAFAF1">
						<td>
							<input type="button" value="提交" onclick="checkInput();"/>
						</td>

						<td>
							<input type="reset" value="恢复" />
						</td>
						<td> <span>*为必填项</span></td>
					</tr>
				</table>

			</s:form>
  </body>
</html>
