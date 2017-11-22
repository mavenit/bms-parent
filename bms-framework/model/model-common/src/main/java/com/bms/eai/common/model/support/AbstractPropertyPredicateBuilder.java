package com.bms.eai.common.model.support;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractPropertyPredicateBuilder implements PropertyPredicateBuilder  {

	protected String[] split(String propertyName) {
		return FindByPropertyValueSpecifications.split(propertyName);
	}
	
	@SuppressWarnings("rawtypes")
	protected Path navigate(From<?, ?> root, String[] props) {
		Path lastPath = null;
		for (String prop : props) {
			if (lastPath == null) {
				lastPath = root.get(prop);
			} else {
				lastPath = lastPath.get(prop);
			}
		}
		return lastPath;
	}

	protected Predicate[] toPredicates(List<PropertyPredicateBuilder> builders, From<?, ?> from,
			CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
		Predicate[] predicates = new Predicate[builders.size()];
		for (int i = 0; i < builders.size(); i++) {
			PropertyPredicateBuilder spec = builders.get(i);
			predicates[i] = spec.build(from, criteriaQuery, cb);
		}
		return predicates;
	}
	
}
