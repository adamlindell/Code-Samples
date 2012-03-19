package com.expensetracker.backingbeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import com.expensetracker.delegate.BudgetDelegate;
import com.expensetracker.delegate.ExpenseDelegate;
import com.expensetracker.dto.CategoryDTO;
import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.exception.ExpenseTrackerException;

/**
 * This bean backs the form on which users enter new expenses.
 * 
 * @author Adam Lindell
 * 
 */
public class EnterExpenseBackingBean extends ExpenseTrackerBackingBean {
	private List<SelectItem> budgetCategories;
	private Integer budgetCategoryId;
	private String comments;
	private BigDecimal expenseAmount;
	private Date expenseDate;

	//Constructor
	/**
	 * Set up the information required for a blank form.
	 */
	public EnterExpenseBackingBean(){
		initializeBudgetCategories();
	}
	
	//Actions
	/**
	 * Attempt to save the new expense values on the form.  
	 * If invalid data exists, abort save and display errors.
	 * Otherwise, persist row and display success message.
	 * 
	 * @return "Success" if expense is saved successfully.
	 *         "Invalid" if the form is invalid.
	 *         "Error" if we experience any exceptions from the delegate.
	 *         Currently, the user returns to the same page regardless of the result.
	 */
	public String save(){
		if ( !isValid() )
			return "Invalid";
		ExpenseDelegate expenseDelegate = new ExpenseDelegate();
		try {
			expenseDelegate.saveExpense( buildExpenseDTO() );
			addInfo(ExpenseTrackerBackingBean.UserFacingMessage.SAVED_SUCCESSFULLY);
		} 
		catch (ExpenseTrackerException e) {
			addError (ExpenseTrackerBackingBean.UserFacingMessage.DB_ERROR);
			return "Error";
		}
		
		cleanUpForm();
		return "Success";
	}

	/**
	 * Erase form values and update the budget and recent 
	 * expense sections of the page to show the newly saved expense.
	 */
	private void cleanUpForm() {
		budgetCategoryId = null;
		comments = null;
		expenseAmount = null;
		expenseDate = null;
		
		Application application = FacesContext.getCurrentInstance().getApplication();
		RecentExpenseBackingBean rebb = (RecentExpenseBackingBean) 
				application.evaluateExpressionGet(FacesContext.getCurrentInstance(), 
						"#{recentExpenseBackingBean}", 
						RecentExpenseBackingBean.class);
		rebb.refresh();
		
		BudgetBackingBean bbb = (BudgetBackingBean) 
				application.evaluateExpressionGet(FacesContext.getCurrentInstance(), 
						"#{budgetBackingBean}", 
						BudgetBackingBean.class);
		bbb.refresh();
	}
	
	//Properties
	/**
	 * @return All possible budget categories for the current month, 
	 * represented as an alphabetical <code>List</code> of <code>SelectItem</code>s.  
	 */
	public List<SelectItem> getBudgetCategories() {		
		return budgetCategories;
	}

	/**
	 * @return Selected category of the expense.
	 */
	public Integer getBudgetCategoryId() {
		return budgetCategoryId;
	}

	/**
	 * 
	 * @param budgetCategoryId
	 */
	public void setBudgetCategoryId(Integer budgetCategoryId) {
		this.budgetCategoryId = budgetCategoryId;
	}

	/**
	 * @return Optional comments about the expense.
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * 
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return Amount of the expense.
	 */
	public BigDecimal getExpenseAmount() {
		return expenseAmount;
	}
	
	/**
	 * 
	 * @param expenseAmount
	 */
	public void setExpenseAmount(BigDecimal expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	
	/**
	 * 
	 * @return Date of the expense.
	 */
	public Date getExpenseDate() {
		return expenseDate;
	}
	
	/**
	 * 
	 * @param expenseDate
	 */
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	//Private Methods
	/**
	 * Using the form data provided, create a DTO 
	 * to hand over to the delegate for saving.
	 *  
	 * @return <code>ExpenseDTO</code> containing the information to save.
	 */
	private ExpenseDTO buildExpenseDTO(){
		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setBudgetCategoryId(getBudgetCategoryId());
		expenseDTO.setComments(getComments());
		expenseDTO.setExpenseDate(getExpenseDate());
		expenseDTO.setExpenseAmount(getExpenseAmount());
		return expenseDTO;
	}
	
	/**
	 * Populates the drop down on the expense entry form.
	 */
	private void initializeBudgetCategories() {
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		
		//Retrieve available category DTOs 
		ExpenseDelegate expenseDelegate = new ExpenseDelegate();
		try{
			result = expenseDelegate.getCurrentCategories();
		} catch (ExpenseTrackerException ete) {
			//super.addMessage(UtilityBackingBean.MSG_DB_ERROR);
		}
		
		//Turn DTOs into list items for display
		budgetCategories = new ArrayList<SelectItem>();
		for ( CategoryDTO budgetCategoryDTO : result){
			SelectItem si = new SelectItem();
			si.setValue(budgetCategoryDTO.getCategoryId());
			si.setLabel(budgetCategoryDTO.getCategoryDescription());
			budgetCategories.add(si);
		}
	}
	
	/**
	 * Ensure that all required information is filled out and correct.
	 * If invalid data exists, create messages to the user explaining the problem.
	 * 
	 * @return - true if data is valid for saving
	 *           false if data is not valid
	 */
	private boolean isValid (){
		boolean valid = true;
		
		if ( getExpenseDate() == null ){
			addError ( "Expense Date", ExpenseTrackerBackingBean.UserFacingMessage.REQUIRED);
			valid = false;
		}
		if ( getExpenseAmount() == null || BigDecimal.ZERO.compareTo(getExpenseAmount()) >= 0){
			addError ( "Expense Amount", ExpenseTrackerBackingBean.UserFacingMessage.REQUIRED_POSTIVE);
			valid = false;
		}
		
		return valid;
	}
}
