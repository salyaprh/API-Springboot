package com.api.security.persistent;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {
	private String id;
	private String nama;
	private String token;
	private String session_token;
	private LocalDate valid_token;
	private Integer valid_session;
	private String role;
	private String password;	
}
