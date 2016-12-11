function checkInput(){
	var flag=1;
//	var obj=document.inputForm.elements['user.account'];
//	var s=trim(obj.value);
//	if(s.length==0){
//		document.getElementById('accountSpan').innerHTML="<font color='red'>"+"请填写"+"</font>";
//		flag=0;
//	}else{
//		document.getElementById('accountSpan').innerHTML="<font color='red'>"+"</font>";
//	}
	
	
	obj=document.inputForm.elements['user.name'];
	s=trim(obj.value);
	if(s.length==0){
		document.getElementById('nameSpan').innerHTML="<font color='red'>"+"请填写"+"</font>";
		flag=0;
	}else{
		document.getElementById('nameSpan').innerHTML="<font color='red'>"+"</font>";
	}
	
	
	
	obj=document.inputForm.elements['user.age'];
    s=trim(obj.value);
    var re = /^\d*$/;
	if (s.match(re) == null) {
		document.getElementById('ageSpan').innerHTML = "<font color='red'>"
			+ "格式不对" + "</font>";
	    flag = 0;
	}else
		document.getElementById('ageSpan').innerHTML = "<font color='red'>"
			+ "</font>";
	
	obj=document.inputForm.elements['user.password'];
	var pw1=trim(obj.value);
	if(pw1.length==0){
		document.getElementById('passwordSpan').innerHTML="<font color='red'>"+"请填写"+"</font>";
		flag=0;
	}else{
		document.getElementById('passwordSpan').innerHTML="<font color='red'>"+"</font>";
	}
	
	obj=document.inputForm.elements['password'];
	var pw2=trim(obj.value);
	if(pw2.length==0){
		document.getElementById('passwordConSpan').innerHTML="<font color='red'>"+"请填写"+"</font>";
		flag=0;
	}else{
		document.getElementById('passwordConSpan').innerHTML="<font color='red'>"+"</font>";
	}
	
	if(pw1!=pw2)
	{
		alert("两次输入的密码不相同");
		flag=0;
	}

	
	if(flag==1){
		document.inputForm.submit();
	}
	
	
}
function trim(str)
{
     return str.replace(/(^\s*)(\s*$)/g, "");
}



