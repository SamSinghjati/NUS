package com.luv2shopdev.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2shopdev.ecommerce.dao.CustomerRepository;
import com.luv2shopdev.ecommerce.dto.PaymentInfo;
import com.luv2shopdev.ecommerce.dto.Purchase;
import com.luv2shopdev.ecommerce.dto.PurchaseResponse;
import com.luv2shopdev.ecommerce.utils.Utility;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;

import net.minidev.json.JSONObject;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey) {
        this.customerRepository = customerRepository;

        // init Stripe API with secretKey
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {


        // retrieve the order info from db
        var order = purchase.getOrder();

        // generate tracking number
        var orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        var orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        var customer = purchase.getCustomer();

        // check if this is an existing customer(based on customer email)
        var email = customer.getEmail();
        var customerFromDB = customerRepository.findByEmail(email);

        if (customerFromDB != null) {
            customer = customerFromDB;
        }

        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        PurchaseResponse purResp = new PurchaseResponse();
        purResp.setOrderTrackingNumber(orderTrackingNumber);
        
        // return a response
        return purResp;
    }

    @Override
    public JSONObject createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        var paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        var params = new HashMap<String, Object>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "luv2shop purchase");
        params.put("receipt_email", paymentInfo.getReceiptEmail());

        Utility utility = new Utility();
        JSONObject paymentIntentLocal = utility.createPaymentIntentLocal(params);
//        return PaymentIntent.create(params);
        return paymentIntentLocal;
    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
