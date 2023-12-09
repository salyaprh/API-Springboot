package com.api.persistent;

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
@Table(name = "tb_customer")
@Entity
public class TbCustomer {
	@Id
	@Column(name = "id_cust")
	private Integer idCust;
	
	@Column(name = "nama_cust", length = 100)
	private String namaCust;
	
	@Column(name = "no_hp", length = 20)
	private String noHp;
	
	@Column(name = "alamat", length = 200)
	private String alamat;
}





