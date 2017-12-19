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
@Table(name = "account")
public class Account extends AbstractBmsEntity<Account, Long> {

	public static final String USERNAME_PROPERTY_NAME = "username";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "profileId")
	private Profile profile = null;

	@Column(name = "username", length = 100)
	private String username = null;

	@Column(name = "password", length = 256)
	private String password = null;

	@Column(name = "salt", length = 256)
	private String salt = null;

	@Column(name = "iteration")
	private int iteration = 0;

	@Column(name = "token", length = 256)
	private String token = null;

	@Column(name = "tokenTimestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenTimestamp = null;

	@Column(name = "retryCount")
	private short retryCount = 0;

	@Column(name = "isProfileComplete")
	private boolean profileComplete = false;

	@Column(name = "isReset")
	private boolean reset = false;

	@Column(name = "isActive")
	private boolean active = false;

	@Column(name = "activationTimestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date activationTimestamp = null;

	@Override
	public Long getId() {
		return id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenTimestamp() {
		return tokenTimestamp;
	}

	public void setTokenTimestamp(Date tokenTimestamp) {
		this.tokenTimestamp = tokenTimestamp;
	}

	public short getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(short retryCount) {
		this.retryCount = retryCount;
	}

	public boolean isProfileComplete() {
		return profileComplete;
	}

	public void setProfileComplete(boolean profileComplete) {
		this.profileComplete = profileComplete;
	}

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getActivationTimestamp() {
		return activationTimestamp;
	}

	public void setActivationTimestamp(Date activationTimestamp) {
		this.activationTimestamp = activationTimestamp;
	}

	@Override
	protected void doCopyUpdateFieldsFrom(Account fromEntity) {
		if (fromEntity.getProfile() != null) {
			if (getProfile() == null) {
				setProfile(new Profile());
			}
			getProfile().doCopyUpdateFieldsFrom(fromEntity.getProfile());
			setProfileComplete(true);
		}
	}

}
