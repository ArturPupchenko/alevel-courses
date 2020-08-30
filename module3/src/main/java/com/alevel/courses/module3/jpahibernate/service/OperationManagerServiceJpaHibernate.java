//package com.alevel.courses.module3.service.impl;
//
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import java.util.Arrays;
//
//public class OperationManagerServiceJpaHibernate {
//
//    public void addNewOperationFromConsole(String[] args) {
//        String userId = args[1];
//        String dbUser = args[2];
//        String dbPassword = args[3];
//        String account = args[4];
//        String amount = args[5];
//        String numberOfCategories = args[6];
//        String[] categoriesIds = Arrays.copyOfRange(args, 7, args.length - 1);
//
//        if (amount > 0)
//
//            Configuration configuration = new Configuration().configure();
//        configuration.setProperty("hibernate.connection.username", dbUser);
//        configuration.setProperty("hibernate.connection.password", dbPassword);
//
//        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
//             Session session = sessionFactory.openSession()) {
//            final UserEntityDao userDao = new UserEntityDaoJpaHibernate(session);
//            final ExpenseCategoryEntityDao expenseCategoryDao = new ExpenseCategoryEntityDaoJpaHibernate(session);
//            final IncomeCategoryEntityDao incomeCategoryDao = new IncomeCategoryEntityDaoJpaHibernate(session);
//
//            final OperationEntityDao operationDao = new OperationEntityDaoJpaHibernate(session);
//
//            User currentUser = userDao.findById(Long.parseLong(userId));
//            Account currentAccount = currentUser.getAccounts().get(0);
//            Long currentAmount = 1000L;
//            IncomeCategory currentIncomeCategoryOne = incomeCategoryDao.findById(1L);
//            IncomeCategory currentIncomeCategoryTwo = incomeCategoryDao.findById(3L);
//            Income incomeOne = new Income(currentAccount, currentAmount);
//            incomeOne.getCategories().add(currentIncomeCategoryOne);
//            incomeOne.getCategories().add(currentIncomeCategoryTwo);
//
//            session.getTransaction().begin();
//            operationDao.saveOrUpdate(incomeOne);
//            session.getTransaction().commit();
//        }
//    }
//
//}
