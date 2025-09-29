package com.littlelotus.service;

import com.littlelotus.model.Product;

import com.littlelotus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService 
{

    @Autowired
    private ProductRepository productRepository;

    // phân trang, tìm kiếm
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDir, String keyword) 
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortField).ascending() : 
                    Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.isEmpty() ) 
        {
            return productRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }
        
        return productRepository.findAll(pageable);
    }

    // các chức năng crud
    public void saveProduct(Product product) 
    {
        productRepository.save(product);
    }

    public Optional<Product> getProductById(Integer id) 
    {
        return productRepository.findById(id);
    }

    public void deleteProduct(Integer id) 
    {
        productRepository.deleteById(id);
    }
    
    
    public Page<Product> findPaginatedByCategoryId(Integer categoryId, int pageNo, int pageSize, String sortField, String sortDir) 
    {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortField).ascending() : 
                    Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (categoryId != null) 
        {
            
            return productRepository.findByCategoryId(categoryId, pageable);
        }
        
        
        return productRepository.findAll(pageable);
    }
}