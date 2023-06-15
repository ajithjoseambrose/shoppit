package com.orderservice.shoppit.response;

import com.orderservice.shoppit.entity.Customer;
import com.orderservice.shoppit.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String status;
    private String message;
    private Customer customer;
}
