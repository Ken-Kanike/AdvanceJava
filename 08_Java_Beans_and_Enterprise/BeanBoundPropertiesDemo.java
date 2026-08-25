package java_beans_and_enterprise;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * <h1>JavaBean Bound Properties & Event Notification Demo</h1>
 * <p>
 * Demonstrates the JavaBean Bound Properties event model:
 * <ul>
 *   <li>Attaching a <code>PropertyChangeListener</code> to a JavaBean.</li>
 *   <li>Receiving <code>PropertyChangeEvent</code> with property name, old value, and new value.</li>
 *   <li>Validating event-driven decoupling between business models and observers.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class BeanBoundPropertiesDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   JavaBeans Bound Properties & Event Listener   ");
        System.out.println("=================================================");

        EmployeeBean employee = new EmployeeBean(101, "Alice Johnson", "Engineering", 90000.00);
        System.out.println("Initial Bean State: " + employee);

        // Register PropertyChangeListener observer
        employee.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("🔔 [PropertyChangeEvent Triggered]%n"
                                + "   • Property: '%s'%n"
                                + "   • Old Value: %s%n"
                                + "   • New Value: %s%n"
                                + "   • Source: %s%n%n",
                        evt.getPropertyName(), evt.getOldValue(), evt.getNewValue(), evt.getSource().getClass().getSimpleName());
            }
        });

        System.out.println("\n--- Modifying Bean Properties ---");
        // Changing salary triggers event
        employee.setSalary(98500.00);

        // Changing department triggers event
        employee.setDepartment("Cloud Architecture");

        System.out.println("Final Bean State: " + employee);
    }
}
