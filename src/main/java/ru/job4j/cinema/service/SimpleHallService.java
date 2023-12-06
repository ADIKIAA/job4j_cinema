package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.HallDto;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SimpleHallService implements HallService {

    private final HallRepository hallRepository;

    public SimpleHallService(HallRepository sql2oHallRepository) {
        this.hallRepository = sql2oHallRepository;
    }

    @Override
    public Collection<HallDto> findAll() {
        var halls = hallRepository.findAll();
        return halls.stream().map(this::convertHallToHallDto).collect(Collectors.toList());
    }

    @Override
    public HallDto findById(int id) {
        var hall = hallRepository.findById(id);
        return convertHallToHallDto(hall);
    }

    private HallDto convertHallToHallDto(Hall hall) {
        List<Integer> rowList = IntStream.rangeClosed(1, hall.getRowCount()).boxed().toList();
        List<Integer> placeList = IntStream.rangeClosed(1, hall.getPlaceCount()).boxed().toList();
        return new HallDto(
                hall.getId(),
                hall.getName(),
                hall.getDescription(),
                rowList,
                placeList);
    }

}
