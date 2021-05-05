package com.farkalit.webdemo.model;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CyberPayment {

	@NonNull
	private String orderId;

	@NonNull
	private String amount;

	@NonNull
	private String currency;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "CyberPayment [orderId=" + orderId + ", amount=" + amount + ", currency=" + currency + "]";
	}

}
