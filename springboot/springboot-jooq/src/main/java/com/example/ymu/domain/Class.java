package com.example.ymu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 班级。
 * 
 * @author Administrator
 *
 */
@Entity
public class Class extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5406676654492554090L;

	/**
	 * 所属年级
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Grade grade;

	/**
	 * 班级下所有学生。
	 */
	@OneToMany(mappedBy = "class1", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<>();

	/**
	 * 拥有多个老师。
	 */
	@ManyToMany(mappedBy = "classes")
	private List<Teacher> teachers = new ArrayList<>();

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
}
