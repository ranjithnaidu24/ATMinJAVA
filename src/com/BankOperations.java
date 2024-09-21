package com;

import java.util.Scanner;

public class BankOperations implements Bank {
	static Scanner s = new Scanner(System.in);

	public void startTransaction(User user, Bank bank) {
		try{
		String optionInput;
		do {
			System.out.println("Main menu");
			System.out.println("1. Balance Enquiry");
			System.out.println("2. Deposit");
			System.out.println("3. Cardless Deposit");
			System.out.println("4. Withdraw");
			System.out.println("5. Mini statement");
			System.out.println("6. Exit");
			System.out.println("Select an option");
			
			optionInput = s.next();
			//int option =Integer.parseInt(optionInput);
			switch (optionInput) {
				case "1":
					bank.checkBalance(user);
					break;
				case "2":
					bank.cashDeposit(user);
					break;
				case "3":
					bank.cardlessCashDeposit(user);
					break;
				case "4":
					bank.cashWithdraw(user);
					break;
				case "5":
					bank.miniStatement(user);
					break;
				case "6":
					bank.exit(user);
					break;
				default:
					System.out.println("Invalid option!!!");
					break;
			}
		}
		while (optionInput != "6");
	}
		catch(Exception e){
			System.out.println("Invalid option");
			bank.startTransaction(user, bank);
		}

	}

	public void checkBalance(User user) {
		int pin = user.getPin();
		double balance = user.getBalance();
		System.out.println("Enter the 4 digit security pin");
		String pinCheckInput = s.next();
		try {
			int pinCheck = Integer.parseInt(pinCheckInput);
			if (pinCheck == pin) {
				System.out.println("Available balance is " + balance + " INR");
				System.out.println("Thank you " + user.getUserName());
			} else {
				System.out.println("Incorrect pin entered");
				exit(user);
				// checkBalance(user);
			}
		} catch (Exception e) {
			System.out.println("Invalid pin entered");
			exit(user);
			// checkBalance(user);
		}
	}

	public void cashDeposit(User user) {
		int pin = user.getPin();
		double balance = user.getBalance();
		System.out.println("Enter the 4 digit security pin");
		String pinCheckInput = s.next();
		try {
			int pinCheck = Integer.parseInt(pinCheckInput);
			if (pinCheck == pin) {
				System.out.println("Enter amount to deposit");
				String addAmountInput = s.next();
				try {
					double addAmount = Double.parseDouble(addAmountInput);
					if (addAmount > 0) {
						balance = balance + addAmount;
						user.setBalance(balance);
						user.addTransaction(
								"Deposited : " + addAmount + " INR. Available balance : " + balance + " INR");
						System.out.println("Amount deposit of " + addAmount + " INR is successful");
						// System.out.println("Total available balance is " + balance);
						System.out.println("Thank you " + user.getUserName());
					} else {
						System.out.println("Invalid amount entered");
						exit(user);
						// deposit(user);
					}
				} catch (Exception e) {
					System.out.println("Invalid amount entered");
					exit(user);
					// deposit(user);
				}
			} else {
				System.out.println("Incorrect pin entered");
				exit(user);
				// deposit(user);
			}
		} catch (Exception e) {
			System.out.println("Invalid pin entered");
			exit(user);
			// deposit(user);
		}
	}

	public void cardlessCashDeposit(User user) {
		int accNo = user.getAccNo();
		String userName = user.getUserName();
		System.out.println("Enter the 10 digit account number");
		String accNoCheckInput = s.next();
		try {
			int accNoCheck = Integer.parseInt(accNoCheckInput);
			if (accNoCheck == accNo) {
				System.out.println("Account Holder name " + userName);
				System.out.println("Account no. " + accNo);
				System.out.println("Select yes to continue or no to cancel the transaction");
				int yesOrNo;
				boolean confirm = true;
				do {
					System.out.println("1. Yes");
					System.out.println("2. No");
					yesOrNo = s.nextInt();
					switch (yesOrNo) {
						case 1:
							continueDeposit(user);
							confirm = false;
							break;
						case 2:
							System.out.println("Transaction cancelled. Thank you");
							confirm = false;
							break;
						default:

					}
				} while (confirm == true);
			} else {
				System.out.println("User not found");
				exit(user);
			}
		} catch (Exception e) {
			System.out.println("Invalid input.");
			exit(user);
		}
	}

	public void continueDeposit(User user) {
		double balance = user.getBalance();
		System.out.println("Enter amount to deposit");
		String addCardlessAmountInput = s.next();
		try {
			double addCardlessAmount = Double.parseDouble(addCardlessAmountInput);
			if (addCardlessAmount > 0) {
				balance = balance + addCardlessAmount;
				user.setBalance(balance);
				user.addTransaction(
						"Cardless Deposit : " + addCardlessAmount + " INR. Available balance : " + balance + " INR");
				System.out.println("Amount deposit of " + addCardlessAmount + " INR successful");
				// System.out.println("Total available balance is " + balance);
				System.out.println("Thank you " + user.getUserName());
			} else {
				System.out.println("Invalid amount entered");
				exit(user);
			}
		} catch (Exception e) {
			System.out.println("Invalid amount format");
			exit(user);
		}
	}

	public void cashWithdraw(User user) {
		int pin = user.getPin();
		double balance = user.getBalance();
		System.out.println("Enter the amount to withdraw");
		String amountWithdrawInput = s.next();
		try {
			double amountWithdraw = Double.parseDouble(amountWithdrawInput);
			if (amountWithdraw > 0) {
				System.out.println("Enter 4 digit security pin");
				String pinCheckInput = s.next();
				int pinCheck = Integer.parseInt(pinCheckInput);
				if (pinCheck == pin) {
					if (amountWithdraw < balance) {
						balance = balance - amountWithdraw;
						user.setBalance(balance);
						System.out.println("Amount withdraw of " + amountWithdraw + " INR is successful");
						System.out.println("Thank you " + user.getUserName());
					} else {
						System.out.println("No Sufficient balance");
					}
				} else {
					System.out.println("Incorrect pin entered");
				}
			} else {
				System.out.println("Invalid amount entered");
			}
		} catch (Exception e) {
			System.out.println("Invalid data entered");
			exit(user);
		}
	}

	public void miniStatement(User user) {
		int pin = user.getPin();
		System.out.println("Enter the 4 digit security pin");
		String pinCheckInput = s.next();
		try {
			int pinCheck = Integer.parseInt(pinCheckInput);
			if (pinCheck == pin) {
				if (user.getTransactionHistory().isEmpty()) {
					System.out.println("No transactions available");
					exit(user);
				} else {
					user.getTransactionHistory().forEach(System.out::println);
				}
			} else {
				System.out.println("Incorrect pin entered");
				exit(user);
			}
		} catch (Exception e) {
			System.out.println("Invalid pin entered");
			exit(user);
		}
	}

	public void exit(User user) {
		System.out.println("Thank you!!!");
	}
}