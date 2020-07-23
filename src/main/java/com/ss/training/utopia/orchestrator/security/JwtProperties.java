package com.ss.training.utopia.orchestrator.security;
/**
 * @author Trevor Huis in 't Veld
 * @author Justin O'Brien
 */
public class JwtProperties {

	public static final String SECRET = System.getenv("JWT_SECRET"); // "SmoothstackJava042020UTOPIA";
    public static final int EXPIRATION_TIME = 86_400_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}