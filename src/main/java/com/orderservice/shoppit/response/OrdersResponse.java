package com.orderservice.shoppit.response;

import com.orderservice.shoppit.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {
    private String status;
    private String message;
    private List<Orders> orders;
}
