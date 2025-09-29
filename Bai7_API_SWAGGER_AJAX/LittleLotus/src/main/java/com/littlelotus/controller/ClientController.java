package com.littlelotus.controller;

import com.littlelotus.model.Category;
import com.littlelotus.model.Product;
import com.littlelotus.service.CategoryService;
import com.littlelotus.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController 
{

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/shop"})
    public String viewShop(Model model, 
                           @RequestParam(required = false, name = "category") Integer categoryId,
                           @RequestParam(defaultValue = "1") int pageNo,
                           @RequestParam(defaultValue = "8") int pageSize, 
                           @RequestParam(defaultValue = "name") String sortField,
                           @RequestParam(defaultValue = "asc") String sortDir) {
        
        // 1. Danh sách category
        List<Category> listCategories = categoryService.findPaginated(1, Integer.MAX_VALUE, "name", "asc", null).getContent();
        model.addAttribute("listCategories", listCategories);

        // 2. Lấy danh sách sản phẩm ( lọc đc )
        Page<Product> page;
        if (categoryId != null) 
        {
        
            page = productService.findPaginatedByCategoryId(categoryId, pageNo, pageSize, sortField, sortDir);
            model.addAttribute("currentCategory", categoryService.getCategoryById(categoryId).orElse(null));
        } 
        else 
        {

  
            Category allProductsCategory = new Category(); 
            allProductsCategory.setName("Tất Cả Sản Phẩm");
            
            page = productService.findPaginated(pageNo, pageSize, sortField, sortDir, null);
            model.addAttribute("currentCategory", allProductsCategory); 
        }
        
        // 3. Phân tranng
        model.addAttribute("listProducts", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("categoryId", categoryId); 

        return "client/shop";
    }
    

    @GetMapping("/shop/product/{id}")
    public String viewProductDetail(@PathVariable("id") Integer id, Model model) 
    {
        Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại!"));
        
        model.addAttribute("product", product);
        return "client/product-detail";
    }
}
