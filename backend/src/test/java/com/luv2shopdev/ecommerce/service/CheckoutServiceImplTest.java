package com.luv2shopdev.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.luv2shopdev.ecommerce.dao.CustomerRepository;
import com.luv2shopdev.ecommerce.dto.Purchase;
import com.luv2shopdev.ecommerce.dto.PurchaseResponse;
import com.luv2shopdev.ecommerce.entity.Address;
import com.luv2shopdev.ecommerce.entity.Customer;
import com.luv2shopdev.ecommerce.entity.Order;
import com.luv2shopdev.ecommerce.entity.OrderItem;

@SpringBootTest
public class CheckoutServiceImplTest {

	    @InjectMocks
	    private CheckoutServiceImpl checkoutServiceImpl;
	    
	    @Mock
	    private CustomerRepository customerRepository;
	    
	    @Mock
	    private Order order;
	    
	    @Mock
	    private Set<OrderItem> ordItems;
	    

	    @Test
	    public void testPlaceOrder() {
	        // Create a Purchase object for testing
	        Purchase purchase = createTestPurchase();
	        PurchaseResponse purchaseResponse = createTestPurchaseResponse();
	        Customer customer = createTestCustomer();
	        
	        Order order = createOrder();

	        // Mock behavior for the customerRepository.findByEmail method
	        when(customerRepository.findByEmail(anyString())).thenReturn(null);
	        when(customerRepository.save(any())).thenReturn(null);
	        
	        // Call the method to be tested
	        PurchaseResponse result = checkoutServiceImpl.placeOrder(purchase);

	        // Perform assertions based on the expected behavior
	        assertNotNull(result);
	        assertNotNull(result.getOrderTrackingNumber());
	        // Add more assertions as needed
	    }

	    private Customer createTestCustomer() {
			
	    	Customer customer = new Customer();
	    	customer.setId(10001l);
	    	customer.setFirstName("John");
	    	customer.setLastName("Doe");
	    	customer.setEmail("JohnDoe@gmail.com");
	    	
			return customer;
		}

	    private Purchase createTestPurchase() {
	    	
	    	Purchase purchase = new Purchase();
	    	Address address = createAddress(1001l);
	    	purchase.setBillingAddress(address);
	    	purchase.setShippingAddress(address);
	    	purchase.setCustomer(createTestCustomer());
	    	purchase.setOrder(createOrder());
	    	purchase.setOrderItems(ordItems);
	    	
	        return purchase;
	    }
	    
	    private PurchaseResponse createTestPurchaseResponse() {
			PurchaseResponse purchaseResponse = new PurchaseResponse();
			purchaseResponse.setOrderTrackingNumber("123456");
			return purchaseResponse;
		}
	    
	    private Order createOrder() {
	    	
	    	Order order = this.order;
	    	order.setOrderTrackingNumber(UUID.randomUUID().toString());
	    	ordItems.add(createOrderItem(1001l));
	    	order.setOrderItems(ordItems);
	    	Address address = createAddress(1001l);
	    	order.setBillingAddress(address);
	    	order.setShippingAddress(address);
	    	
	        return order;
	    }
	    
	    private OrderItem createOrderItem(long id) {
			
	    	OrderItem ordItem = new OrderItem();
	    	ordItem.setId(id);
	    	ordItem.setImageUrl("/src/main/resources/shoes1");
	    	ordItem.setProductId(id);
	    	ordItem.setQuantity(3);
	    	ordItem.setUnitPrice(new BigDecimal("500.00"));
	    	ordItem.setOrder(order);
	    	
	    	return ordItem;
	    }
	    	
	    private Address createAddress(long id) {
	    
	    	Address address = new Address();
	    	address.setId(id);
	    	address.setCity("BLR");
	    	address.setCountry("IND");
	    	address.setState("KAR");
	    	address.setStreet("Residency Road");
	    	address.setZipCode("56001");
	    
	    	return address;
	    }
}
