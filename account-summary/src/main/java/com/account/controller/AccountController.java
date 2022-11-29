package com.account.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.model.request.Account;
import com.account.model.response.AccountSummary;
import com.account.repository.AccountQueryDao;
import com.account.service.AccountServiceImpl;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountServiceImpl customerServiceImpl;
	
	@Autowired
	AccountQueryDao accountQueryDao;
	
	@Value("${message}")
	private String message;
	

	@GetMapping("/all")
	public ResponseEntity<List<Account>> allAccount() {
		System.out.println("test profile"+message);
		List<Account> customerList = customerServiceImpl.allCustomer();
		return new ResponseEntity<List<Account>>(customerList, HttpStatus.OK);
	}
	
	@GetMapping("/{account}")
	public ResponseEntity<AccountSummary> accountById(@PathVariable("account") String account_id) {
		AccountSummary account = customerServiceImpl.findAccount(account_id);
		return new ResponseEntity<AccountSummary>(account, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Account> saveAccount(@RequestBody Account customer) throws ParseException {
		Account customer1 = customerServiceImpl.saveCustomer(customer);
		return new ResponseEntity<Account>(customer1, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id) {
		String message = customerServiceImpl.deleteById(id);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable String id,@RequestBody Account account){
		Account cust=customerServiceImpl.updateCustomer(id,account);
		return new ResponseEntity<Account>(cust,HttpStatus.OK);
	}
	

	@PutMapping("/updatebalance/{account_id}/{balance}")
	public ResponseEntity<String> updateAccountBalance(@PathVariable String account_id, @PathVariable Float balance){
		String cust=customerServiceImpl.updateAccountBalance(account_id,balance);
		return new ResponseEntity<String>(cust,HttpStatus.OK);
	}
	
	@GetMapping("/defaulter")
	public List<Account> getAccountList(){
		List<Account> accountList=accountQueryDao.getDefaulterAccounts();
		return accountList;
	}
	
	
}
