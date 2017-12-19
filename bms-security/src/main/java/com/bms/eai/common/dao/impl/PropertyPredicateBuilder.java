package com.bms.eai.common.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * @author kul_sudhakar
 *
 */
public interface PropertyPredicateBuilder {

	Predicate build(From<?, ?> from, CriteriaQuery<?> query, CriteriaBuilder cb);
	
}
