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
public class RefundCapture {

	private static final Logger LOG = LogManager.getLogger(RefundCapture.class);

	private static String responseCode = null;
	private static String status = null;
	private static Properties merchantProp;

//	public static void main(String args[]) throws Exception {
//		run();
//	}

	public static PtsV2PaymentsRefundPost201Response doRefund(CyberPayment payment) {
		LOG.info("Start refundCapture....");
		// PtsV2PaymentsCapturesPost201Response captureResponse =
		// CapturePayment.doCapturePayment(payment);
		// String id = "6195861296896622204002";//captureResponse.getId();
		TssV2TransactionsPost201Response serachResult = GetSearchResults.runSearchResult(payment);
		String id = serachResult.getEmbedded().getTransactionSummaries().get(0).getId();

		RefundCaptureRequest requestObj = new RefundCaptureRequest();

		Ptsv2paymentsClientReferenceInformation clientReferenceInformation = new Ptsv2paymentsClientReferenceInformation();
		// set Order Id
		clientReferenceInformation.code(payment.getOrderId());
		requestObj.clientReferenceInformation(clientReferenceInformation);

		Ptsv2paymentsidrefundsOrderInformation orderInformation = new Ptsv2paymentsidrefundsOrderInformation();
		Ptsv2paymentsidcapturesOrderInformationAmountDetails orderInformationAmountDetails = new Ptsv2paymentsidcapturesOrderInformationAmountDetails();
		// set amount and currency
		orderInformationAmountDetails.totalAmount(payment.getAmount());
		orderInformationAmountDetails.currency(payment.getCurrency());
		orderInformation.amountDetails(orderInformationAmountDetails);

		requestObj.orderInformation(orderInformation);

		PtsV2PaymentsRefundPost201Response result = null;
		try {
			merchantProp = Configuration.getMerchantDetails();
			ApiClient apiClient = new ApiClient();
			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
			apiClient.merchantConfig = merchantConfig;

			RefundApi apiInstance = new RefundApi(apiClient);
			result = apiInstance.refundCapture(requestObj, id);

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
