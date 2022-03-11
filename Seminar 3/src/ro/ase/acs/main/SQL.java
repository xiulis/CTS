package ro.ase.acs.main;

import ro.ase.acs.database.Database;
import ro.ase.acs.database.Insertable;
import ro.ase.acs.database.Readable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class SQL implements Database, Insertable, Readable {
    Connection connection;

    @Override
    public void createDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(String nameTable) {
        String sqlDrop = "DROP TABLE IF EXISTS "+nameTable;
        String sqlCreate = "CREATE TABLE "+nameTable+" (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT, address TEXT, salary REAL)";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlDrop);
            statement.executeUpdate(sqlCreate);
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertData(Map<String, String> data, String tableName) {
        String sqlInsertWithParams = "INSERT INTO "+tableName+" (name,address,salary) VALUES (?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlInsertWithParams);

            preparedStatement.setString(1, (String)data.get("name"));
            preparedStatement.setString(2, (String)data.get("address"));
            preparedStatement.setDouble(3, Double.parseDouble(data.get("salary")));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readData(String tableName) {
        String sqlSelect = "SELECT * FROM "+tableName;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlSelect);
            while(rs.next()) {
                int id = rs.getInt("id");
                System.out.println("id: " + id);
                String name = rs.getString(2);
                System.out.println("name: " + name);
                String address = rs.getString("address");
                System.out.println("address: " + address);
                double salary = rs.getDouble("salary");
                System.out.println("salary: " + salary);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
