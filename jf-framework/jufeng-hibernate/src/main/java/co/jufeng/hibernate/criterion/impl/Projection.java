package co.jufeng.hibernate.criterion.impl;

import co.jufeng.accessor.criterion.ICriterion;
import co.jufeng.accessor.criterion.IProjection;

/**
 * 投射
 * @author Administrator
 *
 */
public class Projection extends Restrictions implements IProjection {

	private static final long serialVersionUID = -2359495851267887650L;

	ICriterion criterion;
	
	
	@Override
	public Object getValue() {
		return criterion.getValue();
	}

	@Override
	public String getName() {
		return criterion.getName();
	}


}
