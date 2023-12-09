package com.api.security.persistent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenSessionRepository extends JpaRepository<TokenSession, String>{
	

}
