package java8.misuses.optional;

import java8.structures.Annotations.Bad;
import java8.structures.Annotations.Ugly;
import lombok.val;

import java.util.Map;
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
    
    public static void main(String[] args) {
//        Optional<Person> person = Optional.of(new Person());
//        System.out.println(person);
//        System.out.println(person.get());
//        System.out.println(person.get().getCar());
//        System.out.println(person.get().getCar().get().getInsurance());

        val person = new Person();
        String rsl;
        rsl = new UsingMapWithUncheckedGet().getPersonCarInsuranceName(person);
        System.out.println(rsl);
        rsl = new UsingMapWithOrElseEmptyObjectToFixUncheckedGet().getPersonCarInsuranceName(person);
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
}
