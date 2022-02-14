package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.accounHst.AccountHst;
import com.example.demo.domain.accounHst.AccountHstRepository;
import com.example.demo.domain.account.Account;
import com.example.demo.domain.account.AccountRepository;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.service.TasksService;

/**
 * 과제 ServiceImpl
 */
@Service
public class TasksServiceImpl implements TasksService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountHstRepository accountHstRepository;
	
	/**
	 * 사용자 추가
	 */
	@Override
	public User postUser(User user) throws Exception {
		return userRepository.save(user);
	}
	
	/**
	 * 사용자 목록
	 */
	@Override
	public List<User> getUser() throws Exception {
		return (List<User>) userRepository.findAll();
	}
	
	/**
	 * 계좌 추가
	 */
	@Override
	public Account postAccount(Account account) throws Exception {
		return accountRepository.save(account);
	}
	
	/**
	 * 계좌 목록
	 */
	@Override
	public List<Account> getAccount() throws Exception {
		return (List<Account>) accountRepository.findAll();
	}
	
	/**
	 * 계좌내역 추가
	 */
	@Override
	public AccountHst postAccountHst(AccountHst accountHst) throws Exception {
		return accountHstRepository.save(accountHst);
	}
	
	/**
	 * 계좌내역 목록
	 */
	@Override
	public List<AccountHst> getAccountHst() throws Exception {
		return (List<AccountHst>) accountHstRepository.findAll();
	}
	
}
