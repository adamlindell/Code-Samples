package com.expensetracker.backingbeans;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.expensetracker.delegate.BudgetDelegate;
import com.expensetracker.dto.BudgetDTO;
import com.expensetracker.dto.BudgetMonthDTO;
import com.expensetracker.exception.ExpenseTrackerException;

/**
 * This bean backs the "Monthy Budget" section of the application.
 * 
 * @author Adam Lindell
 */
public class BudgetBackingBean extends ExpenseTrackerBackingBean {
	private List<SelectItem> availableBudgetMonths;
	private String budgetMonth;
	private List<BudgetDTO> budgetItems;
	
	//Constructors
	/**
	 * 
	 */
	public BudgetBackingBean (){
		initializeAvailableBudgetMonths();
		initializeBudgetMonth();
		initializeBudgetItems();
	}
	
	//Public Methods
	/**
	 * When the month drop down changes, update the budget 
	 * items to reflect the current month. 
	 * 
	 * @param vce ValueChangeEvent from Faces.
	 */
	public void monthChanged (ValueChangeEvent vce){
		setBudgetMonth(vce.getNewValue().toString());
		initializeBudgetItems();
	}
	
	/**
	 * On save, refresh brings current the data that may be affected by an application action. 
	 */
	public void refresh() {
		initializeBudgetItems();
	}
	
	//Properties
	/**
	 * 
	 * @return <code>SelectItems</code> representing each month with a budget
	 * present in the system.
	 */
	public List<SelectItem> getAvailableBudgetMonths() {		
		return availableBudgetMonths;
	}
	
	/**
	 * 
	 * @return The selected month for which to display a budget.
	 */
	public String getBudgetMonth() {		
		return budgetMonth;
	}
	
	/**
	 * 
	 * @param budgetMonth
	 */
	public void setBudgetMonth(String budgetMonth) {
		this.budgetMonth = budgetMonth;
	}
	
	/**
	 * 
	 * @return The list of budgeted categories for this month 
	 * as well as the current amount spent.
	 */
	public List<BudgetDTO> getBudgetItems() {
		return budgetItems;
	}
	
	/**
	 * 
	 * @param budgetItems
	 */
	public void setBudgetItems(List<BudgetDTO> budgetItems) {
		this.budgetItems = budgetItems;
	}
	
	//Private Methods
	/**
	 * Construct SelectItem label displaying the current Month and Year together. 
	 * 
	 * @param month
	 * @param year
	 * @return user facing label
	 */
	private String buildLabel ( Integer month, Integer year ){
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
		String[] months = dateFormatSymbols.getMonths();
		StringBuilder result = new StringBuilder();
		result.append(months[month - 1]);
		result.append(" " );
		result.append( year );
		return result.toString();
	}
	
	/**
	 * Retrieve months that have at least one budgeted category 
	 * and convert them to select items for the drop down.
	 */
	private void initializeAvailableBudgetMonths() {
		this.availableBudgetMonths = new ArrayList<SelectItem>();
		BudgetDelegate budgetDelegate = new BudgetDelegate();
		List<BudgetMonthDTO> results = null;
		try{
			results = budgetDelegate.getAvailableBudgetMonths();
		} 
		catch ( ExpenseTrackerException ete ) {
			addError(ExpenseTrackerBackingBean.UserFacingMessage.DB_ERROR);
		}
		
		for ( BudgetMonthDTO budgetDTO : results ){
			SelectItem si = new SelectItem();
			si.setLabel ( buildLabel(budgetDTO.getMonth(), budgetDTO.getYear()) );
			si.setValue( budgetDTO.getMonth().toString() + budgetDTO.getYear().toString() );
			availableBudgetMonths.add(si);
		}
	}

	/**
	 * Use today's month to initialize the table.  
	 * If the current month does not have a budget, the default will be selected.
	 */
	private void initializeBudgetMonth() {
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(calendar.MONTH + 1);
		sb.append(calendar.get(Calendar.YEAR));
		budgetMonth = sb.toString();
	}
	
	/**
	 * Retrieve budgeted categories for this month as well as 
	 * the month-to-date expenses in each.
	 */
	private void initializeBudgetItems() {
		BudgetDelegate budgetDelegate = new BudgetDelegate();
		final int YEAR_OFFSET = 4;
		String monthBudgetId = getBudgetMonth();
		int monthYearSplitIndex = monthBudgetId.length() - YEAR_OFFSET;
		Integer month = Integer.parseInt( monthBudgetId.substring(0, monthYearSplitIndex) );
		Integer year = Integer.parseInt( monthBudgetId.substring(monthYearSplitIndex) );
		try{
			this.budgetItems = budgetDelegate.getMonthlyBudgetItems(month, year);
		} 
		catch ( ExpenseTrackerException ete ) {
			addError(ExpenseTrackerBackingBean.UserFacingMessage.DB_ERROR);
		}
	}
}
