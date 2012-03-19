package com.expensetracker.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  Represents a budgeted amount for a given category for a given month.
 * 
 * @author Adam Lindell
 * @see BudgetDTO, BudgetDAO
 */
public class Budget {
	private BigDecimal budgetAmount;
	private Integer budgetId;
	private Category category;
	private Date createTimestamp;
	private Integer month;
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
	 * @return Category of the budgeted amount.
	 */
	public Category getCategory() {
		return category;
	}
	
	/**
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * 
	 * @return date time  on which the budget was persisted
	 */
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	
	/**
	 * 
	 * @param createTimestamp
	 */
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
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
