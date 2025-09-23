package components;

public class SavingsAccount extends Account {

	public SavingsAccount(Client client) {
		super("Compte épargne", client);
	}

	// Constructeur vide pour Jackson
	public SavingsAccount() {
		super();
	}

}
