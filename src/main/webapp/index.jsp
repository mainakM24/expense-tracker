<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//HttpSession sess = request.getSession(false);  
	if (session != null && session.getAttribute("user") != null) {
	
	    response.sendRedirect("home");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expense-tracker</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>


	<!-- header -->
	<nav class="flex justify-between p-3 border-2 rounded-md">

		<!-- Icon and Name -->
		<div class="flex items-center space-x-2">
			<img alt="logo"
				src="${pageContext.request.contextPath }/assets/logo.svg">
			<h1 class="font-bold text-lg">Expense Tracker</h1>
		</div>

		<!-- Auth Buttons -->
		<div>
			<a href="login">
				<button
					class="bg-black hover:bg-gray-700 rounded-lg px-5 py-2.5 mx-1 text-white text-bold text-center text-sm">Login</button>
			</a> 
			<a href="register">
				<button
					class="bg-black hover:bg-gray-700 rounded-lg px-5 py-2.5 mx-1 text-white text-bold text-center text-sm">Register</button>
			</a>
		</div>
	</nav>


	<!-- Hero section -->
	<div class="flex-col sm:flex justify-center items-center text-center">

		<!-- Wrapper of Hero -->
		<div class="max-w-xl my-24">

			<!-- Bold Heading -->
			<h1 class="text-4xl font-extrabold text-center mt-2">
				Manage Your Finance,<br> Track Your Spending!
			</h1>

			<!-- Paragraph -->
			<p class="p-3 my-1">This platform is designed to help you manage
				your expenses. Start tracking your expenses today! You can track
				your daily expenses like food, transportation, housing, and so on.
				You can also set a budget for each category and receive
				notifications when you go over budget.</p>

			<!-- Get Started Button -->
			<a href="register">
				<button
					class="bg-black hover:bg-gray-700 rounded-lg px-5 py-2.5 my-3 text-white text-bold text-center text-sm">
					Get Started</button>
			</a>
		</div>
		<img alt="dashboard"
			src="${pageContext.request.contextPath }/assets/dashboardOG.png"
			class="w-3/4 mx-auto mb-32 rounded-md border-2">
	</div>

</body>
</html>












