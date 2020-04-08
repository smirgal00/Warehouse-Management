package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Singleton type class used for creating a connection to the database only once
 */

public class DBase {
    private static final Logger Logs = Logger.getLogger(DBase.class.getName());
    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/warehouse";
    private static final String User = "user=root";
    private static final String Pass = "password=Lenesu2@";

    private static DBase Database = new DBase();

    private DBase() {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL + "?" + User + "&" + Pass);
        } catch (SQLException e) {
            System.out.println("The connection could not be created!");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return Database.createConnection();
    }

    /**
     *
     * @param connection Connection used to access the database
     */

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection couldn't be closed!");
        }
    }

    /**
     *
     * @param statement Statement used for executing a query in the database
     */

    public static void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Statement couldn't be closed!");
        }
    }

}
