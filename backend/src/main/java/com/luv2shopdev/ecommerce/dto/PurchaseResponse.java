package com.luv2shopdev.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {

    private String orderTrackingNumber;

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

}
