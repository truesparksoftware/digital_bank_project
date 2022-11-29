package com.transaction.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
private String id;
private String fname;
private String lname;
private String mobile;
private String email;
private String account;

}
