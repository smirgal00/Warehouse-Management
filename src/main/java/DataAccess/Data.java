package DataAccess;

import java.io.IOException;
import java.sql.*;

import Connection.DBase;

public class Data {
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

    public void callProcedure(String query) {
        PreparedStatement statement = generateStatement(query);

        try {
            if(statement != null) {
                statement.executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
