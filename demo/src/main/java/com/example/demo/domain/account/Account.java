package com.example.demo.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 계좌 Entity
 */
@Data
@Table(name = "ACCOUNT")
@Entity
public class Account {
	
	@Id
	@Column(name = "ACCOUNT_NO")
	private String accountNo; 	//계좌번호
	
	@Column(name = "ID")
	private int id;				//사용자 ID
	
	
}
