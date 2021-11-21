package com.sample.test.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;

public class DemoTest extends TestBase {


	public String selectPizzaAndToppings(String pizzaType, String toppings1, String toppings2) {
		 
	        Select pizza = new Select(driver.findElement(getLocators("pizza1")));
	        pizza.selectByValue(pizzaType);
	        Select toppins1 = new Select(driver.findElement(getLocators("pizza1Toppings1")));
	        toppins1.selectByValue(PizzaToppings.EXTRACHEESE.getDisplayName());
	        Select toppins2 = new Select(driver.findElement(getLocators("pizza1Toppings2")));
	        toppins2.selectByValue(PizzaToppings.MANGOS.getDisplayName());
	        int totalPizza=5;
	        driver.findElement(getLocators("pizza1Quantity")).clear();
	        driver.findElement(getLocators("pizza1Quantity")).sendKeys(""+totalPizza);
	        driver.findElement(getLocators("costLabel")).click();
	        
	        String expectedCost = pizza.getFirstSelectedOption().getText().split("\\$")[1];
	        String actualCost = driver.findElement(getLocators("pizza1Cost")).getAttribute("value");
	        Assert.assertEquals(Double.parseDouble(actualCost), Double.parseDouble(expectedCost)*totalPizza);
	        return actualCost;
	}
	
	public void selectPickupInformation() {
		 driver.findElement(getLocators("name")).sendKeys("Test");
	     driver.findElement(getLocators("email")).sendKeys("test@test.com");
	     driver.findElement(getLocators("phone")).sendKeys("343453535");
	       
	}
	
	public void selectPaymentAndOrder(String pizzaType, String totalCost) {
		 driver.findElement(getLocators("radioCreditCard")).click();
	        
	        driver.findElement(getLocators("placeOrderButton")).click();
	        
	        String response = driver.findElement(getLocators("dialogText")).getText();
	        String arr[] = response.split("TOTAL:");
	        String arr1[] = arr[1].trim().split(" ");
	        String totalOrderAmount = arr1[0];
	        String pizzaName = arr[1].substring(totalOrderAmount.length()+1).trim();
	        Assert.assertEquals(pizzaType, pizzaName);
	        Assert.assertEquals(totalCost, totalOrderAmount);
	}
	public void verifyErrorMessage() {
		 driver.findElement(getLocators("radioCreditCard")).click();
	        
	        driver.findElement(getLocators("placeOrderButton")).click();
	        
	        String response = driver.findElement(getLocators("dialogText")).getText();
	      
	        Assert.assertTrue(response.contains("Missing name"), "Error text: Missing name is not present");
	        Assert.assertTrue(response.contains("Missing phone number"), "Error text: Missing phone number is not present");
	}
	
	public void verifyFieldsDefaultValue() {
		
		Select pizza = new Select(driver.findElement(getLocators("pizza1")));
		Assert.assertEquals(pizza.getFirstSelectedOption().getText(),"Choose Pizza");

        Select toppins1 = new Select(driver.findElement(getLocators("pizza1Toppings1")));
        Assert.assertEquals(toppins1.getFirstSelectedOption().getText(),"Choose a Toppings 1");
        Select toppins2 = new Select(driver.findElement(getLocators("pizza1Toppings2")));
        Assert.assertEquals(toppins2.getFirstSelectedOption().getText(),"Choose a Toppings 2");
	}
	
	
    @Test
    public void demoTest() {
    	 String pizzaType = PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName();
    	 String toppings1 = PizzaToppings.EXTRACHEESE.getDisplayName();
    	 String toppings2 = PizzaToppings.ITALIANHAM.getDisplayName();
    	 String totalCost = selectPizzaAndToppings(pizzaType, toppings1, toppings2);
     
    	selectPickupInformation();
    	selectPaymentAndOrder(pizzaType, totalCost);
       
    }
    
    @Test
    public void demoTestNegative() {
    	 String pizzaType = PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName();
    	 String toppings1 = PizzaToppings.EXTRACHEESE.getDisplayName();
    	 String toppings2 = PizzaToppings.ITALIANHAM.getDisplayName();
    	 
    	 String totalCost = selectPizzaAndToppings(pizzaType, toppings1, toppings2);
     
    	 verifyErrorMessage();
       
    }

    @Test
    public void demoTestErrorCase() {
    	 String pizzaType = PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName();
    	 String toppings1 = PizzaToppings.EXTRACHEESE.getDisplayName();
    	 String toppings2 = PizzaToppings.ITALIANHAM.getDisplayName();
    	 
    	 selectPizzaAndToppings(pizzaType, toppings1, toppings2);
    	 driver.findElement(getLocators("resetButton")).click();
    	 verifyFieldsDefaultValue();
       
    }
}
