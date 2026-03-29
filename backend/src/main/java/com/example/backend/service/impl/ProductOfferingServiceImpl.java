package com.example.backend.service.impl;

import com.example.backend.common.Ustatus;
import com.example.backend.dto.request.ProductOfferingCreateRequest;
import com.example.backend.dto.request.ProductOfferingFilter;
import com.example.backend.entity.ProductOffering;
import com.example.backend.exception.ProductNotFoundException;
import com.example.backend.reponsitory.ProductOfferingRepo;
import com.example.backend.service.ProductOfferingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public Page<ProductOffering> findAll(Pageable pageable) {
        return productsOfferingRepo.findAll(pageable);
    }

    @Override
    public Page<ProductOffering> findAll(ProductOfferingFilter filter, Pageable pageable) {
        if (!hasFilterCriteria(filter)) {
            return findAll(pageable);
        }
        return findWithFilters(filter, pageable);
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
        return productsOfferingRepo.save(product);
    }

    @Override
    public ProductOffering update(Long id, ProductOffering product) {
        getById(id);
        product.setId(id);
        return productsOfferingRepo.save(product);
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
        return productsOfferingRepo.save(productOffering);
    }

    private Page<ProductOffering> findWithFilters(ProductOfferingFilter productOfferingFilter, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<ProductOffering> dataQuery = cb.createQuery(ProductOffering.class);
        Root<ProductOffering> root = dataQuery.from(ProductOffering.class);
        List<Predicate> predicates = buildFilterPredicates(root, cb, productOfferingFilter);
        if (!predicates.isEmpty()) {
            dataQuery.where(predicates.toArray(Predicate[]::new));
        }
        dataQuery.orderBy(buildOrders(cb, root, pageable));

        TypedQuery<ProductOffering> typedQuery = entityManager.createQuery(dataQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<ProductOffering> content = typedQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<ProductOffering> countRoot = countQuery.from(ProductOffering.class);
        countQuery.select(cb.count(countRoot));
        List<Predicate> countPredicates = buildFilterPredicates(countRoot, cb, productOfferingFilter);
        if (!countPredicates.isEmpty()) {
            countQuery.where(countPredicates.toArray(Predicate[]::new));
        }
        long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(content, pageable, total);
    }

    private static List<Predicate> buildFilterPredicates(
            Root<ProductOffering> root, CriteriaBuilder cb, ProductOfferingFilter f) {
        List<Predicate> predicates = new ArrayList<>();
        if (f.getName() != null && !f.getName().isEmpty()) {
            predicates.add(cb.like(root.get("name"), "%" + f.getName() + "%"));
        }
        if (f.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), f.getMinPrice()));
        }
        if (f.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), f.getMaxPrice()));
        }
        if (f.getColor() != null && !f.getColor().isEmpty()) {
            predicates.add(cb.like(root.get("color"), "%" + f.getColor() + "%"));
        }
        return predicates;
    }

    private static List<Order> buildOrders(CriteriaBuilder cb, Root<ProductOffering> root, Pageable pageable) {
        List<Order> orders = new ArrayList<>();
        for (Sort.Order sortOrder : pageable.getSort()) {
            Path<?> path = sortPropertyPath(root, sortOrder.getProperty());
            orders.add(sortOrder.isAscending() ? cb.asc(path) : cb.desc(path));
        }
        if (orders.isEmpty()) {
            orders.add(cb.asc(root.get("Id")));
        }
        return orders;
    }

    private static Path<?> sortPropertyPath(Root<ProductOffering> root, String property) {
        if (property == null) {
            return root.get("Id");
        }
        return switch (property) {
            case "id", "Id" -> root.get("Id");
            case "name" -> root.get("name");
            case "price" -> root.get("price");
            case "color" -> root.get("color");
            case "status" -> root.get("status");
            default -> root.get("Id");
        };
    }
}
