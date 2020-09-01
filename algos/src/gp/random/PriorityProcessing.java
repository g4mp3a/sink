import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;

class Student {

    private int id;
    private String name;
    private double cgpa;

    public Student( String name, double cgpa, int id ) {
        this.id=id; this.name=name; this.cgpa=cgpa;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public double getCGPA() { return cgpa; }
}

class Priorities {

    public List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> pq = new PriorityQueue(Comparator
                        .comparing(Student::getCGPA).reversed()
                        .thenComparing(Student::getName)
                        .thenComparing(Student::getID));

        for (String e : events) {
            String[] tokens = e.split(" ");
            switch (tokens[0]) {
                case "SERVED": pq.poll();
                    break;
                case "ENTER": pq.add(
                    new Student(
                        tokens[1],
                        new Double(tokens[2]),
                        new Integer(tokens[3])));
            };
        }
        
        List<Student> result = new ArrayList<>();
        while (pq.size()!=0) result.add(pq.poll());
        return result;
    }
}


public class PriorityProcessing {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}