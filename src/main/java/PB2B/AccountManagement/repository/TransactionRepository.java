package PB2B.AccountManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PB2B.AccountManagement.entities.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> 
{
	public List<Transaction> findByCreditToAccount(Long accountNumber);
	public List<Transaction> findByDebitFromAccount(Long accountNumber);
}
