package com.orderservice.shoppit.response;

import com.orderservice.shoppit.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    public OrderResponse(String status, String message){
        this.message = message;
        this.status = status;
    }
    private String status;
    private String message;
    private Orders orders;
}
