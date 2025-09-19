// 1.1.2 Creation of main class for tests

package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import components.Client;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nombre de clients ? ");
		int numclients = scanner.nextInt();

		List<Client> clients = generateClients(numclients);
		displayClients(clients);

		scanner.close();
	}

	private static void displayClients(List<Client> clients) {
		String result = clients.stream().map(Client::toString).collect(Collectors.joining("\n"));
		System.out.println(result);

	}

	private static List<Client> generateClients(int n) {
		List<Client> clients = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			clients.add(new Client("Name" + i, "Firstname" + i));
		}
		return clients;
	}

}
