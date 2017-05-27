package cn.apopo.springbootshiro.settings;

public class ShiroSettings {
	
	private boolean cachingEnabled;
	
	private String authenticationCacheName;
	
	private boolean authenticationCachingEnabled;
	
	private String authorizationCacheName;
	
	private boolean authorizationCachingEnabled;

	public boolean isCachingEnabled() {
		return cachingEnabled;
	}

	public void setCachingEnabled(boolean cachingEnabled) {
		this.cachingEnabled = cachingEnabled;
	}

	public String getAuthenticationCacheName() {
		return authenticationCacheName;
	}

	public void setAuthenticationCacheName(String authenticationCacheName) {
		this.authenticationCacheName = authenticationCacheName;
	}

	public boolean isAuthenticationCachingEnabled() {
		return authenticationCachingEnabled;
	}

	public void setAuthenticationCachingEnabled(boolean authenticationCachingEnabled) {
		this.authenticationCachingEnabled = authenticationCachingEnabled;
	}

	public String getAuthorizationCacheName() {
		return authorizationCacheName;
	}

	public void setAuthorizationCacheName(String authorizationCacheName) {
		this.authorizationCacheName = authorizationCacheName;
	}

	public boolean isAuthorizationCachingEnabled() {
		return authorizationCachingEnabled;
	}

	public void setAuthorizationCachingEnabled(boolean authorizationCachingEnabled) {
		this.authorizationCachingEnabled = authorizationCachingEnabled;
	}
}
