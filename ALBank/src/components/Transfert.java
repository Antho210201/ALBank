// 1.3.3 Creation of the Transfert class

package components;

public class Transfert extends Flow {

	private int issuingAccountNumber;

	public Transfert(String comment, double amount, int targetAccountNumber, boolean effect, int issuingAccountNumber) {
		super(comment, amount, targetAccountNumber, effect);
		this.issuingAccountNumber = issuingAccountNumber;
	}

	public Transfert() {
		super();
	}

	public int getIssuingAccountNumber() {
		return issuingAccountNumber;
	}

	public void setIssuingAccountNumber(int issuingAccountNumber) {
		this.issuingAccountNumber = issuingAccountNumber;
	}

}
