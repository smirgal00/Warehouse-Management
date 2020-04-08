package DataAccess;

import java.sql.*;
import Connection.DBase;

/**
 * Class used for executing given queries
 * And returning their results, if necessary
 */

public class Data {
    /**
     * Represents the connection instance of the database
     */
    Connection connection;

    public Data() {
        connection = DBase.getConnection();
    }

    private PreparedStatement generateStatement(String query) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Couldn't execute query " + query);
            return null;
        }

        return statement;
    }

    /**
     *
     * @param query Represents the SQL query that need to be executed
     * @return Returns the result from the query, saved in a specific class used to store them
     */

    public ResultSet createResult(String query) {
        ResultSet resultSet = null;
        PreparedStatement statement = generateStatement(query);

        try {
            if (statement != null) {
                resultSet = statement.executeQuery();
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Couldn't load ResultSet from query " + query);
            return null;
        }

        return resultSet;
    }

    /**
     *
     * @param query Represents the SQL query that need to be executed
     */

    public void callProcedure(String query) {
        PreparedStatement statement = generateStatement(query);

        try {
            if(statement != null) {
                statement.executeQuery(query);
            }
        } catch (SQLException e) {
            if(query.contains("insertClient")) {
                System.out.println("Clientul deja exista!");
            }
        }
    }
}
