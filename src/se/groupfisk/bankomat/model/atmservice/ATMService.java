package se.groupfisk.bankomat.model.atmservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import se.groupfisk.bankomat.model.account.Account;
import se.groupfisk.bankomat.model.bank.Bank;
import se.groupfisk.bankomat.transaction.Transaction;
import se.groupfisk.storage.Service;

public final class ATMService implements Service {

	public Account accounts;
	private Date date;
	private final Bank banks;
	private Writer writer = null;
	private Reader reader = null;
	private Transaction transactionMemory;

	public ATMService(Bank banks, Account accounts) {
		this.transactionMemory = new Transaction(new LinkedList<>());
		this.banks = banks;
		this.accounts = accounts;
	}

	public Date getDate() {
		return this.date;
	}

	public int checkBalance() {
		return accounts.getBalance();
	}

	public void getDateFromOneMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.get(Calendar.MONTH);
	}

	public Writer printReciept() throws IOException {

		getDateFromOneMonth();

		try (FileWriter fw = new FileWriter("receipt.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(accounts.toString());
			out.println(transactionMemory.toString());
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
		return writer;
	}

	public Reader printLastMonthReciept() throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader("receipt.txt"))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	public int withDraw(int amount) throws IOException {

		printReciept();

		if (amount <= 99 || amount >= 9999) {
			throw new IllegalArgumentException("You can withdraw just from 100$ up to 10000$");
		} else if (amount % 100 != 0) {
			throw new IllegalArgumentException("You can withdraw just even amount like 100$, 200$...");
		} else if (checkBalance() <= 0 || amount >= 9999 || checkBalance() <= -1)
			throw new IllegalArgumentException("Insufficient funds in your account");
		{
			transactionMemory.saveAllTransactions(amount);
		}
		return accounts.balance -= amount;

	}

	public Bank getBanks() {
		return banks;
	}
}
