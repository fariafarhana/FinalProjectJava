package com.example.finalproject;

import java.sql.*;

public class DatabaseManagement {
    DatabaseManagement(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean check(String uname,String pass){

        String sql = "Select * From info where username = "+ "\'"+uname +"\'"+" and password = "+ "\'"+pass +"\'" ;
        boolean r = false;
        try {
            Connection connection = getCon();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("name:" + resultSet.getString(2));
                r= true;
            }
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;

    }
    public boolean registrationCheck(String uname,String email){

        String sql = "Select * From info where username = "+ "\'"+uname +"\'"+" or email = "+ "\'"+email +"\'" ;
        boolean chk = false;
        try {
            Connection connection = getCon();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                chk= true;
            }
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chk;

    }
    public boolean insertData(String username,String email,String pass){
        String insertSql ="INSERT INTO info values (\'"+username +"\',\'"+ email+"\',\'"+pass +"\',\'"+ "" +"\')";
        // insert into info values ('n','n@gmail.com','123','otp')
        boolean r = false;
        Connection con= getCon();
        try {
           Statement smt = con.createStatement();
          int  i =  smt.executeUpdate(insertSql);
          if (i>-1) {
              r = true;
          }
          smt.close();
          con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;

    }
    public void saveOtp(String otp, String email){
        Connection con = getCon();
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE info set otp =  "+ "\'"+otp +"\'"+" where email = "+ "\'"+email +"\'";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //UPDATE info set otp = '920' where email = 'a@gmail.com'
    }
    public void clearOtp(String otp, String email){
        Connection con = getCon();
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE info set otp =  "+ "\'"+otp +"\'"+" where email = "+ "\'"+email +"\'";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //UPDATE info set otp = '920' where email = 'a@gmail.com'
    }
    public boolean checkOtp(String otp){

        String sql = "Select * From info where otp = "+ "\'"+otp +"\'" ;
        boolean r = false;
        try {
            Connection connection = getCon();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                r= true;
            }
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;

    }
public Connection getCon (){
    Connection c= null;
    try {
       c=  DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/server","root","");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return c;
}
}
