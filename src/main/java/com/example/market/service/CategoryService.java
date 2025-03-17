package com.example.market.service;

import com.example.market.entity.Category;
import com.example.market.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category categorySave(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }

    public Optional<Category> categoryById(Long id){
        return categoryRepository.findById(id);
    }

    public void categoryDeleteById(Long id){
        categoryRepository.deleteById(id);
    }

}
