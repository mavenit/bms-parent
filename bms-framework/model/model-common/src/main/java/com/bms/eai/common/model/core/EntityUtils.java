package com.bms.eai.common.model.core;

import com.google.common.base.CaseFormat;

/**
 * @author kul_sudhakar
 *
 */
public class EntityUtils {

	@SuppressWarnings("rawtypes")
	public static <T extends AbstractEntity> String getEntityName(Class<T> entityClass) {
        return getEntityName(entityClass, CaseFormat.LOWER_UNDERSCORE);
    }

    @SuppressWarnings("rawtypes")
	public static <T extends AbstractEntity> String getEntityName(Class<T> entityClass, CaseFormat format) {
        return CaseFormat.UPPER_CAMEL.to(format, entityClass.getSimpleName());
    }
	
}
