package com.api.persistent;

import com.api.security.RecordKeyFinder;
import com.api.security.TokenFields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomerFinder {
	private TokenFields tokenHeader;
	private RecordKeyFinder finder;
}





