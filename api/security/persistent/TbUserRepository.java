package com.api.security.persistent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TbUserRepository extends JpaRepository<TbUser, String>{
	

}
