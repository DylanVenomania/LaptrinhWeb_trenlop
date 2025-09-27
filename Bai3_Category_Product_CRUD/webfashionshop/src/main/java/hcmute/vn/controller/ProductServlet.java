package hcmute.vn.controller;

import hcmute.vn.dao.CategoryDao;
import hcmute.vn.dao.ProductDao;
import hcmute.vn.model.Category;
import hcmute.vn.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private ProductDao productDao;
    private CategoryDao categoryDao;

    public void init()
    {
        productDao = new ProductDao();
        categoryDao = new CategoryDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String idStr = request.getParameter("id");
        String categoryIdStr = request.getParameter("categoryId");
        try
        {
            List<Category> categories = categoryDao.findAll();
            request.setAttribute("categories", categories);

            if (idStr != null)
            {
                int id = Integer.parseInt(idStr);
                Product product = productDao.findById(id);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/jsp/product-detail.jsp").forward(request, response);
            }
            else
            {
                if (categoryIdStr != null)
                {
                    int categoryId = Integer.parseInt(categoryIdStr);
                    request.setAttribute("products", productDao.findByCategory(categoryId));
                }
                else
                {
                    request.setAttribute("products", productDao.findAll());
                }
                request.getRequestDispatcher("/jsp/products.jsp").forward(request, response);
            }
        }
        catch (SQLException e)
        {
            throw new ServletException(e);
        }
    }
}


