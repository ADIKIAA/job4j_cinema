package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;

public interface FilmSessionRepository {

    Collection<FilmSession> findAll();

    FilmSession findById(int id);

    Collection<FilmSession> findByHallId(int id);

    Collection<FilmSession> findByFilmId(int id);

}
