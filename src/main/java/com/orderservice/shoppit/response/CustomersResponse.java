package com.orderservice.shoppit.response;

import com.orderservice.shoppit.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomersResponse {
    private String status;
    private String message;
    private List<Customer> customer;
}
