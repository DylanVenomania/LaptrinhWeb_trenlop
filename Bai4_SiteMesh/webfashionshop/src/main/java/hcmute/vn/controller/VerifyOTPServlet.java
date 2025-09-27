package hcmute.vn.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "VerifyOTPServlet", value = "/verify-otp")
public class VerifyOTPServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userOTP = request.getParameter("otp");
        HttpSession session = request.getSession(false);
        
        if (session != null) 
        {
            String storedOTP = (String) session.getAttribute("otp");
            String email = (String) session.getAttribute("email");
            
            if (userOTP != null && userOTP.equals(storedOTP)) 
            {
                session.removeAttribute("otp");
                request.setAttribute("email", email);
                request.getRequestDispatcher("/jsp/reset.jsp").forward(request, response);
            } 
            else 
            {
                request.setAttribute("error", "Invalid OTP!");
                request.getRequestDispatcher("/jsp/verify.jsp").forward(request, response);
            }
        } 
        
        else 
        {
            response.sendRedirect("/jsp/login.jsp");
        }
        
    }
}