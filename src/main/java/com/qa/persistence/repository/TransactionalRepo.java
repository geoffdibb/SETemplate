package com.qa.persistence.repository;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Default
@Transactional(TxType.SUPPORTS)
public class TransactionalRepo implements AccountRepository, BusinessLogic {

	@PersistenceContext(unitName = "primary")

	private EntityManager em;

	@Override
	@Transactional(TxType.SUPPORTS)
	public String getAllAccounts() {

		TypedQuery<Account> query = null;
		query = em.createQuery("SELECT a FROM Account a", Account.class);
		List<Account> accList = query.getResultList();
		return new JSONUtil().getJSONForObject(accList);

	}

	@Transactional(TxType.SUPPORTS)
	public Account findAnAccount(int accountNumber) {
		return em.find(Account.class, accountNumber);
	}

	@Override
	@Transactional(TxType.REQUIRED)
	public String createAccount(String account) {
		Account newAccount = new JSONUtil().getObjectForJSON(account, Account.class);
		em.persist(newAccount);

		return "Account made";
	}

	@Transactional(TxType.REQUIRED)
	public String deleteAccount(int accountNumber) {
		em.remove(em.find(Account.class, accountNumber));
		return "removed";
	}

	@Override
	@Transactional(TxType.REQUIRED)
	public String updateAccount(int accountNumber, String account) {
		Account newAccount = new JSONUtil().getObjectForJSON(account, Account.class);
		em.persist(newAccount);
		return null;
	}

	@Override
	public String CreateBannedAccount(String account) {
		Account newAccount = new JSONUtil().getObjectForJSON(account, Account.class);
		if (account == "9999") {
			return "User is banned";
		}
		em.persist(newAccount);

		return "Account made";
	}

}
