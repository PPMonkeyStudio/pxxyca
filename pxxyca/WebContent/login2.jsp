<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=basePath%>suser_/login_" method="post">
	<label>用户名</label><input type="text" name="username" /><br />
	<label>密码</label><input type="password" name="password" /><br />
	<input type="submit" value="提交" /><input type="button" src="register.jsp" value="注册" />
</form>
</body>
</html>