package java8.misuses.optional;

import java8.structures.Annotations.Bad;
import java8.structures.Annotations.Ugly;

import java.util.Optional;

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
    
    public static void main(String[] args) {
        String rsl;
        rsl = new UsingMapWithUncheckedGet().getPersonCarInsuranceName(null);
        System.out.println(rsl);
        rsl = new UsingMapWithOrElseEmptyObjectToFixUncheckedGet().getPersonCarInsuranceName(null);
        System.out.println(rsl);
        
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
}
