package com.example.market.service;

import com.example.market.entity.Product;
import com.example.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product productSave(Product product){
        return productRepository.save(product);
    }

    public List<Product> productList(){
        return productRepository.findAll();
    }

    public Optional<Product> productById(Long id){
        return productRepository.findById(id);
    }

    public void productDeleteById (Long id){
        productRepository.deleteById(id);
    }

}
