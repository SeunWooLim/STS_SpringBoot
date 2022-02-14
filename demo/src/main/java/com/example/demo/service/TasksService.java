package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.accounHst.AccountHst;
import com.example.demo.domain.account.Account;
import com.example.demo.domain.user.User;

public interface TasksService {
	
	public User postUser(User user) throws Exception;
	
	public List<User> getUser() throws Exception;
	
	public Account postAccount(Account account) throws Exception;
	
	public List<Account> getAccount() throws Exception;
	
	public AccountHst postAccountHst(AccountHst accountHst) throws Exception;
	
	public List<AccountHst> getAccountHst() throws Exception;
	
}
