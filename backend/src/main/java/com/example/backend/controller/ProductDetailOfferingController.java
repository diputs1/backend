package com.example.backend.controller;

import com.example.backend.dto.request.AssignProductReq;
import com.example.backend.entity.ProductOffering;
import com.example.backend.service.ProductDetailOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDetailOfferingController {

    @Autowired
    private ProductDetailOfferingService productDetailOfferingService;

    @PostMapping("/assign-product-offering")
    public ResponseEntity<ProductOffering> assignProductDetail(@RequestBody AssignProductReq request){
        ProductOffering productOffering = productDetailOfferingService.assignmentProductDetail(request);
        return ResponseEntity.ok(productOffering);
    }
}
