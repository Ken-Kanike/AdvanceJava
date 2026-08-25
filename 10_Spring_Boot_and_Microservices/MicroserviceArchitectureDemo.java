package spring_boot_and_microservices;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>Spring Cloud Microservices & Distributed Architecture</h1>
 * <p>
 * Demonstrates the core architectural pillars of Spring Cloud Microservices:
 * <ul>
 *   <li><b>Service Registry & Discovery (Netflix Eureka)</b>: Microservices registering heartbeats and discovering dynamic endpoints.</li>
 *   <li><b>API Gateway (Spring Cloud Gateway)</b>: Centralized routing, authentication filter, and load balancing.</li>
 *   <li><b>Circuit Breaker (Resilience4j)</b>: Fallback mechanisms preventing cascading service failures (CLOSED, OPEN, HALF-OPEN states).</li>
 *   <li><b>Spring Boot Actuator</b>: Production observability, health checks (<code>/actuator/health</code>), and readiness probes.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class MicroserviceArchitectureDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" Spring Cloud Microservices Architecture Engine  ");
        System.out.println("=================================================");

        // 1. Eureka Service Registry
        EurekaServiceRegistry registry = new EurekaServiceRegistry();
        registry.registerInstance("STUDENT-SERVICE", "http://10.0.0.12:8081");
        registry.registerInstance("STUDENT-SERVICE", "http://10.0.0.14:8081");
        registry.registerInstance("COURSE-SERVICE", "http://10.0.0.25:8082");
        registry.registerInstance("PAYMENT-SERVICE", "http://10.0.0.30:8083");

        System.out.println("\n--- 1. Eureka Service Registry Status ---");
        registry.printRegistry();

        // 2. API Gateway Routing with Load Balancing
        System.out.println("\n--- 2. Spring Cloud API Gateway Route Dispatch ---");
        ApiGateway gateway = new ApiGateway(registry);
        gateway.routeRequest("/api/v1/students");
        gateway.routeRequest("/api/v1/courses");

        // 3. Resilience4j Circuit Breaker
        System.out.println("\n--- 3. Resilience4j Circuit Breaker & Fallback ---");
        CircuitBreaker paymentCircuitBreaker = new CircuitBreaker("PaymentServiceCircuitBreaker");

        // Normal execution
        paymentCircuitBreaker.execute(() -> "Payment Processed ($150.00)", () -> "Fallback Payment Provider Executed");

        // Simulated downstream service failure
        System.out.println("\nSimulating Downstream Service Outage (Triggering Fallback)...");
        paymentCircuitBreaker.execute(
                () -> { throw new RuntimeException("503 Service Unavailable: Payment Gateway timeout"); },
                () -> "✓ [Resilience4j Fallback] Queued payment for async retry queue."
        );

        // 4. Spring Boot Actuator Health Check
        System.out.println("\n--- 4. Spring Boot Actuator (/actuator/health) ---");
        ActuatorHealthEndpoint actuator = new ActuatorHealthEndpoint(registry);
        System.out.println(actuator.getHealthJson());
    }
}

// =========================================================================
// EUREKA SERVICE REGISTRY SIMULATION
// =========================================================================

class EurekaServiceRegistry {
    private final Map<String, List<String>> registry = new ConcurrentHashMap<>();

    public void registerInstance(String serviceName, String instanceUrl) {
        registry.computeIfAbsent(serviceName, k -> new ArrayList<>()).add(instanceUrl);
    }

    public String discoverInstance(String serviceName) {
        List<String> instances = registry.get(serviceName);
        if (instances == null || instances.isEmpty()) {
            throw new NoSuchElementException("No active instances found for service: " + serviceName);
        }
        // Round-robin / random load balancing
        return instances.get(new Random().nextInt(instances.size()));
    }

    public void printRegistry() {
        registry.forEach((service, urls) -> {
            System.out.printf("• Service [%s]: %d active instance(s) -> %s%n", service, urls.size(), urls);
        });
    }

    public int getTotalInstances() {
        return registry.values().stream().mapToInt(List::size).sum();
    }
}

// =========================================================================
// API GATEWAY ROUTER
// =========================================================================

class ApiGateway {
    private final EurekaServiceRegistry registry;

    public ApiGateway(EurekaServiceRegistry registry) {
        this.registry = registry;
    }

    public void routeRequest(String path) {
        String targetService;
        if (path.startsWith("/api/v1/students")) {
            targetService = "STUDENT-SERVICE";
        } else if (path.startsWith("/api/v1/courses")) {
            targetService = "COURSE-SERVICE";
        } else {
            targetService = "UNKNOWN";
        }

        String targetUrl = registry.discoverInstance(targetService);
        System.out.printf("🌐 [API Gateway] Incoming '%s' -> Dispatched to [%s] at: %s%n", path, targetService, targetUrl);
    }
}

// =========================================================================
// RESILIENCE4J CIRCUIT BREAKER SIMULATION
// =========================================================================

class CircuitBreaker {
    private final String name;
    private int failureCount = 0;
    private String state = "CLOSED"; // CLOSED, OPEN, HALF_OPEN

    public CircuitBreaker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void execute(SupplierThrowable primary, Supplier<String> fallback) {
        try {
            if ("OPEN".equals(state)) {
                System.out.println("⚡ [" + name + ": OPEN] Bypassing target call directly to Fallback.");
                System.out.println("  Result: " + fallback.get());
                return;
            }

            String result = primary.get();
            System.out.println("✓ [" + name + ": CLOSED] Successful call: " + result);
            failureCount = 0;

        } catch (Exception ex) {
            failureCount++;
            System.err.println("⚠️ Target call failed: " + ex.getMessage());
            if (failureCount >= 2) {
                state = "OPEN";
                System.out.println("🚨 Circuit Breaker tripped to OPEN state! Protecting downstream cluster.");
            }
            System.out.println("  Fallback Execution: " + fallback.get());
        }
    }

    @FunctionalInterface
    interface SupplierThrowable {
        String get() throws Exception;
    }

    @FunctionalInterface
    interface Supplier<T> {
        T get();
    }
}

// =========================================================================
// SPRING BOOT ACTUATOR SIMULATION
// =========================================================================

class ActuatorHealthEndpoint {
    private final EurekaServiceRegistry registry;

    public ActuatorHealthEndpoint(EurekaServiceRegistry registry) {
        this.registry = registry;
    }

    public String getHealthJson() {
        return "{\n"
                + "  \"status\": \"UP\",\n"
                + "  \"components\": {\n"
                + "    \"db\": { \"status\": \"UP\", \"details\": { \"database\": \"PostgreSQL\", \"validationQuery\": \"isValid()\" } },\n"
                + "    \"diskSpace\": { \"status\": \"UP\", \"details\": { \"free\": \"84.5 GB\", \"threshold\": \"10.0 GB\" } },\n"
                + "    \"discoveryComposite\": { \"status\": \"UP\", \"details\": { \"registeredServices\": " + registry.getTotalInstances() + " } }\n"
                + "  }\n"
                + "}";
    }
}
