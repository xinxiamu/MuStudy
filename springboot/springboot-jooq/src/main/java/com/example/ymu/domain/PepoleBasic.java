package com.example.ymu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 个人基础信息。
 * 
 * @author Administrator
 *
 */
@Entity
public class PepoleBasic extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7154284115402423593L;

	/**
	 * 姓名。
	 */
	@Column(nullable = false,length = 80)
	private String name;
	
	/**
	 * 生日
	 */
	@Column(nullable = true, length = 4)
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdayTime;   
	
}
