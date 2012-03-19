package com.expensetracker.backingbeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Superclass from which all backing beans descend. 
 * Handles the creation of server-side JSF messages. 
 * 
 * @author Adam Lindell
 */
public class ExpenseTrackerBackingBean {
	/**
	 * Standard messages displayed to the user. 
	 * 
	 * @author Adam Lindell
	 */
	protected static enum UserFacingMessage {
		DB_ERROR ("This system is temporarily unavailable.  Please try again later."),
		REQUIRED (" is required."),
		REQUIRED_POSTIVE (" is required."),
		SAVED_SUCCESSFULLY ("Saved.");
		
		private final String messageText;
		
		UserFacingMessage ( String messageText ){
			this.messageText = messageText;
		}
		
		public String getMessageText(){
			return this.messageText;
		}
	}
	
	/**
	 * Add an informational message to the list about a specific field.
	 * 
	 * @param fieldLabel
	 * @param message
	 */
	protected void addInfo( String fieldLabel, UserFacingMessage message ){
		addMessage ( fieldLabel, message, FacesMessage.SEVERITY_INFO );
	}
	
	/**
	 * Add an informational message to the list.
	 * 
	 * @param message
	 */
	protected void addInfo( UserFacingMessage message ){
		addMessage ( message, FacesMessage.SEVERITY_INFO );
	}
	
	/**
	 * Add an error message to the list about a specific field.
	 * 
	 * @param fieldLabel
	 * @param message
	 */
	protected void addError( String fieldLabel, UserFacingMessage message ){
		addMessage ( fieldLabel, message, FacesMessage.SEVERITY_ERROR );
	}
	
	/**
	 * Add an error message to the list.
	 * 
	 * @param message
	 */
	protected void addError( UserFacingMessage message ){
		addMessage ( message, FacesMessage.SEVERITY_ERROR );
	}
	
	/**
	 * Add a message to  the list about a specific field.
	 * 
	 * @param fieldLabel
	 * @param message
	 * @param severity
	 */
	private void addMessage( String fieldLabel, UserFacingMessage message, FacesMessage.Severity severity ){
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = fieldLabel + message.getMessageText();
		FacesMessage facesMessage = new FacesMessage(severity, msg, msg);
		context.addMessage(null, facesMessage);
	}
	
	/**
	 * Add a message to the list.
	 * 
	 * @param message
	 * @param severity
	 */
	private void addMessage( UserFacingMessage message, FacesMessage.Severity severity){
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = message.getMessageText();
		FacesMessage facesMessage = new FacesMessage(severity, msg, msg);
		context.addMessage(null, facesMessage);
	}
}
