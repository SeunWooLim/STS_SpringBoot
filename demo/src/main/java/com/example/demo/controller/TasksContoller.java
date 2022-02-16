package com.example.demo.controller;

import java.util.List;

import org.qlrm.mapper.JpaResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 *	Controller
 *	- 사용자 추가
 *	- 사용자 목록
 *	- 계좌 추가
 *	- 계좌 목록
 *	- 계좌내역 추가
 *	- 계좌내역 목록
 *	- 추가 REST API
		1) 사용자를 입력받아, 사용자의 계좌별 예치금을 출력
		2) 사용자 나이대 별로, 평균 예치금을 출력
		3) 년도를 입력받아, 해당년도의 예치금 총액을 출력
		4) 기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력
 */
@RestController
public class TasksContoller {
	
	private final Logger LOGGER = LoggerFactory.getLogger(TasksContoller.class);
	
	private TasksQuery tasksQuery;
	
	private TasksService tasksService;
	
	@Autowired
	public TasksContoller(TasksQuery tasksQuery, TasksService tasksService) {
		super();
		this.tasksQuery = tasksQuery;
		this.tasksService = tasksService;
	}

	@PostMapping("/exception")
    public void exceptionTest() throws Exception {
        throw new Exception();
    }
	
	@PostMapping("/log")
    public void logTest() throws Exception {
		LOGGER.trace("Trace Log");
        LOGGER.debug("Debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("Error Log");
    }
	
	/**
	 * 사용자 추가
	 */
	@PostMapping("/insertUser")
	public ResponseEntity<User> postUser(@RequestBody User user) throws Exception {
		LOGGER.info("insert user");
		return new ResponseEntity<User>(tasksService.postUser(user), HttpStatus.CREATED);
	}
	
	/**
	 * 사용자 목록
	 */
	@GetMapping("/getUserList")
	public ResponseEntity<List<User>> getUser() throws Exception {
		LOGGER.info("user collection");
		return new ResponseEntity<List<User>>(tasksService.getUser(), HttpStatus.OK);
	}
	
	/**
	 * 계좌 추가
	 */
	@PostMapping("/insertAccount")
	public ResponseEntity<Account> postAccount(@RequestBody Account account) throws Exception {
		LOGGER.info("insert account");
		return new ResponseEntity<Account>(tasksService.postAccount(account), HttpStatus.CREATED);
	}
	
	/**
	 * 계좌 목록
	 */
	@GetMapping("/getAccountList")
	public ResponseEntity<List<Account>> getAccount() throws Exception {
		LOGGER.info("account collection");
		return new ResponseEntity<List<Account>>(tasksService.getAccount(), HttpStatus.OK);
	}
	
	/**
	 * 계좌내역 추가
	 */
	@PostMapping("/insertAccountHst")
	public ResponseEntity<AccountHst> postAccountHst(@RequestBody AccountHst accountHst) throws Exception {
		LOGGER.info("insert accountHst");
		return new ResponseEntity<AccountHst>(tasksService.postAccountHst(accountHst), HttpStatus.CREATED);
	}
	
	/**
	 * 계좌내역 목록
	 */
	@GetMapping("/getAccountHstList")
	public ResponseEntity<List<AccountHst>> getAccountHst() throws Exception {
		LOGGER.info("accountHst collection");
		return new ResponseEntity<List<AccountHst>>(tasksService.getAccountHst(), HttpStatus.OK);
	}
	
	/**
	 * 추가 REST API
	 * 1) 사용자를 입력받아, 사용자의 계좌별 예치금을 출력
	 */
	@GetMapping("/getBalanceByAccount/{id}")
	public ResponseEntity<List<BalancdAccountDto>> getBalanceByAccount(@PathVariable String id) throws Exception {
		
		LOGGER.info("id : " + id);
		
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		List<BalancdAccountDto> returnList = jpaResultMapper.list(tasksQuery.findBalanceByAccount(id), BalancdAccountDto.class);
		
		return new ResponseEntity<List<BalancdAccountDto>>(returnList, HttpStatus.OK);
	}
	
	/**
	 * 추가 REST API
	 * 2) 사용자 나이대 별로, 평균 예치금을 출력
	 */
	@GetMapping("/getAvgBalanceByAge")
	public ResponseEntity<List<BalanceAgeDto>> getAvgBalanceByAge() throws Exception {
		
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		List<BalanceAgeDto> returnList = jpaResultMapper.list(tasksQuery.findAvgBalanceByAge(), BalanceAgeDto.class);
		
		return new ResponseEntity<List<BalanceAgeDto>>(returnList, HttpStatus.OK);
	}
	
	/**
	 * 추가 REST API
	 * 3) 년도를 입력받아, 해당년도의 예치금 총액을 출력
	 */
	@GetMapping("/getBalanceByYear/{year}")
	public ResponseEntity<List<BalanceYearDto>> getBalanceByYear(@PathVariable String year) throws Exception {
		
		LOGGER.info("year : " + year);
		
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		List<BalanceYearDto> returnList = jpaResultMapper.list(tasksQuery.findBalanceByYear(year), BalanceYearDto.class);
		
		return new ResponseEntity<List<BalanceYearDto>>(returnList, HttpStatus.OK);
	}
	
	/**
	 * 추가 REST API
	 * 4) 기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력
	 */
	@GetMapping("/getBalanceBySortingId/{fromDate}/{toDate}")
	public ResponseEntity<List<BalancePeriodDto>> getBalanceByPeriod(@PathVariable String fromDate, @PathVariable String toDate) throws Exception {
		
		LOGGER.info("fromDate : " + fromDate + " toDate : " + toDate);
		
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		
		List<BalancePeriodDto> returnList = jpaResultMapper.list(tasksQuery.findBalanceBySortingId(fromDate, toDate), BalancePeriodDto.class);
		
		return new ResponseEntity<List<BalancePeriodDto>>(returnList, HttpStatus.OK);
	}
	
}
