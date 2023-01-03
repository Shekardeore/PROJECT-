package com.order.Model.Payload;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestPayload {

    private Long customerId;

    private List<Long> productId;

    private int orderItem;

}
