package com.bms.eai.module.core;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractSdkEntity {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	public abstract String getId();

	public abstract void setId(String id);
	
}
