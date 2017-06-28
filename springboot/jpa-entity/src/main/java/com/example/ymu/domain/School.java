package com.example.ymu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 学校。
 * 
 * @author Administrator
 *
 */
@Entity
public class School extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1572811526297928046L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 学校名称
	 */
	@Column(nullable = false, length = 80)
	private String name;

	/**
	 * 学校所在地址
	 */
	@Column(nullable = false, length = 180)
	private String addr;

	/**
	 * 学校创办时间
	 */
	@Column(nullable = true, length = 4)
	@Temporal(TemporalType.TIMESTAMP)
	private Date foundTime;

	/**
	 * 学校老师
	 */
	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Teacher> teachers = new ArrayList<>();
	
	/**
	 * 所有年级。单向
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Grade> grades = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getFoundTime() {
		return foundTime;
	}

	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}
	
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
		teacher.setSchool(this);
	}
	
	public void removeReacher(Teacher teacher) {
		teachers.remove(teacher);
		teacher.setSchool(null);  
	}
	
	public List<Grade> getGrades() {
		return grades;
	}
	
}
