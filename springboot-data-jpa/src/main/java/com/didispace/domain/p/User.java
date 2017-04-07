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
    
	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
}
