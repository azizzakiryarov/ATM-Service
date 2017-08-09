package se.groupfisk.bankomat.model.bank;

import java.util.HashSet;
import java.util.Set;
import se.groupfisk.model.card.Card;
import se.groupfisk.storage.BankStorage;

public final class Bank implements BankStorage {

	private final Card card;
	private final String bankName;
	private final Set<Bank> banks;

	public Bank(String bankName, Card card) {
		this.card = card;
		this.bankName = bankName;
		this.banks = new HashSet<Bank>();

		if (banks.contains(getBanks().isEmpty())) {
			throw new IllegalArgumentException("No Banks");
		}

	}

	public Card getCard() {
		return card;
	}

	public void addBank(Bank bank) {
		banks.add(bank);
	}

	public Set<Bank> getBanks() {
		return new HashSet<>(banks);
	}

	public String getBankName() {
		return bankName;
	}

	@Override
	public String toString() {
		return "Bank [bankName=" + bankName + ", banks=" + banks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
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
		Bank other = (Bank) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		return true;
	}

}
