package PB2B.AccountManagement.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import PB2B.AccountManagement.dao.AccountDao;
import PB2B.AccountManagement.dao.TransactionDao;
import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.Transaction;
import PB2B.AccountManagement.repository.AccountRepository;
import PB2B.AccountManagement.util.ResponseStructure;

@Service
public class AccountServiceImpl implements AccountService 
{
	@Autowired
	AccountDao accountDao; 
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account createAccount(Customer customer) 
	{
		return accountDao.save((new Account(customer.getCustomerId(), Long.parseLong("0"))));
		
			//return accountDao.getOne(customer.getCustomerId());
	}

	@Override
	public Account deposit(Long accountNo, Account account) 
	{
		Account databaseAccount = accountDao.findById(accountNo).get();
		databaseAccount.setBalance(databaseAccount.getBalance() + account.getBalance());
					
		return accountDao.save(databaseAccount);
		
	}

	
	@Override
	public Boolean withdraw(Long accountNo, Account account) 
	{
		
		Boolean isWithdrawn = false;
		
		Account databaseAccount = accountDao.findById(accountNo).get();
		
		if(databaseAccount.getBalance() > account.getBalance())
		{
			databaseAccount.setBalance(databaseAccount.getBalance() - account.getBalance());
			accountDao.save(databaseAccount);
			isWithdrawn = true;	
		}
				
		 return isWithdrawn;		 
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccounts(Long customerId) 
	{
		List<Account> allAccounts = accountRepository.findByCustomerID(customerId);
		ResponseStructure<List<Account>> structure = new ResponseStructure<List<Account>>();
		
		structure.setMessage("All linked accounts for the customer id "+customerId);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(allAccounts);
		
		return new ResponseEntity<ResponseStructure<List<Account>>>(structure, HttpStatus.CREATED);
	}

	
	@Override
	public Account getAccountInfo(Long accountNo) 
	{
		return accountDao.findById(accountNo).get();
	}

	
	
	
}
