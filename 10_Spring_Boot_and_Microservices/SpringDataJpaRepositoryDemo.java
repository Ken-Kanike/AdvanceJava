package spring_boot_and_microservices;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>Spring Data JPA & Repository Pattern</h1>
 * <p>
 * Demonstrates Spring Data JPA architectural principles:
 * <ul>
 *   <li><b>@Entity & @Table</b>: Mapping Java domain objects to relational database tables.</li>
 *   <li><b>JpaRepository Interface</b>: Automatic CRUD method generation (<code>save</code>, <code>findById</code>, <code>findAll</code>, <code>deleteById</code>).</li>
 *   <li><b>Derived Query Methods</b>: Dynamic query generation from method signatures (e.g., <code>findByDepartment</code>, <code>findByGradeGreaterThan</code>).</li>
 *   <li><b>Pagination & Sorting</b>: Efficient batch retrieval and query ordering.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class SpringDataJpaRepositoryDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Spring Data JPA Repository & Query Derivation ");
        System.out.println("=================================================");

        StudentJpaRepository repository = new StudentJpaRepositoryImpl();

        // 1. Save Entities (EntityManager / JpaRepository.save)
        repository.save(new StudentEntity(1, "Alice Johnson", "Engineering", 96.5));
        repository.save(new StudentEntity(2, "Bob Smith", "Data Analytics", 88.0));
        repository.save(new StudentEntity(3, "Charlie Davis", "Engineering", 92.4));
        repository.save(new StudentEntity(4, "Diana Prince", "Cyber Security", 98.0));

        System.out.println("✓ 4 Entities persisted into simulated relational store.");

        // 2. Query All
        System.out.println("\n--- 1. findAll() ---");
        for (StudentEntity s : repository.findAll()) {
            System.out.println("• " + s);
        }

        // 3. Derived Query: findByDepartment
        System.out.println("\n--- 2. Derived Query: findByDepartment('Engineering') ---");
        for (StudentEntity s : repository.findByDepartment("Engineering")) {
            System.out.println("• " + s);
        }

        // 4. Derived Query: findByGradeGreaterThan(90.0)
        System.out.println("\n--- 3. Derived Query: findByGradeGreaterThan(90.0) ---");
        for (StudentEntity s : repository.findByGradeGreaterThan(90.0)) {
            System.out.println("• " + s);
        }

        // 5. Total Count
        System.out.println("\n• Total Records (count()): " + repository.count());
    }
}

// =========================================================================
// JPA ENTITY MODEL (@Entity, @Table, @Id)
// =========================================================================

class StudentEntity {
    private Integer id;
    private String name;
    private String department;
    private Double grade;

    public StudentEntity() {}

    public StudentEntity(Integer id, String name, String department, Double grade) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.grade = grade;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public Double getGrade() { return grade; }
    public void setGrade(Double grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("StudentEntity[ID=%d, Name='%s', Dept='%s', Grade=%.1f]", id, name, department, grade);
    }
}

// =========================================================================
// SPRING DATA JPA REPOSITORY INTERFACE
// =========================================================================

interface StudentJpaRepository {
    StudentEntity save(StudentEntity entity);
    Optional<StudentEntity> findById(Integer id);
    List<StudentEntity> findAll();
    void deleteById(Integer id);
    long count();

    // Derived Query Methods
    List<StudentEntity> findByDepartment(String department);
    List<StudentEntity> findByGradeGreaterThan(Double gradeThreshold);
}

class StudentJpaRepositoryImpl implements StudentJpaRepository {
    private final Map<Integer, StudentEntity> table = new HashMap<>();

    @Override
    public StudentEntity save(StudentEntity entity) {
        table.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<StudentEntity> findById(Integer id) {
        return Optional.ofNullable(table.get(id));
    }

    @Override
    public List<StudentEntity> findAll() {
        return new ArrayList<>(table.values());
    }

    @Override
    public void deleteById(Integer id) {
        table.remove(id);
    }

    @Override
    public long count() {
        return table.size();
    }

    @Override
    public List<StudentEntity> findByDepartment(String department) {
        return table.values().stream()
                .filter(s -> department.equalsIgnoreCase(s.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentEntity> findByGradeGreaterThan(Double gradeThreshold) {
        return table.values().stream()
                .filter(s -> s.getGrade() > gradeThreshold)
                .collect(Collectors.toList());
    }
}
