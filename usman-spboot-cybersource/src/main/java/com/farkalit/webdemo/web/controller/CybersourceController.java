package com.farkalit.webdemo.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farkalit.webdemo.model.CyberPayment;
import com.farkalit.webdemo.utils.CapturePayment;
import com.farkalit.webdemo.utils.RefundCapture;

import Model.PtsV2PaymentsCapturesPost201Response;
import Model.PtsV2PaymentsRefundPost201Response;

@RestController
@RequestMapping("/api")
public class CybersourceController {

	private static final Logger LOG = LogManager.getLogger(CybersourceController.class);

	@GetMapping(value = "/health")
	public String showLoginPage() {
		LOG.info("I am ready to serve...");
		return "I am ready to serve...";
	}

//	@PostMapping(value = "/payment", produces = "application/json")
//	public String doPayment(@RequestBody CyberPayment payment) {
//
//		LOG.info(payment);
//		PtsV2PaymentsPost201Response doPayment = SimpleAuthorizationInternet.doPayment(payment);
//		LOG.info("payment response:{}", doPayment);
//		return "payment";
//	}

	@PostMapping(value = "/capturepayment", produces = "application/json")
	public String doCapture(@RequestBody CyberPayment payment) {
		LOG.info("payment input:{}", payment);
		PtsV2PaymentsCapturesPost201Response capture = CapturePayment.doCapturePayment(payment);
		LOG.info("capture response.status:{}", capture.getStatus());
		return "capture";
	}

	@PostMapping(value = "/refund", produces = "application/json")
	public String doRefund(@RequestBody CyberPayment payment) {
		LOG.info("refund input:{}", payment);
		PtsV2PaymentsRefundPost201Response doRefund = RefundCapture.doRefund(payment);
		LOG.info("refund response.status:{}", doRefund.getStatus());
		return "refund";
	}

}
