package com.orderservice.shoppit.response;

import com.orderservice.shoppit.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponse {
    private String status;
    private String message;
    private List<Product> product;
}
