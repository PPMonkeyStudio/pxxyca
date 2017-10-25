<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="<%=basePath%>snews/news_createNews">创建新闻</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="<%=basePath%>snews/category_createCategory">创建新闻类别</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="<%=basePath%>snews/category_listCategory">新闻类别列表</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="<%=basePath%>snews/news_listNews">新闻列表</a>
	</div>
</body>
</html>