package com.logistics.model.lg.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mu.persist.model.strategy.EntityBase;

/**
 * 会员类型
 * 
 * @author mt
 * 
 */
@Entity
@Table(name = "dic_user_type",catalog = "lg_dictionary")
public class UserTypeDic extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7375870563833114377L;

	/**
	 * 类型名称。车主、货主、物流运输公司(车主)、工厂企业(货主)、物流服务提供商
	 */
	@Column(updatable = false, nullable = false, length = 50)
	private String userTypeName;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(updatable = false, name = "id", unique = true, nullable = false)
	private Long id;

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
