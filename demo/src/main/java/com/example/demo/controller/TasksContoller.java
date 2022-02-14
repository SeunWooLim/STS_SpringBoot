package com.example.demo.controller;

import java.util.List;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.accounHst.AccountHst;
import com.example.demo.domain.account.Account;
import com.example.demo.domain.dto.BalancdAccountDto;
import com.example.demo.domain.dto.BalanceAgeDto;
import com.example.demo.domain.dto.BalancePeriodDto;
import com.example.demo.domain.dto.BalanceYearDto;
import com.example.demo.domain.user.User;
import com.example.demo.query.TasksQuery;
import com.example.demo.service.TasksService;


/**
 *	과제 Controller
 *	- 사용자 추가
 *	- 사용자 목록
 *	- 계좌 추가
 *	- 계좌 목록
 *	- 계좌내역 추가
 *	- 계좌내역 목록
 *	- 추가 REST API
		1) 사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오
		2) 사용자 나이대 별로, 평균 예치금을 출력하시오
		3) 년도를 입력받아, 해당년도의 예치금 총액을 출력하시오
		4) 기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오
 */
@RestController
public class TasksContoller {
	
	@Autowired
	private TasksQuery tasksQuery;
	
	@Autowired
	private TasksService tasksService;
	
	@PostMapping("/exception")
    public void exceptionTest() throws Exception {
        throw new Exception();
    }
	
	/**
	 * 사용자 추가
	 */
	@PostMapping("/insertUser")
	public ResponseEntity<User> postUser(@RequestBody User user) throws Exception {
		return new ResponseEntity<User>(tasksService.postUser(user), HttpStatus.CREATED);
	}
	
	/**
	 * 사용자 목록
	 */
	@GetMapping("/getUserList")
	public ResponseEntity<List<User>> getUser() throws Exception {
		return new ResponseEntity<List<User>>(tasksService.getUser(), HttpStatus.OK);
	}
	
	/**
	 * 계좌 추가
	 */
	@PostMapping("/insertAccount")
	public ResponseEntity<Account> postAccount(@RequestBody Account account) throws Exception {
		return new ResponseEntity<Account>(tasksService.postAccount(account), HttpStatus.CREATED);
	}
	
	/**
	 * 계좌 목록
	 */
	@GetMapping("/getAccountList")
	public ResponseEntity<List<Account>> getAccount() throws Exception {
		return new ResponseEntity<List<Account>>(tasksService.getAccount(), HttpStatus.OK);
	}
	
	/**
	 * 계좌내역 추가
	 */
	@PostMapping("/insertAccountHst")
	public ResponseEntity<AccountHst> postAccountHst(@RequestBody AccountHst accountHst) throws Exception {
		return new ResponseEntity<AccountHst>(tasksService.postAccountHst(accountHst), HttpStatus.CREATED);
	}
	
	/**
	 * 계좌내역 목록
	 */
	@GetMapping("/getAccountHstList")
	public ResponseEntity<List<AccountHst>> getAccountHst() throws Exception {
		return new ResponseEntity<List<AccountHst>>(tasksService.getAccountHst(), HttpStatus.OK);
	}
	
	/**
	 * 추가 REST API
	 * 1) 사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오
	 */
	@GetMapping("/getBalanceByAccount/{id}")
	public ResponseEntity<List<BalancdAccountDto>> getBalanceByAccount(@PathVariable String id) throws Exception {
		
		System.out.println("id : " + id);
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		List<BalancdAccountDto> returnList = jpaResultMapper.list(tasksQuery.findBalanceByAccount(id), BalancdAccountDto.class);
		
		return new ResponseEntity<List<BalancdAccountDto>>(returnList, HttpStatus.OK);
	}
	
	/**
	 * 추가 REST API
	 * 2) 사용자 나이대 별로, 평균 예치금을 출력하시오
	 */
	@GetMapping("/getAvgBalanceByAge")
	public ResponseEntity<List<BalanceAgeDto>> getAvgBalanceByAge() throws Exception {
		
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		List<BalanceAgeDto> returnList = jpaResultMapper.list(tasksQuery.findAvgBalanceByAge(), BalanceAgeDto.class);
		
		return new ResponseEntity<List<BalanceAgeDto>>(returnList, HttpStatus.OK);
	}
	
	/**
	 * 추가 REST API
	 * 3) 년도를 입력받아, 해당년도의 예치금 총액을 출력하시오
	 */
	@GetMapping("/getBalanceByYear/{year}")
	public ResponseEntity<List<BalanceYearDto>> getBalanceByYear(@PathVariable String year) throws Exception {
		
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		List<BalanceYearDto> returnList = jpaResultMapper.list(tasksQuery.findBalanceByYear(year), BalanceYearDto.class);
		
		return new ResponseEntity<List<BalanceYearDto>>(returnList, HttpStatus.OK);
	}
	
	/**
	 * 추가 REST API
	 * 4) 기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오
	 */
	@GetMapping("/getBalanceBySortingId/{fromDate}/{toDate}")
	public ResponseEntity<List<BalancePeriodDto>> getBalanceByPeriod(@PathVariable String fromDate, @PathVariable String toDate) throws Exception {
		
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		List<BalancePeriodDto> returnList = jpaResultMapper.list(tasksQuery.findBalanceBySortingId(fromDate, toDate), BalancePeriodDto.class);
		
		return new ResponseEntity<List<BalancePeriodDto>>(returnList, HttpStatus.OK);
	}
	
}
