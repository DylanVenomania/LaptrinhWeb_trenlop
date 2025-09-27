package hcmute.vn.controller;

import hcmute.vn.dao.UserDao;
import hcmute.vn.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() 
    {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException 
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname"); // Thêm trường mới
        String phone = request.getParameter("phone"); // Thêm trường mới
        String imageUrl = request.getParameter("imageUrl"); // Thêm trường mới (có thể để null)

        User user = new User(username, password, email);
        user.setFullname(fullname);
        user.setPhone(phone);
        user.setImageUrl(imageUrl);
        try 
        {
            userDao.registerUser(user);
            response.sendRedirect("/webfashionshop/jsp/login.jsp");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("/webfashionshop/jsp/register.jsp").forward(request, response);
        }
    }
}