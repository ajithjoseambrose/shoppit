package com.orderservice.shoppit.service;

import com.orderservice.shoppit.entity.Orders;
import com.orderservice.shoppit.repository.CustomerRepository;
import com.orderservice.shoppit.repository.OrderRepository;
import com.orderservice.shoppit.repository.ProductRepository;
import com.orderservice.shoppit.request.OrderRequest;
import com.orderservice.shoppit.response.OrderResponse;
import com.orderservice.shoppit.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderResponse createOrder(OrderRequest orderRequest){
        Orders order = orderRequest.getOrders();
        String cust_Id = order.getCustomerId();
        String prod_Id = order.getProductId();

        if(customerRepository.existsById(cust_Id) && productRepository.existsById(prod_Id)){
            orderRepository.save(order);
            return new OrderResponse("success","Order placed successfully",order);
        }
        return new OrderResponse("fail","Product or customer doesn't exist");
    }
    public OrderResponse getOrderById(String orderId){
        Orders order = orderRepository.findById(orderId).get();
        return new OrderResponse("success","Order found",order);
    }
    public OrdersResponse getAllOrders(){
        List<Orders> orders = orderRepository.findAll();
        return new OrdersResponse("success","All orders",orders);
    }
    public void deleteOrder(String id){
        orderRepository.deleteById(id);
    }
}
