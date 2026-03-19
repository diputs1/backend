package com.example.backend.controller;

import com.example.backend.dto.request.ProductDetailCreateRequest;
import com.example.backend.entity.ProductDetail;
import com.example.backend.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductDetailController {
    private final ProductDetailService productDetailServices;

    @GetMapping("/product_detail")
    public ResponseEntity<List<ProductDetail>> getAll(){
        return ResponseEntity.ok(productDetailServices.getAll());
    }

    @PostMapping("product-detail-dto")
    public ResponseEntity<ProductDetail> productDetail(@RequestBody ProductDetailCreateRequest request){
        ProductDetail productDetails = productDetailServices.createProductDetail(request);
        return ResponseEntity.ok(productDetails);
    }
}
