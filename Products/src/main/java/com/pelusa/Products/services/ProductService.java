package com.pelusa.Products.services;

import com.pelusa.Products.models.Product;
import com.pelusa.Products.repository.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;



    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public Product findProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product findProductByName(String name) {
        return null;
    }

    @Override
    public void editProduct(Long id, Product product) {
        Product edit = this.findProduct(id);

        if(edit != null){
            edit.setName(product.getName());
            edit.setBrand(product.getBrand());
            edit.setPrice(product.getPrice());
            productRepository.save(edit);
        }else {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }

    }



}
