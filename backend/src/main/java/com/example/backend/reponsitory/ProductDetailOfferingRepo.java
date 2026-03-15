package com.example.backend.reponsitory;

import com.example.backend.entity.ProductDetailOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailOfferingRepo extends JpaRepository< ProductDetailOffering,Long> {
    List<ProductDetailOffering> findByProductOfferingId(Long productOfferingId);

    /*@Query("delete from ProductDetailOffering p where p.id =:id")
    void deleteAll(Long id);*/
}
