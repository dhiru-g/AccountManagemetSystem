package PB2B.AccountManagement.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import PB2B.AccountManagement.dao.AccountDao;
import PB2B.AccountManagement.dao.TransactionDao;
import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.Transaction;
import PB2B.AccountManagement.modal.TransactionComparator;
import PB2B.AccountManagement.repository.TransactionRepository;
import PB2B.AccountManagement.util.ResponseStructure;

@Service
public class TransactionServiceImpl implements TransactionService
{
	@Autowired
	TransactionDao transactionDao; 
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountService accountService;
	
	
	@Override
	public ResponseEntity<ResponseStructure<Account>> creditTransaction(String type, Long accountNo, Long amount, Account account) 
	{
		Date currentDate = new Date(System.currentTimeMillis());
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		
		Transaction transaction = new Transaction(currentDate, type, amount, new Long(0), accountNo);
		transactionDao.save(transaction);
		account =  accountService.deposit(accountNo, account);
		
		structure.setMessage("Funds Deposited ...");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(account);
		
		return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED); 
		
		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<Account>> debitTransaction(String type, Long accountNo, Long amount, Account account) 
	{
		String withdrawMeassage = "Insufficient funds to withdraw";
		Date currentDate = new Date(System.currentTimeMillis());
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		
		Transaction transaction = new Transaction(currentDate, type, amount, accountNo, new Long(0) );
		transactionDao.save(transaction);
		
		
		if(accountService.withdraw(accountNo, account))
		{
			withdrawMeassage = "Funds withdrawn ... ";
		}
		
		account = accountService.getAccountInfo(accountNo);
		structure.setMessage(withdrawMeassage);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(account);
		
		return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED); 
	}

	
	
	@Override
	public ResponseEntity<ResponseStructure<List<Transaction>>> findStatementByAccountNumber(Long accountNumber) 
	{
		List<Transaction> creditTransaction = transactionRepository.findByCreditToAccount(accountNumber);
		List<Transaction> debitTransaction  = transactionRepository.findByDebitFromAccount(accountNumber);
		
		List<Transaction> allTransaction = new ArrayList<Transaction>();
		
		ResponseStructure<List<Transaction>> structure = new ResponseStructure<List<Transaction>>();
		
		allTransaction.addAll(debitTransaction);
		allTransaction.addAll(creditTransaction);
		
		//Collections.sort(allTransaction, new TransactionComparator());
		Collections.sort(allTransaction, Collections.reverseOrder(new TransactionComparator()));
		
		List<Transaction> miniStatement = new ArrayList<Transaction>();
		
		for(int i=0; i<5; i++)
		{
			miniStatement.add(allTransaction.get(i));
		}
		
		
		structure.setMessage("Mini statement for the account number "+accountNumber);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(miniStatement);
		
		return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure, HttpStatus.CREATED);
		
	}

	
	
	@Override
	public ResponseEntity<ResponseStructure<Account>> fundsTransfer(Transaction transaction) 
	{
		
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		Account debitAccount = accountDao.findById(transaction.getDebitFromAccount()).get();
		Account creditAccount = accountDao.findById(transaction.getCreditToAccount()).get();
		String transferMessage = "Insufficient funds to transfer";
		
		if(transaction.getAmount() < debitAccount.getBalance())
		{
			debitAccount.setBalance(debitAccount.getBalance() - transaction.getAmount());
			creditAccount.setBalance(creditAccount.getBalance() + transaction.getAmount());
			accountDao.save(debitAccount);
			accountDao.save(creditAccount);
			
			Date currentDate = new Date(System.currentTimeMillis());
			transaction.setDate(currentDate);	
			transactionDao.save(transaction);
			
			transferMessage = "Funds transferred ...";
		}
		
		Account account = accountService.getAccountInfo(debitAccount.getAccountNo());
		
		structure.setMessage(transferMessage);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(account);
			
		return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED); 
	}

}
