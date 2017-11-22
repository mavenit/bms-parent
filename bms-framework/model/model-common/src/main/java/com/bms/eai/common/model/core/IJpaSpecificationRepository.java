package com.bms.eai.common.model.core;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author kul_sudhakar
 *
 */
@NoRepositoryBean
public interface IJpaSpecificationRepository <T, ID extends Serializable>extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>,PagingAndSortingRepository<T,ID> {

}
