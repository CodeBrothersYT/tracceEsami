package main.java.it.core;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/delete")
public class Delete extends HttpServlet {

    Connection connection;
    PreparedStatement preparedStatement;
    int row;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empid = request.getParameter("id");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/canedb", "root", "admin");
            preparedStatement = connection.prepareStatement("delete from employee where id = ?");
            preparedStatement.setString(1, empid);
            row = preparedStatement.executeUpdate();

            out.println("<font color='green'>  Record Deleted   </font>");


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println("<font color='red'>  Record Failed   </font>");
        }
    }
}