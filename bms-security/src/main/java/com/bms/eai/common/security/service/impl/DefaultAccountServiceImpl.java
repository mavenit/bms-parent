package com.bms.eai.common.security.service.impl;

import static com.bms.eai.common.dao.impl.FindByPropertyValueSpecifications.and;
import static com.bms.eai.common.dao.impl.FindByPropertyValueSpecifications.collectionProperties;
import static com.bms.eai.common.dao.impl.FindByPropertyValueSpecifications.equal;
import static com.bms.eai.common.dao.impl.FindByPropertyValueSpecifications.like;
import static com.bms.eai.common.dao.impl.FindByPropertyValueSpecifications.nonDeleted;
import static com.bms.eai.common.dao.impl.FindByPropertyValueSpecifications.or;
import static com.bms.eai.common.dao.impl.FindByPropertyValueSpecifications.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.cmn.error.support.ServiceExceptionHelper;
import com.bms.eai.common.security.encryption.EncryptionException;
import com.bms.eai.common.security.encryption.EncryptionManager;
import com.bms.eai.common.security.encryption.EncryptionToken;
import com.bms.eai.common.service.AccountService;
import com.bms.eai.common.service.impl.AbstractBaseCrudService;
import com.bms.eai.lib.WildcardValueHelper;
import com.bms.eai.portal.security.entity.Account;
import com.bms.eai.portal.security.lib.BmsServiceErrorCodes;
import com.bms.eai.portal.security.lib.CodecUtils;
import com.bms.eai.portal.security.lib.DateUtils;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class DefaultAccountServiceImpl extends AbstractBaseCrudService<Account, Long> implements AccountService {

	@Autowired
	private EncryptionManager encryptionManager;

	@Override
    @Transactional(readOnly = true)
    public Page<Account> searchByNameOrNricOrPassport(String searchValue, Pageable p) throws ServiceException {
        try {
            searchValue = WildcardValueHelper.decorate(searchValue);
            return getRepository().findAll(and(
                    or(
                            properties(like("profile.fullName", searchValue, true)),
                            properties(like("profile.passportNo", searchValue, false)),
                            properties(like("profile.nric", searchValue, false))
                    ),
                    nonDeleted()
            ), p);
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, BmsServiceErrorCodes.SEARCH_ACCOUNT_BY_ACCOUNT_DETAILS_ERROR,
                    searchValue, e.getMessage());
        }
    }
	
	@Override
    @Transactional(readOnly = true)
    public Page<Account> searchByOwnerNameOrNricOrPassport(String searchValue, Pageable p) throws ServiceException {
        try {
            searchValue = WildcardValueHelper.decorate(searchValue);

            return getRepository().findAll(and(
                    collectionProperties("owners",
                            like("profile.fullName", searchValue, true),
                            like("profile.passportNo", searchValue, false),
                            like("profile.nric", searchValue, false)),
                    collectionProperties("owners", equal("deleted", false)),
                    nonDeleted()
            ), p);
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, BmsServiceErrorCodes.SEARCH_ACCOUNT_BY_OWNER_DETAILS_ERROR,
                    searchValue, e.getMessage());
        }
    }
	
	@Override
    @Transactional(readOnly = true)
    public Account getByUsername(String username) throws ServiceException {
        try {
            return getRepository().findOne(and(
                    properties(equal(Account.USERNAME_PROPERTY_NAME, username)),
                    nonDeleted()
            ));
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, BmsServiceErrorCodes.GET_BY_USERNAME_ERROR, username,
                    e.getMessage());
        }
    }

	@Override
    @Transactional(readOnly = true)
    public boolean usernameExists(String username) throws ServiceException {
        try {
            return getRepository().count(and(
                    properties(equal(Account.USERNAME_PROPERTY_NAME, username)),
                    nonDeleted()
            )) > 0;
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, BmsServiceErrorCodes.CHECK_USERNAME_EXISTS_ERROR, username,
                    e.getMessage());
        }
    }
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
    public Account addAccount(Account account, String password) throws ServiceException {
        try {
            EncryptionToken encrypted = encryptionManager.encrypt(password);
            account.setPassword(CodecUtils.encodeBsae64(encrypted.getEncrypted()));
            account.setSalt(CodecUtils.encodeBsae64(encrypted.getSalt()));
            account.setIteration(encrypted.getIteration());
            return add(account);
        } catch (EncryptionException e) {
            throw ServiceExceptionHelper.wrap(e, BmsServiceErrorCodes.ADD_ACCOUNT_ENCRYPTION_ERROR,
                    account.getUsername(), password, e.getMessage());
        }
    }

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
    public Account updateToActive(Long accountId) throws ServiceException {
        Account account = getById(accountId);
        if (account != null) {
            try {
                account.setActive(true);
                account.setActivationTimestamp(DateUtils.now());
                return getRepository().saveAndFlush(account);
            } catch (Exception e) {
                throw ServiceExceptionHelper.wrap(e, BmsServiceErrorCodes.UPDATE_TO_ACTIVE_ERROR, accountId,
                        e.getMessage());
            }
        } else {
            throw ServiceExceptionHelper.invalid(BmsServiceErrorCodes.UPDATE_TO_ACTIVE_ACCOUNT_NOT_FOUND,
                    accountId);
        }
    }
	
}
