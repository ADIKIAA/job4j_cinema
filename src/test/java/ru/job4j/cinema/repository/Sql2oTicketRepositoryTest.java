package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Ticket;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oTicketRepositoryTest {

    private static Sql2oTicketRepository sql2oTicketRepository;

    @BeforeAll
    public static void initRepository() throws IOException {
        var properties = new Properties();
        try (var inputStream = Sql2oUserRepository.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
    }

    @AfterEach
    public void deleteAllTickets() {
        var tickets = sql2oTicketRepository.findAll();
        for (Ticket ticket : tickets) {
            sql2oTicketRepository.deleteById(ticket.getId());
        }
    }

    @Test
    public void saveTicketThenFindThemById() {
        Ticket ticket = new Ticket(1, 1, 1, 1);
        var savedTicket = sql2oTicketRepository.save(ticket).get();
        assertThat(savedTicket).usingRecursiveComparison().isEqualTo(ticket);
    }

    @Test
    public void saveTicketThenTrySaveAgain() {
        Ticket ticket = new Ticket(1, 1, 1, 1);
        sql2oTicketRepository.save(ticket);

        var rsl = sql2oTicketRepository.save(ticket);

        assertThat(rsl).isEmpty();
    }

    @Test
    public void saveSomeTicketsThenDeleteOneAndFindAll() {
        Ticket ticket1 = new Ticket(1, 1, 1, 1);
        Ticket ticket2 = new Ticket(2, 2, 2, 2);
        Ticket ticket3 = new Ticket(3, 3, 3, 3);
        sql2oTicketRepository.save(ticket1);
        sql2oTicketRepository.save(ticket2);
        sql2oTicketRepository.save(ticket3);
        sql2oTicketRepository.deleteById(ticket3.getId());
        var rsl = sql2oTicketRepository.findAll();
        assertThat(rsl).usingRecursiveComparison().isEqualTo(List.of(ticket1, ticket2));
    }

}