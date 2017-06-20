package com.example.ymu.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.ymu.domain.School;

//@Repository
public class SchoolDaoImpl extends BaseDaoImpl<School, Long> implements SchoolDao {  

	private EntityManager em;

	public SchoolDaoImpl(Class<School> domainClass, EntityManager em) { 
		super(domainClass, em);
		this.em = em;
	}

	@Override
	public void showSchoolName() {
		System.out.println("马槛小学");
		System.out.println("em:" + em);
	}

}
