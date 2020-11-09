package jsonmapping.models;

import jsonmapping.ann.MyJsonMap;

import static jsonmapping.ann.MapCase.JSON_FULL;
import static jsonmapping.ann.MapCase.JSON_NAME;


public class AnnotatedModel {
    @MyJsonMap(mapCases = JSON_FULL)
    private int id;
    @MyJsonMap(mapCases = {JSON_FULL, JSON_NAME})
    private String name;

    @MyJsonMap(mapCases = JSON_FULL)
    private OneMoreModel innerObj;


    public AnnotatedModel() {
    }

    public AnnotatedModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AnnotatedModel(int id, String name, OneMoreModel innerObj) {
        this.id = id;
        this.name = name;
        this.innerObj = innerObj;
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
}
