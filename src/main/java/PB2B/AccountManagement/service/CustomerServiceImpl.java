package PB2B.AccountManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PB2B.AccountManagement.dao.AccountDao;
import PB2B.AccountManagement.dao.CustomerDao;
import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService 
{
	@Autowired
	CustomerDao customerDao;
	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public Customer createCustomer(Customer customer) 
	{
		customerDao.save(customer);
		return customerRepository.findByPanCard(customer.getPanCard());
	}


	@Override
	public Customer findCustomer(String panNumber) 
	{
		
		return customerRepository.findByPanCard(panNumber); 
	}

}
