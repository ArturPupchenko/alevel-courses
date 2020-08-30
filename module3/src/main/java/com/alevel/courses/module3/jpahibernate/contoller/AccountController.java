package com.alevel.courses.module3.jpahibernate.contoller;


import com.alevel.courses.module3.jpahibernate.dao.AccountDao;
import com.alevel.courses.module3.jpahibernate.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void updateBalance(Long amount, Long accountId) {
        Account account = accountDao.findAccountById(accountId);
        if (amount != 0 && account != null) {
            Long balance = account.getBalance() + amount;
            account.setBalance(balance);
            accountDao.saveOrUpdate(account);
            if (amount < 0) {
                log.info("User " + account.getUser().getName() + " removed " + amount + " from account "
                        + account.getName());
            } else {
                log.info("User " + account.getUser().getName() + " added " + amount + " from account "
                        + account.getName());
            }
        }
    }

}
