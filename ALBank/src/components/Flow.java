// 1.3.2 Creation of the Flow class

package components;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, // Jackson choisit la sous-classe grâce à "type"
		include = JsonTypeInfo.As.PROPERTY, // "type" doit être une propriété du JSON
		property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Credit.class, name = "Credit"),
		@JsonSubTypes.Type(value = Debit.class, name = "Debit"),
		@JsonSubTypes.Type(value = Transfert.class, name = "Transfer") })

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
