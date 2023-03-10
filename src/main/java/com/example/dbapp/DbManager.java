package com.example.dbapp;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component   // means create the bean of Dbmanager class
public class DbManager {

    public Connection connection;

    public DbManager() throws SQLException {
        getConnection();
        createTable();
    }

    public Connection getConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teacherdb","root","Rohit@547");

        }
        return connection;
    }

    public void createTable() throws SQLException{

        String sql = "create table  if not exists teacher_info(id INT primary key auto_increment, age INT, name VARCHAR(30))";

        //for making the string into the sql query
        Statement st = connection.createStatement();
        boolean return_value = st.execute(sql);
        System.out.println(return_value);
    }

    public  void insert_info(Student s) throws SQLException {

        String sql = "insert int teacher_info(age,name) values("+s.getAge()+",'"+s.getName()+"')";
        Statement st = connection.createStatement();
        int rows = st.executeUpdate(sql);
        System.out.println("Number of rows affected ="+rows);
    }
    public void getAllStudents() throws SQLException {
        String sql = "select * from student_info";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            String name = rs.getString("name");
            String id = rs.getString("id");
            String age = rs.getString("age");

            System.out.println(name+" "+id+" "+age);
        }
    }
}
