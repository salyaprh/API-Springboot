package com.api.security.persistent;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.security.TokenFields;

@RequestMapping("/api/User")
@RestController
public class TbUserApi {
	private TbUserService service;
	public TbUserApi(TbUserService service) {
		this.service = service;
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<TbUser> findById(String id){
		TbUser cust = service.readUser(id);
		return ResponseEntity.ok(cust);
	}

	@PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DtoUser> postUser(@RequestBody DtoUser dto){
		service.createUser(dto.getId(), dto.getNama(), dto.getPassword());
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/login/")
	public ResponseEntity<TokenFields> login(String id, String password){
		TokenFields token = service.login(id,password);  
		return ResponseEntity.ok(token);
	}

}
