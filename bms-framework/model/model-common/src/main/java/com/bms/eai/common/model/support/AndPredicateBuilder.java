package com.bms.eai.common.model.support;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

import com.google.common.collect.Lists;

/**
 * @author kul_sudhakar
 *
 */
public class AndPredicateBuilder extends AbstractPropertyPredicateBuilder {

	private List<PropertyPredicateBuilder> builders;

	public AndPredicateBuilder(PropertyPredicateBuilder[] builders) {
		this.builders = Lists.newArrayList(builders);
	}

	public AndPredicateBuilder(List<PropertyPredicateBuilder> builders) {
		this.builders = builders;
	}

	@Override
	public Predicate build(From<?, ?> from, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.and(toPredicates(Lists.newArrayList(builders), from, query, cb));
	}
	
}
