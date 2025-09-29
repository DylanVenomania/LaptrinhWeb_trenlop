package com.littlelotus.service;

import com.littlelotus.model.Category;
import com.littlelotus.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CategoryService 
{

    @Autowired
    private CategoryRepository categoryRepository;

   
    public Page<Category> findPaginated(int pageNo, int pageSize, String sortField, String sortDir, String keyword) 
    {
       
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortField).ascending() : 
                    Sort.by(sortField).descending();

     
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.isEmpty()) 
        {
    
            return categoryRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }

      
        return categoryRepository.findAll(pageable);
    }

    //  Lưu/Cập nhật 
    public void saveCategory(Category category) 
    {
        categoryRepository.save(category);
    }

    // Lấy theo ID
    public Optional<Category> getCategoryById(Integer id) 
    {
        return categoryRepository.findById(id);
    }

    // Xóa 
    public void deleteCategory(Integer id) 
    {
        categoryRepository.deleteById(id);
    }
}