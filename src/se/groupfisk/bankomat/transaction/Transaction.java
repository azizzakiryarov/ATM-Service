package se.groupfisk.bankomat.transaction;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import se.groupfisk.storage.TransactionStorage;

public final class Transaction implements TransactionStorage {

	private final List<Integer> transactionsStrorage;

	public Transaction(List<Integer> transactionsStrorage) {
		this.transactionsStrorage = new LinkedList<>();
	}

	@Override
	public void saveAllTransactions(int amount) {
		transactionsStrorage.add(amount);
	}

	@Override
	public void getALastOfMonthTransaction() {
		for (Integer integer : transactionsStrorage) {
			System.out.println(integer);
		}
	}

	@Override
	public String toString() {
		return "You have withdrown: " + transactionsStrorage + " " + getDate();
	}

	public String getDate() {
		Date date = new Date();
		return date.toString();

	}

	public List<Integer> getTransactionsStrorage() {
		return transactionsStrorage;
	}

}
