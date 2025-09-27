package hcmute.vn.controller;

import hcmute.vn.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ResetPasswordServlet", value = "/reset-password")
public class ResetPasswordServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() 
    {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        
        try 
        {
            userDao.updatePassword(email, newPassword);
            request.setAttribute("success", "Password reset successfully! Please login.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } 
        catch (SQLException e) 
        {
            request.setAttribute( "error", "Failed to reset password: " + e.getMessage() );
            request.getRequestDispatcher("/jsp/reset.jsp").forward(request, response);
        }
    }
}