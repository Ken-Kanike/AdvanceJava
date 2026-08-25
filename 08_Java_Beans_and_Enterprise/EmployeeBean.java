package java_beans_and_enterprise;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * <h1>Enterprise JavaBean: EmployeeBean</h1>
 * <p>
 * Complies with the official JavaBeans Specification:
 * <ol>
 *   <li>Implements <code>java.io.Serializable</code> for state persistence and network transport.</li>
 *   <li>Provides a public, zero-argument default constructor.</li>
 *   <li>Encapsulates private instance fields with public getter and setter accessors.</li>
 *   <li>Supports <b>Bound Properties</b> via <code>PropertyChangeSupport</code> notification events.</li>
 * </ol>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // Encapsulated Properties
    private int empId;
    private String name;
    private String department;
    private double salary;

    // PropertyChangeSupport for Bound Properties
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Required zero-argument default constructor.
     */
    public EmployeeBean() {
        this.empId = 0;
        this.name = "";
        this.department = "General";
        this.salary = 0.0;
    }

    /**
     * Parameterized convenience constructor.
     */
    public EmployeeBean(int empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // PropertyChangeListener registration methods
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    // --- Standard Getters and Setters ---

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        int oldId = this.empId;
        this.empId = empId;
        propertyChangeSupport.firePropertyChange("empId", oldId, empId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldName, name);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        String oldDept = this.department;
        this.department = department;
        propertyChangeSupport.firePropertyChange("department", oldDept, department);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        double oldSalary = this.salary;
        this.salary = salary;
        // Fire bound property change event to all registered listeners
        propertyChangeSupport.firePropertyChange("salary", oldSalary, salary);
    }

    @Override
    public String toString() {
        return String.format("EmployeeBean[ID=%d, Name='%s', Dept='%s', Salary=$%,.2f]", empId, name, department, salary);
    }
}
