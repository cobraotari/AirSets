package com.airsets.demo.DAO;

import com.airsets.demo.models.Product;

import java.util.List;

public interface ProductDao {
    boolean createProduct(Product product);
    Product getProductById(int id);
    List<Product> getAllProducts();
    boolean updateProduct(Product product);
    boolean removeProduct(Product product);
}
