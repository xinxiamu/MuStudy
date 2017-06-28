package com.example.ymu;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.ymu.dao.PepoleBasicDao;
import com.example.ymu.dao.PrincipalDao;
import com.example.ymu.dao.SchoolDao;
import com.example.ymu.dao.TeacherDao;
import com.example.ymu.domain.Classz;
import com.example.ymu.domain.Grade;
import com.example.ymu.domain.PepoleBasic;
import com.example.ymu.domain.Principal;
import com.example.ymu.domain.QPepoleBasic;
import com.example.ymu.domain.School;
import com.example.ymu.domain.Teacher;
import com.example.ymu.domain.type.PrincipalType;
import com.example.ymu.domain.type.SexType;
import com.querydsl.jpa.impl.JPAQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
// @ActiveProfiles("prod")
@Transactional
public class DaoTest {

	@Autowired
	private SchoolDao schoolDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private PepoleBasicDao pepoleBasicDao;
	@Autowired
	private PrincipalDao principalDao;

	@Test
	@Commit
	public void addSchool() {
		School school = new School();
		school.setAddr("马槛村");
		school.setFoundTime(new Date(System.currentTimeMillis()));
		school.setName("马槛小学");

		// 一年级
		Grade grade = new Grade();
		grade.setGnum(1);
		// 两个班
		Classz class1 = new Classz();
		class1.setCnum(1);
		Classz class2 = new Classz();
		class2.setCnum(2);
		grade.addClass(class1);
		grade.addClass(class2);
		// 二年级
		Grade grade2 = new Grade();
		grade2.setGnum(2);
		// 一个班
		Classz class3 = new Classz();
		class3.setCnum(1);
		grade2.addClass(class1);

		school.getGrades().add(grade);
		school.getGrades().add(grade2);

		schoolDao.getMRepository().save(school);
	}

	@Test
	public void getSchool() {
		School school = schoolDao.getMRepository().findOne(1L);
		System.out.println("=====school:" + school.getName());
	}

	@Test
	@Commit
	public void addTeacher() {
		Teacher teacher = new Teacher();
		teacher.setSchool(schoolDao.getMRepository().findOne(5L));
		PepoleBasic pepoleBasic = new PepoleBasic();
		pepoleBasic.setBirthdayTime(new java.util.Date());
		pepoleBasic.setName("张三");
		pepoleBasic.setSextType(SexType.MALE);
		pepoleBasicDao.getMRepository().save(pepoleBasic);
		teacher.setPepoleBasic(pepoleBasic);
		teacherDao.getMRepository().save(teacher);
	}

	@Test
	public void getPepoleTest() {
		QPepoleBasic pepoleBasic = QPepoleBasic.pepoleBasic;
		JPAQuery<?> query = new JPAQuery<Void>(pepoleBasicDao.getEntityManager());
		List<String> a = query.select(pepoleBasic.name).from(pepoleBasic).where(pepoleBasic.name.equalsIgnoreCase("张三"))
				.fetch();
		System.out.println("---a:" + a);
	}

	@Test
	@Commit
	public void addPrincipal() {
		Principal principal = new Principal();
		principal.setPrincipalType(PrincipalType.POSITIVE_LEVEL);
		principal.setTeacher(teacherDao.getMRepository().findOne(5L));
		principalDao.getMRepository().save(principal);
	}
}
