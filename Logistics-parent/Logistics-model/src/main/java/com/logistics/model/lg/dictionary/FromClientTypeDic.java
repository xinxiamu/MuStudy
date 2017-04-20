package com.logistics.model.lg.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mu.persist.model.strategy.EntityBase;

/**
 * 终端类型信息表。
 * 
 * @author mt
 * 
 */
@Entity
@Table(name = "dic_from_client_type",catalog = "lg_dictionary")
public class FromClientTypeDic extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2090280025393093051L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable=false,name = "id", nullable = false, unique = true)
	private Long id;
	
	/**
	 * 操作系统名。android、ios、windows
	 */
	@Column(updatable=false,nullable = false, length = 10)
	private String operatingSystem;
	
	/**
	 * 终端类型名称。手机、平板、电脑
	 */
	@Column(updatable=false,nullable = false, length = 10)
	private String clientTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getClientTypeName() {
		return clientTypeName;
	}

	public void setClientTypeName(String clientTypeName) {
		this.clientTypeName = clientTypeName;
	}

}
