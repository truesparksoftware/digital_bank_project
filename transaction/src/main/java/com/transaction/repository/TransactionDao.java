package com.transaction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.transaction.model.request.TransactionModel;

@Repository
public interface TransactionDao {
	List<TransactionModel> getTrnasactionRange(Date startDate,Date endDate,String account);
}
