// 1.1.1 Creation of the client class

package components;

public class Client {

	private String name;
	private String firstname;
	private int clientNumber;

	// Compteur statique pour générer automatiquement les numéros.
	private static int counter = 0;
	// Compteur statique pour générer automatiquement les numéros (pour le fichier
	// XML).
	private static int counterXml = 0;

	public Client(String name, String firstname) {
		this.name = name;
		this.firstname = firstname;
		this.clientNumber = ++counter;
	}

	// Constructeur vide pour Jackson (on incrémente juste avec un compteur à part
	// pour avoir un numéro de client convenable)
	public Client() {
		this.clientNumber = ++counterXml;
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
		return clientNumber;
	}

	@Override
	public String toString() {
		return "Client numéro " + clientNumber + " : " + firstname + " " + name;
	}

}
