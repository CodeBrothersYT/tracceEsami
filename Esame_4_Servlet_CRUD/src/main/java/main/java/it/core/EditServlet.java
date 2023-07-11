package main.java.it.core;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/editServlet")
public class EditServlet extends HttpServlet {

    Connection connection;
    PreparedStatement preparedStatement;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rsp) throws IOException {

        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/canedb", "root", "admin");

            String employeeId = req.getParameter("employee_id");
            String firstName = req.getParameter("first_name");
            String lastName = req.getParameter("last_name");

            preparedStatement = connection.prepareStatement("update employee set first_name = ?, last_name = ? where id = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, employeeId);
            preparedStatement.executeUpdate();

            out.println("<font color='green'>  Record Updated  </font>");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            out.println("<font color='red'>  Record Failed   </font>");

        }

    }

}