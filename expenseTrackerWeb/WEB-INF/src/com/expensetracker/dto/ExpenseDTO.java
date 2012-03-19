package com.expensetracker.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents an expense incurred within a budgeted category.
 * 
 * @author Adam Lindell
 * @see ExpenseDelegate
 */
public class ExpenseDTO {
	private Integer budgetCategoryId;
	private String budgetCategoryDescription;
	private String comments;
	private BigDecimal expenseAmount;
	private Date expenseDate;
	
	/**
	 * 
	 * @return surrogate primary key of the applicable category.
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
	 * 
	 * @return user-facing description of the applicable category. 
	 */
	public String getBudgetCategoryDescription() {
		return budgetCategoryDescription;
	}

	/**
	 * 
	 * @param budgetCategoryDescription
	 */
	public void setBudgetCategoryDescription(String budgetCategoryDescription) {
		this.budgetCategoryDescription = budgetCategoryDescription;
	}

	/**
	 * 
	 * @return Optional comments users may enter about the expense.
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
	 * 
	 * @return Amount of the expense incurred.
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
	 * @return Date on which the expense was incurred.
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
}
