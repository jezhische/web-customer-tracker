package com.jezh.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {

    public static final long serialVersionUID = 1L;

    public static final String user = "springstudent";
    public static final String password = "springstudent";
    public static final String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
    public static final String driver = "com.mysql.jdbc.Driver";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().append("Served at: ").append(request.getContextPath());
        // setup connection variables
        // get connection to database
        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database: " + jdbcUrl);

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.getResultSet();
//            System.out.println(resultSet.getString("first_name"));
//            connection.commit();
            out.println("Connection successfull");
            connection.close();
            out.println("Connection closed: " + connection.isClosed());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServletException(e);
        }
    }

//    public static void main(String[] args) {
//        try {
//            Class.forName(driver);
//
//            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.getResultSet();
//            System.out.println(resultSet.getString("first_name"));
//            connection.commit();
//            connection.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
