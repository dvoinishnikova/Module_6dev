package org.example;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:h2:mem:mydatabase";
    private static final String USERNAME = "myuser";
    private static final String PASSWORD = "mypassword";

    private Connection connection;
    private static Database instance;

    private Database() {
        migrateDatabase();
    }

    private void migrateDatabase() {
        Flyway flyway = new FluentConfiguration()
                .dataSource(URL, USERNAME, PASSWORD)
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        }
        return connection;
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}