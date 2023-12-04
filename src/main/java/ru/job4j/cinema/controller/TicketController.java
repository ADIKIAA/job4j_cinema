package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.dto.TicketDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    private final FilmSessionService filmSessionService;

    private final HallService hallService;

    public TicketController(TicketService ticketService,
                            FilmSessionService filmSessionService, HallService hallService) {
        this.ticketService = ticketService;
        this.filmSessionService = filmSessionService;
        this.hallService = hallService;
    }

    @GetMapping("/create/{id}")
    public String getTicketBuyPage(Model model, @PathVariable int id) {
        var fs = filmSessionService.findById(id).get();
        model.addAttribute("sess", fs);
        model.addAttribute("hall", hallService.findById(fs.getHallId()));
        return "tickets/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("sessionId") int sessionId,
                       @ModelAttribute("rowNumber") int row,
                       @ModelAttribute("placeNumber") int place,
                       @ModelAttribute("userId") int userId,
                       Model model) {
        Ticket ticket = new Ticket(sessionId, row, place, userId);
        if (ticketService.exist(sessionId, row, place)) {
            model.addAttribute("message", String.format("""
                    Не удалось приобрести билет на заданное место (Ряд %s Место %s).
                    Вероятно оно уже занято. 
                    Перейдите на страницу бронирования билетов и попробуйте снова.
                    """, row, place)
            );
            return "errors/404";
        }
        model.addAttribute("message",
                String.format("Вы успешно приобрели билет на такое место (Ряд %s Место %s).", row, place)
        );
        ticketService.save(ticket);
        return "/success/success_page";
    }

}
