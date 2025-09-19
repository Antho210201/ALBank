// 1.1.2 Creation of main class for tests
// 1.2.3 Creation of the tablea account
// 1.3.1 Adaptation of the table of accounts

package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

public class Main {

	private static List<Client> generateClients(int n) {
		List<Client> clients = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			clients.add(new Client("Name" + i, "Firstname" + i));
		}
		return clients;
	}

	private static void displayClients(List<Client> clients) {
		String result = clients.stream().map(Client::toString).collect(Collectors.joining("\n"));
		System.out.println(result);

	}

	private static List<Account> generateAccounts(List<Client> clients) {
		List<Account> accounts = new ArrayList<>();
		for (Client c : clients) {
			accounts.add(new CurrentAccount(c));
			accounts.add(new SavingsAccount(c));
		}
		return accounts;
	}

	private static void displayAccounts(List<Account> accounts) {
		String result = accounts.stream().map(Account::toString).collect(Collectors.joining("\n"));
		System.out.println(result);

	}

	private static Hashtable<Integer, Account> createAccountsTable(List<Account> accounts) {
		Hashtable<Integer, Account> accountsTable = new Hashtable<>();
		for (Account acc : accounts) {
			accountsTable.put(acc.getAccountNumber(), acc);
		}
		return accountsTable;
	}

	private static void displayAccountsSorted(Hashtable<Integer, Account> accountsTable) {
		String result = accountsTable.values().stream().sorted(Comparator.comparingDouble(Account::getBalance))
				.map(Account::toString).collect(Collectors.joining("\n"));
		System.out.println(result);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Nombre de clients ? ");
		int numclients = scanner.nextInt();

		List<Client> clients = generateClients(numclients);
		System.out.println("=== Clients ===");
		displayClients(clients);

		List<Account> accounts = generateAccounts(clients);
		System.out.println("\n=== Comptes ===");
		displayAccounts(accounts);

		Hashtable<Integer, Account> accountsTable = createAccountsTable(accounts);
		System.out.println("\n=== Comptes triés par Balance");
		displayAccountsSorted(accountsTable);

		scanner.close();
	}

}
