package se.groupfisk.model.card;

import java.util.HashMap;
import java.util.Map;
import se.groupfisk.storage.CardStorage;

public final class InMemoryCard implements CardStorage {

	private Map<String, String> saveMyCard;

	public InMemoryCard(Map<String, String> saveMyCard) {
		this.saveMyCard = new HashMap<>();
	}

	@Override
	public Map<String, String> getMyCard() {
		return saveMyCard;
	}

	@Override
	public void addCard(Card card) {
		if (card != null) {
			saveMyCard.put(card.getBankId(), card.getPinCode());

		}
	}
}
