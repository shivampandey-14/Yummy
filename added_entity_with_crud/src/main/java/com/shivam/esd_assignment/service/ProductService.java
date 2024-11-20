package com.shivam.esd_assignment.service;
import com.shivam.esd_assignment.dto.ProductRequest;
import com.shivam.esd_assignment.dto.ProductResponse;
import com.shivam.esd_assignment.entity.Product;
import com.shivam.esd_assignment.mapper.ProductMapper;
import com.shivam.esd_assignment.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepo productRepo;
    public Optional<Product> getProduct(String productName) {
        return productRepo.findByProductName(productName);
    }

    public ProductResponse retrieveProduct(String productName) {
        Product product = getProduct(productName).orElse(null);
        if(product != null) {
            return productMapper.toProductResponse(product);
        }
        return null;
    }

    public String createProduct(ProductRequest request) {
        Product product = productMapper.toProduct(request);
        productRepo.save(product);
        return "New product created";
    }

    public String deleteProduct(int pid) {
        productRepo.deleteById((long) pid);
        return "Product with pid: " + pid + " deleted";
    }

    public String updateProduct(ProductRequest request) {
        Product product = getProduct(request.productName()).orElse(null);
        //product name,pid and c_id cannot be changed.
        //now check which fields client has sent to update.
        //update only those fields that are not null.
        if(product == null) return "Customer does not exist";
        if(request.productDesc() != null) {
            product.setProductDesc(request.productDesc());
        }
        if(request.quantity() != 0)
        {
            product.setQuantity(request.quantity());
        }
        if(request.brand() != null)
        {
            product.setBrand(request.brand());
        }
        assert product != null;
        productRepo.save(product);
        return "Product updated";
    }

    public List<Product> searchByPrice() {
        return productRepo.searchByPrice();
    }
}
