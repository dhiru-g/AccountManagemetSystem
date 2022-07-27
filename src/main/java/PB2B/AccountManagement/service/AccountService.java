package PB2B.AccountManagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.util.ResponseStructure;

public interface AccountService 
{
	public Account createAccount(Customer customer);
	public Account deposit(Long accountNo, Account account);
	public Boolean withdraw(Long accountNo, Account account);
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccounts(Long customerId);
	public Account getAccountInfo(Long accountNo);
	
	
}
