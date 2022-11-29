package com.transaction.controller;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.itextpdf.text.DocumentException;
import com.transaction.model.request.TransactionModel;
import com.transaction.model.response.AccountSummary;
import com.transaction.repository.TransactionDao;
import com.transaction.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	TransactionDao transactionDao;
	
	
	@GetMapping("/getAllTransactions")
	private ResponseEntity<List<TransactionModel>> getAllTransactions() throws FileNotFoundException, DocumentException {
		List<TransactionModel> Transactionlist = transactionService.getAllTransactions();
		return new ResponseEntity<List<TransactionModel>>(Transactionlist, HttpStatus.OK);

	}

	@PostMapping("/saveTransactions")
	private ResponseEntity<TransactionModel> saveTransaction(@RequestBody TransactionModel transactionModel) {
		TransactionModel transactionModel1 = transactionService.saveTransaction(transactionModel);
		return new ResponseEntity<TransactionModel>(transactionModel1, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{transaction_id}")
	private ResponseEntity<String> deleteById(@PathVariable String transaction_id) {
		String message = transactionService.deleteById(transaction_id);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PutMapping("/update/{transaction_id}")
	private ResponseEntity<TransactionModel> updateById(@PathVariable String transaction_id, @RequestBody TransactionModel transactionModel) {
		TransactionModel transactionModel2 = transactionService.updateById(transaction_id, transactionModel);
		return new ResponseEntity<TransactionModel>(transactionModel2, HttpStatus.OK);
	}
	
	// tranction details based on id
	
	@GetMapping("tranhistory/{account_id}")
  public ResponseEntity<AccountSummary> getTransactionDetails(@PathVariable String account_id) throws FileNotFoundException, DocumentException{
		AccountSummary accountSummary=transactionService.getTrnactionDetails(account_id);
	  return new ResponseEntity<AccountSummary>(accountSummary,HttpStatus.OK);
  }
	
	@PostMapping("/transfer/{from_account}/{amount}/{to_account}")
	public ResponseEntity transferFund(@PathVariable String from_account, @PathVariable Float amount,@PathVariable String to_account) throws FileNotFoundException, DocumentException {
		// step 1 fetch from account details and balance to validate amount
		// step 2 fetch to account details and add balance into to account details
		// step 3 generate account transaction and save into transaction table
		// step 4 update account table with latest balance
		transactionService.transferFund(from_account,amount,to_account);
		

		return null;
		
	}
	
	
	@GetMapping("/limithistory/{accounntId}/{fromDate}/{toDate}")
	public List<TransactionModel> getLimitedTransctionHistory(@PathVariable String accounntId,@PathVariable String fromDate,@PathVariable String toDate) throws ParseException{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS\'Z\'");
			Date startDate1 = df.parse(fromDate + "Z");
			Date endDate1 = df.parse(toDate + "Z");
			List<TransactionModel> transactionList = transactionDao.getTrnasactionRange(startDate1, endDate1, accounntId);

		return transactionList;
	}
}


