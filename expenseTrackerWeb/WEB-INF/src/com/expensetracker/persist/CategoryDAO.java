package com.expensetracker.persist;

import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.expensetracker.exception.ExpenseTrackerException;
import com.expensetracker.model.Budget;
import com.expensetracker.model.Category;

/**
 * Retrieves from the database information pertaining to <code>Category</code> Objects
 * 
 * @author Adam Lindell
 * @see Category
 */
public class CategoryDAO {
	/**
	 * 
	 * @return all categories which are not expired (soft deleted).
	 * @throws ExpenseTrackerException
	 */
	public List<Category> getCurrentCategories() throws ExpenseTrackerException{
		List<Category> result = null;
		Transaction tx = null;
		try{
			tx = PersistUtil.beginTransaction();
			result = PersistUtil.getSessionFactory().getCurrentSession().createCriteria(Category.class)
				.add(Restrictions.ge("expireDate", new Date()))
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
	 * @param categoryId
	 * @return the full Category object for the given Id.
	 * @throws ExpenseTrackerException
	 */
	public Category getCategory(Integer categoryId) throws ExpenseTrackerException {
		Category result = null;
		Transaction tx = null;
		try{
			tx = PersistUtil.beginTransaction();
			result = (Category)PersistUtil.getSessionFactory().getCurrentSession().get(Category.class, categoryId);
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
