package PB2B.AccountManagement.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.ResponseEntity;

import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.Transaction;
import PB2B.AccountManagement.util.ResponseStructure;

public interface TransactionService 
{
	public ResponseEntity<ResponseStructure<Account>> creditTransaction(String type, Long accountNo, Long amount, Account account);
	public ResponseEntity<ResponseStructure<Account>> debitTransaction(String type, Long accountNo, Long amount, Account account);	
	public ResponseEntity<ResponseStructure<List<Transaction>>> findStatementByAccountNumber(Long accountNumber); 
	
	public ResponseEntity<ResponseStructure<Account>> fundsTransfer(Transaction transaction);
	
}
