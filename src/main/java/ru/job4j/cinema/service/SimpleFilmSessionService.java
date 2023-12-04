package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.HallRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;

    private final FilmRepository filmRepository;

    private final HallRepository hallRepository;

    public SimpleFilmSessionService(FilmSessionRepository sql2oFilmSessionRepository,
                                    FilmRepository sql2oFilmRepository,
                                    HallRepository sql2oHallRepository) {
        this.filmSessionRepository = sql2oFilmSessionRepository;
        this.filmRepository = sql2oFilmRepository;
        this.hallRepository = sql2oHallRepository;
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        var fs = filmSessionRepository.findAll();
        var fsDto = fs.stream()
                .map((s) -> new FilmSessionDto(
                        s.getId(),
                        s.getFilmId(),
                        filmRepository.findById(s.getFilmId()).get().getName(),
                        s.getHallId(),
                        hallRepository.findById(s.getHallId()).getName(),
                        s.getStartTime(),
                        s.getEndTime(),
                        s.getPrice()))
                .collect(Collectors.toList());
        return fsDto;
    }

    @Override
    public Optional<FilmSessionDto> findById(int id) {
        var fs = filmSessionRepository.findById(id);
        var fsDTO = new FilmSessionDto(fs.getId(),
                fs.getFilmId(),
                filmRepository.findById(fs.getFilmId()).get().getName(),
                fs.getHallId(),
                hallRepository.findById(fs.getHallId()).getName(),
                fs.getStartTime(),
                fs.getEndTime(),
                fs.getPrice());
        return Optional.of(fsDTO);
    }

    @Override
    public Collection<FilmSessionDto> findByHallId(int id) {
        var fs = filmSessionRepository.findByHallId(id);
        var fsDto = fs.stream()
                .map((s) -> new FilmSessionDto(
                        s.getId(),
                        s.getFilmId(),
                        filmRepository.findById(s.getFilmId()).get().getName(),
                        s.getHallId(),
                        hallRepository.findById(s.getHallId()).getName(),
                        s.getStartTime(),
                        s.getEndTime(),
                        s.getPrice()))
                .collect(Collectors.toList());
        return fsDto;
    }

    @Override
    public Collection<FilmSessionDto> findByFilmId(int id) {
        var fs = filmSessionRepository.findByFilmId(id);
        var fsDto = fs.stream()
                .map((s) -> new FilmSessionDto(
                        s.getId(),
                        s.getFilmId(),
                        filmRepository.findById(s.getFilmId()).get().getName(),
                        s.getHallId(),
                        hallRepository.findById(s.getHallId()).getName(),
                        s.getStartTime(),
                        s.getEndTime(),
                        s.getPrice()))
                .collect(Collectors.toList());
        return fsDto;
    }

}
