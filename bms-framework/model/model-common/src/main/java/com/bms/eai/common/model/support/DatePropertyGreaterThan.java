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
public class DatePropertyGreaterThan extends AbstractPropertyPredicateBuilder {

	private final String propertyName;
    private final Date value;
    private boolean orEqualTo;

    DatePropertyGreaterThan(String propertyName, Date value, boolean orEqualTo) {
        this.propertyName = propertyName;
        this.value = value;
        this.orEqualTo = orEqualTo;
    }

    @SuppressWarnings("unchecked")
    public Predicate build(From<?, ?> from, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String[] props = split(propertyName);
        if (orEqualTo) {
            return cb.greaterThanOrEqualTo(navigate(from, props).as(Date.class), value);
        } else {
            return cb.greaterThan(navigate(from, props).as(Date.class), value);
        }
    }
	
}
