package PB2B.AccountManagement.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User 
{
	@Id
	private Long customerId;
	private	String password;
	private Boolean isFirstLogin;
	
	
	public User() 
	{
		super();
	}


	public User(Long customerId, String password, Boolean isFirstLogin) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.isFirstLogin = isFirstLogin;
	}

	
	public User(Long customerId, String password) 
	{
		super();
		this.customerId = customerId;
		this.password = password;	
	}

	
	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}


	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}	
}
