package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;

import java.io.IOException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oHallRepositoryTest {

    private static Sql2oHallRepository sql2oHallRepository;

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

        sql2oHallRepository = new Sql2oHallRepository(sql2o);
    }

    @Test
    public void findHallById() {
        String name = "Зал №2";
        String description = "Средний зал";

        var rsl = sql2oHallRepository.findById(2);

        assertThat(rsl.getName()).isEqualTo(name);
        assertThat(rsl.getDescription()).isEqualTo(description);
    }

    @Test
    public void findAllHalls() {
        assertThat(sql2oHallRepository.findAll()).hasSize(3);
    }

}