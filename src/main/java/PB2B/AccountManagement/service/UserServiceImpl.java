package PB2B.AccountManagement.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import PB2B.AccountManagement.dao.AccountDao;
import PB2B.AccountManagement.dao.CustomerDao;
import PB2B.AccountManagement.dao.UserDao;
import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.User;
import PB2B.AccountManagement.repository.CustomerRepository;
import PB2B.AccountManagement.repository.UserRepository;
import PB2B.AccountManagement.util.ResponseStructure;


@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	CustomerDao customerDao;
		
	@Override
	public User createUser(Customer customer) 
	{	
		String alphnumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		char buffer[] = new char[7]; 
		Random random = new Random();
		
		for(int i=0; i < 7;i++)
		{
			buffer[i] = alphnumeric.charAt(random.nextInt(alphnumeric.length()));
		}
		
		String password = new String(buffer);	
		Boolean isFirstLogin = true;
		
		User user = new User(customer.getCustomerId(),password, isFirstLogin);
		return userDao.save(user);
		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<Customer>> login(User loginUser) 
	{
		String loginMessage = "User doesn't exist, contact Bank Manager";	
		User user = userRepository.findByCustomerId(loginUser.getCustomerId());
		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		Customer customer = null;
		
		if(user != null && user.getPassword().equals(loginUser.getPassword()))
		{
			 customer =  customerDao.findById(user.getCustomerId()).get();
			 loginMessage = user.getCustomerId()+" User logged in"; 
		}
		
		
		structure.setMessage(loginMessage);
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(customer);
		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED); 
		
		
	}

}
