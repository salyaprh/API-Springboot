package com.api.security.persistent;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name = "token_session")
@Entity

public class TokenSession {
	@Id
	@Column(name = "token_id", length = 50)
	private String tokenId;
	
	@Column(name = "waktu_dibuat")
	private LocalDateTime waktuDibuat;
	
	@Column(name = "expired")
	private LocalDateTime expired;
	
	@Column(name = "token", length = 200)
	private String token;
	
}
