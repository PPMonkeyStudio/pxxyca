<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>css/news_list/news_list.css" />
<link rel="stylesheet"
	href="<%=basePath%>css/news_details/news_details.css" />
<script type="text/javascript"
	src="<%=basePath%>js/news_details/news_details.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/news_details/details_download_annex.js"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header.jsp" flush="true"></jsp:include>
	<!------------------------------------------------------------------------------------->
	<!------------------------------------------------------------------------------------->
	<!------------------------------------------------------------------------------------->
	<!-- 存储附件-->
	<div id="div_news_id" style="display: none;">
		<s:property value="detailsVO.newsCategoryContent.news.jsj_snews_news_id" />
	</div>
	<div id="news_details_main"
		style="width: 100%; min-width: 1200px; margin: 30px 0 0 0;">
		<div id="news_head" style="width: 100%;">
			<div id="head" style="width: 1200px; margin: 0 auto;">
				<p>
					<s:property
						value="detailsVO.newsCategoryContent.category.category_name" />
				</p>
			</div>
		</div>
		<div id="news" style="width: 1200px; margin: 0 auto;">
			<div id="news_details_head" style="width: 1200px; margin-top: 40px;">
				<p style="font-size: 36px; font-weight: bold; color: #013364;">
					<s:property value="detailsVO.newsCategoryContent.news.news_title" />
				</p>
				<br /> <span style="font-size: 18px; color: #555555;"><s:property
						value="detailsVO.newsCategoryContent.news.news_gmt_show.substring(0,10)" />
					| <s:property
						value="detailsVO.newsCategoryContent.news.news_source" /></span>
				<hr style="width: 100%; margin-top: 20px; color: #b5c6d6;" />
			</div>
			<div id="news_details_content"
				style="width: 1200px; margin-top: 30px;">
				<p
					style="letter-spacing: 3px; text-align: justify; text-indent: 2em;">
					<s:property
						value="detailsVO.newsCategoryContent.content.content_text"
						escape="false" />
				</p>
				<!-- 存储附件-->
				<div id="div_news_annex" style="display: none;">
					<s:property value="detailsVO.newsCategoryContent.news.news_annex" />
				</div>
				<!-- 附件 -->
				<div id="div_annex_box" style="margin: 30px 0;"></div>

				<!-- 分割线 -->
				<hr style="width: 100%; margin-top: 50px; color: #b5c6d6;" />

			</div>
			<div id="key_share" style="width: 1200px;">
				<br /> <span
					style="float: right;font-family: '微软雅黑'; font-size: 18px; color: #003262;">关键词：<s:property
						value="detailsVO.newsCategoryContent.news.news_keywords" /></span> <%-- <span
					style="float: right;"> <span><img
						src="<%=basePath%>img/news_details/qq2.png" id="img1"
						onmouseenter="imgChange1()" onmouseleave="imgBefore1()" /></span> <span><img
						src="<%=basePath%>img/news_details/微信2.png" id="img2"
						onmouseenter="imgChange2()" onmouseleave="imgBefore2()" /></span> <span><img
						src="<%=basePath%>img/news_details/qq空间2.png" id="img3"
						onmouseenter="imgChange3()" onmouseleave="imgBefore3()" /></span> <span><img
						src="<%=basePath%>img/news_details/新浪2.png" id="img4"
						onmouseenter="imgChange4()" onmouseleave="imgBefore4()" /></span>
				</span> --%>
			</div>
		</div>
		<div id="news_bottom"
			style="background-color: #F7F7F9; height: 310px; margin-top: 70px;">
			<div id="about_news" style="width: 1200px; margin: 0 auto;">
				<br />
				<div id="about_news_head">
					<p
						style="font-size: 18px; font-weight: bold; color: #003262; letter-spacing: 5px;">相关新闻</p>
				</div>
				<br />
				<div id="show_news">
					<ul>
						<s:iterator value="detailsVO.relateNews" id="RN">
							<li><img style="cursor: pointer;"
								onclick="window.location='<%=basePath%>suser/news_details?news.jsj_snews_news_id=<s:property
								value="#RN.news.jsj_snews_news_id" />'"
								src="<%=basePath%>snews/img_getNewsSImg?imgName=<s:property value="#RN.news.news_simg" />" /><span
								onclick="window.location='<%=basePath%>suser/news_details?news.jsj_snews_news_id=<s:property
								value="#RN.news.jsj_snews_news_id" />'"><s:property
										value="#RN.news.news_title" /></span></li>
						</s:iterator>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<!------------------------------------------------------------------------------------->
	<!------------------------------------------------------------------------------------->
	<!------------------------------------------------------------------------------------->
	<jsp:include page="/footer.jsp" flush="true"></jsp:include>

</body>
<script>
download_annex();
</script>
</html>