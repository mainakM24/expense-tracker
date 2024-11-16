<%@ page import="java.util.List" %>
<%@ page import="com.model.Budget" %>
<%@ page import="com.model.Expense" %>
<%@ page import="com.dao.BudgetDAO" %>
<%
	@SuppressWarnings("unchecked")
	List<Expense> allExpenses = (List<Expense>) request.getAttribute("allExpenses");
%>

<div>
	<h1 class="font-bold text-xl m-3">Latest Expenses</h1>
	<div class="overflow-x-auto p-2">
          <table class="min-w-full bg-white border-2 border-gray-300 rounded-lg">
            <thead>
              <tr class="w-full bg-gray-100 border-b border-gray-300">
                <th class="text-center py-3 px-4 font-semibold">Expense Name</th>
                <th class="text-center py-3 px-4 font-semibold">Budget Name</th>
                <th class="text-center py-3 px-4 font-semibold">Amount</th>
                <th class="text-center py-3 px-4 font-semibold">Date</th>
              </tr>
            </thead>
            <tbody>
             <% for(Expense expense : allExpenses){ %>
              <tr class="border-b hover:bg-gray-50">
                <td class="text-center py-3 px-4"><%= expense.getExpense_name() %></td>
                <td class="text-center py-3 px-4"><%= BudgetDAO.getBudgetById(String.valueOf(expense.getBudget_id())).getBudget_name() %></td>
                <td class="text-center py-3 px-4">$<%= expense.getExpense_amount() %></td>
                <td class="text-center py-3 px-4"><%= expense.getExpense_created_at() %></td>
              </tr>
              <%} %>
            </tbody>
          </table>
        </div>
</div>