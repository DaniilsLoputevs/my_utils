package cityeconomic;

import java.util.Objects;
import java.util.StringJoiner;

public class GeoPosition {
    public static GeoPosition of(int x, int y) {
        return new GeoPosition(x, y);
    }

    public static double distance(City from, City target) {
        return distance(from.getGeoPosition(), target.getGeoPosition());
    }
    /**
     * (x2-x1)*(x2-x1)-(y2-y1)*(y2-y1)
     *
     * @param from   -
     * @param target -
     * @return -
     */
    public static double distance(GeoPosition from, GeoPosition target) {
        return Math.sqrt(
                (target.x - from.x) * (target.x - from.x) + (target.y - from.y) * (target.y - from.y)
        );
    }


    private int x;
    private int y;

    public GeoPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GeoPosition that = (GeoPosition) object;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GeoPosition.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .toString();
    }
}
