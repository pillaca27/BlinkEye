package net.royal.spring.framework.web.database;

public class DataBaseContextHolder {
	private ThreadLocal<String> holder = new ThreadLocal<>();

	private DataBaseContextHolder() {
	}

	public static DataBaseContextHolder getInstance() {
		return ContextHolderHelper.instance;
	}

	public void setContext(final String context) {
		if (context == null) {
			holder.set(MultiTenantConnectionProvider.DEFAULT_DATABASE);
		}
		holder.set(context);
	}

	public String getContext() {
		return holder.get();
	}

	private static class ContextHolderHelper {
		private static final DataBaseContextHolder instance = new DataBaseContextHolder();
	}
}