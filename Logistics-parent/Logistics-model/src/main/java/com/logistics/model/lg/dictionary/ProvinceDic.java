package com.logistics.model.lg.dictionary;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * Province entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dic_province", catalog = "lg_dictionary", uniqueConstraints = {
		@UniqueConstraint(columnNames = "code"),
		@UniqueConstraint(columnNames = "name") })
public class ProvinceDic implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String name;
	private Set<CityDic> cities = new HashSet<CityDic>(0);

	// Constructors

	/** default constructor */
	public ProvinceDic() {
	}

	/** minimal constructor */
	public ProvinceDic(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/** full constructor */
	public ProvinceDic(String code, String name, Set<CityDic> cities) {
		this.code = code;
		this.name = name;
		this.cities = cities;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", unique = true, nullable = false, length = 6)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", unique = true, nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "province")
	public Set<CityDic> getCities() {
		return this.cities;
	}

	public void setCities(Set<CityDic> cities) {
		this.cities = cities;
	}

}