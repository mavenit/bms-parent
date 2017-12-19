package com.bms.eai.portal.config.support;

import java.util.List;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractEndpointsProviderBase implements EndpointsProviderBase {

	private List<Endpoint> endpoints;

	@Override
	public List<Endpoint> getEndpoints() {
		if (endpoints == null) {
			endpoints = createEndpoints();
		}
		return endpoints;
	}

	@Override
	public boolean isEndpointMatched(String resourcePath, String method) {
		int lastIndex = resourcePath.indexOf("?");
		if (lastIndex > 0) {
			resourcePath = resourcePath.substring(0, lastIndex);
		}
		List<Endpoint> endpoints = getEndpoints();
		if (endpoints != null && !endpoints.isEmpty()) {
			for (Endpoint endpoint : endpoints) {
				String path = endpoint.getResourcePath();
				int placeholderIndex = path.indexOf("{");
				if (placeholderIndex != -1) {
					// If endpoint contains dynamic value (placeholder). E.g.
					// {token}
					String part = path.substring(0, placeholderIndex);
					if (resourcePath.startsWith(part)) {
						String epRemaining = path.substring(placeholderIndex);
						String[] epParts = epRemaining.split("/");
						String remaining = resourcePath.substring(placeholderIndex);
						String[] parts = remaining.split("/");
						if (epParts.length == parts.length) {
							boolean matched = true;
							for (int i = 0; i < parts.length; i++) {
								if (!(epParts[i].startsWith("{") && epParts[i].endsWith("}"))
										&& !parts[i].equals(epParts[i])) {
									matched = false;
									break;
								}
							}
							if (matched) {
								return true;
							}
						}
					}
				} else {
					if (resourcePath.equalsIgnoreCase(path) && method.equals(endpoint.getHttpMethod().toString())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isEndpointStartedWith(String resourcePath, String method) {
		int lastIndex = resourcePath.indexOf("?");
		if (lastIndex > 0) {
			resourcePath = resourcePath.substring(0, lastIndex);
		}
		List<Endpoint> endpoints = getEndpoints();
		if (endpoints != null && !endpoints.isEmpty()) {
			for (Endpoint endpoint : endpoints) {
				if (resourcePath.startsWith(endpoint.getResourcePath())
						&& method.equals(endpoint.getHttpMethod().toString())) {
					return true;
				}
			}
		}
		return false;
	}

	protected abstract List<Endpoint> createEndpoints();

}
