package com.bms.eai.common.model.support;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * @author kul_sudhakar
 *
 */
public class DatePropertyBetween extends AbstractPropertyPredicateBuilder {

	private final String propertyName;
	private final Date fromDate;
	private final Date toDate;

	public DatePropertyBetween(String propertyName, Date fromDate, Date toDate) {
		this.propertyName = propertyName;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	@SuppressWarnings("unchecked")
	public Predicate build(From<?, ?> from, CriteriaQuery<?> query, CriteriaBuilder cb) {
		String[] props = split(propertyName);
		return cb.between(navigate(from, props).as(Date.class), fromDate, toDate);
	}
	
}
