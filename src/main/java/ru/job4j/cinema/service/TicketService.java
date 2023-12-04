package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.TicketDto;
import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface TicketService {

    Ticket save(Ticket ticket);

    boolean exist(int sessionId, int rowNumber, int placeNumber);

    Collection<TicketDto> findAll();

    Optional<TicketDto> findById(int id);

    boolean delete(int id);

}
