package spring_framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <h1>Spring AOP (Aspect-Oriented Programming) & Dynamic Proxies</h1>
 * <p>
 * Demonstrates cross-cutting concerns decoupled from core business logic:
 * <ul>
 *   <li><b>Aspect</b>: Modular unit encapsulating cross-cutting behavior (e.g., Performance Metrics, Logging, Security).</li>
 *   <li><b>JoinPoint & Pointcut</b>: Predicates matching target method executions.</li>
 *   <li><b>Advice Types</b>:
 *     <ul>
 *       <li><code>@Before</code>: Executes prior to target method.</li>
 *       <li><code>@AfterReturning</code>: Executes after successful method execution.</li>
 *       <li><code>@AfterThrowing</code>: Catches and processes unhandled exceptions.</li>
 *       <li><code>@Around</code>: Wraps execution to measure elapsed time or alter results.</li>
 *     </ul>
 *   </li>
 *   <li><b>JDK Dynamic Proxy</b>: Runtime proxy interception mechanism used by Spring AOP.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class SpringAopAspectDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Spring AOP & Cross-Cutting Concerns Showcase  ");
        System.out.println("=================================================");

        // 1. Create Target Service
        BankingService targetService = new BankingServiceImpl();

        // 2. Wrap Target with AOP Proxy Aspect
        BankingService proxiedService = LoggingAndSecurityAspect.createProxy(targetService, BankingService.class);

        // 3. Invoke Methods (Aspect automatically intercepts and logs before/after/around)
        System.out.println("\n--- Invocation 1: Successful Transfer ---");
        proxiedService.transferFunds(1001, 1002, 750.00);

        System.out.println("\n--- Invocation 2: Security Validation Failure ---");
        try {
            proxiedService.transferFunds(1001, 1002, -50.00); // Invalid amount triggers @AfterThrowing
        } catch (IllegalArgumentException e) {
            System.out.println("Handled in main: " + e.getMessage());
        }
    }
}

// =========================================================================
// TARGET SERVICE CONTRACT & IMPLEMENTATION
// =========================================================================

interface BankingService {
    void transferFunds(int fromAcc, int toAcc, double amount);
}

class BankingServiceImpl implements BankingService {
    @Override
    public void transferFunds(int fromAcc, int toAcc, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be strictly positive.");
        }
        System.out.printf("  [Business Logic] Transferred $%,.2f from Account #%d to #%d%n", amount, fromAcc, toAcc);
    }
}

// =========================================================================
// SPRING AOP ASPECT IMPLEMENTATION (DYNAMIC PROXY INTERCEPTOR)
// =========================================================================

class LoggingAndSecurityAspect implements InvocationHandler {
    private final Object target;

    public LoggingAndSecurityAspect(Object target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target, Class<T> interfaceType) {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                new LoggingAndSecurityAspect(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();

        // 1. @Before Advice
        System.out.println("🛡️ [@Before Advice] Security & Authorization Check passed for method: " + methodName + "()");

        // 2. @Around Advice (Timer start)
        long startTime = System.nanoTime();
        Object result = null;

        try {
            // Target method execution (ProceedingJoinPoint)
            result = method.invoke(target, args);

            // 3. @AfterReturning Advice
            System.out.println("✓ [@AfterReturning Advice] Method " + methodName + "() returned successfully.");

        } catch (Exception ex) {
            Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
            // 4. @AfterThrowing Advice
            System.err.println("⚡ [@AfterThrowing Advice] Exception intercepted in " + methodName + "(): " + cause.getMessage());
            throw cause;
        } finally {
            // @Around Completion (Timer end)
            long durationMicro = (System.nanoTime() - startTime) / 1000;
            System.out.println("⏱️ [@Around Advice] Method execution duration: " + durationMicro + " μs");
        }

        return result;
    }
}
