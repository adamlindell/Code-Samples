package com.expensetracker.persist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.expensetracker.exception.ExpenseTrackerException;
import com.expensetracker.model.Budget;
import com.expensetracker.model.Expense;

/**
 * Retrieves from the database information pertaining to <code>Budget</code>s
 * 
 * @author Adam Lindell
 * @see Budget
 */
public class BudgetDAO {
	/** 
	 * @param month
	 * @param year
	 * @return all budgeted categories for a given month, ordered by category description.
	 * @throws ExpenseTrackerException
	 */
	public List<Budget> getBudgetItems ( Integer month, Integer year ) throws ExpenseTrackerException{
		List<Budget> result = new ArrayList<Budget>();
		Transaction tx = null;
		try{
			tx = PersistUtil.beginTransaction();
			result = PersistUtil.getSessionFactory().getCurrentSession().createCriteria(Budget.class)
				.add(Restrictions.eq("month", month))
				.add(Restrictions.eq("year", year))
				.createCriteria("category")
					.addOrder(Order.asc("categoryDescription"))
				.list();
			tx.commit();
			return result;
		} 
		catch ( Exception e){
			e.printStackTrace();
			if ( tx != null ){
				PersistUtil.rollback(tx);
			}
			throw new ExpenseTrackerException(e);
		}
	}

	/**
	 * 
	 * @return The months for which a budget has been made.
	 * @throws ExpenseTrackerException
	 */
	public List<Object[]> getDistinctBudgetMonths() throws ExpenseTrackerException {
		List<Object[]> result;
		String hql = "select distinct b.month, b.year from Budget b ";
		Transaction tx = null;
		try{
			tx = PersistUtil.beginTransaction();
			result = PersistUtil.getSessionFactory().getCurrentSession().createQuery(hql).list();
			tx.commit();
			return result;
		} 
		catch ( Exception e){
			e.printStackTrace();
			if ( tx != null ){
				PersistUtil.rollback(tx);
			}
			throw new ExpenseTrackerException(e);
		}		
	}
}
