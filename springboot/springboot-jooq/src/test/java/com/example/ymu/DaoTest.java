package com.example.ymu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.ymu.dao.SchoolDao;
import com.example.ymu.dao.SchoolRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class DaoTest {
	
	 @Autowired
	 private SchoolDao schoolDao;
	
	@Test
	public void addSchool() {
		 schoolDao.showSchoolName();
		 SchoolRepository a = schoolDao.getMRepository();

		System.out.println("---");
	}
}
