package com.farkalit.webdemo.utils;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cybersource.authsdk.core.MerchantConfig;
import com.farkalit.webdemo.model.CyberPayment;

import Api.*;
import Invokers.ApiClient;
import Model.*;

@Component
public class CreateSearchRequest {

	private static final Logger LOG = LogManager.getLogger(CreateSearchRequest.class);

	private static String responseCode = null;
	private static String status = null;
	private static Properties merchantProp;

//	public static void main(String args[]) throws Exception {
//		runCreatSearch();
//	}

	public static TssV2TransactionsPost201Response runCreateSearch(CyberPayment payment) {

		LOG.info("create search started....");
		Model.CreateSearchRequest requestObj = new Model.CreateSearchRequest();

		requestObj.save(false);
		requestObj.name("MAFCARREFOUR");
		requestObj.timezone("America/Chicago");
		// requestObj.query("clientReferenceInformation.code:111222001118 AND
		// submitTimeUtc:[NOW/DAY-7DAYS TO NOW/DAY+1DAY}");
		requestObj.query("clientReferenceInformation.code:" + payment.getOrderId());
		requestObj.offset(0);
		requestObj.limit(100);
		requestObj.sort("id:asc,submitTimeUtc:asc");
		TssV2TransactionsPost201Response result = null;
		try {
			merchantProp = Configuration.getMerchantDetails();
			ApiClient apiClient = new ApiClient();
			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
			apiClient.merchantConfig = merchantConfig;

			SearchTransactionsApi apiInstance = new SearchTransactionsApi(apiClient);
			result = apiInstance.createSearch(requestObj);

			responseCode = apiClient.responseCode;
			status = apiClient.status;
			LOG.info("ResponseCode :{}", responseCode);
			LOG.info("ResponseMessage :{}", status);
			// System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
