package com.bms.eai.property.model.repository;

import org.springframework.stereotype.Repository;

import com.bms.eai.common.model.core.IJpaSpecificationRepository;
import com.bms.eai.property.model.entity.PropBlockDetails;

/**
 * @author kul_sudhakar
 *
 */
@Repository
public interface IPropBlockDetailsRepository extends IJpaSpecificationRepository<PropBlockDetails, String>  {

}
