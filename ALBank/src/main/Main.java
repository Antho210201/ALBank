// 1.1.2 Creation of main class for tests

package main;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfert;

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

	// 1.2.3 Creation of the tablea account
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

	// 1.3.1 Adaptation of the table of accounts
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

	// 1.3.4 Creation of the flow array
	private static List<Flow> generateFlows(List<Account> accounts) {
		List<Flow> flows = new ArrayList<>();
		if (accounts.size() < 2) {
			System.out.println("Pas assez de comptes !!!");
			return flows;
		}
		flows.add(new Debit("Achat", 50, 1, true));
		accounts.stream().filter(acc -> acc instanceof CurrentAccount)
				.forEach(acc -> flows.add(new Credit("Prime", 100.50, acc.getAccountNumber(), true)));
		accounts.stream().filter(acc -> acc instanceof SavingsAccount)
				.forEach(acc -> flows.add(new Credit("Salaire", 1500, acc.getAccountNumber(), true)));
		flows.add(new Transfert("Virement", 50, 2, true, 1));
		// flows.add(new Debit("Achat", 50, 1, true)); (Pour tester le cas où un compte
		// se retouve en négatif)
		flows.forEach(flow -> flow.setDate(LocalDate.now().plusDays(2)));
		return flows;
	}

	// 1.3.5 Updating accounts
	private static void updateAccounts(List<Flow> flows, Hashtable<Integer, Account> accountsTable) {
		flows.forEach(flow -> {
			Account acc = accountsTable.get(flow.getTargetAccountNumber());
			if (acc != null) {
				acc.setBalance(flow);
			}
			if (flow instanceof Transfert) {
				Transfert t = (Transfert) flow;
				Account issuingAcc = accountsTable.get(t.getIssuingAccountNumber());
				if (issuingAcc != null) {
					issuingAcc.setBalance(t);
				}
			}
		});
		Predicate<Account> negativeBalance = acc -> acc.getBalance() < 0;
		Optional<Account> opt = accountsTable.values().stream().filter(negativeBalance).findAny();
		opt.ifPresent(acc -> System.out
				.println("ATTENTION: Le compte numéro " + acc.getAccountNumber() + " est négatif !!!"));
		displayAccountsSorted(accountsTable);
	}

	// 2.1 JSON file of flows
	public static List<Flow> loadFlowsFromJson(Path path) {
		try (InputStream is = Files.newInputStream(path)) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule()); // ✅ gestion des LocalDate

			Flow[] flows = mapper.readValue(is, Flow[].class);
			return Arrays.asList(flows);

		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
	}

	// 2.2 XML file of account
	public static List<Account> loadAccountsFromXml(Path path) {
		try (InputStream is = Files.newInputStream(path)) {
			XmlMapper xmlMapper = new XmlMapper();

			// lecture en tableau (polymorphisme activé via @JsonTypeInfo)
			Account[] accounts = xmlMapper.readValue(is, Account[].class);

			return Arrays.asList(accounts);

		} catch (Exception e) {
			e.printStackTrace();
			return List.of();
		}
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
		System.out.println("\n=== Comptes triés par Balance (balance vide) ===");
		displayAccountsSorted(accountsTable);

		List<Flow> flows = generateFlows(accounts);
		System.out.println("\n=== Comptes triés par Balance (balances modifiées par un ensemble de flux) ===");
		updateAccounts(flows, accountsTable);

		// List<Client> clientsFile = generateClients(3);
		Path filepathXml = Path.of("resources/accounts.xml");
		Path filepathJson = Path.of("resources/flows.json");

		List<Account> accountsFromXml = loadAccountsFromXml(filepathXml);
		System.out.println("\n=== Comptes chargés depuis le fichier accounts.xml ===");
		accountsFromXml.forEach(System.out::println);

		List<Flow> flowsFromJson = loadFlowsFromJson(filepathJson);
		System.out.println("\n=== Flux chargés depuis le fichier flows.json ===");
		flowsFromJson.forEach(f -> System.out.println(f.getComment() + " " + f.getAmount()));

		scanner.close();
	}

}
