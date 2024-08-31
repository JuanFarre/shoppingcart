package com.pelusa.Products.controller;


import com.pelusa.Products.models.Product;
import com.pelusa.Products.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProducts(){

        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){

        return ResponseEntity.ok(productService.findProduct(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){

        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PutMapping("/edit/{id}")
    public void editProduct(@PathVariable Long id, @RequestBody Product product){
        productService.editProduct(id, product);
    }









}
