package com.guru99.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementations.ElementControl;

public class BasePage {
 private WebDriver driver;
 public ElementControl elementControl;
 public BasePage(WebDriver driver) {
	this.driver = driver;
	elementControl = new ElementControl(driver);
	}
}
