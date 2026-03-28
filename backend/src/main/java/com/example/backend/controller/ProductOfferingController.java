package com.example.backend.controller;

import com.example.backend.dto.request.ProductOfferingCreateRequest;
import com.example.backend.dto.request.ProductOfferingFilter;
import com.example.backend.dto.request.ProductOfferingUpdateRequest;
import com.example.backend.dto.response.PageResponse;
import com.example.backend.dto.response.ProductOfferingResponse;
import com.example.backend.mapper.ProductDtoMapper;
import com.example.backend.service.ProductOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-offerings")
public class ProductOfferingController {

    private final ProductOfferingService productsOfferingService;
    private final ProductDtoMapper productDtoMapper;

    @GetMapping
    public ResponseEntity<PageResponse<ProductOfferingResponse>> list(
            @ModelAttribute ProductOfferingFilter filter,
            Pageable pageable) {
        if (hasFilterCriteria(filter)) {
            return ResponseEntity.ok(productDtoMapper.toOfferingPageResponse(
                    productsOfferingService.filter(filter, pageable)));
        }
        return ResponseEntity.ok(productDtoMapper.toOfferingPageResponse(
                productsOfferingService.findAll(pageable)));
    }

    private static boolean hasFilterCriteria(ProductOfferingFilter f) {
        if (f == null) {
            return false;
        }
        return (f.getName() != null && !f.getName().isEmpty())
                || (f.getColor() != null && !f.getColor().isEmpty())
                || f.getMinPrice() != null
                || f.getMaxPrice() != null;
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<ProductOfferingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productDtoMapper.toOfferingResponse(productsOfferingService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ProductOfferingResponse> create(@RequestBody ProductOfferingCreateRequest request) {
        return ResponseEntity.ok(productDtoMapper.toOfferingResponse(productsOfferingService.createProduct(request)));
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<ProductOfferingResponse> update(
            @PathVariable Long id,
            @RequestBody ProductOfferingUpdateRequest request) {
        return ResponseEntity.ok(productDtoMapper.toOfferingResponse(
                productsOfferingService.update(id, productDtoMapper.toOfferingEntity(request))));
    }
}
