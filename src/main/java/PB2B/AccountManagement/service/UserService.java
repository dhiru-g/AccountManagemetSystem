package PB2B.AccountManagement.service;

import org.springframework.http.ResponseEntity;

import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.User;
import PB2B.AccountManagement.util.ResponseStructure;

public interface UserService
{
	public User createUser(Customer customer);
	public ResponseEntity<ResponseStructure<Customer>> login(User user);
}
