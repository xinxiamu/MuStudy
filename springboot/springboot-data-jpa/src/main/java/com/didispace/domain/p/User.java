package com.didispace.domain.p;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;
    
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
   	List<File> files;
    
	public User() {
	}



	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}



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



	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public List<File> getFiles() {
		return files;
	}



	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	
}
