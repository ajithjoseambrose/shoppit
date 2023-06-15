package com.orderservice.shoppit.controller;

import com.orderservice.shoppit.exceptions.SomethingWrongException;
import com.orderservice.shoppit.repository.CustomerRepository;
import com.orderservice.shoppit.repository.OrderRepository;
import com.orderservice.shoppit.repository.ProductRepository;
import com.orderservice.shoppit.request.CustomerRequest;
import com.orderservice.shoppit.request.OrderRequest;
import com.orderservice.shoppit.request.ProductRequest;
import com.orderservice.shoppit.response.*;
import com.orderservice.shoppit.service.CustomerService;
import com.orderservice.shoppit.service.OrderService;
import com.orderservice.shoppit.service.ProductService;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class OrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @PostMapping("/product")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.addProduct(productRequest), HttpStatus.CREATED);
    }
    @GetMapping("/products")
    public ResponseEntity<ProductsResponse> products(){
        return new ResponseEntity<>(productService.allProducts(),HttpStatus.OK);
    }
    @PutMapping("/product")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.updateProduct(productRequest),HttpStatus.OK);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductDeleteResponse> deleteProduct(@PathVariable String id){
        if(productRepository.existsById(id)) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(new ProductDeleteResponse("success", "product deleted with id "+id), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ProductDeleteResponse("fail", "product with id "+id+" not found"), HttpStatus.NOT_FOUND);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.getaProduct(id),HttpStatus.OK);
    }
    @PostMapping("/customer")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
        return new ResponseEntity<>(customerService.addCustomer(customerRequest),HttpStatus.CREATED);
    }
    @GetMapping("/customers")
    public ResponseEntity<CustomersResponse> customers(){
        return new ResponseEntity<>(customerService.allCustomers(),HttpStatus.OK);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String id){
        return new ResponseEntity<>(customerService.getaCustomer(id),HttpStatus.OK);
    }
    @PutMapping("/customer")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest customerRequest){
        return new ResponseEntity<>(customerService.updateCustomer(customerRequest),HttpStatus.OK);
    }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<CustomerDeleteResponse> deleteCustomer(@PathVariable String id){

        if(customerRepository.existsById(id)) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(new CustomerDeleteResponse("success", "customer deleted with id "+id), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomerDeleteResponse("fail", "customer with id "+id+" not found"), HttpStatus.NOT_FOUND);
//        throw new SomethingWrongException("custom exception triggered");
    }
    @PostMapping("/order")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest),HttpStatus.CREATED);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String id){
        return new ResponseEntity<>(orderService.getOrderById(id),HttpStatus.OK);
    }
    @GetMapping("/orders")
    public ResponseEntity<OrdersResponse> getOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
    }
    @DeleteMapping("/order/{id}")
    public ResponseEntity<OrderDeleteResponse> deleteOrder(@PathVariable String id){

        if(orderRepository.existsById(id)) {
            orderService.deleteOrder(id);
                return new ResponseEntity<>(new OrderDeleteResponse("success", "order deleted with id "+id), HttpStatus.OK);
        }
        return new ResponseEntity<>(new OrderDeleteResponse("fail", "order with id "+id+" not found"), HttpStatus.NOT_FOUND);
//        throw new SomethingWrongException("custom exception triggered");
    }
}
