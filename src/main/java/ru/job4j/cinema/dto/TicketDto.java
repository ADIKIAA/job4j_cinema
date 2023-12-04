package ru.job4j.cinema.dto;

import java.time.LocalDateTime;

public class TicketDto {

    private int id;

    private int sessionId;

    private String nameFilm;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

    private int rowNumber;

    private int placeNumber;

    private int userId;

    private String userFullName;

    private String userEmail;

    public TicketDto(int id, int sessionId, String nameFilm, LocalDateTime startTime,
                     LocalDateTime endTime, int price, int userId,
                     String userFullName, String userEmail) {
        this.id = id;
        this.sessionId = sessionId;
        this.nameFilm = nameFilm;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.userId = userId;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
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

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
