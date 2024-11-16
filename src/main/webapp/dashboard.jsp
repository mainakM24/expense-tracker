<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.model.Budget" %>
<%@ page import="com.model.Expense" %>
<%@ page import="java.util.Collections" %>

<%
	@SuppressWarnings("unchecked")
	List<Budget> budgets = (List<Budget>) request.getAttribute("budgets");
	@SuppressWarnings("unchecked")
	List<Expense> expenses = (List<Expense>) request.getAttribute("expenses");
	float totalBudget = (float) request.getAttribute("totalBudget");
	float totalExpense = (float) request.getAttribute("totalExpense");
	int budgetCount = budgets.size();
	
	//Data for Monthly Distribution of Expenses
	Map<String, Float> monthlyExpenses = new HashMap<>();
	SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
	
	for (Expense expense : expenses) {
	    Timestamp timestamp = expense.getExpense_created_at();
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(timestamp.getTime());
	    String month = monthFormat.format(cal.getTime());

	    // Sum up expenses per month
	    monthlyExpenses.put(month, monthlyExpenses.getOrDefault(month, 0f) + expense.getExpense_amount());
	}
%>

<script type="text/javascript">

      google.charts.load('current', {'packages':['corechart']});
      window.onresize = drawChart;
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
    	  
    	/////////////////////////////////
		//data fitting for budget chart//
		/////////////////////////////////
        var budgetData = new google.visualization.DataTable();
        budgetData.addColumn('string', 'Budget');
        budgetData.addColumn('number', 'Amount');
        
        budgetData.addRows([
            <% for (Budget budget : budgets) { %>
              ['<%= budget.getBudget_name() %>', <%= budget.getBudget_amount() %>],
            <% } %>
          ]);
        
        var budgetOptions = {
        			   'title':'Budget Amount Distribution',
                       'width':290,
                       'height':350,
                       legend: 'bottom',
                       sliceVisibilityThreshold: .01};

        var budgetChart = new google.visualization.PieChart(document.getElementById('budget_chart'));
        budgetChart.draw(budgetData, budgetOptions);
        
        ////////////////////////////
        //data fitting for expense//
        ////////////////////////////
        var expenseData = new google.visualization.DataTable();
        expenseData.addColumn('string', 'Expense');
        expenseData.addColumn('number', 'Amount');
        
        expenseData.addRows([
            <% for (Expense expense : expenses) { %>
              ['<%= expense.getExpense_name() %>', <%= expense.getExpense_amount() %>],
            <% } %>
          ]);
        
        var expenseOptions = {
        			   'title':'Expense Amount Distribution',
                       'width':290,
                       'height':350,
                       legend: 'bottom',
                       sliceVisibilityThreshold: .01};

        var expenseChart = new google.visualization.PieChart(document.getElementById('expense_chart'));
        expenseChart.draw(expenseData, expenseOptions);
        
        /////////////////////////////////
        //data fitting for column chart//
        /////////////////////////////////
        const parentDiv = document.getElementById('month_chart').parentElement;
        const chartWidth = parentDiv.clientWidth - 10;
        
        var monthlyData = new google.visualization.DataTable();
        monthlyData.addColumn('string', 'Month');
        monthlyData.addColumn('number', 'Total Expense');

        monthlyData.addRows([
          <% for (Map.Entry<String, Float> entry : monthlyExpenses.entrySet()) { %>
            ['<%= entry.getKey() %>', <%= entry.getValue() %>],
          <% } %>
        ]);

        var monthlyOptions = {
			          'title': 'Month-wise Expense Distribution',
			          'height': 260,
			          'width' : chartWidth,
			          legend: 'none'
        };

        var monthlyChart = new google.visualization.ColumnChart(document.getElementById('month_chart'));
        monthlyChart.draw(monthlyData, monthlyOptions);
      }
</script>

