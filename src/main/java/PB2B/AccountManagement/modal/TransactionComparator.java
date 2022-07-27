package PB2B.AccountManagement.modal;

import java.util.Comparator;

import PB2B.AccountManagement.entities.Transaction;

public class TransactionComparator implements Comparator<Transaction> 
{

	@Override
	public int compare(Transaction t1, Transaction t2) 
	{
		
		return t1.getDate().compareTo(t2.getDate());
	}

}
