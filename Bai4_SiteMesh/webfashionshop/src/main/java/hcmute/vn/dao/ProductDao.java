package hcmute.vn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hcmute.vn.model.Product;

public class ProductDao 
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

    private Product mapRow(ResultSet rs) throws SQLException
    {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setCategoryId(rs.getInt("category_id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setDescription(rs.getString("description"));
        p.setImageUrl(rs.getString("image_url"));
        return p;
    }

    public List<Product> findAll() throws SQLException
    {
        String sql = "SELECT id, category_id, name, price, description, image_url FROM Products ORDER BY id DESC";
        List<Product> items = new ArrayList<>();
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) items.add(mapRow(rs));
        }
        return items;
    }

    public List<Product> findByCategory(int categoryId) throws SQLException
    {
        String sql = "SELECT id, category_id, name, price, description, image_url FROM Products WHERE category_id=? ORDER BY id DESC";
        List<Product> items = new ArrayList<>();
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) items.add(mapRow(rs));
        }
        return items;
    }

    public Product findById(int id) throws SQLException
    {
        String sql = "SELECT id, category_id, name, price, description, image_url FROM Products WHERE id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        }
        return null;
    }

    public void insert(Product product) throws SQLException
    {
        String sql = "INSERT INTO Products(category_id, name, price, description, image_url) VALUES(?, ?, ?, ?, ?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImageUrl());
            ps.executeUpdate();
        }
    }

    public void update(Product product) throws SQLException
    {
        String sql = "UPDATE Products SET category_id=?, name=?, price=?, description=?, image_url=? WHERE id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, product.getCategoryId());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImageUrl());
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException
    {
        String sql = "DELETE FROM Products WHERE id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}


