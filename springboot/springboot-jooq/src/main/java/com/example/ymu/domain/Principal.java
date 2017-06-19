package com.example.ymu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.example.ymu.domain.type.PrincipalType;

/**
 * 校长
 * 
 * @author Administrator
 *
 */
@Entity
public class Principal extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5146632634606617525L;

	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private PrincipalType PrincipalType;

	/**
	 * 校长也是老师。单向
	 */
	@OneToOne
	@PrimaryKeyJoinColumn
	private Teacher teacher;

	public PrincipalType getPrincipalType() {
		return PrincipalType;
	}

	public void setPrincipalType(PrincipalType principalType) {
		PrincipalType = principalType;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	
}
