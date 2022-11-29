package com.transaction.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.transaction.model.request.TransactionModel;
@Repository
public interface TransactionRepository extends MongoRepository<TransactionModel, String>{
	@Query("{account :?0}")                                             
	Optional<List<TransactionModel>> getAllTrnasactionById(String account_id);
	
	@Query("{'date' : { $gte: ?0, $lte: ?1 },'account':?0 }")                 
	public List<TransactionModel> getObjectByDate(Date fromDate, Date toDate,String account); 
	
}
