package com.logistics.model.lg.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dic_area", catalog = "lg_dictionary", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class AreaDic implements java.io.Serializable {

	// Fields

	private Short id;
	private String code;
	private String name;
	private String cityCode;

	// Constructors

	/** default constructor */
	public AreaDic() {
	}

	/** full constructor */
	public AreaDic(String code, String name, String cityCode) {
		this.code = code;
		this.name = name;
		this.cityCode = cityCode;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@Column(name = "code", unique = true, nullable = false, length = 6)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "city_code", nullable = false, length = 6)
	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}