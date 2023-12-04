package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oTicketRepository implements TicketRepository {

    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Ticket save(Ticket ticket) {
        try (var connection = sql2o.open()) {
            String sql = """
                    INSERT INTO tickets (session_id, row_number, place_number, user_id)
                    VALUES (:sessionId, :rowNumber, :placeNumber, :userId)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("sessionId", ticket.getSessionId())
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("userId", ticket.getUserId());
            int generationKey = query.setColumnMappings(Ticket.COLUM_MAPPING).executeUpdate().getKey(Integer.class);
            ticket.setId(generationKey);
            return ticket;
        }
    }

    @Override
    public boolean exist(int sessionId, int rowNumber, int placeNumber) {
        try (var connection = sql2o.open();) {
            String sql = """
                    SELECT FROM tickets 
                    WHERE session_id = :sessionId 
                    AND row_number = :rowNumber
                    AND place_number = :placeNumber
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("sessionId", sessionId)
                    .addParameter("rowNumber", rowNumber)
                    .addParameter("placeNumber", placeNumber);
            return query.setColumnMappings(Ticket.COLUM_MAPPING).executeAndFetchFirst(Ticket.class) != null;
        }
    }

    @Override
    public Collection<Ticket> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "SELECT * FROM tickets");
            return query.setColumnMappings(Ticket.COLUM_MAPPING).executeAndFetch(Ticket.class);
        }
    }

    @Override
    public Optional<Ticket> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "SELECT * FROM tickets WHERE id = :id")
                    .addParameter("id", id);
            Ticket ticket = query.setColumnMappings(Ticket.COLUM_MAPPING).executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(ticket);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "DELETE FROM tickets WHERE id = :id")
                    .addParameter("id",  id);
            int affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

}
