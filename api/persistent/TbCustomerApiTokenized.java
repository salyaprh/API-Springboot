package com.api.persistent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/customerTokenized")
@RestController
public class TbCustomerApiTokenized {
	private TbCustomerService service;
	public TbCustomerApiTokenized(TbCustomerService service) {
		this.service = service;
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<TbCustomer> findById(Integer id){
		System.out.println(id+"====>");
		TbCustomer cust = service.readCustomer(id);
		System.out.println(id+"====>"+cust);
		return ResponseEntity.ok(cust);
	}

}
