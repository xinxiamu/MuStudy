package com.terran4j.springboot.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "t_user") // 定义数据库表名称。
@Table(indexes = { // 定义数据库索引。

		// 唯一索引。
		@Index(name = "ux_user_login_name", columnList = "loginName", unique = true), //

		// 非唯一索引。
		@Index(name = "idx_user_age", columnList = "age"), //
})
public class User {

	/**
	 * id, 自增主键。
	 */
	@Id
	@GeneratedValue
	@Column(length = 20)
	private Long id;

	/**
	 * 用户的登录名。
	 */
	@Column(length = 100, nullable = false)
	private String loginName;

	/**
	 * 用户的年龄。
	 */
	@Column(length = 3)
	private Integer age;

	/**
	 * 用户的状态。
	 */
	@Column(length = 16, nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.enable;

	/**
	 * 用户的注册时间。
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date registTime;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getLoginName() {
		return loginName;
	}

	public final void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public final Integer getAge() {
		return age;
	}

	public final void setAge(Integer age) {
		this.age = age;
	}

	public final UserStatus getStatus() {
		return status;
	}

	public final void setStatus(UserStatus status) {
		this.status = status;
	}

	public final Date getRegistTime() {
		return registTime;
	}

	public final void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	
}