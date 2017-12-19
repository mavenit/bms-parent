package com.bms.eai.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.portal.security.entity.Account;

/**
 * @author kul_sudhakar
 *
 */
public interface AccountService extends BaseCrudService<Account, Long> {

	Page<Account> searchByNameOrNricOrPassport(String searchValue, Pageable p) throws ServiceException;

    Page<Account> searchByOwnerNameOrNricOrPassport(String searchValue, Pageable p) throws ServiceException;

    Account getByUsername(String username) throws ServiceException;

    boolean usernameExists(String username) throws ServiceException;

    Account addAccount(Account account, String password) throws ServiceException;

    Account updateToActive(Long accountId) throws ServiceException;
	
}
