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

@WebServlet("/editreturn")
public class Editreturn extends HttpServlet {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse rsp ) throws IOException {

        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        String eid = req.getParameter("id");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/canedb","root","admin");

            preparedStatement = connection.prepareStatement("select * from employee where id = ?");
            preparedStatement.setString(1, eid);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                out.print("<form action='editServlet' method='POST'");
                out.print("<table");

                out.print("<tr> <td>Cane ID</td>    <td> <input type='text' name ='employee_id' id='empid' value= '" + resultSet.getString("id") + "'/> </td> </tr>");
                out.print("<tr> <td>First Name</td>    <td> <input type='text' name ='first_name' id='fname' value= '" + resultSet.getString("first_name") + "'/> </td> </tr>");
                out.print("<tr> <td>Last Name</td>    <td> <input type='text' name ='last_name' id='lname' value= '" + resultSet.getString("last_name") + "'/> </td> </tr>");
                out.print("<tr>  <td colspan ='2'> <input type='submit'  value= 'Edit'/> </td> </tr>");
                out.print("</table");
                out.print("</form");

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Editreturn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            out.println("<font color='red'>  Record Failed   </font>");

        }
    }

}