package com.example.backend.controller;

import com.example.backend.dto.request.ProductDetailCreateRequest;
import com.example.backend.dto.response.ProductDetailResponse;
import com.example.backend.mapper.ProductDtoMapper;
import com.example.backend.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product-details")
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductDetailService productDetailServices;
    private final ProductDtoMapper productDtoMapper;

    @GetMapping
    public ResponseEntity<List<ProductDetailResponse>> getAll() {
        return ResponseEntity.ok(productDtoMapper.toDetailResponseList(productDetailServices.getAll()));
    }

    @PostMapping
    public ResponseEntity<ProductDetailResponse> create(@RequestBody ProductDetailCreateRequest request) {
        return ResponseEntity.ok(productDtoMapper.toDetailResponse(productDetailServices.createProductDetail(request)));
    }
}
