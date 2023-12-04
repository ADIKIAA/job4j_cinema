package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface TicketRepository {

    Ticket save(Ticket ticket);

    boolean exist(int sessionId, int rowNumber, int placeNumber);

    Collection<Ticket> findAll();

    Optional<Ticket> findById(int id);

    boolean deleteById(int id);

}
