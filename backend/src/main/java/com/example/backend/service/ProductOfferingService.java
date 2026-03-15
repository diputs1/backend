package com.example.backend.service;

import com.example.backend.dto.request.ProductOfferingCreateRequest;
import com.example.backend.entity.ProductOffering;

import java.util.List;

public interface ProductOfferingService {
    ProductOffering getById(Long id);

    List<ProductOffering> getAll();

    List<ProductOffering> getByName(String name);
    List<ProductOffering> getByField(String name, String color);

    ProductOffering create(ProductOffering product);
    ProductOffering update(Long id, ProductOffering product);

    ProductOffering     createProduct(ProductOfferingCreateRequest request);
    List<ProductOffering> filter(String name, Long minPrice, Long MaxPrice, String color);
}
