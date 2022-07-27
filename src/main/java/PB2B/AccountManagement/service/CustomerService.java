package PB2B.AccountManagement.service;

import PB2B.AccountManagement.entities.Customer;

public interface CustomerService 
{
	public Customer createCustomer(Customer customer);
	public Customer findCustomer(String panNumber);
}
