package PB2B.AccountManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import PB2B.AccountManagement.entities.User;

public interface UserDao extends JpaRepository<User, Long> 
{

}
