package com.moneyKey.stepDefinition;

import org.apache.log4j.Logger;

import com.moneyKey.processor.CommonProcessor;
import com.moneyKey.util.CommonUtil;
import com.moneyKey.util.Constants;
import com.moneyKey.util.Constants.FileType;

import cucumber.api.java.en.Then;

public class ChildAccount {
	
	private static Logger log = Logger.getLogger(ChildAccount.class);
	private static String pagename = "ChildAccount";

	@Then("^user is on \"([^\"]*)\" page$")
	public void verify_ChildAccountpage(String page) throws Throwable{
		Thread.sleep(2000); //This wait is needed to allow ChildAccount page to load fully
		String pageMessage = Constants.pageMessageChild_en;
		String pageMessLocator = page;
		
		verify_ChildAccountpage_web(pageMessage,pageMessLocator);
	}

	public void verify_ChildAccountpage_web(String errorKeyOrMessage, String errorField) throws Throwable{
		log.info("Start : Verify Login page loads|web");
		try{
			Thread.sleep(3000);
			CommonUtil.current_page = pagename;
			log.info("Start : verify message value");	
			String key = errorField;
			String locator = CommonUtil.getPropertyValue(CommonUtil.current_page, key, FileType.OR);
			
			if(CommonProcessor.isVisible(locator)){
				CommonProcessor.fieldValueCheck(locator, errorKeyOrMessage);
				log.info(("\n"+"input Message :: "+errorKeyOrMessage.trim()+"\n"));
			}

			log.info("\n\nCurrent page will be considered as :: "+CommonUtil.current_page+"\n\n");
			}catch(Throwable e){
			log.warn("Error encountered during Loginpage loading");
			log.error("Error details : "+e.getLocalizedMessage());
			CommonUtil.throwCustomException(e.getMessage());
		}finally{
			log.info("End : Verify Login page loads|web");
		}		
	}
}
