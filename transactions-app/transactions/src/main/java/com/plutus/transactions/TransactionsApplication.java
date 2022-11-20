package com.plutus.transactions;

import com.plutus.transactions.entities.Account;
import com.plutus.transactions.entities.Client;
import com.plutus.transactions.repositories.AccountRepository;
import com.plutus.transactions.repositories.AccountTransactionRepository;
import com.plutus.transactions.repositories.ClientRepository;
import com.plutus.transactions.repositories.ExchangeTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SpringBootApplication
public class TransactionsApplication {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private ExchangeTransactionRepository exchangeTransactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(TransactionsApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
		this.clearDb();
		List<Client> clients = this.populateClients();
		this.populateAccounts(clients);
    }

    /**
     * Clears the DB from any existing data.
     */
    private void clearDb() {
        this.accountTransactionRepository.deleteAll();
        this.exchangeTransactionRepository.deleteAll();
        this.accountRepository.deleteAll();
        this.clientRepository.deleteAll();
    }

    /**
     * Populates the clients table
     */
    private List<Client> populateClients() {

        Client client1 = new Client("Saief", "Zneti");
        Client client2 = new Client("Chouaib", "Mrabet");

        this.clientRepository.save(client1);
        this.clientRepository.save(client2);

        List<Client> clients = this.clientRepository.findAll();

        System.out.println(String.format("Created %d clients", clients.size()));

        for (Client client : clients) {
            System.out.println(client);
        }
		return clients;
    }

	/**
	 * Populates the accounts table. One account for each client.
	 * @param clients List of clients to generate accounts for
	 */
    private void populateAccounts(List<Client> clients) {
		for (Client client : clients) {
			Account account = new Account(client, 1000);
            this.accountRepository.save(account);
		}

		List<Account> accounts = this.accountRepository.findAll();

		System.out.println(String.format("Created %d accounts", accounts.size()));

		for (Account account : accounts) {
			System.out.println(account);
		}
	}


}
