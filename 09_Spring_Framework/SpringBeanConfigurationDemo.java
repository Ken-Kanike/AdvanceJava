package spring_framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>Spring Java-Based Configuration & Bean Definitions</h1>
 * <p>
 * Demonstrates modern Spring Java-based configuration without XML:
 * <ul>
 *   <li><b>@Configuration</b>: Declaring class as a source of bean definitions.</li>
 *   <li><b>@Bean</b>: Factory method explicitly instantiating and configuring managed beans.</li>
 *   <li><b>@Primary & @Qualifier</b>: Resolving autowiring ambiguity when multiple bean candidates exist.</li>
 *   <li><b>@Value & Environment</b>: Externalized configuration and dynamic property injection.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class SpringBeanConfigurationDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Spring Java-Based Configuration & @Bean Demo  ");
        System.out.println("=================================================");

        // 1. Instantiate Application Configuration
        AppConfig config = new AppConfig();

        // 2. Obtain Configured Beans
        PaymentGateway primaryGateway = config.primaryPaymentGateway();
        PaymentGateway paypalGateway = config.paypalPaymentGateway();
        OrderProcessor orderProcessor = config.orderProcessor(primaryGateway);

        // 3. Process Transactions
        System.out.println("\n--- Processing Orders via Configured Payment Beans ---");
        orderProcessor.checkout("ORD-501", 249.99);

        OrderProcessor paypalProcessor = config.orderProcessor(paypalGateway);
        paypalProcessor.checkout("ORD-502", 99.50);

        // 4. Inspect Injected Configuration Properties
        System.out.println("\n--- Externalized Environment Properties (@Value) ---");
        System.out.println("• Database URL:        " + config.getDatabaseUrl());
        System.out.println("• Max Connection Pool: " + config.getMaxPoolSize());
        System.out.println("• Active Profile:      " + config.getActiveProfile());
    }
}

// =========================================================================
// PAYMENT GATEWAY CANDIDATE IMPLEMENTATIONS
// =========================================================================

interface PaymentGateway {
    String getProviderName();
    boolean processPayment(String orderId, double amount);
}

class StripePaymentGateway implements PaymentGateway {
    @Override
    public String getProviderName() {
        return "Stripe Enterprise Gateway (Primary)";
    }

    @Override
    public boolean processPayment(String orderId, double amount) {
        System.out.printf("  💳 [Stripe] Processed payment of $%,.2f for order '%s'%n", amount, orderId);
        return true;
    }
}

class PayPalPaymentGateway implements PaymentGateway {
    @Override
    public String getProviderName() {
        return "PayPal Digital Wallet Gateway";
    }

    @Override
    public boolean processPayment(String orderId, double amount) {
        System.out.printf("  🅿️ [PayPal] Processed payment of $%,.2f for order '%s'%n", amount, orderId);
        return true;
    }
}

class OrderProcessor {
    private final PaymentGateway paymentGateway;

    public OrderProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount) {
        System.out.println("Initiating checkout with Provider: " + paymentGateway.getProviderName());
        paymentGateway.processPayment(orderId, amount);
        System.out.println("✓ Order checkout successfully completed.\n");
    }
}

// =========================================================================
// SPRING CONFIGURATION CLASS (@Configuration & @Bean)
// =========================================================================

class AppConfig {
    // Simulated @Value properties
    private final String databaseUrl = System.getenv().getOrDefault("SPRING_DATASOURCE_URL", "jdbc:postgresql://localhost:5432/advance_spring_db");
    private final int maxPoolSize = 20;
    private final String activeProfile = "production";

    public PaymentGateway primaryPaymentGateway() {
        // Simulates @Bean @Primary
        return new StripePaymentGateway();
    }

    public PaymentGateway paypalPaymentGateway() {
        // Simulates @Bean @Qualifier("paypal")
        return new PayPalPaymentGateway();
    }

    public OrderProcessor orderProcessor(PaymentGateway gateway) {
        // Simulates @Bean with Dependency Injection
        return new OrderProcessor(gateway);
    }

    public String getDatabaseUrl() { return databaseUrl; }
    public int getMaxPoolSize() { return maxPoolSize; }
    public String getActiveProfile() { return activeProfile; }
}
