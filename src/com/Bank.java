package com;

public interface Bank {

	public void startTransaction(User user, Bank bank);

	public void checkBalance(User user);

	public void cashDeposit(User user);

	public void cardlessCashDeposit(User user);

	public void cashWithdraw(User user);

	public void miniStatement(User user);

	public void exit(User user);

}