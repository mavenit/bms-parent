package com.bms.eai.portal.security.lib;

import com.bms.eai.portal.security.entity.core.AbstractBaseEntity;
import com.google.common.base.CaseFormat;

/**
 * @author kul_sudhakar
 *
 */
public class EntityUtils {

	public static <T extends AbstractBaseEntity> String getEntityName(Class<T> entityClass) {
        return getEntityName(entityClass, CaseFormat.LOWER_UNDERSCORE);
    }

    public static <T extends AbstractBaseEntity> String getEntityName(Class<T> entityClass, CaseFormat format) {
        return CaseFormat.UPPER_CAMEL.to(format, entityClass.getSimpleName());
    }
	
}
