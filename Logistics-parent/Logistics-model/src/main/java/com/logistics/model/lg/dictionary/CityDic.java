package com.logistics.model.lg.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dic_city",catalog = "lg_dictionary", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class CityDic implements java.io.Serializable {

	// Fields

	private Short id;
	private ProvinceDic province;
	private String code;
	private String name;

	// Constructors

	/** default constructor */
	public CityDic() {
	}

	/** full constructor */
	public CityDic(ProvinceDic province, String code, String name) {
		this.province = province;
		this.code = code;
		this.name = name;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "province_code", nullable = false)
	public ProvinceDic getProvince() {
		return this.province;
	}

	public void setProvince(ProvinceDic province) {
		this.province = province;
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

}