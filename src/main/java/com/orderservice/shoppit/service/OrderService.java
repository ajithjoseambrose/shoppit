package com.orderservice.shoppit.service;

import com.orderservice.shoppit.entity.Customer;
import com.orderservice.shoppit.entity.Orders;
import com.orderservice.shoppit.entity.Product;
import com.orderservice.shoppit.repository.CustomerRepository;
import com.orderservice.shoppit.repository.OrderRepository;
import com.orderservice.shoppit.repository.ProductRepository;
import com.orderservice.shoppit.request.OrderRequest;
import com.orderservice.shoppit.response.OrderResponse;
import com.orderservice.shoppit.response.OrdersResponse;
import freemarker.core.ParseException;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration configuration;

    public OrderResponse createOrder(OrderRequest orderRequest){
        Orders order = orderRequest.getOrders();
        String cust_Id = order.getCustomerId();
        String prod_Id = order.getProductId();

        if(customerRepository.existsById(cust_Id) && productRepository.existsById(prod_Id)){
            Customer customer = customerRepository.findById(cust_Id).get();
            Product product = productRepository.findById(prod_Id).get();

            orderRepository.save(order);
            MimeMessage message = mailSender.createMimeMessage();
            try {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

                Map<String,Object> model = new HashMap<>();
                model.put("name",customer.getName());
                model.put("item",product.getName());
                model.put("location",customer.getCity());
                model.put("orderId",order.getOrdId());

                Template template = configuration.getTemplate("email_template.ftl");
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);

//            mimeMessageHelper.setFrom(emailRequest.getFromName());
                mimeMessageHelper.setFrom(new InternetAddress("noreply@aj.com","Aji"));
                mimeMessageHelper.setText(html,true);
                mimeMessageHelper.setTo(customer.getEmail());
                mimeMessageHelper.setSubject("Your Order #"+order.getOrdId());

                mailSender.send(message);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (TemplateNotFoundException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } catch (MalformedTemplateNameException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
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
