// 1.2.1 Creation of the account class

package components;

public abstract class Account {

	protected String label;
	protected double balance;
	protected int accountNumber;
	protected Client client;

	// Compteur statique pour générer automatiquement les numéros de compte.
	private static int counter = 0;

	public Account(String label, Client client) {
		this.label = label;
		this.client = client;
		this.balance = 0.0;
		this.accountNumber = ++counter;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Compte numéro " + accountNumber + " (" + label + ") " + " - Client: " + client.toString()
				+ " - Balance: " + balance + " €";
	}

}
