package PB2B.AccountManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PB2B.AccountManagement.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
	public User findByCustomerId(Long customerId);
}
