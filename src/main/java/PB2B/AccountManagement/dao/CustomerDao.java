package PB2B.AccountManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import PB2B.AccountManagement.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long>
{

}
