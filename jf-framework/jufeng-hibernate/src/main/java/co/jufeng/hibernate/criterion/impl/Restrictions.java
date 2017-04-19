package co.jufeng.hibernate.criterion.impl;

import java.util.UUID;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.hibernate.internal.SessionFactoryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.jufeng.accessor.criterion.ICriterion;
import co.jufeng.accessor.criterion.IRestrictions;

public class Restrictions implements IRestrictions{

	private static final long serialVersionUID = 4420323923967091329L;
	
	public static final Logger LOG = LoggerFactory.getLogger(Restrictions.class);
	
	UUID uuid = UUID.randomUUID();

	ICriterion criterion;
	
	@Override
	public Object getValue() {
		return criterion.getValue();
	}

	@Override
	public String getName() {
		return criterion.getName();
	}
	
	@Override
	public boolean getCacheable() {
		return criterion.getCacheable();
	}

	@Override
	public Reference getReference() throws NamingException {
		// from javax.naming.Referenceable
		LOG.debug( "Returning a Reference to the ICriterion" );
		return new Reference(
				Restrictions.class.getName(),
				new StringRefAddr("uuid", uuid.toString()),
				SessionFactoryRegistry.ObjectFactoryImpl.class.getName(),
				null
		);
	}

}
