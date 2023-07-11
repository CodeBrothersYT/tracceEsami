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

@WebServlet("/viewemployee")
public class ViewEmployee extends HttpServlet {

    Connection con;
    ResultSet resultSet;

    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse rsp ) throws IOException {

        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();


        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/canedb","root","admin");

            String sql;

            sql = "select * from employee";
            Statement stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);

            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr>");
            out.println("<td> EmpID </td>");
            out.println("<td> Firstname </td>");
            out.println("<td> Lastname </td>");
            out.println("<td> Edit </td>");
            out.println("<td> Delete </td>");

            out.println("</tr>");

            while(resultSet.next())
            {
                out.println("<tr>");
                out.println("<td>"  + resultSet.getString("id")   +  "</td>");
                out.println("<td>"  + resultSet.getString("first_name")   +  "</td>");
                out.println("<td>"  + resultSet.getString("last_name")   +  "</td>");

                out.println("<td>"  + "<a href='editreturn?id=" +  resultSet.getString("id")  + "roleId=" + resultSet.getString("ruolo_id") + "'> Edit </a>" + "</td>");
                out.println("<td>"  + "<a href='delete?id=" +  resultSet.getString("id")  + "'> Delete </a>" + "</td>");
                out.println("</tr>");

            }

            out.println("</table>");


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            out.println("<font color='red'>  Record Failed   </font>");
        }
    }
}