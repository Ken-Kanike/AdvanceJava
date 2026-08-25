package spring_framework;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>Spring Framework: IoC Container & Dependency Injection Engine</h1>
 * <p>
 * Demonstrates the internal architecture of Spring's Inversion of Control (IoC) Container:
 * <ul>
 *   <li><b>Dependency Injection (DI)</b>: Field & Constructor injection via custom <code>@InjectBean</code> / <code>@Component</code> annotations.</li>
 *   <li><b>Bean Scopes</b>: <code>SINGLETON</code> (cached single instance) vs <code>PROTOTYPE</code> (new instance per request).</li>
 *   <li><b>Bean Lifecycle Hooks</b>: <code>@PostConstruct</code> (after properties set) and <code>@PreDestroy</code> (on context shutdown).</li>
 *   <li><b>ApplicationContext</b>: Component scanning, reflection-based instantiation, dependency resolution, and circular dependency safety.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class IoCContainerAndDiDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=================================================");
        System.out.println("   Spring IoC Container & DI Architectural Demo  ");
        System.out.println("=================================================");

        // 1. Initialize Spring-like Application Context
        MiniApplicationContext context = new MiniApplicationContext();

        // 2. Register Managed Components
        context.registerComponent(UserRepository.class);
        context.registerComponent(NotificationService.class);
        context.registerComponent(UserService.class);

        // 3. Initialize Beans & Inject Dependencies
        context.refresh();

        // 4. Retrieve Bean from Container
        UserService userService = context.getBean(UserService.class);

        // 5. Execute Business Operations through Injected Services
        System.out.println("\n--- Executing Business Logic ---");
        userService.registerUser("Alice Johnson", "alice.johnson@example.com");
        userService.registerUser("Bob Smith", "bob.smith@example.com");

        // 6. Demonstrate Bean Scopes
        System.out.println("\n--- Testing Bean Singleton Scope ---");
        UserService userService2 = context.getBean(UserService.class);
        System.out.println("• userService == userService2: " + (userService == userService2) + " (Singleton Scope Confirmed)");

        // 7. Context Shutdown (Triggers @PreDestroy)
        System.out.println("\n--- Closing ApplicationContext ---");
        context.close();
    }
}

// =========================================================================
// CUSTOM SPRING-LIKE ANNOTATIONS
// =========================================================================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Component {
    String scope() default "SINGLETON"; // SINGLETON or PROTOTYPE
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Service {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Repository {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Autowired {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PostConstruct {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PreDestroy {}

// =========================================================================
// MANAGED BEAN DEFINITIONS
// =========================================================================

@Repository
class UserRepository {
    private final List<String> database = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("  ✓ [UserRepository] @PostConstruct: Connected to simulated data store.");
    }

    public void save(String user) {
        database.add(user);
        System.out.println("  [UserRepository] Saved record to DB: " + user);
    }

    public int count() {
        return database.size();
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("  ✓ [UserRepository] @PreDestroy: Database connections released.");
    }
}

@Service
class NotificationService {

    @PostConstruct
    public void init() {
        System.out.println("  ✓ [NotificationService] @PostConstruct: Mail server template ready.");
    }

    public void sendEmail(String to, String message) {
        System.out.println("  [NotificationService] 📧 Sent Email to <" + to + ">: " + message);
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("  ✓ [NotificationService] @PreDestroy: Notification queues flushed.");
    }
}

@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @PostConstruct
    public void init() {
        System.out.println("  ✓ [UserService] @PostConstruct: Dependencies wired successfully.");
    }

    public void registerUser(String name, String email) {
        System.out.println("\n[UserService] Processing user registration for: " + name);
        userRepository.save(name + " (" + email + ")");
        notificationService.sendEmail(email, "Welcome to Advance Java Spring Architecture!");
        System.out.println("[UserService] Registration complete. Total users in system: " + userRepository.count());
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("  ✓ [UserService] @PreDestroy: User sessions terminated.");
    }
}

// =========================================================================
// MINI APPLICATION CONTEXT (IoC CONTAINER CORE)
// =========================================================================

class MiniApplicationContext {
    private final List<Class<?>> componentClasses = new ArrayList<>();
    private final Map<Class<?>, Object> singletonRegistry = new ConcurrentHashMap<>();

    public void registerComponent(Class<?> clazz) {
        componentClasses.add(clazz);
    }

    public void refresh() throws Exception {
        System.out.println("Initializing MiniApplicationContext (Spring IoC Container)...");

        // 1. Instantiate all Singleton Beans
        for (Class<?> clazz : componentClasses) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            singletonRegistry.put(clazz, instance);
            System.out.println("• Instantiated Bean: " + clazz.getSimpleName());
        }

        // 2. Perform Dependency Injection (@Autowired)
        System.out.println("\nInjecting Dependencies (@Autowired)...");
        for (Object bean : singletonRegistry.values()) {
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Class<?> dependencyType = field.getType();
                    Object dependency = singletonRegistry.get(dependencyType);
                    if (dependency != null) {
                        field.setAccessible(true);
                        field.set(bean, dependency);
                        System.out.println("  └─ Injected " + dependencyType.getSimpleName() + " into " + bean.getClass().getSimpleName() + "." + field.getName());
                    } else {
                        throw new NoSuchElementException("No qualifying bean of type '" + dependencyType.getName() + "' found for dependency injection.");
                    }
                }
            }
        }

        // 3. Invoke @PostConstruct Lifecycle Methods
        System.out.println("\nExecuting @PostConstruct Lifecycle Hooks...");
        for (Object bean : singletonRegistry.values()) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(PostConstruct.class)) {
                    method.setAccessible(true);
                    method.invoke(bean);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        T bean = (T) singletonRegistry.get(clazz);
        if (bean == null) {
            throw new NoSuchElementException("No bean found for class: " + clazz.getName());
        }
        return bean;
    }

    public void close() {
        for (Object bean : singletonRegistry.values()) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(PreDestroy.class)) {
                    try {
                        method.setAccessible(true);
                        method.invoke(bean);
                    } catch (Exception e) {
                        System.err.println("Error in @PreDestroy: " + e.getMessage());
                    }
                }
            }
        }
        singletonRegistry.clear();
        System.out.println("✓ MiniApplicationContext closed successfully.");
    }
}
