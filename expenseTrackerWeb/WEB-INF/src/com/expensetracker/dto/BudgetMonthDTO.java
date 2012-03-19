package com.expensetracker.dto;

/**
 * Represents a month for which budgeted categories exist.
 * 
 * @author Adam Lindell
 * @see BudgetDelegate
 */
public class BudgetMonthDTO {
	private Integer month;
	private Integer year;
	
	/**
	 * 
	 * @return two-ditit month (Janary => 01 and December => 12)
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
	 * @return four digit year.
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
