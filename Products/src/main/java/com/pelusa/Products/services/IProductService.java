package com.pelusa.Products.services;

import com.pelusa.Products.models.Product;

import java.util.List;

public interface IProductService {


    public List<Product> getProducts();

    public Product saveProduct(Product product);

    public void deleteProduct(Long id);

    public Product findProduct(Long id);

    public Product findProductByName(String name);

    public void editProduct(Long id, Product product);

    public List<Product> findAllByCarritoId(Long id);
}
