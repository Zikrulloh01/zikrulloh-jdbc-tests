package jdbc_tests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {
    String dbUrl = "jdbc:oracle:thin:@100.26.102.120 :1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

// create connection between db and project
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("select * from departments");






        while(resultSet.next()){
            System.out.println(resultSet.getRow() + " " + resultSet.getString(2));
        }

        System.out.println("===================================================================");
        resultSet = statement.executeQuery("select * from regions");
        while(resultSet.next()){
            System.out.println(resultSet.getRow() + " " + resultSet.getString(2));
        }

        //close all connections
        connection.close();;
        statement.close();
        resultSet.close();

    }



    @Test
    public void MetaDataExample() throws SQLException {

// create connection between db and project
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("select * from employees");


        // get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();
        System.out.println("User = " + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());

        System.out.println("==========================================================");

        // get resultSet object metaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int colCount = resultSetMetaData.getColumnCount();
        System.out.println("colCount = " + colCount);

        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName(2) = " + resultSetMetaData.getColumnName(2));

        for(int i = 1; i <= resultSetMetaData.getColumnCount(); i ++ )
        {
            System.out.println(i + ". " + resultSetMetaData.getColumnName(i));
        }


        //close all connections
        connection.close();;
        statement.close();
        resultSet.close();

    }
}
