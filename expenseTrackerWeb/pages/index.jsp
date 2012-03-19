<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>

<f:view>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.1 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>Welcome to Expense Tracker</title>
		<link rel="stylesheet" href="../css/main.css" type="text/css" />
		<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css" type="text/css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js" type="text/javascript"></script>
		<script type="application/javascript">
			$(function (){ 
				$("#expenseDate").datepicker();
			});
		</script>
	</head>
	<body>
		<h:form id='expsenseTracker'>
			<div id="header">
				<h1>Expense Tracker!</h1>
				<p> 
					Stay within your budget!  
					Enter your receipts below and track how you're doing.
				</p> 
				<t:messages id="messages" forceId="true" infoClass='info' errorClass='error' />
			</div>
			<ul id="sections">
				<li id='enterExpense'><jsp:include page="enterExpense.jsp" /></li>
				<li id='recentExpense'><jsp:include page="recentExpenses.jsp" /></li>
				<li id='budget'><jsp:include page="budget.jsp" /></li>
			</ul>
		</h:form>
	</body>
</html>
</f:view>