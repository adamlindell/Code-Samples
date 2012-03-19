package com.expensetracker.backingbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import com.expensetracker.delegate.ExpenseDelegate;
import com.expensetracker.dto.CategoryDTO;
import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.exception.ExpenseTrackerException;

/**
 * This bean backs the "Recently Entered" section of the application,
 * which displays a history of the most recently entered transactions.
 * 
 * @author Adam Lindell
 *
 */
public class RecentExpenseBackingBean extends ExpenseTrackerBackingBean {
	private static final Integer DEFAULT_RESULTS_PER_PAGE = 10;
	
	private List<ExpenseDTO> recentExpenses;
	private Integer numResults;
	
	//Constructors
	/**
	 * 
	 */
	public RecentExpenseBackingBean (){
		numResults = DEFAULT_RESULTS_PER_PAGE;
		initializeRecentExpenses();
	}
	
	//Public Methods
	/**
	 * When the user chooses a new number of results to display, 
	 * update the recent expenses list accordingly.
	 * @param vce
	 */
	public void resultsChanged (ValueChangeEvent vce){
		setNumResults(Integer.parseInt(vce.getNewValue().toString()));
		initializeRecentExpenses();
	}
	
	/**
	 * On save, refresh brings current the data that may be affected by an application action. 
	 */
	public void refresh() {
		initializeRecentExpenses();
	}
	
	//Properties
	/**
	 * 
	 * @return The list of recently entered expenses, ordered by create timestamp.
	 */
	public List<ExpenseDTO> getRecentExpenses() {
		return recentExpenses;
	}
	
	/**
	 * 
	 * @param recentExpenses
	 */
	public void setRecentExpenses(List<ExpenseDTO> recentExpenses) {
		this.recentExpenses = recentExpenses;
	}

	/**
	 * 
	 * @return the number of results to be displayed. 
	 */
	public Integer getNumResults() {
		return numResults;
	}
	
	/**
	 * 
	 * @param numResults
	 */
	public void setNumResults(Integer numResults) {
		this.numResults = numResults;
	}
	
	/**
	 * Retrieve recent expenses.
	 */
	private void initializeRecentExpenses(){
		ExpenseDelegate expenseDelegate = new ExpenseDelegate();
		try {
			recentExpenses = expenseDelegate.getRecentExpenses( numResults );
		} 
		catch (ExpenseTrackerException e) {
			addError(ExpenseTrackerBackingBean.UserFacingMessage.DB_ERROR);
		}
	}
}