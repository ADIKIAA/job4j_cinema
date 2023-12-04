package ru.job4j.cinema.dto;

import java.util.ArrayList;
import java.util.List;

public class HallDto {

    private int id;

    private String name;

    private int rowCount;

    private int placeCount;

    private String description;

    private List<Integer> rowList = new ArrayList<>();

    private List<Integer> placeList = new ArrayList<>();

    public HallDto(int id, String name, int rowCount, int placeCount, String description) {
        this.id = id;
        this.name = name;
        this.rowCount = rowCount;
        this.placeCount = placeCount;
        this.description = description;
        for (int i = 1; i <= rowCount; i++) {
            this.rowList.add(i);
        }
        for (int i = 1; i <= placeCount; i++) {
            this.placeList.add(i);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        rowList = new ArrayList<>();
        for (int i = 1; i <= rowCount; i++) {
            this.rowList.add(i);
        }
        this.rowCount = rowCount;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        placeList = new ArrayList<>();
        for (int i = 1; i <= placeCount; i++) {
            this.placeList.add(i);
        }
        this.placeCount = placeCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getRowList() {
        return rowList;
    }

    public List<Integer> getPlaceList() {
        return placeList;
    }

}
