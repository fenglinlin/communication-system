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
    
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>文档管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script language="javascript">
function viewArc(aid){
	if(aid==0) aid = getOneItem();
	window.open("archives.asp?aid="+aid+"&action=viewArchives");
}
function editArc(aid){
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=editArchives";
}
function updateArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=makeArchives&qstr="+qstr+"";
}
function checkArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=checkArchives&qstr="+qstr+"";
}
function moveArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=moveArchives&qstr="+qstr+"";
}
function adArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=commendArchives&qstr="+qstr+"";
}
function delArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=delArchives&qstr="+qstr+"";
}

//获得选中文件的文件名
function getCheckboxItem()
{
	var allSel="";
	if(document.form2.id.value) return document.form2.id.value;
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
			if(allSel=="")
				allSel=document.form2.id[i].value;
			else
				allSel=allSel+"`"+document.form2.id[i].value;
		}
	}
	return allSel;
}

//获得选中其中一个的id
function getOneItem()
{
	var allSel="";
	if(document.form2.id.value) return document.form2.id.value;
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
				allSel = document.form2.id[i].value;
				break;
		}
	}
	return allSel;
}
function selAll()
{
	for(i=0;i<document.form2.id.length;i++)
	{
		if(!document.form2.id[i].checked)
		{
			document.form2.id[i].checked=true;
		}
	}
}
function noSelAll()
{
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
			document.form2.id[i].checked=false;
		}
	}
}
function deleConfirm(){
return window.confirm("Are you sure?");
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="skin/images/tbg.gif">&nbsp;文档列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="6%">ID</td>
	<td width="4%">选择</td>
	<td width="8%">用户账号</td>
	<td width="10%">用户名</td>
	<td width="30%">个性签名</td>
	<td width="8%">年龄</td>
	<td width="6%">性别</td>
	<td width="8%">头像ID</td>
	<td width="8%">发布人</td>
	<td width="10%">操作</td>
</tr>


<s:iterator value="users" status="st">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:property value="#st.index+1"/></td>
	<td><input name="id" type="checkbox" id="id" value="97" class="np"></td>
	<td align="left"><a href=''><u><s:property value="account"/></u></a></td>
	<td><s:property value="name"/></td>
	<td><s:property value="signature"/></td>
	<td><s:property value="age"/> </td>
	<td><s:property value="sex"/></td>
	<td><s:property value="profileID "/></td>
	<td>admin</td>
	<td><a href="userAction!edit?account=<s:property value="account"/>">编辑</a>
	        <s:if test="online">
	           | <a href="userAction!offLine?account=<s:property value="account"/>" onclick="return deleConfirm();">下线</a>
	        </s:if>
	        <s:else>
	          | <a href="userAction!delete?account=<s:property value="account"/>" onclick="return deleConfirm();">删除</a> 
	        </s:else>
	  </td>
</tr>
</s:iterator>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="10">
	&nbsp;
	<a href="showDetail.jsp" class="coolbg">添加</a>
	<!--  
	<a href="javascript:noSelAll()" class="coolbg">取消</a>
	<a href="javascript:updateArc(0)" class="coolbg">&nbsp;更新&nbsp;</a>
	<a href="javascript:checkArc(0)" class="coolbg">&nbsp;审核&nbsp;</a>
	<a href="javascript:adArc(0)" class="coolbg">&nbsp;推荐&nbsp;</a>
	<a href="javascript:moveArc(0)" class="coolbg">&nbsp;移动&nbsp;</a>
	<a href="javascript:delArc(0)" class="coolbg">&nbsp;删除&nbsp;</a>-->
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center">
	
	    <%--分页代码 --%>

    <s:iterator begin="1" end="%{pageCount}" status="sta"> 
       <a href="userAction!list?pageNow=<s:property value="#sta.index+1"/>">
           [<s:property value="#sta.index+1"/>]
       </a>     
    </s:iterator>
    <a href="userAction!list?pageNow=<s:property value="pageCount"/>">末页</a>
    </td>
</tr>
</table>

</form>

<!--  搜索表单  -->
<form name='serach' action='userAction!list' method='post'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='flag' style='width:150'>
            <option value='0'>用户账号</option>
            <option value='1'>用户姓名</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='condition' value="" style='width:150px' />
        </td>
        <td width='110'>
    		
        </td>
        <td>
          <input type="submit">
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</body>
</html>