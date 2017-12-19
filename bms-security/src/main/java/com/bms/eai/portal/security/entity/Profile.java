package com.bms.eai.portal.security.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bms.eai.portal.security.entity.core.AbstractBmsEntity;

/**
 * @author kul_sudhakar
 *
 */
@Entity
@Table(name = "profile")
public class Profile extends AbstractBmsEntity<Profile, Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@Column(name = "fullName", length = 150)
	private String fullName = null;

	@ManyToOne
	@JoinColumn(name = "nationalityCode")
	private Country nationality = null;

	@Column(name = "nric", length = 20)
	private String nric = null;

	@Column(name = "passportNo", length = 20)
	private String passportNo = null;

	@Column(name = "passportExpDate")
	@Temporal(TemporalType.DATE)
	private Date passportExpDate = null;

	@Column(name = "licenseNo", length = 20)
	private String licenseNo = null;

	@Column(name = "licenseExpDate")
	@Temporal(TemporalType.DATE)
	private Date licenseExpDate = null;

	@Column(name = "isOrg")
	private boolean org = false;

	@Column(name = "orgName", length = 150)
	private String orgName = null;

	@Column(name = "orgRegNo", length = 20)
	private String orgRegNo = null;

	@Column(name = "orgDept", length = 100)
	private String orgDept = null;

	@Column(name = "orgContactPerson", length = 150)
	private String orgContactPerson = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "contactId")
	private Contact contact = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "contactCorrId")
	private Contact contactCorr = null;

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Country getNationality() {
		return nationality;
	}

	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getPassportExpDate() {
		return passportExpDate;
	}

	public void setPassportExpDate(Date passportExpDate) {
		this.passportExpDate = passportExpDate;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Date getLicenseExpDate() {
		return licenseExpDate;
	}

	public void setLicenseExpDate(Date licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}

	public boolean isOrg() {
		return org;
	}

	public void setOrg(boolean org) {
		this.org = org;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgRegNo() {
		return orgRegNo;
	}

	public void setOrgRegNo(String orgRegNo) {
		this.orgRegNo = orgRegNo;
	}

	public String getOrgDept() {
		return orgDept;
	}

	public void setOrgDept(String orgDept) {
		this.orgDept = orgDept;
	}

	public String getOrgContactPerson() {
		return orgContactPerson;
	}

	public void setOrgContactPerson(String orgContactPerson) {
		this.orgContactPerson = orgContactPerson;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Contact getContactCorr() {
		return contactCorr;
	}

	public void setContactCorr(Contact contactCorr) {
		this.contactCorr = contactCorr;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(Profile fromEntity) {
		setFullName(fromEntity.getFullName());
		setNationality(fromEntity.getNationality());
		setNric(fromEntity.getNric());
		setPassportNo(fromEntity.getPassportNo());
		setPassportExpDate(fromEntity.getPassportExpDate());
		setLicenseNo(fromEntity.getLicenseNo());
		setLicenseExpDate(fromEntity.getLicenseExpDate());
		setOrg(fromEntity.isOrg());
		setOrgName(fromEntity.getOrgName());
		setOrgRegNo(fromEntity.getOrgRegNo());
		setOrgDept(fromEntity.getOrgDept());
		setOrgContactPerson(fromEntity.getOrgContactPerson());
		if (fromEntity.getContactCorr() != null) {
			if (getContactCorr() == null) {
				setContactCorr(new Contact());
			}
			getContactCorr().copyUpdateFieldsFrom(fromEntity.getContactCorr());
		} else {
			setContactCorr(null);
		}
		if (fromEntity.getContact() != null) {
			if (getContact() == null) {
				setContact(new Contact());
			}
			getContact().copyUpdateFieldsFrom(fromEntity.getContact());
		} else {
			setContact(null);
		}
	}

}
