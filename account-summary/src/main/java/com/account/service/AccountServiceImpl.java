package com.account.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.account.model.request.Account;
import com.account.model.response.AccountDetails;
import com.account.model.response.AccountSummary;
import com.account.model.response.Customer;
import com.account.repository.AccountRepository;

@Service
public class AccountServiceImpl {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	AccountRepository customerRepository;

	@Value("${customer_details_byid}")
	private String customer_by_id_url;

	public Account saveCustomer(Account customer) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
		String requiredDate = df.format(new Date());
		customer.setDate(requiredDate);
		customerRepository.save(customer);
		return customer;
	}

	public List<Account> allCustomer() {
		List<Account> customerList = customerRepository.findAll();
		return customerList;

	}

	public String deleteById(String id) {
		customerRepository.deleteById(id);
		return "customer deleted successFully";
	}

	public Account updateCustomer(String id, Account customer) {
		Optional<Account> customerupdate = customerRepository.findById(id);
		Account cust = customerupdate.get();
		cust.setAccType(customer.getAccType());
		cust.setBalance(customer.getBalance());
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
		String requiredDate = df.format(new Date());
		cust.setDate(requiredDate);
		customerRepository.save(cust);
		return cust;
	}

	public AccountSummary findAccount(String account_id) {
		Optional<Account> acc = customerRepository.findById(account_id);
		Account acc1 = acc.get();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<Customer> entity = new HttpEntity<Customer>(header);
		Map<String, String> vars = new HashMap<>();
		vars.put("account", account_id);
		ResponseEntity<Customer> cus = restTemplate.exchange(customer_by_id_url, HttpMethod.GET, entity, Customer.class,vars);
		/*
		 * org.json.JSONArray jsonArray=new org.json.JSONArray(cus.getBody());
		 * List<Customer> customerList=new ArrayList<Customer>(); for (int i =0; i<
		 * jsonArray.length(); i++) { JSONObject json = (JSONObject) jsonArray.get(i);
		 * try { Customer customer=new Customer(); customer.setId(json.getString("id"));
		 * customer.setFname(json.getString("fname"));
		 * customer.setLname(json.getString("lname"));
		 * customer.setMobile(json.getString("mobile"));
		 * customer.setAccount(json.getString("account")); customerList.add(customer);
		 * System.out.println(customer.toString()); }catch(Exception e) {
		 * e.printStackTrace(); } }
		 * 
		 * 
		 * 
		 * System.out.println("customer list"+customerList);
		 */

		
		  
		  Customer customer=cus.getBody();
		  System.out.println("customer body  "+customer.toString()); 
		  AccountSummary accSummary=new AccountSummary(); 
		  AccountDetails accDetails=new AccountDetails();
		  accDetails.setAccount(acc1.getAccount());
		  accDetails.setAccType(acc1.getAccType());
		  accDetails.setBalance(acc1.getBalance()); accDetails.setDate(acc1.getDate());
		  accSummary.setAccountDetails(accDetails); accSummary.setCustomer(customer);
		 
		return accSummary;
	}

	public String updateAccountBalance(String account_id, Float balance) {
		Optional<Account> accountDetails = customerRepository.findById(account_id);
		Account acc1 = accountDetails.get();
		acc1.setBalance(balance);
		customerRepository.save(acc1);
		return "account updated successFully";
	}
}
