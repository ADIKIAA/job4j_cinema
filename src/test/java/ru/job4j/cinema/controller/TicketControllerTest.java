package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.dto.HallDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.TicketService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketControllerTest {

    private TicketService ticketService;

    private FilmSessionService filmSessionService;

    private HallService hallService;

    private TicketController ticketController;

    @BeforeEach
    public void initServices() {
        ticketService = mock(TicketService.class);
        filmSessionService = mock(FilmSessionService.class);
        hallService = mock(HallService.class);
        ticketController = new TicketController(ticketService, filmSessionService, hallService);
    }

    @Test
    public void whenRequestCreateTicketByFilmSessionThenGetTicketCreatePage() {
        var filmSessionDto = Optional.of(new FilmSessionDto(1, 1, "filmName", 1,
                "hallName", LocalDateTime.now(), LocalDateTime.now(), 1));
        when(filmSessionService.findById(anyInt())).thenReturn(filmSessionDto);
        var hallDto = new HallDto(1, "hallName", "description", List.of(1, 2, 3), List.of(1, 2, 3, 4));
        when(hallService.findById(anyInt())).thenReturn(hallDto);

        int id = 1;
        var model = new ConcurrentModel();
        var view = ticketController.getTicketBuyPage(model, id);
        var sess = model.getAttribute("sess");
        var hall = model.getAttribute("hall");

        assertThat(view).isEqualTo("tickets/create");
        assertThat(sess).isEqualTo(filmSessionDto.get());
        assertThat(hall).isEqualTo(hallDto);
    }

    @Test
    public void whenRequestSaveTicketThenSuccessfulPageAndMessage() {
        int sessionId = 1;
        int row = 1;
        int place = 1;
        int userId = 1;
        when(ticketService.save(any())).thenReturn(Optional.of(new Ticket(sessionId, row, place, userId)));

        var model = new ConcurrentModel();
        var view = ticketController.save(sessionId, row, place, userId, model);
        var message = model.getAttribute("message");

        assertThat(view).isEqualTo("/success/success_page");
        assertThat(message).isEqualTo("Вы успешно приобрели билет на такое место (Ряд 1 Место 1).");
    }

    @Test
    public void whenRequestSaveTicketThenFailGetFailPageWithMessage() {
        int sessionId = 1;
        int row = 1;
        int place = 1;
        int userId = 1;
        when(ticketService.save(any())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = ticketController.save(sessionId, row, place, userId, model);
        var message = model.getAttribute("message");

        assertThat(view).isEqualTo("errors/404");
        assertThat(message).isEqualTo("""
                    Не удалось приобрести билет на заданное место (Ряд 1 Место 1).
                    Вероятно оно уже занято.
                    Перейдите на страницу бронирования билетов и попробуйте снова.
                    """);
    }

}