<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">

/*
 * 提交表单有2种方式；
  1种是在表单里面加一个input type="submit" 按钮，点击提交
  
  另一种是用js写，但这两种方法都得写form里面的action路径
  
 */
 function createOrder() {
	//获取表单标签，调用submit方法就可以
	document.getElementById("orderForm").submit();
}
 
</script>


</head>


<body class="main">
	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
							href="cart.jsp">&nbsp;购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单
					</div>

					<form id="orderForm" action="${pageContext.request.contextPath}/CreateOrderServlet" method="post">
						<table cellspacing="0" class="infocontent">
							<tr>
								<td><table width="100%" border="0" cellspacing="0">
										<tr>
											<td><img src="images/buy2.gif" width="635" height="38" />
												<p>您好：${user.username}先生！欢迎您来到商城结算中心</p></td>
										</tr>
										<tr>
											<td><table cellspacing="1" class="carttable">
													<tr>
														<td width="10%">序号</td>
														<td width="40%">商品名称</td>
														<td width="10%">价格</td>
														<td width="10%">类别</td>
														<td width="10%">数量</td>
														<td width="10%">小计</td>

													</tr>
												</table>

												<table width="100%" border="0" cellspacing="0">
													<!--  设置一个变量c:set totalPrice ,设置初始变量为0-->
												<c:set var="totalPrice" value="0"></c:set>
													<!-- 因为存入session里的值是Map<Product, Integer>类型，遍历之后是entry，为了方便起见设变量为entry。 varStatus是变量的状态-->
													
													<c:forEach items="${cart}" var="entry" varStatus="vs">
													<tr>
														<td width="10%">${vs.count}</td>
														<td width="40%">${entry.key.name}</td>
														<td width="10%">${entry.key.price}</td>
														<td width="10%">${entry.key.category}</td>
														<td width="10%"><input name="text" type="text"
															value="${entry.value}" style="width:20px" readonly="readonly" /></td>
														<td width="10%">${entry.key.price * entry.value}</td>

													</tr>
													<!--  设置一个变量c:set totalPrice ,把它放在每个tr的外面，则每循环一次就做一次总价统计-->
													<c:set var="totalPrice" value="${totalPrice + entry.key.price * entry.value}"></c:set>
		
													</c:forEach>
												</table>


												<table cellspacing="1" class="carttable">
													<tr>
														<td style="text-align:right; padding-right:40px;"><font
															style="color:#FF0000">合计：&nbsp;&nbsp;${totalPrice + entry.key.price * entry.value}元</font></td>
													</tr>
												</table>

												<p>
													收货地址：<input name="receiverAddress" type="text" value=""
														style="width:350px" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"></a>
													<br /> 收货人：&nbsp;&nbsp;&nbsp;&nbsp;<input
														name="receiverName" type="text" value=""
														style="width:150px" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"></a>
													<br /> 联系方式：<input type="text" name="receiverPhone"
														value="" style="width:150px" />&nbsp;&nbsp;&nbsp;&nbsp;

												</p>
												<hr />
												<p style="text-align:right">
													<img src="images/gif53_029.gif" width="204" height="51"
														border="0" onclick="createOrder()" />
												</p></td>
										</tr>
									</table></td>
							</tr>
						</table>
					</form></td>
			</tr>
		</table>
	</div>


	<jsp:include page="foot.jsp" />


</body>
</html>
