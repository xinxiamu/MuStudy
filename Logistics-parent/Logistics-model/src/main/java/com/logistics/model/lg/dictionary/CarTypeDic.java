package com.logistics.model.lg.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mu.persist.model.strategy.EntityBase;

/**
 * 车辆类型
 * 
 * @author mutou
 * 
 */
@Entity
@Table(name = "dic_car_type",catalog = "lg_dictionary")
public class CarTypeDic extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4221973538863817141L;

	/**
	 * 类型名称。
	 */
	@Column(updatable = false, nullable = false, length = 50)
	private String carTypeName;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(updatable=false,name = "id", unique = true, nullable = false)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

}
