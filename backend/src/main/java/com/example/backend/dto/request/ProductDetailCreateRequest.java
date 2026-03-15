package com.example.backend.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDetailCreateRequest implements Serializable {

    private Long weight;

    private String feature;

    private String power;

    private String branch;

    private String video;
}
