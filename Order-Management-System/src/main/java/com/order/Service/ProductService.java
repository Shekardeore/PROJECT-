package com.order.Service;

import com.order.Exceptions.ProductNotFoundException;
import com.order.Model.Payload.ProductRequestPayload;
import com.order.Model.Product;
import com.order.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product createProduct(ProductRequestPayload requestPayload){

        Product product = new Product();
        product.setProductName(requestPayload.getProductName());
        product.setPrice(requestPayload.getProductPrice());
        return productRepository.save(product);
    }

    public Product productId(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        return product.orElseThrow(()-> new ProductNotFoundException("Product Not found with id "+id));

    }


    public List<Product> productList() {
        return productRepository.findAll();
    }
}
