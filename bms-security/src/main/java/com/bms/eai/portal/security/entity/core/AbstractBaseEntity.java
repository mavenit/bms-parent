package com.bms.eai.portal.security.entity.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.FilterDef;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author kul_sudhakar
 *
 */
@MappedSuperclass
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@FilterDef(name = AbstractBaseEntity.NON_DELETED_FILTER_NAME, defaultCondition = AbstractBaseEntity.NON_DELETED_CONDITION)
public abstract class AbstractBaseEntity<T extends AbstractBaseEntity, ID extends Serializable>
		implements Serializable {

	public static final String NON_DELETED_FILTER_NAME = "non-deleted";
	public static final String NON_DELETED_CONDITION = "deleted = 0";

	@Column
	@JsonIgnore
	private boolean deleted = false;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
     * Copy the fields to be updated from the entity passed in to the fields for this object.
     *
     * @param fromEntity The entity of which the fields to be updated will be copied from.
     */
    public void copyUpdateFieldsFrom(T fromEntity) {
        doCopyUpdateFieldsFrom(fromEntity);
    }

    /**
     * Get the value of the ID field for equals() and hashCode() methods.
     *
     * @return value of the ID field.
     */
    protected abstract ID getId();

    protected String getCurrentUsername() {
        String currentUsername = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal != null && principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                currentUsername = userDetails.getUsername();
            }
        }
        if (currentUsername == null) {
            currentUsername = "SYS";
        }
        return currentUsername;
    }

    /**
     * Compares the ID value as per returned by the getId() method implemented by the actual entity class.
     *
     * @param o The other object to compare to.
     * @return True if this object is having the same ID as the other object. Returns false if ID for one of the objects
     * is null. If ID is null for both objects, false is returned as well.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Serializable id = getId();

        AbstractBaseEntity entity = (AbstractBaseEntity) o;

        return (id != null && id.equals(entity.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        Serializable id = getId();
        return id != null ? id.hashCode() : super.hashCode();
    }

    protected abstract void doCopyUpdateFieldsFrom(T fromEntity);
	
}
