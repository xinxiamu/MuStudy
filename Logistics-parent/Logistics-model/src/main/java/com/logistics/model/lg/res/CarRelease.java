package com.logistics.model.lg.res;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mu.persist.model.strategy.EntityStrategyAuto;

/**
 * 车辆发布路线
 * @author mutou
 *
 */
@Entity
@Table(name = "car_release",catalog="lg")
public class CarRelease extends EntityStrategyAuto<CarRelease> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1317360240036322054L;


}
