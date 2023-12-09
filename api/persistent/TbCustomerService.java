package com.api.persistent;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.security.persistent.TbUserService;

@Service
public class TbCustomerService {
	private TbCustomerRepository repository;
	
	public TbCustomerService(TbCustomerRepository repository) {
		this.repository = repository;
	}
	
	public TbCustomer readCustomer(Integer id) {
		Optional<TbCustomer> option = repository.findById(id);
		if (option.isEmpty())
			return null;
		else	
			return option.get();
					
	}

}
