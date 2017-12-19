package com.bms.eai.common.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

/**
 * @author kul_sudhakar
 *
 */
public class FindByPropertyValueSpecifications {

	public static PropertyValueEqual equal(String propertyName, Object value) {
        return new PropertyValueEqual(propertyName, value);
    }
	
	public static PropertyValueLike like(String propertyName, String value, boolean ignoreCase) {
        return new PropertyValueLike(propertyName, value, ignoreCase);
    }

    public static DatePropertyGreaterThan gt(String propertyName, Date dateValue) {
        return new DatePropertyGreaterThan(propertyName, dateValue, false);
    }

    public static DatePropertyLessThan lt(String propertyName, Date dateValue) {
        return new DatePropertyLessThan(propertyName, dateValue, false);
    }

    public static DatePropertyGreaterThan gte(String propertyName, Date dateValue) {
        return new DatePropertyGreaterThan(propertyName, dateValue, true);
    }

    public static DatePropertyLessThan lte(String propertyName, Date dateValue) {
        return new DatePropertyLessThan(propertyName, dateValue, true);
    }

    public static DatePropertyBetween between(String propertyName, Date fromDate, Date toDate) {
        return new DatePropertyBetween(propertyName, fromDate, toDate);
    }

    public static <T> Specification<T> collectionProperties(String pathToCollection,
                                                            PropertyPredicateBuilder... builders) {
        return (root, criteriaQuery, cb) -> {
            String[] colProps = split(pathToCollection);
            Join<?, ?> join = join(root, colProps);
            Predicate[] predicates = new Predicate[builders.length];
            for (int i = 0; i < builders.length; i++) {
                PropertyPredicateBuilder builder = builders[i];
                predicates[i] = builder.build(join, criteriaQuery, cb);
            }
            return cb.or(predicates);
        };
    }

	@SafeVarargs
	public static <T> Specification<T> and(Specification<T>... specs) {
        return (root, criteriaQuery, cb) -> cb.and(toPredicates(Lists.newArrayList(specs), root, criteriaQuery, cb));
    }

    public static <T> Specification<T> and(List<Specification<T>> specs) {
        return (root, criteriaQuery, cb) -> cb.and(toPredicates(specs, root, criteriaQuery, cb));
    }

    @SuppressWarnings("unchecked")
	public static <T> Specification<T> or(Specification<T>... specs) {
        return (root, criteriaQuery, cb) -> cb.or(toPredicates(Lists.newArrayList(specs), root, criteriaQuery, cb));
    }

    public static <T> Specification<T> or(List<Specification<T>> specs) {
        return (root, criteriaQuery, cb) -> cb.or(toPredicates(specs, root, criteriaQuery, cb));
    }

    public static <T> Specification<T> properties(PropertyPredicateBuilder... builders) {
        return (root, criteriaQuery, cb) -> {
            Predicate[] predicates = new Predicate[builders.length];
            for (int i = 0; i < builders.length; i++) {
                PropertyPredicateBuilder builder = builders[i];
                predicates[i] = builder.build(root, criteriaQuery, cb);
            }
            return cb.or(predicates);
        };
    }
    
    public static <T> Specification<T> propertiesAnd(PropertyPredicateBuilder... builders) {
        return (root, criteriaQuery, cb) -> {
            Predicate[] predicates = new Predicate[builders.length];
            for (int i = 0; i < builders.length; i++) {
                PropertyPredicateBuilder builder = builders[i];
                predicates[i] = builder.build(root, criteriaQuery, cb);
            }
            return cb.and(predicates);
        };
    }

    public static <T> Specification<T> nonDeleted() {
        return properties(equal("deleted", Boolean.FALSE));
    }
    
    private static <T> Predicate[] toPredicates(List<Specification<T>> specs, Root<T> root, CriteriaQuery<?> criteriaQuery,
                                                CriteriaBuilder cb) {
        Predicate[] predicates = new Predicate[specs.size()];
        for (int i = 0; i < specs.size(); i++) {
            Specification<T> spec = specs.get(i);
            predicates[i] = spec.toPredicate(root, criteriaQuery, cb);
        }
        return predicates;
    }

    static String[] split(String propertyName) {
        return propertyName.split("\\.");
    }

    private static <T> Join<?, ?> join(Root<T> root, String[] colProps) {
        Join<?, ?> lastJoin = null;
        for (String colProp : colProps) {
            if (lastJoin == null) {
                lastJoin = root.join(colProp);
            } else {
                lastJoin = lastJoin.join(colProp);
            }
        }
        return lastJoin;
    }
	
}
