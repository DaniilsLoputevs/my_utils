package jsonmapping.models;

import jsonmapping.ann.MyJsonMap;

import java.util.Objects;
import java.util.StringJoiner;

import static jsonmapping.ann.MapCase.JSON_FULL;

public class OneMoreModel {
    @MyJsonMap(mapCases = JSON_FULL)
    private int id;
    @MyJsonMap(mapCases = JSON_FULL)
    private String made;
    @MyJsonMap(mapCases = JSON_FULL)
    private String target;

    public OneMoreModel() {
    }

    public OneMoreModel(int id, String made, String target) {
        this.id = id;
        this.made = made;
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        OneMoreModel that = (OneMoreModel) object;
        return id == that.id
                && Objects.equals(made, that.made)
                && Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, made, target);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OneMoreModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("made='" + made + "'")
                .add("target='" + target + "'")
                .toString();
    }
}
