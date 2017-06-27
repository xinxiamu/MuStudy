package com.example.ymu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 学生
 * 
 * @author Administrator
 *
 */
@Entity
public class Student extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7731264918256279520L;

	/**
	 * 老师，多个。双向
	 */
	@ManyToMany(mappedBy = "students")
	private List<Teacher> teachers = new ArrayList<>();

	/**
	 * 学生所属班级。
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Classz classz;

	/**
	 * 用户基础信息。单向
	 */
	@OneToOne
	@JoinColumn(unique = true, name = "pepole_basic_id")
	private PepoleBasic PepoleBasic;

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Classz getClassz() {
		return classz;
	}

	public void setClassz(Classz classz) {
		this.classz = classz;
	}

}
