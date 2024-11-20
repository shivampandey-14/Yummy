package com.shivam.esd_assignment.repo;

import com.shivam.esd_assignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String name);

    @Query(value = "SELECT * FROM products  WHERE  price BETWEEN 15.00 AND 30.00 ORDER BY price LIMIT 2",nativeQuery = true)
    public List<Product> searchByPrice();
}

