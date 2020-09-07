package com.alevel.courses.modules.module3;

import com.alevel.courses.modules.module3.service.InitDbService;
import com.alevel.courses.modules.module3.service.OperationServiceJPA;
import com.alevel.courses.modules.module3.service.StatementServiceJDBC;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

public class Application {

    private static Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        //For simple testing
        args = new String[]{"1", "1", "postgres", "3176rz1t", "1", "500", "Salary", "Gifts"};

        //args:
        // 0 - chosen functionality
        // 1 - user id
        // 2 - db username
        // 3 - db password
        // 4 - account id
        //                                  if functionality is
        // JPA-provided                                          JDBC-provided
        // 5 -  operation amount                            5 - time from
        // 6 - first operation category                     6 - time to
        // ... - remaining operation categories

        String dbUsername = args[2];
        String dbPassword = args[3];

        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.username", dbUsername)
                .setProperty("hibernate.connection.password", dbPassword)
                .configure();

        long userId = Long.valueOf(args[1]);
        long accountId = Long.valueOf(args[4]);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            InitDbService initDbService = new InitDbService(sessionFactory);
            Instant[] instants = initDbService.initDb();

            if (args[0].equals("1")) {
                long operationAmount = Long.valueOf(args[5]);
                String[] curentOperationCategories = Arrays.copyOfRange(args, 6, args.length - 1);
                OperationServiceJPA operationServiceJPA = new OperationServiceJPA(sessionFactory);
                operationServiceJPA.addOperationByCurrentUser(userId, accountId, operationAmount, curentOperationCategories);
            }


            //For simple testing
            args = new String[]{"2", "1", "postgres", "3176rz1t", "1", "2020-09-06T14:07:48", "2020-09-06T14:07:49"};
            //For demonstration timestamps for requested operation are created by InitDbService
            //(timestamps between initial operations)
            Timestamp from = Timestamp.from(instants[0]);
            Timestamp to = Timestamp.from(instants[1]);

            if (args[0].equals("2")) {

//               Timestamp from = formatStringISOtoTimestamp(args[5]);
//               Timestamp to = formatStringISOtoTimestamp(args[6]);
                log.debug("from = " + from);
                log.debug("to = " + to);
                log.debug("acount_id = " + args[1]);

                StatementServiceJDBC statementServiceJDBC = new StatementServiceJDBC(dbUsername, dbPassword);
                statementServiceJDBC.getStatements(userId, accountId, from, to);
            }
        }
    }
}
