package se.groupfisk.storage;

public interface TransactionStorage {

	void saveAllTransactions(int amount);

	void getALastOfMonthTransaction();

}
