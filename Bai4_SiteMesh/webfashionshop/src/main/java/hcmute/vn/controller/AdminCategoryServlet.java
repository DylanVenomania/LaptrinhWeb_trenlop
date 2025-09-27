package hcmute.vn.controller;

import hcmute.vn.dao.CategoryDao;
import hcmute.vn.model.Category;
import hcmute.vn.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet", value = "/admin/categories")
public class AdminCategoryServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;

    public void init()
    {
        categoryDao = new CategoryDao();
    }

    private boolean ensureAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession(false);
        if (session == null) { response.sendRedirect(request.getContextPath() + "/jsp/login.jsp"); return false; }
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == null || !"admin".equalsIgnoreCase(user.getRole()))
        {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!ensureAdmin(request, response)) return;
        String action = request.getParameter("action");
        try
        {
            if (action == null || action.isEmpty())
            {
                List<Category> categories = categoryDao.findAll();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/jsp/admin-categories.jsp").forward(request, response);
            }
            else if ("new".equals(action))
            {
                request.getRequestDispatcher("/jsp/admin-category-form.jsp").forward(request, response);
            }
            else if ("edit".equals(action))
            {
                int id = Integer.parseInt(request.getParameter("id"));
                Category cat = categoryDao.findById(id);
                request.setAttribute("category", cat);
                request.getRequestDispatcher("/jsp/admin-category-form.jsp").forward(request, response);
            }
            else if ("delete".equals(action))
            {
                int id = Integer.parseInt(request.getParameter("id"));
                categoryDao.delete(id);
                response.sendRedirect(request.getContextPath() + "/admin/categories");
            }
        }
        catch (SQLException e)
        {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!ensureAdmin(request, response)) return;
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        try
        {
            if (idStr == null || idStr.isEmpty())
            {
                Category c = new Category(name, description);
                categoryDao.insert(c);
            }
            else
            {
                Category c = new Category(name, description);
                c.setId(Integer.parseInt(idStr));
                categoryDao.update(c);
            }
            response.sendRedirect(request.getContextPath() + "/admin/categories");
        }
        catch (SQLException e)
        {
            throw new ServletException(e);
        }
    }
}


