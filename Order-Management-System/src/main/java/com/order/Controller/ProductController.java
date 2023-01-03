package com.order.Controller;

import com.order.Exceptions.ProductNotFoundException;
import com.order.Model.Payload.ProductRequestPayload;
import com.order.Model.Product;
import com.order.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //create product
    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestPayload requestPayload){
        Product product = productService.createProduct(requestPayload);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //find product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProdutById(@PathVariable Long id) throws ProductNotFoundException {

        Product product = productService.productId(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // get all product
    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> findAllProduct(){
        List<Product> productList = productService.productList();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
