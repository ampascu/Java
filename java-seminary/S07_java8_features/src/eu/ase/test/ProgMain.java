package eu.ase.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

class Person {
	private int id;
	private String name;

	public int getId() {
		return this.id;
	}

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
}

@FunctionalInterface
interface MathOperation {
	//We need not to mention public and abstract in interface
    //as all the methods in interface are 
    //public and abstract by default so the compiler will
    //treat this as public abstract
	public abstract int operation(int a, int b);
}

class Math {
	public int doOperation(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}

//1. Parallel array sorting
//2. Date & Time API
//3. Lambda expressions
//4. Functional interfaces
public class ProgMain {

	public static void main(String[] args) {
		// 1. Parallel array sorting

		int[] numbers = { 1, 50, 30, 2, 40, 20, 16, 30 };
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}

		Arrays.parallelSort(numbers);

		System.out.println();
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();

		// 2. Date & Time API

		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);

		LocalDate localDate2 = LocalDate.of(2022, 04, 10);
		System.out.println(localDate2);

		LocalDate localDate3 = LocalDate.parse("2022-04-20");
		System.out.println(localDate3);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate4 = LocalDate.parse("10-04-2022", dtf);
		System.out.println(localDate4);

		LocalDate tomorrow = LocalDate.now().plusDays(1);
		System.out.println(tomorrow);

		System.out.println(tomorrow.getDayOfWeek());
		System.out.println(tomorrow.getDayOfMonth());
		System.out.println(tomorrow.getYear());

		LocalTime now = LocalTime.now();
		System.out.println(now);

		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		System.out.println(Period.between(localDate2, localDate3).getDays() + " days");

		// 3. Lambda expressions
		// parameter -> expression
		// (parameter1, parameter2) -> { code block }

		List<String> strings = Arrays.asList("cherry", "apple", "pear", "", "pineapple", "");

		long count = strings.stream().filter(string -> string.isEmpty()).count();
		System.out.println("Empty Strings: " + count);

		count = strings.stream().filter(string -> string.length() > 4).count();
		System.out.println("Strings of length > 4: " + count);

		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("Filtered List: " + filtered);

		List<Person> customers = Arrays.asList(new Person(1, "Ana"), new Person(2, "Mihai"), new Person(3, "Mirela"),
				new Person(1, "Ana"));
		List<Integer> personIds = customers.stream().map(person -> {
			return person.getId();
		}).distinct().collect(Collectors.toList());
		System.out.println("Person ids: " + personIds);

		IntSummaryStatistics stats = personIds.stream().mapToInt(x -> x).summaryStatistics();

		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
		System.out.println("Sum of all numbers : " + stats.getSum());
		System.out.println("Average of all numbers : " + stats.getAverage());

		//4. Functional interfaces
		//A functional interface in Java is an interface that contains only a single abstract
		//(unimplemented) method. 
		//A functional interface can contain default and static methods 
		//which do have an implementation, in addition to the single unimplemented method.
		
		//A Java functional interface can be implemented by a Java Lambda Expression.
		MathOperation addition = (int a, int b) -> a + b;
		MathOperation substraction = (a, b) -> a - b;
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};
		MathOperation division = (int a, int b) -> a/b;
		
		System.out.println("20 + 7 = " + new Math().doOperation(20, 7, addition));
		System.out.println("20 - 7 = " + new Math().doOperation(20, 7, substraction));
		System.out.println("20 * 7 = " + new Math().doOperation(20, 7, multiplication));
		System.out.println("20 / 7 = " + new Math().doOperation(20, 7, division));
	}

}
