package com.bms.eai.common.model.support;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * @author kul_sudhakar
 *
 */
public class PropertyValueEqual extends AbstractPropertyPredicateBuilder {

	private final String propertyName;
	private final Object value;

	PropertyValueEqual(String propertyName, Object value) {
		this.propertyName = propertyName;
		this.value = value;
	}

	@Override
	public Predicate build(From<?, ?> from, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String[] props = split(propertyName);
		return cb.equal(navigate(from, props), value);
	}
	
}
