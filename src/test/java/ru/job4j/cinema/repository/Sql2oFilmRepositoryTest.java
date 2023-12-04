package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;

import java.io.IOException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oFilmRepositoryTest {

    private static Sql2oFilmRepository sql2oFilmRepository;

    @BeforeAll
    public static void initRepository() throws IOException {
        var properties = new Properties();
        try (var inputStream = Sql2oUserRepository.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
    }

    @Test
    public void findFilmByName() {
        String name = "Оружейный барон";
        int year = 2005;
        var rsl = sql2oFilmRepository.findByName(name).get().getYear();
        assertThat(rsl).isEqualTo(year);
    }

    @Test
    public void findFilmById() {
        int id = 3;
        var rsl = sql2oFilmRepository.findById(id).get().getName();
        assertThat(rsl).isEqualTo("Без лица");
    }

    @Test
    public void findAllFilms() {
        var rsl = sql2oFilmRepository.findAll();
        assertThat(rsl).hasSize(3);
    }

}