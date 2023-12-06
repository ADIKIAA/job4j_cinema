package ru.job4j.cinema.dto;

import java.util.ArrayList;
import java.util.List;

public class HallDto {

    private int id;

    private String name;

    private String description;

    private List<Integer> rowList = new ArrayList<>();

    private List<Integer> placeList = new ArrayList<>();

    public HallDto(int id, String name, String description, List<Integer> rowList, List<Integer> placeList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rowList = rowList;
        this.placeList = placeList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getRowList() {
        return rowList;
    }

    public void setRowList(List<Integer> rowList) {
        this.rowList = rowList;
    }

    public List<Integer> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Integer> placeList) {
        this.placeList = placeList;
    }

}
