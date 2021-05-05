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
public class GetSearchResults {

	private static final Logger LOG = LogManager.getLogger(GetSearchResults.class);

	private static String responseCode = null;
	private static String status = null;
	private static Properties merchantProp;

//	public static void main(String args[]) throws Exception {
//		runSearchResult();
//	}

	public static TssV2TransactionsPost201Response runSearchResult(CyberPayment payment) {
		LOG.info("Search Result started......");
		String searchId = CreateSearchRequest.runCreateSearch(payment).getSearchId();

		LOG.info("...created serachId:{}", searchId);
		TssV2TransactionsPost201Response result = null;
		try {
			merchantProp = Configuration.getMerchantDetails();
			ApiClient apiClient = new ApiClient();
			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
			apiClient.merchantConfig = merchantConfig;

			SearchTransactionsApi apiInstance = new SearchTransactionsApi(apiClient);
			result = apiInstance.getSearch(searchId);

			responseCode = apiClient.responseCode;
			status = apiClient.status;
			LOG.info("ResponseCode :{}" , responseCode);
			LOG.info("ResponseMessage :{}" , status);
			// LOG.info(result);

			LOG.info("Search Result Order ID:{}", result.getEmbedded().getTransactionSummaries().get(0).getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
