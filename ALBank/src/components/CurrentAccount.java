// 1.2.2 Creation of the CurrentAcount

package components;

public class CurrentAccount extends Account {

	public CurrentAccount(Client client) {
		super("Compte courant", client);
	}

	// Constructeur vide pour Jackson
	public CurrentAccount() {
		super();
	}

}
