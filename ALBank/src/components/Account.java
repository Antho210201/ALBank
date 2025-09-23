// 1.2.1 Creation of the account class

package components;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// JsonTypeInfo indique à Jackson où chercher l'info du type (ici dans la propriété "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")

// JsonSubTypes fait le mapping entre la valeur de "type" et les sous-classes concrètes
@JsonSubTypes({ @JsonSubTypes.Type(value = CurrentAccount.class, name = "Current"),
		@JsonSubTypes.Type(value = SavingsAccount.class, name = "Savings") })

// Permet d'éviter les erreurs si le XML a plus de champs que la classe Java
@JsonIgnoreProperties(ignoreUnknown = true)

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

	// Constructeur vide pour Jackson
	public Account() {
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

	// 1.3.5 Updating accounts
	public void setBalance(Flow flow) {
		if (flow instanceof Credit) {
			this.balance += flow.getAmount();
		} else if (flow instanceof Debit) {
			this.balance -= flow.getAmount();
		} else if (flow instanceof Transfert) {
			Transfert t = (Transfert) flow;
			if (this.accountNumber == t.getIssuingAccountNumber()) {
				this.balance -= t.getAmount();
			} else if (this.accountNumber == t.getTargetAccountNumber()) {
				this.balance += t.getAmount();
			}
		}

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
