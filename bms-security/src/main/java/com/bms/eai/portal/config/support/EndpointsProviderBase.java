package com.bms.eai.portal.config.support;

import java.util.List;

/**
 * @author kul_sudhakar
 *
 */
public interface EndpointsProviderBase {

	List<Endpoint> getEndpoints();

	boolean isEndpointMatched(String resourcePath, String method);

	boolean isEndpointStartedWith(String resourcePath, String method);

}
