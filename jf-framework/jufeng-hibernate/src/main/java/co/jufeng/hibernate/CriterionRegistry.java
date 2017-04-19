package co.jufeng.hibernate;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.jufeng.accessor.criterion.ICriterion;

public class CriterionRegistry {
	
	public static final Logger LOG = LoggerFactory.getLogger(CriterionRegistry.class);
	
	public static final CriterionRegistry INSTANCE = new CriterionRegistry();
	
	private final ConcurrentHashMap<String, ICriterion> criterionMap = new ConcurrentHashMap<String, ICriterion>();

	@SuppressWarnings("null")
	public ICriterion getCriterion(String uuid) {
		LOG.debug( "Lookup: uid=%s", uuid );
		final ICriterion criterion = criterionMap.get( uuid );
		if ( criterion == null && LOG.isDebugEnabled() ) {
			LOG.debug( "Not found: %s", uuid );
			LOG.debug( criterion.toString() );
		}
		return criterion;
	}
	
	public static class ObjectFactoryImpl implements ObjectFactory {
		@Override
		public Object getObjectInstance(Object reference, Name name, Context nameCtx, Hashtable<?, ?> environment)
				throws Exception {
			LOG.debug( "JNDI lookup: %s", name );
			final String uuid = (String) ( (Reference) reference ).get( 0 ).getContent();
			LOG.trace( "Resolved to UUID = %s", uuid );
			return INSTANCE.getCriterion( uuid );
		}
	}

}
