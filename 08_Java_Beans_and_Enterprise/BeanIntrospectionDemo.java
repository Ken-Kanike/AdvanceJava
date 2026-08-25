package java_beans_and_enterprise;

import java.beans.*;
import java.lang.reflect.Method;

/**
 * <h1>JavaBean Introspection & Reflection Demo</h1>
 * <p>
 * Demonstrates inspecting JavaBeans dynamically at runtime via the <code>java.beans.Introspector</code>:
 * <ul>
 *   <li>Extracting <code>BeanInfo</code> for any given Java class.</li>
 *   <li>Inspecting <code>PropertyDescriptor</code> (property names, getter methods, setter methods, property types).</li>
 *   <li>Inspecting <code>MethodDescriptor</code> (public exposed methods).</li>
 *   <li>Dynamically invoking accessors via Reflection.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class BeanIntrospectionDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     JavaBeans Introspection & Reflection        ");
        System.out.println("=================================================");

        try {
            // 1. Introspect EmployeeBean class (stop at Object.class)
            BeanInfo beanInfo = Introspector.getBeanInfo(EmployeeBean.class, Object.class);

            System.out.println("• Introspected Bean Class: " + beanInfo.getBeanDescriptor().getBeanClass().getName());

            // 2. Discover Properties
            System.out.println("\n--- Discovered Bean Properties (PropertyDescriptor) ---");
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor pd : properties) {
                System.out.println("┌ Property Name: " + pd.getName());
                System.out.println("├ Type:          " + pd.getPropertyType().getSimpleName());
                System.out.println("├ Read Method:   " + (pd.getReadMethod() != null ? pd.getReadMethod().getName() + "()" : "none"));
                System.out.println("└ Write Method:  " + (pd.getWriteMethod() != null ? pd.getWriteMethod().getName() + "(...)" : "none"));
                System.out.println();
            }

            // 3. Dynamic Invocation via Reflection
            System.out.println("--- Dynamic Invocation Demonstration ---");
            EmployeeBean sampleBean = new EmployeeBean(201, "Bob Smith", "Security", 85000.00);

            for (PropertyDescriptor pd : properties) {
                Method readMethod = pd.getReadMethod();
                if (readMethod != null) {
                    Object value = readMethod.invoke(sampleBean);
                    System.out.printf("• Dynamic Read [%s]: %s%n", pd.getName(), value);
                }
            }

        } catch (Exception e) {
            System.err.println("Introspection error: " + e.getMessage());
        }
    }
}
