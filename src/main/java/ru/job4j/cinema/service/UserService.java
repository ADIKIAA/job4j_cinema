package ru.job4j.cinema.service;

import ru.job4j.cinema.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> save(User user);

    Collection<User> findAll();

    Optional<User> findById(int id);

    boolean deleteById(int id);

    Optional<User> findByEmailAndPassword(String email, String password);

    boolean update(User user);

}
