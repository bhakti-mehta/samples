package test;

import com.google.common.base.*;
import com.google.common.collect.Collections2;

import java.util.Collection;
import java.util.List;

/**
 * Created by bhakti on 4/16/14.
 */
public class PersonSample {
    public static void main(String[] args) {
        PersonSample mt = new PersonSample();
        System.out.println("Here");
//        mt.go();
    }

    public Collection<Person> getPredicate() {
        final List<Person> persons = Person.createList();

        final Predicate<Person> isValuableMember = new Predicate<Person>() {
            public boolean apply(Person input) {
                return input.getAge() > 30;
            }

            public boolean test(Person person) {
                return person.getAge() > 30;

            }


        };

        Collection<Person> filteredPersons = Collections2.filter(persons,

                isValuableMember);
        return filteredPersons;
    }

    public Collection<Person> getMultiplePredicates() {
        final List<Person> persons = Person.createList();

        final Predicate<Person> ageOver30 = new Predicate<Person>() {
            public boolean apply(Person input) {
                return input.getAge() > 30;
            }

            public boolean test(Person person) {
                return person.getAge() > 30;

            }

        };
        final Predicate<Person> nameBeginsWith = new Predicate<Person>() {
            public boolean test(Person person) {
                return person.getLastName().startsWith("W");

            }

            public boolean apply(Person person) {
                return person.getLastName().startsWith("W");

            }
        };

        Collection<Person> filteredPersons = Collections2.filter(persons,
                Predicates.and(ageOver30, nameBeginsWith));
        return filteredPersons;
    }

    public Collection<Person> getSuffix() {
        final List<Person> persons = Person.createList();
        final Predicate<Person> isValuableMember = new Predicate<Person>() {
            public boolean apply(Person input) {
                return input.getAge() > 30;
            }

            public boolean test(Person person) {
                return person.getAge() > 30;

            }

        };
        Collection<Person> filteredPersons = Collections2.filter(persons,

                isValuableMember);
        return filteredPersons;
    }

    public int getSupplier() {
        Supplier<Person> anotherone = new Supplier<Person>() {
            public Person get() {
                return new Person("James", "Sculley", 53,Optional.of("Sr"));
            }
        };

        return anotherone.get().getAge();
    }

    public String getJoiner() {
        Joiner joiner = Joiner.on("; ");
        return joiner.join("Violet", "Indigo", "Blue", "Green", "Yellow", "Orange", "Red");
    }

}
