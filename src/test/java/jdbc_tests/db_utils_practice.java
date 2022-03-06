package jdbc_tests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class db_utils_practice {


    @Test
    public void test1(){

        // create connection and use the method from dB_utility
        DBUtils.createConnection();

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select * from employees");


        for (Map<String, Object> stringObjectMap : queryResultMap) {
            System.out.println(stringObjectMap.toString());

        }



        // close the connections use method from Db_utility
        DBUtils.destroy();
    }


    @Test
    public void test2(){
        DBUtils.createConnection();

        Map<String, Object> rowMap = DBUtils.getRowMap("select first_name, last_name, salary, job_id\n" +
                "from employees\n" +
                "where employee_id= 100");

        System.out.println("rowMap.toString() = " + rowMap.toString());

        DBUtils.destroy();


    }
}
