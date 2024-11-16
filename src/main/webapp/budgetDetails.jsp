<%@ page import="java.util.List" %>
<%@ page import="com.model.Budget" %>
<%@ page import="com.model.Expense" %>

<%
	@SuppressWarnings("unchecked")
	List<Expense> expenses = (List<Expense>) request.getAttribute("expenses");
	Budget budget = (Budget) request.getAttribute("budget");
	float totalExpense = (float) request.getAttribute("totalExpense");
	float remainingAmount = budget.getBudget_amount() - totalExpense;
	float fractalRed = totalExpense / budget.getBudget_amount() * 100;
	float fractalGreen = 100 - fractalRed;
%>

<div>
	<!-- Header -->
	<div class="flex justify-between border-2 items-center">
		<a href="${pageContext.request.contextPath}/budget" class="flex space-x-2 font-bold text-xl px-3 items-center">
			<span class="material-icons text-4xl">keyboard_backspace</span>
			<h1 class="leading-10"><%= budget.getBudget_name() %></h1>
		</a>
		<div class="flex">
			<button onclick="toggle('confirm')" class="grow flex bg-red-500 text-white font-bold px-3 py-2 m-2 rounded-lg text-center">
				<span class="material-icons">delete</span>Delete
			</button>
			<button onclick="toggle('edit')" class="grow flex bg-blue-500 text-white font-bold px-3 py-2 m-2 rounded-lg text-center">
				<span class="material-icons">edit</span>Edit
			</button>
		</div>
	</div>
	
	<!-- Edit Budget Modal -->
	<div id="edit" class="hidden absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-20">
    	<div class="w-screen h-screen flex justify-center items-center backdrop-blur-sm backdrop-brightness-90">
	    	<div class="w-[350px] border border-black p-2 rounded-lg m-3 tab bg-white">
	          <h1 class="text-center font-bold text-2xl mt-5">Create new Budget</h1>
	          
	          <!-- Form for creating Budget -->
	          <!-- Add a handler for this form -->
	          <form action="${pageContext.request.contextPath}/editBudget" method="post" class="flex flex-col p-4">
	          	
	          	<!-- Name of the budget -->
	            <label for="name">Name</label>
	            <input type="text" name="budget_name" class="border-2 border-slate-400 p-2 mb-3 rounded-md" value="<%= budget.getBudget_name() %>" required/>
	            
	            <!-- BudgetID -->
	            <input type="hidden" name="budgetId" value="<%=budget.getBudget_id() %>" required>
	            
	            <!-- Amount of the budget -->
	            <label for="amount">Amount</label>
	            <input type="number" name="budget_amount" class="border-2 border-slate-400 p-2 mb-3 rounded-md" value="<%= budget.getBudget_amount() %>" required/>
	            
	            <!-- Submit Button -->
	            <button type="submit" class="bg-black text-white font-bold rounded-md p-2 my-2">
	              Confirm Edit
	            </button>
	            
	            <!-- Close Button -->
	            <button onclick="toggle('edit')" type="reset" class="bg-black text-white font-bold rounded-md p-2 my-2">
	              close
	            </button>
	          </form>
	        </div>
        </div>
    </div>
	
	<!-- Confirmation Modal -->
	<div id="confirm" class="hidden absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-20 flex justify-center items-center">
		<div class="grow w-screen h-screen flex justify-center items-center backdrop-blur-sm backdrop-brightness-90">
    	<div class="w-[350px] border-2 border-red-500 p-4 rounded-md m-3 tab bg-white">
          <h1 class="text-left font-bold text-xl">Are you Sure?</h1>
          <p class="my-2">This action can not be undone. This budget and all the expenses of this budget will be permanently deleted!</p>
          
          <!-- Form for deleting Budget -->
          <form action="${pageContext.request.contextPath}/deleteBudget" method="post" class="flex justify-end space-x-2">
          	<!-- Sending Budget Id -->
          	<input type="hidden" name="budgetId" value="<%= budget.getBudget_id() %>">
            
            <!-- Submit Button -->
            <button type="submit" class="bg-black text-white font-bold rounded-md p-2 my-2">
              Delete
            </button>
            
            <!-- Close Button -->
            <button onclick="toggle('confirm')" type="reset" class="bg-black text-white font-bold rounded-md p-2 my-2">
              close
            </button>
          </form>
        </div>
        </div>
    </div>
	
	<!-- Cards and table-->
	<div class="flex flex-col h-full w-full">
      <div class="flex flex-col md:flex-col lg:flex-row justify-between">
        <div class="grow-[3] flex">
          <!-- Total Budget Card -->
          <div class="grow flex flex-col justify-center border-2 border-black bg-green-200 m-3 h-[180px] md:h-[240px] rounded-md py-10 text-center">
          	<h1 class="text-2xl font-bold py-1">Total Budget</h1>
          	<h1 class="text-3xl font-extrabold py-1">$<%= budget.getBudget_amount() %></h1>
          </div>
          
          <!-- Left Amount Card -->
          <div class="grow flex flex-col justify-center border-2 border-black m-3 h-[180px] md:h-[240px] rounded-md py-10 text-center relative -z-20">
          	<h1 class="text-2xl font-bold py-1">Budget Left</h1>
          	<h1 class="text-3xl font-extrabold py-1">$<%= remainingAmount %></h1>
          	<div class="absolute top-0 h-[<%= fractalRed %>%] w-full bg-red-200 rounded-t-md -z-10"></div>
          	<div class="absolute bottom-0 h-[<%= fractalGreen %>%] w-full bg-green-200 rounded-b-md -z-10"></div>
          </div>
          
          <!-- Spent Amount Card -->
          <div class="grow flex flex-col justify-center border-2 border-black bg-red-200 m-3 h-[180px] md:h-[240px] rounded-md py-10 text-center">
          	<h1 class="text-2xl font-bold py-1">Total Spent</h1>
          	<h1 class="text-3xl font-extrabold py-1">$<%= totalExpense %></h1>
          </div>
        </div>
        
        <!--Add New Expense Form -->
        <div class="grow-[2] border-2 border-black m-3 p-3 rounded-md">
        	<h1 class="text-center font-extrabold text-xl">Add new Expense</h1>
        	<form action="addExpense" method="post" class="flex flex-col">
        		<label for="expense-name">Expense Name</label>
        		<input id="expense-name" type="text" name="expenseName" class="border-2 border-slate-400 p-2 mb-3 rounded-md" required>
        		
        		<label for="expense-amount">Expense Amount</label>
        		<input id="expense-amount" type="number" name="expenseAmount" min="0" max="<%= remainingAmount %>" class="border-2 border-slate-400 p-2 mb-3 rounded-md" required>
        		
        		<input type="number" name="budgetId" value="<%= budget.getBudget_id() %>" hidden="true">
        		
        		<button type="submit" class="bg-black text-white font-bold rounded-md p-2 my-2">Add Expense</button>
        	</form>
        </div>
      </div>
	  <!--Expenses Table -->
      <div class="grow m-3 rounded-md">
      	 <div class="overflow-x-auto">
          <table class="min-w-full bg-white border border-gray-200 rounded-lg">
            <thead>
              <tr class="w-full bg-gray-100 border-b border-gray-300">
                <th class="text-center py-3 px-4 font-semibold">Expense Name</th>
                <th class="text-center py-3 px-4 font-semibold">Amount</th>
                <th class="text-center py-3 px-4 font-semibold">Date</th>
                <th class="text-center py-3 px-4 font-semibold">Action</th>
              </tr>
            </thead>
            <tbody>
             <% for(Expense expense : expenses){ %>
              <tr class="border-b hover:bg-gray-50">
                <td class="text-center py-3 px-4"><%= expense.getExpense_name() %></td>
                <td class="text-center py-3 px-4">$<%= expense.getExpense_amount() %></td>
                <td class="text-center py-3 px-4"><%= expense.getExpense_created_at() %></td>
                <td class="py-3 px-4 text-center">
                  <form action="${pageContext.request.contextPath}/deleteExpense" method="post" class="inline-block">
              		<input type="hidden" name="expenseId" value="<%= expense.getExpense_id() %>">
              		<input type="hidden" name="budgetId" value="<%= budget.getBudget_id() %>">
              		<button type="submit" class="text-red-500 hover:underline text-bold"><span class="material-icons">delete</span></button>
          		  </form>
                </td>
              </tr>
              <%} %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
</div>





