<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<f:subview id="recentExpenses">
	<h2>Recently Added</h2>
	<h:outputLabel for="resultsPerPage" value="Number of Results:"/>
	<t:selectOneMenu 
			id="resultsPerPage" 
			forceId="true"
			onchange="submit()"
			valueChangeListener="#{recentExpenseBackingBean.resultsChanged}" 
			value="#{recentExpenseBackingBean.numResults}">
		<f:selectItem itemValue="10" />
		<f:selectItem itemValue="50" />
		<f:selectItem itemValue="100" />
	</t:selectOneMenu>
    <t:dataTable
			id="recentExpenseList"
			var="results"
			value="#{recentExpenseBackingBean.recentExpenses}"
			rowClasses="odd, even">
		<t:column>
		    <f:facet name="header">
		    	<h:outputText value="Expense Date" />
		    </f:facet>
		 	<h:outputText value="#{results.expenseDate}">
		 		<f:convertDateTime pattern="MM/dd/yyyy" />
		 	</h:outputText>
		 </t:column>
		<t:column>
	        <f:facet name="header">
	        	<h:outputText value="Category" />
	        </f:facet>
	        <h:outputText value="#{results.budgetCategoryDescription}" />
		</t:column>
		<t:column styleClass="money">
	        <f:facet name="header">
	        	<h:outputText value="Amount" />
	        </f:facet>
	        <h:outputText value="#{results.expenseAmount}">
	        	<f:convertNumber maxFractionDigits="2" currencySymbol="$" type="currency" />
	        </h:outputText>
		</t:column>
		<t:column>
	        <f:facet name="header">
	        	<h:outputText value="Comment" />
	        </f:facet>
	        <h:outputText value="#{results.comments}" />
		</t:column>
    </t:dataTable>
</f:subview>