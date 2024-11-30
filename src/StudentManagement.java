import java.util.Arrays;

public class StudentManagement {
    private StudentStack students;

    public StudentManagement() {
        this.students = new StudentStack();
    }

    // Thêm một sinh viên
    public void addStudent(Student student) {
        students.push(student);
    }

    // Xóa một sinh viên
    public void removeStudent(String id) {
        StudentStack tempStack = new StudentStack();
        boolean found = false;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId().equals(id)) {
                found = true;
            } else {
                tempStack.push(student);
            }
        }

        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        if (found) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Cập nhật thông tin sinh viên
    public void updateStudent(String id, String name, double grade) {
        StudentStack tempStack = new StudentStack();
        boolean updated = false;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId().equals(id)) {
                student = new Student(id, name, grade);  // Cập nhật sinh viên
                tempStack.push(student);
                updated = true;
            } else {
                tempStack.push(student);
            }
        }

        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        if (updated) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Tìm kiếm sinh viên theo ID
    public Student searchStudent(String id) {
        for (Student student : students.toArray()) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;  // Nếu không tìm thấy sinh viên
    }

    // Hiển thị tất cả sinh viên
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students.toArray()) {
            System.out.println(student);
        }
    }

    // Sắp xếp sinh viên theo điểm giảm dần bằng Bubble Sort
    public void sortStudentsByGrade() {
        if (students.isEmpty()) {
            System.out.println("Student list is empty.");
            return;
        }

        // Chuyển stack thành mảng
        Student[] studentArray = new Student[students.size()];
        int index = 0;

        // Lấy tất cả sinh viên ra khỏi stack và lưu vào mảng
        while (!students.isEmpty()) {
            studentArray[index++] = students.pop();
        }

        int n = studentArray.length;

        // Bắt đầu đo thời gian
        long startTime = System.nanoTime();

        // Thuật toán Bubble Sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (studentArray[j].getGrade() < studentArray[j + 1].getGrade()) {
                    // Hoán đổi hai sinh viên
                    Student temp = studentArray[j];
                    studentArray[j] = studentArray[j + 1];
                    studentArray[j + 1] = temp;
                }
            }
        }

        // Kết thúc đo thời gian
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        // In thời gian thực thi
        System.out.println("Sorting execution time: " + elapsedTime + " nanoseconds");

        // Đưa các sinh viên đã sắp xếp trở lại stack
        for (Student student : studentArray) {
            students.push(student);
        }
    }









}
