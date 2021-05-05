package com.farkalit.webdemo.utils;

import java.util.Properties;

public class Configuration {

	/**
	 * mafuae_merchantId=ni_001110255098_aed
	 * mafuae_secretkey=JMlhU1HSAUStRZnYEjt9uaL68+/Y0u50M9A+ak9zGuE=
	 * mafuae_apikey=2331c369-fba9-42a2-8f20-dd55ef82a580
	 * 
	 * @return
	 */
//	public static Properties getMerchantDetails() {
//		Properties props = new Properties();
//
//		// HTTP_Signature and JWT
//		props.setProperty("authenticationType", "http_signature");
//		props.setProperty("merchantID", "ni_001110255098_aed");
//		props.setProperty("runEnvironment", "CyberSource.Environment.SANDBOX");
//		props.setProperty("requestJsonPath", "resources/request.json");
//
//		// JWT Parameters
//		props.setProperty("keyAlias", "ni_001110255098_aed");
//		props.setProperty("keyPass", "ni_001110255098_aed");
//		props.setProperty("keyFileName", "ni_001110255098_aed");
//
//		// P12 key path. Enter the folder path where the .p12 file is located.
//
//		props.setProperty("keysDirectory", "resources");
//		// HTTP Parameters
//		props.setProperty("merchantKeyId", "2331c369-fba9-42a2-8f20-dd55ef82a580");
//		props.setProperty("merchantsecretKey", "JMlhU1HSAUStRZnYEjt9uaL68+/Y0u50M9A+ak9zGuE=");
//		// Logging to be enabled or not.
//		props.setProperty("enableLog", "true");
//		// Log directory Path
//		props.setProperty("logDirectory", "log");
//		props.setProperty("logFilename", "cybs");
//
//		// Log file size in KB
//		props.setProperty("logMaximumSize", "5M");
//
//		return props;
//
//	}
	/**
	 * Local mafcarrefour config data.
	 * 
	 * @return
	 */
	public static Properties getMerchantDetails() {
		Properties props = new Properties();

		// HTTP_Signature and JWT
		props.setProperty("authenticationType", "http_signature");
		props.setProperty("merchantID", "mafcarrefour");
		props.setProperty("runEnvironment", "CyberSource.Environment.SANDBOX");
		props.setProperty("requestJsonPath", "resources/request.json");

		// JWT Parameters
		props.setProperty("keyAlias", "mafcarrefour");
		props.setProperty("keyPass", "mafcarrefour");
		props.setProperty("keyFileName", "mafcarrefour");

		// P12 key path. Enter the folder path where the .p12 file is located.

		props.setProperty("keysDirectory", "resources");
		// HTTP Parameters
		props.setProperty("merchantKeyId", "217f12b8-2c4c-4f56-a922-0399d89eaa92");
		props.setProperty("merchantsecretKey", "OIeefPDS6+kxtYS3TWNeNaDwWEQac244dcC1lbS2Ftk=");
		// Logging to be enabled or not.
		props.setProperty("enableLog", "true");
		// Log directory Path
		props.setProperty("logDirectory", "log");
		props.setProperty("logFilename", "cybs");

		// Log file size in KB
		props.setProperty("logMaximumSize", "5M");

		return props;

	}

}
