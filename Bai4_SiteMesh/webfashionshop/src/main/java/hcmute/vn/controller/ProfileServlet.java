package hcmute.vn.controller;

import hcmute.vn.dao.UserDao;
import hcmute.vn.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProfileServlet", value = "/profile")
@MultipartConfig(maxFileSize = 10485760) // 10MB
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }
        request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        Part filePart = request.getPart("image");
        String imageUrl = user.getImageUrl(); // Giữ nguyên nếu không upload

        if (filePart != null && filePart.getSize() > 0) {
            // Lưu file (giả lập, cần server upload như Cloudinary)
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            filePart.write("/path/to/save/" + fileName); // Thay bằng logic upload thực tế
            imageUrl = request.getContextPath() + "/uploads/" + fileName;
        }

        try {
            userDao.updateProfile(user.getUsername(), fullname, phone, imageUrl);
            user.setFullname(fullname);
            user.setPhone(phone);
            user.setImageUrl(imageUrl);
            session.setAttribute("user", user);
            request.setAttribute("success", "Profile updated successfully!");
            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Update failed: " + e.getMessage());
            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
        }
    }
}