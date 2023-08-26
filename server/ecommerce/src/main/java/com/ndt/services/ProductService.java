package com.ndt.services;

import com.ndt.pojo.Product;
import com.ndt.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public List<Product> getProducts() {
        return this.productRepo.getProducts();
    }
}
