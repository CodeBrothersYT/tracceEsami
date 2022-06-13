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
import java.sql.ResultSet;


@WebServlet("/employee")
public class Employee extends HttpServlet {

    Connection connection;
    PreparedStatement preparedStatement;

    public void doPost(HttpServletRequest req, HttpServletResponse rsp) throws IOException {

        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/canedb", "root", "admin");
            String employeeId = req.getParameter("employee_id");
            String employeeFirstName = req.getParameter("first_name");
            String employeeLastName = req.getParameter("last_name");
            String roleName = req.getParameter("role_name");


            preparedStatement = connection.prepareStatement("insert into employee(id,first_name,last_name)values(?,?,?)");
            preparedStatement.setString(1, employeeId);
            preparedStatement.setString(2, employeeFirstName);
            preparedStatement.setString(3, employeeLastName);

            //controllo che hai inserito un ruolo esistente
            final String[] roles = roleName.trim().split("[ ,]+");
            for(String role : roles){
                final PreparedStatement getRoleStatement = connection.prepareStatement("select * from ruoli where nome = ?");
                getRoleStatement.setString(1, role);
                final ResultSet resultSet = getRoleStatement.executeQuery();
                resultSet.next();
                final String roleId = resultSet.getString("id");
                //prendo il ruolo corrispondente e lo setto popolando la tabella users_roles
                final PreparedStatement insertUserRoleStatement = connection.prepareStatement("insert into users_roles (user_id, role_id) values (?,?)");
                insertUserRoleStatement.setString(1,employeeId);
                insertUserRoleStatement.setString(2,roleId);
                insertUserRoleStatement.executeUpdate();
            }
            preparedStatement.executeUpdate();
            out.println("<font color='green'>  Records Added   </font>");

        } catch (Exception ex) {
            out.println(ex);
            out.println("<font color='red'>  Record Failed  </font>");
        }
    }
}