package com.example.backend.service.impl;

import com.example.backend.common.Ustatus;
import com.example.backend.dto.request.ProductOfferingCreateRequest;
import com.example.backend.entity.ProductOffering;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.service.ProductOfferingService;
import com.example.backend.exception.ProductNotFoundException;
import com.example.backend.reponsitory.ProductOfferingRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOfferingServiceImpl implements ProductOfferingService {


    private final EntityManager entityManager;

    private final ProductOfferingRepo productsOfferingRepo;

    @Override
    public ProductOffering getById(Long id) {
        return productsOfferingRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<ProductOffering> getAll() {
        return productsOfferingRepo.findAll();
    }

    @Override
    public List<ProductOffering> getByName(String name) {
        return productsOfferingRepo.findByName(name);
    }

    @Override
    public List<ProductOffering> getByField(String name, String color) {
        return productsOfferingRepo.findByNameAndColor(name, color);
    }

    @Override
    public ProductOffering create(ProductOffering product) {
        if (product.getId() != null) {
            throw new RuntimeException("Khong duoc phep truyen vao id");
        }
            /*Random random = new Random();
            product.setId(random.nextLong(10000));*/
        ProductOffering saveProduct = productsOfferingRepo.save(product); //update or save
        return saveProduct;
    }

    @Override
    public ProductOffering update(Long id, ProductOffering product) {
        getById(id);
        product.setId(id);
        ProductOffering saveProduct1 = productsOfferingRepo.save(product);
        return saveProduct1;
    }

    @Override
    public ProductOffering createProduct(ProductOfferingCreateRequest request) {
        if (request.getName() == null || request.getColor() == null || request.getPrice() == null) {
            throw new RuntimeException("Chua dien du thong tin");
        }
        ProductOffering productOffering = new ProductOffering();
        productOffering.setName(request.getName());
        productOffering.setColor(request.getColor());
        productOffering.setPrice(request.getPrice());
        productOffering.setStatus(Ustatus.ACTIVE);
        ProductOffering saveproduct1 = productsOfferingRepo.save(productOffering);
        return saveproduct1;

    }

    @Override
    public List<ProductOffering> filter(String name, Long minPrice, Long maxPrice, String color) {
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        /*La JPA entityManager dung de tuong tac voi db*/
        CriteriaQuery<ProductOffering> query = criteria.createQuery(ProductOffering.class);
        /*Truy van tra ve kieu ProductOffering*/
        Root<ProductOffering> root = query.from(ProductOffering.class);
        /*bang chinh cua query */
        List<Predicate> predicates = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            Predicate predicate = criteria.like(root.get("name"), "%" + name + "%");
            /*.like giong sql */
            predicates.add(predicate);
            /*.them dieu kien vao query */
        }

        if (minPrice != null) {
            Predicate predicate = criteria.greaterThanOrEqualTo(root.get("price"), minPrice);
            predicates.add(predicate);
        }
        if (maxPrice != null) {
            Predicate predicate = criteria.lessThanOrEqualTo(root.get("price"), maxPrice);
            predicates.add(predicate);
        }
        if (color != null && !color.isEmpty()) {
            Predicate predicate = criteria.like(root.get("color"), "%" + color + "%");
            predicates.add(predicate);
        }
        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }


}