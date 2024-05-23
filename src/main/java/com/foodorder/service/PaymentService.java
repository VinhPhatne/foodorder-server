package com.foodorder.service;

import com.foodorder.model.Order;
import com.foodorder.response.PaymentResponse;
import com.stripe.exception.StripeException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;


}
