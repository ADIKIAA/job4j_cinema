package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;

import java.io.IOException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oGenreRepositoryTest {

    private static Sql2oGenreRepository sql2oGenreRepository;

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

        sql2oGenreRepository = new Sql2oGenreRepository(sql2o);
    }

    @Test
    public void findGenreById() {
        String name = "Приключенческий фильм";
        var rsl = sql2oGenreRepository.findById(2);
        assertThat(rsl.getName()).isEqualTo(name);
    }

    @Test
    public void findAllGenres() {
        assertThat(sql2oGenreRepository.findAll()).hasSize(3);
    }

}