package com.example.backend.reponsitory;

import com.example.backend.entity.ProductOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductOfferingRepo extends JpaRepository<ProductOffering, Long> {
    List<ProductOffering> findByName(String name);

    List<ProductOffering> findByNameAndColor(String name, String color);
}
