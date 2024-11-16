<%@ page import="java.util.List" %>
<%@ page import="com.model.Budget" %>
<%@ page import="com.dao.ExpenseDAO" %>

<div class="grid grid-cols-[repeat(auto-fill,minmax(300px,1fr))] gap-2 justify-center p-5">
	
	<!-- Card Button for Creating new budget -->
	<button onclick="toggle('budget')" class="h-[120px] rounded-md flex flex-col justify-center items-center border-2 border-black border-dashed">
         <span class="text-xl font-bold text-center text-slate-500">+</span>
         <span class="text-xl font-bold text-center text-slate-500">Add Budget</span>
    </button>
    
    <!-- Modal for creating budget -->
    <div id="budget" class="hidden absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-20">
    	<div class="w-screen h-screen flex justify-center items-center backdrop-blur-sm backdrop-brightness-90">
	    	<div class="w-[350px] border border-black p-2 rounded-lg m-3 tab bg-white">
	          <h1 class="text-center font-bold text-2xl mt-5">Create new Budget</h1>
	          
	          <!-- Form for creating Budget -->
	          <!-- Add a handler for this form -->
	          <form action="addBudget" method="post" class="flex flex-col p-4">
	          	
	          	<!-- Name of the budget -->
	            <label for="name">Name</label>
	            <input type="text" name="budget_name" class="border-2 border-slate-400 p-2 mb-3 rounded-md" required/>
	            
	            <!-- Amount of the budget -->
	            <label for="amount">Amount</label>
	            <input type="number" name="budget_amount" class="border-2 border-slate-400 p-2 mb-3 rounded-md" required/>
	            
	            <!-- Submit Button -->
	            <button type="submit" class="bg-black text-white font-bold rounded-md p-2 my-2">
	              Save
	            </button>
	            
	            <!-- Close Button -->
	            <button onclick="toggle('budget')" type="reset" class="bg-black text-white font-bold rounded-md p-2 my-2">
	              close
	            </button>
	          </form>
	        </div>
        </div>
    </div>
    <!--and access each one through <a> like budget/23
    and load a new screen, so need create a new page, add that to home tiles JSP, and need a back button for getting back to budget page-->
    <!-- Need to create a dynamic route like /budget/* -->
    <%
    	@SuppressWarnings("unchecked")
    	List<Budget> budgets = (List<Budget>) request.getAttribute("budgets");
    	if(budgets != null && !budgets.isEmpty()){
    		for(Budget budget: budgets){
    			float totalSpent = ExpenseDAO.getTotalExpensesByBudgetId(budget.getBudget_id());
    			float remainingAmount = budget.getBudget_amount() - totalSpent;
    			float fractal = totalSpent / budget.getBudget_amount();
    %>
    	<a href="budgetDetails/<%=budget.getBudget_id() %>" class="border-2 border-slate-400 hover:bg-gray-100 h-[120px] rounded-md block">
    	
    		<div class="flex flex-col justify-between h-full p-3">
    			<div class="flex justify-between">
    				<div class="font-bold"><%=budget.getBudget_name() %></div>
    				<div class="font-bold">$<%=budget.getBudget_amount() %></div>
    			</div>
    			<div>
    				<div class="flex justify-between">
	    				<small>Spent:$<%= totalSpent %></small>
	    				<small>Remaining:$<%= remainingAmount %></small>
    				</div>
    				<meter value="<%= fractal %>" optimum="0" low="0.3" high="0.7" class="w-full rounded-md" ></meter>
    			</div>
    		</div>
    	</a>
    <%} %>
    <%} %>	
</div>












