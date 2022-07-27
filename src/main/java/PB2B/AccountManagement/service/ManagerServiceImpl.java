package PB2B.AccountManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import PB2B.AccountManagement.dao.AccountDao;
import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.User;
import PB2B.AccountManagement.util.ResponseStructure;


@Service
public class ManagerServiceImpl implements ManagerService
{
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private EmailService emailService;
	

	@Override
	public ResponseEntity<ResponseStructure<Account>> createCustomer(Customer customer) 
	{
		Account account = null;
		User user = null;
		
		ResponseStructure<Account> structure = new ResponseStructure<Account>();
		String createAccountMessage = "Error occurred while creating a new account ! ";
		
		 try 
		 {
			 Customer newCustomer = customerService.findCustomer(customer.getPanCard());
			 
			if(newCustomer != null)																	// check if customer exist, create only account
			{
				account = accountService.createAccount(newCustomer);
				createAccountMessage = "Account created for customer: "+ newCustomer.getCustomerId();
				
			}
			else																					// create new customer, account and user
			{
				customer = customerService.createCustomer(customer);
				account =  accountService.createAccount(customer);
				user = userService.createUser(customer);
				createAccountMessage = "Account created ...\n {Customer Id: "+customer.getCustomerId()+"}\n {Temporary Password: "+user.getPassword()+"}";
				
				// email service goes here
				
				String subject = "Welcome to Barclays Bank";
				String mailBody = "Dear "+customer.getName()+",\n\n"+"Your account has been created "
						+ "and account number is "+account.getAccountNo()+"\n\n"
						+ "***** Your internet banking credentials *****\n\n"
						+ "Customer ID: "+user.getCustomerId()+"\n"
						+ "One Time Password: "+user.getPassword()+"\n\n"
						+ "Regards,\nBarclays Bank";
				
				emailService.sendEmail(customer.getEmail(), subject, mailBody);
				
			}	 
		}
		 catch (Exception e) 
		{

			 e.printStackTrace();
		}
		 
			structure.setMessage(createAccountMessage);
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(account);
			return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);     
	}

	
	
	
	
	@Override
	public ResponseEntity<ResponseStructure<Customer>> findCustomerByPanCard(String panNumber)
    {
		
		Customer customer = null;
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		String searchResult = "Customer not found for pan card number : "+panNumber;
		
		customer = customerService.findCustomer(panNumber);
		
		if(customer != null)
		{
			searchResult = customer.getPanCard()+" Customer exist";
		}
			
		structure.setMessage(searchResult);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(customer);
		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);    
	} 

}