<div class="h-full flex flex-col xl:flex-row p-1">
	<!-- Main Pane -->
	<div class="lg:grow-[7] lg:h-full">
		<!-- 3 Cards -->
		<div class="flex">
			<!-- Total Budget Card -->
			<div class="h-[144px] grow border-2 border-slate-700 rounded-md m-1 text-center flex flex-col justify-center">
				<h1 class="text-xl font-extrabold">Total Budget</h1>
				<h1 class="text-3xl font-extrabold text-green-500">$<%= totalBudget %></h1> 
			</div>
			
			<!-- Total Expense Card-->
			<div class="h-[144px] grow border-2 border-slate-700 rounded-md m-1 text-center flex flex-col justify-center">
				<h1 class="text-xl font-extrabold">Total Expense</h1>
				<h1 class="text-3xl font-extrabold text-red-500">$<%= totalExpense %></h1>
			</div>
			
			<!-- Budget Count Card -->
			<div class="h-[144px] grow border-2 border-slate-700 rounded-md m-1 text-center flex flex-col justify-center">
				<h1 class="text-xl font-extrabold">Budget Count</h1>
				<h1 class="text-3xl font-extrabold text-cyan-500"><%= budgetCount %></h1>
			</div>
		</div>
		<!-- 2 Charts -->
		<div class="flex flex-col md:flex-row">
		
			<!-- Budget Distribution Chart -->
			<div class="md:grow h-[360px] border-2 border-slate-700 rounded-md m-1 items-center">
				<div id="budget_chart" class="flex justify-center items-center -z-10"></div>
			</div>
			
			<!-- Expense Distribution Chart -->
			<div class="md:grow h-[360px] border-2 border-slate-700 rounded-md m-1 items-center">
				<div id="expense_chart" class="flex justify-center items-center"></div>
			</div>
		</div>
		<!-- Column Chart to show Month wise distribution-->
		<div class="flex justify-center items-center h-[264px] border-2 border-slate-700 rounded-md m-1">
			<div id="month_chart" class="grow flex justify-center items-center"></div>
		</div>
	</div>
	
	<!-- Side Pane -->
	<div class="xl:grow-[3] xl:h-full flex flex-row xl:flex-col">
	<!-- 2 Cards for Latest Entries -->
		<!-- Card 1 for Latest Expenses-->
		<div class="grow border-2 border-slate-700 rounded-md h-[250px] m-1 overflow-auto">
			<div class="text-center text-xl font-bold p-2 bg-red-200 sticky top-0">Latest Expenses</div>
			<div class="overflow-auto">
	          <table class="min-w-full bg-white border-2 border-gray-300 rounded-lg">
	            <thead>
	              <tr class="w-full bg-gray-100 border-b border-gray-300">
	                <th class="text-center py-3 px-4 font-semibold">Expense Name</th>
	                <th class="text-center py-3 px-4 font-semibold">Amount</th>
	              </tr>
	            </thead>
	            <tbody>
	             <% for(Expense expense : expenses){ %>
	              <tr class="border-b hover:bg-gray-50">
	                <td class="text-center py-3"><%= expense.getExpense_name() %></td>
	                <td class="text-center py-3">$<%= expense.getExpense_amount() %></td>
	              </tr>
	              <%} %>
	            </tbody>
	          </table>
        	</div>
		</div>
		
		<!-- Card 2 for Latest Budgets-->
		<div class="grow border-2 border-slate-700 rounded-md h-[250px] m-1 overflow-auto">
			<div class="text-center text-xl font-bold p-2 bg-green-200 sticky top-0">Latest Budgets</div>
			<div class="overflow-auto">
	          <table class="min-w-full bg-white border-2 border-gray-300 rounded-lg">
	            <thead>
	              <tr class="w-full bg-gray-100 border-b border-gray-300">
	                <th class="text-center py-3 px-4 font-semibold">Budget Name</th>
	                <th class="text-center py-3 px-4 font-semibold">Amount</th>
	              </tr>
	            </thead>
	            <tbody>
	             <% Collections.reverse(budgets); %>
	             <% for(Budget budget : budgets){ %>
	              <tr class="border-b hover:bg-gray-50">
	                <td class="text-center py-3"><%= budget.getBudget_name() %></td>
	                <td class="text-center py-3">$<%= budget.getBudget_amount() %></td>
	              </tr>
	              <%} %>
	            </tbody>
	          </table>
        	</div>
		</div>
	</div>
</div>








