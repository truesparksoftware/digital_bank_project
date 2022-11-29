package com.account.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.account.model.request.Account;

@Repository
public interface AccountQueryDao {
   public  List<Account> getDefaulterAccounts();
}
