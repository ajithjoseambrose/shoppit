package com.orderservice.shoppit.response;

import com.orderservice.shoppit.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String status;
    private String message;
    private Product product;
}
