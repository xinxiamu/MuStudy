package com.logistics.model.lg.res;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 发布的货源
 * @author mutou
 *
 */
@Entity
@Table(name = "goods_release",catalog="lg")
public class GoodsRelease extends EntityStrategyAuto<GoodsRelease> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4818433869165429347L;


}
