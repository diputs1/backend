package com.example.backend.service;

import com.example.backend.dto.request.AssignProductReq;
import com.example.backend.entity.ProductOffering;

public interface ProductDetailOfferingService {
    ProductOffering assignmentProductDetail(AssignProductReq request);

}
