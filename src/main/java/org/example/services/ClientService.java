package org.example.services;

import org.example.Database;
import org.example.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    public long create(String name) throws SQLException {
        validateName(name);

        String sql = "INSERT INTO client (NAME) VALUES (?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
        }
    }

    public String getById(long id) throws SQLException {
        String sql = "SELECT NAME FROM client WHERE ID = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("NAME");
                } else {
                    throw new SQLException("Client not found.");
                }
            }
        }
    }

    public void setName(long id, String name) throws SQLException {
        validateName(name);

        String sql = "UPDATE client SET NAME = ? WHERE ID = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setLong(2, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Client not found.");
            }
        }
    }

    public void deleteById(long id) throws SQLException {
        String sql = "DELETE FROM client WHERE ID = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Client not found.");
            }
        }
    }

    public List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();

        String sql = "SELECT ID, NAME FROM client";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                clients.add(new Client(id, name));
            }
        }

        return clients;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }
}