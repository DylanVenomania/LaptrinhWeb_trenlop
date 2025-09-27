package hcmute.vn.dao;

import hcmute.vn.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Random;


public class UserDao 
{
    private String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=WebFashionShop;encrypt=true;trustServerCertificate=true";
    private String jdbcUsername = "sa"; 
    private String jdbcPassword = "123456789"; 

    protected Connection getConnection() 
    {
        Connection connection = null;
        try 
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } 
        catch (SQLException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        return connection;
    }

    
    
    public void registerUser(User user) throws SQLException 
    {
        String INSERT_USER_SQL = "INSERT INTO Users (username, password, email, role) VALUES (?, ?, ?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) 
        {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole() == null ? "user" : user.getRole());
            preparedStatement.executeUpdate();
        }
    }

    
    
    public User loginUser(String username, String password ) throws SQLException 
    {
        String SELECT_USER_SQL = "SELECT id, username, password, email, role FROM Users WHERE username = ? AND password = ?;";
        User user = null;
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) 
        {
            preparedStatement.setString( 1, username );
            preparedStatement.setString( 2, password );
            ResultSet rs = preparedStatement.executeQuery( );
            if (  rs.next()  ) 
            {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
        }
        
        return user;
    }
    
    
    
    public boolean checkEmailExists(String email) throws SQLException 
    {
        String CHECK_EMAIL_SQL = "SELECT COUNT(*) FROM Users WHERE email = ?;";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL_SQL)) 
        {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) 
            {
                return true;
            }
        }
        return false;
    }

   
    public String generateOTP() 
    {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    
    public void updatePassword(String email, String newPassword) throws SQLException 
    {
        String UPDATE_PASSWORD_SQL = "UPDATE Users SET password = ? WHERE email = ?;";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_SQL)) 
        {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        }
    }
}
