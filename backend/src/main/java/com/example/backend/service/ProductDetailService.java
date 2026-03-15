package com.example.backend.service;

import com.example.backend.dto.request.ProductDetailCreateRequest;
import com.example.backend.entity.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> getAll();
    ProductDetail createProductDetail(ProductDetailCreateRequest request);
}
