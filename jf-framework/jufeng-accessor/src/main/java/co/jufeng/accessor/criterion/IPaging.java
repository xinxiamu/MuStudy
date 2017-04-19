package co.jufeng.accessor.criterion;


public abstract interface IPaging extends IRestrictions {
	
	int getFirstResult();
	
	int getMaxResults();
	
}
