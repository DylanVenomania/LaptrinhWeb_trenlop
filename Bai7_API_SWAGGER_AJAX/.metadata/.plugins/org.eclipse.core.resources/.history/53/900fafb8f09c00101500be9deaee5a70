package com.littlelotus.controller;

import com.littlelotus.model.Category;
import com.littlelotus.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class CategoryController 
{

    @Autowired
    private CategoryService categoryService;

    
    @GetMapping("/admin/categories") 
    public String viewHomePage(Model model, 
                               @RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(defaultValue = "5") int pageSize,
                               @RequestParam(defaultValue = "id") String sortField,
                               @RequestParam(defaultValue = "asc") String sortDir,
                               @RequestParam(required = false) String keyword) 
    {

    
        Page<Category> page = categoryService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword);

     
        model.addAttribute("listCategories", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNo);

    
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

   
        return "category/category-list"; 
    }

    // Hiển thị Form 
    @GetMapping("/admin/category/new")
    public String showNewCategoryForm(Model model) 
    {
        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("formTitle", "Thêm Danh mục Mới");
        return "category/category-form";
    }

    //  Xử lý Submit
    @PostMapping("/admin/category/save")
    public String saveCategory(@ModelAttribute("category") Category category, 
                             RedirectAttributes ra) {
        try 
        {
            categoryService.saveCategory(category);
            ra.addFlashAttribute("message", "Lưu danh mục thành công!");
        } 
        catch (Exception e) 
        {
            ra.addFlashAttribute("error", "Lưu danh mục thất bại: " + e.getMessage());
        }
        return "redirect:/admin/categories"; 
    }

    //  Hiển thị form 
    @GetMapping("/admin/category/edit/{id}")
    public String showEditCategoryForm(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes ra) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) 
        {
            model.addAttribute("category", category.get());
            model.addAttribute("formTitle", "Chỉnh sửa Danh mục ID: " + id);
            return "category/category-form";
        } 
        else 
        {
            ra.addFlashAttribute("error", "Không tìm thấy Danh mục có ID: " + id);
            return "redirect:/admin/categories"; 
        }
    }

    // xoá
    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable(value = "id") Integer id, RedirectAttributes ra) 
    {
        try 
        {
            categoryService.deleteCategory(id);
            ra.addFlashAttribute("message", "Xóa danh mục ID: " + id + " thành công!");
        } 
        catch (Exception e) 
        {
            ra.addFlashAttribute("error", "Xóa danh mục thất bại.");
        }
        return "redirect:/admin/categories";
    }
}