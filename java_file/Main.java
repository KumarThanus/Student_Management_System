
// student file is private 
//stdent management is private
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        manager.loadSampleData();

        int choice;

        // do-while loop: menu repeats until Exit.
        do {
            displayMenu();
            choice = readInt("Enter your choice: ");

            // switch statement: controls menu operations.
            switch (choice) {
                case 1:
                    addRecord();
                    break;

                case 2:
                    manager.displayStudents();
                    break;

                case 3:
                    searchRecord();
                    break;

                case 4:
                    updateRecord();
                    break;

                case 5:
                    deleteRecord();
                    break;

                case 6:
                    System.out.println("\nThank you for using JKT Management System.");
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("\nInvalid choice! Please select 1-6.");
            }
        } while (choice != 6);

        input.close();
    }

    private static void displayMenu() {
        System.out.println("\n============================================");
        System.out.println("       JKT MANAGEMENT SYSTEM");
        System.out.println("============================================");
        System.out.println("1. Add Record");
        System.out.println("2. Display All Records");
        System.out.println("3. Search Record");
        System.out.println("4. Update Record");
        System.out.println("5. Delete Record");
        System.out.println("6. Exit");
        System.out.println("============================================");
    }

    // Method: Add Record
    private static void addRecord() {
        System.out.println("\n========== ADD STUDENT RECORD ==========");

        int id = readPositiveInt("Enter Student ID: ");

        if (manager.searchStudent(id) != null) {
            System.out.println("Error: A student with this ID already exists.");
            return;
        }

        String name = readNonEmptyString("Enter Student Name: ");
        int age = readAge("Enter Age: ");
        String course = readNonEmptyString("Enter Course: ");

        Student student = new Student(id, name, age, course);

        if (manager.addStudent(student)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Unable to add student. Storage may be full.");
        }
    }

    // Method: Search Record
    private static void searchRecord() {
        System.out.println("\n========== SEARCH STUDENT RECORD ==========");

        int id = readPositiveInt("Enter Student ID to search: ");
        Student student = manager.searchStudent(id);

        if (student != null) {
            System.out.println("\nStudent found:");
            student.displayStudent();
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method: Update Record
    private static void updateRecord() {
        System.out.println("\n========== UPDATE STUDENT RECORD ==========");

        int id = readPositiveInt("Enter Student ID to update: ");
        Student student = manager.searchStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        String name = readNonEmptyString("Enter New Student Name: ");
        int age = readAge("Enter New Age: ");
        String course = readNonEmptyString("Enter New Course: ");

        if (manager.updateStudent(id, name, age, course)) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Update failed.");
        }
    }

    // Method: Delete Record
    private static void deleteRecord() {
        System.out.println("\n========== DELETE STUDENT RECORD ==========");

        int id = readPositiveInt("Enter Student ID to delete: ");

        if (manager.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // while loop for valid integer input
    private static int readInt(String message) {
        while (true) {
            System.out.print(message);

            if (input.hasNextInt()) {
                int value = input.nextInt();
                input.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    }

    // if-else validation
    private static int readPositiveInt(String message) {
        while (true) {
            int value = readInt(message);

            if (value > 0) {
                return value;
            } else {
                System.out.println("Value must be greater than zero.");
            }
        }
    }

    private static int readAge(String message) {
        while (true) {
            int age = readInt(message);

            if (age >= 16 && age <= 100) {
                return age;
            } else {
                System.out.println("Age must be between 16 and 100.");
            }
        }
    }

    private static String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            } else {
                System.out.println("Input cannot be empty.");
            }
        }
    }
}
