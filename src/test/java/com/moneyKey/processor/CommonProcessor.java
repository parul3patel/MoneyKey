package com.moneyKey.processor;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import com.moneyKey.util.CommonUtil;
import com.moneyKey.util.WebUtil;

public class CommonProcessor {

	private static Logger log = Logger.getLogger(CommonProcessor.class);

	
	public static void commonClick(String locator) throws Exception{
		try{
			log.info("Common Click starts..");

			List<WebElement> list = WebUtil.getAllWebElements(locator);
			if(list.size() == 0){
				throw new Exception("Element could not be located - Please update OR file.");
			}else if(list.size() > 1){
				throw new Exception("Multiple element with same identifier located - Please update OR file.");
			}
			WebElement we = list.get(0);

			WebUtil.mouseMove(we);
			WebDriverWait wait = new WebDriverWait(CommonUtil.driver, CommonUtil.avgWaitTime);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			we.click();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			log.info("Common Click ends..");
		}
	}
	
	public static void clickByJS(String locator) throws Exception{
		try{
			log.info("JS Click starts..");
			List<WebElement> list = WebUtil.getAllWebElements(locator);
			if(list.size() == 0){
				throw new Exception("Element could not be located - Please update OR file.");
			}else if(list.size() > 1){
				throw new Exception("Multiple element with same identifier located - Please update OR file.");
			}
			WebElement we = list.get(0);

			WebUtil.mouseMove(we);
			WebDriverWait wait = new WebDriverWait(CommonUtil.driver, CommonUtil.avgWaitTime);
			wait.until(ExpectedConditions.elementToBeClickable(we));
			((JavascriptExecutor)CommonUtil.driver).executeScript("arguments[0].click();", we);
			Thread.sleep(6000L);
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			log.info("JS Click ends..");
		}
	}
	
	public static void enterValIntoField(String val, String locator) throws Exception{
		try{
			log.info("enterValIntoField starts..");
			List<WebElement> list = WebUtil.getAllWebElements(locator);
			if(list.size() == 0){
				throw new Exception("Element could not be located - Please update OR file.");
			}else if(list.size() > 1){
				throw new Exception("Multiple element with same identifier located - Please update OR file.");
			}
			WebElement we = list.get(0);
			WebDriverWait wait = new WebDriverWait(CommonUtil.driver, CommonUtil.avgWaitTime);
			log.info("wait="+wait);
			we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			log.info("we="+we);
			//we.clear();
			//WebUtil.mouseMove(we);
			we.click();
			//we.clear();
			we.sendKeys(val);
			//Thread.sleep(3000L);
			log.info("enterValIntoField is done");
		}catch(Exception e){
			log.error("Error in enterValIntoField :: "+e.getLocalizedMessage());
			log.error("\n\n"+e.getMessage()+"\n\n");
			throw e;
		}finally{
			log.info("enterValIntoField ends..");
		}
	}
	
	public static void selectRegularOption(String locator, String value) throws Exception{
		try{
			log.info("selectRegularOption starts..");
			List<WebElement> list = WebUtil.getAllWebElements(locator);
			if(list.size() == 0){
				throw new Exception("Element could not be located - Please update OR file.");
			}else if(list.size() > 1){
				throw new Exception("Multiple element with same identifier located - Please update OR file.");
			}
			WebElement we = list.get(0);

			WebUtil.mouseMove(we);
			Select dropdown = new Select(we);
			dropdown.selectByVisibleText(value);
			log.info("selection(regular) completed");
			//Thread.sleep(3000L);
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			log.info("selectRegularOption ends..");
		}
	}
	
	
	public static void fieldValueCheck(String locator, String value) throws Exception {
		try{
			log.info("ValueCheck starts..");
			Thread.sleep(3000l);
			if(WebUtil.getAllWebElements(locator).size() == 0){
				throw new Exception("Element could not be located - Please update OR file.");
			}
			WebElement we = WebUtil.getWebElement(locator);
			WebUtil.mouseMove(we);
			String origText = we.getText().trim();
			log.info("\n\n\nOriginal= "+origText+"\n\n\n");
			log.info("\n\nInput= "+value+"\n\n");

			value = value.toLowerCase();
			origText = origText.toLowerCase();
			
				if(value.equalsIgnoreCase(origText)){
					log.info("value matches");
				}else{
					log.error("value doesnt match");
					throw new Exception("value does not match");
				}
			
		}catch(Exception e){
			log.error("error occured during FieldValueCheck");
			throw e;
		}finally{
			log.info("End : FieldValueCheck ends..");
		}
	}
	
	public static boolean isVisible(String locator) throws Exception {
		try{
			log.info("isVisible starts..");
			boolean flag = false;
			if(WebUtil.getAllWebElements(locator).size() == 0){
				throw new Exception("Element could not be located - Please update OR file.");
			}
			WebElement we = WebUtil.getWebElement(locator);
			WebUtil.mouseMove(we);
			if(we.isDisplayed()){
				log.info("Field is visible");
				flag = true;
			}
			return flag;
		}catch(Exception e){
			log.error("Error occured during visibility check :: "+e.getLocalizedMessage());
			log.error("\n\n"+e.getMessage()+"\n\n");
			throw e;
		}finally{
			log.info("isVisible ends..");
		}
	}
	}
