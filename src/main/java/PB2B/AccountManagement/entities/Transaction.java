package PB2B.AccountManagement.entities;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Transaction 
{
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private Long transactionId;
	private Date transactionDate;
	private String type;
	private Long amount;
	private Long debitFromAccount;
	private Long creditToAccount;
	
	
	public Transaction() {
		super();
	}


	public Transaction(Long transactionId, Date currentDate, String type, Long amount, Long debitFromAccount,
			Long creditToAccount) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = currentDate;
		this.type = type;
		this.amount = amount;
		this.debitFromAccount = debitFromAccount;
		this.creditToAccount = creditToAccount;
	}

	
	
	public Transaction(Date currentDate, String type, Long amount, Long debitFromAccount, Long creditToAccount) {
		super();
		this.transactionDate = currentDate;
		this.type = type;
		this.amount = amount;
		this.debitFromAccount = debitFromAccount;
		this.creditToAccount = creditToAccount;
	}


	public Transaction(String type, Long amount, Long debitFromAccount,	Long creditToAccount) 
	{
		super();
		
		this.type = type;
		this.amount = amount;
		this.debitFromAccount = debitFromAccount;
		this.creditToAccount = creditToAccount;
	}

	
	
	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public Date getDate() {
		return transactionDate;
	}


	public void setDate(Date currentDate) {
		this.transactionDate = currentDate;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Long getAmount() {
		return amount;
	}


	public void setAmount(Long amount) {
		this.amount = amount;
	}


	public Long getDebitFromAccount() {
		return debitFromAccount;
	}


	public void setDebitFromAccount(Long debitFromAccount) {
		this.debitFromAccount = debitFromAccount;
	}


	public Long getCreditToAccount() {
		return creditToAccount;
	}


	public void setCreditToAccount(Long creditToAccount) {
		this.creditToAccount = creditToAccount;
	}
	
	
	
}
