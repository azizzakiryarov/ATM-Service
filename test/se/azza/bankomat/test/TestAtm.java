package se.azza.bankomat.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import se.groupfisk.bankomat.model.account.Account;
import se.groupfisk.bankomat.model.atmservice.ATMService;
import se.groupfisk.bankomat.model.bank.Bank;
import se.groupfisk.model.card.Card;
import se.groupfisk.storage.AccountStorage;
import se.groupfisk.storage.BankStorage;
import se.groupfisk.storage.CardStorage;
import se.groupfisk.storage.Service;

@RunWith(MockitoJUnitRunner.class)
public class TestAtm {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	private Service service;

	@Mock
	private AccountStorage accountStorage;

	@Mock
	private BankStorage bankStorage;

	@Mock
	private CardStorage cardStorage;

	@InjectMocks
	private ATMService atmService;

	Card card = new Card("300000", "1234");
	Bank bank = new Bank("Nordea", new Card("300000", "1234"));
	ATMService bankomat = new ATMService(new Bank("Nordea", new Card("300000", "1234")),
			new Account(10000, "Erik", "Eriksson"));

	@Test
	public void ifICanDrawMoneyFromMyAccount() throws IOException {
		bankomat.withDraw(300);

		int res = (10000 - 300);
		if (res != 9700) {
			Assert.fail("Sorry... You can not draw money from your account...");
			
			verify(service).withDraw(300);
		}
	}

	@Test
	public void willThrowExceptionIfNotEnoughMoneyOnMyAccount() throws IOException {


		String expectedMassege = "You cannot get this amount money from your account";

		when(service.withDraw(11000)).thenThrow(new IllegalArgumentException(expectedMassege));
		service.withDraw(11000);

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(expectedMassege);

	}

	@Test
	public void willThrowExceptionIfNotCorrectAmount() throws IOException {
		

		String expectedMassege = "You can withdraw just from 100$ up to 10000$";

		when(bankomat.withDraw(13)).thenThrow(new IllegalArgumentException(expectedMassege));
		bankomat.withDraw(13);

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(expectedMassege);

	}

	@Test
	public void ifIHaveReceiptFromMyTransaction() throws IOException {
		assertNotNull(bankomat.printReciept());
	}

	@Test
	public void ifIHaveMoneyOnMyBalance() {
		assertNotNull(bankomat.checkBalance());
	}

	@Test
	public void ifMyPinCodeAndBankIdAreCorrect() {

		Bank bank = new Bank("Nordea", new Card("300000", "1234"));

		String bankId = "300000";
		String pinCode = "1234";

		assertThat(bank.getCard().getBankId(), is(notNullValue()));
		assertThat(bank.getCard().getPinCode(), is(notNullValue()));
		assertThat(bankId, is(bankomat.getBanks().getCard().getBankId()));
		assertThat(pinCode, is(bankomat.getBanks().getCard().getPinCode()));

	}

	@Test
	public void ifMyAccountInfoAreCorrect() {

		String firstName = "Erik";
		String lastName = "Eriksson";
		int balance = 10000;

		assertThat(bankomat.accounts.getBalance(), is(notNullValue()));
		assertThat(bankomat.accounts.getFirstName(), is(notNullValue()));
		assertThat(bankomat.accounts.getLastName(), is(notNullValue()));
		assertThat(firstName, is(bankomat.accounts.getFirstName()));
		assertThat(lastName, is(bankomat.accounts.getLastName()));
		assertThat(balance, is(bankomat.accounts.getBalance()));
		

	}
}
