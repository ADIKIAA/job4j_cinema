package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

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

        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void deleteUsers() {
        var users = sql2oUserRepository.findAll();
        for (User user : users) {
            sql2oUserRepository.deleteById(user.getId());
        }
    }

    @Test
    public void saveUserThenFindThem() {
        User user = new User("name", "email", "password");
        User savedUser = sql2oUserRepository.save(user).get();

        assertThat(savedUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void saveSomeUsersThenDeleteOne() {
        User user1 = new User("name1", "email1", "password1");
        User user2 = new User("name2", "email2", "password2");
        User user3 = new User("name3", "email3", "password3");
        sql2oUserRepository.save(user1);
        sql2oUserRepository.save(user2);
        sql2oUserRepository.save(user3);
        sql2oUserRepository.deleteById(user2.getId());

        var expected = List.of(user1, user3);
        var rsl = sql2oUserRepository.findAll();

        assertThat(rsl).isEqualTo(expected).doesNotContain(user2);
    }

    @Test
    public void saveUserThenUpdateThem() {
        User user = new User("name", "email", "password");
        sql2oUserRepository.save(user);
        int id = user.getId();
        User newUser = new User("new_name", "new_email", "new_password");
        newUser.setId(id);

        sql2oUserRepository.update(newUser);
        var rsl = sql2oUserRepository.findById(id).get();

        assertThat(rsl).usingRecursiveComparison().isEqualTo(newUser);
    }

    @Test
    public void saveSomeThenFindOneByEmailAndPassword() {
        User user1 = new User("name1", "email1", "password1");
        User user2 = new User("name2", "email2", "password2");
        sql2oUserRepository.save(user1);
        sql2oUserRepository.save(user2);

        var rsl = sql2oUserRepository.findByEmailAndPassword("email1", "password1").get();

        assertThat(rsl).isEqualTo(user1);
    }

    @Test
    public void saveTwoUsersWithSimilarEmail() {
        User user1 = new User("name1", "email", "password1");
        User user2 = new User("name2", "email", "password2");
        sql2oUserRepository.save(user1);

        var rsl = sql2oUserRepository.save(user2);

        assertThat(rsl).isEmpty();
    }

}