<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>regist</title>
<script type="text/javascript">
function _change() {
	
	var img= document.getElementById("img");
	
	img.src="<c:url value='/ValidationCodeServlet'/>?a="+new Date().getTime();
	alert(img.src);
	return;
	}
</script>
</head>
<body>
<h1>注册</h1>
<%-- <p style="color: red; font-weight: 900">${msg }</p> --%>
<%--${pageContext.request.contextPath }/RegistServlet --%>
<form action="<c:url value='/RegistServlet'/>" method="post">
  用户名：<input type="text" name="username" value="${form.username }"/><span style="color: red; font-weight: 900">${errors.username }</span><br/>
  密　码：<input type="password" name="password" value="${form.password }"/><span style="color: red; font-weight: 900">${errors.passwprd }</span><br/>
  验证码：<input type="text" name="verifyCode" value="${formverifyCode }" size="3"/>
        <img id="img" src="<c:url value='/ValidationCodeServlet'/>" border="2"/>
        <a href="javascript:_change()">换一张</a><span style="color: red; font-weight: 900">${errors.verifyCode }</span><br/>
  <input type="submit" value="注册"/>
</form>
</body>
</html>