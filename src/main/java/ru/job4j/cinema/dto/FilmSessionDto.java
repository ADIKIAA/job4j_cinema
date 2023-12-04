package ru.job4j.cinema.dto;

import java.time.LocalDateTime;

public class FilmSessionDto {

    private int id;

    private int filmId;

    private String filmName;

    private int hallId;

    private String hallName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

    public FilmSessionDto(int id, int filmId, String filmName, int hallId, String hallName,
                          LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.id = id;
        this.filmId = filmId;
        this.filmName = filmName;
        this.hallId = hallId;
        this.hallName = hallName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getHallName() {
        return hallName;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
