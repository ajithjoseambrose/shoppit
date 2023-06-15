package com.orderservice.shoppit.service;

import com.orderservice.shoppit.entity.Product;
import com.orderservice.shoppit.repository.ProductRepository;
import com.orderservice.shoppit.request.ProductRequest;
import com.orderservice.shoppit.response.ProductResponse;
import com.orderservice.shoppit.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductResponse addProduct(ProductRequest productRequest){
            Product product = productRequest.getProduct();
            Product result = productRepository.save(product);
            return new ProductResponse("success", "Product added",result);
    }

    public ProductsResponse allProducts(){
        return new ProductsResponse("success", "All products",productRepository.findAll());
    }
    public ProductResponse updateProduct(ProductRequest product){
        Product productExists = productRepository.findById(product.getProduct().getId()).get();
        productExists.setName(product.getProduct().getName());
        productExists.setType(product.getProduct().getType());
        productRepository.save(product.getProduct());
        return new ProductResponse("success","Product updated", product.getProduct());
        }

    public void deleteProduct(String id){
            productRepository.deleteById(id);
        }

    public ProductResponse getaProduct(String id) {
        Product productGot =  productRepository.findById(id).get();
        return new ProductResponse("success","Product found",productGot);
    }
}
