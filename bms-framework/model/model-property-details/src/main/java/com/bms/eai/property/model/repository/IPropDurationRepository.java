package com.bms.eai.property.model.repository;

import org.springframework.stereotype.Repository;

import com.bms.eai.common.model.core.IJpaSpecificationRepository;
import com.bms.eai.property.model.entity.PropDuration;

/**
 * @author kul_sudhakar
 *
 */
@Repository
public interface IPropDurationRepository extends IJpaSpecificationRepository<PropDuration, String> {

}
