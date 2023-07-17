package org.example;

import org.example.models.Client;
import org.example.services.ClientService;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        try {
            long clientId = clientService.create("New Client");
            System.out.println("Created client ID: " + clientId);

            String clientName = clientService.getById(clientId);
            System.out.println("Client name: " + clientName);

            clientService.setName(clientId, "Updated Client");

            List<Client> clients = clientService.listAll();
            for (Client client : clients) {
                System.out.println("Client ID: " + client.getId() + ", Name: " + client.getName());
            }

            clientService.deleteById(clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}