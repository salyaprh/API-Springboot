package com.api.security.persistent;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity

public class TbUser {
	@Id
	@Column(name = "id", length = 50)
	private String id;
	
	@Column(name = "nama", length = 100)
	private String nama;
	
	@Column(name = "token", length = 500)
	private String token;
	
	@Column(name = "session_token", length = 500)
	private String session_token;
	
	@Column(name = "valid_token")
	private LocalDate valid_token;
	
	@Column(name = "valid_session")
	private Integer valid_session;
	
	@Column(name = "role", length = 10)
	private String role;
	
	@Column(name = "password", length = 200)
	private String password;
}
