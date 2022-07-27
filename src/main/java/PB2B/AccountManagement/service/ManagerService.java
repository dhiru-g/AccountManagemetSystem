package PB2B.AccountManagement.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.util.ResponseStructure;



public interface ManagerService 
{
	public ResponseEntity<ResponseStructure<Account>> createCustomer(@RequestBody Customer customer);
	public ResponseEntity<ResponseStructure<Customer>> findCustomerByPanCard(@PathVariable String panNumber);
	
}
