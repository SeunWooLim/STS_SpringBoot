package com.example.demo.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 사용자 Entity
 */
@Data
@Table(name = "USER")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;			//사용자 ID
	
	@Column(name = "NAME")
	private String name;	//이름
	
	@Column(name = "AGE")
	private int age;		//나이
	
	@Column(name = "JOIN_DT")
	private String joinDt;	//가입일
}
