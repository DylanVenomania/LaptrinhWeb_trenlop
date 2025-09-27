package hcmute.vn.controller;

import hcmute.vn.dao.UserDao;
import hcmute.vn.model.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

    public void init() 
    {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try 
        {
            User user = userDao.loginUser(username, password);
            if (user != null) 
            {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/webfashionshop/jsp/success.jsp");
            } 
            else 
            {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            request.setAttribute("error", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
