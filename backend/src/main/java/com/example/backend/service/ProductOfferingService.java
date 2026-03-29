package com.example.backend.service;

import com.example.backend.dto.request.ProductOfferingCreateRequest;
import com.example.backend.dto.request.ProductOfferingFilter;
import com.example.backend.entity.ProductOffering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductOfferingService {
    ProductOffering getById(Long id);

    Page<ProductOffering> findAll(Pageable pageable);

    Page<ProductOffering> findAll(ProductOfferingFilter filter, Pageable pageable);

    List<ProductOffering> getByName(String name);
    List<ProductOffering> getByField(String name, String color);

    ProductOffering create(ProductOffering product);
    ProductOffering update(Long id, ProductOffering product);

    ProductOffering createProduct(ProductOfferingCreateRequest request);
}
