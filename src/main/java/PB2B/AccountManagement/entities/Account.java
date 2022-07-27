package PB2B.AccountManagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Account 
{
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	
	private Long accountNo;
	private Long customerID;
	private Long balance;
	
	public Account() {
		super();
	}

	public Account(/*Long accountNo,*/ Long customerID, Long balance) {
		super();
		//this.accountNo = accountNo;
		this.customerID = customerID;
		this.balance = balance;
	}

	public Account(Long accountNo, Long customerID, Long balance) {
		super();
		this.accountNo = accountNo;
		this.customerID = customerID;
		this.balance = balance;
	}
	
	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	
	
}
