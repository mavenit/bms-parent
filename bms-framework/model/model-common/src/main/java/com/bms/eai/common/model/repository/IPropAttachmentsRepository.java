package com.bms.eai.common.model.repository;

import org.springframework.stereotype.Repository;

import com.bms.eai.common.model.core.IJpaSpecificationRepository;
import com.bms.eai.common.model.entity.PropAttachments;

/**
 * @author kul_sudhakar
 *
 */
@Repository
public interface IPropAttachmentsRepository extends IJpaSpecificationRepository<PropAttachments, String> {

}
