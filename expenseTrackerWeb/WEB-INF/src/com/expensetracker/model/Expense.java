package com.expensetracker.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents an expense incurred within a budgeted category.
 * 
 * @author Adam Lindell
 * @see ExpenseDTO, ExpenseDAO
 */
public class Expense {
	private Category category;
	private String comment;
	private Date  createTimestamp;
	private BigDecimal expenseAmount;
	private Date expenseDate;
	private Integer expenseId;
	
	/**
	 * 
	 * @return Budget category of the expense.
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
	 * @return Optional comments users may enter about the expense.
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * 
	 * @param comments
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * 
	 * @return Date on which the Expense was pesisted.
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
	
	/**
	 * 
	 * @return Surrogate primary key of the expense record.
	 */
	public Integer getExpenseId() {
		return expenseId;
	}
	
	/**
	 * 
	 * @param expenseId
	 */
	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}
}
