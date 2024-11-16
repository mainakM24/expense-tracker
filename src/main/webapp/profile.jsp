<%@ page import="com.model.User" %>
<%
	User user = (User) request.getAttribute("User");
%>
<!-- Container -->
<div class="font-mono">
	<div class="flex flex-col items-center">
	
		<!-- Form for updating Personal details -->
		<form action="${pageContext.request.contextPath}/updateUser" method="post" class="w-[400px] mx-auto pt-5">
			<fieldset class="border-2 border-slate-500 rounded-md">
				<!-- Heading -->
				<legend class="m-2 text-xl font-bold">Profile Details</legend>
				
				<!-- User name -->
				 <div class="p-3 flex justify-between">
				 	<label for="uname" class="grow-[1] font-bold">Username</label>
				 	<input id="uname" type="text" name="uname" value="<%= user.getUsername() %>" class="grow-[2] p-2 border border-slate-300 rounded-md ">
				 </div>
				 
				 <!-- Email -->
				 <div class="p-3 flex justify-between">
				 	<label for="email" class="grow-[1] font-bold">Email</label>
				 	<input id="email" type="email" name="email" value="<%= user.getEmail() %>" class="grow-[2] p-2 border border-slate-300 rounded-md ">
				 </div>
				 
				 <!-- Submit Button -->
				 <button class="bg-black font-bold m-2 p-3 text-center text-white rounded-md">Save Changes</button>
				 
				 <% if(request.getAttribute("error") != null){ %>
					<p class="text-center font-bold text-md text-red-500"><%= request.getAttribute("error") %></p>
				 <% } %>
			</fieldset>
		</form>
		
		<!-- Form for updating password -->
		<form action="${pageContext.request.contextPath}/updatePassword" method="post" class="w-[400px] mx-auto pt-5">
			<fieldset class="border-2 border-slate-500 rounded-md">
				<!-- Heading -->
				<legend class="m-2 text-xl font-bold">Change Password</legend>
				
				<!-- Old Password -->
				 <div class="p-3 flex justify-between">
				 	<label for="oldPass" class="grow-[1] font-bold">Old Password</label>
				 	<input id="oldPass" type="password" name="oldPass" class="grow-[2] p-2 border border-slate-300 rounded-md ">
				 </div>
				 
				 <!-- New Password -->
				 <div class="p-3 flex justify-between">
				 	<label for="newPass" class="grow-[1] font-bold">New Password</label>
				 	<input id="newPass" type="password" name="newPass" class="grow-[2] p-2 border border-slate-300 rounded-md ">
				 </div>
				 
				 <!-- Confirm Password --> 
				 <div class="p-3 flex justify-between">
				 	<label for="cnfPass" class="grow-[1] font-bold">Confirm Password</label>
				 	<input id="cnfPass" type="password" name="cnfPass" class="grow-[2] p-2 border border-slate-300 rounded-md ">
				 </div>
				  
				 
				 <!-- Submit Button -->
				 <button class="bg-black font-bold m-2 p-3 text-center text-white rounded-md">Save Changes</button>
				 <% if(request.getAttribute("passError") != null){ %>
					<p class="text-center font-bold text-md text-red-500"><%= request.getAttribute("passError") %></p>
				 <% } %>
				 
			</fieldset>
		</form>
		
		<!-- Delete Account button -->
		<button onclick="toggle('confirm_del')" class="w-[400px] bg-red-500 hover:bg-red-600 rounded-md font-bold text-white text-center flex justify-center items-center mt-3 p-3">
			<span class="material-icons mx-1">delete</span>
			<span class="mx-1 text-lg">Delete Account</span>
		</button>
		
		<% if(request.getAttribute("deleteError") != null){ %>
			<p class="text-center font-bold text-md text-red-500"><%= request.getAttribute("deleteError") %></p>
		<% } %>
		
		
		
		<!-- Confirmation Modal -->
		<div id="confirm_del" class="hidden absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-20 flex justify-center items-center">
			<div class="grow w-screen h-screen flex justify-center items-center backdrop-blur-sm backdrop-brightness-90">
		    	<div class="w-[350px] border-2 border-red-500 p-4 rounded-md m-3 tab bg-white">
		          <h1 class="text-left font-bold text-xl">Are you Sure?</h1>
		          <p class="my-2">This action can not be undone. This budget and all the expenses of this budget will be permanently deleted!</p>
		          
		          <!-- Form for deleting Budget -->
		          <form action="${pageContext.request.contextPath}/deleteAccount" method="post" class="flex justify-end space-x-2">
		          	<!-- Sending User Id -->
		          	<input type="hidden" name="userId" value="<%= user.getId() %>">
		            
		            <!-- Submit Button -->
		            <button type="submit" class="bg-black text-white font-bold rounded-md p-2 my-2">
		              Delete
		            </button>
		            
		            <!-- Close Button -->
		            <button onclick="toggle('confirm_del')" type="reset" class="bg-black text-white font-bold rounded-md p-2 my-2">
		              close
		            </button>
		          </form>
		        </div>
	        </div>
	    </div>
	    
	</div>
</div>







