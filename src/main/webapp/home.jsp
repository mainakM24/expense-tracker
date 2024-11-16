<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expense-Tracker</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<script>
     function toggle(id) {
       document.getElementById(id).classList.toggle('hidden')
     }
</script>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700;800&display=swap');
	body{
		font-family: "Poppins", sans-serif;
	}
</style>
</head>
<body>
	
	<!-- Container -->
	<div class="flex w-[100hw] h-screen">
		<!-- SideNavbar -->
		<div id="sideBar" class="h-full bg-white hidden pt-[67px] flex md:w-1/5 md:pt-0 md:flex flex-col justify-between fixed border-2 z-10">
		
			<!-- Icon and name -->
			<div class="hidden md:flex items-center justify-center p-3 text-center rounded-md">
				<img alt="logo"
					src="${pageContext.request.contextPath }/assets/logo.svg">
				<h1 class="font-bold text-lg pl-3">Expense Tracker</h1>
			</div>
			
			<!-- Navigation tiles -->
			<div class="grow flex flex-col space-y-6 px-4 md:mt-12 pt-3 md:pt-0">
				<a href="${pageContext.request.contextPath}/dashboard" class="cursor-pointer bg-sky-100 hover:bg-sky-200 p-3 rounded-md flex items-center justify-around font-bold"> 
					<span class="material-icons text-4xl">grid_view</span> Home
				</a>
				<a href="${pageContext.request.contextPath}/budget" class="cursor-pointer bg-sky-100 hover:bg-sky-200 p-3 rounded-md flex items-center justify-around font-bold"> 
					<span class="material-icons text-4xl">savings</span> Budget
				</a>
				<a href="${pageContext.request.contextPath}/expense" class="cursor-pointer bg-sky-100 hover:bg-sky-200 p-3 rounded-md flex items-center justify-around font-bold"> 
					<span class="material-icons text-4xl">paid</span> Expense
				</a>
				<a href="${pageContext.request.contextPath}/profile" class="cursor-pointer bg-sky-100 hover:bg-sky-200 p-3 rounded-md flex items-center justify-around font-bold"> 
					<span class="material-icons text-4xl">workspace_premium</span> Profile
				</a>
			</div>
			
			<!-- Logout -->
			
			<a href="${pageContext.request.contextPath}/logout" class="bg-black text-white font-bold p-4 m-2 rounded-lg text-center">📤 Log out</a>
			
		</div>
		
		<!-- Body -->
		<div class="grow flex flex-col md:ml-[20%]">
			<!-- Header -->
			<div class="flex justify-between text-center items-center rounded-md border-2 sticky top-0 bg-white z-20">
				<!-- Heading and Navigation icon -->
				
				<!-- For small devices -->
				<div class="p-2 md:hidden flex items-center">
					<button id="nav-toggle" onClick="toggle('sideBar')" class="material-icons text-4xl">
						list
					</button>
					<div class="font-bold text-2xl pl-3">Expense Tracker</div>
				</div>
				
				<!-- For large devices -->
				<div class="hidden md:block text-2xl font-bold p-3">
					<%= request.getAttribute("page") %>
				</div>
				
				<!-- Profile Photo -->
				<div class="flex">
					<% User userD = (User) session.getAttribute("user"); %>
					<%if(userD == null){ 
						response.sendRedirect(request.getContextPath() + "/login"); 
						return;
						}					
					%>
					<div class="flex items-center font-bold"><%= userD.getUsername() %></div>
					<div class="material-icons text-4xl p-3">account_circle</div>
				</div>
			</div>	
			
			<!-- Content -->
			<div class="grow border-2 rounded-md overflow-auto">
				
				<%
					String pageName = (String) request.getAttribute("page");
				
					if("Dashboard".equals(pageName)){
						%>
							<jsp:include page="dashboard.jsp"></jsp:include>
						<%
					} else if("Budgets".equals(pageName)){
						%>
							<jsp:include page="budget.jsp"></jsp:include>
						<%
					} else if("Expenses".equals(pageName)){
						%>
							<jsp:include page="expense.jsp"></jsp:include>
						<%
					} else if("BudgetDetails".equals(pageName)){
						%>
							<jsp:include page="budgetDetails.jsp"></jsp:include>
						<%
					} else if("Profile".equals(pageName)){
						%>
							<jsp:include page="profile.jsp"></jsp:include>
						<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>
















