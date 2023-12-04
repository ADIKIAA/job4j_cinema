package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FilmSessionService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmSessionControllerTest {

    private FilmSessionController filmSessionController;

    private FilmSessionService filmSessionService;

    @BeforeEach
    public void initServices() {
        filmSessionService = mock(FilmSessionService.class);
        filmSessionController = new FilmSessionController(filmSessionService);
    }

    @Test
    public void whenRequestFilmSessionThenGetPageWithFilmSessions() {
        var session1 = new FilmSessionDto(1, 1, "name1", 1, "name1", LocalDateTime.now(), LocalDateTime.now(), 1);
        var session2 = new FilmSessionDto(2, 2, "name2", 2, "name2", LocalDateTime.now(), LocalDateTime.now(), 2);
        var expectedSessions = List.of(session1, session2);
        when(filmSessionService.findAll()).thenReturn(expectedSessions);

        var model = new ConcurrentModel();
        var view = filmSessionController.findAll(model);
        var actualSessions = model.getAttribute("sessions");

        assertThat(view).isEqualTo("sessions/list");
        assertThat(actualSessions).isEqualTo(expectedSessions);
    }

}