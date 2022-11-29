package com.transaction.model.request;


import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionModel  {
	@MongoId
	private String transaction_id;
	private String account;
	private String from_account;
	private String to_account;
	private Float transaction_amount;
	private Date transaction_date=new Date();
}
