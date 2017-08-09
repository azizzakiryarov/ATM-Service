package se.groupfisk.storage;

import java.util.Set;

import se.groupfisk.bankomat.model.account.Account;

public interface AccountStorage {

	void addAccount(Account account);

	Set<Account> getAllAccounts();

}
