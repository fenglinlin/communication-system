<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC “-//W3C//DTD XHTML 1.0 Transitional//EN” “http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd”>

<html>

  <HEAD>
  <base href="<%=basePath%>">
		<TITLE>用户登录</TITLE>
		<meta http-equiv="pragma" content="no-cache">
	    <meta http-equiv="cache-control" content="no-cache">
	    <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<LINK href="images/Default.css" type=text/css rel=stylesheet>
		<LINK href="images/xtree.css" type=text/css rel=stylesheet>
		<LINK href="images/User_Login.css" type=text/css rel=stylesheet>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META content="MSHTML 6.00.6000.16674" name=GENERATOR>
		
		<script  charset="UTF-8"  type="text/javascript" src="js/login.js"></script>
	</HEAD>
	<BODY id=userlogin_body>
		<DIV></DIV>
    
		<DIV id=user_login>
			<DL>
				<DD id=user_top>
					<UL>
						<LI class=user_top_l></LI>
						<LI class=user_top_c></LI>
						<LI class=user_top_r></LI>
					</UL>
				<DD id=user_main>
				
					<UL>
						<LI class=user_main_l></LI>
						<LI class=user_main_c>
						<form name="loginForm">
							<DIV class=user_main_box>
								<UL>
									<LI class=user_main_text>
										用户名：
									</LI>
									
									<LI class=user_main_input>
										<INPUT class=TxtUserNameCssClass id=TxtUserName maxLength=20
											name=userName>
											
										<SPAN id=valrUserName style=" COLOR: red"></SPAN>
									</LI>
								</UL>
								<UL>
									<LI class=user_main_text>
										密 码：
									</LI>
									<LI class=user_main_input>
										<INPUT class=TxtPasswordCssClass id=TxtPassword type=password
											name=password>
										<SPAN id=valrPassword style=" COLOR: red"></SPAN>
									</LI>
								</UL>
								<UL>
									<LI class=user_main_text>
										Cookie：
									</LI>
									<LI class=user_main_input>
										<SELECT id=DropExpiration name=DropExpiration>
											<OPTION value=None selected>
												不保存
											</OPTION>
											<OPTION value=Day>
												保存一天
											</OPTION>
											<OPTION value=Month>
												保存一月
											</OPTION>
											<OPTION value=Year>
												保存一年
											</OPTION>
										</SELECT>
									</LI>
									
								</UL>
								<br><br>
								<SPAN id=valrValidate style="COLOR: red">
								    <s:property value="valrValidate" />
								</SPAN>
							</DIV>
							</FORM>
						</LI>
						<LI class=user_main_r>
							<INPUT class=IbtnEnterCssClass id=IbtnEnter
								style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
								onclick="return check()"
								type=image src="images/user_botton.gif" name=IbtnEnter>
							
						</LI>
					</UL>
					
				<DD id=user_bottom>
					<UL>
						<LI class=user_bottom_l></LI>
						<LI class=user_bottom_c>
							<SPAN style="MARGIN-TOP: 40px">如果您尚未在本站注册为用户，请先点此 <A
								href="http://www.huacker.com.cn">注册</A> 。</SPAN>
						</LI>
						<LI class=user_bottom_r></LI>
					</UL>
				</DD>
			</DL>
		</DIV>	
		<DIV id=validationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

		<DIV></DIV>

		
	</BODY>
</html>
