package java8.misuses.optional;

import java8.structures.Annotations.*;
import lombok.val;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class HundredAndOneApproach {
    @Ugly
    class SameOldImperativeStyle {
        public String getPersonCarInsuranceName(Person person) {
            String name = "Unknown";
            if (Optional.ofNullable(person).isPresent()) {
                if (person.getCar().isPresent()) {
                    if (person.getCar().get().getInsurance().isPresent()) {
                        name = person.getCar().get().getInsurance().get().getName();
                    }
                }
            }
            return name;
        }
    }

    @Ugly
    class UsingIfPresentInSameImperativeWayWithDirtyHack {
        public String getPersonCarInsuranceName(Person person) {
            final StringBuilder builder = new StringBuilder();
            Optional.ofNullable(person).ifPresent(
                    p -> p.getCar().ifPresent(
                            c -> c.getInsurance().ifPresent(
                                    i -> builder.append(i.getName())
                            )
                    )
            );
            return builder.toString();
        }
    }

    @Bad
    static class UsingMapWithUncheckedGet {
        public String getPersonCarInsuranceName(Person person) {
            return Optional.ofNullable(person)
//                    .map(it -> it.getCar().get().getInsurance().get().getName())
//                    .orElse("")
                    .map(Person::getCar)
                    .map(car -> car.get().getInsurance())
                    .map(insurance -> insurance.get().getName())
                    .orElse("Unknown");
        }
    }

    @Ugly
    static class UsingMapWithOrElseEmptyObjectToFixUncheckedGet {
        public String getPersonCarInsuranceName(Person person) {
            return Optional.ofNullable(person)
                    .map(Person::getCar)
                    .map(car -> car.orElseGet(Car::new).getInsurance())
                    .map(insurance -> insurance.orElseGet(Insurance::new).getName())
                    .orElse("Unknown");
        }
    }

    @Good
    static class UsingFlatMap {
        public String getCarInsuranceNameFromPersonUsingFlatMap(Person person) {
            return Optional.ofNullable(person)
                    .flatMap(Person::getCar)
                    .flatMap(Car::getInsurance)
                    .map(Insurance::getName)
                    .orElse("Unknown");
        }

    }

    @My
    static class MyClass {
        public String getCarInsuranceNameIf(Person1 person) {
            if (person != null) {
                if (person.getCar() != null) {
                    if (person.getCar().getInsurance() != null) {
                        String name = person.getCar().getInsurance().getName();
                        return name != null ? name : "Unknown";
                    } else return "Unknown";
                } else return "Unknown";
            } else return "Unknown";
        }

        public String getCarInsuranceNameIfNot(Person1 person) {
            if (person == null) return "Unknown";
            if (person.getCar() == null) return "Unknown";
            if (person.getCar().getInsurance() == null) return "Unknown";
            String name = person.getCar().getInsurance().getName();
            return name != null ? name : "Unknown";
        }

        public String getCarInsuranceNameWithoutOptional(Person1 person) {
            return Optional.ofNullable(person)
                    .map(Person1::getCar)
                    .map(Car1::getInsurance)
                    .map(Insurance1::getName)
                    .orElse("Unknown");
        }

        public String getCarInsuranceNameBinaryAnd(Person1 person) {
            return (person != null
                    && person.getCar() != null
                    && person.getCar().getInsurance() != null
                    && person.getCar().getInsurance().getName() != null)
                    ? person.getCar().getInsurance().getName()
                    : "Unknown";
        }

        public String getCarInsuranceNameBinaryOr(Person1 person) {
            return (person == null
                    || person.getCar() == null
                    || person.getCar().getInsurance() == null
                    || person.getCar().getInsurance().getName() == null)
                    ? "Unknown"
                    : person.getCar().getInsurance().getName();
        }
    }

    public static void main(String[] args) {
//        Optional<Person> person = Optional.of(new Person());
//        System.out.println(person);
//        System.out.println(person.get());
//        System.out.println(person.get().getCar());
//        System.out.println(person.get().getCar().get().getInsurance());

        val person = new Person();
        String rsl;
//        rsl = new UsingMapWithUncheckedGet().getPersonCarInsuranceName(person);
//        System.out.println(rsl);
//        rsl = new UsingMapWithOrElseEmptyObjectToFixUncheckedGet().getPersonCarInsuranceName(person);
//        System.out.println(rsl);
        rsl = new UsingFlatMap().getCarInsuranceNameFromPersonUsingFlatMap(person);
        System.out.println(rsl);
        rsl = new MyClass().getCarInsuranceNameWithoutOptional(new Person1());
        System.out.println(rsl);

//        val map = Map.<String, Object>of();
//        Optional.ofNullable(map.get("abc"))
//                .ifPresentOrElse(obj -> {}, () -> {});


//        val rsl = map.get("asdasd");
//        if (rsl != null) {

//        } else {

//        }

    }

    static class Person {
        Optional<Car> getCar() {
            return Optional.empty(); //stub
        }
    }

    static class Car {
        Optional<Insurance> getInsurance() {
            return Optional.empty(); //stub
        }
    }

    static class Insurance {
        String getName() {
            return ""; //stub
        }
    }

    // Not Optional structure

    static class Person1 {
        Car1 getCar() {
//            return null; //stub
            return new Car1(); //stub
        }
    }

    static class Car1 {
        Insurance1 getInsurance() {
//            return null; //stub
            return new Insurance1(); //stub
        }
    }

    static class Insurance1 {
        String getName() {
            return null; //stub
        }
    }
}
