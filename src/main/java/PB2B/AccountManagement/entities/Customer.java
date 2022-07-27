package PB2B.AccountManagement.entities;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer 
{
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO ) 
	
	private Long customerId;
	private String panCard;
	private String aadharCard;
	private String name;
	private String address;
	private Date dob;
	private String email;
	
	
	


	public Customer() {
		super();
	}


	public Customer(Long customerId, String panCard, String aadharCard, String name, String address, Date dob, String email) {
		super();
		this.customerId = customerId;
		this.panCard = panCard;
		this.aadharCard = aadharCard;
		this.name = name;
		this.address = address;
		this.dob = dob;
		this.email = email;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getPanCard() {
		return panCard;
	}


	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}


	public String getAadharCard() {
		return aadharCard;
	}


	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
