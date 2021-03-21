package cityeconomic;

import cityeconomic.product.AbsProduct;

import java.util.List;

public class Caravan {
    private String name;
    private Route route;
    private GeoPosition currentGeoPosition;
    private List<AbsProduct> products;

    /* FOR FUTURE */
//    private List<Machine> transports;
//    private List<Machine> convoy;

    public Caravan(String name, Route route, GeoPosition currentGeoPosition, List<AbsProduct> products) {
        this.name = name;
        this.route = route;
        this.currentGeoPosition = currentGeoPosition;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public GeoPosition getCurrentGeoPosition() {
        return currentGeoPosition;
    }

    public void setCurrentGeoPosition(GeoPosition currentGeoPosition) {
        this.currentGeoPosition = currentGeoPosition;
    }

    public List<AbsProduct> getProducts() {
        return products;
    }

    public void setProducts(List<AbsProduct> products) {
        this.products = products;
    }
}
