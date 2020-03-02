package com.moneyKey.stepDefinition;

import org.apache.log4j.Logger;
import com.moneyKey.util.CommonUtil;
import cucumber.api.java.en.Then;

public class CreateAccountPage {
	
	private static Logger log = Logger.getLogger(CreateAccountPage.class);
	private static String pagename = "CreateAccount";

	@Then("^user is on CreateAccount page$")
	public void verify_loginpage() throws Throwable{
		Thread.sleep(2000); //This wait is needed to allow CreateAccount page to load fully
		verify_Loginpage_web();
	}

	public void verify_Loginpage_web() throws Throwable{
		log.info("Start : Verify Login page loads|web");
		try{
			Thread.sleep(300);
			CommonUtil.current_page = pagename;
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
