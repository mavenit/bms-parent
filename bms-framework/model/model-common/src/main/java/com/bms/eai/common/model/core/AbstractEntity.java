package com.bms.eai.common.model.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.FilterDef;

import com.bms.eai.lib.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings("rawtypes")
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@FilterDef(
        name = AbstractEntity.NON_DELETED_FILTER_NAME,
        defaultCondition = AbstractEntity.NON_DELETED_CONDITION
)
@MappedSuperclass
public abstract class AbstractEntity <T extends AbstractEntity, ID extends Serializable> /*extends Auditable<T>*/ implements Serializable {

	private static final long serialVersionUID = -9032889008241950269L;
	public static final String NON_DELETED_FILTER_NAME = "non-deleted";
	public static final String NON_DELETED_CONDITION = "deleted = 0";

	@JsonIgnore
	@Column(nullable = false)
    private boolean deleted = false;
	
	@JsonIgnore
	@Column(name = "created_by")
	private String createBy = null;

	@JsonIgnore
	@Column(name = "created_dt")
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_TIME_FORMAT)
	private Date createDt = null;

	@JsonIgnore
	@Column(name = "updated_by")
	private String updateBy = null;

	@JsonIgnore
	@Column(name = "updated_dt")
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_TIME_FORMAT)
	private Date updateDt = null;
	
	@JsonIgnore
	@Column(name = "deleted_by")
	private String deleted_by = null;

	@JsonIgnore
	@Column(name = "deleted_dt")
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_TIME_FORMAT)
	private Date deleted_dt = null;

	protected abstract ID getId();
	
	protected abstract void setId(ID id);
	
	protected abstract void doCopyUpdateFieldsFrom(T fromEntity);

	/**
	 * Get the value of the ID field for equals() and hashCode() methods.
	 *
	 * @return value of the ID field.
	 */
	 
	  @PrePersist 
	  public void prePersist() {
	   this.setCreateDt(DateUtils.now());
	   String createdBy = getCurrentUsername();
	   this.setCreatedBy(createdBy);
	  }
	

	/**
	 * Method to be triggered right before the JPA update operation to the database.
	 * This will set the updatedBy to the currently logged in user and updateTime to
	 * current time.
	 */
	 
	 @PreUpdate 
	 public void preUpdate() { 
	 	this.setUpdateDt(DateUtils.now());
	 	String updatedBy = getCurrentUsername();
	 	this.setUpdateBy(updatedBy); 
	 }
	 
	 @PreRemove 
	 public void preDelete() { 
	 	this.setDeleted(true);
	 	this.setDeleted_dt(DateUtils.now());
	 	String deletedBy = getCurrentUsername();
	 	this.setDeleted_by(deletedBy);
	 }
	
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
	    
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createBy = createdBy;
	}

	public void copyUpdateFieldsFrom(T fromEntity) {
		doCopyUpdateFieldsFrom(fromEntity);
	}

	protected String getCurrentUsername() {
	    String currentUsername = "EVENT-MODULE";		     
	    return currentUsername;
	} 
	
	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_dt() {
		return deleted_dt;
	}

	public void setDeleted_dt(Date deleted_dt) {
		this.deleted_dt = deleted_dt;
	}

	/**
	 * Compares the ID value as per returned by the getId() method implemented by
	 * the actual entity class.
	 *
	 * @param o
	 *            The other object to compare to.
	 * @return True if this object is having the same ID as the other object.
	 *         Returns false if ID for one of the objects is null. If ID is null for
	 *         both objects, false is returned as well.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Serializable id = getId();

		AbstractEntity entity = (AbstractEntity) o;

		return (id != null && id.equals(entity.getId()));
	}

	@Override
	public int hashCode() {
		Serializable id = getId();
		return id != null ? id.hashCode() : super.hashCode();
	}
	
}
