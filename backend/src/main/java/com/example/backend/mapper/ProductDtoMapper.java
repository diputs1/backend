package com.example.backend.mapper;

import com.example.backend.dto.request.ProductOfferingUpdateRequest;
import com.example.backend.dto.response.ProductDetailResponse;
import com.example.backend.dto.response.ProductOfferingResponse;
import com.example.backend.entity.ProductDetail;
import com.example.backend.entity.ProductOffering;
import com.example.backend.dto.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDtoMapper {

    public ProductOfferingResponse toOfferingResponse(ProductOffering entity) {
        if (entity == null) {
            return null;
        }
        ProductOfferingResponse dto = new ProductOfferingResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setColor(entity.getColor());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<ProductOfferingResponse> toOfferingResponseList(List<ProductOffering> entities) {
        return entities.stream().map(this::toOfferingResponse).toList();
    }

    public PageResponse<ProductOfferingResponse> toOfferingPageResponse(Page<ProductOffering> page) {
        return PageResponse.fromPage(page, toOfferingResponseList(page.getContent()));
    }

    public ProductOffering toOfferingEntity(ProductOfferingUpdateRequest request) {
        if (request == null) {
            return null;
        }
        ProductOffering entity = new ProductOffering();
        entity.setName(request.getName());
        entity.setPrice(request.getPrice());
        entity.setColor(request.getColor());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public ProductDetailResponse toDetailResponse(ProductDetail entity) {
        if (entity == null) {
            return null;
        }
        return new ProductDetailResponse(
                entity.getId(),
                entity.getWeight(),
                entity.getFeature(),
                entity.getPower(),
                entity.getBranch(),
                entity.getVideo()
        );
    }

    public List<ProductDetailResponse> toDetailResponseList(List<ProductDetail> entities) {
        return entities.stream().map(this::toDetailResponse).toList();
    }
}
