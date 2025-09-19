// 1.1.1 Creation of the client class

package components;

public class Client {

	private String name;
	private String firstname;
	private int clientnumber;

	// Compteur statique pour générer automatiquement les numéros.
	private static int counter = 0;

	public Client(String name, String firstname) {
		this.name = name;
		this.firstname = firstname;
		this.clientnumber = ++counter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getClientnumber() {
		return clientnumber;
	}

	@Override
	public String toString() {
		return "Client numéro " + clientnumber + " : " + firstname + " " + name;
	}

}
