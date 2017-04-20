package com.logistics.model.lg.res;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 专线信息表
 * @author mutou
 *
 */
@Entity
@Table(name = "special_line",catalog="lg")
public class SpecialLine extends EntityStrategyAuto<SpecialLine> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8762957649862164929L;


}
