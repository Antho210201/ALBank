// 1.3.2 Creation of the Flow class

package components;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//JsonTypeInfo indique à Jackson où chercher l'info du type (ici dans la propriété "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")

//JsonSubTypes fait le mapping entre la valeur de "type" et les sous-classes concrètes
@JsonSubTypes({ @JsonSubTypes.Type(value = Credit.class, name = "Credit"),
		@JsonSubTypes.Type(value = Debit.class, name = "Debit"),
		@JsonSubTypes.Type(value = Transfert.class, name = "Transfer") })

//Permet d'éviter les erreurs si le XML a plus de champs que la classe Java
@JsonIgnoreProperties(ignoreUnknown = true)

public abstract class Flow {

	private String comment;
	private int identifier;
	private double amount;
	private int targetAccountNumber;
	private boolean effect;
	private LocalDate date;

	// Compteur statique pour générer des identifiants
	private static int counter = 0;

	public Flow(String comment, double amount, int targetAccountNumber, boolean effect) {
		this.comment = comment;
		this.identifier = ++counter;
		this.amount = amount;
		this.targetAccountNumber = targetAccountNumber;
		this.effect = effect;
		this.date = LocalDate.now();
	}

	// Constructeur vide pour Jackson
	public Flow() {

	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIdentifier() {
		return identifier;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}

	public boolean isEffect() {
		return effect;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
