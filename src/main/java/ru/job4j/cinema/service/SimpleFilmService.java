package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;

    private final FileService fileService;

    private final GenreRepository genreRepository;

    public SimpleFilmService(FilmRepository sql2oFilmRepository,
                             FileService fileService, GenreRepository sql2oGenreRepository) {
        this.filmRepository = sql2oFilmRepository;
        this.fileService = fileService;
        this.genreRepository = sql2oGenreRepository;
    }

    @Override
    public Collection<FilmDto> findAll() {
        var films = filmRepository.findAll();
        var filmsDto = films.stream()
                .map((f) -> new FilmDto(
                        f.getId(),
                        f.getName(),
                        f.getDescription(),
                        f.getYear(),
                        genreRepository.findById(f.getGenreId()).getName(),
                        f.getMinimalAge(),
                        f.getDurationInMinutes(),
                        f.getFileId()))
                .collect(Collectors.toList());
        return filmsDto;
    }

    @Override
    public Optional<FilmDto> findByName(String name) {
        var filmOptional = filmRepository.findByName(name);
        if (filmOptional.isEmpty()) {
            return Optional.empty();
        }
        Film f = filmOptional.get();
        return Optional.of(
                new FilmDto(
                        f.getId(),
                        f.getName(),
                        f.getDescription(),
                        f.getYear(),
                        genreRepository.findById(f.getGenreId()).getName(),
                        f.getMinimalAge(),
                        f.getDurationInMinutes(),
                        f.getFileId()
                ));
    }

    @Override
    public Optional<FilmDto> findById(int id) {
        var filmOptional = filmRepository.findById(id);
        if (filmOptional.isEmpty()) {
            return Optional.empty();
        }
        Film f = filmOptional.get();
        return Optional.of(
                new FilmDto(
                        f.getId(),
                        f.getName(),
                        f.getDescription(),
                        f.getYear(),
                        genreRepository.findById(f.getGenreId()).getName(),
                        f.getMinimalAge(),
                        f.getDurationInMinutes(),
                        f.getFileId()
                ));
    }

}
