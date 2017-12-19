package com.bms.eai.common.model.repository;

import org.springframework.stereotype.Repository;

import com.bms.eai.common.model.core.IJpaSpecificationRepository;
import com.bms.eai.common.model.entity.PropCountry;

/**
 * @author kul_sudhakar
 *
 */
@Repository
public interface IPropCountryRepository extends IJpaSpecificationRepository<PropCountry, String> {

}
