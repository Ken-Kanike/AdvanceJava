package spring_boot_and_microservices;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>Spring Boot RESTful API Controller (@RestController)</h1>
 * <p>
 * Demonstrates modern REST API development with Spring Web MVC:
 * <ul>
 *   <li><b>@RestController</b>: Combines <code>@Controller</code> and <code>@ResponseBody</code> to serialize responses directly to JSON.</li>
 *   <li><b>HTTP Method Mappings</b>: <code>@GetMapping</code>, <code>@PostMapping</code>, <code>@PutMapping</code>, <code>@DeleteMapping</code>.</li>
 *   <li><b>Parameter Binding</b>: <code>@PathVariable</code> (URI path parameters), <code>@RequestParam</code> (query parameters), <code>@RequestBody</code> (JSON payload).</li>
 *   <li><b>ResponseEntity</b>: Crafting standard HTTP headers and status codes (200 OK, 201 Created, 404 Not Found).</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class SpringBootRestControllerDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  Spring Boot RESTful Web Service Controller API ");
        System.out.println("=================================================");

        StudentRestController controller = new StudentRestController();

        // 1. POST: Create New Student
        System.out.println("--- 1. POST /api/v1/students (Create Student) ---");
        StudentDto newStudent = new StudentDto(101, "Alice Johnson", "alice.j@example.com", "Computer Science", 96.5);
        ApiResponse<StudentDto> createResponse = controller.createStudent(newStudent);
        System.out.println("Status: " + createResponse.getStatus() + " | Body: " + createResponse.getData());

        // 2. GET: Retrieve All Students
        System.out.println("\n--- 2. GET /api/v1/students (Get All) ---");
        controller.createStudent(new StudentDto(102, "Bob Smith", "bob.s@example.com", "Data Analytics", 88.0));
        ApiResponse<List<StudentDto>> allStudents = controller.getAllStudents(null);
        for (StudentDto s : allStudents.getData()) {
            System.out.println("• " + s);
        }

        // 3. GET: Retrieve by ID (@PathVariable)
        System.out.println("\n--- 3. GET /api/v1/students/{id} (Path Variable) ---");
        ApiResponse<StudentDto> studentById = controller.getStudentById(101);
        System.out.println("Found: " + studentById.getData());

        // 4. PUT: Update Student
        System.out.println("\n--- 4. PUT /api/v1/students/{id} (Update) ---");
        newStudent.setGrade(99.0);
        ApiResponse<StudentDto> updateResponse = controller.updateStudent(101, newStudent);
        System.out.println("Updated: " + updateResponse.getData());

        // 5. DELETE: Remove Student
        System.out.println("\n--- 5. DELETE /api/v1/students/{id} (Delete) ---");
        ApiResponse<String> deleteResponse = controller.deleteStudent(102);
        System.out.println("Result: " + deleteResponse.getMessage());
    }
}

// =========================================================================
// DATA TRANSFER OBJECT (DTO) & RESPONSE ENVELOPE
// =========================================================================

class StudentDto {
    private int id;
    private String name;
    private String email;
    private String course;
    private double grade;

    public StudentDto() {}

    public StudentDto(int id, String name, String email, String course, double grade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.grade = grade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("StudentDto[ID=%d, Name='%s', Course='%s', Grade=%.1f]", id, name, course, grade);
    }
}

class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}

// =========================================================================
// REST CONTROLLER IMPLEMENTATION
// =========================================================================

class StudentRestController {
    private final Map<Integer, StudentDto> studentDatabase = new ConcurrentHashMap<>();

    // @PostMapping("/api/v1/students")
    public ApiResponse<StudentDto> createStudent(StudentDto student) {
        studentDatabase.put(student.getId(), student);
        return new ApiResponse<>(201, "Student created successfully", student);
    }

    // @GetMapping("/api/v1/students")
    public ApiResponse<List<StudentDto>> getAllStudents(String courseFilter) {
        List<StudentDto> list = new ArrayList<>(studentDatabase.values());
        return new ApiResponse<>(200, "Success", list);
    }

    // @GetMapping("/api/v1/students/{id}")
    public ApiResponse<StudentDto> getStudentById(int id) {
        StudentDto s = studentDatabase.get(id);
        if (s == null) {
            throw new NoSuchElementException("Student not found with ID: " + id);
        }
        return new ApiResponse<>(200, "Success", s);
    }

    // @PutMapping("/api/v1/students/{id}")
    public ApiResponse<StudentDto> updateStudent(int id, StudentDto updated) {
        studentDatabase.put(id, updated);
        return new ApiResponse<>(200, "Student updated successfully", updated);
    }

    // @DeleteMapping("/api/v1/students/{id}")
    public ApiResponse<String> deleteStudent(int id) {
        studentDatabase.remove(id);
        return new ApiResponse<>(200, "Student with ID " + id + " deleted successfully", null);
    }
}
