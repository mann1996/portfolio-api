package com.portfolio.api.security;

public class WebSecurityConstants {
	public static final Long EXPIRATION_TIME = 864000000L; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGNUP_URL = "/users/create";
	public static final String SHOW_URL = "/users/show";
	public static final String LOGIN_URL = "/users/login";
	public static final String TOKEN_SECRET = "6muc3b2kzq0e";
}
