package com.farkalit.webdemo.utils;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cybersource.authsdk.core.MerchantConfig;
import com.farkalit.webdemo.model.CyberPayment;

import Api.*;
import Invokers.ApiClient;
import Model.*;

@Component
public class SimpleAuthorizationInternet {

	private static final Logger LOG = LogManager.getLogger(SimpleAuthorizationInternet.class);

	private static String responseCode = null;
	private static String status = null;
	private static Properties merchantProp;
	public static boolean userCapture = false;

//	public static void main(String args[]) throws Exception {
//		doPayment();
//	}

	public static PtsV2PaymentsPost201Response doPayment(CyberPayment payment) {

		LOG.info("authorize payment started....");
		CreatePaymentRequest requestObj = new CreatePaymentRequest();

		Ptsv2paymentsClientReferenceInformation clientReferenceInformation = new Ptsv2paymentsClientReferenceInformation();
		// set orderId
		clientReferenceInformation.code(payment.getOrderId());
		requestObj.clientReferenceInformation(clientReferenceInformation);

		Ptsv2paymentsProcessingInformation processingInformation = new Ptsv2paymentsProcessingInformation();
		processingInformation.capture(false);
		if (userCapture) {
			processingInformation.capture(true);
		}

		requestObj.processingInformation(processingInformation);

		Ptsv2paymentsPaymentInformation paymentInformation = new Ptsv2paymentsPaymentInformation();
		Ptsv2paymentsPaymentInformationCard paymentInformationCard = new Ptsv2paymentsPaymentInformationCard();
		paymentInformationCard.number("4111111111111111");
		paymentInformationCard.expirationMonth("12");
		paymentInformationCard.expirationYear("2031");
		paymentInformation.card(paymentInformationCard);

		requestObj.paymentInformation(paymentInformation);

		Ptsv2paymentsOrderInformation orderInformation = new Ptsv2paymentsOrderInformation();
		Ptsv2paymentsOrderInformationAmountDetails orderInformationAmountDetails = new Ptsv2paymentsOrderInformationAmountDetails();
		// set amount and currency
		orderInformationAmountDetails.totalAmount(payment.getAmount());
		orderInformationAmountDetails.currency(payment.getCurrency());
		orderInformation.amountDetails(orderInformationAmountDetails);

		Ptsv2paymentsOrderInformationBillTo orderInformationBillTo = new Ptsv2paymentsOrderInformationBillTo();
		orderInformationBillTo.firstName("John");
		orderInformationBillTo.lastName("Doe");
		orderInformationBillTo.address1("1 Market St");
		orderInformationBillTo.locality("san francisco");
		orderInformationBillTo.administrativeArea("CA");
		orderInformationBillTo.postalCode("94105");
		orderInformationBillTo.country("US");
		orderInformationBillTo.email("test@cybs.com");
		orderInformationBillTo.phoneNumber("4158880000");
		orderInformation.billTo(orderInformationBillTo);

		requestObj.orderInformation(orderInformation);

		PtsV2PaymentsPost201Response result = null;
		try {
			merchantProp = Configuration.getMerchantDetails();
			ApiClient apiClient = new ApiClient();
			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
			apiClient.merchantConfig = merchantConfig;

			PaymentsApi apiInstance = new PaymentsApi(apiClient);
			result = apiInstance.createPayment(requestObj);

			responseCode = apiClient.responseCode;
			status = apiClient.status;
			LOG.info("ResponseCode :{}", responseCode);
			LOG.info("ResponseMessage :{}", status);
			// LOG.info(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
