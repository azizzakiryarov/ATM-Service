package se.groupfisk.storage;

import java.util.Set;

import se.groupfisk.bankomat.model.bank.Bank;

public interface BankStorage {

	void addBank(Bank bank);

	Set<Bank> getBanks();

}
