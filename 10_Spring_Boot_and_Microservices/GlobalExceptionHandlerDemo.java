package spring_boot_and_microservices;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * <h1>Spring Boot: Global Exception Handling (@RestControllerAdvice)</h1>
 * <p>
 * Demonstrates centralized, cross-cutting error handling for enterprise REST APIs:
 * <ul>
 *   <li><b>@RestControllerAdvice</b>: Intercepts exceptions thrown across all controller endpoints.</li>
 *   <li><b>@ExceptionHandler</b>: Maps specific exception types to HTTP status codes (e.g. 400, 404, 500).</li>
 *   <li><b>RFC 7807 / ProblemDetail</b>: Standardized JSON error response envelope.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class GlobalExceptionHandlerDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" Spring Boot Global Exception Handler & RFC 7807 ");
        System.out.println("=================================================");

        GlobalExceptionHandler advisor = new GlobalExceptionHandler();

        // 1. Simulating 404 Not Found Exception
        System.out.println("--- Scenario 1: Resource Not Found (404) ---");
        try {
            throw new NoSuchElementException("Student with ID 'STU-999' does not exist in registry.");
        } catch (NoSuchElementException ex) {
            ProblemDetail error = advisor.handleNotFoundException(ex);
            System.out.println(error);
        }

        // 2. Simulating 400 Bad Request / Validation Failure
        System.out.println("\n--- Scenario 2: Validation / Bad Request (400) ---");
        try {
            throw new IllegalArgumentException("Student grade cannot exceed 100.0 (received: 145.0).");
        } catch (IllegalArgumentException ex) {
            ProblemDetail error = advisor.handleBadRequestException(ex);
            System.out.println(error);
        }
    }
}

class ProblemDetail {
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
    private String timestamp;

    public ProblemDetail(String type, String title, int status, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
        this.timestamp = LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return String.format("{\n  \"type\": \"%s\",\n  \"title\": \"%s\",\n  \"status\": %d,\n  \"detail\": \"%s\",\n  \"instance\": \"%s\",\n  \"timestamp\": \"%s\"\n}",
                type, title, status, detail, instance, timestamp);
    }
}

class GlobalExceptionHandler {

    // @ExceptionHandler(NoSuchElementException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNotFoundException(NoSuchElementException ex) {
        return new ProblemDetail(
                "https://api.advancejava.dev/errors/not-found",
                "Resource Not Found",
                404,
                ex.getMessage(),
                "/api/v1/students/999"
        );
    }

    // @ExceptionHandler(IllegalArgumentException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleBadRequestException(IllegalArgumentException ex) {
        return new ProblemDetail(
                "https://api.advancejava.dev/errors/bad-request",
                "Invalid Request Payload",
                400,
                ex.getMessage(),
                "/api/v1/students"
        );
    }
}
