package co.jufeng.hibernate.criterion.impl;

import java.io.Serializable;

import co.jufeng.accessor.Immutable;
import co.jufeng.accessor.criterion.IRestrictions;
import co.jufeng.hibernate.OrderEnum;


@Immutable
public class Order extends Restrictions implements IRestrictions, Cloneable, Serializable {


	private static final long serialVersionUID = 3052003156604146945L;

	private String name;
	
	private Object value;
	
	public static Order add(OrderEnum orderEnum, String value){
		return new Order(orderEnum, value);
	}
	
	Order(OrderEnum orderEnum, String value) {
		if (value == null) {
		      throw new IllegalArgumentException("value may not be null");
	    }
	    this.name = orderEnum.value;
	    this.value = value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
