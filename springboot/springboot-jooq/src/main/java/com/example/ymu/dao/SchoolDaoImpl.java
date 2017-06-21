package com.example.ymu.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class SchoolDaoImpl implements SchoolDao {

	@Autowired
	@PersistenceContext
	private EntityManager em;

	@Autowired
	public SchoolRepository schoolRepository;

	@Override
	public void showSchoolName() {
		System.out.println("马槛小学");
		System.out.println("em:" + em);
	}

}
