package hcmute.vn.controller;

import hcmute.vn.dao.UserDao;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet( name = "ForgotPasswordServlet", value = "/forgot-password" )
public class ForgotPasswordServlet extends HttpServlet 
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
        try 
        {
        	
            if (userDao.checkEmailExists(email)) 
            {
            	
                String otp = userDao.generateOTP();
                HttpSession session = request.getSession();
                session.setAttribute("otp", otp);
                session.setAttribute("email", email);
                sendOTP(email, otp);
                response.sendRedirect("jsp/verify.jsp");
                
            } 
            
            else 
            {
                request.setAttribute("error", "Email not found!");
                request.getRequestDispatcher("/jsp/forgot.jsp").forward(request, response);
            }
            
        } 
        
        catch (SQLException e) 
        {
            request.setAttribute( "error", "Database error: " + e.getMessage() );
            request.getRequestDispatcher("/jsp/forgot.jsp").forward( request, response );
        }
    }

    private void sendOTP(String toEmail, String otp) 
    {
        final String fromEmail = "hoangcamton@gmail.com"; // email gửi : của bản thân
        final String password = "ftmt dfni rwqi lypw"; // App password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your OTP for Password Reset");
            message.setText("Your OTP is: " + otp + ". Valid for 5 minutes.");

            Transport.send(message);
        } 
        
        catch (MessagingException e) 
        {
            e.printStackTrace();
        }
    }
}