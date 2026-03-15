package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="product_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
//jsonignore lq quan den lazy and
public class  ProductDetail implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long weight;

    @Column
    private String feature;

    @Column
    private String power;

    @Column
    private String branch;

    @Column
    private String video;
     /*@OneToMany(mappedBy = "productDetail")
    private List<ProductOfferings> product;
*/
    @OneToMany(mappedBy = "productDetail")
    @JsonIgnore
    private List<ProductDetailOffering> productDetailOfferings;

}
