package com.example.backend.service.impl;

import com.example.backend.dto.request.AssignProductReq;
import com.example.backend.entity.ProductDetail;
import com.example.backend.entity.ProductDetailOffering;
import com.example.backend.entity.ProductOffering;
import com.example.backend.reponsitory.ProductDetailOfferingRepo;
import com.example.backend.reponsitory.ProductDetailRepo;
import com.example.backend.reponsitory.ProductOfferingRepo;
import com.example.backend.service.ProductDetailOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductDetailOfferingServiceImpl  implements ProductDetailOfferingService {

    @Autowired
    private ProductOfferingRepo productOfferingRepo;

    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private ProductDetailOfferingRepo productDetailOfferingRepo;
    @Override
    public ProductOffering assignmentProductDetail(AssignProductReq request) {
        if(request.getProductOfferingId()== null ){
          throw new RuntimeException("product offering id not null");
        }
        if(request.getProductDetailId() == null || request.getProductDetailId().isEmpty()){
            throw new RuntimeException("Product offering not exist");
        }
        Optional<ProductOffering> productOfferingOptional = productOfferingRepo.findById(request.getProductOfferingId());
        if(productOfferingOptional.isEmpty()){
            throw new RuntimeException("Product id is not exist");
        }
        ProductOffering productOffering = productOfferingOptional.get();
        List<ProductDetail> productDetail = productDetailRepo.findAllById(request.getProductDetailId());
        if(productDetail.isEmpty()){
            throw new RuntimeException("ProductDetail id is not exist");
        }
        List<ProductDetailOffering> productDetailOfferings = new ArrayList<>();
        List<ProductDetailOffering> checkProductDetailOffering = productDetailOfferingRepo.findByProductOfferingId(request.getProductOfferingId());
        Set<Long> productDetailId = new HashSet<>();
        for(ProductDetailOffering pdo : checkProductDetailOffering){
            productDetailId.add(pdo.getProductDetail().getId());
        }
        for(ProductDetail pd : productDetail){
            if(!productDetailId.contains(pd.getId())){
                ProductDetailOffering productDetailOffering = new ProductDetailOffering();
                productDetailOffering.setProductDetail(pd);
                productDetailOffering.setProductOffering(productOfferingOptional.get());
                productDetailOfferings.add(productDetailOffering);
            }
        }
        productDetailOfferingRepo.saveAll(productDetailOfferings);
        Set<Long> requestDetailId = new HashSet<>(request.getProductDetailId());
        List<ProductDetailOffering> removePDO  = new ArrayList<>();
        for(ProductDetailOffering pdo: checkProductDetailOffering){
            if(!requestDetailId.contains(pdo.getProductDetail().getId())){
                removePDO.add(pdo);
            }
        }
        productDetailOfferingRepo.deleteAll(removePDO);
        /*if (checkProductDetailOffering != null && !checkProductDetailOffering.isEmpty()) {
            productDetailOfferingRepo.deleteAll(checkProductDetailOffering);
            // hoặc nếu repo có: productDetailOfferingRepo.deleteByProductOfferingId(request.getProductOfferingId());
        }*//*
            for(int i = 0; i< productDetail.size();i++){
                ProductDetailOffering productDetailOffering = new ProductDetailOffering();
                productDetailOffering.setProductOffering(productOfferingOptional.get());
                productDetailOffering.setProductDetail(productDetail.get(i));
                productDetailOfferings.add(productDetailOffering);
                productDetailOfferingRepo.saveAll(productDetailOfferings);
            }*/
        //productOffering.setProductDetailOfferings(productDetailOfferings);
        return productOffering;
    }
}
