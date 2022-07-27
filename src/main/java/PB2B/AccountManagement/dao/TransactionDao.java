package PB2B.AccountManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import PB2B.AccountManagement.entities.Transaction;


public interface TransactionDao extends JpaRepository<Transaction, Long> 
{
	
}
