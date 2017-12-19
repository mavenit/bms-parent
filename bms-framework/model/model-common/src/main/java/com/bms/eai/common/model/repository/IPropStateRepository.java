package com.bms.eai.common.model.repository;

import org.springframework.stereotype.Repository;

import com.bms.eai.common.model.core.IJpaSpecificationRepository;
import com.bms.eai.common.model.entity.PropState;

/**
 * @author kul_sudhakar
 *
 */
@Repository
public interface IPropStateRepository extends IJpaSpecificationRepository<PropState, String>  {

}
