package com.expensetracker.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.expensetracker.dto.CategoryDTO;
import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.exception.ExpenseTrackerException;
import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;
import com.expensetracker.persist.CategoryDAO;
import com.expensetracker.persist.ExpenseDAO;
import com.expensetracker.persist.PersistUtil;

/**
 * The delegate layer mediates between the data access and user interface layers.
 * It is responsible for creating DTO's for the user interface 
 * and Model objects for the data access layer. 
 * 
 * The ExpenseDelegate handles all requests related to expenses.
 * 
 * @author Adam Lindell
 */
public class ExpenseDelegate {
	
	/**
	 * 
	 * @return Categories where the expire date has not yet been reached.
	 * Categories that have not been deleted have expire dates of '9999-12-31'. 
	 * 
	 * @throws ExpenseTrackerException
	 */
	public List<CategoryDTO> getCurrentCategories() throws ExpenseTrackerException{
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getCurrentCategories();
		for ( Category c : categories ){
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCategoryId(c.getCategoryId());
			categoryDTO.setCategoryDescription(c.getCategoryDescription());
			categoryDTO.setExpireDate(c.getExpireDate());
			result.add(categoryDTO);
		}
		return result;
	}
	
	/**
	 * 
	 * @param maxResults
	 * @return The <code>maxResults</code> number of expenses, ordered by createTimestamp.
	 * @throws ExpenseTrackerException
	 */
	public List<ExpenseDTO> getRecentExpenses ( Integer maxResults ) throws ExpenseTrackerException{
		List<ExpenseDTO> result = new ArrayList<ExpenseDTO>();
		ExpenseDAO expenseDAO = new ExpenseDAO();
		List<Expense> expenses = expenseDAO.getRecentlyEnteredExpenses(maxResults);
		for (Expense e : expenses){
			ExpenseDTO expenseDTO = new ExpenseDTO();
			expenseDTO.setBudgetCategoryId(e.getCategory().getCategoryId());
			expenseDTO.setBudgetCategoryDescription(e.getCategory().getCategoryDescription());
			expenseDTO.setComments(e.getComment());
			expenseDTO.setExpenseAmount(e.getExpenseAmount());
			expenseDTO.setExpenseDate(e.getExpenseDate());
			result.add(expenseDTO);
		}
		return result;
	}
	
	/**
	 * Save a new expense.
	 * 
	 * @param expenseDTO
	 * @throws ExpenseTrackerException
	 */
	public void saveExpense (ExpenseDTO expenseDTO) throws ExpenseTrackerException{
		CategoryDAO categoryDAO = new CategoryDAO();
		Category category = categoryDAO.getCategory(expenseDTO.getBudgetCategoryId());

		ExpenseDAO expenseDAO = new ExpenseDAO();
		Expense expense = new Expense();
		expense.setCategory(category);
		expense.setComment(expenseDTO.getComments());
		expense.setCreateTimestamp(null); //DB will generate on save
		expense.setExpenseAmount(expenseDTO.getExpenseAmount());
		expense.setExpenseDate(expenseDTO.getExpenseDate());
		expense.setExpenseId(null); //DB will generate a new one.
		expenseDAO.saveExpense(expense);
	}
}
