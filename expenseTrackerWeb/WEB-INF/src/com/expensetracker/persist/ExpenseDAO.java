package com.expensetracker.persist;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.expensetracker.exception.ExpenseTrackerException;
import com.expensetracker.model.Budget;
import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Retrieves from the database information pertaining to <code>Expenses</code>s
 * 
 * @author Adam Lindell
 * @see Expense
 */
public class ExpenseDAO {
	
	/**
	 * 
	 * @param month
	 * @param year
	 * @param categoryId
	 * @return the sum of all expenses entered for the given budget month and category.
	 * @throws ExpenseTrackerException
	 */
	public BigDecimal getTotalExpense ( Integer month, Integer year, Integer categoryId ) throws ExpenseTrackerException{
		String hql = "select sum(e.expenseAmount) " +
				"from Expense e " +
				"where month(e.expenseDate) = :month " +
				"and year(e.expenseDate) = :year " +
				"and e.category.categoryId = :categoryId";
		Transaction tx = null;
		try{
			tx = PersistUtil.beginTransaction();
			Object queryResult =PersistUtil.getSessionFactory().getCurrentSession().createQuery(hql)
				.setInteger("month", month)
				.setInteger("year", year)
				.setInteger("categoryId", categoryId)
				.uniqueResult();
			tx.commit();
			if ( queryResult != null ){
				return new BigDecimal(queryResult.toString());
			} 
			else {
				return BigDecimal.ZERO;
			}
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
	 * @param maxCountOfExpenses
	 * @return the give number of the most recent expenses, ordered by create timestamp descending.
	 * @throws ExpenseTrackerException
	 */
	public List<Expense> getRecentlyEnteredExpenses ( int maxCountOfExpenses ) throws ExpenseTrackerException{
		List<Expense> result = null;
		Transaction tx = null;
		try{
			tx = PersistUtil.beginTransaction();
			result = PersistUtil.getSessionFactory().getCurrentSession().createCriteria(Expense.class)
				.addOrder(Order.desc("createTimestamp"))
				.setMaxResults(maxCountOfExpenses)
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
	 * Persist the given expense to the database.
	 * 
	 * @param expenseToSave
	 * @throws ExpenseTrackerException
	 */
	public void saveExpense(Expense expenseToSave) throws ExpenseTrackerException{
		Transaction tx = null;
		try{
			tx = PersistUtil.beginTransaction();
			PersistUtil.getSessionFactory().getCurrentSession().save(expenseToSave);
			tx.commit();
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