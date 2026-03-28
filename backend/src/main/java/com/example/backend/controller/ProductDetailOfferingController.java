package com.example.backend.controller;

import com.example.backend.dto.request.AssignProductReq;
import com.example.backend.dto.response.ProductOfferingResponse;
import com.example.backend.mapper.ProductDtoMapper;
import com.example.backend.service.ProductDetailOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product-detail-offerings")
@RequiredArgsConstructor
public class ProductDetailOfferingController {

    private final ProductDetailOfferingService productDetailOfferingService;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public ResponseEntity<ProductOfferingResponse> assignProductDetail(@RequestBody AssignProductReq request) {
        return ResponseEntity.ok(productDtoMapper.toOfferingResponse(
                productDetailOfferingService.assignmentProductDetail(request)));
    }
}
