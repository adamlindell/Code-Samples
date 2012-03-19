package com.expensetracker.dto;

import java.math.BigDecimal;

/**
 * Represents a budgeted amount for a given category for a given month.
 * 
 * @author Adam Lindell
 * @see BudgetDelegate
 */
public class BudgetDTO {
	private BigDecimal budgetAmount;
	private Integer budgetId;
	private String budgetCategoryDescription;
	private Integer budgetCategoryId;
	private Integer month;
	private BigDecimal totalSpentAmount;
	private Integer year;
	
	/**
	 * 
	 * @return Amount budgeted for this month and category.
	 */
	public BigDecimal getBudgetAmount() {
		return budgetAmount;
	}
	
	/**
	 * 
	 * @param budgetAmount
	 */
	public void setBudgetAmount(BigDecimal budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	
	/**
	 * 
	 * @return Surrogate primary key of the budget.
	 */
	public Integer getBudgetId() {
		return budgetId;
	}
	
	/**
	 * 
	 * @param budgetId
	 */
	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}
	
	/**
	 * 
	 * @return User-facing description of the budget category.
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
	 * @return Surrogate key of the budget category.
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
	 * @return Calendar month for which the budget is meant.
	 * January => 1, December =>12
	 */
	public Integer getMonth() {
		return month;
	}
	
	/**
	 * 
	 * @param month
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	/**
	 * 
	 * @return the sum of the expenses that have been entered for the 
	 * budgeted category during the budgeted month.
	 */
	public BigDecimal getTotalSpentAmount() {
		return totalSpentAmount;
	}
	
	/**
	 * 
	 * @param totalSpentAmount
	 */
	public void setTotalSpentAmount(BigDecimal totalSpentAmount) {
		this.totalSpentAmount = totalSpentAmount;
	}
	
	/**
	 * 
	 * @return Four-digit year for which the budget is meant.
	 */
	public Integer getYear() {
		return year;
	}
	
	/**
	 * 
	 * @param year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
}
