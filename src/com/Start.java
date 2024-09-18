package com;

public class Start {
	public static void main(String[] args) {

		User user = new User("Ranjith", 1234567890, "**7878", 4545, 5000.00);
		Bank bank = new BankOperations();
		bank.startTransaction(user, bank);
	}
}