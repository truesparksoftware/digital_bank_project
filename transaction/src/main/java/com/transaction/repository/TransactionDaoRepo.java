package com.transaction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.transaction.model.request.TransactionModel;

@Repository
public class TransactionDaoRepo implements TransactionDao{

	@Autowired 
	MongoTemplate mongoTemplate;
	
	
	
	@Override
	public List<TransactionModel> getTrnasactionRange(Date startDate, Date endDate, String account) {
		
		//transaction_date>start && transaction_date<endDate && account=?
		//String dat="2022:01";
		Query query=new Query();
		query.addCriteria(Criteria.where("transaction_date").gt(startDate).andOperator(Criteria.where("transaction_date").lt(endDate).andOperator(Criteria.where("account").is(account))));
		List<TransactionModel> transactionList= mongoTemplate.find(query, TransactionModel.class);
		return transactionList;
	}
}
