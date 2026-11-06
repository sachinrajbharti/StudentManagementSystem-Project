package StudentManagementSystem;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Student implements Serializable
{
    private static final long
    SerialVersionUID = 1L;
    int id;
    String name;
    int age;
    String course;

    Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
        System.out.println("Student Course: " + course);
        System.out.println("--------------------------------");
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static void saveStudents() {
    try {
        ObjectOutputStream out =
            new ObjectOutputStream(
                new FileOutputStream("students.dat")
            );

        out.writeObject(students);
        out.close();

        System.out.println("Data Saved Successfully!");

    } catch (IOException e) {
        System.out.println("Error Saving Data!");
    }
}

static void loadStudents() {
    try {
        ObjectInputStream in =
            new ObjectInputStream(
                new FileInputStream("students.dat")
            );

        students = (ArrayList<Student>) in.readObject();
        in.close();

        System.out.println("Data Loaded Successfully!");

    } catch (FileNotFoundException e) {
        System.out.println("No Previous Data Found!");

    } catch (Exception e) {
        System.out.println("Error Loading Data!");
    }
}

    static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    static void addStudent() {
        System.out.println("\n========== ADD STUDENT ==========");

        int id = readInt("Enter Student ID: ");

        if (findStudent(id) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int age = readInt("Enter Student Age: ");

        System.out.print("Enter Student Course: ");
        String course = sc.nextLine();

        Student s = new Student(id, name, age, course);
        students.add(s);
        saveStudents();

        System.out.println("\nStudent Added Successfully!");
        s.display();
    }

    static void viewStudents() {
        System.out.println("\n========== ALL STUDENTS ==========");

        if (students.isEmpty()) {
            System.out.println("No Student Records Found!");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {
        System.out.println("\n========== SEARCH STUDENT ==========");

        int id = readInt("Enter Student ID: ");

        Student s = findStudent(id);

        if (s != null) {
            System.out.println("Student Found!");
            s.display();
        } else {
            System.out.println("Student Not Found!");
        }
    }

    static void updateStudent() {
        System.out.println("\n========== UPDATE STUDENT ==========");

        int id = readInt("Enter Student ID: ");

        Student s = findStudent(id);

        if (s != null) {
            System.out.print("Enter New Student Name: ");
            s.name = sc.nextLine();

            s.age = readInt("Enter New Student Age: ");

            System.out.print("Enter New Student Course: ");
            s.course = sc.nextLine();
            saveStudents();
            System.out.println("Student Updated Successfully!");
        } else {
            System.out.println("Student Not Found!");
        }
    }

    static void deleteStudent() {
        System.out.println("\n========== DELETE STUDENT ==========");

        int id = readInt("Enter Student ID: ");

        Student s = findStudent(id);

        if (s != null) {
            students.remove(s);
            saveStudents();
            System.out.println("Student Deleted Successfully!");
        } else {
            System.out.println("Student Not Found!");
        }
    }

    public static void main(String[] args) {

        loadStudents();

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("      STUDENT MANAGEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit Application");
            System.out.println("==================================");

            choice = readInt("Enter Your Choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Thank You For Using Student Management System!");
                    break;

                default:
                    System.out.println("Invalid Choice! Please Try Again.");
            }

        } while (choice != 6);

        sc.close();
    }
}