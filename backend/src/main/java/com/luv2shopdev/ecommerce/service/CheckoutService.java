package com.luv2shopdev.ecommerce.service;

import com.luv2shopdev.ecommerce.dto.PaymentInfo;
import com.luv2shopdev.ecommerce.dto.Purchase;
import com.luv2shopdev.ecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;

import net.minidev.json.JSONObject;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    JSONObject createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
