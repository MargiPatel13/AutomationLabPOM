package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final int  DEFAULT_TIME_OUT = 5;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String PAGE_HEADER = "Your Store";

	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	
	public static final List<String> EXPECTED_ACCOUNT_SECTION_LIST= Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	public static final String SUCCESS_REGISTRATION_MESSAGE = "";

	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_SHEET_NAME = "product";
	public static final String SEARCHDATA_SHEET_NAME = "searchdata";
	public static final String PRODUCTINFO_SHEET_NAME = "productinfo";

}
