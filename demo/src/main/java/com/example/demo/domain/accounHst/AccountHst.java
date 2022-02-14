package com.example.demo.domain.accounHst;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 계좌내역 Entity
 */
@Data
@Table(name = "ACCOUNT_HST")
@Entity
public class AccountHst {
    
    @Id
    @Column(name = "ACCOUNT_NO")
    private String accountNo; 		//계좌번호
	
	@Column(name = "IO_YN")
	private String ioYn;			//입출금여부
	
	@Column(name = "DEPOSIT_PRC")
	private BigDecimal depositPrc;	//입금액
	
	@Column(name = "DEPOSIT_DT")
	private String depositDt;		//입금일

	public AccountHst(String accountNo, String ioYn, BigDecimal depositPrc, String depositDt) {
		this.accountNo = accountNo;
		this.ioYn = ioYn;
		this.depositPrc = depositPrc;
		this.depositDt = depositDt;
	}
	
	
}
