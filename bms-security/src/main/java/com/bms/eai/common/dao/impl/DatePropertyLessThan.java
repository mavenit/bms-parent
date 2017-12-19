package com.bms.eai.common.dao.impl;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * @author kul_sudhakar
 *
 */
public class DatePropertyLessThan extends AbstractPropertyPredicateBuilder {

	private final String propertyName;
	private final Date value;
	private boolean orEqualTo;

	DatePropertyLessThan(String propertyName, Date value, boolean orEqualTo) {
		this.propertyName = propertyName;
		this.value = value;
		this.orEqualTo = orEqualTo;
	}

	@SuppressWarnings("unchecked")
	public Predicate build(From<?, ?> from, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String[] props = split(propertyName);
		if (orEqualTo) {
			return cb.lessThanOrEqualTo(navigate(from, props).as(Date.class), value);
		} else {
			return cb.lessThan(navigate(from, props).as(Date.class), value);
		}
	}
	
}
