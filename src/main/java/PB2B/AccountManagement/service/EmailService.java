package PB2B.AccountManagement.service;

import PB2B.AccountManagement.entities.Customer;
import PB2B.AccountManagement.entities.User;

public interface EmailService 
{
	public void sendEmail(String sendTo, String subject, String mailBody);
}
