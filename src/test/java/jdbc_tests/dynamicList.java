package jdbc_tests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamicList {

    String dbUrl = "jdbc:oracle:thin:@100.26.102.120 :1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";



    @Test
    public void MetaDataExample2() throws SQLException {

        // create connection between db and project
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // run query and get the result in resultSet object
        ResultSet resultSet = statement.executeQuery("select * from customer");
        // get resultSet object metaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String,Object>> listQueries = new ArrayList<>();


        int count = resultSetMetaData.getColumnCount();


        while(resultSet.next()){ // loop gets executed until last row, so code dynamically runs all rows
            Map<String , Object> row = new HashMap<>(); // dummy map to keep data of each row

            for ( int i = 1; i <= count; i ++ ){ // each column name as a key for map and value of the column as value for map

                row.put(resultSetMetaData.getColumnName(i) /*column name as a key for map*/, resultSet.getString(i)/*column value as a value for map*/);
                // in this loop we add every column as a key value into the map

            }
            listQueries.add(row); // we add the map into a list, so each object in this list is each row;
        }

        for (Map<String, Object> listQuery : listQueries) {
            System.out.println(listQuery.toString());
        }





        //close all connections
        connection.close();;
        statement.close();
        resultSet.close();

    }

}
