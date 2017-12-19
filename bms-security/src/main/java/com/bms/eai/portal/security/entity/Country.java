package com.bms.eai.portal.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bms.eai.portal.security.entity.core.AbstractBmsBaseEntity;

/**
 * @author kul_sudhakar
 *
 */
@Entity
@Table(name = "ref_country")
public class Country extends AbstractBmsBaseEntity<Country, String> {

	@Id
	@Column(length = 2)
	private String code = null;
	@Column(length = 45)
	private String tag = null;

	public Country() {

	}

	public Country(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	protected String getId() {
		return code;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(Country fromEntity) {
		setTag(fromEntity.getTag());
	}

}
