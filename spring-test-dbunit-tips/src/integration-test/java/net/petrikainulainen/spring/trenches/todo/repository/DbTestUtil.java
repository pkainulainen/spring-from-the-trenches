package net.petrikainulainen.spring.trenches.todo.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to invoke SQL statements to the database before our test cases are run. You
 * can use this class as long as:
 *
 * <ul>
 *     <li>
 *         You run your integration tests by using the {@link org.springframework.test.context.junit4.SpringJUnit4ClassRunner}
 *         class and configure the used application context configuration class or file by using the
 *         {@link org.springframework.test.context.ContextConfiguration} annotation.
 *     </li>
 *     <li>
 *         Your application context configuration configures both {@link javax.sql.DataSource}
 *         and {@link org.springframework.core.env.Environment} beans. The {@link javax.sql.DataSource} bean
 *         is used to open a database connection. The {@link org.springframework.core.env.Environment} bean
 *         is used to obtain the used SQL statement template from a properties file.
 *     </li>
 *     <li>
 *         You have configured the SQL statement template in the properties file of your application
 *         by using the key 'test.reset.sql.template'. This template must be configured by using the
 *         format that is supported by the {@link String#format(String, Object...)} method.
 *     </li>
 * </ul>
 *
 * @author Petri Kainulainen
 */
public class DbTestUtil {

    private static final String PROPERTY_KEY_RESET_SQL_TEMPLATE = "test.reset.sql.template";

    /**
     * This method reads the invoked SQL statement template from a properties file, creates
     * the invoked SQL statements, and invokes them.
     *
     * @param applicationContext    The application context that is used by our tests.
     * @param resetSqlArguments     The arguments that are used to create the actual SQL statements. One argument
     *                              is used to create one SQL statement.
     * @throws SQLException         If a SQL statement cannot be invoked for some reason.
     */
    public static void invokeSql(ApplicationContext applicationContext, String... resetSqlArguments) throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        String resetSqlTemplate = getResetSqlTemplate(applicationContext);
        try (Connection dbConnection = dataSource.getConnection()) {
            for (String resetSqlArgument: resetSqlArguments) {
                try (Statement statement = dbConnection.createStatement()) {
                    String resetSql = String.format(resetSqlTemplate, resetSqlArgument);
                    statement.execute(resetSql);
                }
            }
        }
    }

    private static String getResetSqlTemplate(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getBean(Environment.class);
        return environment.getRequiredProperty(PROPERTY_KEY_RESET_SQL_TEMPLATE);
    }
}
