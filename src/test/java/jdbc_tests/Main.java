package jdbc_tests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@100.26.102.120 :1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        // create connection between db and project
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement();

        // run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("select * from departments");

//        // move pointer to the first row
//        resultSet.next();
//        System.out.println(resultSet.getString(1) + " - " +  resultSet.getString("region_name"));
//        resultSet.next();
//        System.out.println(resultSet.getString(1) + " - " +  resultSet.getString("region_name"));


        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + " - " +  resultSet.getString(2) + " - " + resultSet.getString(3) +" - " + resultSet.getString(4) );
        }


        //close all connections
        connection.close();;
        statement.close();
        resultSet.close();

    }

}
