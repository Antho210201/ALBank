// 1.3.3 Creation of the Credit class 

package components;

public class Credit extends Flow {

	public Credit(String comment, double amount, int targetAccountNumber, boolean effect) {
		super(comment, amount, targetAccountNumber, effect);
	}

	// Constructeur vide pour Jackson
	public Credit() {
		super();
	}

}
