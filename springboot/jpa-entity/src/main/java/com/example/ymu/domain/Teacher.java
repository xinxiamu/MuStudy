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
 * 老师
 * 
 * @author Administrator
 *
 */
@Entity
public class Teacher extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5950133228835170941L;

	/**
	 * 学校。
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private School school;

	/**
	 * 学生，多个。双向
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private List<Student> students = new ArrayList<>();

	/**
	 * 教多个班级。
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private List<Classz> classzs = new ArrayList<>();

	/**
	 * 校长。双向
	 */
	@OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Principal principal;
	
	/**
	 * 用户基础信息。单向
	 */
	@OneToOne
	@JoinColumn(unique = true,name = "pepole_basic_id")
	private PepoleBasic PepoleBasic;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Classz> getClasszs() {
		return classzs;
	}

	public void setClasszs(List<Classz> classzs) {
		this.classzs = classzs;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public PepoleBasic getPepoleBasic() {
		return PepoleBasic;
	}

	public void setPepoleBasic(PepoleBasic pepoleBasic) {
		PepoleBasic = pepoleBasic;
	}
}
