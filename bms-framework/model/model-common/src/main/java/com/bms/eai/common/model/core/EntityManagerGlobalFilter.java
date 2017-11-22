package com.bms.eai.common.model.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kul_sudhakar
 *
 */
@Aspect
public class EntityManagerGlobalFilter {

private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Pointcut("execution(* javax.persistence.EntityManagerFactory.createEntityManager(..))")
	protected void entityManagerFetch() {}
	
	@AfterReturning(pointcut = "entityManagerFetch()", returning = "result")
	public void enableGlobalFilter(JoinPoint joinPoint, Object result) {
		logger.info("Setting hibernate session filter.");
       /*EntityManager entityManager = (EntityManager) result;
       Session session = entityManager.unwrap(Session.class);
       Filter enabledFilter = session.getEnabledFilter(AbstractEntity.NON_DELETED_FILTER_NAME);
       if (enabledFilter == null) {
           session.enableFilter(AbstractEntity.NON_DELETED_FILTER_NAME);
       }*/
	 } 
	
}
