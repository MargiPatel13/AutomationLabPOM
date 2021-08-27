package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImgs = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By productQuantity = By.cssSelector("input#input-quantity");
	private By addToCartBtn = By.cssSelector("button#button-cart");
	
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}
	
	public int getProductImgsCount() {
		return	elementUtil.getElements(productImgs).size();
	}
	
	public Map<String, String> getProductInfo() {
		
		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("Name", getProductHeaderText());
		
		List<WebElement> metaDataList = elementUtil.getElements(productMetaData);
		System.out.println("Product MetaData List: " + metaDataList.size());
		
		for(WebElement e: metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		
		List<WebElement> priceDataList = elementUtil.getElements(productPriceData);
		
		String price = priceDataList.get(0).getText().trim();
		String exTaxPrice = priceDataList.get(1).getText().trim();

		productInfoMap.put("Price", price);
		productInfoMap.put("TaxPrice", exTaxPrice);
		
		return productInfoMap;
	}
	
	
	
}
