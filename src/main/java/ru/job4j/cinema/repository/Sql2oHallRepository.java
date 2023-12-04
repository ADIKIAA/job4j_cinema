package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.dto.HallDto;
import ru.job4j.cinema.model.Hall;

import java.util.Collection;

@Repository
public class Sql2oHallRepository implements HallRepository {

    private final Sql2o sql2o;

    public Sql2oHallRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<HallDto> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls");
            return query.setColumnMappings(Hall.COLUM_MAPPING).executeAndFetch(HallDto.class);
        }
    }

    @Override
    public HallDto findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery(
                    "SELECT * FROM halls WHERE id = :id"
            ).addParameter("id", id);
            return query.setColumnMappings(Hall.COLUM_MAPPING).executeAndFetchFirst(HallDto.class);
        }
    }

}
