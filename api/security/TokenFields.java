package com.api.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenFields {
	private String tokenHolder;
	private String algorithm;
	private String flag;
	private String sessionToken;
	private String message;
}
