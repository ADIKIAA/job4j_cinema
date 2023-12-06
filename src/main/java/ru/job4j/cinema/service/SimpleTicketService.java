package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.TicketDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.TicketRepository;
import ru.job4j.cinema.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimpleTicketService implements TicketService {

    private final TicketRepository ticketRepository;

    private final FilmSessionRepository filmSessionRepository;

    private final FilmService filmService;

    private final UserRepository userRepository;

    public SimpleTicketService(TicketRepository sql2oTicketRepository,
                               FilmSessionRepository sql2oFilmSessionRepository,
                               UserRepository sql2oUserRepository,
                               FilmService filmService) {
        this.ticketRepository = sql2oTicketRepository;
        this.filmSessionRepository = sql2oFilmSessionRepository;
        this.userRepository = sql2oUserRepository;
        this.filmService = filmService;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Collection<TicketDto> findAll() {
        return ticketRepository.findAll().stream()
                .map(this::convertTicketToTicketDto)
                .collect(Collectors.toList());
    }

    private TicketDto convertTicketToTicketDto(Ticket ticket) {
        FilmSession fs = filmSessionRepository.findById(ticket.getSessionId());
        FilmDto f = filmService.findById(fs.getFilmId()).get();
        User u = userRepository.findById(ticket.getUserId()).get();
        return new TicketDto(
                ticket.getId(),
                ticket.getSessionId(),
                f.getName(),
                fs.getStartTime(),
                fs.getEndTime(),
                fs.getPrice(),
                ticket.getUserId(),
                u.getFullName(),
                u.getEmail()
        );
    }

    @Override
    public Optional<TicketDto> findById(int id) {
        return ticketRepository.findById(id).map(this::convertTicketToTicketDto);
    }

    @Override
    public boolean delete(int id) {
        return ticketRepository.deleteById(id);
    }

}

