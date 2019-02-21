package practice;

import java.io.FileNotFoundException;

public class Employee implements Comparable<Employee>{
	
	private String name;
	private Integer age;
	private Double salary;
	public Employee() {}
	
	public Employee(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Employee e) {
		int c = this.getName().compareTo(e.getName());
		if(c == 0) {
			return - e.getAge().compareTo(this.getAge());
		}
		return c;
	}
	
	public  void testMethod() throws FileNotFoundException {
		
	}
	
	

}
