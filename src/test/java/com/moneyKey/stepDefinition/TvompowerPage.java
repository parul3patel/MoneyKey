package com.moneyKey.stepDefinition;

import com.moneyKey.util.CommonUtil;
import com.moneyKey.util.WebUtil;

import org.apache.log4j.Logger;
import cucumber.api.java.en.Given;
import com.moneyKey.util.Constants.Config;

public class TvompowerPage {

	private static Logger log = Logger.getLogger(TvompowerPage.class);
	
	@Given("^user launched moneyKey Application$")
	public void launchMoneyKey() throws Throwable {
		try {
			log.info("Start :: Launch moneyKey Application");
			//log.info("CommonUtil.browser.trim()"+CommonUtil.browser.trim());
			WebUtil.initializeBrowser("ch");
			String env = CommonUtil.environment;
			WebUtil.launchApplication(CommonUtil.getConfigObject(Config.environment, env));
		}catch(Exception e) {
			log.warn("Error encountered during moneyKey launch");
			log.error("Error details : "+e.getLocalizedMessage());
			CommonUtil.throwCustomException(e.getMessage());
		}finally {
			log.info("End :: Launch moneyKey Application");
		}
	}
}
