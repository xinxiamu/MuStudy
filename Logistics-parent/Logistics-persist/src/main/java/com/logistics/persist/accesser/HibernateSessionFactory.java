package com.logistics.persist.accesser;

import org.hibernate.SessionFactory;

public interface HibernateSessionFactory {

	/**
	 * lg库
	 * @return
	 */
	public SessionFactory getSesionFactoryLg();
	
	/**
	 * lg_log库
	 * @return
	 */
	public SessionFactory getSesionFactoryLg_log();
	
	/**
	 * lg_dictionary库
	 * @return
	 */
	public SessionFactory getSesionFactoryLg_dictionary();
	
}
