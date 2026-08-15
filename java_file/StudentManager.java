public class StudentManager {
    private Student[] students = new Student[20];
    private int count = 0;

    // Method 1: Add Record
    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }

        if (count < students.length && searchStudent(student.getStudentId()) == null) {
            students[count] = student;
            count++;
            return true;
        }
        return false;
    }

    // Method 2: Display All Records
    public void displayStudents() {

        if (count == 0) {
            System.out.println("\nNo student records found.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("           ALL STUDENT RECORDS");
        System.out.println("==============================================");

        System.out.printf("%-10s %-20s %-8s %-25s%n",
                "ID", "NAME", "AGE", "COURSE");

        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < count; i++) {

            System.out.printf("%-10d %-20s %-8d %-25s%n",
                    students[i].getStudentId(),
                    students[i].getStudentName(),
                    students[i].getAge(),
                    students[i].getCourse());
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("Total Student Records: " + count);
        System.out.println("==============================================");
    }

    // Method 3: Search Record
    public Student searchStudent(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId() == id) {
                return students[i];
            }
        }
        return null;
    }

    // Method 4: Update Record
    public boolean updateStudent(int id, String name, int age, String course) {
        Student student = searchStudent(id);

        if (student != null) {
            student.setStudentName(name);
            student.setAge(age);
            student.setCourse(course);
            return true;
        }
        return false;
    }

    // Method 5: Delete Record
    public boolean deleteStudent(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId() == id) {
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    // Load 10 records for Task 5.
    public void loadSampleData() {
        addStudent(new Student(101, "Kumar", 21, "Computer Science"));
        addStudent(new Student(102, "Siva", 22, "Information Technology"));
        addStudent(new Student(103, "Ravi", 20, "Software Engineering"));
        addStudent(new Student(104, "Nimal", 23, "Computer Science"));
        addStudent(new Student(105, "Arun", 21, "Information Technology"));
        addStudent(new Student(106, "David", 22, "Software Engineering"));
        addStudent(new Student(107, "Sara", 20, "Computer Science"));
        addStudent(new Student(108, "John", 23, "Information Technology"));
        addStudent(new Student(109, "Kamal", 21, "Software Engineering"));
        addStudent(new Student(110, "Raj", 22, "Computer Science"));
    }

    public int getCount() {
        return count;
    }
}
