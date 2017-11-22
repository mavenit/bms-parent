package com.bms.eai.common.model.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.cmn.error.support.ServiceExceptionHelper;
import com.bms.eai.common.model.support.FindByPropertyValueSpecifications;
import com.bms.eai.error.codes.FrameworkErrorCodes;
import com.bms.eai.lib.WildcardValueHelper;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings({"unused"})
public class AbstractCrudService <T extends AbstractEntity<T,ID>, ID extends Serializable>implements ICrudService<T, ID>  {

	protected Class<T> entityClass = null;
	protected String entityName = null;
	protected String tableName = null;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IJpaSpecificationRepository<T, ID> repository;
	
	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractCrudService() {
    	super();
        Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(getClass(), ICrudService.class);
        entityClass = (Class<T>) classes[0];
        entityName = EntityUtils.getEntityName(entityClass);
        tableName = entityClass.getAnnotation(Table.class).name();
    }
	
	@Override
    @Transactional(readOnly = true)
	public Page<T> getPaged(Pageable p) throws ServiceException {
	  try {
          return getRepository().findAll(FindByPropertyValueSpecifications.nonDeleted(), p);
      } catch (Exception e) {
          throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.GET_PAGE_ERROR, entityName, e.getMessage());
      }
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<T> getAll() throws ServiceException {
		try {
           return getRepository().findAll(FindByPropertyValueSpecifications.nonDeleted());
       } catch (Exception e) {
           throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.GET_ALL_ERROR, entityName, e.getMessage());
       }
	}
	
	@Override
	@Transactional(readOnly = true)
	public T getById(ID id) throws ServiceException {
		try {
            T one = getRepository().findOne(id);
            if (one != null && !one.isDeleted()) {
            //if (one != null) {
                return one;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.GET_BY_ID_ERROR, entityName, id,
                    e.getMessage());
        }
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public T add(T entity) throws ServiceException {
		try {
            preAdd(entity);
            T added = getRepository().save(entity);
            postAdd(added);
            return added;
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.ADD_ERROR, entityName, e.getMessage());
        }
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public List<T> addAll(List<T> entities) throws ServiceException {
		try {
            preAddAll(entities);
            List<T> addedList = getRepository().save(entities);
            postAddAll(addedList);
            return addedList;
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.ADD_ALL_ERROR, entityName, e.getMessage());
        }
	}
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public T update(ID id, T entity) throws ServiceException {
		try {
            T existing = getRepository().findOne(id);
            if (existing != null && !existing.isDeleted()) {
            //if (existing != null) {
                existing.copyUpdateFieldsFrom(entity);
                preUpdate(existing, entity);
                T updated = getRepository().saveAndFlush(existing);
                postUpdate(updated, entity);
                return updated;
            } else {
                throw ServiceExceptionHelper.invalid(FrameworkErrorCodes.UPDATE_ENTITY_NOT_FOUND, entityName,id);
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.UPDATE_ERROR, entityName, id,e.getMessage());
        }
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public void delete(ID id) throws ServiceException {
		try {
            T existing = getRepository().findOne(id);
            if (existing != null && !existing.isDeleted()) {
           // if (existing != null) {
                preDelete(id);
                existing.setDeleted(true);
                getRepository().saveAndFlush(existing);
                postDelete(id);
            } else {
                throw ServiceExceptionHelper.invalid(FrameworkErrorCodes.DELETE_ENTITY_NOT_FOUND, entityName,id);
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.DELETE_ERROR, entityName, id,e.getMessage());
        }
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public void deleteAll(List<ID> ids) throws ServiceException {
		try {
             for (ID id : ids) {
                delete(id);
            } 
           // ids.forEach(item->delete(item));
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.DELETE_ALL_ERROR, entityName, e.getMessage());
        }
	}
	
	@Override
	@Transactional(readOnly = true)
	public long totalCount() throws ServiceException {
		 try {
	            return getRepository().count(FindByPropertyValueSpecifications.nonDeleted());
	        } catch (Exception e) {
	            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.COUNT_ALL_ERROR, entityName,e.getMessage());
	        }
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean exists(ID id) throws ServiceException {
		try {
            T existing = getById(id);
            return existing != null;
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.COUNT_ALL_ERROR, entityName,e.getMessage());
        }
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<T> search(String searchTerm, Pageable p) throws ServiceException {
		try {
            searchTerm = WildcardValueHelper.decorate(searchTerm);
            //return null;
            return getRepository().findAll(FindByPropertyValueSpecifications.and(createSearchSpecification(searchTerm),FindByPropertyValueSpecifications.nonDeleted()), p);
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.SEARCH_ERROR, entityName, searchTerm,
                    e.getMessage());
        }
	}

	public final IJpaSpecificationRepository<T, ID> getRepository() {
        return repository;
    }
	
	@Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public String getEntityName() {
        return entityName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }
    
    protected void preAdd(T entity) throws Exception {

    }

    protected void postAdd(T added) throws Exception {

    }
    protected void preAddAll(List<T> entities) throws Exception {
        for (T entity : entities) {
            preAdd(entity);
        }
    }

    protected void postAddAll(List<T> addedEntities) throws Exception {
        for (T addedEntity : addedEntities) {
            postAdd(addedEntity);
        }
    }
    
    protected void preUpdate(T existing, T source) throws Exception {
    }

    protected void postUpdate(T updated, T source) throws Exception {

    }

    protected void preDelete(ID id) throws Exception {

    }

    protected void postDelete(ID id) throws Exception {
    }
    
    protected Specification<T> createSearchSpecification(String searchTerm) throws Exception {
        throw new ServiceException(FrameworkErrorCodes.SEARCH_NOT_SUPPORTED_ERROR, entityName);
    }
	
	
}
