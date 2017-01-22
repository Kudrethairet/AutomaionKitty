package com.selenium.week8.greatcourses.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.selenium.customlibrary.BasePage;

public class HomePage extends BasePage{

	private String homepageURL = "http://www.thegreatcourses.com/";
	
	public HomePage goto_theGreatCoursesWebsite()
	{
		driver.get(homepageURL);
		String pageTitle = driver.getTitle();
		System.out.println("Website title: " + pageTitle);
		Assert.assertEquals(pageTitle, "The Great Courses");
		return this;
	}
	
	public HomePage click_CategorySienceLink()
	{		
		myLib.clickOnHiddenElement(By.linkText("Science"));
		return this;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
