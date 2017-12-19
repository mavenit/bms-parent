package com.bms.eai.portal.config.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

import com.google.common.collect.Lists;

/**
 * @author kul_sudhakar
 *
 */
public class BackendRequestParamTokenEndpointsProviderImpl extends AbstractEndpointsProviderBase
implements RequestParamTokenEndpointsProvider {

	@Value("${bms.backend.user.self.profilePic.full.resource.path}")
    private String fullProfilePicUrl;
    @Value("${bms.backend.user.self.profilePic.thumb.resource.path}")
    private String thumbProfilePicUrl;

    @Override
    public List<Endpoint> createEndpoints() {
        return Lists.newArrayList(
                new Endpoint(fullProfilePicUrl, HttpMethod.GET),
                new Endpoint(thumbProfilePicUrl, HttpMethod.GET)
        );
    }

	
}
