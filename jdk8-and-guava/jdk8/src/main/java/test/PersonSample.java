package test;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;


/**
 * Created by bhakti on 4/16/14.
 */
public class PersonSample {
    public static void main(String[] args) {
        PersonSample ps = new PersonSample();

    }

    public Stream<Person> getPredicate() {
        final List<Person> persons = Person.createList();

        final Predicate<Person> ageOver30 = new Predicate<Person>() {
            public boolean test(Person person) {
                return person.getAge() > 30;

            }
        };

        Stream<Person> filteredPersons = persons.stream().filter(
                ageOver30);
        return filteredPersons;
    }

    public Stream<Person> getMultiplePredicates() {
        final List<Person> persons = Person.createList();

        final Predicate<Person> ageOver30 = new Predicate<Person>() {
            public boolean test(Person person) {
                return person.getAge() > 30;

            }
        };
        final Predicate<Person> nameBeginsWith = new Predicate<Person>() {
            public boolean test(Person person) {
                return person.getLastName().startsWith("W") ;

            }
        };

        Stream<Person> filteredPersons = persons.stream().filter(
                ageOver30.and(nameBeginsWith));
        return filteredPersons;
    }

    public Stream<Person> getSuffix() {
        final List<Person> persons = Person.createList();

        final Predicate<Person> ageOver30 = new Predicate<Person>() {
            public boolean test(Person person) {
                return person.getAge() > 30;

            }
        };


        Stream<Person> filteredPersons = persons.stream().filter(
                ageOver30);
        return filteredPersons;
    }

    public int getSupplier() {
        final List<Person> persons = Person.createList();
        Supplier<Person> anotherone = () -> { Person psn = new Person("James", "Sculley", 53, Optional.of("Sr"));
            return psn;
        };

        return anotherone.get().getAge();
    }


    public String getJoiner() {
        StringJoiner joiner = new StringJoiner("; ");
        return joiner.add("Violet").add( "Indigo").add( "Blue").add( "Green")
        .add("Yellow").add( "Orange").add( "Red").toString();
    }


}
