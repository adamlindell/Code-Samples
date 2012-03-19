<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<f:subview id="budget">
	<h2>Monthly Budget</h2>
	<h:outputLabel value="Month:" for="budgetMonth" />
	<t:selectOneMenu 
			id="budgetMonth" 
			forceId="true"
			onchange="submit()" 
			valueChangeListener="#{budgetBackingBean.monthChanged}"
			value="#{budgetBackingBean.budgetMonth}">
		<f:selectItems value="#{budgetBackingBean.availableBudgetMonths}" />
	</t:selectOneMenu> 
	<t:dataTable
			id="budgetItems"
			var="results"
			value="#{budgetBackingBean.budgetItems}"
			rowClasses="odd, even">
		<t:column>
		    <f:facet name="header">
		    	<h:outputText value="Category" />
		    </f:facet>
		 	<h:outputText value="#{results.budgetCategoryDescription}" />
		 </t:column>
		<t:column styleClass="money">
	        <f:facet name="header">
	        	<h:outputText value="Budgeted" />
	        </f:facet>
	        <h:outputText value="#{results.budgetAmount}">
	        	<f:convertNumber maxFractionDigits="2" currencySymbol="$" type="currency" />
	        </h:outputText>
		</t:column>
		<t:column styleClass="money">
	        <f:facet name="header">
	        	<h:outputText value="Spent" />
	        </f:facet>
	        <h:outputText value="#{results.totalSpentAmount}">
	        	<f:convertNumber maxFractionDigits="2" currencySymbol="$" type="currency" />
	        </h:outputText>
		</t:column>
		<t:column styleClass="money">
	        <f:facet name="header">
	        	<h:outputText value="Remaining" />
	        </f:facet>
	        <h:outputText value="#{results.budgetAmount - results.totalSpentAmount}">
	        	<f:convertNumber maxFractionDigits="2" currencySymbol="$" type="currency" />
	        </h:outputText>
		</t:column>
	</t:dataTable>
</f:subview>