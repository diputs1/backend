package com.example.backend.dto.response;

import com.example.backend.common.Ustatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferingResponse implements Serializable {
    private Long id;
    private String name;
    private Long price;
    private String color;
    private Ustatus status;
}
