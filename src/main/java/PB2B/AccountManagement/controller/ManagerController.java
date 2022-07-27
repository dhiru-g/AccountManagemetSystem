package PB2B.AccountManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import PB2B.AccountManagement.entities.Account;
import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.Transaction;
import PB2B.AccountManagement.entities.User;
import PB2B.AccountManagement.service.AccountService;
import PB2B.AccountManagement.service.CustomerService;
import PB2B.AccountManagement.service.EmailService;
import PB2B.AccountManagement.service.ManagerService;
import PB2B.AccountManagement.service.TransactionService;
import PB2B.AccountManagement.service.UserService;
import PB2B.AccountManagement.util.ResponseStructure;


@RestController
public class ManagerController 
{
		@Autowired
		private ManagerService managerService;
		
		
		
		@PostMapping("/manager/createAccount")
		public ResponseEntity<ResponseStructure<Account>> createCustomer(@RequestBody Customer customer)
		{
			 return managerService.createCustomer(customer);
		}
		
		
		
		
		@GetMapping("/manager/findCustomer/{panNumber}")
		public ResponseEntity<ResponseStructure<Customer>> findCustomerByPanCard(@PathVariable String panNumber)
		{
			return managerService.findCustomerByPanCard(panNumber);
		}
			
}


