<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="w-screen h-screen flex justify-center items-center">
	
	<div>
		<div class="flex items-center justify-center p-3 text-center ">
			<img alt="logo"
				src="${pageContext.request.contextPath }/assets/logo.svg">
			<h1 class="font-bold text-lg pl-3">Expense Tracker</h1>
		</div>
		<form action="LoginHandler" method="post" class="flex flex-col justify-center border-2 rounded-lg max-w-xl p-3">
			<h1 class="text-2xl font-bold p-5 text-center">Login to your account</h1>
			
			<label for="email">Email</label>
			<input id="email" name="email" type="email" class="w-[300px] border-2 border-grey-600 rounded-md p-2 mb-3" required>
			
			<label for="password">Password</label>
			<input id="password" name="password" type="password" class="w-[300px] border-2 border-grey-600 rounded-md p-2 mb-4" required>
				
			<button type="submit" class="bg-black hover:bg-gray-700 rounded-lg px-5 py-2.5 mb-3 text-white text-bold text-center">Login</button>
			
			<% if(request.getAttribute("message") != null){ %>
				<p class="text-center font-bold text-md text-red-500"><%= request.getAttribute("message") %></p>
			<% } %>
			
			<div class="mt-3 text-center">
	                <span class="text-sm">Don't have an acoount? </span>
	                <a href="register" class="text-sm text-blue-700 font-bold">Register Now</a>
	        </div>
		</form>
	</div>

</body>
</html>