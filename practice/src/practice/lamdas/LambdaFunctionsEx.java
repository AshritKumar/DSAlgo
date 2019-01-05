package practice.lamdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaFunctionsEx {
	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39)
				);
		
		// Step 1: Sort list by last name
		Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
		
		// Step 2: Create a method that prints all elements in the list
		// if we want to pass on a behaviour to the performConditionally method, like wethor to print to condole or to a file
		// we could pass it as a lambda expr using Consumer<T> interface of java.util.function package
		System.out.println("Printing all persons");
		printConditionally(people, p -> true, (p)-> System.out.println(p));
		
		// Step 3: Create a method that prints all people that have last name beginning with C
		System.out.println("Printing all persons with last name beginning with C");
		printConditionally(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p.getLastName()));

		System.out.println("Printing all persons with first name beginning with C");
		
		printConditionally(people, p -> p.getFirstName().startsWith("C"), p-> System.out.println(p.getFirstName()));
		
	}
	
	// for the common patters like testing a condition etc, we dont need to write an interface and define the method
	//java 8 already provides lots of interfaces for these purposes, present in java.util.function package
	private static void printConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> c) {
		for (Person p : people) {
			if (predicate.test(p)) {
				c.accept(p);
			}
		}
	}
	
}
