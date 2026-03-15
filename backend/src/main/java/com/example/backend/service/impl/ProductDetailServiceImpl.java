package com.example.backend.service.impl;

import com.example.backend.dto.request.ProductDetailCreateRequest;
import com.example.backend.entity.ProductDetail;
import com.example.backend.reponsitory.ProductDetailRepo;
import com.example.backend.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepo productDetailRepos;

    @Override
    public List<ProductDetail> getAll() {
        return productDetailRepos.findAll();
    }

    @Override
    public ProductDetail createProductDetail(ProductDetailCreateRequest request) {
         if(request.getBranch() == null || request.getFeature()==null || request.getVideo() == null ||
         request.getPower()== null || request.getWeight() == null){
             throw new RuntimeException("Chua dien du thong tin");
        }
         ProductDetail productDetails = new ProductDetail();
         productDetails.setBranch(request.getBranch());
         productDetails.setFeature(request.getFeature());
         productDetails.setVideo(request.getVideo());
         productDetails.setWeight(request.getWeight());
         productDetails.setPower(request.getPower());
        return productDetailRepos.save(productDetails);
    }
}
