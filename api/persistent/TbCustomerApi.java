package com.api.persistent;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.security.TokenFields;
import com.api.security.persistent.DtoUser;
import com.api.security.persistent.TbUserService;

@RequestMapping("/api/customer")
@RestController
public class TbCustomerApi {
	private TbCustomerService service;
	private TbUserService userService;

	public TbCustomerApi(TbCustomerService service, TbUserService userService) {
		this.service = service;
		this.userService = userService;
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<TbCustomer> findById(Integer id){
		System.out.println(id+"====>");
		TbCustomer cust = service.readCustomer(id);
		System.out.println(id+"====>"+cust);
		return ResponseEntity.ok(cust);
	}

	
	@PostMapping(value = "/findTokenedCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DtoCustomer> postFinder(@RequestBody DtoCustomerFinder dto){
		TokenFields token = dto.getTokenHeader();
		DtoCustomer dtoCustomer = new DtoCustomer();
		if (!userService.validateToken(token)) {
			token.setMessage("Token session sudah tidak valid");
			token.setFlag("false");
			dtoCustomer.setTokenHeader(token);
			return ResponseEntity.ok(dtoCustomer);
		}
		TbCustomer cust = service.readCustomer(Integer.parseInt(dto.getFinder().getKey()));
		if (cust == null) {
			token.setFlag("false");
			token.setMessage("Nasabah tidak ditemukan");
		}else {
			token.setFlag("true");
			token.setMessage("Ok");
		}
	    dtoCustomer.setCustomer(cust);
	    dtoCustomer.setTokenHeader(token);
	    System.out.println("response>> "+dtoCustomer.getCustomer().getNamaCust());
		return ResponseEntity.ok(dtoCustomer);
	}
}
