package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;

@Repository
public class Sql2oFilmSessionRepository implements FilmSessionRepository {

    private final Sql2o sql2o;

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<FilmSession> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions");
            return query.setColumnMappings(FilmSession.COLUM_MAPPING).executeAndFetch(FilmSession.class);
        }
    }

    @Override
    public FilmSession findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "SELECT * FROM film_sessions WHERE id = :id")
                    .addParameter("id", id);
            return query.setColumnMappings(FilmSession.COLUM_MAPPING).executeAndFetchFirst(FilmSession.class);
        }
    }

    @Override
    public Collection<FilmSession> findByHallId(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "SELECT * FROM film_sessions WHERE hall_id = :hallId")
                    .addParameter("hallId", id);
            return query.setColumnMappings(FilmSession.COLUM_MAPPING).executeAndFetch(FilmSession.class);
        }
    }

    @Override
    public Collection<FilmSession> findByFilmId(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "SELECT * FROM film_sessions WHERE film_id = :filmId")
                    .addParameter("filmId", id);
            return query.setColumnMappings(FilmSession.COLUM_MAPPING).executeAndFetch(FilmSession.class);
        }
    }

}
