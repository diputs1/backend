package com.example.backend.entity;



import com.example.backend.common.Ustatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_offerings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOffering implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "color")
    private String color;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Ustatus status;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="detail_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductsDetail productDetail;*/
    @OneToMany(mappedBy = "productOffering")
    private List<ProductDetailOffering> productDetailOfferings;

}
