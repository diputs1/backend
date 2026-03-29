package com.example.backend.dto.request;

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
public class ProductOfferingUpdateRequest implements Serializable {
    private String name;
    private Long price;
    private String color;
    private Ustatus status;
}
