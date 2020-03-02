package com.moneyKey.stepDefinition;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class apiTestRunner {

	String addURI;
	HttpHeaders headers;
	RestTemplate restTemplate;
	ResponseEntity<String> response;
	String employeeId; 
	String responseBody;
		
	@Given("^user set POST Employee service api endpoint$")
	public void user_set_POST_Employee_service_api_endpoint() throws Throwable {
		  addURI = "http://dummy.restapiexample.com/api/v1/create";
		  System.out.println("Add URL :"+addURI);
	 
	}

	@When("^user set request Header$")
	public void user_set_request_Header() throws Throwable {
	    
	  headers = new HttpHeaders();
	 headers.add("Accept","application/json");
	 headers.add("Content-Type","application/json");
	}

	@When("^user Send a POST HTTP Request$")
	public void user_Send_a_POST_HTTP_Request() throws Throwable {
		   String emp_name = "meena";
		    String jsonBody="{\"name\":\""+emp_name+"\",\"salary\":\"1230\",\"age\":\"29\",\"id\":\"25\"}";
		  System.out.println("\n\n" + jsonBody);
		  HttpEntity<String>entity = new HttpEntity<String>(jsonBody, headers);  
		  //POST Method to Add New Employee
		  System.out.println("\nentity\n" + entity);
		  System.out.println("\naddURI\n" + addURI);
		  restTemplate = new RestTemplate ();
		  response = restTemplate.postForEntity(addURI, entity, String.class);
		  System.out.println("Update Response Body :"+response);  
	 
	}

	@Then("^user recieves valid Response$")
	public void user_recieves_valid_Response() throws Throwable {
		   // Write response to file
		   responseBody = response.getBody().toString();
		  System.out.println("responseBody --->" + responseBody);
		  // Get ID from the Response object
		    employeeId = getEmpIdFromResponse(responseBody);
		  System.out.println("empId is :" + employeeId);
		  // Check if the added Employee is present in the response body.
		  Assert.assertTrue(responseBody.contains("meena"));
		  System.out.println("Employee is Added successfully employeeId:"+employeeId); 
	 
	}

	@Given("^user set PUT Employee service api endpoint$")
	public void user_set_PUT_Employee_service_api_endpoint() throws Throwable {
		  String addURI = "http://dummy.restapiexample.com/api/v1/update/21";
		  System.out.println("Add URL :"+addURI);
	 
	}

	@When("^user set Update request Body$")
	public void user_set_Update_request_Body() throws Throwable {
		  
		  String emp_name = "employee123";
		  String updateURI = "http://dummy.restapiexample.com/api/v1/update/21";
		    String jsonBody="{\"name\":\""+emp_name+"\",\"salary\":\"123\",\"age\":\"23\",\"id\":\"25\"}";
		  System.out.println("\n\n" + jsonBody);
		  HttpEntity<String>entity = new HttpEntity<String>(jsonBody, headers);  
		  //POST Method to Add New Employee
		  restTemplate = new RestTemplate ();
	       response = restTemplate.exchange(updateURI, HttpMethod.PUT, entity, String.class);
	          String responseBody = response.getBody().toString();
	          System.out.println("Update Response Body :"+responseBody);  
	 
	}

	@Then("^user recieves valid HTTP Response Code (\\d+)$")
	public void user_recieves_valid_HTTP_Response_Code(int arg1) throws Throwable {
	  
	 // Check if the status code is 200
		  Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	 
	}

	@Given("^user set GET Employee service api endpoint$")
	public void user_set_GET_Employee_service_api_endpoint() throws Throwable {
		  String addURI = "http://dummy.restapiexample.com/api/v1/employees";
		  System.out.println("Add URL :"+addURI);
	 
	}

	@When("^user Send GET HTTP Request and verifies success code$")
	public void user_Send_GET_HTTP_Request() throws Throwable {
	       String getURI = "http://dummy.restapiexample.com/api/v1/employees";
	       RestTemplate restTemplate = new RestTemplate();
	          //GET Method to Get existing Employee
	       ResponseEntity<String> response = restTemplate.getForEntity(getURI,String.class);
	          System.out.println("response :"+response);
	          // Write response to file
	          String responseBody = response.getBody().toString();
	           
	          //Suppressing for log diffs
	          System.out.println("GET Response Body :"+responseBody);
	           
	           
	          // Check if the added Employee ID is present in the response body.
	          Assert.assertTrue(responseBody.contains("Doris Wilder"));
	           
	          // Check if the status code is 200
	          Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	 
	}

	@Given("^user set DELETE Employee service api endpoint$")
	public void user_set_DELETE_Employee_service_api_endpoint() throws Throwable {
		  String addURI = "http://dummy.restapiexample.com/api/v1/delete/6";
		  System.out.println("Add URL :"+addURI);
	 
	}

	@Given("^user Send a DELETE HTTP Request$")
	public void user_Send_a_DELETE_HTTP_Request() throws Throwable {
		   String delURI = "http://dummy.restapiexample.com/api/v1/delete/6";
           HttpHeaders headers = new HttpHeaders();
           HttpEntity<String> entity = new HttpEntity<String>(headers); 
           RestTemplate restTemplate = new RestTemplate();
	        
	       
           //DELETE Method to Delete existing Employee
           ResponseEntity<String> response = restTemplate.exchange(delURI, HttpMethod.DELETE, entity, String.class);  
           System.out.println("response :"+response);
           // Check if the status code is 204
           Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);           
            
           String responseBody = response.getBody();
           System.out.println("responseBody :"+responseBody);
            
           Assert.assertEquals(getMessageFromResponse(responseBody), "successfully! deleted Records");
            	 
	}

	 public static String getEmpIdFromResponse(String json) {
	        JSONParser parser = new JSONParser();
	        JSONObject jsonResponseObject = new JSONObject();
	        Object obj = new Object();
	        try {
	            obj = parser.parse(json);
	            System.out.println("obj :"+obj);
	        } catch (org.json.simple.parser.ParseException e) {
	            e.printStackTrace();
	        }
	        jsonResponseObject = (JSONObject) obj;
	        System.out.println("jsonResponseObject :"+jsonResponseObject);
	         String id = jsonResponseObject.get("data").toString();
	         
	        System.out.println("id :"+id);
	        
	        return id;
	    }
     public static String getMessageFromResponse(String json) {
         String successMessageText = null;
         try {
             JSONParser parser = new JSONParser();
             JSONObject jsonResponseObject = new JSONObject();
             jsonResponseObject = (JSONObject) (parser.parse(json));
             String successMessage = jsonResponseObject.get("status").toString();
             System.out.println("successMessage :"+successMessage);
             jsonResponseObject = (JSONObject) (parser.parse(successMessage));
             successMessageText = jsonResponseObject.get("text").toString();
             System.out.println("successMessageText :"+successMessageText);
         } catch (org.json.simple.parser.ParseException e) {
             e.printStackTrace();
         }
         return successMessageText;
     }
}
