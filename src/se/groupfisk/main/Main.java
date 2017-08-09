package se.groupfisk.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import se.groupfisk.bankomat.cardexception.CardException;
import se.groupfisk.bankomat.model.account.Account;
import se.groupfisk.bankomat.model.atmservice.ATMService;
import se.groupfisk.bankomat.model.bank.Bank;
import se.groupfisk.bankomat.transaction.Transaction;
import se.groupfisk.model.card.Card;

public final class Main {

	int choice = 0;
	Scanner keyboard = new Scanner(System.in);
	boolean exit;

	public static void main(String[] args) throws IOException, CardException {
	}

	ATMService bankomat = new ATMService(new Bank("Nordea", new Card("300000", "1234")),
			new Account(10000, "Erik", "Eriksson"));
	
	Transaction transaction = new Transaction(new ArrayList<>());

	public void runMenu() throws IOException {
		printHeader();
		printHeadMenu();
		int choice = getInput();
		performAction(choice);
	}

	private void printHeader() {
		System.out.println("+--------------------------------+");
		System.out.println("|     Welcome to ATM 'Norvic'    |");
		System.out.println("|      create by group fish      |");
		System.out.println("+--------------------------------+");
	}

	private void printHeadMenu() {
		System.out.println("Please make a selection: ");
		System.out.println("Choose your Bank Account:\n" + "1) Nordea\n" + "2) SwedBank:");
	}

	private void printMenu() {
		System.out.println("1) Make a withdrawal");
		System.out.println("2) Check balance");
		System.out.println("3) Print last receipt");
		System.out.println("4) Get receipt for the entire month");
		System.out.println("0) Exit");
	}

	private void performAction(int choice) throws IOException {
		switch (choice) {
		case 0:
			System.out.println("Thank you for using our service");
			System.exit(0);
		case 1:
			System.out.println("Welcome to Nordea Bank AB!");
			pressYourPinCode();
			chooseFromMenu();
			break;
		case 2:
			System.out.println("Sorry, but you are not customer with SwedBank..." + "Thanks for choosing our service!");
			runMenu();

		}
	}

	public void chooseFromMenu() throws IOException {
		int choice = 0;
		choice = keyboard.nextInt();

		switch (choice) {
		case 0:
			System.out.println("Thank you for using our service." + "Don't forget your card! Bye!");
			System.exit(0);
		case 1:
			System.out.println("How much money do you want to draw: ");
			bankomat.withDraw(keyboard.nextInt());
			System.out.println(bankomat.accounts.toString());
			printMenu();
			chooseFromMenu();
			break;
		case 2:
			System.out.println("This is your balance now: ");
			System.out.println(bankomat.accounts.getBalance());
			printMenu();
			chooseFromMenu();
			break;
		case 3:
			bankomat.printReciept();
			System.out.println(bankomat.accounts.toString());
			printMenu();
			chooseFromMenu();
			break;
		case 4: 
			bankomat.printLastMonthReciept();
			System.out.println(bankomat.printLastMonthReciept());
			printMenu();
			chooseFromMenu();
			break;
		}
	}

	private void pressYourPinCode() {
		System.out.println("Please press your PinCode: ");
		if (keyboard.nextLine().equals(bankomat.getBanks().getCard().getPinCode())) {
			printMenu();
		} else {
			System.out.println("Please press valid pinCode...");
			pressYourPinCode();
		}
	}

	private int getInput() {
		int choice = -1;
		do {
			System.out.println("Enter your choice: ");
			try {
				choice = Integer.parseInt(keyboard.nextLine());
			} catch (NumberFormatException e) {
				System.err.println("Invalid selection. Numbers only please.");
			}
			if (choice < 0 || choice > 4) {
				System.out.println("Choice outside of range. Please choose agein.");
			}
		} while (choice < 0 || choice > 4);
		return choice;
	}
}
