package com.expensetracker.delegate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.expensetracker.dto.BudgetDTO;
import com.expensetracker.dto.BudgetMonthDTO;
import com.expensetracker.dto.CategoryDTO;
import com.expensetracker.exception.ExpenseTrackerException;
import com.expensetracker.model.Budget;
import com.expensetracker.persist.BudgetDAO;
import com.expensetracker.persist.ExpenseDAO;
import com.expensetracker.persist.PersistUtil;

/**
 * The delegate layer mediates between the data access and user interface layers.
 * It is responsible for creating DTO's for the user interface 
 * and Model objects for the data access layer.
 * 
 * The BudgetDelegate handles all requests related to budgets.
 * 
 * @author Adam Lindell
 */
public class BudgetDelegate {
	
	/**
	 * 
	 * @param month - integer representation of the month.  January is 1 and December is 12.
	 * @param year - integer representation of the four-digit year.
	 * @return the budgeted amounts (by category) for the given month.
	 * @throws ExpenseTrackerException
	 */
	public List<BudgetDTO> getMonthlyBudgetItems ( Integer month, Integer year ) throws ExpenseTrackerException{
		List<BudgetDTO> result = new ArrayList<BudgetDTO>();
		BudgetDAO budgetDAO = new BudgetDAO();
		ExpenseDAO expenseDAO = new ExpenseDAO();
		
		List<Budget> budgets = budgetDAO.getBudgetItems(month, year);
		
		for ( Budget b : budgets ){
			BudgetDTO budgetDTO = new BudgetDTO();
			budgetDTO.setBudgetAmount(b.getBudgetAmount());
			budgetDTO.setBudgetCategoryDescription(b.getCategory().getCategoryDescription());
			budgetDTO.setBudgetCategoryId(b.getCategory().getCategoryId());
			budgetDTO.setMonth(b.getMonth());
			budgetDTO.setYear(b.getYear());
			BigDecimal totalExpense = expenseDAO.getTotalExpense (b.getMonth(), b.getYear(), b.getCategory().getCategoryId());
			budgetDTO.setTotalSpentAmount(totalExpense);
			result.add(budgetDTO);
		}
		return result;
	}
	
	/**
	 * 
	 * @return The list of months which have at least one budgeted category.  
	 * Any of these months will return results when they are passed to <code>getMonthlyBudgetItems</code>.
	 * @throws ExpenseTrackerException
	 */
	public List<BudgetMonthDTO> getAvailableBudgetMonths() throws ExpenseTrackerException{
		List<BudgetMonthDTO> result = new ArrayList<BudgetMonthDTO>();
		BudgetDAO budgetDAO = new BudgetDAO();
		List<Object[]> availableBudgetMonths = budgetDAO.getDistinctBudgetMonths();
		
		for ( Object[] o : availableBudgetMonths ){
			Integer month = (Integer)o[0];
			Integer year = (Integer)o[1];
			BudgetMonthDTO budgetMonthDTO = new BudgetMonthDTO();
			budgetMonthDTO.setMonth(month);
			budgetMonthDTO.setYear(year);
			result.add(budgetMonthDTO);
		}
		return result;
	}
}
