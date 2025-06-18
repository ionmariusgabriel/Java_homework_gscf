package models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Builder
public class Produce {
    private String name;
    private Double price;
    private Enum<Category> category;

    @Override
    public String toString() {
        return "produce name: " + name + " | produce price: " + price + " | produce category: " + category;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        final Produce other = (Produce) object;
        if ((this.name == null) ? (other.price != null) : !this.name.equals(other.name)) {
            return false;
        }

        if (!Objects.equals(this.price, other.price)) {
            return false;
        }

        return Objects.equals(this.category, other.category);

    }


    public String compare(Object object) {
        List<String> diff = new ArrayList<>();
        final Produce other = (Produce) object;
        if (!Objects.equals(this.name, other.name)) {
            diff.add("produce name");
        }
        if (!Objects.equals(this.price, other.price)) {
            diff.add("produce price");
        }
        if (!Objects.equals(this.category, other.category)) {
            diff.add("produce category");
        }
        return diff.toString();
    }

}
