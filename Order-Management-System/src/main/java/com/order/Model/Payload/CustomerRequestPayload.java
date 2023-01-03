package com.order.Model.Payload;

import lombok.Data;

@Data
public class CustomerRequestPayload {

    private String name;

    private String email;

    private String mobileNumber;

    private String address;
}
