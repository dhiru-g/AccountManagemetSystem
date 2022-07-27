package PB2B.AccountManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PB2B.AccountManagement.entities.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> 
{
		public List<Account> findByCustomerID(Long customerId);
}
