package com.bms.eai.common.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author kul_sudhakar
 *
 */
@NoRepositoryBean
public interface JpaSpecificationRepository<T, ID extends Serializable>
extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>  {

}
