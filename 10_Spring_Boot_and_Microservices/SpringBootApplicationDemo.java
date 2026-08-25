package spring_boot_and_microservices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Spring Boot: Core Architecture & Auto-Configuration</h1>
 * <p>
 * Demonstrates the internal mechanics of Spring Boot:
 * <ul>
 *   <li><b>@SpringBootApplication</b>: Composite annotation combining <code>@Configuration</code>, <code>@EnableAutoConfiguration</code>, and <code>@ComponentScan</code>.</li>
 *   <li><b>Embedded Web Server</b>: Auto-configuring embedded Tomcat/Jetty on port 8080.</li>
 *   <li><b>Externalized Configuration</b>: Resolving properties from <code>application.yml</code> / <code>application.properties</code>.</li>
 *   <li><b>CommandLineRunner / ApplicationRunner</b>: Executing startup tasks immediately after ApplicationContext boot.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class SpringBootApplicationDemo {

    public static void main(String[] args) {
        printSpringBootBanner();
        SpringBootContext bootContext = SpringBootContext.run(SpringBootApplicationDemo.class, args);

        System.out.println("✓ Spring Boot Application started successfully.");
        System.out.println("• Active Port: " + bootContext.getProperty("server.port", "8080"));
        System.out.println("• App Name:    " + bootContext.getProperty("spring.application.name", "advance-java-enterprise-service"));
        System.out.println("• Environment: " + bootContext.getProperty("spring.profiles.active", "production"));
    }

    private static void printSpringBootBanner() {
        System.out.println("  .   ____          _            __ _ _");
        System.out.println(" /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\");
        System.out.println("( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\");
        System.out.println(" \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )");
        System.out.println("  '  |____| .__|_| |_|_| |_\\__, | / / / /");
        System.out.println(" =========|_|==============|___/=/_/_/_/");
        System.out.println(" :: Spring Boot ::               (v3.2.0)\n");
    }
}

class SpringBootContext {
    private final Map<String, String> environmentProperties = new HashMap<>();

    private SpringBootContext() {
        // Simulates application.yml / properties parsing
        environmentProperties.put("server.port", "8080");
        environmentProperties.put("spring.application.name", "advance-java-enterprise-service");
        environmentProperties.put("spring.profiles.active", "production");
        environmentProperties.put("spring.datasource.url", "jdbc:postgresql://localhost:5432/advance_db");
        environmentProperties.put("management.endpoints.web.exposure.include", "health,info,metrics");
    }

    public static SpringBootContext run(Class<?> primarySource, String[] args) {
        System.out.println("1. Initializing SpringBootApplication from: " + primarySource.getSimpleName());
        System.out.println("2. Loading Auto-Configuration modules (WebMvc, DataJpa, Security, Actuator)...");
        System.out.println("3. Embedded Tomcat server initialized on port(s): 8080 (http)");
        return new SpringBootContext();
    }

    public String getProperty(String key, String defaultValue) {
        return environmentProperties.getOrDefault(key, defaultValue);
    }
}
