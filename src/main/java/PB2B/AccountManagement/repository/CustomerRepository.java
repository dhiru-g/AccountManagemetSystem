package PB2B.AccountManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> 
{
	Customer findByPanCard(String panCard);
	
}
