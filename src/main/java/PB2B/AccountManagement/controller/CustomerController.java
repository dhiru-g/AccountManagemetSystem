package PB2B.AccountManagement.controller;

import java.sql.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Transaction;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.User;
import PB2B.AccountManagement.service.AccountService;
import PB2B.AccountManagement.service.CustomerService;
import PB2B.AccountManagement.service.EmailService;
import PB2B.AccountManagement.service.TransactionService;
import PB2B.AccountManagement.service.UserService;
import PB2B.AccountManagement.util.ResponseStructure;



@RestController
public class CustomerController 
{
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	EmailService emailService;
	
	
	/*  This part has been commented as this is part of managercontroller
	 * 
	 * 
	 * @PostMapping("/CreateAccount") public String createCustomer(@RequestBody
	 * Customer customer) { User user = null; String createAccountMessage =
	 * "Account created for customer: ";
	 * 
	 * try { Customer newCustomer =
	 * customerService.findCustomer(customer.getPanCard());
	 * 
	 * if(newCustomer != null) // check if customer exist, create only account {
	 * accountService.createAccount(newCustomer);
	 * 
	 * // TODO: Print Customer Id createAccountMessage+=
	 * newCustomer.getCustomerId(); } else // create new customer, account and user
	 * { customer = customerService.createCustomer(customer);
	 * accountService.createAccount(customer); user =
	 * userService.createUser(customer); createAccountMessage =
	 * "Account created ...\n Customer Id: "+customer.getCustomerId()
	 * +"\nTemporary Password: "+user.getPassword(); } } catch (Exception e) {
	 * 
	 * return "Failed to create an account"; }
	 * 
	 * return createAccountMessage; }
	 * 
	 * 
	 * 
	 * @GetMapping("/FindCustomer/{panNumber}")
		public String findCustomerByPanCard(@PathVariable String panNumber)
		{
			String searchResult = "Customer not found for pan card number : "+panNumber;
			Customer customer = null;
		
			customer = customerService.findCustomer(panNumber);
		
			if(customer != null)
			{
				searchResult = customer.getPanCard()+" Customer exist";
			}
			
			return searchResult;
		}
	
	 * 
	 */
	
	
		
	@PostMapping("/customer/userLogin")
	public ResponseEntity<ResponseStructure<Customer>> userLogin(@RequestBody User user)
	{	 
		return userService.login(user);
	}
	

	
	@PutMapping("/customer/deposit/{accountNo}")
	public ResponseEntity<ResponseStructure<Account>> depositAmount(@PathVariable("accountNo") Long accountNo, @RequestBody Account account)
	{
		return transactionService.creditTransaction("Credit", account.getAccountNo(), account.getBalance(), account); 
	}
	
	
	
	@PutMapping("/customer/withdraw/{accountNo}")
	public ResponseEntity<ResponseStructure<Account>> withdrawAmount(@PathVariable("accountNo") Long accountNo, @RequestBody Account account)
	{
		return  transactionService.debitTransaction("Debit", account.getAccountNo(), account.getBalance(), account);
	}
	
	
	@GetMapping("/customer/miniStatement/{accountNumber}")
	public ResponseEntity<ResponseStructure<List<Transaction>>> getFiveTransaction(@PathVariable Long accountNumber) 
	{
		return transactionService.findStatementByAccountNumber(accountNumber);
	}
	
	
	
	@GetMapping("/customer/viewAccounts/{customerId}")
	public ResponseEntity<ResponseStructure<List<Account>>> getAllAccounts(@PathVariable Long customerId)
	{
		return accountService.getAllAccounts(customerId);
	}
	
	
	
	@PutMapping("/customer/transfer")
	public ResponseEntity<ResponseStructure<Account>> depositAmount(@RequestBody Transaction transaction)
	{
		
		return transactionService.fundsTransfer(transaction);
	}
	
	
	/*
	 * @GetMapping("/sendMail") public void sendMail(Customer customer, User user) {
	 * try { emailService.sendEmail(customer, user); } catch(Exception e) {
	 * e.printStackTrace(); } }
	 */ 
	
}
