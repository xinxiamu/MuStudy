package com.example.ymu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * 年级。
 * 
 * @author Administrator
 *
 */
@Entity
public class Grade extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4189772496745086809L;

	/**
	 * 年级号
	 */
	@Column(nullable = false, length = 15)
	private Integer gnum;

	/**
	 * 所有班级。
	 */
	@OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Classz> classes = new ArrayList<>();

	public List<Classz> getClasses() {
		return classes;
	}
	
	public void addClass(Classz class1) {
        classes.add( class1 );
        class1.setGrade(this);
    }

    public void removeClass(Classz class1) {
        classes.remove( class1 );
        class1.setGrade(null);
    }

	public Integer getGnum() {
		return gnum;
	}

	public void setGnum(Integer gnum) {
		this.gnum = gnum;
	}

}
