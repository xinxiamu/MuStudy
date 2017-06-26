package com.example.ymu.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntity implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6902749579633247824L;   
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
}
