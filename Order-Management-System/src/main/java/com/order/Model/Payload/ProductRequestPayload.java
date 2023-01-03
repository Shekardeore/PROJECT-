package com.order.Model.Payload;

import lombok.Data;

@Data
public class ProductRequestPayload {

    private String productName;

    private double productPrice;
}
