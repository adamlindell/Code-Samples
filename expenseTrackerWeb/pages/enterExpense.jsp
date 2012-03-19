<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<f:subview id="enterExpense">
    <h2>Enter Expense</h2>
	<fieldset>
		<legend>Enter your expense information.</legend>
		<h:outputLabel for="expenseDate" value="Expense Date" />
		<t:inputText label="Expense Date" id="expenseDate" forceId="true" value="#{enterExpenseBackingBean.expenseDate}">
			<f:convertDateTime pattern="MM/dd/yyyy" />
		</t:inputText>
		<h:outputLabel for="expenseAmount" value="Expense Amount" />
		<t:inputText label="Expense Amount" id="expenseAmount" forceId="true" value="#{enterExpenseBackingBean.expenseAmount}" styleClass="money">
			<f:convertNumber maxFractionDigits="2" />
		</t:inputText>
		<h:outputLabel for="budgetCategory" value="Budget Category" />
		<t:selectOneMenu id="budgetCategory" forceId="true" value="#{enterExpenseBackingBean.budgetCategoryId}">
			<f:selectItems id="categoryList" value="#{enterExpenseBackingBean.budgetCategories}" />
		</t:selectOneMenu> 
		<h:outputLabel for="expenseComments" value="Comments" />
		<t:inputTextarea id="expenseComments" forceId="true" value="#{enterExpenseBackingBean.comments}" />
		<t:commandButton value="Save" action="#{enterExpenseBackingBean.save}" id="save" forceId="true" />
	</fieldset>
</f:subview>