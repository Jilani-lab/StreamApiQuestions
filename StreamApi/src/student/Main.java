package student;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<Student> list = Arrays.asList(
				new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
				new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
				new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
				new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
				new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
				new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
				new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
				new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
				new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
				new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));

		// Find list of students whose first name starts with alphabet A
		List<Student> students = list.stream().filter(st -> st.getFirstName().startsWith("A"))
				.collect(Collectors.toList());

		// Group The Student By Department Names
		Map<String, List<Student>> depStudent = list.stream()
				.collect(Collectors.groupingBy(Student::getDepartmantName));

		// Find the total count of student using stream
		long count = list.stream().count();

		// Find the max age of student
		OptionalInt maxAge = list.stream().mapToInt(dt -> dt.getAge()).max();
		System.out.println("Max Age " + maxAge.getAsInt());

		// Find all departments names
		List<String> departments = list.stream().map((st) -> st.getDepartmantName()).distinct()
				.collect(Collectors.toList());

		// Find the count of student in each department
		Map<String, Long> eachDept = list.stream()
				.collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()));

		// Find the list of students whose age is less than 30
		List<Student> belowAgeStu = list.stream().filter((st) -> st.getAge() < 30).collect(Collectors.toList());

		// Find the list of students whose rank is in between 50 and 100
		List<Student> student = list.stream().filter((st) -> st.getRank() < 50 && st.getRank() > 100)
				.collect(Collectors.toList());

		// Find the average age of male and female students
		Map<String, Double> avgStud = list.stream()
				.collect(Collectors.groupingBy(Student::getGender, Collectors.averagingDouble(Student::getAge)));

		// Find the department who is having maximum number of students
		Entry<String, Long> Maxdept = list.stream()
				.collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting())).entrySet().stream()
				.max(Map.Entry.comparingByValue()).get();

		// Find the Students who stays in Delhi and sort them by their names
		List<Student> sortName = list.stream().filter((st) -> st.getCity().equalsIgnoreCase("delhi"))
				.sorted(Comparator.comparing(Student::getFirstName)).collect(Collectors.toList());

		// Find the average rank in all departments
		Map<String, Double> avgDept = list.stream().collect(
				Collectors.groupingBy(Student::getDepartmantName, Collectors.averagingDouble(Student::getRank)));

		// Find the highest rank in each department
		Map<String, Optional<Student>> rankStudent = list.stream().collect(Collectors
				.groupingBy(Student::getDepartmantName, Collectors.minBy(Comparator.comparing(Student::getRank))));

//		Find the list of students and sort them by their rank
		List<Student> student1 = list.stream().sorted(Comparator.comparing(Student::getRank))
				.collect(Collectors.toList());

		// Find the student who has second rank
		Student secondRankStudent = list.stream().sorted(Comparator.comparing(Student::getRank)).skip(1).findFirst()
				.get();
	}

}
