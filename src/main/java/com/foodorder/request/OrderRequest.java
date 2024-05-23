package com.foodorder.request;

import com.foodorder.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address delivery;
}
