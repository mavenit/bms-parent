package com.bms.eai.common.model.repository;

import org.springframework.stereotype.Repository;

import com.bms.eai.common.model.core.IJpaSpecificationRepository;
import com.bms.eai.common.model.entity.PropType;

/**
 * @author kul_sudhakar
 *
 */
@Repository
public interface IPropTypeRepository extends IJpaSpecificationRepository<PropType, String> {

}
