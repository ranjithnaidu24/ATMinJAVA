package com;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	private int accNo;
	private String cardNo;
	private int pin;
	private double balance;
	private List<String> transactionHistory;

	public User() {
	}

	public User(String userName, int accNo, String cardNo, int pin, double balance) {
		this.userName = userName;
		this.accNo = accNo;
		this.cardNo = cardNo;
		this.pin = pin;
		this.balance = balance;
		this.transactionHistory = new ArrayList<>();
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getPin() {
		return pin;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void addTransaction(String transactionDetail) {
		transactionHistory.add(transactionDetail);
	}

	public List<String> getTransactionHistory() {
		return transactionHistory;
	}
}