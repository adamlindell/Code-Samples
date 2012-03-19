package com.expensetracker.persist;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * This Util class handles the plumbing required by Hibernate.
 * It manages the session factory configuration as well as 
 * assisting with transaction management.
 *   
 * @author Adam Lindell
 *
 */
public class PersistUtil {
	private static SessionFactory sessionFactory;
	
	/**
	 * 
	 * @return Retrieves the sessionFactory singleton.
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
		  initSessionFactory();
		return sessionFactory;
	}

	/**
	 * Initiates a transaction.  All database interaction should start 
	 * with acquiring a new transaction
	 * @return open Hibernate Transaction
	 */
	public static Transaction beginTransaction() {
		if (PersistUtil.getSessionFactory().getCurrentSession().isOpen()) {
			Session session = PersistUtil.getSessionFactory().getCurrentSession();
			return session.beginTransaction();
		}
		else {
			Session session = PersistUtil.getSessionFactory().openSession();
			return session.beginTransaction();
		}
	}

	/**
	 * On failure, rollback the current transaction.
	 * Closes the session as well to prevent corruption of the session data.
	 * @param tx
	 */
	public static void rollback(Transaction tx) {
		tx.rollback();
		Session session = PersistUtil.getSessionFactory().getCurrentSession();
		session.close();
	}
	
	/**
	 * Build session factory from Hibernate configuration XML.
	 */
	private static void initSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		}
		catch (Exception e){	
			e.printStackTrace();
			throw new HibernateException("Could not initialize the Hibernate configuration");
		}
	}

}
