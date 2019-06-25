<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- //为了写Java代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${category}&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>计算机</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${pageresult.totalCount }种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>
								<table cellspacing="0" class="booklist">
									<tr>
									
									<c:forEach items="${pageresult.list}" var="product">
									
										<td>
 
											<div class="divbookpic">
												<p>
													<a href="${pageContext.request.contextPath}/ProductInfoServlet?id=${product.id}"><img src="${pageContext.request.contextPath}${product.imgurl}" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="${pageContext.request.contextPath}/ProductInfoServlet?id=${product.id}">书名:${product.name}<br />售价:${product.price} </a>
											</div>
										</td>
									</c:forEach>
									 
									</tr>
								</table>
 
								<div class="pagination">
								
								   第 ${pageresult.currentPage}页/总共${pageresult.totalPage} 页
									<ul>
									
													<!--  上一页-->
 									<!-- 	<li class="disablepage">上一页 &lt;&lt;</li> disablepage是为了设置他的样式    不可用-->
										<c:if test="${pageresult.currentPage == 1}">
											<li class="disablepage">上一页 &lt;&lt;</li>
										</c:if>
										
										<c:if test="${pageresult.currentPage!= 1}">
											<li><a href="${pageContext.request.contextPath}/showProductByPage?category=${category}&page=${pageresult.currentPage-1}">上一页 &lt;&lt;</a></li>
										</c:if>
										
										<!-- <li class="currentpage">1</li> -->
										
										
										<!-- 显示分页 -->
										 <c:forEach begin="1" end="${pageresult.totalPage}" var="i">
										 
										<li><a href="${pageContext.request.contextPath}/showProductByPage?category=${category}&page=${i}">${i}</a></li>
										 
										 </c:forEach>


												<!--  下一页-->
 									<!-- 	<li class="disablepage">上一页 &lt;&lt;</li> disablepage是为了设置他的样式    不可用-->
										<c:if test="${pageresult.currentPage == pageresult.totalPage}">
											<li class="disablepage">下一页 &lt;&lt;</li>
										</c:if>
										
										<c:if test="${pageresult.currentPage!= pageresult.totalPage}">
											<li><a href="${pageContext.request.contextPath}/showProductByPage?category=${category}&page=${pageresult.currentPage+1}">下一页 &lt;&lt;</a></li>
										</c:if>
										
										 

									</ul>
								</div>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
