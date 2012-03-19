package com.expensetracker.exception;

/**
 * This high-level exception wraps any exceptions that 
 * may occur while attempting to serve user requests.
 * 
 * User interfaces accessing this application will need to 
 * handle ExpenseTrackerExceptions, usually by informing the
 * user of the error and taking appropriate clean up actions if required.   
 * 
 * @author Adam Lindell
 */
public class ExpenseTrackerException extends Exception {

	public ExpenseTrackerException(Exception e) {
		super (e);
	}

}
