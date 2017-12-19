package com.bms.eai.portal.security.entity.core;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bms.eai.portal.security.lib.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author kul_sudhakar
 *
 */
@MappedSuperclass
public abstract class AbstractBmsSimpleEntity<T extends AbstractBmsSimpleEntity, ID extends Serializable>
		extends AbstractBmsBaseEntity<T, ID> {

	@Column(name = "createDate")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_TIME_FORMAT)
	private Date createDate = null;

	@PrePersist
	public void prePersist() {
		this.setCreateDate(new Date());
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
