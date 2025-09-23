// 1.3.3 Creation of the Debit class

package components;

public class Debit extends Flow {

	public Debit(String comment, double amount, int targetAccountNumber, boolean effect) {
		super(comment, amount, targetAccountNumber, effect);
	}

	// Constructeur vide pour Jackson
	public Debit() {
		super();
	}

}
