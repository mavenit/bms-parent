package com.bms.eai.portal.security.entity.core;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * @author kul_sudhakar
 *
 */
@MappedSuperclass
public abstract class AbstractBmsBaseEntity <T extends AbstractBmsBaseEntity, ID extends Serializable>extends AbstractBaseEntity<T, ID> {

}
