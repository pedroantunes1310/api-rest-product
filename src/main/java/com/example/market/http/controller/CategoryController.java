package com.example.market.http.controller;

import com.example.market.entity.Category;
import com.example.market.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> categoryList(){
        return categoryService.categoryList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category categorySave(@RequestBody Category category){
        return categoryService.categorySave(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void categoryDelete(@PathVariable("id") Long id){
        categoryService.categoryById(id)
                .map(category -> {
                    categoryService.categoryDeleteById(category.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void categoryUpdate(@PathVariable("id") Long id, @RequestBody Category category){
        categoryService.categoryById(id)
                .map(categoryBase -> {
                    modelMapper.map(category, categoryBase);
                    categoryService.categorySave(categoryBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found."));
    }

}
