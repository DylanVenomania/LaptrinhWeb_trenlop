package com.littlelotus.controller;

import com.littlelotus.model.Category;
import com.littlelotus.model.Product;
import com.littlelotus.service.CategoryService;
import com.littlelotus.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID; 

@Controller
public class ProductController 
{

    
    private static final String UPLOAD_DIR = "src/main/resources/static/images/product_images/";
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService; 

   
    private void addCategoriesToModel(Model model) 
    {
        List<Category> categories = categoryService.findPaginated(1, Integer.MAX_VALUE, "name", "asc", null).getContent(); 
        model.addAttribute("listCategories", categories); 
    }

    @GetMapping("/admin/products")
    public String viewProductList(Model model, 
                                @RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "5") int pageSize,
                                @RequestParam(defaultValue = "id") String sortField,
                                @RequestParam(defaultValue = "asc") String sortDir,
                                @RequestParam(required = false) String keyword) 
    {
        
        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword);
        
        model.addAttribute("listProducts", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNo);
        
        // sap xếp, tìm kiếm
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

       
        return "product/product-list"; 
    }
    
    //hiển thị form Thêm mới
    @GetMapping("/admin/product/new")
    public String showNewProductForm(Model model) 
    {
        Product product = new Product();
        
        addCategoriesToModel(model);
        
        model.addAttribute("product", product);
        model.addAttribute("formTitle", "Thêm Sản phẩm Mới");
        return "product/product-form";
    }

    //xử lý submit
    @PostMapping("/admin/product/save")
    public String saveProduct(@ModelAttribute("product") Product product, 
                              @RequestParam("imageFile") MultipartFile imageFile, 
                              RedirectAttributes ra) {
        try 
        {
            String oldImageUrl = product.getImageUrl(); // Lưu lại đường dẫn cũ của ảnh

            if (imageFile != null && !imageFile.isEmpty()) {
                // 1. Tạo thư mục nếu chưa tồn tại
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // 2. Tạo tên file duy nhất 
                String originalFilename = imageFile.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String uniqueFileName = UUID.randomUUID().toString() + extension;
                
                // 3. Lưu file
                Path filePath = Paths.get(UPLOAD_DIR, uniqueFileName);
                Files.copy(imageFile.getInputStream(), filePath);

                // 4. Gán đường dẫn vào product.imageUrl
                product.setImageUrl("/images/product_images/" + uniqueFileName);
            }
          
            else if (product.getId() != null && oldImageUrl != null) 
            {
                 product.setImageUrl(oldImageUrl);
            }
            
            productService.saveProduct(product);
            ra.addFlashAttribute("message", "Lưu sản phẩm thành công!");
        } 
        catch (Exception e) 
        {
            System.err.println("Lỗi khi lưu sản phẩm hoặc upload file: " + e.getMessage());
            ra.addFlashAttribute("error", "Lưu sản phẩm thất bại: " + e.getMessage());
        }
        return "redirect:/admin/products";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/admin/product/edit/{id}")
    public String showEditProductForm(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes ra) 
    {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) 
        {
            addCategoriesToModel(model); 
            
            model.addAttribute("product", product.get());
            model.addAttribute("formTitle", "Chỉnh sửa Sản phẩm ID: " + id);
            return "product/product-form";
        } 
        else 
        {
            ra.addFlashAttribute("error", "Không tìm thấy Sản phẩm có ID: " + id);
            return "redirect:/admin/products";
        }
    }

    // xoá
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Integer id, RedirectAttributes ra) 
    {
        try 
        {
            productService.deleteProduct(id);
            ra.addFlashAttribute("message", "Xóa sản phẩm ID: " + id + " thành công!");
        } 
        catch (Exception e) 
        {
            ra.addFlashAttribute("error", "Xóa sản phẩm thất bại: " + e.getMessage());
        }
        return "redirect:/admin/products";
    }
}
