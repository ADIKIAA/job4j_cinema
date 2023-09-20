package ru.job4j.cinema.model;

import java.util.Map;

public class Ticket {

    private static final Map<String, String> COLUM_MAPPING = Map.of(
            "id", "id",
            "session_id", "sessionId",
            "place_number", "placeNumber",
            "user_id", "userId"
    );

    private int id;

    private int sessionId;

    private int placeNumber;

    private int userId;

    public Ticket(int sessionId, int placeNumber, int userId) {
        this.sessionId = sessionId;
        this.placeNumber = placeNumber;
        this.userId = userId;
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

}
