package jdbc_tests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOFMaps_for_DB {

    String dbUrl = "jdbc:oracle:thin:@100.26.102.120 :1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";



    @Test
    public void MetaDataExample() throws SQLException {

// create connection between db and project
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("select * from employees");


        // get resultSet object metaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();



        Map<String,Object> row1 = new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_Pres");


        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochhar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");


        System.out.println("row2.get(\"salary\") = " + row2.get("salary"));


        List<Map<String,Object>> list = new ArrayList<>();
        list.add(row1);
        list.add(row2);

        System.out.println("list.get(0).get(\"first_name\") = " + list.get(0).get("first_name"));
        System.out.println("list.get(0).get(\"first_name\") = " + list.get(0).get("last_name"));



        //close all connections
        connection.close();;
        statement.close();
        resultSet.close();

    }

    @Test
    public void MetaDataExample2() throws SQLException {

        // create connection between db and project
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("select first_name, last_name, salary, job_id\n" +
                "from employees\n" +
                "where rownum <= 5");
        // get resultSet object metaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        resultSet.next();

        Map<String,Object> row1 = new HashMap<>();
        row1.put(resultSetMetaData.getColumnName(1), resultSet.getString(1));
        row1.put(resultSetMetaData.getColumnName(2), resultSet.getString(2));
        row1.put(resultSetMetaData.getColumnName(3), resultSet.getString(3));
        row1.put(resultSetMetaData.getColumnName(4), resultSet.getString(4));


        System.out.println("row1.toString() = " + row1.toString());

        resultSet.next();
        Map<String,Object> row2 = new HashMap<>();
        row2.put(resultSetMetaData.getColumnName(1), resultSet.getString(1));
        row2.put(resultSetMetaData.getColumnName(2), resultSet.getString(2));
        row2.put(resultSetMetaData.getColumnName(3), resultSet.getString(3));
        row2.put(resultSetMetaData.getColumnName(4), resultSet.getString(4));

        System.out.println("row2.toString() = " + row2.toString());

        List<Map<String,Object>> list = new ArrayList<>();
        list.add(row1);
        list.add(row2);

        System.out.println(list.get(1).get("SALARY"));


        //close all connections
        connection.close();;
        statement.close();
        resultSet.close();

    }

}
