package com.alevel.courses.modules.module3.service;

import com.alevel.courses.modules.module3.dao.AccountDao;
import com.alevel.courses.modules.module3.dao.OperationDao;
import com.alevel.courses.modules.module3.entity.Account;
import org.hibernate.SessionFactory;

public class OperationServiceJPA {

    private final SessionFactory sessionFactory;
    private final OperationDao operationDao;
    private final AccountDao accountDao;

    public OperationServiceJPA(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.operationDao = new OperationDao(sessionFactory);
        this.accountDao = new AccountDao(sessionFactory);

    }

    public void addOperationByCurrentUser(long userId, long accountId, long amount, String... categories) {
        Account currentAccount = accountDao.getById(accountId);
        if (currentAccount.getUser().getId().equals(userId))
            operationDao.addOperation(accountId, amount, categories);
    }
}
