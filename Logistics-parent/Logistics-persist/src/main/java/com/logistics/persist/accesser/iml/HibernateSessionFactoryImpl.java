package com.logistics.persist.accesser.iml;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.logistics.persist.accesser.HibernateSessionFactory;

@Component(value="hibernateSessionFactory")
public class HibernateSessionFactoryImpl implements HibernateSessionFactory {
	
	@Resource
	private org.hibernate.SessionFactory sessionFactoryLg;
	
	@Resource
	private org.hibernate.SessionFactory sessionFactoryLg_log;
	
	@Resource
	private org.hibernate.SessionFactory sessionFactoryLg_dictionary;

	@Override
	public org.hibernate.SessionFactory getSesionFactoryLg() {
		return sessionFactoryLg;
	}

	@Override
	public SessionFactory getSesionFactoryLg_dictionary() {
		return sessionFactoryLg_dictionary;
	}

	@Override
	public SessionFactory getSesionFactoryLg_log() {
		return sessionFactoryLg_log;
	}

}
