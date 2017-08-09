package se.groupfisk.bankomat.model.account;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import se.groupfisk.storage.AccountStorage;

public final class Account implements AccountStorage {

	public int balance;
	private int accountNumber;
	private final Set<Account> accounts;
	private final String lastName;
	private final String firstName;
	private static int NUMBER_OF_ACCOUNTS = 10000000;

	public Account(int balance, String firstName, String lastName) {

		accountNumber = NUMBER_OF_ACCOUNTS++;
		this.balance = balance;
		this.lastName = lastName;
		this.firstName = firstName;
		this.accounts = new HashSet<>();

	}

	@Override
	public void addAccount(Account account) {
		if (account != null) {
			accounts.add(account);
		}
	}

	public String getDate() {
		Date date = new Date();
		return date.toString();

	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getBalance() {
		return balance;
	}

	public Set<Account> getAllAccounts() {
		return new HashSet<Account>(accounts);
	}

	@Override
	public String toString() {
		return "Account: \n" + "First name: " + firstName + '\n' + "Last name: " + lastName + '\n' + "accountNumber: "
				+ accountNumber + '\n' + "Balance: " + balance + '\n';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
