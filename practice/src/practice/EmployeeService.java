package practice;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService extends Employee{
	
	static List<Employee> emps = Arrays.asList(
				new Employee("Chitra", 23), new Employee("Divya", 24), new Employee("Dhanunjaya", 26), new Employee("Eeshwar", 25),
				new Employee("Ashrit", 25),new Employee("Bhanu", 23), new Employee("Charan", 24),  new Employee("Charan", 25),
				new Employee("Bhushan", 24), new Employee("Bhuvan", 22), new Employee("Gayathri", 26), new Employee("Gayathri", 22),
				new Employee("Gayathri", 24), new Employee("Farhan", 27), new Employee("Guna", 28),  new Employee("Teju", 24),
				new Employee("Farhan", 26),new Employee("Ashrit", 26), new Employee("Amar", 21), new Employee("Anupam", 46),
				 new Employee("Teju", 21),  new Employee("Bhanu", 24), new Employee("Vaishali", 19)
				
			);
	
	public static void sortEmployees() {
		Collections.sort(emps);
	}
	
	public static List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,9));
	public static void main(String[] args) {
		//Collections.sort(emps);
		/*
		 * Collections.sort(emps, new Comparator<Employee>() {
		 * 
		 * @Override public int compare(Employee e1, Employee e2) { return
		 * e1.getAge().compareTo(e2.getAge()); } });
		 */
		
		Collections.sort(emps, (e1, e2) -> -e1.getAge().compareTo(e2.getAge()));
		
		
		String s = "j";
		String t = s.intern();
		
		//System.out.println(emps);
	}
	
	
   public void testMethod() throws FileNotFoundException {
		
	}
	

}
