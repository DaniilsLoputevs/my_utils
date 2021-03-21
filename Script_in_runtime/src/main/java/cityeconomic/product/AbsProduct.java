package cityeconomic.product;

import java.util.Objects;
import java.util.StringJoiner;

public abstract class AbsProduct {
    private String name;
    private int averagePrice;
    private int localPrice;

    public AbsProduct() {
    }

    public AbsProduct(String name, int averagePrice, int localPrice) {
        this.name = name;
        this.averagePrice = averagePrice;
        this.localPrice = localPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(int averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbsProduct that = (AbsProduct) object;
        return averagePrice == that.averagePrice &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, averagePrice);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AbsProduct.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("price=" + averagePrice)
                .toString();
    }
}
