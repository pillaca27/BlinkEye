package net.royal.spring.framework.web.database;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

	public static ThreadLocal<String> db = new ThreadLocal<>();

	@Override
	public String resolveCurrentTenantIdentifier() {
		if (DataBaseContextHolder.getInstance().getContext() == null) {
			return MultiTenantConnectionProvider.DEFAULT_DATABASE;
		}
		return DataBaseContextHolder.getInstance().getContext();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
