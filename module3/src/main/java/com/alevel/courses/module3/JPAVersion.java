package com.alevel.courses.module3;


import com.alevel.courses.module3.jpahibernate.contoller.AccountController;
import com.alevel.courses.module3.jpahibernate.contoller.CategoryController;
import com.alevel.courses.module3.jpahibernate.contoller.OperationController;
import com.alevel.courses.module3.jpahibernate.dao.AccountDao;
import com.alevel.courses.module3.jpahibernate.dao.ExpenseCategoryDao;
import com.alevel.courses.module3.jpahibernate.dao.IncomeCategoryDao;
import com.alevel.courses.module3.jpahibernate.dao.OperationDao;
import com.alevel.courses.module3.jpahibernate.service.OperationsControlService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class JPAVersion {

    public static void run(String[] args) {

        final Long id = Long.valueOf(args[0]);
        final String username = args[1];
        final String password = args[2];

        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.current_session_context_class","org.hibernate.context.internal.ThreadLocalSessionContext")
                .configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            final OperationDao operationDao = new OperationDao(sessionFactory);
            final OperationController operationController = new OperationController(operationDao);

            final IncomeCategoryDao incomeCategoryDao = new IncomeCategoryDao(sessionFactory);
            final ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDao(sessionFactory);
            final CategoryController categoryController = new CategoryController(expenseCategoryDao, incomeCategoryDao);

            final AccountDao accountDao = new AccountDao(sessionFactory);
            final AccountController accountController = new AccountController(accountDao);

            final OperationsControlService operationMaker = new OperationsControlService(sessionFactory, operationController, categoryController, accountController);

            operationMaker.createOperation(id);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
