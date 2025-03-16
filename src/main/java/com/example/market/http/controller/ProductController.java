package com.example.market.http.controller;

import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import com.example.market.entity.Product;
import com.example.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> prodcutList(){
        return productService.productList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product productSave(@RequestBody Product product){
        return productService.productSave(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void productDelete(@PathVariable("id") Long id){
        productService.productById(id)
                .map(product -> {
                    productService.productDeleteById(product.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void productUpdate(@PathVariable("id") Long id, @RequestBody Product product){
         productService.productById(id)
                .map(productBase -> {
                    modelMapper.map(product, productBase);
                    productService.productSave(productBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

}
