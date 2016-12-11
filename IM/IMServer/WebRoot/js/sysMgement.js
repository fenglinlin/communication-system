function startServer(){
	document.serverForm.submit();
	document.serverForm.start.disabled   =true;
}

function closeServer(){
	document.serverForm.action="serverAction!closeServer";
	document.serverForm.submit();
	document.serverForm.close.disabled   =true;
}


var xmlHttp;

function ajax() {
	try {
		xmlHttp = new xmlHttpRequest();
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("不支持AJAX");
				return false;
			}
		}
	}
}

function getStartInfo(){
	ajax();
	xmlHttp.onreadystatechange=function(){
        if(xmlHttp.readyState==4){
//        	alert(xmlHttp.responseText);
          document.getElementById("loginInfo").innerHTML="<font color='red'>"+xmlHttp.responseText+"</font>";
          }
      }
	var url="serverAction!startServer"//
    xmlHttp.open("GET",url,true);    
    //发送请求    

    xmlHttp.send(null);    

}



function getCloseInfo(){
	ajax();
	xmlHttp.onreadystatechange=function(){
        if(xmlHttp.readyState==4){
//        	alert(xmlHttp.responseText);
          document.getElementById("loginInfo").innerHTML=xmlHttp.responseText;
          }
      }
	var url="serverAction!closeServer"//
    xmlHttp.open("GET",url,true);    
    //发送请求    
    xmlHttp.send(null);    

}