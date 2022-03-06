package reviews;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOracle {


    Connection connection;
    Statement statement;
    ResultSet resultSet;




    @BeforeMethod
    public void connect() throws SQLException {
        System.out.println("\tconnecting to db......");
        String dbUrl = "jdbc:oracle:thin:@100.26.102.120:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";
        String query = "select first_name, last_name, salary from employees";


        connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

    }



    @Test
    public void ConnectionTest() throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<Map<String,Object>> list = new ArrayList<>();
        while (resultSet.next()){
            Map<String,Object> map = new HashMap<>();
            for ( int i = 1; i <= resultSetMetaData.getColumnCount(); i ++ ){
                map.put(resultSetMetaData.getColumnName(i),resultSet.getObject(i));
            }
            list.add(map);
        }

        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap.toString());
        }

    }


    @Test
    public void verifyExample() throws SQLException {

        resultSet = statement.executeQuery("select salary\n" +
                "from employees\n" +
                "where employee_id = 100");

        resultSet.next();
        int expectedSalary = 24000;
        int actualSalary = resultSet.getInt(1);
        System.out.println("actualSalary = " + actualSalary);
        Assert.assertEquals(actualSalary,expectedSalary);
    }






    @AfterMethod
    public void destroy() throws SQLException {
        System.out.println("\tdisconnecting db.........");
        connection.close();
        statement.close();
        resultSet.close();
    }
}
