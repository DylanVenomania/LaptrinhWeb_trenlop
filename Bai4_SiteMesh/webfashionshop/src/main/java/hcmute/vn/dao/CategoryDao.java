package hcmute.vn.dao;

import hcmute.vn.model.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao 
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

    public List<Category> findAll() throws SQLException
    {
        String sql = "SELECT id, name, description FROM Categories ORDER BY name";
        List<Category> items = new ArrayList<>();
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Category cat = new Category();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
                cat.setDescription(rs.getString("description"));
                items.add(cat);
            }
        }
        return items;
    }

    public Category findById(int id) throws SQLException
    {
        String sql = "SELECT id, name, description FROM Categories WHERE id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Category cat = new Category();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
                cat.setDescription(rs.getString("description"));
                return cat;
            }
        }
        return null;
    }

    public void insert(Category category) throws SQLException
    {
        String sql = "INSERT INTO Categories(name, description) VALUES(?, ?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.executeUpdate();
        }
    }

    public void update(Category category) throws SQLException
    {
        String sql = "UPDATE Categories SET name=?, description=? WHERE id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException
    {
        String sql = "DELETE FROM Categories WHERE id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}


