package com.bms.eai.portal.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bms.eai.portal.security.entity.core.AbstractBmsEntity;

/**
 * @author kul_sudhakar
 *
 */
@Entity
@Table(name = "contact")
public class Contact extends AbstractBmsEntity<Contact, Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	@Column(name = "address1", length = 100)
	private String address1 = null;
	
	@Column(name = "address2", length = 100)
	private String address2 = null;
	
	@Column(name = "address3", length = 100)
	private String address3 = null;
	
	@Column(name = "city", length = 100)
	private String city = null;
	
	@Column(name = "postcode", length = 10)
	private String postcode = null;
	
	@Column(name = "state", length = 45)
	private String state = null;
	
	@ManyToOne
	@JoinColumn(name = "countryCode")
	private Country country = null;
	
	@Column(name = "email", length = 100)
	private String email = null;
	
	@Column(name = "fixPhone", length = 45)
	private String fixPhone = null;
	
	@Column(name = "mobilePhone", length = 45)
	private String mobilePhone = null;

	@Override
    public Long getId() {
        return id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFixPhone() {
        return fixPhone;
    }

    public void setFixPhone(String fixPhone) {
        this.fixPhone = fixPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    protected void doCopyUpdateFieldsFrom(Contact fromEntity) {
        setAddress1(fromEntity.getAddress1());
        setAddress2(fromEntity.getAddress2());
        setAddress3(fromEntity.getAddress3());
        setCity(fromEntity.getCity());
        setPostcode(fromEntity.getPostcode());
        setState(fromEntity.getState());
        setCountry(fromEntity.getCountry());
        setFixPhone(fromEntity.getFixPhone());
        setMobilePhone(fromEntity.getMobilePhone());
        setEmail(fromEntity.getEmail());
    }

	
}
