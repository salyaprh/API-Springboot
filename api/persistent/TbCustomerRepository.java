package com.api.persistent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TbCustomerRepository extends JpaRepository<TbCustomer, Integer> {
	
	
}
