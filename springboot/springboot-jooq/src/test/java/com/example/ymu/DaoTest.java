package com.example.ymu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.ymu.dao.BaseDao;
import com.example.ymu.dao.SchoolDaoImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class DaoTest {
	
//	 @Autowired
//	 private SchoolDaoImpl schoolDao;
	
	@Test
	public void addSchool() {
//		 schoolDao.showSchoolName();
//		 schoolDao.sayHello("miss zhang");

		System.out.println("---");
	}
}
