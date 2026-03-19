package com.example.backend.controller;

import com.example.backend.dto.request.ProductOfferingCreateRequest;
import com.example.backend.entity.ProductOffering;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.service.ProductOfferingService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductOfferingController {


    private final ProductOfferingService productsOfferingService;

    @GetMapping("filter")
    public ResponseEntity<List<ProductOffering>> filter(@RequestParam(name="name", required = false) String name,
                                                         @RequestParam(name="minPrice",required =false)Long minPrice,
                                                         @RequestParam(name="maxPrice",required = false)Long maxPrice,
                                                         @RequestParam(name="color",required= false) String color){
        List<ProductOffering> productOffering = productsOfferingService.filter(name,minPrice,maxPrice,color);
        return ResponseEntity.ok(productOffering);
    }

    @GetMapping("/")
    public String home() {
        return "Backend đang chạy. Thử: GET /product hoặc GET /product?id=1";
    }

    @GetMapping("/product")
    public ResponseEntity<ProductOffering> getById(@RequestBody Long id) {
        /*if (id == null) {
            id = 1L;
        }*/
        ProductOffering product = productsOfferingService.getById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductOffering>> getAll() {
        List<ProductOffering> products = productsOfferingService.getAll();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/name-products")
    public ResponseEntity<List<ProductOffering>> getByName(String name){
        List<ProductOffering> productsOfferings = productsOfferingService.getByName(name);
        return ResponseEntity.ok(productsOfferings);
    }
    @GetMapping("/field-products")
    public ResponseEntity<List<ProductOffering>> getByField(String name, String color){
        List<ProductOffering> productsOfferings = productsOfferingService.getByField(name,color);
        return ResponseEntity.ok(productsOfferings);
    }
    @PostMapping("/create")
    public ResponseEntity<ProductOffering> create(@RequestBody ProductOffering products){
        ProductOffering product= productsOfferingService.create(products);
        return ResponseEntity.ok(product);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductOffering> update(@PathVariable Long id, @RequestBody ProductOffering products){
        ProductOffering product = productsOfferingService.update(id,products);
        return ResponseEntity.ok(product);
    }
    @PostMapping("/product-dto")
    public ResponseEntity<ProductOffering> dto(@RequestBody ProductOfferingCreateRequest request){
        ProductOffering product = productsOfferingService.createProduct(request);
        return ResponseEntity.ok(product);
    }

}
