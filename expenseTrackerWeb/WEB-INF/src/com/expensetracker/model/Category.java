package com.expensetracker.model;

import java.util.Date;

/**
 * Represents a category of expenses.  
 * These categories are also used to set budgets in advance of the expenses. 
 * 
 * @author Adam Lindell
 * @see CategoryDTO, CategoryDAO
 */
public class Category {
	private Integer categoryId;
	private String categoryDescription;
	private Date createTimestamp;
	private Date expireDate;

	/**
	 * 
	 * @return Surrogate primary key of the category
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	
	/**
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * 
	 * @return User-facing description of the Category
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}
	
	/**
	 * 
	 * @param categoryDescription
	 */
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	/**
	 * 
	 * @return Date and time this category was persisted. 
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
	 * @return Date on which this category is no longer available for budgeting or expenses.  
	 * The date on which a category expires it is soft delted from the application.
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	
	/**
	 * 
	 * @param expireDate
	 */
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
