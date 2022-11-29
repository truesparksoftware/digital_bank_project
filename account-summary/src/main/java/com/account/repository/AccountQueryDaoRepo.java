package com.account.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.account.model.request.Account;

@Repository
public class AccountQueryDaoRepo implements AccountQueryDao{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<Account> getDefaulterAccounts() {
		// TODO Auto-generated method stub
		Query query=new Query();
		query.addCriteria(Criteria.where("balance").lt(5000));
		List<Account> accList=mongoTemplate.find(query, Account.class);
		return accList;
	}

}
