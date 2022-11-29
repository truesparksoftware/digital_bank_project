package com.transaction.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails {
private String account;
private float balance;
private String accType;
private String date;
}
