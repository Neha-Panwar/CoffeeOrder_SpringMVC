<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Coco Cafe</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Pinyon+Script&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/style-user.css">
<link rel="stylesheet" href="css/style-table.css">
</head>

<body>
	<div class="main-container-register">
		<%@ include file="fragments/header.jsp"%>
		<div class="section grid">
			<%@ include file="fragments/sidebar-navbar.jsp"%>
			<div class="content">
			<h1>Review Orders</h1>
			
				<c:choose>
					<c:when test="${not empty successMsg}">

						<div id="successMessage" class="success text-center pb-4">${successMsg}</div>
					</c:when>
				</c:choose>
				
				<table class="table table-striped">
					<thead>
						<tr>
							<th class="center">#</th>
							<th>Beverage Name</th>
							<th>Beverage Size</th>
							<th>Topping</th>
							<th>Sandwich</th>
							<th>Cost (Rs.)</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order" varStatus="loop">
							<tr>
								<td class="center">${loop.index+1}</td>
								<td>${order.getBeverage().getBeverageName()}</td>
								<td>${order.getBeverage().getBeverageSize()}</td>
								<td>${order.getTopping()==null ? "-" : order.getTopping().getToppingName()}</td>
								<td>${order.getSandwich()==null ? "-" : order.getSandwich().getSandwichName()}</td>
								<td>${order.getOrderCost()}</td>
								<td><a type="button" class="btn btn-icon" href="/coffeeOrdering/updateOrder?orderId=${order.getOrderId()}"><i class="fa fa-pencil"></i></a></td>
								<td><a type="button" class="btn btn-icon" href="/coffeeOrdering/confirmOrder/${order.getOrderId()}"><i class="fa fa-check"></i></a></td>
								<td><a type="button" class="btn btn-icon" href="/coffeeOrdering/deleteOrder/${order.getOrderId()}"><i class="fa fa-close"></i></a></td>							
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

</html>