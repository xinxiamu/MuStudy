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
	 * 所有班级。双向
	 */
	@OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Classz> classzs = new ArrayList<>();

	public List<Classz> getClasses() {
		return classzs;
	}
	
	public void addClass(Classz classz) {
		classzs.add(classz);
		classz.setGrade(this);
    }

    public void removeClass(Classz classz) {
    	classzs.remove(classz);
        classz.setGrade(null);
    }

	public Integer getGnum() {
		return gnum;
	}

	public void setGnum(Integer gnum) {
		this.gnum = gnum;
	}

}
