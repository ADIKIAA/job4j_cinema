package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.HallDto;

import java.util.Collection;

public interface HallService {

    Collection<HallDto> findAll();

    HallDto findById(int id);

}
