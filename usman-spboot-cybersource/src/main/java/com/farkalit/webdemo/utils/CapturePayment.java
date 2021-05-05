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
public class CapturePayment {

	private static final Logger LOG = LogManager.getLogger(CapturePayment.class);

	private static String responseCode = null;
	private static String status = null;
	private static Properties merchantProp;

//	public static void main(String args[]) throws Exception {
//		run();
//	}

	public static PtsV2PaymentsCapturesPost201Response doCapturePayment(CyberPayment payment) {
		LOG.info(" capture payment started...");
		PtsV2PaymentsPost201Response paymentResponse = SimpleAuthorizationInternet.doPayment(payment);
		String id = paymentResponse.getId();

		CapturePaymentRequest requestObj = new CapturePaymentRequest();

		Ptsv2paymentsClientReferenceInformation clientReferenceInformation = new Ptsv2paymentsClientReferenceInformation();
		// set order id
		clientReferenceInformation.code(payment.getOrderId());
		requestObj.clientReferenceInformation(clientReferenceInformation);

		Ptsv2paymentsidcapturesOrderInformation orderInformation = new Ptsv2paymentsidcapturesOrderInformation();
		Ptsv2paymentsidcapturesOrderInformationAmountDetails orderInformationAmountDetails = new Ptsv2paymentsidcapturesOrderInformationAmountDetails();
		// set amount and currency
		orderInformationAmountDetails.totalAmount(payment.getAmount());
		orderInformationAmountDetails.currency(payment.getCurrency());
		orderInformation.amountDetails(orderInformationAmountDetails);

		requestObj.orderInformation(orderInformation);

		PtsV2PaymentsCapturesPost201Response result = null;
		try {
			merchantProp = Configuration.getMerchantDetails();
			ApiClient apiClient = new ApiClient();
			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
			apiClient.merchantConfig = merchantConfig;

			CaptureApi apiInstance = new CaptureApi(apiClient);
			result = apiInstance.capturePayment(requestObj, id);

			responseCode = apiClient.responseCode;
			status = apiClient.status;
			LOG.info("ResponseCode :{}", responseCode);
			LOG.info("ResponseMessage :{}", status);
			//LOG.info(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
