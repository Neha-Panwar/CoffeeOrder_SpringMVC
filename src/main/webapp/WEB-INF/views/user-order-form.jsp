<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/style-user.css">
</head>
<body>
	
	<div class="main-container-register">
		<%@ include file="fragments/header.jsp"%>
		<div class="section grid">
			<%@ include file="fragments/sidebar-navbar.jsp"%>
			<div class="content list-container">
				<h1>Coffee Order Details</h1>
				<div class="form-container">
					
					<c:choose>
					<c:when test="${not empty successMsg}">
						<c:set var = "edit" value = "${0}"/>
						<div class="success text-center pb-4">${successMsg}</div>
					</c:when>
					</c:choose>
					
					<form:form  method="post" modelAttribute="orderForm">
						
						<c:set var = "edit" value = "${0}"/>
      					<c:if test = "${orderForm.beverageName != null}">
        				<c:set var = "edit" value = "${1}"/>
      					</c:if>
						
						<div class="row">
							<div class="form-group col-lg-6 col-md-12 col-sm-12">
								<form:label path="beverageName">Beverage:</form:label>
								<form:select path="beverageName" class="form-control" id="bevName"
									items="${beverageList}" required="required" />	
							</div>
						
							<div class="form-group col-lg-6 col-md-12  col-sm-12">
								<form:label path="beverageSize">Beverage Size:</form:label>
								<form:select path="beverageSize" class="form-control" id="bevSize"
									items="${beverageSizeList}" required="required" />
							</div>
						</div>  
						
						<div class="row">
							<div class="form-group col-lg-6 col-md-12 col-sm-12">
								<form:label path="toppingName">Select Topping:</form:label>
								<form:select path="toppingName" class="form-control" id="topping"
									items="${toppingList}" />
							</div>
							
							<div class="form-group col-lg-6 col-md-12 col-sm-12">
								<form:label path="sandwichName">Select Sandwich:</form:label>
								<form:select path="sandwichName" class="form-control" id="sandwich"
									items="${sandwichList}" />
							</div>
						</div>
						
						<div class="row">
							<form:input type="hidden" path="status" id="status" name="status" value="PENDING"/>
						</div>
						
						<form:button type="submit" class="btn btn-save">Save Order</form:button>
						
						<c:if test = "${edit == 0}">
							<button class="btn btn-submit" onclick="placeCoffeeOrder()">Place Order</button>
      					</c:if>
						
					</form:form>
				</div>
			
			</div>
		</div>
		
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/script.js"></script>
	
</body>
</html>