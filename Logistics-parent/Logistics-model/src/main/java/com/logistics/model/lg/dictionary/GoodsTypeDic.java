package com.logistics.model.lg.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mu.persist.model.strategy.EntityBase;

/**
 * 货源类型
 * 
 * @author mt
 * 
 */
@Entity
@Table(name = "dic_goods_type",catalog = "lg_dictionary")
public class GoodsTypeDic extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4488810203382498221L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable=false,name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(updatable=false,nullable = false, length = 50)
	private String goodsTypeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
}
