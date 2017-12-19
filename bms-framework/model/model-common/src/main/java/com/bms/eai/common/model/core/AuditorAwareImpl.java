package com.bms.eai.common.model.core;

import org.springframework.data.domain.AuditorAware;

/**
 * @author kul_sudhakar
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "Sudhakar";
	}
}
