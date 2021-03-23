<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Potta+One&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<div class="main-container">
		<%@ include file="fragments/header.jsp"%>
		
		<div class="left-main">
		</div>
		<div class="right-main">
			<div class="container login-section justify">
				<h1 class="mb-3">Login here</h1>
				<form action="/coffeeOrdering/login" method="post">
				
					<div class="form-group">
						<label path="userName" for="username">Username</label>
						<input type="text" path="userName" class="form-control"
							id="username" name="username" placeholder="Enter Username"
							required="required" autocomplete="off"  />

					</div>
					<div class="form-group">
						<label path="password" for="password">Password</label>
						<input type="password" path="password" class="form-control"
							id="password" name="password" placeholder="Enter Password"
							required="required" autocomplete="off"  />

					</div>
					
					<c:if test="${param.error != null}">          
	            		<div class="error text-center">Invalid username and password. </div> 
    				</c:if>
					
					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  
        
					<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-login">Login</button>
					</div>
				</form>
				
			</div>
		</div>

	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>