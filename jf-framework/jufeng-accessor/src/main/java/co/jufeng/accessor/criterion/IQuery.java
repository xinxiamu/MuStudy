package co.jufeng.accessor.criterion;


public interface IQuery extends ICriterion {
	
	String getQuery();
	
	void setCacheable(boolean cacheable);
	

}
