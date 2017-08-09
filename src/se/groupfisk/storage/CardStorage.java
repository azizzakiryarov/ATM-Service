package se.groupfisk.storage;

import java.util.Map;

import se.groupfisk.model.card.Card;

public interface CardStorage {
	
	Map<String, String> getMyCard();
	
	void addCard(Card card);

}
