package com.bms.eai.portal.security.entity.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bms.eai.portal.security.lib.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author kul_sudhakar
 *
 */
@MappedSuperclass
public abstract class AbstractBmsEntity <T extends AbstractBmsEntity, ID extends Serializable>extends AbstractBmsSimpleEntity<T, ID> {

	@Column(name = "updateDate")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_TIME_FORMAT)
    private Date updateDate = null;

    @PreUpdate
    public void preUpdate() {
        this.setUpdateDate(new Date());
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
	
}
