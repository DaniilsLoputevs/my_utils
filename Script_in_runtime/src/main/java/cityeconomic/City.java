package cityeconomic;

import cityeconomic.product.AbsProduct;

import java.util.*;

public class City {
    private String name;
    private GeoPosition geoPosition;

    private Map<City, List<Route>> contactsAndRoutes;
    private int money;
    private List<AbsProduct> haveProduct = new ArrayList<>();

    public City(String name, GeoPosition geoPosition,
                Map<City, List<Route>> contactsAndRoutes, int money) {
        this.name = name;
        this.geoPosition = geoPosition;
        this.contactsAndRoutes = contactsAndRoutes;
        this.money = money;
    }


    /* METHODS */
    public void makeTicTurn() {
        for (var each : List.of()) {
            
        }

    }
    /* METHODS */


    public String getName() {
        return name;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public int getMoney() {
        return money;
    }

    public List<AbsProduct> getHaveProduct() {
        return haveProduct;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        City city = (City) object;
        return money == city.money &&
                Objects.equals(name, city.name) &&
                Objects.equals(geoPosition, city.geoPosition) &&
                Objects.equals(haveProduct, city.haveProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, geoPosition, money, haveProduct);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", City.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("geoPosition=" + geoPosition)
                .add("money=" + money)
                .add("haveProduct=" + haveProduct)
                .toString();
    }
}
